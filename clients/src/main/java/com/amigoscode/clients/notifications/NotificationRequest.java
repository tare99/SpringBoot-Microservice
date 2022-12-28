package com.amigoscode.clients.notifications;

import lombok.Builder;

@Builder
public record NotificationRequest(
    String sender, Integer toCustomerId, String toCustomerEmail, String message) {}
