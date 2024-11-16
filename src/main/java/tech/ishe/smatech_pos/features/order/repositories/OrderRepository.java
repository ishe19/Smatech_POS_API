package tech.ishe.smatech_pos.features.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ishe.smatech_pos.features.order.models.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
