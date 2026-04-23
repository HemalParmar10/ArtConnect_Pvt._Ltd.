package com.artconnect.notificationservice.controller;

import com.artconnect.notificationservice.dto.NotificationRequest;
import com.artconnect.notificationservice.dto.NotificationResponse;
import com.artconnect.notificationservice.service.NotificationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/artwork")
    public ResponseEntity<NotificationResponse> notifyArtworkUploaded(
            @RequestBody NotificationRequest request) {

        return ResponseEntity.ok(notificationService.notifyArtworkUploaded(request));
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAll() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<NotificationResponse>> getByArtist(@PathVariable Long artistId) {
        return ResponseEntity.ok(notificationService.getByArtistId(artistId));
    }

    @GetMapping("/email")
    public ResponseEntity<List<NotificationResponse>> getByEmail(@RequestParam String email) {
        return ResponseEntity.ok(notificationService.getByRecipientEmail(email));
    }
}
