package com.yl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yl.base.BaseApiService;
import com.yl.base.ResponseBase;
import com.yl.entity.ProductCategory;
import com.yl.exceptions.AmaException;
import com.yl.mapper.ProductCategoryMapper;
import com.yl.messages.ErrorEnum;
import com.yl.service.IProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-03-26
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

  @Autowired
  private BaseApiService baseApiService;
  @Autowired
  private ProductCategoryMapper categoryMapper;

  /**
   * 创建商品分类信息
   *
   * @param productCategory
   * @return
   */
  @Override
  public ResponseBase create(ProductCategory productCategory) {
    return save(productCategory) ? baseApiService.setResultSuccess("创建成功！") : baseApiService.setResultError("创建失败！");
  }

  /**
   * 删除分类信息
   *
   * @param categoryId
   * @return
   */
  @Override
  public ResponseBase delete(long categoryId) {
    //需要判断商品分类中是否存在商品，如果存在不允许删除

    return categoryMapper.deleteById(categoryId) > 0 ? baseApiService.setResultSuccess("删除成功！") : baseApiService.setResultError("删除失败！");
  }

  /**
   * 分页查询商品分类信息
   *
   * @param name
   * @param level
   * @param skip
   * @param limit
   * @return
   */
  @Override
  public IPage<ProductCategory> page(String name, Integer level, Integer skip, Integer limit) {
    QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("name", name);
    queryWrapper.eq("level", level);
    queryWrapper.eq("parent_id", 0);

    Page<ProductCategory> page = new Page<>(skip, level);
    return page(page, queryWrapper);
  }

  /**
   * 修改商品分类信息
   *
   * @param productCategory
   * @return
   */
  @Override
  public ResponseBase update(ProductCategory productCategory) {
    if (productCategory.getId() == null)
      throw new AmaException(ErrorEnum.MISSING_PARAM, "商品分类ID不能为空！");

    ProductCategory oldCategory = getById(productCategory.getId());
    if (oldCategory == null)
      throw new AmaException(ErrorEnum.NOT_FOUND, "数据不存在！");

    boolean isUpdate = updateById(productCategory);
    return isUpdate ? baseApiService.setResultSuccess("更新成功！") : baseApiService.setResultError("更新失败！");
  }

  /**
   * 根据categoryId获取商品分类信息
   *
   * @param categoryId
   * @return
   */
  @Override
  public ResponseBase getCategoryById(Long categoryId) {
    ProductCategory productCategory = getById(categoryId);
    if (productCategory == null)
      throw new AmaException(ErrorEnum.NOT_FOUND, "数据不存在！");

    return baseApiService.setResultSuccess(productCategory);
  }

  /**
   * 获取下级分类信息
   *
   * @param parentId
   * @return
   */
  @Override
  public ResponseBase findByParentId(Long parentId) {
    QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("parent_id", parentId);

    List<ProductCategory> list = list(queryWrapper);
    return baseApiService.setResultSuccess(list);
  }

  /**
   * 获取所有一级商品分类信息
   *
   * @return
   */
  @Override
  public ResponseBase findAllParentCategory() {
    QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("parent_id", 0);

    List<ProductCategory> list = list(queryWrapper);
    if (CollectionUtils.isEmpty(list))
      throw new AmaException(ErrorEnum.NOT_FOUND, "数据不存在");

    return baseApiService.setResultSuccess(list);
  }
}
