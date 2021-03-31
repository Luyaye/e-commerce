package com.yl.controller;

import com.yl.base.ResponseBase;
import com.yl.constants.Constants;
import com.yl.fegin.MemberServiceFegin;
import com.yl.member.entity.MemberUser;
import com.yl.utils.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

@Controller
public class LoginController {

  private final static String LOGIN = "login";
  private final static String INDEX = "index";

  @Autowired
  private MemberServiceFegin memberServiceFegin;

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String LoginGet() {
    return LOGIN;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String loginPost(MemberUser memberUserEntity, HttpServletRequest request, HttpServletResponse response) {
    //1、验证参数
    //2、调用登录接口，获取token信息
    ResponseBase loginBase = memberServiceFegin.login(memberUserEntity);
    if (!loginBase.getCode().equals(Constants.HTTP_RES_CODE_200)) {
      request.setAttribute("error", "账号或密码错误！");
      return LOGIN;
    }
    LinkedHashMap loginData = (LinkedHashMap) loginBase.getData();
    String memberToken = (String) loginData.getOrDefault("memberToken", "");
    if (StringUtils.isBlank(memberToken)) {
      request.setAttribute("error", "会话已经失效！");
      return LOGIN;
    }
    //3、将token信息存放在cookie里面
    CookieUtil.addCookie(response, Constants.COOKIE_MEMBER_TOKEN, memberToken, Constants.TOKEN_MEMBER_TIME.intValue());

    return INDEX;
  }
}
