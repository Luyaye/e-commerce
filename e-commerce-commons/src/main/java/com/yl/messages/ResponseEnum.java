package com.yl.messages;

public interface ResponseEnum {

  Integer getCode();

  String getMessage();

  default String getLocaleMessage() {
    return getLocaleMessage(null);
  }

  String getLocaleMessage(Object[] args);
}
