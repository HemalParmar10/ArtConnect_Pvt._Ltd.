package com.artconnect.notificationservice.dto;

import lombok.Data;

@Data
public class ArtistDTO {

    private Long id;
    private String username;
    private String email;
    private String contact;
    private String city;
    private String bio;
    private String artistType;
}
