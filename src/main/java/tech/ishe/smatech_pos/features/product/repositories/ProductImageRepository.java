package tech.ishe.smatech_pos.features.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ishe.smatech_pos.features.product.models.entities.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    ProductImage findByProductId(Long id);
}
