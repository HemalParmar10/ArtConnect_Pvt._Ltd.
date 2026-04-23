package com.artconnect.artistservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.artconnect.artistservice.dto.ArtworkResponseDTO;

import java.util.List;

@FeignClient(name = "portfolio-service")
public interface PortfolioClient {

    @GetMapping("/api/v1/artworks/artist/{artistId}")
    List<ArtworkResponseDTO> getArtworksByArtist(@PathVariable Long artistId);

    @GetMapping("/api/v1/artworks/{id}")
    ArtworkResponseDTO getArtworkById(@PathVariable Long id);
}
