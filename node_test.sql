/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : node_test

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 15/12/2021 09:28:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for node
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父节点ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of node
-- ----------------------------
INSERT INTO `node` VALUES (1, 0, 'A');
INSERT INTO `node` VALUES (2, 1, 'B');
INSERT INTO `node` VALUES (3, 2, 'C');
INSERT INTO `node` VALUES (4, 3, 'D');
INSERT INTO `node` VALUES (5, 4, 'E');
INSERT INTO `node` VALUES (6, 2, 'F');
INSERT INTO `node` VALUES (7, 0, 'G');
INSERT INTO `node` VALUES (8, 7, 'H');

SET FOREIGN_KEY_CHECKS = 1;
