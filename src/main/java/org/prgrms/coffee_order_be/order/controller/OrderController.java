package org.prgrms.coffee_order_be.order.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.prgrms.coffee_order_be.order.dto.OrderCreateDto;
import org.prgrms.coffee_order_be.order.dto.OrderResponseDto;
import org.prgrms.coffee_order_be.order.dto.OrderUpdateDto;
import org.prgrms.coffee_order_be.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderResponseDto> createOrder(
      @Valid @RequestBody OrderCreateDto createDto) {
    OrderResponseDto responseDto = orderService.createOrder(createDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  @GetMapping
  public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
    List<OrderResponseDto> responseDtos = orderService.getOrders();
    return ResponseEntity.ok().body(responseDtos);
  }

  @GetMapping("/by-email")
  public ResponseEntity<List<OrderResponseDto>> getOrdersByEmail(
      @RequestParam("email") String email) {
    List<OrderResponseDto> responseDtos = orderService.getOrdersByEmail(email);
    return ResponseEntity.ok().body(responseDtos);
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<OrderResponseDto> getOrderById(
      @PathVariable("orderId") UUID orderId) {
    OrderResponseDto responseDto = orderService.getOrder(orderId);
    return ResponseEntity.ok().body(responseDto);
  }

  @PutMapping("/{orderId}")
  public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable("orderId") UUID orderId,
      @RequestBody OrderUpdateDto updateDto) {
    OrderResponseDto responseDto = orderService.updateOrder(orderId, updateDto);
    return ResponseEntity.ok().body(responseDto);
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<OrderResponseDto> deleteOrder(@PathVariable("orderId") UUID orderId) {
    orderService.deleteOrder(orderId);
    return ResponseEntity.noContent().build();
  }

}
