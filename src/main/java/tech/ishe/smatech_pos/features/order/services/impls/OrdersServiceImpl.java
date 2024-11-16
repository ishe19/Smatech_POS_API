package tech.ishe.smatech_pos.features.order.services.impls;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.ishe.smatech_pos.features.order.models.dto.OrderDto;
import tech.ishe.smatech_pos.features.order.models.entities.Order;
import tech.ishe.smatech_pos.features.order.models.entities.OrderItem;
import tech.ishe.smatech_pos.features.order.models.requests.OrderRequest;
import tech.ishe.smatech_pos.features.order.models.requests.ProductOrderRequest;
import tech.ishe.smatech_pos.features.order.repositories.OrderItemRepository;
import tech.ishe.smatech_pos.features.order.repositories.OrderRepository;
import tech.ishe.smatech_pos.features.order.services.interfaces.OrdersService;
import tech.ishe.smatech_pos.features.product.models.entities.Product;
import tech.ishe.smatech_pos.features.product.repositories.ProductRepository;
import tech.ishe.smatech_pos.utils.DtoMapper;
import tech.ishe.smatech_pos.utils.PosResponse;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrderRepository orderRepository;
    private final DtoMapper dtoMapper;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersServiceImpl.class);

    @Override
    public ResponseEntity<PosResponse> getOrders() {
        try {

            List<OrderDto> orderDtos = orderRepository.findAll().stream().map(dtoMapper::mapToOrderDto).toList();

            return new ResponseEntity<>(new PosResponse(orderDtos), HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error("Get orders error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<PosResponse> placeOrder(OrderRequest orderRequest) {
        try {
            Order order = Order
                    .builder()
                    .orderedOn(new Date())
                    .orderCode(UUID.randomUUID().toString())
                    .total(orderRequest.getTotal())
                    .deliveryFee(orderRequest.getDeliveryFee())
                    .subTotal(orderRequest.getSubTotal())
                    .build();

            Order savedOrder = orderRepository.save(order);

            for (ProductOrderRequest productOrderRequest : orderRequest.getProducts()) {
                Product product = productRepository.findByProductSku(productOrderRequest.getProductSku());
                OrderItem item = OrderItem
                        .builder()
                        .order(savedOrder)
                        .product(product)
                        .quantity(productOrderRequest.getQuantity())
                        .build();
                orderItemRepository.save(item);
            }

            return new ResponseEntity<>(new PosResponse("Order placed successfully"), HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Post order error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
