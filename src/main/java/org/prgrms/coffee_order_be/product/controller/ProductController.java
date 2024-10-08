package org.prgrms.coffee_order_be.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.product.dto.ProductUpdateDto;
import org.prgrms.coffee_order_be.product.service.ProductService;
import org.prgrms.coffee_order_be.product.dto.ProductCreateDto;
import org.prgrms.coffee_order_be.product.dto.ProductResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product API", description = "상품 CRUD 관련 API")
public class ProductController {

  private final ProductService productService;

  @Operation(summary = "상품 생성", description = "새 상품을 생성함")
  @PostMapping
  public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductCreateDto createDto) {
    ProductResponseDto responseDto = productService.create(createDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }
  @Operation(summary = "상품 조회 ", description = "등록된 모든 상품을 리스트로 조회함.")
  @GetMapping
  public ResponseEntity<List<ProductResponseDto>> getProducts() {
    List<ProductResponseDto> products = productService.getProducts();
    return ResponseEntity.ok().body(products);
  }

  @Operation(summary = "특정 상품 조회", description = "상품 ID로 특정 상품을 조회함.")
  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("productId") UUID productId) {
    ProductResponseDto product = productService.getProduct(productId);
    return ResponseEntity.ok().body(product);
  }

  @Operation(summary = "상품 정보 수정", description = "상품 ID로 특정 상품의 정보를 수정함.")
  @PutMapping("/{productId}")
  public ResponseEntity<ProductResponseDto> updateProduct(
      @PathVariable("productId") UUID productId,
      @Valid @RequestBody ProductUpdateDto updateDto) {
    ProductResponseDto product = productService.updateProduct(productId, updateDto);
    return ResponseEntity.ok().body(product);
  }

  @Operation(summary = "상품 삭제", description = "상품 ID로 특정 상품을 삭제함.")
  @DeleteMapping("/{productId}")
  public ResponseEntity<?> deleteProduct(@PathVariable("productId") UUID productId) {
    productService.deleteProduct(productId);

    return ResponseEntity.noContent().build();
  }
}
