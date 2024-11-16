package tech.ishe.smatech_pos.features.order.models.dto;

public record ProductOrderDto(String productSku, String productName, double price, String description, int quantity) {
}
