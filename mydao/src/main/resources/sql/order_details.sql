/*
Navicat MySQL Data Transfer

Source Server         : myjob
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : myjob

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-04-21 22:41:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `order_details`
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `id` varchar(60) NOT NULL,
  `order_id` varchar(60) NOT NULL,
  `product_id` varchar(60) NOT NULL,
  `product_price` varchar(20) DEFAULT NULL,
  `product_real_price` varchar(20) DEFAULT NULL,
  `product_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_details
-- ----------------------------
INSERT INTO `order_details` VALUES ('1', '1', '1', '100', '80', '迅达燃气灶');
