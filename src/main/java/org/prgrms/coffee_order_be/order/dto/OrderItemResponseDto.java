package org.prgrms.coffee_order_be.order.dto;

import java.util.UUID;
import lombok.Getter;
import org.prgrms.coffee_order_be.order.entity.OrderItem;

@Getter
public class OrderItemResponseDto {
  private final UUID productId;
  private final String productName;
  private final String category;
  private final Long price;
  private final Integer quantity;

  public OrderItemResponseDto(UUID productId, String productName, String category, Long price,
      Integer quantity) {
    this.productId = productId;
    this.productName = productName;
    this.category = category;
    this.price = price;
    this.quantity = quantity;
  }

  public static OrderItemResponseDto from(OrderItem orderItem) {
    return new OrderItemResponseDto(
        orderItem.getProduct().getProductId(),
        orderItem.getProduct().getProductName(),
        orderItem.getCategory(),
        orderItem.getPrice(),
        orderItem.getQuantity()
    );
  }
}
