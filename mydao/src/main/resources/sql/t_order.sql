/*
Navicat MySQL Data Transfer

Source Server         : myjob
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : myjob

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-04-21 22:08:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` varchar(60) NOT NULL,
  `user_id` varchar(60) DEFAULT NULL,
  `state` int(2) DEFAULT '0' COMMENT '0:待处理：1：处理中 2:完成 99:异常',
  `remark` text,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `start_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `total_amount` varchar(20) DEFAULT NULL COMMENT '订单总金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '1', '0', '我要迅达', '2015-04-21 21:53:47', '2015-04-21 21:53:25', '2015-04-21 21:53:28', '100');
