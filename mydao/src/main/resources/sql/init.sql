/*
Navicat MySQL Data Transfer

Source Server         : myjob_tang
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : myjob

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-03-30 23:07:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_msg_message`
-- ----------------------------
DROP TABLE IF EXISTS `t_msg_message`;
CREATE TABLE `t_msg_message` (
  `id` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` text,
  `create_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_msg_message
-- ----------------------------
INSERT INTO `t_msg_message` VALUES ('1', '今日气价', '<p>12千克:95元</p><p>14千克:110元</p>', '2015-03-23 20:58:11');

-- ----------------------------
-- Table structure for `t_portal_product`
-- ----------------------------
DROP TABLE IF EXISTS `t_portal_product`;
CREATE TABLE `t_portal_product` (
  `id` int(20) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `describe` text,
  `pic_url` varchar(400) DEFAULT NULL,
  `price` varchar(10) DEFAULT NULL,
  `color` varchar(100) DEFAULT NULL,
  `style` varchar(100) DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_portal_product
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` varchar(64) NOT NULL,
  `user_name` varchar(128) NOT NULL,
  `user_pwd` varchar(128) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'tang', 'tang');
