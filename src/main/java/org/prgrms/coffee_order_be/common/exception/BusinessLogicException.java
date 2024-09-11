package org.prgrms.coffee_order_be.common.exception;

import lombok.Getter;
import org.prgrms.coffee_order_be.common.exception.ExceptionCode;

@Getter
public class BusinessLogicException extends RuntimeException {

  private final ExceptionCode exceptionCode;

  public BusinessLogicException(ExceptionCode exceptionCode) {
    super(exceptionCode.getMessage());
    this.exceptionCode = exceptionCode;
  }

}
