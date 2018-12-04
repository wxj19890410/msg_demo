/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50547
Source Host           : localhost:3305
Source Database       : wxj_test

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2018-11-06 10:49:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for org_department
-- ----------------------------
DROP TABLE IF EXISTS `org_department`;
CREATE TABLE `org_department` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  `order_nub` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `edit_date` datetime DEFAULT NULL,
  `del_flag` char(2) DEFAULT NULL,
  `pass_word` varchar(50) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
