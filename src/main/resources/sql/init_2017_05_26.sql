CREATE TABLE `admin_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '模块名称',
  `remark` varchar(100) DEFAULT '' COMMENT '备注',
  `sort_id` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT'创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='系统模块信息表';

CREATE TABLE `admin_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module_id` int(11) DEFAULT NULL COMMENT '子系统',
  `code` varchar(10) DEFAULT '' COMMENT '菜单编号，唯一键',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '名字',
  `remark` varchar(200) DEFAULT '' COMMENT '菜单描述',
  `url` text COMMENT 'url',
  `level` smallint(6) DEFAULT NULL COMMENT '级别',
  `parent_code` varchar(10) DEFAULT '' COMMENT '父编号',
  `sort_id` int(11) DEFAULT NULL COMMENT '排序',
  `is_valid` tinyint(3) unsigned NOT NULL COMMENT '是否有效',
  `is_visible` tinyint(3) unsigned DEFAULT NULL COMMENT '是否可见',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_code` (`code`) USING BTREE,
  KEY `index_module_id` (`module_id`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='系统菜单信息表';
