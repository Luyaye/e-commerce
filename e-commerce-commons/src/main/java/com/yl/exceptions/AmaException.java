package com.yl.exceptions;

import com.yl.messages.ResponseEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AmaException extends RuntimeException {

  private final ResponseEnum responseEnum;
  private final Object[] args;//打印参数
  private final String message;//异常信息
  private final Throwable cause;//异常栈

  public AmaException(final ResponseEnum responseEnum) {
    this(responseEnum, null, responseEnum.getMessage(), null);
  }

  public AmaException(final ResponseEnum responseEnum, final String message) {
    this(responseEnum, null, message, null);
  }

  public AmaException(final ResponseEnum responseEnum, final Object[] args, final String message) {
    this(responseEnum, args, message, null);
  }

  public AmaException(final ResponseEnum responseEnum, final Object[] args, final String message, final Throwable cause) {
    this.responseEnum = responseEnum;
    this.args = args;
    this.message = message;
    this.cause = cause;
  }

}
