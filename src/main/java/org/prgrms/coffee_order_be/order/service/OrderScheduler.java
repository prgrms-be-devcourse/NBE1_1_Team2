package org.prgrms.coffee_order_be.order.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.order.entity.Order;
import org.prgrms.coffee_order_be.order.entity.OrderStatus;
import org.prgrms.coffee_order_be.order.repository.OrderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderScheduler {

  private final OrderRepository orderRepository;

  @Scheduled(cron = "0 0 14 * * ?")
  @Transactional
  public void updateOrdersToShippingStarted() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime today2pm = now.withHour(14).withMinute(0).withSecond(0).withNano(0);
    LocalDateTime yesterday2pm = today2pm.minusDays(1);

    List<Order> orders = orderRepository.findAllByStatusAndDateRange(
        OrderStatus.ORDER_COMPLETED, yesterday2pm, today2pm
    );

    orders.forEach(Order::startShipping);
  }
}
