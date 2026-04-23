package com.artconnect.notificationservice.dto;

import lombok.Data;

@Data
public class NotificationRequest {

    // The ID of the newly created artwork
    private Long artworkId;

    // The email of the artist who uploaded the artwork (extracted from JWT in portfolio-service)
    private String artistEmail;
}
