/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : localhost:3306
 Source Schema         : sensboot

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 19/08/2019 23:53:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `cost_time` int(11) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `ip_info` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `request_param` longtext,
  `request_type` varchar(255) DEFAULT NULL,
  `request_url` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `log_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
BEGIN;
INSERT INTO `log` VALUES (1, 'system', '2019-08-19 23:35:33', 0, 'system', '2019-08-19 23:35:33', 3720, '127.0.0.1', NULL, '用户登录', '{\"password\":\"你是看不见我的\",\"username\":\"saysky\"}', 'POST', '/doLogin', NULL, 1);
INSERT INTO `log` VALUES (2, 'system', '2019-08-19 23:35:39', 0, 'system', '2019-08-19 23:35:39', 2202, '127.0.0.1', NULL, '查询用户列表', '{}', 'GET', '/user', 'saysky', 0);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` int(2) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_username_uq` (`username`) USING BTREE COMMENT '用户名，唯一索引',
  UNIQUE KEY `user_email_uq` (`email`) USING BTREE COMMENT '邮箱，唯一索引',
  KEY `user_created_time` (`created_time`) USING BTREE COMMENT '创建时间，普通索引'
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 0, '', '言曌', '847064370@qq.com', 'saysky', '123456', '2019-07-22 15:04:09', 'system', '2019-07-22 15:04:16', 'system');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
