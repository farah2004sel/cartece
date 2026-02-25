package com.example.cartecom.service.impl;

import com.example.cartecom.domain.Roles;
import com.example.cartecom.domain.Users;
import com.example.cartecom.dto.mapper.IUserMapper;
import com.example.cartecom.dto.request.LoginRequest;
import com.example.cartecom.dto.request.RegisterRequest;
import com.example.cartecom.dto.response.LoginResponse;
import com.example.cartecom.dto.response.RegisterResponse;
import com.example.cartecom.dto.response.UserResponse;
import com.example.cartecom.repository.IUserRepository;
import com.example.cartecom.service.IAuthService;
import com.example.cartecom.service.IRoleService;
import com.example.cartecom.service.IJwtService;
import com.example.cartecom.service.IEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private static final int MAX_LOGIN_ATTEMPTS = 5;
    private static final int LOCK_DURATION_MINUTES = 15;

    private final IUserMapper userMapper;
    private final IRoleService roleService;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;
    private final IEmailService emailService;

    // ================= REGISTER =================
    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByUserEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cet email est déjà utilisé");
        }

        Roles role = roleService.getRoleByName("COMMERCANT")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rôle COMMERCANT non trouvé"));

        Users user = userMapper.toEntity(request);
        user.setUserPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setUserEnabled(false);

         int min = 100000;
        int max = 999999;
        int codeNumber = (int) (Math.random() * (max - min + 1)) + min;
        user.setUserVerificationCode(String.valueOf(codeNumber));

        userRepository.save(user);

         emailService.sendEmail(
                user.getUserEmail(),
                "Code de vérification",
                "Votre code de vérification est : " + user.getUserVerificationCode()
        );

        return new RegisterResponse(
                "Inscription réussie. Vérifiez votre email.",
                user.getUserEmail(),
                false
        );
    }

    // ================= LOGIN =================
    @Override
    public LoginResponse login(LoginRequest request) {
        Optional<Users> optUser = userRepository.findByUserNameOrUserEmailIgnoreCase(request.getUsernameOrEmail());

        Users user = optUser.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants incorrects")
        );

        if (isAccountLocked(user)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Compte temporairement bloqué. Réessayez dans quelques minutes."
            );
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getUserPassword())) {
            incrementFailedLoginAttempts(user);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants incorrects");
        }

        resetLoginAttempts(user);

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                token,
                userMapper.toResponse(user),
                user.getUserEmail(),
                user.getRole().getRoleName(),
                user.isUserEnabled()
        );
    }

    // ================= RESET PASSWORD =================
    @Override
    @Transactional
    public void resetPassword(String email, String key, String newPassword) {
        Users user = findUserByEmail(email);

        if (!key.equals(user.getUserVerificationCode())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Code de réinitialisation invalide");
        }

        user.setUserPassword(passwordEncoder.encode(newPassword));
        user.setUserVerificationCode(null);
        userRepository.save(user);
    }

    @Override
    public boolean verifyResetKey(String email, String key) {
        Users user = findUserByEmail(email);
        return key.equals(user.getUserVerificationCode());
    }

    @Override
    @Transactional
    public void forgotPassword(String email) {
        Users user = findUserByEmail(email);

         int min = 100000;
        int max = 999999;
        int resetCodeNumber = (int) (Math.random() * (max - min + 1)) + min;
        user.setUserVerificationCode(String.valueOf(resetCodeNumber));

        userRepository.save(user);

        emailService.sendEmail(
                user.getUserEmail(),
                "Réinitialisation de mot de passe",
                "Utilisez ce code pour réinitialiser votre mot de passe : " + user.getUserVerificationCode()
        );
    }

    // ================= UTILS =================
    private Users findUserByEmail(String email) {
        return userRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
    }

    private boolean isAccountLocked(Users user) {
        return user.getUserLockUntil() != null && user.getUserLockUntil().isAfter(LocalDateTime.now());
    }

    private void resetLoginAttempts(Users user) {
        user.setUserFailedLoginAttempts(0);
        user.setUserLockUntil(null);
        userRepository.save(user);
    }

    private void incrementFailedLoginAttempts(Users user) {
        int attempts = user.getUserFailedLoginAttempts() + 1;
        user.setUserFailedLoginAttempts(attempts);

        if (attempts >= MAX_LOGIN_ATTEMPTS) {
            user.setUserLockUntil(LocalDateTime.now().plusMinutes(LOCK_DURATION_MINUTES));
            log.warn("Compte bloqué pour {} minutes → email: {}", LOCK_DURATION_MINUTES, user.getUserEmail());
        }

        userRepository.save(user);
    }

    // ================= USERS MANAGEMENT =================
    @Override
    @Transactional
    public UserResponse archiveUserById(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        user.setUserCancel(1); // Archivé
        userRepository.save(user);

        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsersOrderBySortOrderDesc() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "sortOrder"))
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }
}