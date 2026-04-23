package com.artconnect.licenseservice.dto;

import lombok.Data;

@Data
public class LicenseRequest {

    private Long artworkId;
    private String licenseType;
    private Double price;
}
