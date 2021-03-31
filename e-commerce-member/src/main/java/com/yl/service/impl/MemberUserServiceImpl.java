package com.yl.service.impl;

import com.yl.entity.MemberUser;
import com.yl.mapper.MemberUserMapper;
import com.yl.service.IMemberUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-03-24
 */
@Service
public class MemberUserServiceImpl extends ServiceImpl<MemberUserMapper, MemberUser> implements IMemberUserService {

}
