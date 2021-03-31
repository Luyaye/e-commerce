package com.yl.api.member.service;

import com.yl.base.ResponseBase;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/member")
public interface TestApiService {

  @RequestMapping("/test")
  Map<String, Object> test(Long id, String name);

  @RequestMapping("/testResponseBase")
  ResponseBase testResponseBase();

  @PutMapping("/setTestRedis")
  ResponseBase setTestRedis(String key, String value);

}
