package org.prgrms.coffee_order_be.order.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Getter;
import org.prgrms.coffee_order_be.order.entity.Order;
import org.prgrms.coffee_order_be.order.entity.OrderStatus;

@Getter
public class OrderResponseDto {

  private final UUID orderId;
  private final String email;
  private final String address;
  private final String postcode;
  private final OrderStatus orderStatus;
  private final Long totalAmount;
  private final List<OrderItemResponseDto> orderItems;

  public OrderResponseDto(UUID orderId, String email, String address, String postcode,
      OrderStatus orderStatus, Long totalAmount, List<OrderItemResponseDto> orderItems) {
    this.orderId = orderId;
    this.email = email;
    this.address = address;
    this.postcode = postcode;
    this.orderStatus = orderStatus;
    this.totalAmount = totalAmount;
    this.orderItems = orderItems;
  }

  public static OrderResponseDto from(Order order) {
    return new OrderResponseDto(
        order.getOrderId(),
        order.getEmail(),
        order.getAddress(),
        order.getPostcode(),
        order.getOrderStatus(),
        order.calculateTotalAmount(),
        order.getOrderItems().stream()
            .map(OrderItemResponseDto::from)
            .collect(Collectors.toList())
    );
  }
}
