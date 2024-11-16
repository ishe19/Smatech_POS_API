package tech.ishe.smatech_pos.features.order.models.requests;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private Double total;
    private Double subTotal;
    private Double deliveryFee;
    private List<ProductOrderRequest> products;
}
