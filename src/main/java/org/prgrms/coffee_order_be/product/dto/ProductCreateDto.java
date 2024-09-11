package org.prgrms.coffee_order_be.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prgrms.coffee_order_be.product.entity.Product;

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

  public Product toEntity() {
    return Product.builder()
        .productName(this.getProductName())
        .category(this.getCategory())
        .price(this.getPrice())
        .description(this.getDescription())
        .build();
  }
}
