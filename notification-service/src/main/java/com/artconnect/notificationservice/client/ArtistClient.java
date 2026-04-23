package com.artconnect.notificationservice.client;

import com.artconnect.notificationservice.dto.ArtistDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "artist-service")
public interface ArtistClient {

    @GetMapping("/api/v1/artists/{id}")
    ArtistDTO getArtistById(@PathVariable Long id);

    @GetMapping("/api/v1/artists/by-email")
    ArtistDTO getArtistByEmail(@RequestParam String email);
}
