package com.yl.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author jobob
 * @since 2021-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MemberUser对象", description="用户信息表")
public class MemberUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "会员名称")
    private String username;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "删除状态（默认：正常  false）")
    @TableLogic
    private Boolean deleted;

    @ApiModelProperty(value = "创建时间")
    private Long createdAt;

    @ApiModelProperty(value = "最后修改时间")
    private Long updatedAt;


}
