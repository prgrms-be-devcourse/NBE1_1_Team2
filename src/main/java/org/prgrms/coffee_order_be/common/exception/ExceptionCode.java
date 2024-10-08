package org.prgrms.coffee_order_be.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

  // PRODUCT ERROR
  DUPLICATED_PRODUCT(400, "이미 존재하는 제품입니다."),
  NOT_FOUND_PRODUCT(404, "존재하지 않는 제품입니다."),

  // ORDER ERROR
  NOT_FOUND_ORDER(404, "존재하지 않는 주문입니다."),
  CANNOT_UPDATE_ORDER(400, "업데이트할 수 없는 주문입니다. (배송 시작)"),
  CANNOT_DELETE_ORDER(400, "삭제할 수 없는 주문입니다. (배송 시작)"),

  // COUPON ERROR
  NOT_FOUND_COUPON(404, "존재하지 않는 쿠폰입니다."),
  EXIST_COUPON(400, "이미 발급한 쿠폰 입니다."),
  ALREADY_USED_COUPON(400, "이미 사용한 쿠폰 입니다."),
  ALREADY_CANCELED_COUPON(400, "이미 취소한 쿠폰 입니다."),

  // USER ERROR
  NOT_FOUND_USER(404, "존재하지 않는 회원입니다.");



  private final int code;
  private final String message;
}
