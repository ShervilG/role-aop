package com.project.aspecttest.exception;

public class UnauthorizedRoleException extends RuntimeException {

  public UnauthorizedRoleException(String message) {
    super(message);
  }
}
