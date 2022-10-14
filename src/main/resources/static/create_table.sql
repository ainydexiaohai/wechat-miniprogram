create table biz_user (
id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
`user_id` bigint(20) NOT NULL COMMENT '用户中心id',
`mobile` varchar(20) DEFAULT '' COMMENT '手机号',
`is_delete` tinyint(4) NOT NULL DEFAULT '0',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='业务用户表';

create table biz_user (
id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
`user_id` bigint(20) NOT NULL COMMENT '用户中心id',
`openid` varchar(64) NOT NULL COMMENT '微信openid',
`unionid` varchar(50) DEFAULT '' COMMENT '微信unionid',
`is_delete` tinyint(4) NOT NULL DEFAULT '0',
`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_openid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户与微信openid对应绑定关系表';