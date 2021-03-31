package com.yl.api.service.impl;

import com.google.common.collect.Maps;
import com.yl.api.member.service.TestApiService;
import com.yl.base.BaseApiService;
import com.yl.base.ResponseBase;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestApiServiceImpl extends BaseApiService implements TestApiService {

  @Override
  public Map<String, Object> test(Long id, String name) {
    Map<String, Object> result = Maps.newHashMap();
    result.put("code", "200");
    result.put("msg", "success");
    result.put("data", "id:" + id + ",name:" + name);
    return result;
  }

  @Override
  public ResponseBase testResponseBase() {
    return setResultSuccess();
  }

  @Override
  public ResponseBase setTestRedis(String key, String value) {
    baseRedisService.setString(key, value, null);
    return setResultSuccess();
  }
}
