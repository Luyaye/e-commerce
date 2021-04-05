package com.yl.service;

import com.yl.base.ResponseBase;
import com.yl.entity.MemberUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-04-05
 */
public interface IMemberUserService extends IService<MemberUser> {

  /**
   * 根据用户名密码获取token
   *
   * @param userName
   * @param password
   * @return
   */
  String getMemberInfo(String userName, String password);

}
