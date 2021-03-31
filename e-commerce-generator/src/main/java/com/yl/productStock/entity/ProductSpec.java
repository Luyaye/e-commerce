package com.yl.productStock.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品规格表
 * </p>
 *
 * @author jobob
 * @since 2021-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProductSpec对象", description="商品规格表")
public class ProductSpec implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品规格表ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "平台（1，BBC    2，B2C）")
    private Integer channel;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品规格")
    private String productSpec;

    @ApiModelProperty(value = "规格详情")
    private String productSpecInfo;

    @ApiModelProperty(value = "商品编号")
    private String productSerialNumber;

    @ApiModelProperty(value = "商品多规格图片")
    private String image;

    @ApiModelProperty(value = "多规格价格（单位：分）")
    private Long price;

    @ApiModelProperty(value = "多规格_productId")
    private String specKey;

    @ApiModelProperty(value = "商品单位")
    private String unit;

    @ApiModelProperty(value = "实际库存")
    private BigDecimal realityStock;

    @ApiModelProperty(value = "可销售库存")
    private BigDecimal vendableStock;

    @ApiModelProperty(value = "超售库存")
    private BigDecimal overbookStock;

    @ApiModelProperty(value = "实际超售")
    private BigDecimal actualOverbook;

    @ApiModelProperty(value = "成本均价（单位：分）")
    private Long costAvgPrice;

    @ApiModelProperty(value = "现价（单位：分）")
    private Long currentPrice;

    @ApiModelProperty(value = "库存金额（单位：分）")
    private Long stockAmount;

    @ApiModelProperty(value = "仓库ID")
    private Long storageId;

    @ApiModelProperty(value = "仓库货位")
    private String storageShelf;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty(value = "是否开启称重")
    private Boolean weighSwitch;

    @ApiModelProperty(value = "称重单位 1 Kg 2 g")
    private Integer weighUnit;

    @ApiModelProperty(value = "总重量(g)")
    private Long totalWeigh;

    @ApiModelProperty(value = "实际总重量(g)")
    private BigDecimal realityWeigh;

    @ApiModelProperty(value = "可销售总重量(g)")
    private BigDecimal vendableWeigh;

    @ApiModelProperty(value = "称重商品类型：0 普通商品，1 计重商品, 2 收银称重商品, 3 收银称重和计重共存")
    private Integer weighType;

    @ApiModelProperty(value = "创建时间")
    private Long createdAt;

    @ApiModelProperty(value = "更新时间")
    private Long updatedAt;


}
