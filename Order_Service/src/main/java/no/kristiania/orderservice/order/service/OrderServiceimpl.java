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

    private final CarService carService;
    private final RestTemplate restTemplate;

    @Autowired
    public OrderServiceimpl(CarService carService, RestTemplate restTemplate) {
        this.carService = carService;
        this.restTemplate = restTemplate;
    }

    public String getCarDetails(Long id) {
        String carDetailsUrl = gatewayUrl + "/cars/" + id;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(carDetailsUrl, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return null;
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
                if (root != null && root.has("available") && !root.get("available").isNull()) {
                    return root.get("available").asBoolean();
                } else {

                    return false;
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public void createOrder(Long id) {
        boolean isProductAvailable = checkProductAvailability(id);

        if (isProductAvailable) {
            String carDetails = getCarDetails(id);
            if (carDetails != null) {
                carService.saveCarDetails(carDetails);
                notifyOrder(id);
                System.out.println("Order created successfully for product with ID " + id);
            } else {
                System.out.println("Car details not found for product with ID " + id);
            }
        } else {
            System.out.println("Product with ID " + id + " is not available.");
        }

    }

    private void notifyOrder(Long id) {
        System.out.println("Order notification sent for product with ID " + id);
    }
}


