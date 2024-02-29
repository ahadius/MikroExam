package no.kristiania.orderservice.order.service;

import lombok.AllArgsConstructor;
import no.kristiania.orderservice.order.entity.Order;
import no.kristiania.orderservice.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class OrderServiceIMPL implements OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl; // Changed to private

    @Override
    public Order createOrder(Order order) {
        // Check if the car is available by making a request to the product service through the gateway
        ResponseEntity<Boolean> response = restTemplate.getForEntity(gatewayUrl + "/api/products/" + order.getCarId() + "/availability", Boolean.class);
        boolean isCarAvailable = Boolean.TRUE.equals(response.getBody());

        if (!isCarAvailable) {
            // Handle scenario where car is not available (e.g., throw an exception or return null)
            return null;
        }

        // Car is available, create the order
        return orderRepository.save(order);
    }
}





