package org.prgrms.coffee_order_be.product.repository;

import java.util.Optional;
import java.util.UUID;
import org.prgrms.coffee_order_be.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID> {

  Optional<Product> findByProductName(String productName);

}
