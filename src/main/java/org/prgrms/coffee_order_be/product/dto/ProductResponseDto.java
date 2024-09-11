package org.prgrms.coffee_order_be.product.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prgrms.coffee_order_be.product.entity.Product;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductResponseDto {

  private UUID productId;
  private String productName;
  private String category;
  private Long price;
  private String description;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public ProductResponseDto(UUID productId, String productName, String category, Long price,
      String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.productId = productId;
    this.productName = productName;
    this.category = category;
    this.price = price;
    this.description = description;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public static ProductResponseDto from(Product product) {
    return new ProductResponseDto(
        product.getProductId(),
        product.getProductName(),
        product.getCategory(),
        product.getPrice(),
        product.getDescription(),
        product.getCreatedAt(),
        product.getUpdatedAt()
    );
  }

}
