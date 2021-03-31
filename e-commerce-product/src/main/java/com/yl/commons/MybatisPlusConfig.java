package com.yl.commons;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

  /**
   * 分页插件
   */
  @Bean
  public PaginationInnerInterceptor paginationInnerInterceptor() {
    return new PaginationInnerInterceptor();
  }

  /**
   * 自动填充功能
   *
   * @return
   */
  @Bean
  public GlobalConfig globalConfig() {
    GlobalConfig globalConfig = new GlobalConfig();
    globalConfig.setMetaObjectHandler(new MetaHandler());
    return globalConfig;
  }

}
