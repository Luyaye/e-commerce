package com.yl.controller;


import com.yl.base.BaseApiService;
import com.yl.base.ResponseBase;
import com.yl.service.IMemberUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/member-user")
public class MemberUserController {

  @Autowired
  private IMemberUserService memberUserService;
  @Autowired
  private BaseApiService baseApiService;

  /**
   * 用户登录获取token值
   *
   * @param username
   * @param password
   * @return
   */
  @GetMapping("/login")
  public ResponseBase login(@RequestParam("username") String username, @RequestParam("password") String password) {
    String loginToken = memberUserService.getMemberInfo(username, password);

    return baseApiService.setResultSuccess(loginToken);
  }

}
