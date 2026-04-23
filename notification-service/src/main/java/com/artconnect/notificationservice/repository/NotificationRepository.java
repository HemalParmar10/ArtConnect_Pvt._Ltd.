package com.artconnect.notificationservice.repository;

import com.artconnect.notificationservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByRecipientEmail(String recipientEmail);

    List<Notification> findByArtistId(Long artistId);

    List<Notification> findByStatus(String status);
}
