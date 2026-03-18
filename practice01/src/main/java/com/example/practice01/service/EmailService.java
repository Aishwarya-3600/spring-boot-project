package com.example.practice01.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String to, String subject, String text) throws Exception {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom("your.email@gmail.com"); // Set your email

            mailSender.send(message);
            log.info("Email sent successfully");
        }catch (Exception e) {
            log.error("Error sending email: ", e);
            throw new Exception("Failed to send email");
        }
    }
}
