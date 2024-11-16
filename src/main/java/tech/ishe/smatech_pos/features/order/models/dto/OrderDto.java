package tech.ishe.smatech_pos.features.order.models.dto;


import java.util.Date;
import java.util.List;

public record OrderDto(String orderCode, Double subTotal, Double total, Double deliverFee, Date orderedOn,
                       List<ProductOrderDto> orderedItems) {
}
