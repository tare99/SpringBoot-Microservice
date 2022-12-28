package com.amigoscode.notifications;

import com.amigoscode.clients.notifications.NotificationRequest;
import com.amigoscode.clients.notifications.NotificationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/send-notification")
@RestController
@AllArgsConstructor
public class NotificationController {

  private final NotificationService notificationService;

  @PostMapping
  public NotificationResponse sendNotification(@RequestBody NotificationRequest request) {
    return notificationService.sendNotification(request);
  }
}
