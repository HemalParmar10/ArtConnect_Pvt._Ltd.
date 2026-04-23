package com.artconnect.notificationservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who the email was sent to
    private String recipientEmail;

    // Email subject line
    private String subject;

    @Column(length = 2000)
    private String message;

    // SENT or FAILED
    private String status;

    // Which artwork triggered this notification
    private Long artworkId;

    // Which artist owns the artwork
    private Long artistId;

    private LocalDateTime createdAt;
}
