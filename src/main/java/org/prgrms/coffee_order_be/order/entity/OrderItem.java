package org.prgrms.coffee_order_be.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prgrms.coffee_order_be.common.audit.Timestamped;
import org.prgrms.coffee_order_be.product.entity.Product;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends Timestamped {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "seq")
  private Long seq;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(name = "category", nullable = false, length = 50)
  private String category;

  @Column(name = "price", nullable = false)
  private Long price;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @Builder
  public OrderItem(Product product, String category, Long price, Integer quantity) {
    this.product = product;
    this.category = category;
    this.price = price;
    this.quantity = quantity;
  }

  public OrderItem(Long seq, Product product, String category, Long price,
      Integer quantity) {
    this.seq = seq;
    this.product = product;
    this.category = category;
    this.price = price;
    this.quantity = quantity;
  }

  public void assignOrder(Order order) {
    this.order = order;
  }
}
