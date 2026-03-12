package com.example.cartecom.controller;

import com.example.cartecom.entity.Commercant;
import com.example.cartecom.service.CommercantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commercants")
@CrossOrigin(origins = "http://localhost:4200")
public class CommercantController {

    @Autowired
    private CommercantService commercantService;

    @PostMapping("/register")
    public Commercant registerCommercant(@RequestBody Commercant commercant) {
        try {
            return commercantService.registerCommercant(commercant);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam String email, @RequestParam String code) {
        boolean verified = commercantService.verifyEmail(email, code);
        if (verified) {
            return "Email vérifié avec succès !";
        } else {
            return "Code invalide !";
        }
    }


}
