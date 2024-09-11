package org.prgrms.coffee_order_be.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductUpdateDto {
  @Positive
  private Long price;
  @NotBlank
  private String description;

  public ProductUpdateDto(Long price, String description) {
    this.price = price;
    this.description = description;
  }
}
