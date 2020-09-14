/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : lottery

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 14/09/2020 21:07:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `tel` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电话',
  `sort` int(11) NULL DEFAULT NULL COMMENT '序号',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeKey` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `periodNo` int(11) NULL DEFAULT NULL,
  `pubDate` datetime(0) NULL DEFAULT NULL,
  `res` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1101 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for worksheet
-- ----------------------------
DROP TABLE IF EXISTS `workSheet`;
CREATE TABLE `workSheet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NULL DEFAULT NULL COMMENT '人员id',
  `workDate` date NULL DEFAULT NULL COMMENT '日期',
  `workStatus` int(11) NULL DEFAULT NULL COMMENT '工作状态：全勤1、未到0、其他比例',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sheet_unique`(`mid`, `workDate`) USING BTREE COMMENT '用户id+日期必须唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for worktask
-- ----------------------------
DROP TABLE IF EXISTS `workTask`;
CREATE TABLE `workTask`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `workType` int(11) NULL DEFAULT NULL COMMENT '工作类型：白班、夜班',
  `workDate` date NULL DEFAULT NULL COMMENT '工作日期',
  `workItem` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工作项',
  `unitPrice` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `quantity` decimal(10, 2) NULL DEFAULT NULL COMMENT '数量',
  `memo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `task_unique`(`workDate`, `workItem`) USING BTREE COMMENT '工作项+时间必须唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
