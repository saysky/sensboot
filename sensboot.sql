/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : localhost
 Source Database       : sensboot

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : utf-8

 Date: 06/26/2019 17:31:55 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sens_user`
-- ----------------------------
DROP TABLE IF EXISTS `sens_user`;
CREATE TABLE `sens_user` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `login_enable` varchar(10) DEFAULT NULL,
  `login_error` int(11) DEFAULT NULL,
  `login_last` datetime DEFAULT NULL,
  `user_avatar` varchar(255) DEFAULT NULL,
  `user_desc` varchar(255) DEFAULT NULL,
  `user_display_name` varchar(255) DEFAULT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `user_pass` varchar(255) DEFAULT NULL,
  `user_site` varchar(255) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email_enable` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_user_email` (`user_email`) USING BTREE,
  UNIQUE KEY `uk_user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sens_user`
-- ----------------------------
BEGIN;
INSERT INTO `sens_user` VALUES ('1', 'true', '0', '2019-05-30 21:59:22', 'http://cdn-blog.liuyanzhao.com/uploads/2019/4/1f00317cbaaf712c93e481d021e93791', '', '言曌', '847064370@qq.com', 'saysky', 'a021a665f503979c06f50b8de66a4218', null, '0', '2019-01-24 00:07:33', 'false');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
