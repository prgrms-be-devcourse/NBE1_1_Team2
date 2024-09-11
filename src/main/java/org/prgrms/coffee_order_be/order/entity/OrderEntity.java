package org.prgrms.coffee_order_be.order.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.prgrms.coffee_order_be.common.audit.Timestamped;
import org.prgrms.coffee_order_be.order.dto.OrderUpdateDto;

@Alias("orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity extends Timestamped {

  private UUID orderId;
  private String email;
  private String address;
  private String postcode;
  private OrderStatus orderStatus;
  private List<OrderItemEntity> orderItems = new ArrayList<>();

  @Builder
  public OrderEntity(LocalDateTime createdAt, LocalDateTime updatedAt, UUID orderId, String email,
      String address, String postcode, OrderStatus orderStatus, OrderItemEntity... orderItems) {
    super(createdAt, updatedAt);
    this.orderId = orderId;
    this.email = email;
    this.address = address;
    this.postcode = postcode;
    this.orderStatus = orderStatus;

    for (OrderItemEntity orderItem : orderItems) {
      this.addOrderItem(orderItem);
    }
  }

  public void addOrderItem(OrderItemEntity orderItem) {
    orderItems.add(orderItem);
    orderItem.assignOrder(this);
  }

  public Long calculateTotalAmount() {
    return orderItems.stream()
        .mapToLong(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
        .sum();
  }

  public boolean isUpdatable() {
    return OrderStatus.ORDER_COMPLETED.equals(this.orderStatus);
  }

  public boolean isDeletable() {
    return OrderStatus.ORDER_COMPLETED.equals(this.orderStatus);
  }

  public void updateFromDto(OrderUpdateDto updateDto) {
    this.address = updateDto.getAddress();
    this.postcode = updateDto.getPostcode();
    super.update();
  }

  public void startShipping() {
    this.orderStatus = OrderStatus.SHIPPING_STARTED;
    super.update();
  }
}
