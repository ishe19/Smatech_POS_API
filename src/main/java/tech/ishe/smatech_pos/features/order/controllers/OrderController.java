package tech.ishe.smatech_pos.features.order.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ishe.smatech_pos.features.order.models.requests.OrderRequest;
import tech.ishe.smatech_pos.features.order.services.interfaces.OrdersService;
import tech.ishe.smatech_pos.utils.PosResponse;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrdersService ordersService;

    @GetMapping("/")
    public ResponseEntity<PosResponse> getAllOrders() {
        return ordersService.getOrders();
    }

    @PostMapping("/")
    public ResponseEntity<PosResponse> placeOrder(@RequestBody OrderRequest orderRequest){
        return ordersService.placeOrder(orderRequest);
    }

}
