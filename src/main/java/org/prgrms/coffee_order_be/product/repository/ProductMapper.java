package org.prgrms.coffee_order_be.product.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.ibatis.annotations.Mapper;
import org.prgrms.coffee_order_be.product.entity.ProductEntity;

@Mapper
public interface ProductMapper {

  void save(ProductEntity customer);
  void update(ProductEntity customer);
  Optional<ProductEntity> findById(UUID productId);

  Optional<ProductEntity> findByProductName(String productName);

  List<ProductEntity> findAll();

  void delete(ProductEntity product);
}
