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

 Date: 18/02/2021 17:40:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_promotion_base_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_promotion_base_info`;
CREATE TABLE `tb_promotion_base_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '促销编码',
  `activity_type` varchar(32) DEFAULT NULL COMMENT '活动类型',
  `sales_start_time` datetime DEFAULT NULL COMMENT '促销开始时间',
  `sales_end_time` datetime DEFAULT NULL COMMENT '促销结束时间',
  `amount` int DEFAULT NULL COMMENT '每台限制张数',
  `bill_cycle` varchar(32) DEFAULT NULL COMMENT '回款周期',
  `description` varchar(32) DEFAULT NULL COMMENT '活动描述',
  `introduction` varchar(32) DEFAULT NULL COMMENT '七字描述',
  `channel` varchar(32) DEFAULT NULL COMMENT '团购网站',
  `the_way` varchar(32) DEFAULT NULL COMMENT '团购形式',
  `shared_activity` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '共享活动',
  `selling_price` decimal(10,2) DEFAULT NULL COMMENT '销售单价',
  `bill_price` decimal(10,2) DEFAULT NULL COMMENT '回款单价',
  `handling_fee` decimal(10,2) DEFAULT NULL COMMENT '手续费',
  `tax_rate` decimal(5,2) DEFAULT NULL COMMENT '税率',
  `other` varchar(32) DEFAULT NULL COMMENT '其他',
  `created_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `created_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  `updated_user` datetime DEFAULT NULL COMMENT '修改人',
  `updated_time` datetime DEFAULT NULL COMMENT '修改时间',
  `submit` varchar(32) DEFAULT NULL COMMENT '提交状态',
  `type` varchar(32) DEFAULT NULL COMMENT '单据类型',
  `usage_start_time` datetime DEFAULT NULL COMMENT '核销开始时间',
  `usage_end_time` datetime DEFAULT NULL COMMENT '核销结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='促销基本信息表 ';

SET FOREIGN_KEY_CHECKS = 1;
