package org.prgrms.coffee_order_be.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prgrms.coffee_order_be.order.entity.OrderEntity;
import org.prgrms.coffee_order_be.order.entity.OrderItemEntity;
import org.prgrms.coffee_order_be.order.entity.OrderStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderCreateDto {

  @NotBlank
  private String email;

  @NotBlank
  private String address;

  @NotBlank
  private String postcode;

  @NotEmpty
  private List<OrderItemCreateDto> orderItems;

  public OrderEntity toEntity(List<OrderItemEntity> orderItems) {
    return OrderEntity.builder()
        .orderId(UUID.randomUUID())
        .email(this.getEmail())
        .address(this.getAddress())
        .postcode(this.getPostcode())
        .orderStatus(OrderStatus.ORDER_COMPLETED)
        .orderItems(orderItems.toArray(new OrderItemEntity[0]))
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
  }
}
