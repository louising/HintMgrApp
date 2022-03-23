/*
Navicat MySQL Data Transfer

Source Server         : TEST_MySQL
Source Server Version : 50736
Source Host           : 119.91.195.217:3306
Source Database       : pim

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2021-12-06 00:21:50
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
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1bb60038e24c46a6aaa3be93cce14807', 'Role0', 'Role Desc0', '[\"/adminManage\",\"/clueLevel\",\"/clueManage\",\"/flowerClue\",\"/clueType\"]');
INSERT INTO `sys_role` VALUES ('44097245adc442628a5a8f0f5f4c4588', 'ceshi', 'ceshi', '[\"/clueManage\",\"/flowerClue\",\"/projectManage\",\"/projectType\",\"/total\"]');
INSERT INTO `sys_role` VALUES ('4c2941af5b594e429d08f11efd4a61dd', 'Role4', 'Role Desc4', '[\"/adminManage\",\"/clueLevel\",\"/clueManage\",\"/flowerClue\",\"/clueType\",\"/customerType\"]');
INSERT INTO `sys_role` VALUES ('58ae78dfd2a84681955e06c81e1e93f2', 'Role3', 'Role Desc3', '[\"/adminManage\",\"/clueLevel\",\"/clueManage\",\"/flowerClue\",\"/clueType\",\"/customerType\"]');
INSERT INTO `sys_role` VALUES ('63655e9f2e464e4285a3f00ce4efe84c', 'Role1', 'Role Desc1', '[\"/adminManage\",\"/clueLevel\",\"/clueManage\",\"/flowerClue\",\"/clueType\",\"/customerType\"]');
INSERT INTO `sys_role` VALUES ('9d0fa55ea1af4d3096b874aa9e43eb14', 'ceshi2', 'ceshi2', '[\"/userList\",\"/codeList\",\"/roleList\",\"/clueType\",\"/customerType\",\"/clueLevel\"]');

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
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('032f9278597a4bb984e8599e92b6f631', 'Account3', 'p8/liuZtvRq/pSeARJwH8w==', '测试2', '44097245adc442628a5a8f0f5f4c4588', '123123', '0');
INSERT INTO `sys_user` VALUES ('2b7b8610233b4137813c2bf26f4a0f4b', 'Account1', 'LSgIs10JyNr6Rw+8/OnS1Q==', 'user_name1', '1bb60038e24c46a6aaa3be93cce14807', 'Remark1', '1');
INSERT INTO `sys_user` VALUES ('601b30772be64a11896fa54f8506ec97', 'Account4', '1TpnGUc773iQyY9uHZoPYA==', 'user_name4', '63655e9f2e464e4285a3f00ce4efe84c', 'Remark4', '1');
INSERT INTO `sys_user` VALUES ('86130c4ac600406097d2b12d68463d6a', 'Account0', 'UfmnbZlozOG1cMS6HfAh8g==', 'user_name0', '1bb60038e24c46a6aaa3be93cce14807', 'Remark0', '0');
INSERT INTO `sys_user` VALUES ('8f4bcdd59144409fb6873f0242187514', 'alice', 'PcXtp6DX5c3zJR8/X3L4NQ==', 'Alice Lee', 'role001', 'Remark001', '1');
INSERT INTO `sys_user` VALUES ('f05caabdc2594758a02a446923a7d8bf', 'Account2', 'CvZNpeY472JE++qhbLnAAw==', 'user_name2', '1bb60038e24c46a6aaa3be93cce14807', 'Remark2', '1');

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
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('090e4d483b8a496cb4980e3cb6e7b79d', '开发部2', 'Dev Dept2', '2021-11-08 21:05:33', '01', '2021-12-01 10:53:20');
INSERT INTO `t_dict` VALUES ('158d2c57a27545c0a1eec33727b8033b', '一级', 'VVIP2', '2021-11-08 21:05:34', '04', '2021-12-01 10:53:38');
INSERT INTO `t_dict` VALUES ('241a8357372547c5a125a14e2bb22691', '科技企业', '科技企业', '2021-11-08 22:54:14', '03', '2021-12-01 10:50:05');
INSERT INTO `t_dict` VALUES ('2ccdc29c2474407ca4bcb1b51d65cdfe', '二级', 'VIP', '2021-11-08 20:50:32', '04', '2021-11-08 20:50:32');
INSERT INTO `t_dict` VALUES ('308317b4ae304175bee1b9eae48f808f', '三级', 'Normal', '2021-11-08 22:54:20', '04', '2021-11-08 22:54:20');
INSERT INTO `t_dict` VALUES ('5c3595e09acc447ea8d30c3729990308', '银行', '银行 desc', '2021-11-08 22:54:11', '02', '2021-11-08 22:54:19');
INSERT INTO `t_dict` VALUES ('7e99329fc15a427da11502661d636724', '餐饮', '餐饮 desc', '2021-11-08 22:54:12', '02', '2021-11-08 22:54:19');
INSERT INTO `t_dict` VALUES ('8869a155863b418a9f186e6581d14363', '运营部', '运营部', '2021-11-08 20:50:31', '01', '2021-11-08 20:50:32');
INSERT INTO `t_dict` VALUES ('b4a13f6fc3a94fe28af89087eb2803b2', '传统企业', '传统企业  desc', '2021-11-08 22:54:15', '03', '2021-11-08 22:54:19');
INSERT INTO `t_dict` VALUES ('baa094e0e5074822af76deb6ed447e6f', '市场部', '市场部', '2021-11-08 20:50:32', '01', '2021-11-08 20:50:32');
INSERT INTO `t_dict` VALUES ('bdfaa627dd26472198cdca578f4fbd92', '银行超市', '超市 desc2', '2021-11-08 22:54:13', '02', '2021-12-01 10:53:07');
INSERT INTO `t_dict` VALUES ('c7b11a21bf6048618f16e59c486df7c0', '机器人企业', '机器人企业 desc', '2021-11-08 22:54:26', '03', '2021-11-08 22:54:20');
INSERT INTO `t_dict` VALUES ('c91a32a068fd47a19e073796ddff6cb3', '动漫企业2', '动漫企业3', '2021-12-01 10:50:27', '03', '2021-12-01 10:50:45');
INSERT INTO `t_dict` VALUES ('d38659a5453a45939ba8f3351420aa56', '销售部', '销售部', '2021-11-08 20:50:33', '01', '2021-11-08 20:50:32');
INSERT INTO `t_dict` VALUES ('e49856fbd63042178619a3e7cd58b0c1', '经理部', '经理部', '2021-11-08 21:05:34', '01', '2021-11-08 21:05:33');

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
-- Records of t_hint
-- ----------------------------
INSERT INTO `t_hint` VALUES ('162328711e254bd680d745a977c0313f', '导入线索2', '158d2c57a27545c0a1eec33727b8033b', null, null, null, '2021-11-28 11:30:13', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '广州', '天河', '中建二局', '天河大道101号', '银河证券', '13111112222', 'VVIP', '3', '0', null, null, null, null, null, '2021-11-28 11:30:13');
INSERT INTO `t_hint` VALUES ('19145879b423444795b16614cd437c96', '导入线索22', '158d2c57a27545c0a1eec33727b8033b', null, null, 'root', '2021-12-01 00:57:02', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '广州', '天河', '中建二局', '天河大道101号', '银河证券', '13111112222', 'VVIP', '3', '0', null, null, null, null, null, '2021-12-01 00:57:02');
INSERT INTO `t_hint` VALUES ('1bd55cac37544d408a8cffb920740f74', '线索4', '308317b4ae304175bee1b9eae48f808f', '144f807280c34ad290493591a5cae4cb', 'sale01', 'sale01', '2021-11-09 22:13:35', '2', '2021-11-29 07:46:51', '241a8357372547c5a125a14e2bb22691', 'bdfaa627dd26472198cdca578f4fbd92', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '苹果投资', '海滨路321号', '李四123', '13022223333', '线索 Remark4', '1', '1', null, '2021-11-28 17:56:03', '2021-11-03 00:00:00', '2022-01-04 00:00:00', '2021-11-03 22:13:35', '2021-12-01 10:35:30');
INSERT INTO `t_hint` VALUES ('229c1384ac8b4f90a64407bb0989fb17', '导入线索3', '2ccdc29c2474407ca4bcb1b51d65cdfe', null, null, null, '2021-11-28 11:34:35', '1', null, 'b4a13f6fc3a94fe28af89087eb2803b2', '7e99329fc15a427da11502661d636724', '8869a155863b418a9f186e6581d14363,d38659a5453a45939ba8f3351420aa56', '福建', '厦门', '集美', '中建三局', '集美广场203号', '科龙电器', '13211112222', 'Normal', '3', '0', null, null, null, null, null, '2021-11-28 11:34:35');
INSERT INTO `t_hint` VALUES ('284943528d784742895c129fa60fed62', '导入线索22', '158d2c57a27545c0a1eec33727b8033b', null, null, 'root', '2021-12-01 01:00:47', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '广州', '天河', '中建二局', '天河大道101号', '银河证券', '13111112222', 'VVIP', '3', '0', null, null, null, null, null, '2021-12-01 01:00:47');
INSERT INTO `t_hint` VALUES ('2ff9fa79a0964816b21a16360b4cab4b', '导入线索2', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', '2f964aaeaba84464b78e21726662f00e', 'sale02', '2021-11-21 09:47:27', '1', '2021-12-01 21:23:04', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '广州', '天河', '中建二局', '天河大道101号', '银河证券', '13111112222', 'VVIP', '3', '1', null, null, '2021-11-21 11:09:29', '2021-12-21 11:09:29', '2021-11-21 10:30:18', '2021-11-21 09:47:27');
INSERT INTO `t_hint` VALUES ('331141e734c344ed9813c6adcded88cb', '导入线索11', '158d2c57a27545c0a1eec33727b8033b', null, null, 'root', '2021-12-01 00:58:10', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '深圳', '罗湖', '中建一局', '宝安大道102号', '腾讯投资', '13011112222,010-222333', 'VIP', '3', '0', null, null, null, null, null, '2021-12-01 00:58:10');
INSERT INTO `t_hint` VALUES ('3cc43cb21a52470bb94eb9663d1086e4', '测试项目线索名称2', '158d2c57a27545c0a1eec33727b8033b', '54ab33f84167478aaabd549523ccc5cf', null, 'root', '2021-12-03 10:27:15', '1', null, 'c7b11a21bf6048618f16e59c486df7c0', '7e99329fc15a427da11502661d636724', 'baa094e0e5074822af76deb6ed447e6f', '广东省', '深圳市', '南山区', '测试项目有限公司2', '测试地址2', '测试客户2', '18718417110', '测试备注2', '1', '0', null, null, null, null, null, '2021-12-03 10:27:15');
INSERT INTO `t_hint` VALUES ('409284c94fc24316bc1bb213409fe381', '导入线索22', '158d2c57a27545c0a1eec33727b8033b', null, null, 'root', '2021-12-01 00:59:05', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '广州', '天河', '中建二局', '天河大道101号', '银河证券', '13111112222', 'VVIP', '3', '0', null, null, null, null, null, '2021-12-01 00:59:05');
INSERT INTO `t_hint` VALUES ('4315847db8cc4bdb85370c19f1cdea59', '线索1', '258d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', 'build01', 'sale01', '2021-11-09 22:13:34', '2', '2021-11-21 11:09:23', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '苹果公司', '海滨路321号', '张多', '13022223333', '线索 Remark1', '1', '1', 'It is incorrect', '2021-11-21 11:22:06', '2021-11-02 22:13:35', '2021-12-02 22:13:35', '2021-11-03 22:13:35', '2021-11-09 22:13:34');
INSERT INTO `t_hint` VALUES ('43ac5753c7864a15b5ddbc169cb8f5e0', 'apple线索', '158d2c57a27545c0a1eec33727b8033b', '283b26c29ac148b1bdea47f019151713', 'sale01', 'root', '2021-11-29 14:21:24', '1', '2021-11-29 14:54:58', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '北京市', '北京市', '东城区', 'apple科技有限公司', '第一街', '余先生', '18718417101', '', '1', '1', null, null, null, null, null, '2021-11-29 14:21:24');
INSERT INTO `t_hint` VALUES ('43bce6d5d9d5458486996b904874ea90', '三生三世', '2ccdc29c2474407ca4bcb1b51d65cdfe', '283b26c29ac148b1bdea47f019151713', 'sale01', 'sale01', '2021-11-28 08:09:55', '1', '2021-12-03 09:31:13', 'b4a13f6fc3a94fe28af89087eb2803b2', '7e99329fc15a427da11502661d636724', '8869a155863b418a9f186e6581d14363,baa094e0e5074822af76deb6ed447e6f,d38659a5453a45939ba8f3351420aa56', '广东省', '广州市', '荔湾区', '少时诵诗书', '荔湾大厦', '撒啊啊女', '2807064', '少时诵诗书所所所所', '1', '1', null, null, '2021-12-03 09:31:13', '2022-01-02 09:31:13', null, '2021-11-28 08:09:55');
INSERT INTO `t_hint` VALUES ('44957deb0e9c4936beb2244ea464504e', '线索5', '7ccdc29c2474407ca4bcb1b51d65cdfe', '144f807280c34ad290493591a5cae4cb', 'build02', 'build01', '2021-11-09 22:13:35', '1', '2021-11-29 13:22:25', '241a8357372547c5a125a14e2bb22691', 'bdfaa627dd26472198cdca578f4fbd92', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '苹果投资', '海滨路321号', '李云', '13022223333', '线索 Remark5', '1', '1', null, '2021-11-04 22:13:35', '2021-11-29 13:22:25', '2021-12-29 13:22:25', '2021-11-03 22:13:35', '2021-11-09 22:13:35');
INSERT INTO `t_hint` VALUES ('52604242bbbd4ad49ea3ffa12899977a', 'apple线索2', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', '303630d9c77543499679881822bf972f', 'root', '2021-11-29 14:19:53', '1', '2021-11-29 17:55:47', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '天津市', '天津市', '和平区', 'apple线索公司', '第一街', '余先生', '18718417101', '', '1', '1', null, null, null, null, null, '2021-11-29 14:19:53');
INSERT INTO `t_hint` VALUES ('56429f75ce594ed5ae317b7e94209597', '导入线索33', '2ccdc29c2474407ca4bcb1b51d65cdfe', null, null, 'root', '2021-12-01 01:00:47', '1', null, 'b4a13f6fc3a94fe28af89087eb2803b2', '7e99329fc15a427da11502661d636724', '8869a155863b418a9f186e6581d14363,d38659a5453a45939ba8f3351420aa56', '福建', '厦门', '集美', '中建三局', '集美广场203号', '科龙电器', '13211112222', 'Normal', '3', '0', null, null, null, null, null, '2021-12-01 01:00:47');
INSERT INTO `t_hint` VALUES ('596d91ca893547a5a261a4d1456aff86', '测试新建', '158d2c57a27545c0a1eec33727b8033b', '34ba13fb6b9a409f85653ce8d1d79aa7', null, 'd8ccd736036048558db2b65c0a72256e', '2021-12-05 15:30:32', '0', null, 'c7b11a21bf6048618f16e59c486df7c0', '7e99329fc15a427da11502661d636724', 'baa094e0e5074822af76deb6ed447e6f,d38659a5453a45939ba8f3351420aa56,090e4d483b8a496cb4980e3cb6e7b79d', '辽宁省', '朝阳市', '朝阳县', '1111', '11111', '222', '1111', '12221', '2', '0', null, null, null, null, null, '2021-12-05 15:30:32');
INSERT INTO `t_hint` VALUES ('5c32f500ee1d4d8db9c4740611e8f365', '测试项目2 线索名称1', '158d2c57a27545c0a1eec33727b8033b', '10f57047f8914425a5b35af09b448865', '41a0adbe32314e93b6c1e45debc29879', 'root', '2021-12-03 15:09:59', '1', '2021-12-03 15:18:15', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', 'baa094e0e5074822af76deb6ed447e6f', '北京市', '北京市', '东城区', '测试有限公司', '测试地址', '测试客户2', '18718417110', '测试备注2', '1', '1', null, null, null, null, null, '2021-12-03 15:09:59');
INSERT INTO `t_hint` VALUES ('5ec80ebdafcf43fb86799aeb157b23ef', '线索2', '258d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', 'sale01', 'sale01', '2021-11-09 22:13:34', '1', '2021-11-21 11:09:25', '241a8357372547c5a125a14e2bb22691', '7e99329fc15a427da11502661d636724', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '苹果科技', '海滨路321号', '张锋', '13022223333', '线索 Remark2', '1', '1', null, '2021-11-05 22:13:35', '2021-11-04 22:13:35', '2021-12-04 22:13:35', '2021-11-03 22:13:35', '2021-11-09 22:13:34');
INSERT INTO `t_hint` VALUES ('6060863620fb42559253bd61fb35d706', '导入线索11', '158d2c57a27545c0a1eec33727b8033b', null, null, 'root', '2021-12-01 01:00:47', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '深圳', '罗湖', '中建一局', '宝安大道102号', '腾讯投资', '13011112222,010-222333', 'VIP', '3', '0', null, null, null, null, null, '2021-12-01 01:00:47');
INSERT INTO `t_hint` VALUES ('6a3ba613405a4c21afebf0ec062356ad', '测试线索', '158d2c57a27545c0a1eec33727b8033b', '283b26c29ac148b1bdea47f019151713', '7251e5eb4bf4456680e62fbb8eee8848', 'root', '2021-11-29 15:37:44', '1', '2021-12-01 23:18:31', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '山西省', '太原市', '小店区', '测试公司', '小店街', '余先生', '18718417110', '', '1', '1', null, null, null, null, null, '2021-11-29 15:37:44');
INSERT INTO `t_hint` VALUES ('6b47b8834c1346bb89d7a8d4b0dc22f8', '测试江新增', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', null, '2f964aaeaba84464b78e21726662f00e', '2021-12-02 05:50:58', '0', null, 'b4a13f6fc3a94fe28af89087eb2803b2', '7e99329fc15a427da11502661d636724', '8869a155863b418a9f186e6581d14363,d38659a5453a45939ba8f3351420aa56', '山西省', '长治市', '平顺县', '哈哈哈哈', '', '凌', '18825070658', '测试新增审批', '2', '0', null, null, null, null, null, '2021-12-02 05:50:58');
INSERT INTO `t_hint` VALUES ('77b2e5c46ec7473bb0d7bc2bd7027f90', '测试重新审核', '158d2c57a27545c0a1eec33727b8033b', '34ba13fb6b9a409f85653ce8d1d79aa7', '4a64237d151e426e9d5566e391d40d87', '4a64237d151e426e9d5566e391d40d87', '2021-12-04 15:53:11', '1', '2021-12-04 16:30:00', 'c7b11a21bf6048618f16e59c486df7c0', '7e99329fc15a427da11502661d636724', 'd38659a5453a45939ba8f3351420aa56,baa094e0e5074822af76deb6ed447e6f', '辽宁省', '本溪市', '本溪满族自治县', '1111', '这是详细地址啊', '1111', '18825070645', '测试一下重新提交', '2', '1', null, '2021-12-04 15:55:09', null, null, null, '2021-12-04 15:54:21');
INSERT INTO `t_hint` VALUES ('7febddc5122a4370aab0fa4327428b49', '导入线索3', '2ccdc29c2474407ca4bcb1b51d65cdfe', null, null, null, '2021-11-28 11:30:13', '1', null, 'b4a13f6fc3a94fe28af89087eb2803b2', '7e99329fc15a427da11502661d636724', '8869a155863b418a9f186e6581d14363,d38659a5453a45939ba8f3351420aa56', '福建', '厦门', '集美', '中建三局', '集美广场203号', '科龙电器', '13211112222', 'Normal', '3', '0', null, null, null, null, null, '2021-11-28 11:30:13');
INSERT INTO `t_hint` VALUES ('830a411bf6144497ba0c284f249355d5', '线索3', '7ccdc29c2474407ca4bcb1b51d65cdfe', '144f807280c34ad290493591a5cae4cb', 'sale02', 'sale01', '2021-11-09 22:13:34', '1', '2021-11-21 10:30:16', '241a8357372547c5a125a14e2bb22691', '7e99329fc15a427da11502661d636724', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '苹果科技', '海滨路321号', '李斯', '13022223333', '线索 Remark3', '1', '0', null, '2021-11-06 22:13:35', '2021-11-05 22:13:35', '2021-12-05 22:13:35', '2021-11-03 22:13:35', '2021-11-09 22:13:34');
INSERT INTO `t_hint` VALUES ('8495c8d82157416198ca7683f5a2b4c6', '测试线索2', '158d2c57a27545c0a1eec33727b8033b', '283b26c29ac148b1bdea47f019151713', '4d84038c476b4d0196de8ec00575d986', '7251e5eb4bf4456680e62fbb8eee8848', '2021-12-02 10:02:50', '2', '2021-12-03 10:21:09', 'b4a13f6fc3a94fe28af89087eb2803b2', '7e99329fc15a427da11502661d636724', '090e4d483b8a496cb4980e3cb6e7b79d', '北京市', '北京市', '崇文区', '测试有限公司', '', '余先生', '18718417110', '测试线索', '2', '1', null, '2021-12-02 10:08:13', '2021-12-03 10:21:09', '2022-01-02 10:21:09', null, '2021-12-02 10:02:50');
INSERT INTO `t_hint` VALUES ('86d0ed0aead145c596f63d30621e1e47', '测试审批通过', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', '95c2b370adc54162a41863b15c4aa1ab', '2f964aaeaba84464b78e21726662f00e', '2021-12-02 07:26:21', '1', '2021-12-03 14:51:12', 'b4a13f6fc3a94fe28af89087eb2803b2', '7e99329fc15a427da11502661d636724', '8869a155863b418a9f186e6581d14363,5165d8920a724443b59d6e8755def80a', '辽宁省', '营口市', '盖州市', '是是是', '', '少时诵诗书所', '2807068', '三生三世', '2', '1', null, '2021-12-02 07:29:59', null, null, null, '2021-12-02 07:26:21');
INSERT INTO `t_hint` VALUES ('9659314008f64a42870780eedb4c383d', '测试拒绝', '158d2c57a27545c0a1eec33727b8033b', '34ba13fb6b9a409f85653ce8d1d79aa7', null, '4a64237d151e426e9d5566e391d40d87', '2021-12-04 16:02:54', '2', null, 'b4a13f6fc3a94fe28af89087eb2803b2', '7e99329fc15a427da11502661d636724', 'd38659a5453a45939ba8f3351420aa56,baa094e0e5074822af76deb6ed447e6f,8869a155863b418a9f186e6581d14363', '内蒙古', '呼伦贝尔市', '额尔古纳市', '啊啊啊', '啊啊啊啊', '是是是', '三生三世', '少时诵诗书所', '2', '0', null, '2021-12-04 16:03:06', null, null, null, '2021-12-04 16:03:21');
INSERT INTO `t_hint` VALUES ('970d053bdf50420c9db8c04f831064fa', '导入线索33', '2ccdc29c2474407ca4bcb1b51d65cdfe', null, null, 'root', '2021-12-01 00:58:10', '1', null, 'b4a13f6fc3a94fe28af89087eb2803b2', '7e99329fc15a427da11502661d636724', '8869a155863b418a9f186e6581d14363,d38659a5453a45939ba8f3351420aa56', '福建', '厦门', '集美', '中建三局', '集美广场203号', '科龙电器', '13211112222', 'Normal', '3', '0', null, null, null, null, null, '2021-12-01 00:58:10');
INSERT INTO `t_hint` VALUES ('9a60cd84117b4ffe87dc507f8080314e', '线索100德国 new', '258d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', 'sale01', 'sale02', '2021-11-19 13:06:04', '1', '2021-11-21 10:30:18', 'b4a13f6fc3a94fe28af89087eb2803b2', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省 new', '深圳市 bew', '罗湖区', '甲骨文Oracle new', '银河村秋天组', '张先生', '13022334455', '高级客户线索', '1', '0', null, '2021-11-19 13:06:04', '2022-01-06 00:00:00', '2022-02-08 00:00:00', '2021-11-03 22:13:35', '2021-11-20 07:41:27');
INSERT INTO `t_hint` VALUES ('9f4f2e868c4c4da2a10aebe58fa1c6fc', '测试项目2线索名称3', '158d2c57a27545c0a1eec33727b8033b', '10f57047f8914425a5b35af09b448865', 'dd2f4fb9a0894e3f9d8c4096e1350a7e', 'root', '2021-12-03 15:12:34', '1', '2021-12-03 18:25:08', 'b4a13f6fc3a94fe28af89087eb2803b2', '5c3595e09acc447ea8d30c3729990308', '8869a155863b418a9f186e6581d14363,090e4d483b8a496cb4980e3cb6e7b79d,d38659a5453a45939ba8f3351420aa56', '北京市', '北京市', '西城区', '测试3有限公司', '测试地址', '测试客户3', '18718417110', '测试备注', '1', '1', null, null, '2021-12-03 00:00:00', '2022-01-03 00:00:00', null, '2021-12-04 15:43:46');
INSERT INTO `t_hint` VALUES ('9fad7faa6ca54445ab1cf81c67ba2edb', '线索6', '7ccdc29c2474407ca4bcb1b51d65cdfe', '144f807280c34ad290493591a5cae4cb', null, 'sale01', '2021-11-09 22:13:35', '2', null, '241a8357372547c5a125a14e2bb22691', 'bdfaa627dd26472198cdca578f4fbd92', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '凌云科技', '海滨路321号', '张三', '13022223333', '线索 Remark6', '1', '0', null, '2021-11-19 13:06:04', '2021-11-07 22:13:35', '2021-12-07 22:13:35', '2021-11-03 22:13:35', '2021-11-09 22:13:35');
INSERT INTO `t_hint` VALUES ('a0899288a1084b06810ab63ae1221198', '导入线索1', '258d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', 'sale01', 'build01', '2021-11-21 09:47:27', '1', '2021-11-21 11:09:22', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '深圳', '罗湖', '中建一局', '宝安大道102号', '腾讯投资', '13011112222,010-222333', 'VIP', '3', '0', null, null, null, null, null, '2021-11-21 09:47:27');
INSERT INTO `t_hint` VALUES ('a0991fd630c4410ba7dab5f886c45fd2', '导入线索2', '158d2c57a27545c0a1eec33727b8033b', null, null, null, '2021-11-28 11:34:35', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '广州', '天河', '中建二局', '天河大道101号', '银河证券', '13111112222', 'VVIP', '3', '0', null, null, null, null, null, '2021-11-28 11:34:35');
INSERT INTO `t_hint` VALUES ('a0eadc62596b495baa1b4d81f9121500', '测试线索5', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', null, '95c2b370adc54162a41863b15c4aa1ab', '2021-12-03 14:53:18', '0', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '8869a155863b418a9f186e6581d14363', '北京市', '北京市', '朝阳区', '测试公司名称5', '测试地址', '测试客户3', '18718417110', '测试备注备注', '2', '0', null, null, null, null, null, '2021-12-03 14:53:18');
INSERT INTO `t_hint` VALUES ('a5a5388b18a2499e9d326b73685a2ed0', 'apple线索', '158d2c57a27545c0a1eec33727b8033b', '144f807280c34ad290493591a5cae4cb', '303630d9c77543499679881822bf972f', 'root', '2021-11-29 14:15:12', '1', '2021-11-29 17:55:48', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '北京市', '北京市', '东城区', 'apple科技有限公司', '第一街', '余先生', '18718417101', '', '1', '1', null, null, null, null, null, '2021-11-29 14:15:12');
INSERT INTO `t_hint` VALUES ('a5c541def46a43e2988ced496e0e10f3', '测试项目2 线索名称2', '158d2c57a27545c0a1eec33727b8033b', '10f57047f8914425a5b35af09b448865', '11cf48dd5f4d45258c8bbc92e9f9f906', 'root', '2021-12-03 15:11:22', '1', '2021-12-05 18:53:11', '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '8869a155863b418a9f186e6581d14363', '天津市', '天津市', '和平区', '测试有限公司2', '测试详细地址2', '客户2', '18718417110', '测试备注2', '1', '1', null, null, null, null, null, '2021-12-03 15:11:22');
INSERT INTO `t_hint` VALUES ('ab49bf0fe6044c08ac1f90e9616a0efe', '导入线索1', '158d2c57a27545c0a1eec33727b8033b', null, null, null, '2021-11-28 11:34:35', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '深圳', '罗湖', '中建一局', '宝安大道102号', '腾讯投资', '13011112222,010-222333', 'VIP', '3', '0', null, null, null, null, null, '2021-11-28 11:34:35');
INSERT INTO `t_hint` VALUES ('aff3d9bca1954b60978da7a8a55b7fb2', '测试项目线索1', '158d2c57a27545c0a1eec33727b8033b', '54ab33f84167478aaabd549523ccc5cf', 'cf03358742d544c7974021bd960d090f', 'root', '2021-12-03 10:26:09', '1', '2021-12-03 11:12:04', 'b4a13f6fc3a94fe28af89087eb2803b2', '5c3595e09acc447ea8d30c3729990308', '8869a155863b418a9f186e6581d14363', '广东省', '广州市', '越秀区', '测试项目有限公司', '测试详细地址', '测试项目客户1', '18718417110', '测试备注', '1', '1', null, null, null, null, null, '2021-12-03 10:26:09');
INSERT INTO `t_hint` VALUES ('b178bd4d1f324c519bd1a657cee4466b', '', '158d2c57a27545c0a1eec33727b8033b', '10f57047f8914425a5b35af09b448865', null, '41a0adbe32314e93b6c1e45debc29879', '2021-12-03 15:30:20', '0', null, '', '', '', '', '', '', '', '', '', '', '', '2', '0', null, null, null, null, null, '2021-12-03 15:30:20');
INSERT INTO `t_hint` VALUES ('b2e2b024962d410bb73beaee107eeed8', '测试新增', '308317b4ae304175bee1b9eae48f808f', '283b26c29ac148b1bdea47f019151713', 'sale01', 'root', '2021-11-29 16:17:08', '1', null, 'b4a13f6fc3a94fe28af89087eb2803b2', '7e99329fc15a427da11502661d636724', 'baa094e0e5074822af76deb6ed447e6f,e49856fbd63042178619a3e7cd58b0c1', '河北省', '唐山市', '路北区', '2332', '1212121', '1111', '111111', '33333', '1', '0', null, null, null, null, null, '2021-11-29 16:17:08');
INSERT INTO `t_hint` VALUES ('b5ec7bec46cc48fdbb482fa840c66615', '线索9', 'e08317b4ae304175bee1b9eae48f808f', '144f807280c34ad290493591a5cae4cb', 'sale01', 'sale01', '2021-11-09 22:13:35', '0', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '国际电话', '海滨路321号', '张三', '13022223333', '线索 Remark9', '1', '0', null, '2021-11-19 13:06:04', '2021-11-08 22:13:35', '2021-12-08 22:13:35', '2021-11-03 22:13:35', '2021-11-09 22:13:35');
INSERT INTO `t_hint` VALUES ('b6fe7ba28b0f438685e02440519ed1d4', '导入线索1', '158d2c57a27545c0a1eec33727b8033b', null, null, null, '2021-11-28 11:30:13', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '深圳', '罗湖', '中建一局', '宝安大道102号', '腾讯投资', '13011112222,010-222333', 'VIP', '3', '0', null, null, null, null, null, '2021-11-28 11:30:13');
INSERT INTO `t_hint` VALUES ('c01332a020784c2abf54005d4eece868', '导入线索33', '2ccdc29c2474407ca4bcb1b51d65cdfe', null, null, 'root', '2021-12-01 00:57:02', '1', null, 'b4a13f6fc3a94fe28af89087eb2803b2', '7e99329fc15a427da11502661d636724', '8869a155863b418a9f186e6581d14363,d38659a5453a45939ba8f3351420aa56', '福建', '厦门', '集美', '中建三局', '集美广场203号', '科龙电器', '13211112222', 'Normal', '3', '0', null, null, null, null, null, '2021-12-01 00:57:02');
INSERT INTO `t_hint` VALUES ('c3b856dfdcd540e68b32d455884bd2dc', '导入线索22', '158d2c57a27545c0a1eec33727b8033b', null, null, 'root', '2021-12-01 00:58:10', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '广州', '天河', '中建二局', '天河大道101号', '银河证券', '13111112222', 'VVIP', '3', '0', null, null, null, null, null, '2021-12-01 00:58:10');
INSERT INTO `t_hint` VALUES ('d4f49155637c44648f434c7917f02f8e', '测试线索22', '158d2c57a27545c0a1eec33727b8033b', '283b26c29ac148b1bdea47f019151713', null, 'root', '2021-12-03 10:19:00', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '北京市', '北京市', '东城区', '测试有限公司', '测试北京地址', '测试客户2', '18718417110', '测试备注', '1', '0', null, null, null, null, null, '2021-12-03 10:19:00');
INSERT INTO `t_hint` VALUES ('e613f51e1fa545be82847fe531f985a9', '测试新建', '158d2c57a27545c0a1eec33727b8033b', '283b26c29ac148b1bdea47f019151713', 'sale02', 'root', '2021-11-29 15:00:22', '1', null, 'b4a13f6fc3a94fe28af89087eb2803b2', '7e99329fc15a427da11502661d636724', '090e4d483b8a496cb4980e3cb6e7b79d,8869a155863b418a9f186e6581d14363,d38659a5453a45939ba8f3351420aa56', '北京市', '北京市', '东城区', '测试', '撒啊啊啊啊啊', '江', '18825070645', '', '1', '0', null, null, null, null, null, '2021-11-29 15:00:22');
INSERT INTO `t_hint` VALUES ('e776626491004ee7883f7ec03aa726ce', '广东科学新10', '258d2c57a27545c0a1eec33727b8033b', '283b26c29ac148b1bdea47f019151713', null, 'sale01', '2021-11-09 22:13:34', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '甲骨文Oracle new', '海滨路321号', '张三', '13022223333', '线索 Remark0', '1', '0', null, '2021-11-19 13:06:04', '2021-11-09 22:13:35', '2021-12-09 22:13:35', '2021-11-03 22:13:35', '2021-11-09 22:23:58');
INSERT INTO `t_hint` VALUES ('e79820c2a5004f71ae2bf3d069a0f90e', '线索7', '7ccdc29c2474407ca4bcb1b51d65cdfe', '283b26c29ac148b1bdea47f019151713', null, 'sale01', '2021-11-09 22:13:35', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东省', '深圳市', '罗湖区', '风云资本', '海滨路321号', '张三', '13022223333', '线索 Remark7', '1', '0', null, '2021-11-19 13:06:04', '2021-11-10 22:13:35', '2021-12-10 22:13:35', '2021-11-03 22:13:35', '2021-11-09 22:13:35');
INSERT INTO `t_hint` VALUES ('f1984eab1ba04483b8daae8a1865837a', '导入线索11', '158d2c57a27545c0a1eec33727b8033b', null, null, 'root', '2021-12-01 00:59:05', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '广东', '深圳', '罗湖', '中建一局', '宝安大道102号', '腾讯投资', '13011112222,010-222333', 'VIP', '3', '0', null, null, null, null, null, '2021-12-01 00:59:05');
INSERT INTO `t_hint` VALUES ('f2f89a8b22d54e94b27b4b6cd7a37613', '导入线索33', '2ccdc29c2474407ca4bcb1b51d65cdfe', null, null, 'root', '2021-12-01 00:59:05', '1', null, 'b4a13f6fc3a94fe28af89087eb2803b2', '7e99329fc15a427da11502661d636724', '8869a155863b418a9f186e6581d14363,d38659a5453a45939ba8f3351420aa56', '福建', '厦门', '集美', '中建三局', '集美广场203号', '科龙电器', '13211112222', 'Normal', '3', '0', null, null, null, null, null, '2021-12-01 00:59:05');
INSERT INTO `t_hint` VALUES ('fc20f14373ed4073b8ee6eb3ba4bdb7b', '招商123', '158d2c57a27545c0a1eec33727b8033b', '22', null, 'root', '2021-11-29 18:17:35', '1', null, '241a8357372547c5a125a14e2bb22691', '5c3595e09acc447ea8d30c3729990308', '090e4d483b8a496cb4980e3cb6e7b79d', '天津市', '天津市', '塘沽区', '测试123', '23日4 ', '测试123', '12344', '', '1', '0', null, null, null, null, null, '2021-11-29 18:17:35');

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
INSERT INTO `t_hint_image` VALUES ('1', '0a1d62dc51d14168ae616ad7224e4f30', 'aa.jpg', null);
INSERT INTO `t_hint_image` VALUES ('153bbe0bd5bc45879e3e9e229dbbe29e', '9659314008f64a42870780eedb4c383d', '20211204/8b36e724bde24af790293fcd4546044e.png', null);
INSERT INTO `t_hint_image` VALUES ('2', '0a1d62dc51d14168ae616ad7224e4f30', 'bb.png', null);
INSERT INTO `t_hint_image` VALUES ('2e594548f80a49d88747ce164760bce0', '6b47b8834c1346bb89d7a8d4b0dc22f8', '20211202/ef61f09c489748c29fb885de3fe9a04a.png', null);
INSERT INTO `t_hint_image` VALUES ('62e2282770204c60835dac209a64a4ac', '77b2e5c46ec7473bb0d7bc2bd7027f90', '20211204/9302d522cb124c2382422d3f5781cab9.png', null);
INSERT INTO `t_hint_image` VALUES ('689168689e6b4a24ba66fc166e977351', 'b178bd4d1f324c519bd1a657cee4466b', '20211203/e65f580fd39c47d98d89b93f3d3a21a1.jpg', null);
INSERT INTO `t_hint_image` VALUES ('6f7005d4f0754f5580a2ed4f1f430756', '9659314008f64a42870780eedb4c383d', '20211204/bf436dd9d0f3422b871e1373aa952f38.png', null);
INSERT INTO `t_hint_image` VALUES ('76bdbaefe3b94136b1f782abb8fbfa82', '86d0ed0aead145c596f63d30621e1e47', '20211202/5cd46d210123448db12039778f05419c.png', null);
INSERT INTO `t_hint_image` VALUES ('7aa03134281647c4932b7be83bec73bc', '77b2e5c46ec7473bb0d7bc2bd7027f90', '20211204/7f8601469f3f49beb049b48378fca75e.png', null);
INSERT INTO `t_hint_image` VALUES ('815d20af7c3e4d119ff3a2aaa6750ed8', '6b47b8834c1346bb89d7a8d4b0dc22f8', '20211202/877294a0f194464cb0b4f182cfa8e5ef.png', null);
INSERT INTO `t_hint_image` VALUES ('a170765d5e5e4e59b069f9006efcd429', '596d91ca893547a5a261a4d1456aff86', '20211205/a4014314458342e7aedc138c0f7e5b81.png', null);
INSERT INTO `t_hint_image` VALUES ('f0913911e9da47ba9b887706945d4a9f', '8495c8d82157416198ca7683f5a2b4c6', '20211202/560635d2447d4739b7ecb82ce316285a.jpg', null);

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
-- Records of t_hint_record
-- ----------------------------
INSERT INTO `t_hint_record` VALUES ('111', '4315847db8cc4bdb85370c19f1cdea59', '144f807280c34ad290493591a5cae4cb', 'area01', '很好', '2021-11-20 20:18:27');
INSERT INTO `t_hint_record` VALUES ('205420ce70e245ac8e9b3381d301223f', 'a5c541def46a43e2988ced496e0e10f3', '10f57047f8914425a5b35af09b448865', '11cf48dd5f4d45258c8bbc92e9f9f906', '今生今世几年时间', '2021-12-05 18:53:47');
INSERT INTO `t_hint_record` VALUES ('222', '4315847db8cc4bdb85370c19f1cdea59', '144f807280c34ad290493591a5cae4cb', 'build02', '完美', '2021-11-20 20:18:28');
INSERT INTO `t_hint_record` VALUES ('333', '44957deb0e9c4936beb2244ea464504e', '144f807280c34ad290493591a5cae4cb', 'sale01', '继续继续', '2021-11-21 20:18:30');
INSERT INTO `t_hint_record` VALUES ('3a6607ff1cba45699f79456afe9c4b3d', 'a5c541def46a43e2988ced496e0e10f3', '10f57047f8914425a5b35af09b448865', '11cf48dd5f4d45258c8bbc92e9f9f906', '', '2021-12-05 18:53:38');
INSERT INTO `t_hint_record` VALUES ('444', '44957deb0e9c4936beb2244ea464504e', '144f807280c34ad290493591a5cae4cb', 'sale02', '待跟进', '2021-11-20 20:18:35');
INSERT INTO `t_hint_record` VALUES ('4fec9b5ab04640dc84254ada72355e46', '2ff9fa79a0964816b21a16360b4cab4b', '144f807280c34ad290493591a5cae4cb', '2f964aaeaba84464b78e21726662f00e', '嘻嘻嘻', '2021-12-01 21:23:18');
INSERT INTO `t_hint_record` VALUES ('555', '44957deb0e9c4936beb2244ea464504e', '144f807280c34ad290493591a5cae4cb', 'sale01', 'GOOD', '2021-11-29 03:12:33');
INSERT INTO `t_hint_record` VALUES ('63db79b3f8fc4ac99b3c0273da96ddf2', '77b2e5c46ec7473bb0d7bc2bd7027f90', '34ba13fb6b9a409f85653ce8d1d79aa7', '4a64237d151e426e9d5566e391d40d87', '这是我的跟进', '2021-12-04 16:30:10');
INSERT INTO `t_hint_record` VALUES ('940da0524b334ca5b9d5771ff29808a5', '6a3ba613405a4c21afebf0ec062356ad', '283b26c29ac148b1bdea47f019151713', '7251e5eb4bf4456680e62fbb8eee8848', '测试跟进', '2021-12-02 00:09:29');
INSERT INTO `t_hint_record` VALUES ('bd1d52524e31404d8a95cbd8a57ce49f', 'aff3d9bca1954b60978da7a8a55b7fb2', '54ab33f84167478aaabd549523ccc5cf', 'cf03358742d544c7974021bd960d090f', '跟进测试', '2021-12-03 11:12:31');
INSERT INTO `t_hint_record` VALUES ('bf3bcdb92534437aab2e86fb371338ab', null, '144f807280c34ad290493591a5cae4cb', '2f964aaeaba84464b78e21726662f00e', '哈哈哈哈哈', '2021-12-01 20:59:03');
INSERT INTO `t_hint_record` VALUES ('c1ce716b27704f99808484f0634abdc2', '5c32f500ee1d4d8db9c4740611e8f365', '10f57047f8914425a5b35af09b448865', '41a0adbe32314e93b6c1e45debc29879', '跟进测试内容', '2021-12-03 15:18:46');
INSERT INTO `t_hint_record` VALUES ('d95bc40ddb41407a8ac7ec5c35cc965c', 'a5c541def46a43e2988ced496e0e10f3', '10f57047f8914425a5b35af09b448865', '11cf48dd5f4d45258c8bbc92e9f9f906', '细菌学家记得回复', '2021-12-05 18:53:51');
INSERT INTO `t_hint_record` VALUES ('e6a92f52943d45408b7b6cb4d7f2cf61', '2ff9fa79a0964816b21a16360b4cab4b', '144f807280c34ad290493591a5cae4cb', '2f964aaeaba84464b78e21726662f00e', '试一下咯', '2021-12-01 23:43:40');
INSERT INTO `t_hint_record` VALUES ('ef40c2926e4442f7b14d73e8f2fcdd98', null, '144f807280c34ad290493591a5cae4cb', '2f964aaeaba84464b78e21726662f00e', '好但事实上', '2021-12-01 20:59:33');
INSERT INTO `t_hint_record` VALUES ('f37d9bb9fccf4d6dbfc06523d176b49c', '2ff9fa79a0964816b21a16360b4cab4b', '144f807280c34ad290493591a5cae4cb', '2f964aaeaba84464b78e21726662f00e', '哈哈哈哈哈', '2021-12-01 21:09:51');
INSERT INTO `t_hint_record` VALUES ('fafc27a5d1d34fd58a1ca5ab28fb5b43', 'a5c541def46a43e2988ced496e0e10f3', '10f57047f8914425a5b35af09b448865', '11cf48dd5f4d45258c8bbc92e9f9f906', '就是今生今世', '2021-12-05 18:53:42');

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
INSERT INTO `t_invite_code` VALUES ('01f6cc79', '11', '33bab25a8a7941459350217563573b23', 'oE47u0FPCH0mkweVdngF5jCj8B_0', '1', '2021-11-29 14:53:57', '2021-11-27 21:37:20');
INSERT INTO `t_invite_code` VALUES ('02718cd2', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', 'oE47u0FPCH0mkweVdngF5jCj8B_0', '1', '2021-11-27 23:40:49', '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('04d00806', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', 'oE47u0AyoBUevmcFhN5TEq3d2eVE', '1', '2021-11-29 15:07:43', '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('062f8e03', '34ba13fb6b9a409f85653ce8d1d79aa7', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-12-04 10:49:17');
INSERT INTO `t_invite_code` VALUES ('078b66c0', '11', '33bab25a8a7941459350217563573b23', 'oE47u0FPCH0mkweVdngF5jCj8B_0', '1', '2021-12-01 03:16:35', '2021-11-29 15:02:23');
INSERT INTO `t_invite_code` VALUES ('0c0ca9af', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '2', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('0ca9af2b', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', 'oE47u0FPCH0mkweVdngF5jCj8B_0', '1', '2021-12-02 07:36:02', '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('0d684a0a', '54ab33f84167478aaabd549523ccc5cf', '33bab25a8a7941459350217563573b23', 'oWJfU4scii-vAq4oNzhaM70gt41k', '1', '2021-12-03 11:10:01', '2021-12-03 10:32:19');
INSERT INTO `t_invite_code` VALUES ('11b739d8', '144f807280c34ad290493591a5cae4cb', '33bab25a8a7941459350217563573b23', 'oE47u0FPCH0mkweVdngF5jCj8B_0', '1', '2021-12-01 20:35:43', '2021-12-01 20:31:25');
INSERT INTO `t_invite_code` VALUES ('1424fcf8', '144f807280c34ad290493591a5cae4cb', '16803e86d0c5472686641365483f2d17', 'oE47u0M2MjjTD2_xOHcHJjFWJsoU', '1', '2021-11-29 17:55:11', '2021-11-29 17:31:28');
INSERT INTO `t_invite_code` VALUES ('18cd2c75', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('19d78e58', '34ba13fb6b9a409f85653ce8d1d79aa7', '33bab25a8a7941459350217563573b23', 'oWJfU4kcRjDMHtRWN6XplFOOPu1w', '1', '2021-12-05 00:33:41', '2021-12-04 15:51:43');
INSERT INTO `t_invite_code` VALUES ('1f6cc791', '11', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-11-27 21:37:20');
INSERT INTO `t_invite_code` VALUES ('2718cd2c', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', 'oE47u0AyoBUevmcFhN5TEq3d2eVE', '1', '2021-11-29 14:33:41', '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('2b42d69b', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '2', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('2d69b027', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', 'oWJfU4lz3VLc_TQZiyXcskuJ2mGk', '1', '2021-12-03 10:10:47', '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('42d69b02', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '2', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('455205', '144f807280c34ad290493591a5cae4cb', '29c563e9b36a4d84bf4d7dfa0450dbf7', null, '0', null, '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4656184b', '10f57047f8914425a5b35af09b448865', '33bab25a8a7941459350217563573b23', 'oWJfU4scii-vAq4oNzhaM70gt41k', '1', '2021-12-03 15:18:08', '2021-12-03 15:14:08');
INSERT INTO `t_invite_code` VALUES ('4a7ea1', '144f807280c34ad290493591a5cae4cb', '33bab25a8a7941459350217563573b23', 'oWJfU4scii-vAq4oNzhaM70gt41k', '1', '2021-12-03 10:44:11', '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4a7ea2', '144f807280c34ad290493591a5cae4cb', '33bab25a8a7941459350217563573b23', 'oWJfU4scii-vAq4oNzhaM70gt41k', '1', '2021-12-03 10:58:04', '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4a7ea3', '144f807280c34ad290493591a5cae4cb', '33bab25a8a7941459350217563573b23', '', '1', '2021-11-23 20:07:49', '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4a7ea4', '144f807280c34ad290493591a5cae4cb', '33bab25a8a7941459350217563573b23', 'oE47u0H7rVxv9_u3Wps3E0G8rUms', '1', '2021-11-29 17:55:29', '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4a7ea5', '144f807280c34ad290493591a5cae4cb', '33bab25a8a7941459350217563573b23', null, '1', null, '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4a7ea6', '144f807280c34ad290493591a5cae4cb', '33bab25a8a7941459350217563573b23', 'oWJfU4scii-vAq4oNzhaM70gt41k', '1', '2021-12-03 14:50:46', '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('4d00806b', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('4d8537c4', '283b26c29ac148b1bdea47f019151713', '33bab25a8a7941459350217563573b23', 'oWJfU4scii-vAq4oNzhaM70gt41k', '1', '2021-12-02 14:19:11', '2021-12-02 14:18:03');
INSERT INTO `t_invite_code` VALUES ('50d684a0', '54ab33f84167478aaabd549523ccc5cf', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-12-03 10:32:19');
INSERT INTO `t_invite_code` VALUES ('54411d60', '10f57047f8914425a5b35af09b448865', '16803e86d0c5472686641365483f2d17', 'oWJfU4lWE311GSErLv_6RDDZR4M4', '1', '2021-12-03 18:23:37', '2021-12-03 15:13:32');
INSERT INTO `t_invite_code` VALUES ('56184be6', '10f57047f8914425a5b35af09b448865', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-12-03 15:14:08');
INSERT INTO `t_invite_code` VALUES ('5c6e24e3', '34ba13fb6b9a409f85653ce8d1d79aa7', '29c563e9b36a4d84bf4d7dfa0450dbf7', null, '0', null, '2021-12-04 10:50:15');
INSERT INTO `t_invite_code` VALUES ('601f6cc7', '11', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-11-27 21:37:20');
INSERT INTO `t_invite_code` VALUES ('6184be66', '10f57047f8914425a5b35af09b448865', '33bab25a8a7941459350217563573b23', 'oWJfU4kMa_Z_eGi-t3-PQJsi7tS8', '1', '2021-12-05 18:52:58', '2021-12-03 15:14:08');
INSERT INTO `t_invite_code` VALUES ('656184be', '10f57047f8914425a5b35af09b448865', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-12-03 15:14:08');
INSERT INTO `t_invite_code` VALUES ('684a0a6b', '54ab33f84167478aaabd549523ccc5cf', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-12-03 10:32:19');
INSERT INTO `t_invite_code` VALUES ('69b02718', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', 'oE47u0CWnZR4krOOkQ0KDaqEiAlo', '1', '2021-11-29 15:15:23', '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('6cc791b4', '11', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-11-27 21:37:20');
INSERT INTO `t_invite_code` VALUES ('6e1a12ad', '34ba13fb6b9a409f85653ce8d1d79aa7', '33bab25a8a7941459350217563573b23', 'oWJfU4lhL0J7_4xpg7ibBlz4zqj0', '1', '2021-12-04 12:09:08', '2021-12-04 10:53:35');
INSERT INTO `t_invite_code` VALUES ('718cd2c7', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('7601f6cc', '11', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-11-27 21:37:20');
INSERT INTO `t_invite_code` VALUES ('791b44c5', '11', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-11-27 21:37:20');
INSERT INTO `t_invite_code` VALUES ('7dd991cf', '54ab33f84167478aaabd549523ccc5cf', '16803e86d0c5472686641365483f2d17', 'oWJfU4kcRjDMHtRWN6XplFOOPu1w', '1', '2021-12-03 13:06:53', '2021-12-03 10:32:00');
INSERT INTO `t_invite_code` VALUES ('814972', '144f807280c34ad290493591a5cae4cb', '412f6c8db4d94ac3a85249076717b809', null, '1', null, '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('84a0a6be', '54ab33f84167478aaabd549523ccc5cf', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-12-03 10:32:19');
INSERT INTO `t_invite_code` VALUES ('8cd2c750', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('9062f8e0', '34ba13fb6b9a409f85653ce8d1d79aa7', '16803e86d0c5472686641365483f2d17', 'oWJfU4kcRjDMHtRWN6XplFOOPu1w', '1', '2021-12-04 10:54:54', '2021-12-04 10:49:17');
INSERT INTO `t_invite_code` VALUES ('91b44c5b', '11', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-11-27 21:37:20');
INSERT INTO `t_invite_code` VALUES ('91cf4c0c', '54ab33f84167478aaabd549523ccc5cf', '16803e86d0c5472686641365483f2d17', 'oWJfU4lz3VLc_TQZiyXcskuJ2mGk', '1', '2021-12-03 10:33:55', '2021-12-03 10:32:00');
INSERT INTO `t_invite_code` VALUES ('991cf4c0', '54ab33f84167478aaabd549523ccc5cf', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-12-03 10:32:00');
INSERT INTO `t_invite_code` VALUES ('9af2b42d', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('9b02718c', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('9d78e58c', '34ba13fb6b9a409f85653ce8d1d79aa7', '33bab25a8a7941459350217563573b23', 'oWJfU4kcRjDMHtRWN6XplFOOPu1w', '1', '2021-12-04 15:52:06', '2021-12-04 15:51:43');
INSERT INTO `t_invite_code` VALUES ('a9af2b42', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('af2b42d6', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('b02718cd', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('b078b66c', '11', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-11-29 15:02:23');
INSERT INTO `t_invite_code` VALUES ('b42d69b0', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('c0ca9af2', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('c11b739d', '144f807280c34ad290493591a5cae4cb', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-12-01 20:31:25');
INSERT INTO `t_invite_code` VALUES ('c247c2ac', '54ab33f84167478aaabd549523ccc5cf', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-12-03 13:06:13');
INSERT INTO `t_invite_code` VALUES ('c6e24e37', '34ba13fb6b9a409f85653ce8d1d79aa7', '29c563e9b36a4d84bf4d7dfa0450dbf7', null, '0', null, '2021-12-04 10:50:15');
INSERT INTO `t_invite_code` VALUES ('c791b44c', '11', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-11-27 21:37:20');
INSERT INTO `t_invite_code` VALUES ('ca9af2b4', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('cc247c2a', '54ab33f84167478aaabd549523ccc5cf', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-12-03 13:06:13');
INSERT INTO `t_invite_code` VALUES ('cc791b44', '11', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-11-27 21:37:20');
INSERT INTO `t_invite_code` VALUES ('cd2c7500', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('d0c0ca9a', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('d2c75009', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('d4d8537c', '283b26c29ac148b1bdea47f019151713', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-12-02 14:18:03');
INSERT INTO `t_invite_code` VALUES ('d684a0a6', '54ab33f84167478aaabd549523ccc5cf', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-12-03 10:32:19');
INSERT INTO `t_invite_code` VALUES ('d69b0271', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('d8537c49', '283b26c29ac148b1bdea47f019151713', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-12-02 14:18:03');
INSERT INTO `t_invite_code` VALUES ('d991cf4c', '54ab33f84167478aaabd549523ccc5cf', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-12-03 10:32:00');
INSERT INTO `t_invite_code` VALUES ('dd991cf4', '54ab33f84167478aaabd549523ccc5cf', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-12-03 10:32:00');
INSERT INTO `t_invite_code` VALUES ('e3a00f', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-10-24 22:32:02');
INSERT INTO `t_invite_code` VALUES ('f2b42d69', '283b26c29ac148b1bdea47f019151713', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
INSERT INTO `t_invite_code` VALUES ('f4707911', '283b26c29ac148b1bdea47f019151713', '33bab25a8a7941459350217563573b23', 'oE47u0GxXuAkLWHlR1YZR6IGibY8', '1', '2021-11-29 15:33:45', '2021-11-29 15:14:55');
INSERT INTO `t_invite_code` VALUES ('f6cc791b', '11', '33bab25a8a7941459350217563573b23', null, '0', null, '2021-11-27 21:37:20');
INSERT INTO `t_invite_code` VALUES ('fcfd3311', '10f57047f8914425a5b35af09b448865', '29c563e9b36a4d84bf4d7dfa0450dbf7', null, '0', null, '2021-12-03 15:14:15');
INSERT INTO `t_invite_code` VALUES ('fd0c0ca9', '144f807280c34ad290493591a5cae4cb', '16803e86d0c5472686641365483f2d17', null, '0', null, '2021-11-24 22:24:32');
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
  PRIMARY KEY (`project_id`),
  KEY `idx_project_admin_id` (`project_admin_id`) USING BTREE,
  KEY `idx_project_area_admin_id` (`project_area_admin_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project
-- ----------------------------
INSERT INTO `t_project` VALUES ('10f57047f8914425a5b35af09b448865', '测试项目2', '', 'dd2f4fb9a0894e3f9d8c4096e1350a7e', '1', '测试项目2', '2021-12-03 15:08:38', '2021-12-05 18:52:58');
INSERT INTO `t_project` VALUES ('144f807280c34ad290493591a5cae4cb', '国际会展中心项目1', 'proj02', '3195d91d8026469199032d7165cb11d9', '1', '国际会展中心项目 Desc', '2021-10-24 22:22:31', '2021-12-03 14:50:46');
INSERT INTO `t_project` VALUES ('283b26c29ac148b1bdea47f019151713', '国际智慧小区项目2', null, '914e305f0aa144fab74a399a340cfae1', '1', null, '2021-10-24 22:22:31', null);
INSERT INTO `t_project` VALUES ('34ba13fb6b9a409f85653ce8d1d79aa7', '测试新建项目管理员', '', '7c2e578a1ff246e6a5333e4bcfd8a67b', '1', '三生三世', '2021-12-04 10:48:52', '2021-12-05 00:33:41');
INSERT INTO `t_project` VALUES ('36494b53277c4e25bf9ca2e0e23e4e38', '未来城市项目3', null, 'area01', '1', null, '2021-10-24 22:22:31', null);
INSERT INTO `t_project` VALUES ('4ed58177342d4ece9267a8ee096e8aa5', '未来都市项目4', null, 'area01', '1', '未来都市项目 Desc', '2021-11-17 06:40:27', null);
INSERT INTO `t_project` VALUES ('54ab33f84167478aaabd549523ccc5cf', '测试项目1', 'proj01', '180da917a8454a9abf244f7681225649', '1', '测试项目1', '2021-12-03 10:23:12', '2021-12-03 13:06:53');
INSERT INTO `t_project` VALUES ('5e8b00909461477880bf78ac4efa3e68', '城市智慧国际交通项目5', null, 'area02', '1', '城市智慧交通项目 Desc', '2021-11-19 00:02:46', null);
INSERT INTO `t_project` VALUES ('6892ef8d8cfb4eb38082e34ce8d678e3', '测试线索Project', null, '914e305f0aa144fab74a399a340cfae1', '1', '', '2021-11-29 14:22:39', '2021-11-29 14:22:39');
INSERT INTO `t_project` VALUES ('6e8b00909461477880bf78ac4efa3e6', '城市园林项目6', null, 'area02', '1', null, '2021-11-19 00:02:46', '2021-11-23 13:15:34');
INSERT INTO `t_project` VALUES ('dc4e28347c9d4216ae2c5eb936853528', 'Project001', null, '914e305f0aa144fab74a399a340cfae1', '1', '', '2021-11-28 06:21:47', '2021-11-28 06:21:47');

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
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('16803e86d0c5472686641365483f2d17', '区域管理员', '1', '158d2c57a27545c0a1eec33727b8033b', null, '2021-10-24 22:11:51');
INSERT INTO `t_role` VALUES ('29c563e9b36a4d84bf4d7dfa0450dbf7', '项目经理', '2', '158d2c57a27545c0a1eec33727b8033b', null, '2021-10-24 22:11:52');
INSERT INTO `t_role` VALUES ('33bab25a8a7941459350217563573b23', '一级销售员', '3', '158d2c57a27545c0a1eec33727b8033b', null, '2021-10-24 22:11:53');
INSERT INTO `t_role` VALUES ('3a97a2fb62c64217b25e933b49dea9a5', '1111', '4', null, '22222', '2021-11-27 19:48:41');
INSERT INTO `t_role` VALUES ('412f6c8db4d94ac3a85249076717b809', '一级拓客员', '4', '2ccdc29c2474407ca4bcb1b51d65cdfe', null, '2021-10-24 22:11:54');
INSERT INTO `t_role` VALUES ('53bab25a8a7941459350217563573b23', '二级销售员', '3', '2ccdc29c2474407ca4bcb1b51d65cdfe', '', '2021-10-24 22:11:53');
INSERT INTO `t_role` VALUES ('7a3d039191e84abea4b6b984b62a3433', '测试线索等级', '3', '2ccdc29c2474407ca4bcb1b51d65cdfe', '', '2021-11-27 19:59:58');
INSERT INTO `t_role` VALUES ('bb8c0a0514774f008f6ee65bfbad2b7b', 'Role002', '4', '2ccdc29c2474407ca4bcb1b51d65cdfe', 'Role 002', '2021-11-24 20:35:07');
INSERT INTO `t_role` VALUES ('c45aefbda91941dcb977357455be1779', 'Role002 New', '4', '2ccdc29c2474407ca4bcb1b51d65cdfe', 'Role 002 New', '2021-11-24 20:32:39');
INSERT INTO `t_role` VALUES ('e9834ade133e4d1d8ef4193adb87998f', '322', '4', null, '1111', '2021-11-27 19:51:05');
INSERT INTO `t_role` VALUES ('eb80dad511084dd2ade7d6df48a76a9e', '测试角色', '1', null, '123', '2021-11-27 19:44:18');
INSERT INTO `t_role` VALUES ('f0b74cf7f86747eea32a397c75f721b3', '33333', '4', null, '44444', '2021-11-27 19:50:03');

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

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('11cf48dd5f4d45258c8bbc92e9f9f906', '测试项目2', '15814424558', null, null, '202112/80ba9e5d02cb4be795c1490d1204e491', '1', 'oWJfU4kMa_Z_eGi-t3-PQJsi7tS8', '33bab25a8a7941459350217563573b23', '10f57047f8914425a5b35af09b448865', '2021-12-05 18:52:58', '6184be66');
INSERT INTO `t_user` VALUES ('2af00281f56841fab276262038a59eba', 'Louis', '13425046922', null, null, null, '1', 'oE47u0CWnZR4krOOkQ0KDaqEiAlo', '16803e86d0c5472686641365483f2d17', '283b26c29ac148b1bdea47f019151713', '2021-11-29 15:15:23', '69b02718');
INSERT INTO `t_user` VALUES ('303630d9c77543499679881822bf972f', '多拉', '18589851597', null, null, null, '1', 'oE47u0H7rVxv9_u3Wps3E0G8rUms', '33bab25a8a7941459350217563573b23', '36494b53277c4e25bf9ca2e0e23e4e38', '2021-11-29 17:55:29', '4a7ea4');
INSERT INTO `t_user` VALUES ('3195d91d8026469199032d7165cb11d9', '海城', '18718417110', null, null, null, '1', 'oWJfU4lz3VLc_TQZiyXcskuJ2mGk', '16803e86d0c5472686641365483f2d17', '54ab33f84167478aaabd549523ccc5cf', '2021-12-03 10:33:55', '91cf4c0c');
INSERT INTO `t_user` VALUES ('416eb098fdaa4a9ca60ad424c515d46d', '测试2', '18718417110', null, null, null, '1', 'oWJfU4scii-vA', '33bab25a8a7941459350217563573b23', '144f807280c34ad290493591a5cae4cb', '2021-12-03 10:58:04', '4a7ea2');
INSERT INTO `t_user` VALUES ('41a0adbe32314e93b6c1e45debc29879', '海城2', '18718417101', null, null, null, '1', 'oWJfU4scii-vAq4oNzhaM70gt41k', '33bab25a8a7941459350217563573b23', '10f57047f8914425a5b35af09b448865', '2021-12-03 15:18:08', '4656184b');
INSERT INTO `t_user` VALUES ('4d84038c476b4d0196de8ec00575d986', 'vae', '18418417120', null, null, null, '1', 'oWJfU4lz3VLc_TQZiyXcs', '16803e86d0c5472686641365483f2d17', '283b26c29ac148b1bdea47f019151713', '2021-12-03 10:10:47', '2d69b027');
INSERT INTO `t_user` VALUES ('7251e5eb4bf4456680e62fbb8eee8848', '测试', '13229937425', null, null, null, '1', 'oE47u0GxXuAkLWHl', '33bab25a8a7941459350217563573b23', '283b26c29ac148b1bdea47f019151713', '2021-11-29 15:33:45', 'f4707911');
INSERT INTO `t_user` VALUES ('76b86c7dc18f4982aee933309b162286', 'vae', '18718417110', null, null, null, '1', 'oWJfU4scii-vAq4o', '33bab25a8a7941459350217563573b23', '144f807280c34ad290493591a5cae4cb', '2021-12-03 10:44:11', '4a7ea1');
INSERT INTO `t_user` VALUES ('79165b9709cd45878bcf995def380599', '余海城', '18718417101', null, null, null, '1', 'oE47u0AyoBUevmcFhN5TEq3d2eVE', '16803e86d0c5472686641365483f2d17', '283b26c29ac148b1bdea47f019151713', '2021-11-29 15:07:43', '04d00806');
INSERT INTO `t_user` VALUES ('95c2b370adc54162a41863b15c4aa1ab', '测试4', '18718417101', null, null, null, '0', 'oWJfU4scii-vAq4oN', '33bab25a8a7941459350217563573b23', '144f807280c34ad290493591a5cae4cb', '2021-12-03 14:50:46', '4a7ea6');
INSERT INTO `t_user` VALUES ('9dcd5668df1649b6b53c4180f9a58456', '测试人员', '13229937426', null, null, null, '1', 'oWJfU4scii-vAq4o', '33bab25a8a7941459350217563573b23', '283b26c29ac148b1bdea47f019151713', '2021-12-02 14:19:11', '4d8537c4');
INSERT INTO `t_user` VALUES ('area01', 'Area 01', '13688889999', null, null, null, '1', '111', '33bab25a8a7941459350217563573b23', '11', '2021-10-27 20:01:04', null);
INSERT INTO `t_user` VALUES ('area02', 'Area 02', '13824183324', null, null, null, '1', '112', '33bab25a8a7941459350217563573b23', null, '2021-11-21 15:21:57', null);
INSERT INTO `t_user` VALUES ('build01', 'Build 01', '13122223333', null, null, null, '1', '116', '412f6c8db4d94ac3a85249076717b809', '4ed58177342d4ece9267a8ee096e8aa5', '2021-11-17 06:33:06', null);
INSERT INTO `t_user` VALUES ('build02', 'Build 02', '13122223333', '', null, '', '1', '119', '412f6c8db4d94ac3a85249076717b809', '36494b53277c4e25bf9ca2e0e23e4e38', '2021-11-17 06:33:06', null);
INSERT INTO `t_user` VALUES ('build03', 'Build 03', '13122223333', '', null, '', '1', '116', '412f6c8db4d94ac3a85249076717b809', '283b26c29ac148b1bdea47f019151713', '2021-11-17 06:33:06', null);
INSERT INTO `t_user` VALUES ('cf03358742d544c7974021bd960d090f', '测试3', '18718417110', null, null, null, '1', 'oWJfU4sciiq4o', '33bab25a8a7941459350217563573b23', '54ab33f84167478aaabd549523ccc5cf', '2021-12-03 11:10:01', '0d684a0a');
INSERT INTO `t_user` VALUES ('dd2f4fb9a0894e3f9d8c4096e1350a7e', '多拉', '18589051597', null, null, null, '0', 'oWJfU4lWE311GSErLv_6RDDZR4M4', '16803e86d0c5472686641365483f2d17', '10f57047f8914425a5b35af09b448865', '2021-12-03 18:23:37', '54411d60');
INSERT INTO `t_user` VALUES ('e2f3b12caa284bcb8dedc29ed4a78e4a', '华清', '17796335695', null, null, null, '1', 'oE47u0M2MjjTD2_xOHcHJjFWJsoU', '16803e86d0c5472686641365483f2d17', '36494b53277c4e25bf9ca2e0e23e4e38', '2021-11-29 17:55:11', '1424fcf8');
INSERT INTO `t_user` VALUES ('proj01', 'Project Admin 01', '13011112222', null, null, null, '1', '113', '29c563e9b36a4d84bf4d7dfa0450dbf7', null, '2021-10-27 20:01:04', null);
INSERT INTO `t_user` VALUES ('proj02', 'Project Admin 02', '13011112222', '', null, '', '1', '114', '29c563e9b36a4d84bf4d7dfa0450dbf7', '', '2021-10-27 20:01:04', null);
INSERT INTO `t_user` VALUES ('sale01', 'Sale 01', '13022224444', null, null, null, '1', '115', '33bab25a8a7941459350217563573b23', '144f807280c34ad290493591a5cae4cb', '2021-10-27 20:01:04', '4a7ea1');
INSERT INTO `t_user` VALUES ('sale02', 'Sale 02', '13022224444', '', null, '', '1', '117', '33bab25a8a7941459350217563573b23', '144f807280c34ad290493591a5cae4cb', '2021-10-27 20:01:04', '4a7ea2');
INSERT INTO `t_user` VALUES ('sale03', 'Sale 03', '13022224444', '', null, '', '1', '118', '412f6c8db4d94ac3a85249076717b809', '283b26c29ac148b1bdea47f019151713', '2021-10-27 20:01:04', '4a7ea3');
