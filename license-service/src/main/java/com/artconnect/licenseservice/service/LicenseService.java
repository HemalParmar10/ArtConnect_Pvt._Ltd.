package com.artconnect.licenseservice.service;

import com.artconnect.licenseservice.entity.License;

import java.util.List;

public interface LicenseService {

    // Create License
    License createLicense(License license);

    // Get all licenses
    List<License> getAllLicenses();

    // Get licenses by artist
    List<License> getByArtist(Long artistId);

    // Get license by ID
    License getById(Long id);

    // Delete license
    void deleteLicense(Long id);
}
