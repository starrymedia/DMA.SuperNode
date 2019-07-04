CREATE TABLE `share` (
`id`  bigint(20) NULL AUTO_INCREMENT COMMENT '唯一标识' ,
`address`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投票地址' ,
`vote_value`  decimal(21,8) NULL DEFAULT 0 COMMENT '投票数' ,
`vote_total_value`  decimal(21,8) NULL DEFAULT 0 COMMENT '当前投票总数' ,
`balance`  decimal(21,8) NULL DEFAULT 0 COMMENT '应分配ela数量' ,
`block_height`  bigint(20) NULL DEFAULT NULL COMMENT '当前块高' ,
`is_send`  tinyint(1) NULL DEFAULT 0 COMMENT '是否发生分成（0：未发送，1：已发送）' ,
`create_time`  timestamp NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT NULL COMMENT '修改时间' ,
`deleted`  tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除' ,
`weight`  int(11) NULL DEFAULT NULL COMMENT '排序' ,
`version`  int(11) NULL DEFAULT 0 COMMENT '悲观锁' ,
`json`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'json' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='分配明细表'
;


CREATE TABLE `node_income` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识' ,
`block_height`  bigint(20) NULL DEFAULT NULL COMMENT '当前块高' ,
`income_value`  decimal(21,8) NULL DEFAULT 0 COMMENT '本轮节点总收益金额' ,
`management_value`  decimal(21,8) NULL DEFAULT 0 COMMENT '管理维护金额' ,
`team_value`  decimal(21,8) NULL DEFAULT 0 COMMENT '团队收益金额' ,
`community_value`  decimal(21,8) NULL DEFAULT 0 COMMENT '社区总收益金额' ,
`create_time`  timestamp NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT NULL COMMENT '修改时间' ,
`deleted`  tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除' ,
`weight`  int(11) NULL DEFAULT NULL COMMENT '排序' ,
`json`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'json' ,
`version`  int(11) NULL DEFAULT 0 COMMENT '悲观锁' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='节点收益明细表'
;


CREATE TABLE `node_income_send` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识' ,
`receive_address`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收地址' ,
`send_address`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发放地址' ,
`send_value`  decimal(21,8) NULL DEFAULT 0 COMMENT '发送金额' ,
`txid`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易txid' ,
`block_height_start`  bigint(20) NULL DEFAULT NULL COMMENT '发放的起始块高' ,
`block_height_end`  bigint(20) NULL DEFAULT NULL COMMENT '发放的结束块高' ,
`create_time`  timestamp NULL DEFAULT NULL COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT NULL COMMENT '修改时间' ,
`deleted`  tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除' ,
`weight`  int(11) NULL DEFAULT NULL COMMENT '排序' ,
`json`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'json' ,
`version`  int(11) NULL DEFAULT 0 COMMENT '悲观锁' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='节点收益发放明细表'
;




CREATE TABLE `data_map` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
	`name` VARCHAR(255) NULL DEFAULT NULL COMMENT 'key',
	`value` VARCHAR(255) NULL DEFAULT NULL COMMENT '值',
	`json` TEXT NULL COMMENT '预留字段',
	`description` TEXT NULL COMMENT '描述信息',
	`create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '修改时间',
	`deleted` BIT(1) NULL DEFAULT b'0' COMMENT '逻辑删除',
	`weight` INT(11) NULL DEFAULT NULL COMMENT '排序',
	PRIMARY KEY (`id`)
)
COMMENT='数据字典'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=17
;
