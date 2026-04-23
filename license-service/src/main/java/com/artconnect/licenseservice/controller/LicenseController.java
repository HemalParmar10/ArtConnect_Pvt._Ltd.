package com.artconnect.licenseservice.controller;

import com.artconnect.licenseservice.client.ArtistClient;
import com.artconnect.licenseservice.dto.ArtistDTO;
import com.artconnect.licenseservice.dto.LicenseRequest;
import com.artconnect.licenseservice.entity.License;
import com.artconnect.licenseservice.service.LicenseService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/licenses")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService service;
    private final ArtistClient artistClient;

    @PostMapping
    public ResponseEntity<License> createLicense(
            @RequestBody LicenseRequest request,
            Authentication authentication) {

        // Extract email from JWT
        String email = authentication.getName();

        // CALL ARTIST SERVICE
        ArtistDTO artist = artistClient.getArtistByEmail(email);

        License license = License.builder()
                .artworkId(request.getArtworkId())
                .artistId(artist.getId())
                .licenseType(request.getLicenseType())
                .price(request.getPrice())
                .build();

        return ResponseEntity.ok(service.createLicense(license));
    }

    @GetMapping
    public ResponseEntity<List<License>> getAllLicenses() {
        return ResponseEntity.ok(service.getAllLicenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<License> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<License>> getByArtist(@PathVariable Long artistId) {
        return ResponseEntity.ok(service.getByArtist(artistId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLicense(@PathVariable Long id) {
        service.deleteLicense(id);
        return ResponseEntity.ok("License deleted successfully");
    }
}
