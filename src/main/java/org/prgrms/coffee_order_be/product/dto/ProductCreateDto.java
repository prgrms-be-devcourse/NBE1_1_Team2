package org.prgrms.coffee_order_be.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prgrms.coffee_order_be.product.entity.ProductEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCreateDto {

  @NotBlank
  private String productName;
  @NotBlank
  private String category;
  @Positive
  private Long price;
  @NotBlank
  private String description;

  public ProductCreateDto(String productName, String category, Long price, String description) {
    this.productName = productName;
    this.category = category;
    this.price = price;
    this.description = description;
  }

  public ProductEntity toEntity() {
    return ProductEntity.builder()
        .productId(UUID.randomUUID())
        .productName(this.getProductName())
        .category(this.getCategory())
        .price(this.getPrice())
        .description(this.getDescription())
        .creatdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
  }
}
