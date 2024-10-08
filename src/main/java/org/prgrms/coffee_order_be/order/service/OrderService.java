package org.prgrms.coffee_order_be.order.service;

import static org.prgrms.coffee_order_be.common.exception.ExceptionCode.CANNOT_DELETE_ORDER;
import static org.prgrms.coffee_order_be.common.exception.ExceptionCode.CANNOT_UPDATE_ORDER;
import static org.prgrms.coffee_order_be.common.exception.ExceptionCode.NOT_FOUND_ORDER;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.common.exception.BusinessLogicException;
import org.prgrms.coffee_order_be.common.exception.ExceptionCode;
import org.prgrms.coffee_order_be.order.dto.OrderCreateDto;
import org.prgrms.coffee_order_be.order.dto.OrderItemCreateDto;
import org.prgrms.coffee_order_be.order.dto.OrderResponseDto;
import org.prgrms.coffee_order_be.order.dto.OrderUpdateDto;
import org.prgrms.coffee_order_be.order.entity.Order;
import org.prgrms.coffee_order_be.order.entity.OrderItem;
import org.prgrms.coffee_order_be.order.repository.OrderRepository;
import org.prgrms.coffee_order_be.product.entity.Product;
import org.prgrms.coffee_order_be.product.repository.ProductRepository;
import org.prgrms.coffee_order_be.user.JwtProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final HttpServletRequest request;
  private final JwtProvider jwtProvider;

  @Transactional
  public OrderResponseDto createOrder(OrderCreateDto createDto) {
    List<UUID> productIds = createDto.getOrderItems().stream()
            .map(OrderItemCreateDto::getProductId)
            .collect(Collectors.toList());

    Map<UUID, Product> productMap = productRepository.findAllById(productIds).stream()
            .collect(Collectors.toMap(Product::getProductId, Function.identity()));

    List<OrderItem> orderItems = convertToOrderItems(createDto.getOrderItems(), productMap);

    Order order = createDto.toEntity(orderItems);

    orderRepository.save(order);

    return OrderResponseDto.from(order);
  }

  public List<OrderResponseDto> getOrders() {
    List<Order> orders = orderRepository.findAllOrdersWithProducts();

    return orders.stream().map(OrderResponseDto::from).collect(Collectors.toList());
  }

  public List<OrderResponseDto> getOrdersByEmail(String email) {
    List<Order> orders = orderRepository.findByEmailWithDetails(email);
    return orders.stream().map(OrderResponseDto::from).collect(Collectors.toList());
  }

  public OrderResponseDto getOrder(UUID orderId) {
    Order findOrder = getOrderById(orderId);

    return OrderResponseDto.from(findOrder);
  }

  @Transactional
  public OrderResponseDto updateOrder(UUID orderId, OrderUpdateDto updateDto) {
    Order findOrder = getOrderById(orderId);

    String token = request.getHeader("Authorization");
    String loginEmail = (String) jwtProvider.parseClaims(token).get("email");

    if (!loginEmail.equals(findOrder.getEmail())) {
      throw new RuntimeException("본인의 주문만 수정할 수 있습니다.");
    }

    if (!findOrder.isUpdatable())
      throw new BusinessLogicException(CANNOT_UPDATE_ORDER);

    findOrder.updateFromDto(updateDto);

    return OrderResponseDto.from(findOrder);
  }

  public void deleteOrder(UUID orderId) {
    Order findOrder = getOrderById(orderId);

    String token = request.getHeader("Authorization");
    String loginEmail = (String) jwtProvider.parseClaims(token).get("email");

    if (!loginEmail.equals(findOrder.getEmail())) {
      throw new RuntimeException("본인의 주문만 삭제할 수 있습니다.");
    }

    if (!findOrder.isDeletable())
      throw new BusinessLogicException(CANNOT_DELETE_ORDER);

    orderRepository.delete(findOrder);
  }

  private Order getOrderById(UUID orderId) {
    return orderRepository.findById(orderId).orElseThrow(
        () -> new BusinessLogicException(NOT_FOUND_ORDER)
    );
  }

  private List<OrderItem> convertToOrderItems(List<OrderItemCreateDto> orderItemsDto, Map<UUID, Product> productMap) {
    return orderItemsDto.stream()
            .map(dto -> mapToOrderItem(dto, productMap))
            .collect(Collectors.toList());
  }

  private OrderItem mapToOrderItem(OrderItemCreateDto dto, Map<UUID, Product> productMap) {
    Product product = productMap.get(dto.getProductId());
    if (product == null) {
      throw new BusinessLogicException(ExceptionCode.NOT_FOUND_PRODUCT);
    }

    return dto.toEntity(product);
  }
}
