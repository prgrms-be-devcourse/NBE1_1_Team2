package org.prgrms.coffee_order_be.common.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessLogicException.class)
  public ResponseEntity<ExceptionResponse> handleBadRequestException(BusinessLogicException e) {
    log.warn(e.getMessage(), e);

    return ResponseEntity.badRequest()
        .body(new ExceptionResponse(e.getExceptionCode().getCode(), e.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> exampleResponseValidation(
      MethodArgumentNotValidException e) {
    Map<String, String> error = new HashMap<>();
    e.getAllErrors().forEach(
        c -> error.put(((FieldError) c).getField(), c.getDefaultMessage())
    );
    return ResponseEntity.badRequest().body(error);
  }
}
