/*
Navicat MySQL Data Transfer

Source Server         : localhost3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : mercury

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-06-06 16:45:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='系统菜单信息表';

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES ('1', '1', '001', '注册', '用户注册', null, '1', '1', '1', '1', '1');
INSERT INTO `admin_menu` VALUES ('2', '1', '002', '登录', '用户登录', null, '1', '1', '2', '1', '1');
INSERT INTO `admin_menu` VALUES ('3', '1', '003', '退出', '用户退出', null, '1', '1', '3', '1', '1');
INSERT INTO `admin_menu` VALUES ('11', '2', '0011', '修改信息', '用户修改个人信息', null, '1', '2', '1', '1', '1');
INSERT INTO `admin_menu` VALUES ('12', '2', '0012', '好友管理', '用户添加好友', null, '1', '2', '2', '1', '1');
INSERT INTO `admin_menu` VALUES ('13', '2', '0013', '我的相册', '相册', null, '1', '2', '3', '1', '1');
INSERT INTO `admin_menu` VALUES ('21', '3', '0021', '发表博客', '博客发布', null, '1', '3', '1', '1', '1');
INSERT INTO `admin_menu` VALUES ('22', '3', '0022', '博客管理', '删改查', null, '1', '3', '2', '1', '1');
INSERT INTO `admin_menu` VALUES ('23', '3', '0023', '上传文件', '文章上传', null, '1', '3', '3', '1', '1');
INSERT INTO `admin_menu` VALUES ('31', '4', '0031', '模块管理', '增删改模块', null, '1', '4', '1', '1', '1');
INSERT INTO `admin_menu` VALUES ('32', '1', '0032', '用户权限管理', '修改用户权限', null, '1', '4', '2', '1', '1');
