package com.yl.commons;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MetaHandler implements MetaObjectHandler {

  /**
   * 新增数据执行
   *
   * @param metaObject
   */
  @Override
  public void insertFill(MetaObject metaObject) {
    boolean hasSetter = metaObject.hasSetter("createdAt");
    if (hasSetter) {
      this.setFieldValByName("createdAt", System.currentTimeMillis(), metaObject);
      this.setFieldValByName("updatedAt", System.currentTimeMillis(), metaObject);
    }
  }

  /**
   * 更新数据执行
   *
   * @param metaObject
   */
  @Override
  public void updateFill(MetaObject metaObject) {
    Object val = getFieldValByName("updatedAt", metaObject);
    if (val == null) {
      this.setFieldValByName("updatedAt", System.currentTimeMillis(), metaObject);
    }
  }
}
