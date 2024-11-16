package tech.ishe.smatech_pos.features.order.models.requests;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductOrderRequest {
    private String productSku;
    private int quantity;
}
