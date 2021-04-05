package com.yl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.base.BaseApiService;
import com.yl.base.ResponseBase;
import com.yl.entity.MemberUser;
import com.yl.exceptions.AmaException;
import com.yl.mapper.MemberUserMapper;
import com.yl.messages.ErrorEnum;
import com.yl.service.IMemberUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yl.utils.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-04-05
 */
@Service
public class MemberUserServiceImpl extends ServiceImpl<MemberUserMapper, MemberUser> implements IMemberUserService {

  @Autowired
  private BaseApiService baseApiService;

  /**
   * 根据用户名密码获取token
   *
   * @param userName
   * @param password
   * @return
   */
  @Override
  public String getMemberInfo(String userName, String password) {
    if (StringUtils.isBlank(userName))
      throw new AmaException(ErrorEnum.MISSING_PARAM, "用户名不能为空");
    if (StringUtils.isBlank(password))
      throw new AmaException(ErrorEnum.MISSING_PARAM, "密码不能为空");

    QueryWrapper<MemberUser> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username", userName);
    queryWrapper.eq("password", password);

    MemberUser user = getOne(queryWrapper);
    if (user == null)
      throw new AmaException(ErrorEnum.NOT_FOUND);

    String memberToken = TokenUtils.getMemberToken();
    return memberToken;
  }

}
