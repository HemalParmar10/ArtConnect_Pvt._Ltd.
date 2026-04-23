package com.artconnect.notificationservice.service.impl;

import com.artconnect.notificationservice.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${notification.mail.from}")
    private String fromAddress;

    @Override
    public void sendEmail(String to, String subject, String body) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromAddress);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);

            log.info("✅ Email sent to {} | Subject: {}", to, subject);

        } catch (Exception e) {
            log.error("❌ Failed to send email to {} | Reason: {}", to, e.getMessage());
            // Re-throw so NotificationServiceImpl can catch it and mark status as FAILED
            throw new RuntimeException("Email sending failed: " + e.getMessage(), e);
        }
    }
}
