/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : 127.0.0.1:3306
 Source Schema         : development

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 09/02/2020 09:48:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for circulation_info
-- ----------------------------
DROP TABLE IF EXISTS `circulation_info`;
CREATE TABLE `circulation_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流转id',
  `pid` bigint(20) NOT NULL COMMENT '处方ID (关联 prescription 表 id)',
  `sender` bigint(20) NOT NULL COMMENT '处方流转者 (关联user表的 id)',
  `sender_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '处方流转者姓名',
  `origin_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '处方来源机构  (关联 organization表 code)',
  `origin_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源机构名称',
  `origin_time` datetime(0) NOT NULL COMMENT '处方来源时间',
  `achieve_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '到达机构 (关联 organization表 code)',
  `achieve_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '到达机构名称',
  `receiver` bigint(20) NOT NULL COMMENT '接收者  (关联user表的 id)',
  `receiver_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接收者姓名',
  `accept_status` tinyint(4) NOT NULL COMMENT '接收状态 1:已接收 ,0:未接收, 9. 拒绝',
  `change_time` datetime(0) NULL DEFAULT NULL COMMENT '接收状态改变时间',
  `extra` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ci_UNIQUE`(`id`, `pid`, `origin_code`, `achieve_code`, `receiver`) USING BTREE,
  INDEX `fk_ci_pre_id`(`pid`) USING BTREE,
  INDEX `fk_cio_pre_code`(`origin_code`) USING BTREE,
  INDEX `fk_cia_pre_code`(`achieve_code`) USING BTREE,
  INDEX `fk_cis_usr_sid`(`sender`) USING BTREE,
  INDEX `fk_cir_usr_rid`(`receiver`) USING BTREE,
  CONSTRAINT `fk_ci_pre_id` FOREIGN KEY (`pid`) REFERENCES `prescription_detail` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_cia_pre_code` FOREIGN KEY (`achieve_code`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_cio_pre_code` FOREIGN KEY (`origin_code`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_cir_usr_rid` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_cis_usr_sid` FOREIGN KEY (`sender`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '消息主键',
  `sender` bigint(20) NOT NULL COMMENT '发送者 (关联user表的 id)',
  `sender_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发送者姓名',
  `send_org_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源机构 (关联 organization 表 code)',
  `send_org_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源机构名称',
  `send_time` datetime(0) NOT NULL COMMENT '发送时间',
  `send_flag` tinyint(4) NOT NULL COMMENT '发送标记: 1. 已发 0. 未发',
  `receiver` bigint(20) NOT NULL COMMENT '接收者 (关联user表的 id)',
  `receiver_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接收者姓名',
  `accept_org_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接收机构 (关联 organization 表 code)',
  `accept_org_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接收机构名称',
  `read_time` datetime(0) NULL DEFAULT NULL COMMENT '查看时间',
  `subject` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `read_flag` tinyint(4) NOT NULL COMMENT '阅读标记: 1. 已读 0. 未读',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `msg_UNIQUE`(`id`, `sender`, `receiver`) USING BTREE,
  INDEX `fk_msg_usr_sid`(`sender`) USING BTREE,
  INDEX `fk_msg_usr_rid`(`receiver`) USING BTREE,
  INDEX `fk_msg_org_socode`(`send_org_code`) USING BTREE,
  INDEX `fk_msg_org_rocode`(`accept_org_code`) USING BTREE,
  CONSTRAINT `fk_msg_org_rocode` FOREIGN KEY (`accept_org_code`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_msg_org_socode` FOREIGN KEY (`send_org_code`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_msg_usr_rid` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_msg_usr_sid` FOREIGN KEY (`sender`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `label` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父菜单id',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单url',
  `level` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单层级',
  `flag` tinyint(4) NOT NULL COMMENT '启用标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for org_classify
-- ----------------------------
DROP TABLE IF EXISTS `org_classify`;
CREATE TABLE `org_classify`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `oc_id_UNIQUE`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for org_host
-- ----------------------------
DROP TABLE IF EXISTS `org_host`;
CREATE TABLE `org_host`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `oh_id_UNIQUE`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for org_relation
-- ----------------------------
DROP TABLE IF EXISTS `org_relation`;
CREATE TABLE `org_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `or_id_UNIQUE`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for org_type
-- ----------------------------
DROP TABLE IF EXISTS `org_type`;
CREATE TABLE `org_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ot_id_UNIQUE`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '机构ID',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构代码',
  `orgflag` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构标志(用于统计上下级关系树)',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构名称',
  `classify` int(11) NOT NULL COMMENT '机构分类: 1. 非营利性医疗机构  2. 盈利性医疗机构  3. 其他医疗机构  \r\n',
  `type` int(11) NOT NULL COMMENT '机构类型:  \r\n1	省卫生厅\r\n2	市卫生局\r\n3	区卫生局\r\n4	卫生社会团体\r\n5	其他卫生机构\r\n6	健康教育所(站、中心)\r\n7	医学教育机构\r\n8	医学科学研究机构\r\n9	卫生监督检验(监测、检测)所(站)\r\n10	卫生监督所(局)\r\n11	疾病防控制中心(防疫站)\r\n12	专科疾病防治院(所、站)\r\n13	医院\r\n14	采供血机构\r\n15	急救中心(站)\r\n16	妇幼保健院(所、站)\r\n17	门诊部、诊所、医务室、村卫生室\r\n18	卫生院\r\n19	社区卫生服务中心(站)',
  `host` int(11) NOT NULL COMMENT '主办单位:  1. 卫生行政部门  2. 其他行政部门  3. 企业  4. 事业单位  5. 社会团体  6. 其他社会组织',
  `relation` int(11) NOT NULL COMMENT '隶属关系: 1. 中央属  2. 省,自治区,直辖市属  3. 省辖市(地区,州,盟)  4. 县级市(省辖市区)属  5. 县(旗)属  6. 镇属  7.  乡属  8. 街道属 ',
  `postcode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮政编码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构地址',
  `phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '联系电话',
  `mail` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构邮箱',
  `responser` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '负责人',
  `officer` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '法人代表',
  `web` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构网站',
  `supervising` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '上级机构',
  `date` datetime(0) NOT NULL COMMENT '注册日期',
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '简介',
  `flag` tinyint(4) NOT NULL COMMENT '1:正常,0:停用 9:待激活',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `org_id_UNIQUE`(`id`) USING BTREE,
  UNIQUE INDEX `org_code_UNIQUE`(`code`) USING BTREE,
  INDEX `fk_oc_id`(`classify`) USING BTREE,
  INDEX `fk_oh_id`(`host`) USING BTREE,
  INDEX `fk_or_id`(`relation`) USING BTREE,
  INDEX `fk_ot_id`(`type`) USING BTREE,
  INDEX `org_name_UNIQUE`(`name`) USING BTREE,
  CONSTRAINT `fk_oc_id` FOREIGN KEY (`classify`) REFERENCES `org_classify` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_oh_id` FOREIGN KEY (`host`) REFERENCES `org_host` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_or_id` FOREIGN KEY (`relation`) REFERENCES `org_relation` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_ot_id` FOREIGN KEY (`type`) REFERENCES `org_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for prescription_detail
-- ----------------------------
DROP TABLE IF EXISTS `prescription_detail`;
CREATE TABLE `prescription_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '处方代码',
  `orgcode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构代码 (关联 organization 表 code)',
  `orgname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构名称',
  `uid` bigint(20) NOT NULL COMMENT '录入处方者 (关联 user 表 id)',
  `uname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '录入者姓名',
  `type` tinyint(4) NOT NULL COMMENT '处方类型: 1. 普通处方  2. 特殊处方',
  `doctor_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '开方医生',
  `doctor_phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '开方医生手机号',
  `department` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '归属科室',
  `check_doctor` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '审核医生',
  `check_phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '审核医生手机号',
  `symptom` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '适用症状',
  `sex` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '适用性别',
  `age` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '使用年龄',
  `date` datetime(0) NOT NULL COMMENT '开方日期',
  `medicine` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用药',
  `advice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '医嘱',
  `price` decimal(10, 2) NOT NULL COMMENT '建议价格',
  `origin` tinyint(4) NOT NULL DEFAULT 1 COMMENT '处方来源: 1:本机构,9:外来',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ps_UNIQUE`(`id`, `orgcode`, `uid`, `type`) USING BTREE,
  INDEX `fk_pd_org_code`(`orgcode`) USING BTREE,
  INDEX `fk_pd_usr_id`(`uid`) USING BTREE,
  CONSTRAINT `fk_pd_org_code` FOREIGN KEY (`orgcode`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_pd_usr_id` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for prescription_status
-- ----------------------------
DROP TABLE IF EXISTS `prescription_status`;
CREATE TABLE `prescription_status`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '处方状态ID',
  `pid` bigint(20) NOT NULL COMMENT '处方ID (关联 prescription_detail 表 id)',
  `flag` tinyint(4) NOT NULL COMMENT '流转状态: 1:正常,0:停用',
  `operator` bigint(20) NULL DEFAULT NULL COMMENT '审核员 (关联user表的 id)',
  `operator_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核员姓名',
  `verify` tinyint(4) NOT NULL DEFAULT 9 COMMENT '审核状态: 1:已审核,0: 未通过, 9: 待审核',
  `verify_time` datetime(0) NULL DEFAULT NULL COMMENT '审核日期',
  `forbidden_time` datetime(0) NULL DEFAULT NULL COMMENT '开停方日期',
  `enable` tinyint(4) NOT NULL COMMENT '是否可流转 1:可以,0: 不可以',
  `extra` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ps_UNIQUE`(`id`, `pid`, `operator`) USING BTREE,
  INDEX `fk_ps_usr_id`(`operator`) USING BTREE,
  INDEX `fk_ps_pd_pid`(`pid`) USING BTREE,
  CONSTRAINT `fk_ps_pd_pid` FOREIGN KEY (`pid`) REFERENCES `prescription_detail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色主键',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标题',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色代码',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  `flag` tinyint(4) NOT NULL COMMENT '1:正常,0: 停用:9:待激活',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `r_code_UNIQUE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role_module
-- ----------------------------
DROP TABLE IF EXISTS `role_module`;
CREATE TABLE `role_module`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `rid` bigint(20) NOT NULL COMMENT '角色id',
  `mid` bigint(20) NOT NULL COMMENT '模块id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `rm_r_index`(`rid`) USING BTREE,
  INDEX `fk_rm_mid`(`mid`) USING BTREE,
  CONSTRAINT `fk_rm_mid` FOREIGN KEY (`mid`) REFERENCES `module` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_rm_rid` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 127 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `header` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `register_time` datetime(0) NOT NULL COMMENT '注册日期',
  `modify_time` datetime(0) NOT NULL COMMENT '修改日期',
  `last_login_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后登录时间',
  `flag` tinyint(4) NOT NULL COMMENT '1:正常,0: 停用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_id_UNIQUE`(`id`) USING BTREE,
  INDEX `u_username_UNIQUE`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint(20) NOT NULL COMMENT '信息主键',
  `orgcode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属机构 (关联 organization 表 code)',
  `orgname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构名称',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
  `identity` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证号',
  `sex` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '性别',
  `age` int(4) NOT NULL COMMENT '年龄',
  `nation` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '民族',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `mail` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ui_phone_UNIQUE`(`phone`) USING BTREE,
  UNIQUE INDEX `ui_identity_UNIQUE`(`identity`) USING BTREE,
  UNIQUE INDEX `ui_id_UNIQUE`(`id`) USING BTREE,
  UNIQUE INDEX `ui_mail_UNIQUE`(`mail`) USING BTREE,
  INDEX `fk_usr_orgz_code`(`orgcode`) USING BTREE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_usr_orgz_code` FOREIGN KEY (`orgcode`) REFERENCES `organization` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(20) NOT NULL COMMENT 'user表主键',
  `rid` bigint(20) NOT NULL COMMENT 'role表主键',
  `flag` tinyint(4) NOT NULL COMMENT '角色是否启用标志: 1.启用, 0: 停用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ur_id_UNIQUE`(`id`) USING BTREE,
  INDEX `fk_user_u_id_idx`(`uid`) USING BTREE,
  INDEX `fk_role_r_id_idx`(`rid`) USING BTREE,
  CONSTRAINT `fk_role_r_id` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_u_id` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Triggers structure for table prescription_detail
-- ----------------------------
DROP TRIGGER IF EXISTS `t_insert_detail`;
delimiter ;;
CREATE TRIGGER `t_insert_detail` AFTER INSERT ON `prescription_detail` FOR EACH ROW insert into prescription_status(pid,flag,verify,enable) values (NEW.id,0,9,0)
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
