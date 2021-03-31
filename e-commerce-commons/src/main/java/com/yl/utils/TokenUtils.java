package com.yl.utils;

import com.yl.constants.Constants;

import java.util.UUID;

public class TokenUtils {

  public static String getMemberToken(){
    return Constants.TOKEN_MEMBER + "_" + UUID.randomUUID();
  }

}
