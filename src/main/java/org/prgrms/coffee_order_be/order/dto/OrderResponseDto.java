package org.prgrms.coffee_order_be.order.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.prgrms.coffee_order_be.order.entity.Order;
import org.prgrms.coffee_order_be.order.entity.OrderStatus;

@Getter
@Schema(description = "주문 응답 DTO")
public class OrderResponseDto {

  @Schema(description = "주문 ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  private final UUID orderId;
  @Schema(description = "이메일", example = "hello123@mail.com")
  private final String email;
  @Schema(description = "주소", example = "제주 서귀포시 땡땡동 ")
  private final String address;
  @Schema(description = "우편번호", example = "12345")
  private final String postcode;
  @Schema(description = "주문 상태", example = "ORDER_COMPLETED")
  private final OrderStatus orderStatus;
  @Schema(description = "총 금액", example = "15000")
  private final Long totalAmount;
  @Schema(description = "주문 항목 목록")
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
