/*
Navicat MySQL Data Transfer

Source Server         : 192.168.3.19
Source Server Version : 50723
Source Host           : 192.168.3.19:3306
Source Database       : share

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-09-26 11:49:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for share_category
-- ----------------------------
DROP TABLE IF EXISTS `share_category`;
CREATE TABLE `share_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_category
-- ----------------------------
INSERT INTO `share_category` VALUES ('1', '猫', '2018-08-06 16:34:04', '2018-08-06 16:34:07', '0', '0');
INSERT INTO `share_category` VALUES ('2', '乌龟', '2018-08-06 16:34:18', '2018-08-06 16:34:20', '0', '0');

-- ----------------------------
-- Table structure for share_comment
-- ----------------------------
DROP TABLE IF EXISTS `share_comment`;
CREATE TABLE `share_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `feed_id` int(11) NOT NULL,
  `to_uId` int(11) NOT NULL COMMENT '当为主评论时候，代表发布feed人的id',
  `from_uid` int(11) NOT NULL,
  `t_id` int(11) DEFAULT NULL COMMENT 'null为主评论，其他为表id',
  `content` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `comment_feed_id` (`feed_id`),
  KEY `comment_to_uId` (`to_uId`),
  KEY `comment_from_uid` (`from_uid`),
  KEY `comment_tid` (`t_id`),
  CONSTRAINT `comment_feed_id` FOREIGN KEY (`feed_id`) REFERENCES `share_feed` (`id`),
  CONSTRAINT `comment_from_uid` FOREIGN KEY (`from_uid`) REFERENCES `share_user` (`id`),
  CONSTRAINT `comment_tid` FOREIGN KEY (`t_id`) REFERENCES `share_comment` (`id`),
  CONSTRAINT `comment_to_uId` FOREIGN KEY (`to_uId`) REFERENCES `share_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_comment
-- ----------------------------
INSERT INTO `share_comment` VALUES ('5', '1', '1', '1', null, '你好啊11111', '2018-09-03 17:36:31', '2018-09-03 17:36:31', '0', '0');
INSERT INTO `share_comment` VALUES ('6', '1', '1', '2', '5', 'aaaa你好啊11111', '2018-09-03 17:37:27', '2018-09-03 17:37:27', '0', '0');
INSERT INTO `share_comment` VALUES ('7', '1', '1', '2', '5', 'aaaa你好啊11111', '2018-09-03 17:37:57', '2018-09-03 17:37:57', '0', '0');

-- ----------------------------
-- Table structure for share_feed
-- ----------------------------
DROP TABLE IF EXISTS `share_feed`;
CREATE TABLE `share_feed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `images` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `like_count` int(11) DEFAULT '0',
  `comment_count` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `feed_user_id` (`user_id`),
  KEY `feed_tag_id` (`tag_id`),
  KEY `feed_category_id` (`category_id`),
  CONSTRAINT `feed_category_id` FOREIGN KEY (`category_id`) REFERENCES `share_category` (`id`),
  CONSTRAINT `feed_tag_id` FOREIGN KEY (`tag_id`) REFERENCES `share_tag` (`id`),
  CONSTRAINT `feed_user_id` FOREIGN KEY (`user_id`) REFERENCES `share_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_feed
-- ----------------------------
INSERT INTO `share_feed` VALUES ('1', '1', '测试乌龟', 'http://www.qv67.com/uploads/2016060809/201606080951204343.jpg', null, '2', '12', '2', '2', '2018-08-06 16:35:26', '2018-08-29 15:21:04', '0', '0');
INSERT INTO `share_feed` VALUES ('2', '2', '测试兔子', 'http://www.qv67.com/uploads/2016060809/201606080951204343.jpg', null, '2', '1', '1', '1', '2018-08-10 17:37:01', '2018-09-06 10:14:24', '0', '0');

-- ----------------------------
-- Table structure for share_follow
-- ----------------------------
DROP TABLE IF EXISTS `share_follow`;
CREATE TABLE `share_follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_left` int(11) NOT NULL,
  `user_right` int(11) NOT NULL,
  `relation` int(1) NOT NULL COMMENT '1：单向；2：双向',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `follow_user_left` (`user_left`),
  KEY `follow_user_right` (`user_right`),
  CONSTRAINT `follow_user_left` FOREIGN KEY (`user_left`) REFERENCES `share_user` (`id`),
  CONSTRAINT `follow_user_right` FOREIGN KEY (`user_right`) REFERENCES `share_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_follow
-- ----------------------------
INSERT INTO `share_follow` VALUES ('9', '2', '1', '1', '2018-08-21 16:31:53', '2018-08-28 11:48:52', null, null);

-- ----------------------------
-- Table structure for share_like
-- ----------------------------
DROP TABLE IF EXISTS `share_like`;
CREATE TABLE `share_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `feed_id` int(11) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `like_user_id` (`user_id`),
  KEY `like_feed_id` (`feed_id`),
  CONSTRAINT `like_feed_id` FOREIGN KEY (`feed_id`) REFERENCES `share_feed` (`id`),
  CONSTRAINT `like_user_id` FOREIGN KEY (`user_id`) REFERENCES `share_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_like
-- ----------------------------
INSERT INTO `share_like` VALUES ('7', '2', '2', '2018-08-28 16:50:33', '2018-08-28 16:50:33', null, null);
INSERT INTO `share_like` VALUES ('24', '1', '1', '2018-08-29 12:48:33', '2018-08-29 12:48:33', null, null);
INSERT INTO `share_like` VALUES ('76', '2', '1', '2018-08-29 15:21:04', '2018-08-29 15:21:04', null, null);
INSERT INTO `share_like` VALUES ('77', '1', '2', '2018-09-06 10:14:24', '2018-09-06 10:14:24', null, null);

-- ----------------------------
-- Table structure for share_role
-- ----------------------------
DROP TABLE IF EXISTS `share_role`;
CREATE TABLE `share_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_role
-- ----------------------------
INSERT INTO `share_role` VALUES ('1', 'admin', '2018-08-03 17:24:40', '2018-08-03 17:24:43', '0', '0');
INSERT INTO `share_role` VALUES ('2', 'user', '2018-08-03 17:24:45', '2018-08-03 17:24:48', '0', '0');

-- ----------------------------
-- Table structure for share_tag
-- ----------------------------
DROP TABLE IF EXISTS `share_tag`;
CREATE TABLE `share_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `name` varchar(255) NOT NULL COMMENT '标签名字',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_tag
-- ----------------------------
INSERT INTO `share_tag` VALUES ('1', '社区', '2018-08-03 17:27:48', '2018-08-03 17:27:50', '0', '0');
INSERT INTO `share_tag` VALUES ('2', '内容', '2018-08-03 17:28:17', '2018-08-03 17:28:19', '0', '0');

-- ----------------------------
-- Table structure for share_user
-- ----------------------------
DROP TABLE IF EXISTS `share_user`;
CREATE TABLE `share_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `phone` varchar(11) NOT NULL COMMENT '手机号码',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `app_type` int(1) DEFAULT '0' COMMENT '0: 未知1:ios 2:android 3: web',
  `role_id` int(11) DEFAULT NULL,
  `app_model` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `app_version` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `token` varchar(255) DEFAULT NULL COMMENT 'token',
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  KEY `user_role_id` (`role_id`),
  CONSTRAINT `user_role_id` FOREIGN KEY (`role_id`) REFERENCES `share_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_user
-- ----------------------------
INSERT INTO `share_user` VALUES ('1', '18258005578', 'e10adc3949ba59abbe56e057f20f883e', '2', '1', 'VTR-AL00', '2018-08-06 11:57:13', '1', '2018-08-29 10:19:57', 'MTgyNTgwMDU1Nzg6MTUzODY2Mjc5NjU5NzpiYWIyNzUyYTc4NDYwYzcwZGYxZTY2ZTlkYzMyNzk0Mg', '0', '0');
INSERT INTO `share_user` VALUES ('2', '18258005577', 'e10adc3949ba59abbe56e057f20f883e', '2', '1', 'MI NOTE LTE', '2018-08-17 09:23:34', '1', '2018-08-28 11:04:56', 'MTgyNTgwMDU1Nzc6MTUzODU3OTA5NTc4MTplZjNhOTJjY2MxMzI4MWQzMmQzMmM0OTcxNjMzZmQ3Nw', '0', '0');

-- ----------------------------
-- Table structure for share_user_info
-- ----------------------------
DROP TABLE IF EXISTS `share_user_info`;
CREATE TABLE `share_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT '0',
  `gender` int(255) DEFAULT '0' COMMENT '0 未知1 男 2 女',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `version` int(11) DEFAULT '0',
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `detail_user_id` (`user_id`),
  KEY `detail_category_id` (`category_id`),
  CONSTRAINT `detail_category_id` FOREIGN KEY (`category_id`) REFERENCES `share_category` (`id`),
  CONSTRAINT `detail_user_id` FOREIGN KEY (`user_id`) REFERENCES `share_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_user_info
-- ----------------------------
INSERT INTO `share_user_info` VALUES ('1', '1', '测试吴11', 'http://pic34.photophoto.cn/20150309/0044040946028236_b.jpg', 'http://www.baidu.com', 'handleUserInfoNoFoundException', '1', '24', '2', '2018-09-06 10:13:05', '2018-09-06 10:13:07', '0', '0', null);
INSERT INTO `share_user_info` VALUES ('2', '2', '测试张', 'http://pic34.photophoto.cn/20150309/0044040946028236_b.jpg', 'http://pic34.photophoto.cn/20150309/0044040946028236_b.jpg', '杭州', '1', '22', '2', '2018-08-17 09:24:25', '2018-08-17 09:24:28', '0', '0', null);
