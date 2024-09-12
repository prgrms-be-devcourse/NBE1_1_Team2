package org.prgrms.coffee_order_be.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prgrms.coffee_order_be.order.entity.Order;
import org.prgrms.coffee_order_be.order.entity.OrderItem;
import org.prgrms.coffee_order_be.order.entity.OrderStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "주문 생성 DTO")
public class OrderCreateDto {

  @Schema(description = "고객 이메일", example = "hello123@mail.com")
  @NotBlank
  private String email;

  @Schema(description = "배송 주소", example = "제주 서귀포시 땡땡동")
  @NotBlank
  private String address;

  @Schema(description = "우편번호", example = "12345")
  @NotBlank
  private String postcode;

  @Schema(description = "주문 항목 리스트")
  @NotEmpty
  private List<OrderItemCreateDto> orderItems;

  public Order toEntity(List<OrderItem> orderItems) {
    return Order.builder()
        .email(this.getEmail())
        .address(this.getAddress())
        .postcode(this.getPostcode())
        .orderStatus(OrderStatus.ORDER_COMPLETED)
        .orderItems(orderItems.toArray(new OrderItem[0]))
        .build();
  }
}
