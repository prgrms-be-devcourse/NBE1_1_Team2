package org.prgrms.coffee_order_be.product.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.prgrms.coffee_order_be.common.audit.Timestamped;
import org.prgrms.coffee_order_be.product.dto.ProductUpdateDto;

@Alias("products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity extends Timestamped {

  private UUID productId;
  private String productName;
  private String category;
  private long price;
  private String description;

  @Builder
  public ProductEntity(UUID productId, String productName, String category, long price,
      String description, LocalDateTime creatdAt, LocalDateTime updatedAt) {
    super(creatdAt, updatedAt);
    this.productId = productId;
    this.productName = productName;
    this.category = category;
    this.price = price;
    this.description = description;
  }

  public void updateFromDto(ProductUpdateDto updateDto) {
    this.description = updateDto.getDescription();
    this.price = updateDto.getPrice();
    super.update();
  }
}
