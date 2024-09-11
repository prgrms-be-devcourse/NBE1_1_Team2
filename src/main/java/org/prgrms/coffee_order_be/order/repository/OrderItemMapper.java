package org.prgrms.coffee_order_be.order.repository;

import org.apache.ibatis.annotations.Mapper;
import org.prgrms.coffee_order_be.order.entity.OrderItemEntity;

@Mapper
public interface OrderItemMapper {

  void save(OrderItemEntity orderItem);
}
