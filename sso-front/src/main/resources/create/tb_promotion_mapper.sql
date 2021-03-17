/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 18/02/2021 17:40:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_promotion_mapper
-- ----------------------------
DROP TABLE IF EXISTS `tb_promotion_mapper`;
CREATE TABLE `tb_promotion_mapper` (
  `id` bigint NOT NULL,
  `area` varchar(64) NOT NULL,
  `city` varchar(64) NOT NULL,
  `restaurant_code` varchar(64) NOT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `activity_code` varchar(64) DEFAULT NULL,
  `restaurant_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

SET FOREIGN_KEY_CHECKS = 1;
