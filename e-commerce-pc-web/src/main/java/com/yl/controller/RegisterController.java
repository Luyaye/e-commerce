package com.yl.controller;

import com.yl.api.member.entity.MemberUserEntity;
import com.yl.base.ResponseBase;
import com.yl.constants.Constants;
import com.yl.fegin.MemberServiceFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

  private static final String REGISTER = "register";
  private static final String LOGIN = "login";

  @Autowired
  private MemberServiceFegin memberServiceFegin;

  //跳转注册页面
  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String register() {
    return REGISTER;
  }

  // 注册业务具体实现
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String registerPost(MemberUserEntity memberUserEntity, HttpServletRequest request) {
    // 1、验证参数
    // 2、调用会员注册接口
    ResponseBase regUser = memberServiceFegin.regUser(memberUserEntity);
    // 3、如果失败，跳转到失败页面
    if (!regUser.getCode().equals(Constants.HTTP_RES_CODE_200)) {
      request.setAttribute("error", "注册失败！");
      return REGISTER;
    }
    // 4。如果成功，跳转到成功页面
    return LOGIN ;
  }
}
