package no.kristiania.orderservice.order.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.kristiania.orderservice.order.entity.OrderRequest;
import no.kristiania.orderservice.order.service.OrderServiceimpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Order")
@AllArgsConstructor
@Slf4j
public class OrderController {

    private final OrderServiceimpl orderServiceIMPL;

    @GetMapping("/cars/{id}")
    public ResponseEntity<String> getCarById(@PathVariable Long id) {
        String carDetails = orderServiceIMPL.getCarDetails(id);
        if (carDetails != null) {
            return ResponseEntity.ok(carDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createOrders(@RequestBody OrderRequest orderRequest) {
        if (orderRequest.getId() != null) {
            // Check product availability via OrderServiceimpl
            boolean productAvailable = orderServiceIMPL.checkProductAvailability(orderRequest.getId());
            if (!productAvailable) {
                return ResponseEntity.badRequest().body("Product is not available");
            }

            // Create the order using the retrieved car details
            orderServiceIMPL.createOrder(orderRequest.getId());

            return ResponseEntity.ok("Order created successfully"); // Return a success message
        } else {
            return ResponseEntity.badRequest().body("Product ID cannot be null");
        }
    }
}


