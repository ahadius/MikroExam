package no.kristiania.orderservice.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceimpl {

    @Value("${gateway.url}")
    private String gatewayUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public OrderServiceimpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCarDetails(Long id) {
        String carDetailsUrl = gatewayUrl + "/cars/" + id;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(carDetailsUrl, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return null; // Car details not found
        }
    }

    public boolean checkProductAvailability(Long id) {
        String availabilityUrl = gatewayUrl + "/cars/" + id;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(availabilityUrl, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode root = objectMapper.readTree(responseBody);
                // Check if the root node and the 'available' field are not null
                if (root != null && root.has("available") && !root.get("available").isNull()) {
                    return root.get("available").asBoolean();
                } else {
                    // Handle missing or null 'available' field
                    return false;
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return false; // Handle parsing error
            }
        } else {
            return false; // Handle non-200 status code
        }
    }

    public void createOrder(Long id) {
        boolean isProductAvailable = checkProductAvailability(id);

        if (isProductAvailable) {
            notifyOrder(id);
            System.out.println("Order created successfully for product with ID " + id);
        } else {
            System.out.println("Product with ID " + id + " is not available.");
        }
    }

    private void notifyOrder(Long id) {
        // Simulate notifying an external system about the new order
        // In a real-world scenario, you would call an endpoint to create a notification
        System.out.println("Order notification sent for product with ID " + id);
    }
}


