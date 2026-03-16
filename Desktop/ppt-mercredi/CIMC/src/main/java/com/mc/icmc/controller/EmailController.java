package com.mc.icmc.controller;

import com.mc.icmc.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping("/send")
    public String sendTestEmail() {
        emailService.sendEmail(
                "ghazibenyahya2@gmail.com",  // remplace par ton mail test
                "Test Spring Boot",
                "Bonjour ! Ceci est un test d'envoi d'e-mail avec Spring Boot."
        );

        return "Email envoyé avec succès !";
    }
}
