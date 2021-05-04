package com.lubycon.curriculum.base.error;

import com.lubycon.curriculum.base.error.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

  // javax.validation.Valid or @Validated 으로 binding error 발생시 발생
  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      final MethodArgumentNotValidException e) {
    final ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
    final ErrorResponse response = ErrorResponse
        .of(errorCode, e.getBindingResult());
    return new ResponseEntity<>(response, errorCode.getStatus());
  }

  // @ModelAttribut으로 binding error 발생시 BindException 발생
  @ExceptionHandler(BindException.class)
  protected ResponseEntity<ErrorResponse> handleBindException(final BindException e) {
    final ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
    final ErrorResponse response = ErrorResponse
        .of(errorCode, e.getBindingResult());
    return new ResponseEntity<>(response, errorCode.getStatus());
  }

  // enum type 일치하지 않아 binding 못할 경우 발생 주로 @RequestParam enum으로 binding 못했을 경우 발생
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
      final MethodArgumentTypeMismatchException e) {
    final ErrorCode errorCode = ErrorCode.FAIL_ENUM_BINDING;
    final ErrorResponse response = ErrorResponse.of(e);
    return new ResponseEntity<>(response, errorCode.getStatus());
  }

  // 지원하지 않은 HTTP method 호출 할 경우 발생
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
      final HttpRequestMethodNotSupportedException e) {
    final ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
    final ErrorResponse response = ErrorResponse.of(errorCode);
    return new ResponseEntity<>(response, errorCode.getStatus());
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
    final ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
    final ErrorResponse response = ErrorResponse.of(errorCode);
    return new ResponseEntity<>(response, errorCode.getStatus());
  }

  @ExceptionHandler(BusinessException.class)
  protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
    final ErrorCode errorCode = e.getErrorCode();
    final ErrorResponse response = ErrorResponse.of(errorCode, e.getMessage());
    return new ResponseEntity<>(response, errorCode.getStatus());
  }
}