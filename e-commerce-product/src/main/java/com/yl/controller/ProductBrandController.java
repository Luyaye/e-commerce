package com.yl.controller;


import com.yl.base.ResponseBase;
import com.yl.entity.ProductBrand;
import com.yl.service.IProductBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品品牌表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-03-26
 */
@RestController
@RequestMapping("/product-brand")
@Api(tags = "商品品牌")
public class ProductBrandController {

  @Autowired
  private IProductBrandService productBrandService;

  @PostMapping("/create")
  @ApiOperation("创建商品品牌信息")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "productBrand", value = "商品品牌信息")
  })
  public ResponseBase create(@RequestBody ProductBrand productBrand) {
    return productBrandService.create(productBrand);
  }

}
