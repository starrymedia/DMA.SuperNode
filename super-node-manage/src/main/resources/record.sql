/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : record

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-06-14 18:05:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_button`
-- ----------------------------
DROP TABLE IF EXISTS `sys_button`;
CREATE TABLE `sys_button` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '全局唯一标识符' ,
`deleted`  bit(1) NULL DEFAULT b'0' ,
`menu_id`  bigint(20) NULL DEFAULT NULL ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`permission`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`weight`  int(11) NULL DEFAULT NULL ,
`json`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`version`  int(11) NULL DEFAULT 0 COMMENT '悲观锁' ,
`create_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT NULL COMMENT '修改时间' ,
`create_user`  bigint(20) NULL DEFAULT NULL COMMENT '创建人' ,
`update_user`  bigint(20) NULL DEFAULT NULL COMMENT '修改人' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2

;

-- ----------------------------
-- Records of sys_button
-- ----------------------------
BEGIN;
INSERT INTO `sys_button` VALUES ('1', '', '1', null, null, '1', null, null, '2019-05-19 23:33:22', '2019-05-19 23:33:29', null, null);
COMMIT;

-- ----------------------------
-- Table structure for `sys_logger`
-- ----------------------------
DROP TABLE IF EXISTS `sys_logger`;
CREATE TABLE `sys_logger` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '全局唯一标识符' ,
`clientIp`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`createTime`  datetime NULL DEFAULT NULL ,
`deleted`  bit(1) NULL DEFAULT NULL ,
`httpStatusCode`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`method`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`paramData`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`returnData`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`returnTime`  datetime NULL DEFAULT NULL ,
`sessionId`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`time`  datetime NULL DEFAULT NULL ,
`timeConsuming`  int(11) NULL DEFAULT NULL ,
`type`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`updateTime`  datetime NULL DEFAULT NULL ,
`uri`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`weight`  int(11) NULL DEFAULT NULL ,
`json`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`version`  int(11) NULL DEFAULT 0 COMMENT '悲观锁' ,
`create_user`  bigint(20) NULL DEFAULT NULL COMMENT '创建人' ,
`update_user`  bigint(20) NULL DEFAULT NULL COMMENT '修改人' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of sys_logger
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '全局唯一标识符' ,
`create_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT NULL COMMENT '修改时间' ,
`creator`  bigint(20) NULL DEFAULT NULL ,
`deleted`  bit(1) NULL DEFAULT NULL ,
`display`  int(11) NULL DEFAULT NULL ,
`flag`  bit(1) NULL DEFAULT NULL ,
`icon`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`menu_type`  smallint(6) NULL DEFAULT NULL COMMENT '菜单类型 1、菜单   2、权限' ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`permission`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`url`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`weight`  int(11) NULL DEFAULT NULL ,
`parent_id`  bigint(20) NULL DEFAULT NULL ,
`json`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`version`  int(11) NULL DEFAULT 0 COMMENT '悲观锁' ,
`create_user`  bigint(20) NULL DEFAULT NULL COMMENT '创建人' ,
`update_user`  bigint(20) NULL DEFAULT NULL COMMENT '修改人' ,
PRIMARY KEY (`id`),
INDEX `FK_shidcs7c9yvgmsl66gdem73vn` (`parent_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='菜单管理表'
AUTO_INCREMENT=50

;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('-1', '2019-05-20 19:36:47', '2019-05-20 19:54:14', null, '', null, null, 'layui-icon-set-fill', null, '根目录', 'auth:tree:view', null, null, null, null, '0', null, null), ('2', '2019-05-20 19:54:09', '2019-05-25 14:30:24', null, '', null, null, 'layui-icon-set-fill', null, '用户管理', 'auth:user:view', '/sys/core/user/index.html', null, '5', null, '0', null, null), ('3', '2019-05-20 19:54:08', '2019-05-20 19:54:17', null, '', null, null, 'layui-icon-set-fill', null, '角色管理', 'auth:role:view', '/sys/core/role/index.html', null, '5', null, '0', null, null), ('4', '2019-05-20 19:54:11', '2019-05-20 19:54:20', null, '', null, null, 'layui-icon-set-fill', null, '菜单管理', 'auth:menu:view', '/sys/core/menu/index.html', null, '5', null, '0', null, null), ('5', '2019-05-20 19:54:05', '2019-06-03 16:43:05', null, '', null, null, 'layui-icon-set-fill', null, '系统模块', 'auth:store:view', '', null, '-1', null, '0', null, null), ('7', '2019-05-20 19:54:04', '2019-05-25 19:19:15', null, '', null, null, 'layui-icon-set-fill', null, '系统日志', 'auth:log:view', '/sys/core/logger/index.html', null, '5', null, '0', null, null), ('46', '2019-06-13 16:08:29', null, null, '', null, null, 'layui-icon-set-fill', null, '节点数据', 'auth:node:view', '', null, '-1', null, null, null, null), ('47', '2019-06-13 16:09:40', '2019-06-13 18:01:38', null, '', null, null, 'layui-icon-template-1', null, '节点列表', 'auth:node:view', '/node/index.html', null, '46', null, null, null, null), ('48', '2019-06-13 18:13:10', null, null, '', null, null, 'layui-icon-template-1', null, '我的节点', 'auth:node:view', '/node/myIndex.html', null, '46', null, null, null, null), ('49', '2019-06-14 15:20:00', '2019-06-14 15:20:34', null, '', null, null, 'layui-icon-align-left', null, '投票统计', 'auth:menu:view', '/node/voters.html', null, '46', null, null, null, null);
COMMIT;

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '全局唯一标识符' ,
`create_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT NULL COMMENT '修改时间' ,
`deleted`  bit(1) NULL DEFAULT NULL ,
`description`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`weight`  int(11) NULL DEFAULT NULL ,
`json`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`version`  int(11) NULL DEFAULT 0 COMMENT '悲观锁' ,
`create_user`  bigint(20) NULL DEFAULT NULL COMMENT '创建人' ,
`update_user`  bigint(20) NULL DEFAULT NULL COMMENT '修改人' ,
`store_id`  bigint(20) NULL DEFAULT NULL COMMENT '关联店铺' ,
`role_type`  tinyint(1) NULL DEFAULT 0 COMMENT '-1:系统管理员,0:店铺管理员' ,
PRIMARY KEY (`id`),
INDEX `FK_d_sys_role_d_store` (`store_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='角色信息主体表'
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('-1', '2019-02-26 19:27:56', '2019-02-13 16:33:05', '', '拥有所有菜单权限', '超级用户', null, null, null, null, null, null, '1'), ('2', '2019-06-13 11:51:46', null, '', '拥有查看数据权限', '管理员', null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
-- Table structure for `sys_role_button`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_button`;
CREATE TABLE `sys_role_button` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '全局唯一标识符' ,
`create_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT NULL COMMENT '修改时间' ,
`deleted`  bit(1) NULL DEFAULT b'0' ,
`flag`  bit(1) NULL DEFAULT NULL ,
`weight`  int(11) NULL DEFAULT NULL ,
`button_id`  bigint(20) NULL DEFAULT NULL ,
`role_id`  bigint(20) NULL DEFAULT NULL ,
`json`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`version`  int(11) NULL DEFAULT 0 COMMENT '悲观锁' ,
`create_user`  bigint(20) NULL DEFAULT NULL COMMENT '创建人' ,
`update_user`  bigint(20) NULL DEFAULT NULL COMMENT '修改人' ,
PRIMARY KEY (`id`),
INDEX `FK_7bgao8210shfq08d66vqo5fkm` (`button_id`) USING BTREE ,
INDEX `FK_25pxhwjecplu5v0w8166xiblm` (`role_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of sys_role_button
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '全局唯一标识符' ,
`create_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT NULL COMMENT '修改时间' ,
`deleted`  bit(1) NULL DEFAULT b'0' ,
`flag`  bit(1) NULL DEFAULT NULL ,
`weight`  int(11) NULL DEFAULT NULL ,
`menu_id`  bigint(20) NULL DEFAULT NULL ,
`role_id`  bigint(20) NULL DEFAULT NULL ,
`json`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`version`  int(11) NULL DEFAULT 0 COMMENT '悲观锁' ,
`create_user`  bigint(20) NULL DEFAULT NULL COMMENT '创建人' ,
`update_user`  bigint(20) NULL DEFAULT NULL COMMENT '修改人' ,
PRIMARY KEY (`id`),
INDEX `FK_bqh3kjjbhp38n49ccdve661g2` (`menu_id`) USING BTREE ,
INDEX `FK_36katxgweq05eir8exv3smjj5` (`role_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=42

;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('29', '2019-06-14 15:20:44', null, '', null, null, '46', '-1', null, null, null, null), ('30', '2019-06-14 15:20:44', null, '', null, null, '49', '-1', null, null, null, null), ('31', '2019-06-14 15:20:44', null, '', null, null, '48', '-1', null, null, null, null), ('32', '2019-06-14 15:20:44', null, '', null, null, '47', '-1', null, null, null, null), ('33', '2019-06-14 15:20:44', null, '', null, null, '5', '-1', null, null, null, null), ('34', '2019-06-14 15:20:44', null, '', null, null, '4', '-1', null, null, null, null), ('35', '2019-06-14 15:20:44', null, '', null, null, '2', '-1', null, null, null, null), ('36', '2019-06-14 15:20:44', null, '', null, null, '3', '-1', null, null, null, null), ('37', '2019-06-14 15:20:44', null, '', null, null, '7', '-1', null, null, null, null), ('38', '2019-06-14 17:53:18', null, '', null, null, '46', '2', null, null, null, null), ('39', '2019-06-14 17:53:18', null, '', null, null, '49', '2', null, null, null, null), ('40', '2019-06-14 17:53:18', null, '', null, null, '48', '2', null, null, null, null), ('41', '2019-06-14 17:53:18', null, '', null, null, '47', '2', null, null, null, null);
COMMIT;

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '全局唯一标识符' ,
`create_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT NULL COMMENT '修改时间' ,
`flag`  bit(1) NULL DEFAULT NULL ,
`deleted`  bit(1) NULL DEFAULT NULL ,
`login_time`  datetime NULL DEFAULT NULL ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`password`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码' ,
`json`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`version`  int(11) NULL DEFAULT 0 COMMENT '悲观锁' ,
`weight`  int(11) NULL DEFAULT NULL ,
`create_user`  bigint(20) NULL DEFAULT NULL COMMENT '创建人' ,
`update_user`  bigint(20) NULL DEFAULT NULL COMMENT '修改人' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='管理员信息主体表'
AUTO_INCREMENT=8

;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('-1', '2019-03-05 20:30:18', '2019-03-05 20:30:19', '', '', '2017-04-07 22:23:15', 'root@2019', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, null, null), ('7', '2019-06-13 11:52:37', '2019-06-13 13:46:28', '', '', null, 'admin@2019', 'E10ADC3949BA59ABBE56E057F20F883E', null, '0', null, null, null);
COMMIT;

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '全局唯一标识符' ,
`create_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT NULL COMMENT '修改时间' ,
`deleted`  bit(1) NULL DEFAULT b'0' ,
`weight`  int(11) NULL DEFAULT NULL ,
`button_id`  bigint(20) NULL DEFAULT NULL ,
`user_id`  bigint(20) NULL DEFAULT NULL ,
`role_id`  bigint(20) NULL DEFAULT NULL ,
`json`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`version`  int(11) NULL DEFAULT 0 COMMENT '悲观锁' ,
`create_user`  bigint(20) NULL DEFAULT NULL COMMENT '创建人' ,
`update_user`  bigint(20) NULL DEFAULT NULL COMMENT '修改人' ,
PRIMARY KEY (`id`),
INDEX `FK_rrc850a7sl9m28nuju4rb3qef` (`button_id`) USING BTREE ,
INDEX `FK_cbgbqwxxueejp133b7b482uo` (`user_id`) USING BTREE ,
INDEX `FK_d_sys_user_role_d_sys_role` (`role_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=9

;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '2019-05-20 19:25:52', '2019-05-20 19:25:56', '', null, null, '-1', '-1', null, null, null, null), ('8', '2019-06-13 13:46:39', null, '', null, null, '7', '2', null, '0', null, null);
COMMIT;

-- ----------------------------
-- Table structure for `voters`
-- ----------------------------
DROP TABLE IF EXISTS `voters`;
CREATE TABLE `voters` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id' ,
`create_time`  timestamp NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT NULL COMMENT '修改时间' ,
`deleted`  bit(1) NULL DEFAULT NULL COMMENT '逻辑删除' ,
`weight`  int(11) NULL DEFAULT NULL ,
`json`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`version`  int(11) NULL DEFAULT 0 COMMENT '悲观锁' ,
`create_user`  bigint(20) NULL DEFAULT NULL COMMENT '创建人' ,
`update_user`  bigint(20) NULL DEFAULT NULL COMMENT '修改人' ,
`address`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投票人地址' ,
`txid`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投票txid' ,
`block_height`  bigint(20) NULL DEFAULT NULL COMMENT '当前节点高度' ,
`vote_time`  bigint(20) NULL DEFAULT NULL COMMENT '投票时间时间' ,
`vote_height`  bigint(20) NULL DEFAULT NULL COMMENT '投票块高度' ,
`vote_value`  decimal(20,8) NULL DEFAULT NULL COMMENT '投票数' ,
`total_value`  decimal(20,8) NULL DEFAULT NULL COMMENT '当前块投票总数' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='投票详情表'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of voters
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Auto increment value for `sys_button`
-- ----------------------------
ALTER TABLE `sys_button` AUTO_INCREMENT=2;

-- ----------------------------
-- Auto increment value for `sys_logger`
-- ----------------------------
ALTER TABLE `sys_logger` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `sys_menu`
-- ----------------------------
ALTER TABLE `sys_menu` AUTO_INCREMENT=50;

-- ----------------------------
-- Auto increment value for `sys_role`
-- ----------------------------
ALTER TABLE `sys_role` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for `sys_role_button`
-- ----------------------------
ALTER TABLE `sys_role_button` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `sys_role_menu`
-- ----------------------------
ALTER TABLE `sys_role_menu` AUTO_INCREMENT=42;

-- ----------------------------
-- Auto increment value for `sys_user`
-- ----------------------------
ALTER TABLE `sys_user` AUTO_INCREMENT=8;

-- ----------------------------
-- Auto increment value for `sys_user_role`
-- ----------------------------
ALTER TABLE `sys_user_role` AUTO_INCREMENT=9;

-- ----------------------------
-- Auto increment value for `voters`
-- ----------------------------
ALTER TABLE `voters` AUTO_INCREMENT=1;
