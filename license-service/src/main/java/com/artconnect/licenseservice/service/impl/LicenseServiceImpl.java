package com.artconnect.licenseservice.service.impl;

import com.artconnect.licenseservice.entity.License;
import com.artconnect.licenseservice.repository.LicenseRepository;
import com.artconnect.licenseservice.service.LicenseService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository repository;

    @Override
    public License createLicense(License license) {

        // Basic validation
        if (license.getArtworkId() == null) {
            throw new IllegalArgumentException("Artwork ID is required");
        }

        if (license.getArtistId() == null) {
            throw new IllegalArgumentException("Artist ID is required");
        }

        if (license.getLicenseType() == null || license.getLicenseType().isBlank()) {
            throw new IllegalArgumentException("License type is required");
        }

        if (license.getPrice() == null || license.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        return repository.save(license);
    }

    @Override
    public List<License> getAllLicenses() {
        return repository.findAll();
    }

    @Override
    public List<License> getByArtist(Long artistId) {

        if (artistId == null) {
            throw new IllegalArgumentException("Artist ID cannot be null");
        }

        return repository.findByArtistId(artistId);
    }

    @Override
    public License getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("License not found with id: " + id));
    }

    @Override
    public void deleteLicense(Long id) {

        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("License not found with id: " + id);
        }

        repository.deleteById(id);
    }
}
