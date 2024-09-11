package org.prgrms.coffee_order_be.order.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderUpdateDto {

  @NotBlank
  private String address;
  @NotBlank
  private String postcode;

  public OrderUpdateDto(String address, String postcode) {
    this.address = address;
    this.postcode = postcode;
  }
}
