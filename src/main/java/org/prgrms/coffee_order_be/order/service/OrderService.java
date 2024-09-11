package org.prgrms.coffee_order_be.order.service;

import static org.prgrms.coffee_order_be.common.exception.ExceptionCode.CANNOT_DELETE_ORDER;
import static org.prgrms.coffee_order_be.common.exception.ExceptionCode.CANNOT_UPDATE_ORDER;
import static org.prgrms.coffee_order_be.common.exception.ExceptionCode.NOT_FOUND_ORDER;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.common.exception.BusinessLogicException;
import org.prgrms.coffee_order_be.common.exception.ExceptionCode;
import org.prgrms.coffee_order_be.order.dto.OrderCreateDto;
import org.prgrms.coffee_order_be.order.dto.OrderItemCreateDto;
import org.prgrms.coffee_order_be.order.dto.OrderResponseDto;
import org.prgrms.coffee_order_be.order.dto.OrderUpdateDto;
import org.prgrms.coffee_order_be.order.entity.OrderEntity;
import org.prgrms.coffee_order_be.order.entity.OrderItemEntity;
import org.prgrms.coffee_order_be.order.repository.OrderItemMapper;
import org.prgrms.coffee_order_be.order.repository.OrderMapper;
import org.prgrms.coffee_order_be.product.entity.ProductEntity;
import org.prgrms.coffee_order_be.product.repository.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

  private final OrderMapper orderMapper;
  private final OrderItemMapper orderItemMapper;
  private final ProductMapper productMapper;

  @Transactional
  public OrderResponseDto createOrder(OrderCreateDto createDto) {

    List<OrderItemEntity> orderItems = convertToOrderItems(createDto.getOrderItems());

    OrderEntity order = createDto.toEntity(orderItems);

    orderMapper.save(order);
    orderItems.forEach(
        orderItemMapper::save
    );

    return OrderResponseDto.from(order);
  }

  public List<OrderResponseDto> getOrders() {
    List<OrderEntity> orders = orderMapper.findAll();

    return orders.stream().map(OrderResponseDto::from).collect(Collectors.toList());
  }

  public List<OrderResponseDto> getOrdersByEmail(String email) {
    List<OrderEntity> orders = orderMapper.findByEmail(email);
    return orders.stream().map(OrderResponseDto::from).collect(Collectors.toList());
  }

  public OrderResponseDto getOrder(UUID orderId) {
    OrderEntity findOrder = getOrderById(orderId);

    return OrderResponseDto.from(findOrder);
  }

  @Transactional
  public OrderResponseDto updateOrder(UUID orderId, OrderUpdateDto updateDto) {
    OrderEntity findOrder = getOrderById(orderId);

    if (!findOrder.isUpdatable()) {
      throw new BusinessLogicException(CANNOT_UPDATE_ORDER);
    }

    findOrder.updateFromDto(updateDto);
    orderMapper.update(findOrder);

    return OrderResponseDto.from(findOrder);
  }

  @Transactional
  public void deleteOrder(UUID orderId) {
    OrderEntity findOrder = getOrderById(orderId);

    if (!findOrder.isDeletable()) {
      throw new BusinessLogicException(CANNOT_DELETE_ORDER);
    }

    orderMapper.delete(findOrder);
  }

  private OrderEntity getOrderById(UUID orderId) {
    return orderMapper.findById(orderId).orElseThrow(
        () -> new BusinessLogicException(NOT_FOUND_ORDER)
    );
  }

  private List<OrderItemEntity> convertToOrderItems(List<OrderItemCreateDto> orderItemsDto) {
    return orderItemsDto.stream()
        .map(this::mapToOrderItem)
        .collect(Collectors.toList());
  }

  private OrderItemEntity mapToOrderItem(OrderItemCreateDto dto) {
    ProductEntity findProduct = productMapper.findById(dto.getProductId())
        .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_PRODUCT));

    return dto.toEntity(findProduct);
  }
}
