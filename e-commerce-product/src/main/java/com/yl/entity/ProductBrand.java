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
 * 商品品牌表
 * </p>
 *
 * @author jobob
 * @since 2021-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductBrand对象", description="商品品牌表")
public class ProductBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "首字母")
    private String firstLetter;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否为品牌制造商 0 -> 不显示；1 -> 显示")
    private Integer factoryStatus;

    @ApiModelProperty(value = "显示状态 0 -> 不显示 1 -> 显示")
    private Integer showStatus;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

    @ApiModelProperty(value = "图标")
    private String logo;

    @ApiModelProperty(value = "关键字")
    private String keywords;

    @ApiModelProperty(value = "品牌故事")
    private String brandStory;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createdAt;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Long updatedAt;


}
