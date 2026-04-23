package com.artconnect.notificationservice.service;

import com.artconnect.notificationservice.dto.NotificationRequest;
import com.artconnect.notificationservice.dto.NotificationResponse;

import java.util.List;

public interface NotificationService {

	NotificationResponse notifyArtworkUploaded(NotificationRequest request);

	List<NotificationResponse> getAllNotifications();

	List<NotificationResponse> getByRecipientEmail(String email);

	List<NotificationResponse> getByArtistId(Long artistId);
}
