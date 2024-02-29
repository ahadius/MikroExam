package no.kristiania.orderservice.order.service;
import no.kristiania.orderservice.order.entity.Order;

public interface OrderService {
    Order createOrder(Order order);
}
