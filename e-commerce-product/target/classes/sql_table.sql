drop table if exists `product_category`;
create table `product_category`
(
    `id`            bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品分类主键',
    `parent_id`     bigint(20)          not null default 0 comment '上级分类的编号 0 表示一级分类',
    `name`          varchar(64)         not null default '' comment '名称',
    `level`         int(1) comment '分类级别 0 -> 1级  1 -> 2级',
    `product_count` int(10)             not null default 0 comment '商品数量',
    `nav_status`    int(1)              not null default 1 comment '是否显示在导航栏 0 -> 不显示；1 -> 显示',
    `show_status`   int(1)              not null default 1 comment '显示状态 0 -> 不显示 1 -> 显示',
    `sort`          int(10)             not null default 0 comment '排序',
    `icon`          varchar(255) comment '图标',
    `keywords`      varchar(255) comment '关键字',
    `description`   text comment '描述',
    `created_at`    bigint(20) unsigned COMMENT '创建时间',
    `updated_at`    bigint(20) unsigned COMMENT '更新时间',
    primary key (`id`),
    index parent_id_index (`parent_id`)
) engine = innoDb
  AUTO_INCREMENT 3
  default charset = utf8 comment = '商品分类表';

drop table if exists `product_brand`;
create table `product_brand`
(
    `id`             bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '会员主键',
    `name`           varchar(64)         not null default '' comment '名称',
    `first_letter`   varchar(8) comment '首字母',
    `sort`           int(10)             not null default 0 comment '排序',
    `factory_status` int(1)              not null default 1 comment '是否为品牌制造商 0 -> 不显示；1 -> 显示',
    `show_status`    int(1)              not null default 1 comment '显示状态 0 -> 不显示 1 -> 显示',
    `product_count`  int(10)             not null default 0 comment '商品数量',
    `logo`           varchar(255) comment '图标',
    `keywords`       varchar(255) comment '关键字',
    `brand_story`    text comment '品牌故事',
    `created_at`     bigint(20) unsigned COMMENT '创建时间',
    `updated_at`     bigint(20) unsigned COMMENT '更新时间',
    primary key (`id`)
) engine = innoDb
  AUTO_INCREMENT 3
  default charset = utf8 comment = '商品品牌表';