package no.kristiania.orderservice.order.Repository;

import no.kristiania.orderservice.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Define custom query methods if needed
}

