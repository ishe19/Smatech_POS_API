package tech.ishe.smatech_pos.features.order.services.interfaces;

import org.springframework.http.ResponseEntity;
import tech.ishe.smatech_pos.features.order.models.requests.OrderRequest;
import tech.ishe.smatech_pos.utils.PosResponse;

public interface OrdersService {
    ResponseEntity<PosResponse> getOrders();

    ResponseEntity<PosResponse> placeOrder(OrderRequest orderRequest);
}
