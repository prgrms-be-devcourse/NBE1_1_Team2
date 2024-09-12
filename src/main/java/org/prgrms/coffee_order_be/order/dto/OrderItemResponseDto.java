package org.prgrms.coffee_order_be.order.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.prgrms.coffee_order_be.order.entity.OrderItem;

@Getter
@Schema(description = "주문 항목 응답 DTO")
public class OrderItemResponseDto {
  @Schema(description = "상품 ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6" )
  private final UUID productId;
  @Schema(description = "상품명", example = "Ethiopia sidamo")
  private final String productName;
  @Schema(description = "카테고리", example = "커피콩")
  private final String category;
  @Schema(description = "가격", example = "5000")
  private final Long price;
  @Schema(description = "수량", example = "7")
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
