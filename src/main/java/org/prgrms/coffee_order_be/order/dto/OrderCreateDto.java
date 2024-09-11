package org.prgrms.coffee_order_be.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prgrms.coffee_order_be.order.entity.Order;
import org.prgrms.coffee_order_be.order.entity.OrderItem;
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

  public Order toEntity(List<OrderItem> orderItems) {
    return Order.builder()
        .email(this.getEmail())
        .address(this.getAddress())
        .postcode(this.getPostcode())
        .orderStatus(OrderStatus.ORDER_COMPLETED)
        .orderItems(orderItems.toArray(new OrderItem[0]))
        .build();
  }
}
