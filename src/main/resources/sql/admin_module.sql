/*
Navicat MySQL Data Transfer

Source Server         : localhost3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : mercury

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-06-06 16:45:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_module
-- ----------------------------
DROP TABLE IF EXISTS `admin_module`;
CREATE TABLE `admin_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '模块名称',
  `remark` varchar(100) DEFAULT '' COMMENT '备注',
  `sort_id` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统模块信息表';

-- ----------------------------
-- Records of admin_module
-- ----------------------------
INSERT INTO `admin_module` VALUES ('1', '首页', '系统首页', '1', '2017-06-06 16:18:52');
INSERT INTO `admin_module` VALUES ('2', '个人空间', '个人信息管理', '2', '2017-06-06 16:19:26');
INSERT INTO `admin_module` VALUES ('3', '我的博客', '个人博客管理', '3', '2017-06-06 16:19:56');
INSERT INTO `admin_module` VALUES ('4', '系统管理', '管理员模块', '4', '2017-06-06 16:21:15');
