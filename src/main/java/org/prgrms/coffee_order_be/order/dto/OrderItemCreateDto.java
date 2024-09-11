package org.prgrms.coffee_order_be.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prgrms.coffee_order_be.order.entity.OrderEntity;
import org.prgrms.coffee_order_be.order.entity.OrderItemEntity;
import org.prgrms.coffee_order_be.product.entity.ProductEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemCreateDto {

  @NotNull
  private UUID productId;

  @Positive
  private Integer quantity;


  public OrderItemEntity toEntity(ProductEntity product) {
    return OrderItemEntity.builder()
        .product(product)
        .category(product.getCategory())
        .price(product.getPrice())
        .quantity(this.getQuantity())
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
  }
}
