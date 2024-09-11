package org.prgrms.coffee_order_be.product.service;

import static org.prgrms.coffee_order_be.common.exception.ExceptionCode.DUPLICATED_PRODUCT;
import static org.prgrms.coffee_order_be.common.exception.ExceptionCode.NOT_FOUND_PRODUCT;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.common.exception.BusinessLogicException;
import org.prgrms.coffee_order_be.product.dto.ProductCreateDto;
import org.prgrms.coffee_order_be.product.dto.ProductResponseDto;
import org.prgrms.coffee_order_be.product.dto.ProductUpdateDto;
import org.prgrms.coffee_order_be.product.entity.ProductEntity;
import org.prgrms.coffee_order_be.product.repository.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

  private final ProductMapper productMapper;

  @Transactional
  public ProductResponseDto create(ProductCreateDto createDto) throws BusinessLogicException {
    Optional<ProductEntity> existProduct = productMapper.findByProductName(
        createDto.getProductName());

    if (existProduct.isPresent()) {
      throw new BusinessLogicException(DUPLICATED_PRODUCT);
    }

    ProductEntity product = createDto.toEntity();
    productMapper.save(product);
    return ProductResponseDto.from(product);
  }

  public List<ProductResponseDto> getProducts() {
    List<ProductEntity> products = productMapper.findAll();

    return products.stream().map(ProductResponseDto::from).toList();
  }

  public ProductResponseDto getProduct(UUID productId) {
    ProductEntity findProduct = findProductOrThrow(productId);
    return ProductResponseDto.from(findProduct);
  }

  @Transactional
  public ProductResponseDto updateProduct(UUID productId, ProductUpdateDto updateDto) {
    ProductEntity findProduct = findProductOrThrow(productId);

    findProduct.updateFromDto(updateDto);
    productMapper.update(findProduct);

    return ProductResponseDto.from(findProduct);
  }

  private ProductEntity findProductOrThrow(UUID productId) {
    return productMapper.findById(productId).orElseThrow(
        () -> new BusinessLogicException(NOT_FOUND_PRODUCT)
    );
  }


  @Transactional
  public void deleteProduct(UUID productId) {
    ProductEntity findProduct = findProductOrThrow(productId);
    productMapper.delete(findProduct);
  }
}
