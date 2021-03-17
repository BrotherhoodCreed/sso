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

 Date: 19/02/2021 18:15:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `tb_dictionary`;
CREATE TABLE `tb_dictionary` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(64) NOT NULL,
  `description_code` varchar(64) NOT NULL,
  `description_type` varchar(64) NOT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 ;

-- ----------------------------
-- Records of tb_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `tb_dictionary` VALUES (1, '套餐', 'SET_MEAL', 'activity_type', 0);
INSERT INTO `tb_dictionary` VALUES (2, '优惠卷', 'COUPON', 'activity_type', 0);
INSERT INTO `tb_dictionary` VALUES (3, '美团', 'MEITUAN_DIANPING_TAKEOUT', 'channel', 0);
INSERT INTO `tb_dictionary` VALUES (4, '饿了么', 'ELEME_TAKEOUT', 'channel', 0);
INSERT INTO `tb_dictionary` VALUES (5, '区域1', '01', 'promotion_area', 0);
INSERT INTO `tb_dictionary` VALUES (6, '区域2', '02', 'promotion_area', 0);
INSERT INTO `tb_dictionary` VALUES (7, '区域3', '03', 'promotion_area', 0);
INSERT INTO `tb_dictionary` VALUES (8, '区域4', '04', 'promotion_area', 0);
INSERT INTO `tb_dictionary` VALUES (9, '区域5', '05', 'promotion_area', 0);
INSERT INTO `tb_dictionary` VALUES (10, '区域6', '06', 'promotion_area', 0);
INSERT INTO `tb_dictionary` VALUES (11, '堂食', '0', 'promotion_type', 0);
INSERT INTO `tb_dictionary` VALUES (12, '外卖', '1', 'promotion_type', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
