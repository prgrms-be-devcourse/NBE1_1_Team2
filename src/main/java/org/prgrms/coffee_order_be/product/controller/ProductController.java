package org.prgrms.coffee_order_be.product.controller;

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
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductCreateDto createDto) {
    ProductResponseDto responseDto = productService.create(createDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  @GetMapping
  public ResponseEntity<List<ProductResponseDto>> getProducts() {
    List<ProductResponseDto> products = productService.getProducts();
    return ResponseEntity.ok().body(products);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("productId") UUID productId) {
    ProductResponseDto product = productService.getProduct(productId);
    return ResponseEntity.ok().body(product);
  }

  @PutMapping("/{productId}")
  public ResponseEntity<ProductResponseDto> updateProduct(
      @PathVariable("productId") UUID productId,
      @Valid @RequestBody ProductUpdateDto updateDto) {
    ProductResponseDto product = productService.updateProduct(productId, updateDto);
    return ResponseEntity.ok().body(product);
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<?> deleteProduct(@PathVariable("productId") UUID productId) {
    productService.deleteProduct(productId);

    return ResponseEntity.noContent().build();
  }
}
