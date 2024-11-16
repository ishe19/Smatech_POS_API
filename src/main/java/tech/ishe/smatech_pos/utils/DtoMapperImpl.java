package tech.ishe.smatech_pos.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.ishe.smatech_pos.features.order.models.dto.OrderDto;
import tech.ishe.smatech_pos.features.order.models.dto.ProductOrderDto;
import tech.ishe.smatech_pos.features.order.models.entities.Order;
import tech.ishe.smatech_pos.features.order.models.entities.OrderItem;
import tech.ishe.smatech_pos.features.order.repositories.OrderItemRepository;
import tech.ishe.smatech_pos.features.product.models.ProductDto;
import tech.ishe.smatech_pos.features.product.models.entities.Product;

import java.util.List;


@Component
@RequiredArgsConstructor
public class DtoMapperImpl implements DtoMapper {

    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getOrderCode(),
                order.getSubTotal(),
                order.getTotal(),
                order.getDeliveryFee(),
                order.getOrderedOn(),
                getOrderItems(order)
        );
    }

    private List<ProductOrderDto> getOrderItems(Order order) {
        return orderItemRepository.findByOrder(order).stream().map(this::mapItemToProductDto).toList();
    }

    private ProductOrderDto mapItemToProductDto(OrderItem orderItem) {
        return mapToProductOrderDto(orderItem.getProduct(), orderItem.getQuantity());
    }

    private ProductOrderDto mapToProductOrderDto(Product product, int quantity) {
        return new ProductOrderDto(
                product.getProductSku(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                quantity
        );
    }


    @Override
    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getProductSku(),
                product.getName(),
                product.getPrice(),
                product.getDescription()
        );
    }
}
