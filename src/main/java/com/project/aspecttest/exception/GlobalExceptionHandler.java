package com.project.aspecttest.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UnauthorizedRoleException.class)
  public ResponseEntity<ExceptionBody> handleUnauthorizedRole(UnauthorizedRoleException e) {
    ExceptionBody exceptionBody = new ExceptionBody().toBuilder()
        .code("UNAUTHORIZED_ROLE")
        .message(e.getMessage())
        .build();
    return new ResponseEntity<>(exceptionBody, HttpStatus.UNAUTHORIZED);

  }
}
