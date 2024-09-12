package org.prgrms.coffee_order_be.product.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.prgrms.coffee_order_be.product.entity.Product;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "상품 응답 DTO")
public class ProductResponseDto {

  @Schema(description = "상품 ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
  private UUID productId;
  @Schema(description = "상품 이름", example = "Ethiopia sidamo")
  private String productName;
  @Schema(description = "상품 카테고리", example = "커피콩")
  private String category;
  @Schema(description = "상품 가격", example = "5000")
  private Long price;
  @Schema(description = "상품 설명", example = "산미가 더해진 커피원두..어쩌고")
  private String description;
  @Schema(description = "상품 생성 시간", example = "2024-09-12T00:05:23.818Z" )
  private LocalDateTime createdAt;
  @Schema(description = "상품 수정 시간", example = "2024-09-12T00:05:23.818Z")
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
