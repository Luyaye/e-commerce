package com.yl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yl.base.ResponseBase;
import com.yl.entity.ProductBrand;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品品牌表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-03-26
 */
public interface IProductBrandService extends IService<ProductBrand> {

  /**
   * 创建商品品牌信息
   *
   * @param productBrand
   * @return
   */
  ResponseBase create(ProductBrand productBrand);

  /**
   * 更新品牌信息
   *
   * @param productBrand
   * @return
   */
  ResponseBase update(ProductBrand productBrand);

  /**
   * 删除品牌信息
   *
   * @param brandId
   * @return
   */
  ResponseBase delete(Long brandId);

  /**
   * 分页查询商品品牌信息
   *
   * @param name
   * @param firstLetter
   * @param skip
   * @param limit
   * @return
   */
  IPage<ProductBrand> page(String name, String firstLetter, Integer skip, Integer limit);

}
