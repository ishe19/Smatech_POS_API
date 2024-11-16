package tech.ishe.smatech_pos.features.product.models.requests;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductRequest {
    private String name;
    private Double price;
    private String description;
}
