/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 116.62.218.244:3306
 Source Schema         : brccq

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 10/01/2022 15:33:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_num` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`admin_num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('root', '219a95ead610e9b4e05376e0ff1aafbb00b549302cda298acaecb88dbaf405bd');

-- ----------------------------
-- Table structure for allocated_com
-- ----------------------------
DROP TABLE IF EXISTS `allocated_com`;
CREATE TABLE `allocated_com`  (
  `com_num` int(11) NOT NULL,
  `work_order_num` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cpu_core` int(11) NULL DEFAULT NULL,
  `ram` int(11) NULL DEFAULT NULL,
  `storage` int(11) NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  PRIMARY KEY (`com_num`, `work_order_num`) USING BTREE,
  INDEX `work_order_num`(`work_order_num`) USING BTREE,
  CONSTRAINT `allocated_com_ibfk_1` FOREIGN KEY (`work_order_num`) REFERENCES `work_order` (`work_order_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of allocated_com
-- ----------------------------

-- ----------------------------
-- Table structure for allocated_vm
-- ----------------------------
DROP TABLE IF EXISTS `allocated_vm`;
CREATE TABLE `allocated_vm`  (
  `com_num` int(11) NOT NULL,
  `work_order_num` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cpu_core` int(11) NULL DEFAULT NULL,
  `ram` int(11) NULL DEFAULT NULL,
  `storage` int(11) NULL DEFAULT NULL,
  `ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `os` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  PRIMARY KEY (`com_num`, `work_order_num`) USING BTREE,
  INDEX `allocated_vm_work_order_work_order_num_fk`(`work_order_num`) USING BTREE,
  CONSTRAINT `allocated_vm_work_order_work_order_num_fk` FOREIGN KEY (`work_order_num`) REFERENCES `work_order` (`work_order_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of allocated_vm
-- ----------------------------

-- ----------------------------
-- Table structure for dep_relate
-- ----------------------------
DROP TABLE IF EXISTS `dep_relate`;
CREATE TABLE `dep_relate`  (
  `dep_num_superior` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dep_num_junior` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`dep_num_superior`, `dep_num_junior`) USING BTREE,
  INDEX `dep_num_junior`(`dep_num_junior`) USING BTREE,
  CONSTRAINT `dep_relate_ibfk_1` FOREIGN KEY (`dep_num_superior`) REFERENCES `department` (`dep_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `dep_relate_ibfk_2` FOREIGN KEY (`dep_num_junior`) REFERENCES `department` (`dep_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dep_relate
-- ----------------------------
INSERT INTO `dep_relate` VALUES ('0001', '0002');
INSERT INTO `dep_relate` VALUES ('0002', '0003');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `dep_num` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dep_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dep_level` int(11) NULL DEFAULT NULL,
  `dep_budget` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`dep_num`) USING BTREE,
  INDEX `dep_num`(`dep_num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('0001', '软件学院', 3, 0);
INSERT INTO `department` VALUES ('0002', '软件19-2班', 2, 0);
INSERT INTO `department` VALUES ('0003', '小组1', 1, 0);

-- ----------------------------
-- Table structure for flow_process
-- ----------------------------
DROP TABLE IF EXISTS `flow_process`;
CREATE TABLE `flow_process`  (
  `work_order_num` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deal_num` int(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `operation_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deal_date` datetime NOT NULL,
  `deal_comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`work_order_num`, `deal_num`, `deal_date`) USING BTREE,
  INDEX `deal_num`(`deal_num`) USING BTREE,
  CONSTRAINT `flow_process_ibfk_1` FOREIGN KEY (`work_order_num`) REFERENCES `work_order` (`work_order_num`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `flow_process_ibfk_2` FOREIGN KEY (`deal_num`) REFERENCES `staff` (`work_num`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 20220013 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flow_process
-- ----------------------------
INSERT INTO `flow_process` VALUES ('202201081138000001', 20220003, '申请', '2022-01-08 11:48:15', '提出申请');
INSERT INTO `flow_process` VALUES ('202201081138000002', 20220001, '审批通过', '2022-01-08 11:48:23', '资源申请合理');
INSERT INTO `flow_process` VALUES ('202201081138000002', 20220004, '申请', '2022-01-08 11:48:15', '提出申请');
INSERT INTO `flow_process` VALUES ('202201081148000003', 20220001, '审批不通过', '2022-01-08 11:50:05', '资源申请不合理');
INSERT INTO `flow_process` VALUES ('202201081148000003', 20220011, '申请', '2022-01-08 11:48:05', '提出申请');
INSERT INTO `flow_process` VALUES ('202201081534000048', 20220001, '审批通过', '2022-01-08 11:51:50', '资源申请合理');
INSERT INTO `flow_process` VALUES ('202201081534000048', 20220012, '申请', '2022-01-07 11:51:50', '提出申请');
INSERT INTO `flow_process` VALUES ('202201081634004848', 20220012, '申请', '2022-01-08 04:42:03', '提出申请');

-- ----------------------------
-- Table structure for his_resource_usage
-- ----------------------------
DROP TABLE IF EXISTS `his_resource_usage`;
CREATE TABLE `his_resource_usage`  (
  `work_order_num` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `res_utilization` double NULL DEFAULT NULL,
  PRIMARY KEY (`work_order_num`) USING BTREE,
  CONSTRAINT `his_resource_usage_ibfk_1` FOREIGN KEY (`work_order_num`) REFERENCES `work_order` (`work_order_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_resource_usage
-- ----------------------------
INSERT INTO `his_resource_usage` VALUES ('202201081534000048', 0.8);

-- ----------------------------
-- Table structure for inform
-- ----------------------------
DROP TABLE IF EXISTS `inform`;
CREATE TABLE `inform`  (
  `inform_num` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sender_num` int(8) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `work_order_num` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recipient` int(8) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `send_time` datetime NULL DEFAULT NULL,
  `read_state` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`inform_num`) USING BTREE,
  INDEX `inform_ibfk_3`(`work_order_num`) USING BTREE,
  INDEX `inform_ibfk_1`(`sender_num`) USING BTREE,
  INDEX `inform_ibfk_2`(`recipient`) USING BTREE,
  CONSTRAINT `inform_ibfk_1` FOREIGN KEY (`sender_num`) REFERENCES `staff` (`work_num`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inform_ibfk_2` FOREIGN KEY (`recipient`) REFERENCES `staff` (`work_num`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inform_ibfk_3` FOREIGN KEY (`work_order_num`) REFERENCES `work_order` (`work_order_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inform
-- ----------------------------

-- ----------------------------
-- Table structure for leadership
-- ----------------------------
DROP TABLE IF EXISTS `leadership`;
CREATE TABLE `leadership`  (
  `leder_num` int(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `under_num` int(8) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`leder_num`, `under_num`) USING BTREE,
  INDEX `under_num`(`under_num`) USING BTREE,
  CONSTRAINT `leadership_ibfk_1` FOREIGN KEY (`leder_num`) REFERENCES `staff` (`work_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 20220015 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leadership
-- ----------------------------
INSERT INTO `leadership` VALUES (20220013, 20220001);
INSERT INTO `leadership` VALUES (20220013, 20220002);
INSERT INTO `leadership` VALUES (20220001, 20220003);
INSERT INTO `leadership` VALUES (20220002, 20220003);
INSERT INTO `leadership` VALUES (20220001, 20220004);
INSERT INTO `leadership` VALUES (20220001, 20220011);
INSERT INTO `leadership` VALUES (20220001, 20220012);
INSERT INTO `leadership` VALUES (20220001, 20220013);

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log`  (
  `work_num` int(8) UNSIGNED ZEROFILL NOT NULL,
  `operate_time` datetime NOT NULL,
  `operate` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`work_num`, `operate_time`) USING BTREE,
  CONSTRAINT `operation_log_ibfk_1` FOREIGN KEY (`work_num`) REFERENCES `staff` (`work_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operation_log
-- ----------------------------
INSERT INTO `operation_log` VALUES (20220001, '2022-01-03 11:39:13', '登录', '117.136.117.15', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-03 11:45:43', '登录', '58.243.254.154', '安徽省合肥市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-04 15:15:15', '登录', '58.243.250.87', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-04 15:22:01', '登录', '58.243.250.87', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-04 15:22:12', '登录', '58.243.250.87', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-04 15:36:13', '登录', '58.243.250.87', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-04 18:56:55', '登录', '58.243.250.87', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-05 20:02:10', '登录', '58.243.250.169', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 14:18:29', '登录', '58.243.254.26', '安徽省合肥市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 14:18:55', '登录', '58.243.254.26', '安徽省合肥市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 14:34:47', '登录', '58.243.254.26', '安徽省合肥市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 14:36:12', '登录', '58.243.254.26', '安徽省合肥市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 19:39:42', '登录', '39.144.35.44', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 19:43:07', '登录', '39.144.35.44', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 19:43:30', '登录', '39.144.35.44', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 19:45:23', '登录', '39.144.35.44', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 19:48:03', '登录', '39.144.35.44', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 20:07:05', '登录', '58.243.254.125', '安徽省合肥市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 20:07:16', '登录', '58.243.254.125', '安徽省合肥市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 20:07:42', '登录', '58.243.254.125', '安徽省合肥市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 20:07:43', '登录', '58.243.254.125', '安徽省合肥市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 20:07:44', '登录', '58.243.254.125', '安徽省合肥市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 21:56:58', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 21:57:03', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 21:58:36', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 21:59:14', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 22:09:39', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 22:09:50', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 22:10:08', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 22:10:38', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 22:12:05', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 22:12:35', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-06 22:16:16', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 09:32:18', '登录', '58.243.250.36', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 09:32:25', '登录', '58.243.250.36', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 09:32:32', '登录', '58.243.250.36', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 09:32:39', '登录', '58.243.250.36', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 09:33:19', '登录', '58.243.250.36', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 09:33:33', '登录', '58.243.250.36', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 09:36:04', '登录', '58.243.250.36', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 09:36:24', '登录', '58.243.250.36', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 11:16:56', '登录', '39.144.35.182', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 11:20:48', '登录', '39.144.35.182', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 11:20:56', '登录', '39.144.35.182', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 11:30:06', '登录', '39.144.35.182', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 15:39:13', '登录', '39.144.35.182', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 15:39:34', '登录', '58.243.254.121', '安徽省合肥市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 15:39:39', '登录', '58.243.254.121', '安徽省合肥市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 19:59:29', '登录', '39.144.35.182', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-07 19:59:36', '登录', '39.144.35.182', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-08 10:00:21', '登录', '58.243.250.58', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-09 20:09:28', '登录', '58.243.250.44', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-10 15:29:11', '登录', '117.136.103.132', 'CHINA');
INSERT INTO `operation_log` VALUES (20220001, '2022-01-10 15:29:23', '登录', '117.136.103.132', 'CHINA');
INSERT INTO `operation_log` VALUES (20220002, '2022-01-03 11:42:51', '登录', '117.136.117.15', 'CHINA');
INSERT INTO `operation_log` VALUES (20220002, '2022-01-03 11:45:01', '登录', '58.243.250.87', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220002, '2022-01-03 11:47:32', '登录', '117.136.101.34', 'CHINA');
INSERT INTO `operation_log` VALUES (20220002, '2022-01-06 21:57:11', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220002, '2022-01-06 21:57:50', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220002, '2022-01-06 21:58:00', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220002, '2022-01-06 21:58:18', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220002, '2022-01-06 21:59:08', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220002, '2022-01-06 22:00:12', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220002, '2022-01-06 22:09:30', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220003, '2022-01-03 11:48:32', '登录', '39.144.34.201', 'CHINA');
INSERT INTO `operation_log` VALUES (20220003, '2022-01-06 21:59:41', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220003, '2022-01-06 21:59:58', '登录', '36.5.200.36', '安徽省');
INSERT INTO `operation_log` VALUES (20220003, '2022-01-07 11:22:39', '登录', '39.144.35.182', 'CHINA');
INSERT INTO `operation_log` VALUES (20220003, '2022-01-08 16:33:26', '登录', '58.243.250.49', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220003, '2022-01-09 19:52:36', '登录', '58.243.250.44', '安徽省阜阳市');
INSERT INTO `operation_log` VALUES (20220003, '2022-01-10 09:44:11', '登录', '58.243.250.9', '安徽省阜阳市');

-- ----------------------------
-- Table structure for physics_com_resource
-- ----------------------------
DROP TABLE IF EXISTS `physics_com_resource`;
CREATE TABLE `physics_com_resource`  (
  `com_num` int(11) NOT NULL AUTO_INCREMENT,
  `cpu_core` int(11) NULL DEFAULT NULL,
  `ram` int(11) NULL DEFAULT NULL,
  `storage` int(11) NULL DEFAULT NULL,
  `assign` bit(1) NULL DEFAULT NULL,
  `price` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`com_num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of physics_com_resource
-- ----------------------------
INSERT INTO `physics_com_resource` VALUES (1, 8, 8, 512, b'0', 2000);
INSERT INTO `physics_com_resource` VALUES (2, 16, 16, 256, b'1', 4000);
INSERT INTO `physics_com_resource` VALUES (3, 8, 32, 2048, b'1', 3000);
INSERT INTO `physics_com_resource` VALUES (4, 16, 32, 1024, b'1', 6000);
INSERT INTO `physics_com_resource` VALUES (5, 32, 64, 1024, b'1', 8000);
INSERT INTO `physics_com_resource` VALUES (6, 4, 16, 128, b'1', 1500);
INSERT INTO `physics_com_resource` VALUES (7, 32, 8, 256, b'1', 5000);
INSERT INTO `physics_com_resource` VALUES (8, 64, 16, 1024, b'1', 7000);
INSERT INTO `physics_com_resource` VALUES (9, 128, 64, 2048, b'1', 10000);

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `work_num` int(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dep_num` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` bit(1) NULL DEFAULT b'1',
  `in_service` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`work_num`) USING BTREE,
  INDEX `FK_depNum`(`dep_num`) USING BTREE,
  CONSTRAINT `FK_depNum` FOREIGN KEY (`dep_num`) REFERENCES `department` (`dep_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 20220018 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (20220001, '梁云', '0002', '13011112222', '219a95ead610e9b4e05376e0ff1aafbb00b549302cda298acaecb88dbaf405bd', b'1', b'1');
INSERT INTO `staff` VALUES (20220002, '林望', '0002', '13011113333', '219a95ead610e9b4e05376e0ff1aafbb00b549302cda298acaecb88dbaf405bd', b'1', b'1');
INSERT INTO `staff` VALUES (20220003, '付守宇', '0001', '13011114444', '219a95ead610e9b4e05376e0ff1aafbb00b549302cda298acaecb88dbaf405bd', b'1', b'1');
INSERT INTO `staff` VALUES (20220004, '付泓菁', '0001', '13011115555', '219a95ead610e9b4e05376e0ff1aafbb00b549302cda298acaecb88dbaf405bd', b'1', b'1');
INSERT INTO `staff` VALUES (20220011, '朱鹏飞', '0001', '10203954444', '219a95ead610e9b4e05376e0ff1aafbb00b549302cda298acaecb88dbaf405bd', b'1', b'1');
INSERT INTO `staff` VALUES (20220012, '陈海涛', '0001', '10203955555', '219a95ead610e9b4e05376e0ff1aafbb00b549302cda298acaecb88dbaf405bd', b'1', b'1');
INSERT INTO `staff` VALUES (20220013, '邢松', '0003', '10203956666', '219a95ead610e9b4e05376e0ff1aafbb00b549302cda298acaecb88dbaf405bd', b'1', b'1');

-- ----------------------------
-- Table structure for virtual_com_resource
-- ----------------------------
DROP TABLE IF EXISTS `virtual_com_resource`;
CREATE TABLE `virtual_com_resource`  (
  `cpu_core` int(11) NULL DEFAULT NULL,
  `ram` int(11) NULL DEFAULT NULL,
  `storage` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of virtual_com_resource
-- ----------------------------
INSERT INTO `virtual_com_resource` VALUES (256, 1024, 65535);

-- ----------------------------
-- Table structure for vm_specifications
-- ----------------------------
DROP TABLE IF EXISTS `vm_specifications`;
CREATE TABLE `vm_specifications`  (
  `com_num` int(11) NULL DEFAULT NULL,
  `cpu_core` int(11) NULL DEFAULT NULL,
  `ram` int(11) NULL DEFAULT NULL,
  `processor_model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `processor_frequency` double NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `disk_price` double NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vm_specifications
-- ----------------------------
INSERT INTO `vm_specifications` VALUES (1, 1, 1, 'Intel(R) Xeon(R) Platinum 8269CY', 2.5, 33, 0.5, '共享标准型 s6');
INSERT INTO `vm_specifications` VALUES (2, 1, 2, 'Intel(R) Xeon(R) Platinum 8269CY', 2.5, 60, 0.5, '共享标准型 s6');
INSERT INTO `vm_specifications` VALUES (3, 1, 4, 'Intel(R) Xeon(R) Platinum 8269CY', 2.5, 90, 0.5, '共享标准型 s6');
INSERT INTO `vm_specifications` VALUES (4, 2, 4, 'Intel(R) Xeon(R) Platinum 8269CY', 2.5, 120, 0.5, '共享标准型 s6');
INSERT INTO `vm_specifications` VALUES (5, 2, 8, 'Intel(R) Xeon(R) Platinum 8269CY', 2.5, 180, 0.5, '共享标准型 s6');
INSERT INTO `vm_specifications` VALUES (6, 4, 8, 'Intel(R) Xeon(R) Platinum 8269CY', 2.5, 240, 0.5, '共享标准型 s6');
INSERT INTO `vm_specifications` VALUES (7, 4, 16, 'Intel(R) Xeon(R) Platinum 8269CY', 2.5, 360, 0.5, '共享标准型 s6');
INSERT INTO `vm_specifications` VALUES (8, 8, 16, 'Intel(R) Xeon(R) Platinum 8269CY', 2.5, 480, 0.5, '共享标准型 s6');
INSERT INTO `vm_specifications` VALUES (9, 8, 32, 'Intel(R) Xeon(R) Platinum 8269CY', 2.5, 720, 0.5, '共享标准型 s6');

-- ----------------------------
-- Table structure for work_order
-- ----------------------------
DROP TABLE IF EXISTS `work_order`;
CREATE TABLE `work_order`  (
  `work_order_num` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `work_order_type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `work_order_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expiration_time` datetime NULL DEFAULT NULL,
  `worker_num` int(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `work_order_state` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  PRIMARY KEY (`work_order_num`) USING BTREE,
  INDEX `fk_workNum`(`worker_num`) USING BTREE,
  CONSTRAINT `fk_workNum` FOREIGN KEY (`worker_num`) REFERENCES `staff` (`work_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 20220028 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_order
-- ----------------------------
INSERT INTO `work_order` VALUES ('202201081138000001', '申请工单', '1班小班辅导资源申请', '2022-02-08 11:48:15', 20220003, NULL, '待审批', '小班辅导需要申请资源', NULL);
INSERT INTO `work_order` VALUES ('202201081138000002', '申请工单', '1班主题班会资源申请', '2022-02-08 11:48:23', 20220004, NULL, '一级审批通过', '主题班会需要申请资源', NULL);
INSERT INTO `work_order` VALUES ('202201081148000003', '申请工单', '班级团建', '2022-02-08 11:50:05', 20220011, NULL, '审批未通过', '班级团建申请资源', NULL);
INSERT INTO `work_order` VALUES ('202201081148000004', '申请工单', '增加虚拟机', '2022-02-08 16:32:58', 20220003, NULL, '待审批', '资源不够用', NULL);
INSERT INTO `work_order` VALUES ('202201081148000005', '申请工单', '工大演唱会', '2022-02-08 16:33:18', 20220003, NULL, '审批通过', NULL, NULL);
INSERT INTO `work_order` VALUES ('202201081148000006', '回退工单', NULL, '2022-02-08 16:33:39', 20220003, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES ('202201081148000007', '回退工单', NULL, '2022-02-08 16:33:50', 20220003, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES ('202201081148000008', '申请工单', NULL, '2022-02-08 16:34:10', 20220004, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES ('202201081148000009', '申请工单', NULL, '2022-02-08 16:34:23', 20220004, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES ('202201081148000010', '申请工单', NULL, '2022-02-08 16:34:35', 20220004, NULL, NULL, NULL, NULL);
INSERT INTO `work_order` VALUES ('202201081534000048', '申请工单', '班级活动', '2022-02-08 11:51:50', 20220012, NULL, '审批通过', '班级活动申请资源', NULL);
INSERT INTO `work_order` VALUES ('202201081634004848', '申请工单', '发票项目', '2022-02-08 14:42:03', 20220012, NULL, '自动挂起', '申请资源', NULL);
INSERT INTO `work_order` VALUES ('202201081634004889', '申请工单', '工单项目', '2022-02-08 14:40:53', 20220011, NULL, '手动挂起', '申请资源', NULL);

-- ----------------------------
-- Table structure for work_order_change
-- ----------------------------
DROP TABLE IF EXISTS `work_order_change`;
CREATE TABLE `work_order_change`  (
  `work_order_num` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `changed_old_order` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`work_order_num`) USING BTREE,
  INDEX `old_work_order_num`(`changed_old_order`) USING BTREE,
  CONSTRAINT `work_order_change_ibfk_1` FOREIGN KEY (`work_order_num`) REFERENCES `work_order` (`work_order_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `work_order_change_ibfk_2` FOREIGN KEY (`changed_old_order`) REFERENCES `work_order` (`work_order_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_order_change
-- ----------------------------

-- ----------------------------
-- Table structure for work_order_rollback
-- ----------------------------
DROP TABLE IF EXISTS `work_order_rollback`;
CREATE TABLE `work_order_rollback`  (
  `work_order_num` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_old_order` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`work_order_num`) USING BTREE,
  INDEX `old_work_order_num`(`rollback_old_order`) USING BTREE,
  CONSTRAINT `work_order_rollback_ibfk_1` FOREIGN KEY (`work_order_num`) REFERENCES `work_order` (`work_order_num`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `work_order_rollback_ibfk_2` FOREIGN KEY (`rollback_old_order`) REFERENCES `work_order` (`work_order_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_order_rollback
-- ----------------------------

-- ----------------------------
-- View structure for admin_operation_log
-- ----------------------------
DROP VIEW IF EXISTS `admin_operation_log`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `admin_operation_log` AS select `staff`.`work_num` AS `work_num`,`staff`.`name` AS `name`,`staff`.`dep_num` AS `dep_num`,`staff`.`phone` AS `phone`,`staff`.`state` AS `state`,`staff`.`in_service` AS `in_service`,`operation_log`.`operate_time` AS `operate_time`,`operation_log`.`operate` AS `operate`,`operation_log`.`ip` AS `ip`,`operation_log`.`address` AS `address` from (`staff` join `operation_log` on((`staff`.`work_num` = `operation_log`.`work_num`)));

-- ----------------------------
-- View structure for admin_workOrder_single_detail
-- ----------------------------
DROP VIEW IF EXISTS `admin_workOrder_single_detail`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `admin_workOrder_single_detail` AS select `work_order`.`work_order_num` AS `work_order_num`,`work_order`.`work_order_type` AS `work_order_type`,`work_order`.`work_order_name` AS `work_order_name`,`work_order`.`apply_time` AS `apply_time`,`work_order`.`file` AS `file`,`work_order`.`work_order_state` AS `work_order_state`,`work_order`.`reason` AS `reason`,`work_order_change`.`changed_old_order` AS `changed_old_order`,`work_order_rollback`.`rollback_old_order` AS `rollback_old_order`,`work_order`.`worker_num` AS `worker_num`,`staff`.`name` AS `name`,`staff`.`dep_num` AS `dep_num`,`staff`.`phone` AS `phone`,`staff`.`state` AS `state`,`staff`.`in_service` AS `in_service`,`department`.`dep_name` AS `dep_name`,`department`.`dep_level` AS `dep_level` from ((((`staff` join `work_order` on((`staff`.`work_num` = `work_order`.`worker_num`))) left join `work_order_change` on((`work_order`.`work_order_num` = `work_order_change`.`work_order_num`))) left join `work_order_rollback` on((`work_order`.`work_order_num` = `work_order_rollback`.`work_order_num`))) join `department` on((`staff`.`dep_num` = `department`.`dep_num`)));

-- ----------------------------
-- View structure for admin_work_order_inform
-- ----------------------------
DROP VIEW IF EXISTS `admin_work_order_inform`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `admin_work_order_inform` AS select `work_order_rollback`.`rollback_old_order` AS `rollback_old_order`,`work_order_change`.`changed_old_order` AS `changed_old_order`,`work_order`.`work_order_num` AS `work_order_num`,`work_order`.`work_order_name` AS `work_order_name`,`work_order`.`work_order_type` AS `work_order_type`,`work_order`.`apply_time` AS `apply_time`,`work_order`.`worker_num` AS `worker_num`,`work_order`.`file` AS `file`,`work_order`.`work_order_state` AS `work_order_state`,`work_order`.`reason` AS `reason`,`staff`.`name` AS `name`,`staff`.`dep_num` AS `dep_num`,`staff`.`phone` AS `phone`,`staff`.`state` AS `state`,`staff`.`in_service` AS `in_service` from (((`work_order` left join `work_order_change` on((`work_order`.`work_order_num` = `work_order_change`.`work_order_num`))) left join `work_order_rollback` on((`work_order`.`work_order_num` = `work_order_rollback`.`work_order_num`))) left join `staff` on((`work_order`.`worker_num` = `staff`.`work_num`)));

-- ----------------------------
-- View structure for dep_tree
-- ----------------------------
DROP VIEW IF EXISTS `dep_tree`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `dep_tree` AS select `department`.`dep_num` AS `dep_num`,`department`.`dep_name` AS `dep_name`,`department`.`dep_level` AS `dep_level`,`dep_relate`.`dep_num_superior` AS `dep_num_superior`,`dep_relate`.`dep_num_junior` AS `dep_num_junior` from (`department` left join `dep_relate` on((`department`.`dep_num` = `dep_relate`.`dep_num_junior`)));

-- ----------------------------
-- View structure for leader_order_detail
-- ----------------------------
DROP VIEW IF EXISTS `leader_order_detail`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `leader_order_detail` AS select `work_order`.`work_order_num` AS `work_order_num`,`work_order`.`work_order_type` AS `work_order_type`,`work_order`.`work_order_name` AS `work_order_name`,`work_order`.`apply_time` AS `apply_time`,`work_order`.`worker_num` AS `worker_num`,`staff`.`name` AS `name`,`work_order`.`work_order_state` AS `work_order_state`,`leadership`.`leder_num` AS `leder_num`,`flow_process`.`deal_date` AS `deal_date`,`flow_process`.`deal_comment` AS `deal_comment` from (((`leadership` join `staff` on((`leadership`.`under_num` = `staff`.`work_num`))) join `work_order` on((`work_order`.`worker_num` = `staff`.`work_num`))) join `flow_process` on((`work_order`.`work_order_num` = `flow_process`.`work_order_num`)));

-- ----------------------------
-- View structure for perWorkerResRate
-- ----------------------------
DROP VIEW IF EXISTS `perWorkerResRate`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `perWorkerResRate` AS select `staff`.`work_num` AS `work_num`,`staff`.`name` AS `name`,`allocated_com`.`ram` AS `ram`,`allocated_com`.`storage` AS `storage`,`allocated_com`.`ip` AS `ip`,`allocated_com`.`os` AS `os`,`allocated_com`.`work_order_num` AS `work_order_num`,`allocated_com`.`com_num` AS `com_num`,`allocated_com`.`com_type` AS `com_type`,`allocated_com`.`cpu_core` AS `cpu_core`,`work_order`.`work_order_name` AS `work_order_name`,`work_order`.`work_order_type` AS `work_order_type`,`work_order`.`apply_time` AS `apply_time`,`his_resource_usage`.`res_utilization` AS `res_utilization` from (((`work_order` join `staff` on((`work_order`.`worker_num` = `staff`.`work_num`))) join `allocated_com` on((`work_order`.`work_order_num` = `allocated_com`.`work_order_num`))) join `his_resource_usage` on((`work_order`.`work_order_num` = `his_resource_usage`.`work_order_num`)));

-- ----------------------------
-- View structure for staff_dep
-- ----------------------------
DROP VIEW IF EXISTS `staff_dep`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `staff_dep` AS select `staff`.`dep_num` AS `dep_num`,`department`.`dep_name` AS `dep_name`,`department`.`dep_level` AS `dep_level`,`staff`.`work_num` AS `work_num`,`staff`.`name` AS `name`,`staff`.`phone` AS `phone`,`staff`.`state` AS `state`,`staff`.`in_service` AS `in_service` from (`department` join `staff` on((`department`.`dep_num` = `staff`.`dep_num`)));

SET FOREIGN_KEY_CHECKS = 1;
