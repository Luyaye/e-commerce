DROP TABLE IF EXISTS `member_user`;
CREATE TABLE `member_user`
(
    `id`         BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户信息主键',
    `username`   VARCHAR(64)         NOT NULL DEFAULT '' COMMENT '会员名称',
    `phone`      VARCHAR(64)         NOT NULL DEFAULT '' COMMENT '手机号',
    `password`   VARCHAR(128)        NOT NULL DEFAULT '' COMMENT '密码',
    `sex`        INT(2)              NOT NULL DEFAULT 0 COMMENT '性别 0 男 1 女',
    `email`      VARCHAR(64) COMMENT '邮箱',
    `deleted`    TINYINT(1)          NOT NULL DEFAULT 0 COMMENT '是否删除',
    `created_at` BIGINT(20) UNSIGNED COMMENT '创建时间',
    `updated_at` BIGINT(20) UNSIGNED COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX idx_email (`email`)
) ENGINE = INNODB
  AUTO_INCREMENT 3
  DEFAULT charset = utf8 COMMENT = '用户信息表';