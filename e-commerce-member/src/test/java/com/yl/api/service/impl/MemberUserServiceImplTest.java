package com.yl.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.base.ResponseBase;
import com.yl.member.entity.MemberUser;
import com.yl.member.mapper.MemberUserMapper;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MemberUserServiceImplTest extends TestCase {

  @Autowired
  private MemberUserMapper memberUserMapper;
  @Autowired
  private MemberUserServiceImpl service;

  @Test
  public void test() {
    QueryWrapper<MemberUser> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("id", 7);
    MemberUser memberUser = memberUserMapper.selectOne(queryWrapper);
    log.info(memberUser.toString());
  }

  @Test
  public void getUserByIdTest() {
    long userId = 7;
    ResponseBase responseBase = service.getUserById(userId);
    log.info(responseBase.toString());
  }

  @Test
  public void deleteUserTest() {
    Integer num = memberUserMapper.deleteById(7);
    log.info("num: -> {}", num);
  }
}