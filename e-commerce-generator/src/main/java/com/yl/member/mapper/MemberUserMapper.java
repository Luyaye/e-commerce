package com.yl.member.mapper;

import com.yl.member.entity.MemberUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2021-02-26
 */
@Mapper
public interface MemberUserMapper extends BaseMapper<MemberUser> {

}
