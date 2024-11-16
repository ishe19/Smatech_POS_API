package tech.ishe.smatech_pos.features.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ishe.smatech_pos.features.product.models.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductSku(String productSku);
}
