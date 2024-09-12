package org.prgrms.coffee_order_be.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prgrms.coffee_order_be.product.entity.Product;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "상품 생성 요청 DTO")
public class ProductCreateDto {

  @Schema(description = "상품 이름", example = "Ethiopia sidamo")
  @NotBlank
  private String productName;
  @Schema(description = "상품 카테고리", example = "커피콩")
  @NotBlank
  private String category;
  @Schema(description = "상품 가격", example = "5000")
  @Positive
  private Long price;
  @Schema(description = "상품 설명", example = "산미가 더해진 커피원두..어쩌고")
  @NotBlank
  private String description;
  @NotBlank
  private int quantity;

  public ProductCreateDto(String productName, String category, Long price, String description, int quantity) {
    this.productName = productName;
    this.category = category;
    this.price = price;
    this.description = description;
    this.quantity = quantity;
  }

  public Product toEntity() {
    return Product.builder()
        .productName(this.getProductName())
        .category(this.getCategory())
        .price(this.getPrice())
        .description(this.getDescription())
            .quantity(this.getQuantity())
        .build();
  }
}
