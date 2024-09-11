package org.prgrms.coffee_order_be.order.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.ibatis.annotations.Mapper;
import org.prgrms.coffee_order_be.order.entity.OrderEntity;
import org.prgrms.coffee_order_be.order.entity.OrderStatus;


@Mapper
public interface OrderMapper {

  void save(OrderEntity order);

  Optional<OrderEntity> findById(UUID orderId);

  void delete(OrderEntity order);

  List<OrderEntity> findAll();

  List<OrderEntity> findByEmail(String email);

  void update(OrderEntity findOrder);

  void updateOrderStatusFromDateToDate(OrderStatus orderStatus, LocalDateTime yesterday2pm,
      LocalDateTime today2pm);
}
