package org.prgrms.coffee_order_be.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prgrms.coffee_order_be.order.entity.OrderItem;
import org.prgrms.coffee_order_be.product.entity.Product;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "주문 항목 생성 DTO")
public class OrderItemCreateDto {

  @Schema(description = "상품 ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  @NotNull
  private UUID productId;

  @Schema(description = "상품 수량", example = "7")
  @Positive
  private Integer quantity;


  public OrderItem toEntity(Product product) {
    return OrderItem.builder()
        .product(product)
        .category(product.getCategory())
        .price(product.getPrice())
        .quantity(this.getQuantity())
        .build();
  }
}
