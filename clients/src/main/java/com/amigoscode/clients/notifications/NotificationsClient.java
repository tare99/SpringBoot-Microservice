package com.amigoscode.clients.notifications;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notifications")
public interface NotificationsClient {
  @PostMapping(path = "api/v1/send-notification")
  NotificationResponse sendNotification(@RequestBody NotificationRequest request);
}
