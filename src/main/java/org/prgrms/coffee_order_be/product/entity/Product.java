package org.prgrms.coffee_order_be.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.prgrms.coffee_order_be.common.audit.Timestamped;
import org.prgrms.coffee_order_be.product.dto.ProductUpdateDto;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends Timestamped {

  @Id
  @UuidGenerator
  @Column(columnDefinition = "BINARY(16)", nullable = false)
  private UUID productId;

  @Column(name = "product_name", nullable = false, length = 20)
  private String productName;

  @Column(name = "category", nullable = false, length = 50)
  private String category;

  @Column(name = "price", nullable = false)
  private Long price;

  @Column(name = "description", length = 500)
  private String description;

  @Builder
  public Product(String productName, String category, Long price, String description) {
    this.productName = productName;
    this.category = category;
    this.price = price;
    this.description = description;
  }

  public Product(UUID productId, String productName, String category, Long price,
      String description) {
    this.productId = productId;
    this.productName = productName;
    this.category = category;
    this.price = price;
    this.description = description;
  }

  public void updateFromDto(ProductUpdateDto updateDto) {
    this.price = updateDto.getPrice();
    this.description = updateDto.getDescription();
  }
}
