CREATE TABLE `shiyong_action` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_mobile` varchar(11) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `activity_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='使用活动-用户申请记录';

CREATE TABLE `headline_reply_attach` (
  `picture_serialnum` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `picture` mediumblob COMMENT '评论图片',
  `reply_id` bigint(20) DEFAULT NULL COMMENT '评论id',
  PRIMARY KEY (`picture_serialnum`),
  KEY `reply_id` (`reply_id`),
  CONSTRAINT `headline_reply_attach_ibfk_1` FOREIGN KEY (`reply_id`) REFERENCES `headline_reply` (`reply_id`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='图片附件表';

CREATE TABLE `zhuanfa_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(255) NOT NULL,
  `zhuanfa_id` bigint(20) NOT NULL COMMENT '转发的用户标识ID,对应用户表的onlyId',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `is_creater` int(11) NOT NULL DEFAULT '0' COMMENT '是否为创建者，0，否，1，是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;