package org.prgrms.coffee_order_be.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "주문 수정 DTO")
public class OrderUpdateDto {

  @Schema(description = "수정할 주소", example = "제주 서귀포시 땡땡동")
  @NotBlank
  private String address;

  @Schema(description = "수정할 우편번호", example = "12345")
  @NotBlank
  private String postcode;

  public OrderUpdateDto(String address, String postcode) {
    this.address = address;
    this.postcode = postcode;
  }
}
