package com.sanger.muni.utils.mail;

public interface EmailService {
    public void sendMail(String toEmail, String subject, String message);
}
