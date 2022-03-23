/*
Navicat MySQL Data Transfer

Source Server         : TEST_MySQL
Source Server Version : 50736
Source Host           : 119.91.195.217:3306
Source Database       : pim

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2021-12-06 00:22:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` char(32) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `role_desc` varchar(200) DEFAULT NULL,
  `role_authories` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  KEY `idx_role_name` (`role_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` char(32) NOT NULL,
  `login_name` varchar(30) NOT NULL,
  `login_pwd` varchar(50) DEFAULT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `role_id` char(32) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `user_status` char(1) DEFAULT '1',
  PRIMARY KEY (`user_id`),
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `dict_id` char(32) NOT NULL,
  `dict_name` varchar(50) DEFAULT NULL,
  `dict_desc` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `dict_type` char(2) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dict_id`),
  KEY `idx_dict_type` (`dict_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_hint
-- ----------------------------
DROP TABLE IF EXISTS `t_hint`;
CREATE TABLE `t_hint` (
  `hint_id` char(32) NOT NULL,
  `hint_name` varchar(200) DEFAULT NULL,
  `hint_level_id` char(32) DEFAULT NULL,
  `project_id` char(32) DEFAULT NULL,
  `user_id` char(32) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `audit_status` char(1) DEFAULT NULL,
  `ask_assign_time` datetime DEFAULT NULL,
  `hint_type_id` char(32) DEFAULT NULL,
  `item_type_id` char(32) DEFAULT NULL,
  `customer_type_ids` varchar(500) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `corp_name` varchar(200) DEFAULT NULL,
  `corp_address` varchar(200) DEFAULT NULL,
  `customer_name` varchar(100) DEFAULT NULL,
  `customer_contact` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `hint_from` char(1) DEFAULT '0',
  `hint_status` char(1) DEFAULT '0',
  `audit_result` varchar(50) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL,
  `valid_starttime` datetime DEFAULT NULL,
  `valid_endtime` datetime DEFAULT NULL,
  `claim_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`hint_id`),
  KEY `idx_hint_type_id` (`hint_type_id`) USING BTREE,
  KEY `idx_hint_level_id` (`hint_level_id`) USING BTREE,
  KEY `idx_project_id` (`project_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_crete_by` (`create_by`) USING BTREE,
  KEY `idx_hint_name` (`hint_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_hint_image
-- ----------------------------
DROP TABLE IF EXISTS `t_hint_image`;
CREATE TABLE `t_hint_image` (
  `hint_image_id` char(32) NOT NULL,
  `hint_id` char(32) NOT NULL,
  `img_objname` varchar(100) DEFAULT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`hint_image_id`),
  KEY `idx_hintid` (`hint_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_hint_record
-- ----------------------------
DROP TABLE IF EXISTS `t_hint_record`;
CREATE TABLE `t_hint_record` (
  `hint_record_id` char(32) NOT NULL,
  `hint_id` char(32) DEFAULT NULL,
  `project_id` char(32) DEFAULT NULL,
  `user_id` char(32) DEFAULT NULL,
  `record_content` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`hint_record_id`),
  KEY `idx_hint_id` (`hint_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_project_id` (`project_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_invite_code
-- ----------------------------
DROP TABLE IF EXISTS `t_invite_code`;
CREATE TABLE `t_invite_code` (
  `invite_code` char(10) NOT NULL,
  `project_id` char(32) DEFAULT NULL,
  `role_id` char(32) DEFAULT NULL,
  `open_id` char(50) DEFAULT NULL,
  `code_status` char(1) NOT NULL DEFAULT '0' COMMENT '0-Unused 1-Used 2-Discard',
  `used_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`invite_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `project_id` char(32) NOT NULL,
  `project_name` varchar(100) DEFAULT NULL,
  `project_admin_id` char(32) DEFAULT NULL,
  `project_area_admin_id` char(32) DEFAULT NULL,
  `project_status` char(4) DEFAULT '1',
  `project_desc` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`project_id`),
  KEY `idx_project_admin_id` (`project_admin_id`) USING BTREE,
  KEY `idx_project_area_admin_id` (`project_area_admin_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` char(32) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_position` varchar(50) DEFAULT NULL,
  `hint_level` char(32) DEFAULT NULL,
  `role_desc` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` char(32) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `tel` char(50) DEFAULT NULL,
  `superior_user_id` char(32) DEFAULT NULL,
  `nickName` varchar(50) DEFAULT NULL,
  `avatarObjectName` char(100) DEFAULT NULL,
  `user_status` char(1) DEFAULT '1',
  `open_id` char(50) DEFAULT NULL,
  `role_id` char(32) DEFAULT NULL,
  `project_id` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `invite_code` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_project_id` (`project_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
