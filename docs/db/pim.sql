/*
Navicat MySQL Data Transfer

Source Server         : Local_MySQL_5.7.36
Source Server Version : 50736
Source Host           : 127.0.0.1:3306
Source Database       : pim

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2022-02-13 09:53:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` char(32) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_desc` varchar(200) DEFAULT NULL,
  `role_authories` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1bb60038e24c46a6aaa3be93cce14807', 'Role0', 'Role Desc0', '[\"/adminManage\",\"/clueLevel\",\"/clueManage\",\"/flowerClue\",\"/clueType\"]', '2021-12-06 19:45:11');
INSERT INTO `sys_role` VALUES ('4c2941af5b594e429d08f11efd4a61dd', 'Role4', 'Role Desc4', '[\"/adminManage\",\"/clueLevel\",\"/clueManage\",\"/flowerClue\",\"/clueType\",\"/customerType\"]', '2021-12-06 19:45:11');
INSERT INTO `sys_role` VALUES ('58ae78dfd2a84681955e06c81e1e93f2', 'Role3', 'Role Desc3', '[\"/adminManage\",\"/clueLevel\",\"/clueManage\",\"/flowerClue\",\"/clueType\",\"/customerType\"]', '2021-12-06 19:45:11');
INSERT INTO `sys_role` VALUES ('63655e9f2e464e4285a3f00ce4efe84c', 'Role1', 'Role Desc1', '[\"/adminManage\",\"/clueLevel\",\"/clueManage\",\"/flowerClue\",\"/clueType\",\"/customerType\"]', '2021-12-06 19:45:11');

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
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('032f9278597a4bb984e8599e92b6f631', 'Account3', 'XX+HFAWY63Yy3jEgwhXlMw==', 'user_name3', '63655e9f2e464e4285a3f00ce4efe84c', 'Remark3', '1', '2021-12-06 19:34:03');
INSERT INTO `sys_user` VALUES ('2b7b8610233b4137813c2bf26f4a0f4b', 'Account1', 'LSgIs10JyNr6Rw+8/OnS1Q==', 'user_name1', '1bb60038e24c46a6aaa3be93cce14807', 'Remark1', '1', '2021-12-06 19:34:03');
INSERT INTO `sys_user` VALUES ('601b30772be64a11896fa54f8506ec97', 'Account4', '1TpnGUc773iQyY9uHZoPYA==', 'user_name4', '63655e9f2e464e4285a3f00ce4efe84c', 'Remark4', '1', '2021-12-06 19:34:03');
INSERT INTO `sys_user` VALUES ('86130c4ac600406097d2b12d68463d6a', 'Account0', 'UfmnbZlozOG1cMS6HfAh8g==', 'user_name0', '1bb60038e24c46a6aaa3be93cce14807', 'Remark0', '0', '2021-12-06 19:34:03');
INSERT INTO `sys_user` VALUES ('8f4bcdd59144409fb6873f0242187514', 'alice', 'PcXtp6DX5c3zJR8/X3L4NQ==', 'Alice Lee', 'role001', 'Remark001', '1', '2021-12-06 19:34:03');
INSERT INTO `sys_user` VALUES ('f05caabdc2594758a02a446923a7d8bf', 'Account2', 'CvZNpeY472JE++qhbLnAAw==', 'user_name2', '1bb60038e24c46a6aaa3be93cce14807', 'Remark2', '1', '2021-12-06 19:34:03');
INSERT INTO `sys_user` VALUES ('U001', 'user001', 'secret', 'Alice', '1bb60038e24c46a6aaa3be93cce14807', '', '1', '2021-12-07 23:28:00');
INSERT INTO `sys_user` VALUES ('U002', 'user001', 'secret', 'Alice', '1bb60038e24c46a6aaa3be93cce14807', 'Hello, Good', '1', '2021-12-07 23:28:26');

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
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('090e4d483b8a496cb4980e3cb6e7b79d', '开发部', 'Dev Dept', '2021-11-08 21:05:33', '01', '2021-11-23 05:11:16');
INSERT INTO `t_dict` VALUES ('158d2c57a27545c0a1eec33727b8033b', '一级', 'VVIP', '2021-11-08 21:05:34', '04', '2021-11-08 21:05:34');
INSERT INTO `t_dict` VALUES ('241a8357372547c5a125a14e2bb22691', '科技企业', '科技企业  desc', '2021-11-08 22:54:19', '03', '2021-11-08 22:54:19');
INSERT INTO `t_dict` VALUES ('2ccdc29c2474407ca4bcb1b51d65cdfe', '二级', 'VIP', '2021-11-08 20:50:32', '04', '2021-11-08 20:50:32');
INSERT INTO `t_dict` VALUES ('308317b4ae304175bee1b9eae48f808f', '三级', 'Normal', '2021-11-08 22:54:20', '04', '2021-11-08 22:54:20');
INSERT INTO `t_dict` VALUES ('5c3595e09acc447ea8d30c3729990308', '银行', '银行 desc', '2021-11-08 22:54:19', '02', '2021-11-08 22:54:19');
INSERT INTO `t_dict` VALUES ('7e99329fc15a427da11502661d636724', '餐饮', '餐饮 desc', '2021-11-08 22:54:19', '02', '2021-11-08 22:54:19');
INSERT INTO `t_dict` VALUES ('8869a155863b418a9f186e6581d14363', '运营部', '运营部', '2021-11-08 20:50:32', '01', '2021-11-08 20:50:32');
INSERT INTO `t_dict` VALUES ('9999', '72', null, '2021-12-08 21:58:14', '90', '2021-12-08 21:58:14');
INSERT INTO `t_dict` VALUES ('b4a13f6fc3a94fe28af89087eb2803b2', '传统企业', '传统企业  desc', '2021-11-08 22:54:19', '03', '2021-11-08 22:54:19');
INSERT INTO `t_dict` VALUES ('baa094e0e5074822af76deb6ed447e6f', '市场部', '市场部', '2021-11-08 20:50:32', '01', '2021-11-08 20:50:32');
INSERT INTO `t_dict` VALUES ('bdfaa627dd26472198cdca578f4fbd92', '银行超市', '超市 desc', '2021-11-08 22:54:19', '02', '2021-11-08 22:54:19');
INSERT INTO `t_dict` VALUES ('c7b11a21bf6048618f16e59c486df7c0', '机器人企业', '机器人企业 desc', '2021-11-08 22:54:20', '03', '2021-11-08 22:54:20');
INSERT INTO `t_dict` VALUES ('d38659a5453a45939ba8f3351420aa56', '销售部', '销售部', '2021-11-08 20:50:32', '01', '2021-11-08 20:50:32');
INSERT INTO `t_dict` VALUES ('e49856fbd63042178619a3e7cd58b0c1', '经理部', '经理部', '2021-11-08 21:05:33', '01', '2021-11-08 21:05:33');

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
  `ask_assign_time` datetime DEFAULT NULL,
  `audit_status` char(1) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
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
  PRIMARY KEY (`hint_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_hint
-- ----------------------------
INSERT INTO `t_hint` VALUES ('1', 'hint1', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', 'sale01', '2021-11-01 10:30:18', '1', 'proj01', '2021-12-01 00:01:52', null, null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, '2021-12-01 00:01:52');
INSERT INTO `t_hint` VALUES ('10', '线索3', '158d2c57a27545c0a1eec33727b8033b', '36494b53277c4e25bf9ca2e0e23e4e38', null, null, '1', 'sale03', '2021-11-09 22:13:34', '241a8357372547c5a125a14e2bb22691', '7e99329fc15a427da11502661d636724', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '苹果科技', '海滨路321号', '李斯', '13022223333', '线索 Remark3', '1', '0', null, '2021-11-06 22:13:35', null, null, '2021-11-03 22:13:35', '2021-11-09 22:13:34');
INSERT INTO `t_hint` VALUES ('11', '线索100德国 new', '158d2c57a27545c0a1eec33727b8033b', '4ed58177342d4ece9267a8ee096e8aa5', null, null, '0', 'sale02', '2021-11-19 13:06:04', 'b4a13f6fc3a94fe28af89087eb2803b2', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省 new', '深圳市 bew', '罗湖区', '甲骨文Oracle new', '银河村秋天组', '张先生', '13022334455', '高级客户线索', '1', '0', null, '2021-11-19 13:06:04', null, null, '2021-11-03 22:13:35', '2021-11-20 07:41:27');
INSERT INTO `t_hint` VALUES ('12', '线索6', '158d2c57a27545c0a1eec33727b8033b', '4ed58177342d4ece9267a8ee096e8aa5', null, null, '1', 'sale03', '2021-11-09 22:13:35', '241a8357372547c5a125a14e2bb22691', 'bdfaa627dd26472198cdca578f4fbd92', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '凌云科技', '海滨路321号', '张三', '13022223333', '线索 Remark6', '1', '0', null, '2021-11-19 13:06:04', null, null, '2021-11-03 22:13:35', '2021-11-09 22:13:35');
INSERT INTO `t_hint` VALUES ('13', '导入线索1', '158d2c57a27545c0a1eec33727b8033b', '4ed58177342d4ece9267a8ee096e8aa5', 'sale03', '2021-11-02 11:09:22', '2', 'build01', '2021-11-21 09:47:27', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '深圳', '罗湖', '中建一局', '宝安大道102号', '腾讯投资', '13011112222,010-222333', 'VIP', '3', '0', null, null, null, null, null, '2021-11-21 09:47:27');
INSERT INTO `t_hint` VALUES ('14', '线索9', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', null, null, '1', 'area01', '2021-11-09 22:13:35', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '国际电话', '海滨路321号', '张三', '13022223333', '线索 Remark9', '1', '0', null, '2021-11-19 13:06:04', null, null, '2021-11-03 22:13:35', '2021-11-09 22:13:35');
INSERT INTO `t_hint` VALUES ('15', '广东科学新10', '158d2c57a27545c0a1eec33727b8033b', '283b26c29ac148b1bdea47f019151713', null, null, '1', 'area01', '2021-11-09 22:13:34', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '甲骨文Oracle new', '海滨路321号', '张三', '13022223333', '线索 Remark0', '1', '0', null, '2021-11-19 13:06:04', null, null, '2021-11-03 22:13:35', '2021-11-09 22:23:58');
INSERT INTO `t_hint` VALUES ('16', '线索7', '308317b4ae304175bee1b9eae48f808f', '283b26c29ac148b1bdea47f019151713', null, null, '1', 'sale03', '2021-11-09 22:13:35', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '风云资本', '海滨路321号', '张三', '13022223333', '线索 Remark7', '1', '0', null, '2021-11-19 13:06:04', null, null, '2021-11-03 22:13:35', '2021-11-09 22:13:35');
INSERT INTO `t_hint` VALUES ('17', 'Janpan 01', '258d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', 'build03', '2021-11-03 05:24:50', '0', 'area01', '2021-12-01 07:27:28', '5c3595e09acc447ea8d30c3729990308', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省 new', '深圳市 bew', '罗湖区', '甲骨文Oracle new', '银河村秋天组', '张先生', '13022334455', '高级客户线索', '2', '0', null, null, null, null, null, '2021-12-01 07:27:28');
INSERT INTO `t_hint` VALUES ('18', 'Janpan 01', '258d2c57a27545c0a1eec33727b8033b', '36494b53277c4e25bf9ca2e0e23e4e38', 'sale03', '2021-11-05 05:24:50', '0', 'proj02', '2021-12-01 07:27:06', '5c3595e09acc447ea8d30c3729990308', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省 new', '深圳市 bew', '罗湖区', '甲骨文Oracle new', '银河村秋天组', '张先生', '13022334455', '高级客户线索', '2', '0', null, null, null, null, null, '2021-12-01 07:27:06');
INSERT INTO `t_hint` VALUES ('19', '线索8', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', null, null, '1', 'sale01', '2021-12-04 16:16:17', null, null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, '2021-12-04 16:16:17');
INSERT INTO `t_hint` VALUES ('1b04676cbdef4b3dbedb5358718b682e', '线索 001', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', null, null, '0', 'build01', '2021-12-07 06:12:33', '5c3595e09acc447ea8d30c3729990308', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省 new', '深圳市 bew', '罗湖区', '甲骨文Oracle new', '银河村秋天组', '张先生', '13022334455', '高级客户线索', '2', '0', null, null, null, null, null, '2021-12-07 06:12:33');
INSERT INTO `t_hint` VALUES ('2', 'aa', '158d2c57a27545c0a1eec33727b8033b', '36494b53277c4e25bf9ca2e0e23e4e38', null, null, '1', 'proj02', '2021-12-01 00:18:38', null, null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, '2021-12-01 00:18:38');
INSERT INTO `t_hint` VALUES ('20', '线索8', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', null, null, '1', 'sale01', '2021-12-04 16:16:25', null, null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, '2021-12-04 16:16:25');
INSERT INTO `t_hint` VALUES ('21', '线索8', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', null, null, '1', 'sale02', '2021-12-04 16:16:49', null, null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, '2021-12-04 16:16:49');
INSERT INTO `t_hint` VALUES ('22', '线索8', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', null, null, '1', 'sale01', '2021-12-04 16:17:22', null, null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, '2021-12-04 16:17:22');
INSERT INTO `t_hint` VALUES ('23', '线索8', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', 'sale01', '2021-11-05 05:24:50', '1', 'sale02', '2021-12-04 16:19:23', null, null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, '2021-12-04 16:19:23');
INSERT INTO `t_hint` VALUES ('3', 'bb', '158d2c57a27545c0a1eec33727b8033b', '36494b53277c4e25bf9ca2e0e23e4e38', null, '2021-11-09 05:24:50', '1', 'proj02', '2021-12-01 00:18:39', null, null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, '2021-12-01 00:18:39');
INSERT INTO `t_hint` VALUES ('4', '线索8', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', null, null, '1', 'sale01', '2021-11-09 22:13:31', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '国际电器', '海滨路321号', '张三', '13022223333,010123', 'Very Good', '1', '0', null, '2021-11-09 22:13:35', null, null, '2021-11-03 22:13:35', '2021-11-09 22:13:35');
INSERT INTO `t_hint` VALUES ('5', '线索4', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', null, null, '1', 'sale03', '2021-11-09 22:13:32', '241a8357372547c5a125a14e2bb22691', 'bdfaa627dd26472198cdca578f4fbd92', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '苹果投资', '海滨路321号', '李四', '13022223333', '线索 Remark4', '1', '0', null, '2021-11-04 22:13:35', null, null, '2021-11-03 22:13:35', '2021-11-09 22:13:35');
INSERT INTO `t_hint` VALUES ('6', '导入线索2', '158d2c57a27545c0a1eec33727b8033b', '36494b53277c4e25bf9ca2e0e23e4e38', null, null, '1', 'sale02', '2021-11-21 09:47:27', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '广州', '天河', '中建二局', '天河大道101号', '银河证券', '13111112222', 'VVIP', '3', '0', null, null, null, null, '2021-11-21 10:30:18', '2021-11-21 09:47:27');
INSERT INTO `t_hint` VALUES ('7', '线索1', '2ccdc29c2474407ca4bcb1b51d65cdfe', '144f807280c34ad290493591a5cae4cb', null, null, '1', 'sale02', '2021-11-09 22:13:33', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '苹果公司', '海滨路321号', '张多', '13022223333', '线索 Remark1', '1', '0', 'It is incorrect', '2021-11-21 11:22:06', null, null, '2021-11-03 22:13:35', '2021-11-09 22:13:34');
INSERT INTO `t_hint` VALUES ('8', '线索5', '158d2c57a27545c0a1eec33727b8033b', '36494b53277c4e25bf9ca2e0e23e4e38', null, null, '1', 'build01', '2021-11-09 22:13:35', '241a8357372547c5a125a14e2bb22691', 'bdfaa627dd26472198cdca578f4fbd92', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '苹果投资', '海滨路321号', '李云', '13022223333', '线索 Remark5', '1', '0', null, '2021-11-04 22:13:35', null, null, '2021-11-03 22:13:35', '2021-11-09 22:13:35');
INSERT INTO `t_hint` VALUES ('9', '线索2', '2ccdc29c2474407ca4bcb1b51d65cdfe', '36494b53277c4e25bf9ca2e0e23e4e38', null, null, '1', 'sale02', '2021-11-09 22:13:34', '241a8357372547c5a125a14e2bb22691', '7e99329fc15a427da11502661d636724', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '苹果科技', '海滨路321号', '张锋', '13022223333', '线索 Remark2', '1', '0', null, '2021-11-05 22:13:35', null, null, '2021-11-03 22:13:35', '2021-11-09 22:13:34');

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
-- Records of t_hint_image
-- ----------------------------
INSERT INTO `t_hint_image` VALUES ('1', '1', 'aa.jpg', null);
INSERT INTO `t_hint_image` VALUES ('2', '1', 'bb.png', null);
INSERT INTO `t_hint_image` VALUES ('3', '1', 'aa/cc.png', null);

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
  PRIMARY KEY (`hint_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_hint_record
-- ----------------------------
INSERT INTO `t_hint_record` VALUES ('111', '4315847db8cc4bdb85370c19f1cdea59', '144f807280c34ad290493591a5cae4cb', 'sale01', '很好', '2021-11-01 20:18:28');
INSERT INTO `t_hint_record` VALUES ('222', '4315847db8cc4bdb85370c19f1cdea59', '144f807280c34ad290493591a5cae4cb', 'build01', '完美', '2021-11-01 20:18:28');
INSERT INTO `t_hint_record` VALUES ('333', '22', '144f807280c34ad290493591a5cae4cb', 'sale01', '待跟进', '2021-11-01 20:18:28');
INSERT INTO `t_hint_record` VALUES ('444', 'e776626491004ee7883f7ec03aa726ce', '144f807280c34ad290493591a5cae4cb', 'sale01', '待跟进', '2021-11-02 20:18:28');
INSERT INTO `t_hint_record` VALUES ('555', '5ec80ebdafcf43fb86799aeb157b23ef', '144f807280c34ad290493591a5cae4cb', 'sale01', '待跟进', '2021-11-02 20:18:28');
INSERT INTO `t_hint_record` VALUES ('666', '830a411bf6144497ba0c284f249355d5', '4ed58177342d4ece9267a8ee096e8aa5', 'sale02', 'AAA', '2021-11-03 07:24:01');
INSERT INTO `t_hint_record` VALUES ('777', '10965cb0a85048c4ae07f493232bde8b', '80af32d21a67406b9499299d544ecf57', 'sale02', 'BBB', '2021-11-03 07:24:01');
INSERT INTO `t_hint_record` VALUES ('888', '9a60cd84117b4ffe87dc507f8080314e', '80af32d21a67406b9499299d544ecf57', 'sale03', 'CCC', '2021-11-03 07:24:01');
INSERT INTO `t_hint_record` VALUES ('9e6dd9b69ca64a2599340968f29be819', '10965cb0a85048c4ae07f493232bde8b', '144f807280c34ad290493591a5cae4cb', 'build01', 'Very Good', '2021-11-05 07:24:01');

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
-- Records of t_invite_code
-- ----------------------------
INSERT INTO `t_invite_code` VALUES ('02718cd2', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('04d00806', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('0c0ca9af', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('0ca9af2b', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('18cd2c75', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('2718cd2c', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('2b42d69b', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('2d69b027', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('42d69b02', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('455205', '144f807280c34ad290493591a5cae4cb', '29c563e9b36a4d84bf4d7dfa0450dbf7', null, '0', null, '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4a7ea1', '144f807280c34ad290493591a5cae4cb', '31bab25a8a7941459350217563573b23', '', '2', '2021-11-23 20:07:49', '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4a7ea2', '144f807280c34ad290493591a5cae4cb', '33bab25a8a7941459350217563573b23', '', '1', '2021-11-23 20:07:49', '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4a7ea3', '144f807280c34ad290493591a5cae4cb', '33bab25a8a7941459350217563573b23', '', '1', '2021-11-23 20:07:49', '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4a7ea4', '144f807280c34ad290493591a5cae4cb', '33bab25a8a7941459350217563573b23', '', '0', '2021-11-23 20:07:49', '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4a7ea5', '144f807280c34ad290493591a5cae4cb', '33bab25a8a7941459350217563573b23', null, '1', null, '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4a7ea6', '144f807280c34ad290493591a5cae4cb', '33bab25a8a7941459350217563573b23', '', '0', '2021-11-23 20:07:49', '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4d00806b', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('69b02718', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('718cd2c7', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('814972', '144f807280c34ad290493591a5cae4cb', '412f6c8db4d94ac3a85249076717b809', null, '1', null, '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('8cd2c750', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('9af2b42d', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('9b02718c', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('a9af2b42', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('af2b42d6', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('b02718cd', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('b42d69b0', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('c0ca9af2', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('ca9af2b4', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('cd2c7500', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('d0c0ca9a', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('d2c75009', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('d69b0271', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('e3a00f', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('f2b42d69', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('fd0c0ca9', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('fdf14e', '144f807280c34ad290493591a5cae4cb', '16803e86d0c5472686641365483f2d17', null, '2', null, '2021-10-24 22:32:02');

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
  `area_admin_join_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `project_admin_join_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project
-- ----------------------------
INSERT INTO `t_project` VALUES ('144f807280c34ad290493591a5cae4cb', '国际会展中心项目1', 'proj01', 'area01', '1', '国际会展中心项目 Desc', '2021-10-24 22:22:31', '2022-01-10 23:45:02', null, null);
INSERT INTO `t_project` VALUES ('283b26c29ac148b1bdea47f019151713', '国际智慧小区项目2', null, 'area02', '1', null, '2021-10-24 22:22:31', null, null, null);
INSERT INTO `t_project` VALUES ('36494b53277c4e25bf9ca2e0e23e4e38', '未来城市项目3', 'proj05', 'area02', '1', null, '2021-10-24 22:22:31', null, null, null);
INSERT INTO `t_project` VALUES ('4ed58177342d4ece9267a8ee096e8aa5', '未来都市项目4', 'proj02', 'area01', '2', '未来都市项目 Desc', '2021-11-17 06:40:27', null, null, null);
INSERT INTO `t_project` VALUES ('5e8b00909461477880bf78ac4efa3e68', '城市智慧国际交通项目5', null, 'area02', '1', '城市智慧交通项目 Desc', '2021-11-19 00:02:46', null, null, null);
INSERT INTO `t_project` VALUES ('6e8b00909461477880bf78ac4efa3e6', '城市园林项目6', null, 'area02', '1', null, '2021-11-19 00:02:46', '2021-11-23 13:15:34', null, null);
INSERT INTO `t_project` VALUES ('70af32d21a67406b9499299d544ecf57', 'Project 7', 'proj02', null, '1', 'New Desc', '2021-11-30 22:38:55', '2021-12-03 12:44:04', null, null);
INSERT INTO `t_project` VALUES ('80af32d21a67406b9499299d544ecf57', 'Project 8', null, null, '1', 'New Desc', '2021-11-30 22:38:55', '2021-11-30 22:38:55', null, null);

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
  PRIMARY KEY (`role_id`),
  KEY `idx_role_position` (`role_position`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('16803e86d0c5472686641365483f2d17', '区域管理员', '1', '158d2c57a27545c0a1eec33727b8033b', null, '2021-10-24 22:11:51');
INSERT INTO `t_role` VALUES ('29c563e9b36a4d84bf4d7dfa0450dbf7', '项目管理员', '2', '158d2c57a27545c0a1eec33727b8033b', null, '2021-10-24 22:11:52');
INSERT INTO `t_role` VALUES ('31bab25a8a7941459350217563573b23', '一级销售员', '3', '158d2c57a27545c0a1eec33727b8033b', null, '2021-10-24 22:11:53');
INSERT INTO `t_role` VALUES ('32bab25a8a7941459350217563573b23', '二级销售员', '3', '2ccdc29c2474407ca4bcb1b51d65cdfe', '', '2021-10-24 22:11:53');
INSERT INTO `t_role` VALUES ('412f6c8db4d94ac3a85249076717b809', '一级拓客员', '4', '158d2c57a27545c0a1eec33727b8033b', null, '2021-10-24 22:11:54');
INSERT INTO `t_role` VALUES ('428c0a0514774f008f6ee65bfbad2b7b', '二级拓客员', '4', '2ccdc29c2474407ca4bcb1b51d65cdfe', 'Role 002', '2021-11-24 20:35:07');
INSERT INTO `t_role` VALUES ('c45aefbda91941dcb977357455be1779', 'Role002 New', '4', '2ccdc29c2474407ca4bcb1b51d65cdfe', 'Role 002 New', '2021-11-24 20:32:39');

-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test` (
  `role_id` char(32) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_position` varchar(50) DEFAULT NULL,
  `hint_level` char(32) DEFAULT NULL,
  `role_desc` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`),
  KEY `idx_role_position` (`role_position`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_test
-- ----------------------------
INSERT INTO `t_test` VALUES ('16803e86d0c5472686641365483f2d17', '区域管理员', '1', '158d2c57a27545c0a1eec33727b8033b', null, '2021-10-24 22:11:51');
INSERT INTO `t_test` VALUES ('29c563e9b36a4d84bf4d7dfa0450dbf7', '项目管理员', '2', '158d2c57a27545c0a1eec33727b8033b', null, '2021-10-24 22:11:52');
INSERT INTO `t_test` VALUES ('31bab25a8a7941459350217563573b23', '一级销售员', '3', '158d2c57a27545c0a1eec33727b8033b', null, '2021-10-24 22:11:53');
INSERT INTO `t_test` VALUES ('32bab25a8a7941459350217563573b23', '二级销售员', '3', '2ccdc29c2474407ca4bcb1b51d65cdfe', '', '2021-10-24 22:11:53');
INSERT INTO `t_test` VALUES ('412f6c8db4d94ac3a85249076717b809', '一级拓客员', '4', '158d2c57a27545c0a1eec33727b8033b', null, '2021-10-24 22:11:54');
INSERT INTO `t_test` VALUES ('428c0a0514774f008f6ee65bfbad2b7b', '二级拓客员', '4', '2ccdc29c2474407ca4bcb1b51d65cdfe', 'Role 002', '2021-11-24 20:35:07');
INSERT INTO `t_test` VALUES ('aa1', '一级销售员', '3', '111', null, '2022-02-12 16:27:03');
INSERT INTO `t_test` VALUES ('aa2', '二级销售员', '4', '111', null, '2022-02-12 16:27:04');
INSERT INTO `t_test` VALUES ('aa3', '一级拓客员', '4', '111', null, '2022-02-12 16:27:05');
INSERT INTO `t_test` VALUES ('aa4', '二级拓客员', '4', '222', null, '2022-02-12 16:27:07');
INSERT INTO `t_test` VALUES ('c45aefbda91941dcb977357455be1779', 'Role002 New', '4', '2ccdc29c2474407ca4bcb1b51d65cdfe', 'Role 002 New', '2021-11-24 20:32:39');

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
  `join_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  KEY `idx_project_id` (`project_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('area01', 'Area 01', '13688889999', null, null, null, '1', '111', '16803e86d0c5472686641365483f2d17', null, '2021-11-01 20:01:04', null, '2021-12-06 22:40:27');
INSERT INTO `t_user` VALUES ('area02', 'Area 02', '13824183324', null, null, null, '1', '112', '16803e86d0c5472686641365483f2d17', null, '2021-11-01 20:01:04', null, null);
INSERT INTO `t_user` VALUES ('build01', 'Build 01', '13122223333', null, null, null, '1', '116', '412f6c8db4d94ac3a85249076717b809', '283b26c29ac148b1bdea47f019151713', '2021-11-01 20:01:04', null, '2021-12-08 23:30:44');
INSERT INTO `t_user` VALUES ('build02', 'Build 02', '13122223333', '', null, '', '1', '116', '412f6c8db4d94ac3a85249076717b809', '144f807280c34ad290493591a5cae4cb', '2021-11-02 20:01:04', null, null);
INSERT INTO `t_user` VALUES ('build03', 'Build 03', '13122223333', '', null, '', '1', '116', '428c0a0514774f008f6ee65bfbad2b7b', '36494b53277c4e25bf9ca2e0e23e4e38', '2021-11-02 20:01:04', null, '2021-12-08 23:30:44');
INSERT INTO `t_user` VALUES ('build04', 'Build 04', '13122223333', '', '', '', '1', '116', '412f6c8db4d94ac3a85249076717b809', '283b26c29ac148b1bdea47f019151713', '2021-11-02 20:01:04', '', null);
INSERT INTO `t_user` VALUES ('proj01', 'Project Admin 01', '13011112222', null, null, '202112/3b407312789e429c8f02f131924526e2', '1', '113', '29c563e9b36a4d84bf4d7dfa0450dbf7', '144f807280c34ad290493591a5cae4cb', '2021-11-01 20:01:04', null, null);
INSERT INTO `t_user` VALUES ('proj02', 'Project Admin 02', '13011112222', '', null, '', '1', '114', '29c563e9b36a4d84bf4d7dfa0450dbf7', '', '2021-11-05 20:01:04', null, null);
INSERT INTO `t_user` VALUES ('proj03', 'Project Admin 03', '', null, '', '', '1', null, '29c563e9b36a4d84bf4d7dfa0450dbf7', null, '2021-11-05 20:01:04', null, null);
INSERT INTO `t_user` VALUES ('proj04', 'Project Admin 04', '', null, '', '', '1', null, '29c563e9b36a4d84bf4d7dfa0450dbf7', null, '2021-11-07 20:01:04', null, null);
INSERT INTO `t_user` VALUES ('proj05', 'Project Admin 05', '', '', '', '', '1', '', '29c563e9b36a4d84bf4d7dfa0450dbf7', '', '2021-11-07 20:01:04', '', null);
INSERT INTO `t_user` VALUES ('sale01', 'Sale 01', '13022224444', null, null, '202112/3b407312789e429c8f02f131924526e2', '1', '115', '31bab25a8a7941459350217563573b23', '144f807280c34ad290493591a5cae4cb', '2021-11-07 20:01:04', '4a7ea1', '2021-11-01 20:01:04');
INSERT INTO `t_user` VALUES ('sale02', 'Sale 02', '13022224444', '', null, '202112/3b407312789e429c8f02f131924526e2', '1', '117', '428c0a0514774f008f6ee65bfbad2b7b', '144f807280c34ad290493591a5cae4cb', '2021-11-01 20:01:04', '4a7ea2', '2021-11-02 20:01:04');
INSERT INTO `t_user` VALUES ('sale03', 'Sale 03', '13022224444', '', null, '', '1', '118', '31bab25a8a7941459350217563573b23', '144f807280c34ad290493591a5cae4cb', '2021-11-01 20:01:04', '4a7ea3', '2021-11-02 20:01:04');
INSERT INTO `t_user` VALUES ('sale04', 'Sale 04', '13022224444', '', '', '', '1', '118', '428c0a0514774f008f6ee65bfbad2b7b', '283b26c29ac148b1bdea47f019151713', '2021-11-09 20:01:04', '4a7ea3', null);
INSERT INTO `t_user` VALUES ('sale05', 'Sale 05', null, null, null, null, '1', null, '31bab25a8a7941459350217563573b23', '', '2021-11-09 20:01:04', null, '2021-12-07 05:07:39');
INSERT INTO `t_user` VALUES ('sale06', 'Sale 06', null, null, null, null, '1', null, '428c0a0514774f008f6ee65bfbad2b7b', '', '2021-11-01 20:01:04', null, '2021-12-07 05:07:39');
