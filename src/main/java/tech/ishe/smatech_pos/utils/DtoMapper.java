package tech.ishe.smatech_pos.utils;

import tech.ishe.smatech_pos.features.order.models.dto.OrderDto;
import tech.ishe.smatech_pos.features.order.models.entities.Order;
import tech.ishe.smatech_pos.features.product.models.ProductDto;
import tech.ishe.smatech_pos.features.product.models.entities.Product;

public interface DtoMapper {
    OrderDto mapToOrderDto(Order order);

    ProductDto mapToProductDto(Product product);
}
