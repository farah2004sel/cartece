package com.example.cartecom.service;

public interface IEmailService {

    /**
     * Envoyer un email simple.
     *
     * @param to      destinataire
     * @param subject sujet de l'email
     * @param text    contenu du message
     */
    void sendEmail(String to, String subject, String text);
}