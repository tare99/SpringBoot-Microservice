package com.amigoscode.customer;

import com.amigoscode.clients.fraud.FraudCheckResponse;
import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.notifications.NotificationRequest;
import com.amigoscode.clients.notifications.NotificationsClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;
  private final NotificationsClient notificationsClient;

  public void registerCustomer(CustomerRegistrationRequest request) {
    Customer customer =
        Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();
    customerRepository.save(customer);
    FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
    if (fraudCheckResponse != null && fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("fraudster");
    }
    notificationsClient.sendNotification(notificationRequest(customer));
  }

  private NotificationRequest notificationRequest(Customer customer) {
    return NotificationRequest.builder()
        .sender(this.getClass().getSimpleName())
        .toCustomerId(customer.getId())
        .toCustomerEmail(customer.getEmail())
        .message(String.format("A new customer with id %s has been added!", customer.getId()))
        .build();
  }
}
