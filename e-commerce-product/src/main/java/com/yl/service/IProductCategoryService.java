package com.yl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yl.base.ResponseBase;
import com.yl.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品分类表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-03-26
 */
public interface IProductCategoryService extends IService<ProductCategory> {

  /**
   * 创建商品分类信息
   *
   * @param productCategory
   * @return
   */
  ResponseBase create(ProductCategory productCategory);

  /**
   * 删除分类信息
   *
   * @param categoryId
   * @return
   */
  ResponseBase delete(long categoryId);

  /**
   * 分页查询商品分类信息
   *
   * @param name
   * @param level
   * @param skip
   * @param limit
   * @return
   */
  IPage<ProductCategory> page(String name, Integer level, Integer skip, Integer limit);

  /**
   * 修改商品分类信息
   *
   * @param productCategory
   * @return
   */
  ResponseBase update(ProductCategory productCategory);

  /**
   * 根据categoryId获取商品分类信息
   *
   * @param categoryId
   * @return
   */
  ResponseBase getCategoryById(Long categoryId);

  /**
   * 获取下级分类信息
   *
   * @param parentId
   * @return
   */
  ResponseBase findByParentId(Long parentId);

  /**
   * 获取所有一级商品分类信息
   *
   * @return
   */
  ResponseBase findAllParentCategory();
}
