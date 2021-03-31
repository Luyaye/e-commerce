package com.yl.base;

import com.yl.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseApiService {
  @Autowired
  public BaseRedisService baseRedisService;

  //返回成功，可以传data值
  public ResponseBase setResultSuccess(Object data) {
    return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, data);
  }

  //返回成功，没有data值
  public ResponseBase setResultSuccess() {
    return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, null);
  }

  public ResponseBase setResultSuccess(String msg) {
    return setResult(Constants.HTTP_RES_CODE_200, msg, null);
  }

  //返回失败，可以传msg值
  public ResponseBase setResultError(String msg) {
    return setResult(Constants.HTTP_RES_CODE_500, msg, null);
  }

  //返回失败，没有data值
  public ResponseBase setResultError() {
    return setResult(Constants.HTTP_RES_CODE_500, Constants.HTTP_RES_CODE_500_VALUE, null);
  }

  //通用封装
  public ResponseBase setResult(Integer code, String msg, Object data) {
    return new ResponseBase(code, msg, data);
  }

}
