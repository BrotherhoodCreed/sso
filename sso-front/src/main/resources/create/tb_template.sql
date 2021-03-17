/*
Navicat MySQL Data Transfer

Source Server         : qpl_work
Source Server Version : 50722
Source Host           : 172.16.20.168:3306
Source Database       : tuhu_qpl_enquiry

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2021-03-09 14:21:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_template
-- ----------------------------
DROP TABLE IF EXISTS `tb_template`;
CREATE TABLE `tb_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `remarks` varchar(255) DEFAULT NULL,
  `version_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_template
-- ----------------------------
INSERT INTO `tb_template` VALUES ('1', 'fd_3860f0c3f7d27e', '', '0', '活动范围', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('2', 'fd_385f8f8888a724', null, '0', '申请日期', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('3', 'fd_385f8fa3935a16', null, '0', '申请人', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('4', 'fd_3860f0a056e866', null, '0', '所属部门或区域', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('5', 'fd_3860f0ec6221e4', null, '0', '活动涉及区域', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('6', 'fd_3860f11f0e66b2', null, '0', '活动类型（线上（含CRM）|1 线上（不含CRM）|2 线下（含CRM）|3 线下（不含CRM）|4）', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('7', 'fd_38de340d0a4de2', null, '0', '合作渠道（仅商场活动|1仅银行活动|2仅新美大活动|3异业合作|4商场活动（含新美大活动）|5商场活动（含口碑活动）|6商场活动（含银行活动）|7第三方平台|8仅口碑|9其他|10）', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('8', 'fd_38de33e7bda542', null, '0', '关联区域营销合同审批流程', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('9', 'fd_3860f135b97eec', null, '0', '是否商场强制（是|1\r\n否|2）', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('10', 'fd_3860f151bc56c8', null, '0', '上传商场活动函或图片', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('11', 'fd_3860f15e3bb342', null, '0', '是否外卖活动(1是 2否)', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('12', 'fd_3860f16b50cb7e', null, '0', '营销活动紧急度（一般，加急）', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('13', 'fd_387c91d4168810', null, '0', '申请类型（新增、变更）', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('14', 'fd_38d0906e39c1a6', null, '0', '活动主题', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('15', 'fd_387c91fc9cde80', null, '0', '关联营销活动申请流程', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('16', 'fd_3860f584123510', null, '0', '上传活动对接明细表', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('17', 'fd_3860f58efc028c', null, '0', '上传促销活动影响测算表', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('18', 'fd_3860f1dcd7575c', null, '0', '活动（变更内容）说明', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('19', 'fd_3860f1e3be2472', null, '0', '促销编码及对应活动名称', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('20', 'fd_3860f1ee6933f0', null, '0', '上传促销编码表（Excel）', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('21', 'fd_3860f1ee6933f0', null, '0', '上传促销编码表（Excel）', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('22', 'fd_3860f21481f73c', null, '0', '确认外卖网站产品已建立', '177f1dc707d68acbb117e1c4364b253b');
INSERT INTO `tb_template` VALUES ('24', 'fd_3860f21271bcf8', null, '0', '确认系统对接完毕（1、是 2、否）', '177f1dc707d68acbb117e1c4364b253b');
