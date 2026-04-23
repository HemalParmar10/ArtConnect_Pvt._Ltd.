package com.artconnect.licenseservice.client;

import com.artconnect.licenseservice.dto.ArtworkDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "portfolio-service")
public interface ArtworkClient {

    @GetMapping("/api/v1/artworks/{id}")
    ArtworkDTO getArtwork(@PathVariable Long id);
}
