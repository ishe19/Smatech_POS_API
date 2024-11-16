package tech.ishe.smatech_pos.features.order.models.entities;

import jakarta.persistence.*;
import lombok.*;
import tech.ishe.smatech_pos.features.product.models.entities.Product;

@Entity
@Table(name= "order_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "product_id")
    private Product product;

    private int quantity;
}
