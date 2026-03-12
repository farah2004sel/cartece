package com.mc.icmc.service.impl;

import com.mc.icmc.domain.Roles;
import com.mc.icmc.domain.Users;
import com.mc.icmc.dto.mapper.IUserMapper;
import com.mc.icmc.dto.request.LoginRequest;
import com.mc.icmc.dto.request.RegisterRequest;
import com.mc.icmc.dto.response.LoginResponse;
import com.mc.icmc.dto.response.RegisterResponse;
import com.mc.icmc.dto.response.UserResponse;
import com.mc.icmc.error.exception.UserNotFoundException;
//import com.mc.icmc.repository.IConcoursEmployeRepository;
import com.mc.icmc.repository.IUserRepository;
import com.mc.icmc.service.IAuthService;
import com.mc.icmc.service.IRoleService;
import com.mc.icmc.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.security.SecureRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserMapper userMapper;
    private final IRoleService roleService;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailServiceImpl emailService;
    private final com.mc.icmc.repository.ICommercantRepository commercantRepository;
    private static final String CHARACTERS =
            "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789";

    private static final SecureRandom secureRandom = new SecureRandom();

    private String generateSecureCode(int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }
    @Override
    public RegisterResponse register(RegisterRequest request) {
        // Vérifier si username ou email existe déjà
        if (userRepository.existsByUserName(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username is already taken!");
        }
        if (userRepository.existsByUserEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email is already taken!");
        }

        // Récupérer le rôle par ID
        Roles role = roleService.getRoleById(request.getRoleId()); // <-- méthode à créer si elle n'existe pas

        // Mapper RegisterRequest → User
        Users user = userMapper.toEntity(request);
        user.setRole(role);

        // Encoder le mot de passe
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        //save code and time code
        user.setUserCode(generateSecureCode(6));
        // FIX: Add 2 hours to compensate for any UTC/Local timezone mismatch bugs during development
        user.setUserCodeDateExp(LocalDateTime.now().plusHours(2));

        // Sauvegarder l'utilisateur
        Users savedUser = userRepository.save(user);

        // Sauvegarder Commercant si le rôle est COMMERCANT
        if (role != null && "COMMERCANT".equalsIgnoreCase(role.getRoleName())) {
            com.mc.icmc.domain.Commercant commercant = new com.mc.icmc.domain.Commercant();
            commercant.setUser(savedUser);
            commercant.setTypePersonne(request.getTypePersonne());
            commercant.setSociete(request.getSociete());
            commercant.setNationaliteId(request.getNationaliteId());
            commercantRepository.save(commercant);
        }

        // Mapper User → UserResponse
        UserResponse userResp = userMapper.toResponse(savedUser);

        //Send mail
        try {
            emailService.sendEmail(
                    user.getUserEmail(),
                    "Code de vérification",
                    "Votre code de vérification est : " + user.getUserCode()
            );
        } catch (Exception e) {
            log.error("Erreur lors de l'envoi de l'email", e);
        }
        return new RegisterResponse("User registered successfully", userResp);
    }

//    @Override
//    public RegisterResponse registerEmploye(RegisterEmployeRequest request) {
//        // Vérifier si username ou email existe déjà
//        if (userRepository.existsByUsername(request.getUsername())) {
//            throw new RuntimeException("Username is already taken!");
//        }
//        if (userRepository.existsByEmail(request.getEmail())) {
//            throw new RuntimeException("Email is already taken!");
//        }
//
//        // Récupérer le rôle par ID
//        Roles role = roleService.getRoleById(request.getRoleId()); // <-- méthode à créer si elle n'existe pas
//
//        // Mapper RegisterRequest → User
//        Users user = userMapper.RegisterEmployeToEntity(request);
//        user.setRole(role);
//
//        // Encoder le mot de passe
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        // Récupérer le max actuel du sort order et l'incrémenter
//        int maxSortOrder = userRepository.findMaxSortOrder();
//        user.setSortOrder(maxSortOrder + 1);
//
//        // Sauvegarder l'utilisateur
//        Users savedUser = userRepository.save(user);
//
//        //  Récupérer l'employé à partir de l'ID
//        ConcoursEmploye employe = employeRepository.findById(request.getEmployeId())
//                .orElseThrow(EmployeNotFoundException::new);
//
//        //  Lier le compte utilisateur à l’employé
//        employe.setUser(savedUser);
//        employeRepository.save(employe);
//
//        // Mapper User → UserResponse
//        UserResponse userResp = userMapper.toResponse(savedUser);
//
//        return new RegisterResponse("User registered successfully", userResp);
//    }

//    @Override
//    public LoginResponse login(LoginRequest request) {
//        // Récupérer l'utilisateur par username ou email
//        Users users = userRepository.findByUsername(request.getUsernameOrEmail())
//                .orElseGet(() -> userRepository.findByEmail(request.getUsernameOrEmail())
//                        .orElseThrow(() -> new RuntimeException("User not found")));
//
//        // Vérifier le mot de passe
//        if (!passwordEncoder.matches(request.getPassword(), users.getPassword())) {
//            throw new RuntimeException("Invalid credentials");
//        }
//
//        // Générer le JWT token
//        String token = jwtService.generateToken(users);
//
//        // Mapper User → UserResponse
//        UserResponse userResp = userMapper.toResponse(users);
//
//        return new LoginResponse(token, userResp);
//    }

    @Override
    public LoginResponse login(LoginRequest request) {

        Users user = userRepository.findByUserName(request.getUsernameOrEmail())
                .orElseGet(() -> userRepository.findByUserEmail(request.getUsernameOrEmail())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Login ou mot de passe incorrect !"
                        ))
                );

        //  Vérifier si le compte est bloqué
        if (isAccountLocked(user)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Compte temporairement indisponible. Veuillez réessayer ultérieurement."
            );
        }

        //  Mot de passe incorrect
        if (!passwordEncoder.matches(request.getPassword(), user.getUserPassword())) {
            incrementLoginAttempts(user, 5, 15); // 5 tentatives → 15 min
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Login ou mot de passe incorrect."
            );
        }

        //  Login réussi → reset
        resetLoginAttempts(user);

        //  Générer token
        String token = jwtService.generateToken(user);

        UserResponse userResp = userMapper.toResponse(user);

        return new LoginResponse(token, userResp);
    }


    @Override
    public List<UserResponse> getAllUsersOrderBySortOrderDesc() {
        return userRepository.findAllActiveUsersOrderBySortOrderDesc()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }


    //Archive utilisateur
    @Override
    public UserResponse archiveUserById(Long id) {
        // Récupérer l’utilisateur
        Users user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        user.setUserCancel(1);

        // Sauvegarde de la mise à jour
        Users updatedUser = userRepository.save(user);

        // Retourner le DTO UserResponse
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void forgotPassword(String email) {

        // chercher user
        Users user = userRepository.findByUserEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Aucun compte trouvé avec cet email.")
                );

        // générer code sécurisé
        String resetCode = generateSecureCode(6);

        // sauvegarder code
        user.setUserKey(resetCode);
        userRepository.save(user);

        // envoyer email
        try {
            emailService.sendEmail(
                    user.getUserEmail(),
                    "Réinitialisation du mot de passe",
                    "Votre code de réinitialisation est : " + resetCode
            );
        } catch (Exception e) {
            log.error("Erreur lors de l'envoi de l'email", e);
        }
    }

    @Override
    public boolean verifyResetKey(String email, String key) {
        Users user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Aucun compte trouvé avec cet email."));

        if (user.getUserKey() == null || !user.getUserKey().equals(key)) {
            throw new RuntimeException("Clé invalide ou expirée.");
        }

        return true;
    }

    @Override
    public boolean verifyConfirmeCompte(String email, String code) {

        // 1️ Chercher user par email
        Users user = userRepository.findByUserEmail(email)
                .orElseThrow(UserNotFoundException::new);

        // 2 Vérifier si code correspond
        if (user.getUserCode() == null || !user.getUserCode().equals(code)) {
            System.out.println("VERIFICATION FAILED: Code mismatch. Expected: " + user.getUserCode() + ", Got: " + code);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Code invalide");
        }

        // 3️ Vérifier expiration
        if (user.getUserCodeDateExp() == null ||
                user.getUserCodeDateExp().isBefore(LocalDateTime.now())) {
            System.out.println("VERIFICATION FAILED: Code expired. Exp Date: " + user.getUserCodeDateExp() + ", Now: " + LocalDateTime.now());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Code expiré");
        }

        // 4️ Confirmer le compte
        user.setUserConfirmedAt(LocalDateTime.now());

        // 5️ Nettoyer le code
        user.setUserCode(null);
        user.setUserCodeDateExp(null);

        userRepository.save(user);

        return true;
    }


    @Override
    public void resetPassword(String email, String key, String newPassword) {
        Users user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Aucun compte trouvé avec cet email."));

        if (user.getUserKey() == null || !user.getUserKey().equals(key)) {
            throw new RuntimeException("Clé invalide ou expirée.");
        }

        // Encoder le nouveau mot de passe
        user.setUserPassword(passwordEncoder.encode(newPassword));

        // Supprimer la clé après usage
        user.setUserKey(null);

        // Sauvegarder
        userRepository.save(user);
    }

    @Override
    public void resendVerificationCode(String email) {
        Users user = userRepository.findByUserEmail(email)
                .orElseThrow(UserNotFoundException::new);
        String newCode = generateSecureCode(6);
        user.setUserCode(newCode);
        user.setUserCodeDateExp(LocalDateTime.now().plusHours(2));

        userRepository.save(user);
        emailService.sendEmail(
                user.getUserEmail(),
                "Code de vérification",
                "Votre nouveau code est : " + newCode
        );
    }


    private boolean isAccountLocked(Users user) {
        return user.getUserLockUntil() != null
                && user.getUserLockUntil().isAfter(LocalDateTime.now());
    }

    private void resetLoginAttempts(Users user) {
        user.setUserFailedLoginAttempts(0);
        user.setUserLockUntil(null);
        userRepository.save(user);
    }

    private void incrementLoginAttempts(Users user, int maxAttempts, int lockMinutes) {
        int attempts = user.getUserFailedLoginAttempts() + 1;
        user.setUserFailedLoginAttempts(attempts);

        if (attempts >= maxAttempts) {
            user.setUserLockUntil(LocalDateTime.now().plusMinutes(lockMinutes));
        }

        userRepository.save(user);
    }



}