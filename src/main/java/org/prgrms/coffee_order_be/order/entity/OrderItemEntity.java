package org.prgrms.coffee_order_be.order.entity;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.prgrms.coffee_order_be.common.audit.Timestamped;
import org.prgrms.coffee_order_be.product.entity.ProductEntity;

@Alias("order_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemEntity extends Timestamped {

  private Long seq;
  private OrderEntity order;
  private ProductEntity product;
  private String category;
  private long price;
  private int quantity;

  @Builder
  public OrderItemEntity(LocalDateTime createdAt, LocalDateTime updatedAt,
      ProductEntity product, String category, long price, int quantity) {
    super(createdAt, updatedAt);
    this.product = product;
    this.category = category;
    this.price = price;
    this.quantity = quantity;
  }

  public void assignOrder(OrderEntity order) {
    this.order = order;
  }
}
