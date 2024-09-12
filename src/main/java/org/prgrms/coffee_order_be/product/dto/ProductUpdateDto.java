package org.prgrms.coffee_order_be.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "상품 수정 DTO")
public class ProductUpdateDto {
  @Schema(description = "상품 가격", example = "5000")
  @Positive
  private Long price;

  @Schema(description = "상품 설명", example = "산미가 더해진 커피원두..어쩌고")
  @NotBlank
  private String description;

  public ProductUpdateDto(Long price, String description) {
    this.price = price;
    this.description = description;
  }
}
