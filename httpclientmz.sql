/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50147
Source Host           : localhost:3306
Source Database       : httpclientmz

Target Server Type    : MYSQL
Target Server Version : 50147
File Encoding         : 65001

Date: 2019-07-10 10:23:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mz_ipandport
-- ----------------------------
DROP TABLE IF EXISTS `mz_ipandport`;
CREATE TABLE `mz_ipandport` (
  `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ip的id',
  `ipName` int(20) DEFAULT NULL COMMENT 'ip名称',
  `ipPort` int(10) DEFAULT NULL COMMENT 'ip端口',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
