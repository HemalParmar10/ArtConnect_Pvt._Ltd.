package com.artconnect.notificationservice.service.impl;

import com.artconnect.notificationservice.client.ArtistClient;
import com.artconnect.notificationservice.client.PortfolioClient;
import com.artconnect.notificationservice.dto.ArtistDTO;
import com.artconnect.notificationservice.dto.ArtworkDTO;
import com.artconnect.notificationservice.dto.NotificationRequest;
import com.artconnect.notificationservice.dto.NotificationResponse;
import com.artconnect.notificationservice.entity.Notification;
import com.artconnect.notificationservice.repository.NotificationRepository;
import com.artconnect.notificationservice.service.EmailService;
import com.artconnect.notificationservice.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;
    private final PortfolioClient portfolioClient;
    private final ArtistClient artistClient;

    @Override
    public NotificationResponse notifyArtworkUploaded(NotificationRequest request) {

        String status = "SENT";
        String subject = "";
        String body = "";
        String recipientEmail = request.getArtistEmail();
        Long artistId = null;

        try {
            // 1️ Fetch artwork details from portfolio-service via Feign
            ArtworkDTO artwork = portfolioClient.getArtworkById(request.getArtworkId());
            log.info("Fetched artwork: {} (id={})", artwork.getTitle(), artwork.getId());

            // 2️ Fetch artist details from artist-service via Feign
            ArtistDTO artist = artistClient.getArtistByEmail(request.getArtistEmail());
            artistId = artist.getId();
            recipientEmail = artist.getEmail();
            log.info("Fetched artist: {} (email={})", artist.getUsername(), artist.getEmail());

            // 3️ Build email subject and body
            subject = "🎨 Your Artwork \"" + artwork.getTitle() + "\" Has Been Uploaded!";

            body = buildEmailBody(artist, artwork);

            // 4️ Send the email
            emailService.sendEmail(recipientEmail, subject, body);

        } catch (Exception e) {
            log.error("Notification failed for artworkId={} | {}", request.getArtworkId(), e.getMessage());
            status = "FAILED";
            subject = subject.isBlank() ? "Artwork Upload Notification" : subject;
        }

        // 5️ Persist notification record (regardless of success/failure)
        Notification notification = Notification.builder()
                .recipientEmail(recipientEmail)
                .subject(subject)
                .message(body)
                .status(status)
                .artworkId(request.getArtworkId())
                .artistId(artistId)
                .createdAt(LocalDateTime.now())
                .build();

        Notification saved = notificationRepository.save(notification);

        return mapToResponse(saved);
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<NotificationResponse> getByRecipientEmail(String email) {
        return notificationRepository.findByRecipientEmail(email)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<NotificationResponse> getByArtistId(Long artistId) {
        return notificationRepository.findByArtistId(artistId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────

    private String buildEmailBody(ArtistDTO artist, ArtworkDTO artwork) {

        StringBuilder sb = new StringBuilder();
        sb.append("Hello ").append(artist.getUsername()).append(",\n\n");
        sb.append("Great news! Your artwork has been successfully uploaded to ArtConnect.\n\n");
        sb.append("─────────────────────────────\n");
        sb.append("🖼  Artwork Details\n");
        sb.append("─────────────────────────────\n");
        sb.append("Title       : ").append(artwork.getTitle()).append("\n");
        sb.append("Description : ").append(artwork.getDescription()).append("\n");
        sb.append("Resolution  : ").append(artwork.getResolution()).append("\n");

        if (artwork.getTags() != null && !artwork.getTags().isEmpty()) {
            sb.append("Tags        : ").append(String.join(", ", artwork.getTags())).append("\n");
        }
        if (artwork.getCollections() != null && !artwork.getCollections().isEmpty()) {
            sb.append("Collections : ").append(String.join(", ", artwork.getCollections())).append("\n");
        }

        sb.append("─────────────────────────────\n\n");
        sb.append("Your artwork is now live and visible to clients on the platform.\n\n");
        sb.append("Thank you for being part of ArtConnect!\n\n");
        sb.append("Best regards,\n");
        sb.append("The ArtConnect Team\n");

        return sb.toString();
    }

    private NotificationResponse mapToResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .recipientEmail(notification.getRecipientEmail())
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt() != null
                        ? notification.getCreatedAt().toString()
                        : null)
                .build();
    }
}
