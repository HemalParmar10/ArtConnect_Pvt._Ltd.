package com.artconnect.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long id;
    private String recipientEmail;
    private String subject;
    private String message;
    private String status;       // SENT / FAILED
    private String createdAt;
}
