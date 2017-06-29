/*
Navicat MySQL Data Transfer

Source Server         : 新开发环境数据库
Source Server Version : 50620
Source Host           : 10.0.6.54:3306
Source Database       : image

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-06-23 13:47:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbl_image`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_image`;
CREATE TABLE `tbl_image` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '图片名称',
  `md5name` varchar(32) DEFAULT NULL COMMENT '图片MD5值',
  `path` varchar(200) NOT NULL COMMENT '图片路径',
  `is_historical_data` tinyint(1) NOT NULL COMMENT '是否是历史数据标识（1：历史数据  0：新数据）',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `extension` varchar(200) DEFAULT NULL COMMENT '扩展信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


