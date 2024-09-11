package org.prgrms.coffee_order_be.order.dto;

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
public class OrderItemCreateDto {

  @NotNull
  private UUID productId;

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
