package org.prgrms.coffee_order_be.order.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.prgrms.coffee_order_be.order.entity.Order;
import org.prgrms.coffee_order_be.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("SELECT o FROM Order o " +
            "JOIN FETCH o.orderItems oi " +
            "JOIN FETCH oi.product " +
            "WHERE o.email = :email")
    List<Order> findByEmailWithDetails(@Param("email") String email);

    @Query("select o from Order o join fetch o.orderItems oi join fetch oi.product")
    List<Order> findAllOrdersWithProducts();


    @EntityGraph(attributePaths = {"orderItems.product"})
    Optional<Order> findById(UUID orderId);

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.orderStatus = :newStatus, o.updatedAt = :now WHERE o.orderStatus = :orderStatus AND o.createdAt >= :startDate AND o.createdAt < :endDate")
    int updateOrderStatusInDateRange(
            @Param("newStatus") OrderStatus newStatus,
            @Param("orderStatus") OrderStatus orderStatus,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("now") LocalDateTime now
    );
}
