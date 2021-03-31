package com.yl.api.member.service;

import com.yl.base.ResponseBase;
import com.yl.member.entity.MemberUser;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
public interface MemberUserService {

  @GetMapping("/getUserById")
  ResponseBase getUserById(@RequestParam("userId") Long userId);

  @PostMapping("/regUser")
  ResponseBase regUser(@RequestBody MemberUser memberUserEntity);

  /**
   * 用户登录
   *
   * @param memberUserEntity
   * @return
   */
  @PostMapping("/login")
  ResponseBase login(@RequestBody MemberUser memberUserEntity);

  /**
   * 根据token获取用户信息
   *
   * @param token
   * @return
   */
  @GetMapping("/findUserByToken")
  ResponseBase findUserByToken(@RequestParam("token") String token);
}
