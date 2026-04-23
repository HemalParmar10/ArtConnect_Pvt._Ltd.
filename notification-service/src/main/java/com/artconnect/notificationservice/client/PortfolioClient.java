package com.artconnect.notificationservice.client;

import com.artconnect.notificationservice.dto.ArtworkDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "portfolio-service")
public interface PortfolioClient {

    @GetMapping("/api/v1/artworks/{id}")
    ArtworkDTO getArtworkById(@PathVariable Long id);
}
