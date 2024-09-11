package org.prgrms.coffee_order_be.order.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.prgrms.coffee_order_be.order.entity.Order;
import org.prgrms.coffee_order_be.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, UUID> {

  List<Order> findByEmail(String email);

  @Query("SELECT o FROM Order o WHERE o.orderStatus = :orderStatus AND o.createdAt >= :startDate AND o.createdAt < :endDate")
  List<Order> findAllByStatusAndDateRange(
      @Param("orderStatus") OrderStatus orderStatus,
      @Param("startDate") LocalDateTime startDate,
      @Param("endDate") LocalDateTime endDate
  );
}
