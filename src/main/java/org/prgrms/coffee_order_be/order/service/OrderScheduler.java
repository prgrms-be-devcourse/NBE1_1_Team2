package org.prgrms.coffee_order_be.order.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.order.entity.OrderStatus;
import org.prgrms.coffee_order_be.order.repository.OrderMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderScheduler {

  private final OrderMapper orderMapper;

  @Scheduled(cron = "0 0 14 * * ?")
  @Transactional
  public void updateOrdersToShippingStarted() {
    LocalDateTime today2pm = LocalDateTime.now().withHour(14).withMinute(0).withSecond(0)
        .withNano(0);
    LocalDateTime yesterday2pm = today2pm.minusDays(1);

    orderMapper.updateOrderStatusFromDateToDate(OrderStatus.SHIPPING_STARTED, yesterday2pm,
        today2pm);

  }
}
