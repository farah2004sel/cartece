package com.example.cartecom.service;

import com.example.cartecom.entity.Commercant;
import com.example.cartecom.repository.CommercantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CommercantService {

    private final CommercantRepository commercantRepository;
    private final EmailService emailService;

    public CommercantService(CommercantRepository commercantRepository, EmailService emailService) {
        this.commercantRepository = commercantRepository;
        this.emailService = emailService;
    }

     public Commercant registerCommercant(Commercant commercant) {

        String code = generateVerificationCode();
        commercant.setVerificationCode(code);
        commercant.setEmailVerified(false);

        Commercant saved = commercantRepository.save(commercant);

         emailService.sendEmail(
                commercant.getEmail(),
                "Code de vérification",
                "Votre code de vérification est: " + code
        );

        return saved;
    }

     public boolean verifyEmail(String email, String code) {
        Commercant c = commercantRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("ce mail n'existe pas dans la base"));

        if (c.getVerificationCode() != null && c.getVerificationCode().equals(code)) {
            c.setEmailVerified(true);
            c.setVerificationCode(null);
            commercantRepository.save(c);
            return true;
        } else {
            return false;
        }
    }


     private String generateVerificationCode() {
        Random random = new Random();
        int num = 100000 + random.nextInt(900000);
        return String.valueOf(num);
    }
}
