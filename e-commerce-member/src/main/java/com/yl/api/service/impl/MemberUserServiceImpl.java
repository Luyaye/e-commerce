package com.yl.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.api.member.service.MemberUserService;
import com.yl.base.BaseApiService;
import com.yl.base.ResponseBase;
import com.yl.constants.Constants;
import com.yl.member.entity.MemberUser;
import com.yl.member.mapper.MemberUserMapper;
import com.yl.mq.RegisterMailboxProducer;
import com.yl.utils.MD5Util;
import com.yl.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class MemberUserServiceImpl extends BaseApiService implements MemberUserService {

  @Autowired
  private MemberUserMapper memberUserMapper;
  @Autowired
  private RegisterMailboxProducer registerMailboxProducer;
  @Value("${messages.queue}")
  private String MESSAGESQUEUE;

  /**
   * 根据userId获取会员信息
   *
   * @param userId
   * @return
   */
  @Override
  public ResponseBase getUserById(@RequestParam("userId") Long userId) {
    Optional<MemberUser> optional = Optional.ofNullable(memberUserMapper.selectById(userId));
    if (!optional.isPresent()) {
      return setResultError("用户信息不存在。");
    }
    return setResultSuccess(optional.get());
  }

  /**
   * 会员注册
   *
   * @param memberUserEntity
   * @return
   */
  @Override
  public ResponseBase regUser(@RequestBody MemberUser memberUserEntity) {
    String username = memberUserEntity.getUsername();
    if (StringUtils.isEmpty(username)) {
      return setResultError("用户名不能为空！");
    }
    String password = memberUserEntity.getPassword();
    if (StringUtils.isEmpty(password))
      return setResultError("密码不能为空！");
    String newPassword = MD5Util.MD5(password);
    memberUserEntity.setPassword(newPassword);
    String email = memberUserEntity.getEmail();
    if (StringUtils.isEmpty(email))
      return setResultError("邮箱不能为空！");
    long currentTime = System.currentTimeMillis();
    memberUserEntity.setCreatedAt(currentTime);
    memberUserEntity.setUpdatedAt(currentTime);
    Integer result = memberUserMapper.insert(memberUserEntity);
    if (result <= 0)
      return setResultError("注册用户信息失败！");
    // 采用异步方式发送消息
    String json = emailJson(email);
    log.info("####会员服务推送消息到消息服务平台####json:{}", json);
    sendMsg(json);

    return setResultSuccess("用户注册成功！");
  }

  /**
   * 用户登录
   *
   * @param memberUserEntity
   * @return
   */
  @Override
  public ResponseBase login(@RequestBody MemberUser memberUserEntity) {
    String username = memberUserEntity.getUsername();
    String password = memberUserEntity.getPassword();
    if (StringUtils.isBlank(username)) {
      return setResultError("登录账号不能为空！");
    }
    if (StringUtils.isBlank(password)) {
      return setResultError("密码不能为空！");
    }
    String newPassword = MD5Util.MD5(password);
    QueryWrapper<MemberUser> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username", username);
    queryWrapper.eq("password", newPassword);
    Optional<MemberUser> optional = Optional.ofNullable(memberUserMapper.selectOne(queryWrapper));
    if (!optional.isPresent()) {
      return setResultError("账号或密码错误！");
    }
    String memberToken = TokenUtils.getMemberToken();
    Long userId = optional.get().getId();
    log.info("####用户信息token存放在redis中。。。key为：{}, value:{}", memberToken, userId);
    baseRedisService.setString(memberToken, userId + "", Constants.TOKEN_MEMBER_TIME);

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("memberToken", memberToken);

    return setResultSuccess(jsonObject);
  }

  /**
   * 根据token获取用户信息
   *
   * @param token
   * @return
   */
  @Override
  public ResponseBase findUserByToken(@RequestParam("token") String token) {
    if (StringUtils.isBlank(token)) {
      return setResultError("token不能为空！");
    }

    String strUserId = (String) baseRedisService.getString(token);
    if (StringUtils.isBlank(strUserId)) {
      return setResultError("token无效或已经过期！");
    }
    Long userId = Long.parseLong(strUserId);
    MemberUser memberUserEntity = memberUserMapper.selectById(userId);
    if (memberUserEntity == null) {
      return setResultError("用户不存在！");
    }
    memberUserEntity.setPassword(null);
    return setResultSuccess(memberUserEntity);
  }

  /**
   * 封装email信息
   *
   * @param email
   * @return
   */
  private String emailJson(String email) {
    JSONObject rootJson = new JSONObject();
    JSONObject header = new JSONObject();
    header.put("interfaceType", Constants.MSG_EMAIL);
    JSONObject content = new JSONObject();
    content.put("email", email);
    rootJson.put("header", header);
    rootJson.put("content", content);
    return rootJson.toJSONString();
  }

  private void sendMsg(String json) {
    ActiveMQQueue activeMQQueue = new ActiveMQQueue(MESSAGESQUEUE);
    registerMailboxProducer.sendMsg(activeMQQueue, json);
  }

}
