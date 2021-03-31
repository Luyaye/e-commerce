package com.yl.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品分类表
 * </p>
 *
 * @author jobob
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductCategory对象", description="商品分类表")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品分类主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "上级分类的编号 0 表示一级分类")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "分类级别 0 -> 1级  1 -> 2级")
    private Integer level;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

    @ApiModelProperty(value = "是否显示在导航栏 0 -> 不显示；1 -> 显示")
    private Integer navStatus;

    @ApiModelProperty(value = "显示状态 0 -> 不显示 1 -> 显示")
    private Integer showStatus;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "关键字")
    private String keywords;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createdAt;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Long updatedAt;


}
