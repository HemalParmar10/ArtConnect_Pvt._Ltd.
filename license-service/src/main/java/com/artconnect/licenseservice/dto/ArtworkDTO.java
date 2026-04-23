package com.artconnect.licenseservice.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ArtworkDTO {

    private Long id;
    private String title;
    private String description;
    private Long artistId;
    private String imageUrl;
    private String resolution;
    private Set<String> tags;
    private Set<String> collections;
}
