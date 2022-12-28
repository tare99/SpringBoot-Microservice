package com.amigoscode.notifications;

import com.amigoscode.clients.notifications.NotificationRequest;
import com.amigoscode.clients.notifications.NotificationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class NotificationService {
  private final NotificationRepository notificationRepository;

  public NotificationResponse sendNotification(NotificationRequest request) {
    Notification notification =
        Notification.builder()
            .message(request.message())
            .sender("Tarik")
            .toCustomerId(request.toCustomerId())
            .toCustomerEmail(request.toCustomerEmail())
            .sentAt(LocalDateTime.now())
            .build();
    notificationRepository.save(notification);
    log.info("Notification sent to {}", request.toCustomerEmail());
    return new NotificationResponse("Message sent!");
  }
}
