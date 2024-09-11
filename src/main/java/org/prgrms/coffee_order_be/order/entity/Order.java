package org.prgrms.coffee_order_be.order.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.prgrms.coffee_order_be.common.audit.Timestamped;
import org.prgrms.coffee_order_be.order.dto.OrderUpdateDto;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends Timestamped {

  @Id
  @UuidGenerator
  @Column(name = "order_id", columnDefinition = "BINARY(16)", nullable = false)
  private UUID orderId;

  @Column(name = "email", nullable = false, length = 50)
  private String email;

  @Column(name = "address", nullable = false, length = 200)
  private String address;

  @Column(name = "postcode", nullable = false, length = 200)
  private String postcode;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "order_status", nullable = false, length = 50)
  private OrderStatus orderStatus;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<OrderItem> orderItems = new ArrayList<>();

  @Builder
  public Order(String email, String address, String postcode, OrderStatus orderStatus,
      OrderItem... orderItems) {
    this.email = email;
    this.address = address;
    this.postcode = postcode;
    this.orderStatus = orderStatus;

    for (OrderItem orderItem : orderItems)
      this.addOrderItem(orderItem);
  }

  public Order(UUID orderId, String email, String address, String postcode,
      OrderStatus orderStatus, OrderItem... orderItems) {
    this.orderId = orderId;
    this.email = email;
    this.address = address;
    this.postcode = postcode;
    this.orderStatus = orderStatus;

    for (OrderItem orderItem : orderItems)
      this.addOrderItem(orderItem);
  }

  public void addOrderItem(OrderItem orderItem) {
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
  }

  public void startShipping() {
    this.orderStatus = OrderStatus.SHIPPING_STARTED;
  }
}
