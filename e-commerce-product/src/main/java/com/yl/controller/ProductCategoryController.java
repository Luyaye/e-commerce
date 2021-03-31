package com.yl.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yl.base.ResponseBase;
import com.yl.entity.ProductCategory;
import com.yl.service.IProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品分类表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-03-26
 */
@RestController
@RequestMapping("/product-category")
@Api(tags = "商品分类相关接口")
public class ProductCategoryController {

  @Autowired
  private IProductCategoryService categoryService;

  @PostMapping("/add")
  @ApiOperation("添加商品分类")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "productCategory", value = "商品分类信息")
  })
  public ResponseBase addCategory(@RequestBody(required = true) ProductCategory productCategory) {
    return categoryService.create(productCategory);
  }

  @DeleteMapping("/delete")
  @ApiOperation("删除商品分类信息")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "categoryId", value = "商品分类ID")
  })
  public ResponseBase deleteCategory(@RequestParam(value = "categoryId", required = true) Long categoryId) {
    return categoryService.delete(categoryId);
  }

  @GetMapping("/page")
  @ApiOperation("分页查询商品分类信息")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "name", value = "分类名称"),
    @ApiImplicitParam(name = "level", value = "级别"),
    @ApiImplicitParam(name = "skip", value = "起始行"),
    @ApiImplicitParam(name = "limit", value = "数量限制")
  })
  public IPage<ProductCategory> page(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "level", required = false) Integer level,
                                     @RequestParam(value = "skip", required = true) Integer skip,
                                     @RequestParam(value = "limit", required = true) Integer limit) {
    return categoryService.page(name, level, skip, limit);
  }

  @PostMapping("/edit")
  @ApiOperation("修改商品分类信息")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "productCategory", value = "修改后的商品分类信息")
  })
  public ResponseBase update(@RequestBody ProductCategory productCategory) {
    return categoryService.update(productCategory);
  }

  @GetMapping("/{categoryId}")
  @ApiOperation("根据分类ID查询商品分类信息")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "categoryId", value = "商品分类ID")
  })
  public ResponseBase get(@PathVariable(value = "categoryId", required = true) Long categoryId) {
    return categoryService.getCategoryById(categoryId);
  }

  @GetMapping("/findSubCategory/{parentId}")
  @ApiOperation("获取下级商品分类信息")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "parentId", value = "父级分类ID")
  })
  public ResponseBase findByParentId(@PathVariable(value = "parentId", required = true) Long parentId) {
    return categoryService.findByParentId(parentId);
  }

  @GetMapping("/findAllParentCategory")
  @ApiOperation("获取所有父级分类信息")
  public ResponseBase findAllParentCategory() {
    return categoryService.findAllParentCategory();
  }
}
