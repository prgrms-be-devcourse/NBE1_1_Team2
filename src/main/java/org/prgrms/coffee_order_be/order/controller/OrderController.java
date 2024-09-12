package org.prgrms.coffee_order_be.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Order API", description = "주문 CRUD 관련 API")
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @Operation(summary = "새로운 주문 생성", description = "새 주문을 생성함.")
  @PostMapping
  public ResponseEntity<OrderResponseDto> createOrder(
      @Valid @RequestBody OrderCreateDto createDto) {
    OrderResponseDto responseDto = orderService.createOrder(createDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  @Operation(summary = "모든 주문 조회", description = "모든 주문 내역을 리스트로 조회함.")
  @GetMapping
  public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
    List<OrderResponseDto> responseDtos = orderService.getOrders();
    return ResponseEntity.ok().body(responseDtos);
  }

  @Operation(summary = "이메일로 주문 조회", description = "사용자의 이메일로 주문 내역을 조회함.")
  @GetMapping("/by-email")
  public ResponseEntity<List<OrderResponseDto>> getOrdersByEmail(
      @RequestParam("email") String email) {
    List<OrderResponseDto> responseDtos = orderService.getOrdersByEmail(email);
    return ResponseEntity.ok().body(responseDtos);
  }

  @Operation(summary = "주문 ID로 주문 조회", description = "주문 ID로 특정 주문을 조회함.")
  @GetMapping("/{orderId}")
  public ResponseEntity<OrderResponseDto> getOrderById(
      @PathVariable("orderId") UUID orderId) {
    OrderResponseDto responseDto = orderService.getOrder(orderId);
    return ResponseEntity.ok().body(responseDto);
  }

  @Operation(summary = "주문 정보(주소/우편번호) 수정", description = "주문 ID로 특정 주문의 주소, 우편번호를 수정함.")
  @PutMapping("/{orderId}")
  public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable("orderId") UUID orderId,
      @RequestBody OrderUpdateDto updateDto) {
    OrderResponseDto responseDto = orderService.updateOrder(orderId, updateDto);
    return ResponseEntity.ok().body(responseDto);
  }

  @Operation(summary = "주문 삭제", description = "주문 ID로 특정 주문을 삭제함. 단, 주문 상태가 '완료'일 경우에만 가능.")
  @DeleteMapping("/{orderId}")
  public ResponseEntity<OrderResponseDto> deleteOrder(@PathVariable("orderId") UUID orderId) {
    orderService.deleteOrder(orderId);
    return ResponseEntity.noContent().build();
  }

}
