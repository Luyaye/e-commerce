package com.yl.service.impl;

import com.yl.base.BaseApiService;
import com.yl.base.ResponseBase;
import com.yl.entity.ProductBrand;
import com.yl.exceptions.AmaException;
import com.yl.mapper.ProductBrandMapper;
import com.yl.messages.ErrorEnum;
import com.yl.service.IProductBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品品牌表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-03-26
 */
@Service
public class ProductBrandServiceImpl extends ServiceImpl<ProductBrandMapper, ProductBrand> implements IProductBrandService {

  @Autowired
  private BaseApiService baseApiService;
  @Autowired
  private ProductBrandMapper productBrandMapper;

  /**
   * 创建商品品牌信息
   *
   * @param productBrand
   * @return
   */
  @Override
  public ResponseBase create(ProductBrand productBrand) {
    if (productBrand.getName() == null)
      throw new AmaException(ErrorEnum.MISSING_PARAM, "品牌名称不能为空！");

    boolean isCreate = save(productBrand);
    return isCreate ? baseApiService.setResultSuccess("创建成功！") : baseApiService.setResultError("创建失败！");
  }

  /**
   * 更新品牌信息
   *
   * @param productBrand
   * @return
   */
  @Override
  public ResponseBase update(ProductBrand productBrand) {
    if (productBrand.getId() == null)
      throw new AmaException(ErrorEnum.MISSING_PARAM, "主键ID不能为空！");

    ProductBrand old = getById(productBrand.getId());
    if (old == null)
      throw new AmaException(ErrorEnum.NOT_FOUND, "数据不存在！");

    return updateById(productBrand) ? baseApiService.setResultSuccess("更新成功！") : baseApiService.setResultError("更新失败！");
  }

  /**
   * 删除品牌信息
   *
   * @param brandId
   * @return
   */
  @Override
  public ResponseBase delete(Long brandId) {
    return removeById(brandId) ? baseApiService.setResultSuccess("删除chengg！") : baseApiService.setResultError("删除失败！");
  }

}
