/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : job

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 31/03/2019 22:01:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `industry` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行业',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司地址',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `scale` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `be_deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('402880eb69d3e8720169d3e968560001', 'data-Security', 'nanjin', 'unary', NULL, b'0');

-- ----------------------------
-- Table structure for employment
-- ----------------------------
DROP TABLE IF EXISTS `employment`;
CREATE TABLE `employment`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `entry_time` datetime(0) NULL DEFAULT NULL COMMENT '入职时间',
  `job_position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作岗位',
  `salary_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '薪资等级',
  `company_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `be_deleted` bit(1) NOT NULL,
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `student` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK3u9c49adog6ox1epg2pb76fn`(`company_id`) USING BTREE,
  INDEX `FKgchq0bqxu8gx55ttycifquiv6`(`student_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employment
-- ----------------------------
INSERT INTO `employment` VALUES ('402880eb69d409e80169d40a41b30001', '2019-03-23 00:00:00', 'dd', 'dd', NULL, NULL, b'0', NULL, NULL);
INSERT INTO `employment` VALUES ('402880eb69d409e80169d40be6ef0002', '2019-03-29 00:00:00', 'df', 'df', NULL, NULL, b'0', NULL, NULL);
INSERT INTO `employment` VALUES ('402880eb69d40cf40169d40de15c0001', '2019-03-29 00:00:00', 'bb', 'bb', NULL, NULL, b'0', 'bb', 'bb');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKpu4in3tmn3k7jpmm7mnx9ittk`(`create_user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('402880eb69d32fc20169d3303cd50000', '2019-03-31 17:59:08', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d3328f0169d332b0680000', '2019-03-31 18:01:48', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d3328f0169d332f28b0001', '2019-03-31 18:02:05', '127.0.0.1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d343f40169d34429890000', '2019-03-31 18:20:54', '127.0.0.1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d3839d0169d383afe20000', '2019-03-31 19:30:17', '127.0.0.1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d3839d0169d384f3b70001', '2019-03-31 19:31:40', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d3869c0169d386b9b30000', '2019-03-31 19:33:36', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d388d60169d38937720000', '2019-03-31 19:36:19', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d38bf50169d38c19ee0000', '2019-03-31 19:39:28', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d38cfc0169d38d13f80000', '2019-03-31 19:40:32', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d390bf0169d390edf90000', '2019-03-31 19:44:45', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d395920169d395a26a0000', '2019-03-31 19:49:53', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d39ed50169d39eef700000', '2019-03-31 20:00:02', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d3b2240169d3b23b270000', '2019-03-31 20:21:07', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d3b3380169d3b363760000', '2019-03-31 20:22:23', '127.0.0.1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d3b56a0169d3b57dca0000', '2019-03-31 20:24:41', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d3c10a0169d3c12aa40000', '2019-03-31 20:37:26', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d3d4930169d3d4abc10000', '2019-03-31 20:58:44', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d3d6dd0169d3d6fe910000', '2019-03-31 21:01:16', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d3e8720169d3e91d3f0000', '2019-03-31 21:21:04', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d3ee0b0169d3ee38810000', '2019-03-31 21:26:38', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d407130169d40779cc0000', '2019-03-31 21:54:14', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d409e80169d40a16260000', '2019-03-31 21:57:05', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');
INSERT INTO `log` VALUES ('402880eb69d40cf40169d40d2b530000', '2019-03-31 22:00:27', '0:0:0:0:0:0:0:1', '402880eb69d31b9b0169d31ba2c90000');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `admission_time` datetime(0) NULL DEFAULT NULL COMMENT '入学时间',
  `birthday` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `employment_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `be_deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK1lxshmivmhrmu58ti3qvo0s01`(`employment_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('402880eb69d38cfc0169d38d34140001', '2019-03-23 00:00:00', '2019-03-22 00:00:00', 'yyh', NULL, 'ddd', b'1');
INSERT INTO `student` VALUES ('402880eb69d3b56a0169d3b5bf190001', '2019-03-22 00:00:00', '2019-03-21 00:00:00', 'yyh', NULL, 'bbbbb', b'1');
INSERT INTO `student` VALUES ('402880eb69d3b56a0169d3b7c7da0002', '2019-03-21 00:00:00', '2019-03-20 00:00:00', 'bbb', NULL, 'bbbbb', b'0');
INSERT INTO `student` VALUES ('402880eb69d3b56a0169d3b8a4250003', '2019-03-30 00:00:00', '2019-03-29 00:00:00', 'mmm', NULL, 'mmm', b'0');
INSERT INTO `student` VALUES ('402880eb69d3c10a0169d3c162040001', '2019-03-29 00:00:00', '2019-03-29 00:00:00', 'ddd', NULL, 'fff', b'0');
INSERT INTO `student` VALUES ('402880eb69d3c10a0169d3c32e7e0002', '2019-03-25 00:00:00', '2019-03-25 00:00:00', 'fdsaf', NULL, 'adsfas', b'0');
INSERT INTO `student` VALUES ('402880eb69d3d4930169d3d4e2cf0001', '2019-03-23 00:00:00', '2019-03-23 00:00:00', 'hu', NULL, 'wefef', b'0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `be_deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_gj2fy3dcix7ph7k8684gka40c`(`name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('402880eb69d31b9b0169d31ba2c90000', '2019-03-31 17:36:38', NULL, 'admin', '$2a$10$NizW0oGcZsuqbP46QxILFOLPRC/zXsT2XEkgcYuC4Z8oUqCk/ZxI6', 'ADMIN', b'0');

SET FOREIGN_KEY_CHECKS = 1;
