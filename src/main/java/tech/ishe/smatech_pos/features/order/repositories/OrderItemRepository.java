package tech.ishe.smatech_pos.features.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ishe.smatech_pos.features.order.models.entities.Order;
import tech.ishe.smatech_pos.features.order.models.entities.OrderItem;

import java.util.Collection;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
}
