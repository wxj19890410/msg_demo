/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50547
Source Host           : localhost:3305
Source Database       : wxj_test

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2018-12-04 12:20:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `code_uuid` varchar(50) DEFAULT NULL,
  `autograph` varchar(50) DEFAULT NULL COMMENT '签名',
  `amount` decimal(11,2) DEFAULT NULL,
  `msg_flag` varchar(2) DEFAULT NULL COMMENT '是否能发短信',
  `del_flag` varchar(2) DEFAULT NULL COMMENT '是否能发短信',
  `create_date` datetime DEFAULT NULL COMMENT '是否能发短信',
  `edit_date` datetime DEFAULT NULL COMMENT '是否能发短信',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for msg_history
-- ----------------------------
DROP TABLE IF EXISTS `msg_history`;
CREATE TABLE `msg_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT NULL,
  `content` text CHARACTER SET utf8,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for recharge_history
-- ----------------------------
DROP TABLE IF EXISTS `recharge_history`;
CREATE TABLE `recharge_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT 'chongzhi 渠道',
  `del_flag` varchar(2) CHARACTER SET utf8 DEFAULT NULL COMMENT '是否能发短信',
  `create_date` datetime DEFAULT NULL COMMENT '日期',
  `change_mode` varchar(10) CHARACTER SET utf8 DEFAULT NULL COMMENT '0 增加 2 减少',
  `remark` text CHARACTER SET utf8 COMMENT '备注',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user_data
-- ----------------------------
DROP TABLE IF EXISTS `user_data`;
CREATE TABLE `user_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pass_word` varchar(50) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `pass_word2` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=741 DEFAULT CHARSET=utf8;
