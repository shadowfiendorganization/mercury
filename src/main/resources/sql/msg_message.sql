/*
Navicat MySQL Data Transfer

Source Server         : localhost3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : mercury

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-06-08 20:04:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for msg_message
-- ----------------------------
DROP TABLE IF EXISTS `msg_message`;
CREATE TABLE `msg_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` text COMMENT '消息内容',
  `title` varchar(10) DEFAULT '' COMMENT '消息标题',
  `msg_send_time` datetime DEFAULT NULL COMMENT '消息发送时间',
  `msg_received_time` datetime DEFAULT NULL COMMENT '消息接收时间',
  `send_user_id` bigint(20) DEFAULT NULL COMMENT '发送方ID',
  `received_user_id` bigint(20) DEFAULT NULL COMMENT '接收方ID',
  `is_deleted` bigint(1) DEFAULT '0' COMMENT '是否删除，1为删除',
  `is_top` bigint(1) DEFAULT '0' COMMENT '是否置顶，1为置顶',
  `is_read` bigint(1) DEFAULT '0' COMMENT '是否已读，1为已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_message
-- ----------------------------
INSERT INTO `msg_message` VALUES ('1', '王二小您好，欢迎使用我们的博客系统！！！', '欢迎', '2017-06-08 18:06:22', '2017-06-08 18:06:35', '1', '2', '0', '0', '0');
INSERT INTO `msg_message` VALUES ('2', '尊敬的王二小您好，您的账户余额不足，请充值', '余额提示', '2017-06-08 18:09:22', '2017-06-08 19:59:58', '1', '2', '0', '0', '1');
INSERT INTO `msg_message` VALUES ('3', '尊敬的王二小，您好，现在是深夜，请您退出系统，注意休息！！！', '友情提示', '2017-06-08 18:10:57', '2017-06-08 18:10:59', '1', '2', '0', '0', '0');
