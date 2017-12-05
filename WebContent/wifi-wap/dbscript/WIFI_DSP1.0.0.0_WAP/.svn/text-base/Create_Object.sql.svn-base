/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014-9-9 9:20:30                             */
/*==============================================================*/

set foreign_key_checks=0;

drop trigger trig_insert_app_click_detail;

drop trigger trig_delete_on_headline_reply;

drop trigger trig_insert_on_headline_reply;

drop trigger trig_insert_video_play;

drop table if exists SHIROUSER_MANAGE;

drop table if exists act_status;

drop table if exists act_type;

drop table if exists app_click_detail;

drop table if exists app_info;

drop table if exists auth_device_account_info;

drop table if exists consume_place;

drop table if exists headline_content;

drop index idx_hca_headline_id on headline_content_attach;

drop table if exists headline_content_attach;

drop table if exists headline_reply;

drop table if exists hometown;

drop table if exists mobile_reply_review;

drop table if exists notice;

drop index idx_pa_flash_sale_date on pin_action;

drop index idx_pa_owner_mobile on pin_action;

drop table if exists pin_action;

drop table if exists pin_action_hist;

drop table if exists pin_activity;

drop index idx_pin_act_table_owner_mobile on pin_createtable;

drop index idx_pin_act_table_createtdate on pin_createtable;

drop table if exists pin_createtable;

drop index idx_pcd_joindate on pin_createtable_detail;

drop index idx_pcd_mobile on pin_createtable_detail;

drop table if exists pin_createtable_detail;

drop index idx_padth_flash_sale_id on pin_createtable_detail_hist;

drop index idx_padth_action_date on pin_createtable_detail_hist;

drop index idx_padth_mobile on pin_createtable_detail_hist;

drop table if exists pin_createtable_detail_hist;

drop index idx_pin_act_table_createtdate on pin_createtable_hist;

drop table if exists pin_createtable_hist;

drop index idx_sn_send_time on sms_notice;

drop index idx_sn_create_time on sms_notice;

drop index idx_sn_mobile on sms_notice;

drop table if exists sms_notice;

drop table if exists student_report;

drop table if exists user_info;

drop table if exists video_info;

drop table if exists video_play;

drop table if exists website_click_detail;

drop table if exists website_info;

drop table if exists wifi_sensitive_word;

drop table if exists wifi_sysconfig;

/*==============================================================*/
/* Table: SHIROUSER_MANAGE                                      */
/*==============================================================*/
create table SHIROUSER_MANAGE
(
   id                   varchar(11) not null comment 'ID',
   account              varchar(11) not null default '账号',
   password             varchar(50) not null comment '密码',
   nickname             varchar(50) not null comment '昵称',
   createTime           timestamp not null default '0000-00-00 00:00:00' comment '创建时间',
   roles                varchar(50) default NULL comment '角色',
   primary key (id)
);

INSERT INTO SHIROUSER_MANAGE VALUES ('1','admin','00b7691d86d96aebd21dd9e138f90840','admin','2014-08-13 19:26:32','1');
commit;

/*==============================================================*/
/* Table: act_status                                            */
/*==============================================================*/
create table act_status
(
   act_status_id        tinyint not null comment '拼单状态ID',
   act_status_name      varchar(50) not null comment '拼单状态',
   primary key (act_status_id)
);

alter table act_status comment '拼单活动状态表';

insert into act_status values (1,'发布待审核');
insert into act_status values (2,'已发布(审核通过)');
insert into act_status values (3,'发布失败(审核不通过)');
insert into act_status values (4,'已过有效期');
insert into act_status values (5,'已成单');
insert into act_status values (6,'上传合照待审核');
insert into act_status values (7,'上传合照审核不通过');
insert into act_status values (8,'已完成');
insert into act_status values (9,'已提交消费发票已领补贴');
insert into act_status values (10,'已解散');
commit;

/*==============================================================*/
/* Table: act_type                                              */
/*==============================================================*/
create table act_type
(
   act_type_id          tinyint not null comment '拼单类型ID',
   act_type_name        varchar(50) not null comment '拼单类型',
   act_type_picture     varchar(200) comment '拼单类型对应图标路径',
   primary key (act_type_id)
);

alter table act_type comment '拼单活动类型表';

insert into act_type values (1,'餐饮','');
insert into act_type values (2,'运动','');
insert into act_type values (3,'娱乐','');
commit;

/*==============================================================*/
/* Table: app_click_detail                                      */
/*==============================================================*/
create table app_click_detail
(
   app_id               bigint not null comment '应用ID',
   mobile               varchar(11) not null comment '点击号码',
   oper_date            timestamp not null default CURRENT_TIMESTAMP comment '点击时间',
   primary key (app_id, mobile, oper_date)
);

alter table app_click_detail comment '应用点击明细表';

/*==============================================================*/
/* Table: app_info                                              */
/*==============================================================*/
create table app_info
(
   app_id               bigint not null auto_increment comment '应用ID',
   app_name             varchar(30)  comment '应用名称',
   app_desc             varchar(255) comment '描述',
   app_type             tinyint comment '应用分类
            1 游戏
            2 社交
            3 工具
            4 娱乐
            5 其它',
   app_for_system       varchar(255) comment '应用支持系统',
   icon                 mediumblob  comment '应用图标',
   app_file_url         varchar(1024) not null comment '应用下载URL',
   app_version          varchar(50) comment '版本信息',
   app_size             int comment '应用大小(字节)',
   app_download_times   bigint default 0 comment '下载次数',
   app_star_level       tinyint comment '推荐星级',
   app_developer        varchar(255) comment '应用开发者',
   app_demo_pic1        mediumblob comment '应用界面截图1',
   app_demo_pic2        mediumblob comment '应用界面截图2',
   app_demo_pic3        mediumblob comment '应用界面截图3',
   app_demo_pic4        mediumblob comment '应用界面截图4',
   last_update_time     timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后更新时间',
   is_top_area          tinyint default 0 comment '是否置顶
            1 置顶
            0 非置顶',
   app_top_pic          mediumblob comment '置顶图片',
   app_status			int default 0 comment '状态，0，表示新增状态 1，表示在线状态 2，表示下线状态',
   click_times			int default 0 comment 'app点击量',
   primary key (app_id)
);

alter table app_info comment '应用表';

/*==============================================================*/
/* Table: auth_device_account_info                              */
/*==============================================================*/
create table auth_device_account_info
(
   user_account         varchar(10) not null comment '认证账号',
   user_passwd          varchar(6) not null comment '认证账号密码',
   used_flag            tinyint default 0 comment '认证设备账号使用状况
            0 未使用
            1 已使用',
   primary key (user_account)
);

alter table auth_device_account_info comment '认证设备账号表';

/*==============================================================*/
/* Table: consume_place                                         */
/*==============================================================*/
create table consume_place
(
   consume_place_id     int not null auto_increment comment 'ID',
   consume_place_name   varchar(100) not null comment '地点名称',
   consume_place_addr   varchar(200) not null comment '地点所在地址',
   consume_place_desc   varchar(500) comment '地点描述',
   act_type_id          tinyint not null comment '拼单类型ID',
   publish_date         timestamp default CURRENT_TIMESTAMP comment '发布时间',
   publish_user         varchar(100) comment '发布者',
   business_code        varchar(6) not null comment '商家渠道编码',
   contacts             varchar(20) comment '商家联系人',
   contact_phone        varchar(20) comment '商家联系电话',
   order_phone          varchar(20) comment '商家预定电话',
   primary key (consume_place_id)
);

alter table consume_place comment '拼单地点表';

insert into consume_place (
   consume_place_name ,
   consume_place_addr ,
   consume_place_desc ,
   act_type_id        ,
   publish_user       ,
   business_code      ,
   contacts           ,
   contact_phone      ,
   order_phone       
) values
('好伦哥',
 '学府路26号',
 '已签合作意向',
 1,
 '管理员',
 '010001',
 '戴南',
 '15901282066',
 '69702778');
insert into consume_place (
   consume_place_name ,
   consume_place_addr ,
   consume_place_desc ,
   act_type_id        ,
   publish_user       ,
   business_code      ,
   contacts           ,
   contact_phone      ,
   order_phone       
) values
('兄弟联球迷餐厅',
 '松园路132号',
 '已签合作意向',
 1,
 '管理员',
 '010002',
 '杨意',
 '13911519776',
 '18612305578');
insert into consume_place (
   consume_place_name ,
   consume_place_addr ,
   consume_place_desc ,
   act_type_id        ,
   publish_user       ,
   business_code      ,
   contacts           ,
   contact_phone      ,
   order_phone       
) values
('海狼西餐',
 '松园路126号',
 '已签合作意向',
 1,
 '管理员', 
 '010003',
 '郑重',
 '13581650371',
 '60741442');
insert into consume_place (
   consume_place_name ,
   consume_place_addr ,
   consume_place_desc ,
   act_type_id        ,
   publish_user       ,
   business_code      ,
   contacts           ,
   contact_phone      ,
   order_phone       
) values
('王林串串香火锅',
 '松园路45号',
 '已签合作意向',
 1,
 '管理员', 
 '010004',
 '王发成',
 '13452736978',
 '89749118');
insert into consume_place (
   consume_place_name ,
   consume_place_addr ,
   consume_place_desc ,
   act_type_id        ,
   publish_user       ,
   business_code      ,
   contacts           ,
   contact_phone      ,
   order_phone       
) values
('美好生活烤肉',
 '松园路148号',
 '已签合作意向',
 1,
 '管理员', 
 '010005',
 '徐强',
 '13836079886',
 '58422127');
insert into consume_place (
   consume_place_name ,
   consume_place_addr ,
   consume_place_desc ,
   act_type_id        ,
   publish_user       ,
   business_code      ,
   contacts           ,
   contact_phone      ,
   order_phone       
) values
('红辣椒',
 '松园路31号政法大学东门',
 '已签合作意向',
 1,
 '管理员', 
 '010006',
 '郑娜',
 '15834966888',
 '69712802');
insert into consume_place (
   consume_place_name ,
   consume_place_addr ,
   consume_place_desc ,
   act_type_id        ,
   publish_user       ,
   business_code      ,
   contacts           ,
   contact_phone      ,
   order_phone       
) values
('家合饭店',
 '松园村21号政法东门对面胡同20米',
 '已签合作意向',
 1,
 '管理员', 
 '010007',
 '顾群',
 '13911030118',
 '80116605');
insert into consume_place (
   consume_place_name ,
   consume_place_addr ,
   consume_place_desc ,
   act_type_id        ,
   publish_user       ,
   business_code      ,
   contacts           ,
   contact_phone      ,
   order_phone       
) values
('锦翔台球城',
 '学府路26号 好伦哥旁4楼',
 '已签合作意向',
 3,
 '管理员', 
 '030001',
 '张敏峰',
 '13901298870',
 '13901298870'); 
commit;

/*==============================================================*/
/* Table: headline_content                                      */
/*==============================================================*/
create table headline_content
(
   headline_id          bigint not null auto_increment comment '头条ID',
   mobile               varchar(11) not null comment '号码
            当content_type_id=6表示公告时,
            此处的号码为发布公告人的号码
            其它情况下 均表示用户表里面的号码',
   nickname             varchar(11) comment '昵称',
   content_type_id      smallint not null comment '头条类型
            1 灌水
            2 新闻
            3 八卦
            4 招聘
            5 求助
            6 公告',
   title                varchar(100) comment '替换敏感词后的头条标题',
   orig_title           varchar(100) comment '原始头条标题',
   content              varchar(10000) comment '替换敏感词后的头条正文',
   orig_content         varchar(10000) comment '原始头条正文',
   oper_date            timestamp default CURRENT_TIMESTAMP comment '操作时间',
   create_date 			timestamp default CURRENT_TIMESTAMP comment '发帖日期',
   audit_user           varchar(20) comment '审核人',
   audit_time           timestamp comment '审核时间',
   audit_comment        varchar(200) comment '审核意见',
   status               smallint default 0 comment '头条状态
            0 初始未审核
            1 审核未通过
            2 审核通过
            3 已删除',
   top_number           bigint comment '置顶标记
            1表示 最高置顶 2 其次  展示时候按此配置页面',
   if_support_comment   tinyint default 1 comment '是否可以评论
            1 可以
            2 不可以',
   reply_num            int default 0 comment '最新评论数',
   praise_num           int default 0 comment '最新点赞数',
   click_num            int DEFAULT 0 COMMENT '最新点击量',
   primary key (headline_id)
);

alter table headline_content comment '头条';

/*==============================================================*/
/* Table: headline_content_attach                               */
/*==============================================================*/
create table headline_content_attach
(
   picture_serialnum    bigint not null auto_increment comment '头条对应图片ID',
   picture              mediumblob comment '头条对应图片',
   headline_id          bigint not null comment '头条ID',
   primary key (picture_serialnum)
);

alter table headline_content_attach comment '头条附件';

/*==============================================================*/
/* Index: idx_hca_headline_id                                   */
/*==============================================================*/
create index idx_hca_headline_id on headline_content_attach
(
   headline_id
);

/*==============================================================*/
/* Table: headline_reply                                        */
/*==============================================================*/
create table headline_reply
(
   reply_id             bigint not null auto_increment comment '评论ID',
   headline_id          bigint not null comment '头条ID',
   mobile               varchar(11) not null comment '号码',
   nickname             varchar(11) comment '昵称',
   reply_type           tinyint comment '评论类型
            1 评论
            2 点赞',
   content              varchar(140) comment '替换敏感词后的回复内容',
   orig_content         varchar(140) comment '原始回复内容',
   parent_reply_id      bigint not null comment '评论ID对应上级评论ID
            0 表示 此评论已经是顶级节点',
   parent_mobile        varchar(11) not null comment '评论ID对应上级号码',
   reply_date           timestamp default CURRENT_TIMESTAMP comment '评论时间',
   audit_user           varchar(20) comment '审核人',
   audit_time           timestamp comment '审核时间',
   audit_comment        varchar(200) comment '审核意见',
   status               smallint default 0 comment '状态
            0 初始未审核
            1 审核未通过
            2 审核通过
            3 已删除',
   primary key (reply_id)
);

alter table headline_reply comment '评论';

/*==============================================================*/
/* Table: hometown                                              */
/*==============================================================*/
create table hometown
(
   province_id          tinyint not null comment '省ID',
   province_name        varchar(30) comment '省份',
   city_id              smallint not null comment '城市ID',
   city_name            varchar(30) comment '城市',
   primary key (province_id, city_id)
);

alter table hometown comment '地域表';

insert into hometown (province_id, province_name, city_id, city_name)
values (1, '北京', 1, '北京');
insert into hometown (province_id, province_name, city_id, city_name)
values (2, '上海', 2, '上海');
insert into hometown (province_id, province_name, city_id, city_name)
values (3, '天津', 3, '天津');
insert into hometown (province_id, province_name, city_id, city_name)
values (4, '重庆', 4, '重庆');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '黑龙江', 477, '大兴安岭');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '黑龙江', 156, '大庆');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '黑龙江', 157, '哈尔滨');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '黑龙江', 158, '鹤岗');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '黑龙江', 159, '黑河');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '黑龙江', 160, '鸡西');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '黑龙江', 161, '佳木斯');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '黑龙江', 162, '牡丹江');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '黑龙江', 163, '七台河');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '黑龙江', 164, '齐齐哈尔');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '黑龙江', 165, '双鸭山');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '黑龙江', 167, '绥化');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '黑龙江', 169, '伊春');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '吉林', 523, '松原');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '吉林', 531, '延边');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '吉林', 223, '白城');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '吉林', 224, '白山');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '吉林', 225, '长春');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '吉林', 231, '吉林');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '吉林', 232, '辽源');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '吉林', 234, '四平');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '吉林', 235, '通化');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 286, '鞍山');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 288, '本溪');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 289, '朝阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 290, '大连');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 291, '丹东');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 292, '抚顺');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 293, '阜新');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 295, '葫芦岛');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 297, '锦州');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 298, '辽阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 299, '盘锦');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 300, '沈阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 301, '铁岭');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '辽宁', 305, '营口');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '内蒙古', 465, '阿拉善盟');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '内蒙古', 481, '鄂尔多斯');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '内蒙古', 668, '呼伦贝尔');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '内蒙古', 552, '乌兰察布');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '内蒙古', 556, '兴安盟');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '内蒙古', 675, '巴彦淖尔');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '内蒙古', 677, '锡林郭勒盟');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '内蒙古', 306, '包头');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '内蒙古', 307, '赤峰');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '内蒙古', 310, '呼和浩特');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '内蒙古', 314, '通辽');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '内蒙古', 315, '乌海');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '河北', 108, '保定');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '河北', 110, '沧州');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '河北', 111, '承德');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '河北', 114, '邯郸');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '河北', 116, '衡水');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '河北', 117, '廊坊');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '河北', 119, '秦皇岛');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '河北', 122, '石家庄');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '河北', 123, '唐山');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '河北', 126, '邢台');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '河北', 127, '张家口');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 545, '济源');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 129, '安阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 131, '鹤壁');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 133, '焦作');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 134, '开封');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 135, '漯河');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 137, '洛阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 138, '南阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 139, '平顶山');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 140, '濮阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 141, '三门峡');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 142, '商丘');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 147, '新乡');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 148, '信阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 149, '许昌');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 151, '郑州');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 152, '驻马店');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '河南', 153, '周口');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 537, '云浮');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 538, '肇庆');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 541, '中山');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 542, '珠海');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 54, '潮州');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 56, '东莞');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 57, '佛山');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 60, '广州');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 61, '河源');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 64, '惠州');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 65, '江门');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 66, '揭阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 70, '茂名');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 71, '梅州');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 74, '清远');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 75, '汕头');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 76, '汕尾');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 77, '韶关');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 78, '深圳');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 82, '阳江');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '广东', 84, '湛江');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 482, '天门');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 495, '汉江');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 498, '黄岗');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 501, '荆洲');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 560, '神农架');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 561, '潜江');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 174, '鄂州');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 175, '恩施');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 178, '黄石');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 179, '仙桃');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 180, '荆门');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 182, '荆州');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 187, '十堰');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 188, '随州');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 190, '武汉');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 193, '咸宁');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 194, '襄阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 195, '孝感');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 196, '宜昌');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '湖北', 200, '黄冈');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 348, '泰安');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 350, '威海');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 351, '潍坊');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 354, '烟台');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 356, '枣庄');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 358, '淄博');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 588, '菏泽');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 328, '滨州');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 329, '德州');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 330, '东营');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 332, '济南');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 333, '济宁');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 334, '莱芜');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 337, '聊城');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 339, '临沂');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 342, '青岛');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, '山东', 345, '日照');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '浙江', 431, '杭州');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '浙江', 432, '湖州');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '浙江', 433, '嘉兴');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '浙江', 437, '金华');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '浙江', 439, '丽水');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '浙江', 441, '宁波');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '浙江', 442, '衢州');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '浙江', 444, '绍兴');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '浙江', 446, '台州');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '浙江', 449, '温州');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '浙江', 457, '舟山');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 473, '池州');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 558, '毫州');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 5, '安庆');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 6, '蚌埠');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 9, '滁州');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 10, '阜阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 11, '合肥');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 12, '淮北');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 13, '淮南');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 14, '黄山');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 15, '六安');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 16, '马鞍山');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 17, '宿州');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 19, '铜陵');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 20, '芜湖');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '安徽', 21, '宣城');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '江苏', 539, '镇江');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '江苏', 241, '常州');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '江苏', 246, '淮安');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '江苏', 253, '连云港');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '江苏', 254, '南京');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '江苏', 255, '南通');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '江苏', 259, '苏州');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '江苏', 260, '宿迁');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '江苏', 263, '泰州');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '江苏', 265, '无锡');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '江苏', 269, '徐州');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '江苏', 270, '盐城');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '江苏', 271, '扬州');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '江西', 533, '宜春');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '江西', 272, '抚州');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '江西', 273, '赣州');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '江西', 274, '吉安');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '江西', 275, '景德镇');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '江西', 277, '九江');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '江西', 280, '南昌');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '江西', 281, '萍乡');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '江西', 282, '上饶');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '江西', 283, '新余');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '江西', 285, '鹰潭');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 416, '楚雄');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 417, '大理');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 418, '普洱');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 420, '昆明');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 421, '丽江');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 422, '曲靖');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 424, '文山');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 425, '玉溪');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 426, '昭通');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 469, '保山');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 478, '德宏');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 479, '迪庆');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 497, '红河');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 506, '临沧');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 513, '怒江');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '云南', 559, '西双版纳');
insert into hometown (province_id, province_name, city_id, city_name)
values (19, '宁夏', 681, '中卫');
insert into hometown (province_id, province_name, city_id, city_name)
values (19, '宁夏', 320, '固原');
insert into hometown (province_id, province_name, city_id, city_name)
values (19, '宁夏', 322, '石嘴山');
insert into hometown (province_id, province_name, city_id, city_name)
values (19, '宁夏', 323, '吴忠');
insert into hometown (province_id, province_name, city_id, city_name)
values (19, '宁夏', 324, '银川');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '青海', 490, '果洛');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '青海', 491, '海北');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '青海', 492, '海东');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '青海', 494, '海西');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '青海', 499, '黄南');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '青海', 536, '玉树');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '青海', 573, '海南');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '青海', 326, '西宁');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, '山西', 371, '长治');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, '山西', 372, '大同');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, '山西', 374, '晋城');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, '山西', 375, '临汾');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, '山西', 376, '朔州');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, '山西', 377, '太原');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, '山西', 378, '忻州');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, '山西', 379, '阳泉');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, '山西', 381, '运城');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, '山西', 500, '晋中');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, '山西', 509, '吕梁');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '陕西', 359, '安康');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '陕西', 360, '宝鸡');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '陕西', 361, '汉中');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '陕西', 364, '铜川');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '陕西', 365, '渭南');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '陕西', 366, '西安');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '陕西', 367, '咸阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '陕西', 368, '延安');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '陕西', 369, '榆林');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '陕西', 522, '商洛');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 543, '株洲');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 201, '常德');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 202, '长沙');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 203, '郴州');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 204, '衡阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 206, '怀化');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 212, '娄底');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 214, '邵阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 215, '湘潭');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 217, '益阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 218, '永州');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 219, '岳阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 220, '张家界');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '湖南', 222, '湘西自治州');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '福建', 26, '福州');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '福建', 28, '龙岩');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '福建', 30, '南平');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '福建', 31, '宁德');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '福建', 32, '莆田');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '福建', 33, '泉州');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '福建', 34, '三明');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '福建', 37, '厦门');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '福建', 39, '漳州');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 480, '定西');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 486, '甘南');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 508, '陇南');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 518, '庆阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 41, '白银');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 43, '嘉峪关');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 44, '金昌');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 45, '酒泉');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 46, '兰州');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 47, '临夏');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 48, '平凉');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 49, '天水');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 50, '武威');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '甘肃', 52, '张掖');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 383, '成都');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 384, '德阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 386, '广元');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 387, '乐山');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 388, '泸州');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 389, '眉山');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 390, '绵阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 391, '南充');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 392, '内江');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 393, '攀枝花');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 395, '遂宁');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 397, '雅安');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 398, '宜宾');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 399, '自贡');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 464, '阿坝');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 467, '巴中');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 476, '达州');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 488, '甘孜');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 489, '广安');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 504, '凉山');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '四川', 544, '资阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 483, '防城港');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 484, '崇左');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 562, '来宾');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 563, '贺州');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 564, '贵港');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 85, '百色');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 86, '北海');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 88, '桂林');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 89, '河池');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 90, '柳州');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 91, '南宁');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 93, '钦州');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 94, '梧州');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '广西', 95, '玉林');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '贵州', 470, '毕节');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '贵州', 514, '黔东南');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '贵州', 516, '黔南');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '贵州', 517, '黔西南');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '贵州', 526, '铜仁');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '贵州', 96, '安顺');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '贵州', 99, '贵阳');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '贵州', 101, '六盘水');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '贵州', 102, '遵义');
insert into hometown (province_id, province_name, city_id, city_name)
values (29, '海南', 493, '三亚');
insert into hometown (province_id, province_name, city_id, city_name)
values (29, '海南', 565, '三沙');
insert into hometown (province_id, province_name, city_id, city_name)
values (29, '海南', 103, '海口');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '西藏', 505, '林芝');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '西藏', 512, '那曲');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '西藏', 519, '日喀则');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '西藏', 521, '山南');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '西藏', 22, '阿里');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '西藏', 23, '昌都');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '西藏', 24, '拉萨');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 400, '阿克苏');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 401, '阿勒泰');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 402, '阿拉尔');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 403, '昌吉');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 404, '哈密');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 405, '和田');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 406, '喀什');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 407, '克拉玛依');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 408, '图木舒克');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 409, '北屯');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 410, '石河子');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 411, '塔城');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 412, '吐鲁番');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 413, '乌鲁木齐');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 414, '博尔塔拉');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 471, '巴音敦楞');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 472, '克孜勒苏');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 503, '五家渠');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '新疆', 532, '伊犁');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',701,'台北市');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',702,'高雄市');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',703,'基隆市');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',704,'台中市');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',705,'台南市');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',706,'新竹市');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',707,'嘉义市');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',708,'台北县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',709,'高雄县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',710,'台中县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',711,'台南县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',712,'新竹县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',713,'嘉义县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',714,'宜兰县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',715,'桃源县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',716,'苗栗县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',717,'彰化县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',718,'南投县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',719,'屏东县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',720,'花莲县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',721,'台东县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',722,'云林县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'台湾',723,'澎湖县');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (33,'香港',801,'香港');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (34,'澳门',901,'澳门');

commit;

/*==============================================================*/
/* Table: mobile_reply_review                                   */
/*==============================================================*/
create table mobile_reply_review
(
   mobile               varchar(11) not null comment '号码',
   last_click_reply_time datetime comment '最后点击我的评论时间',
   last_click_praise_time datetime comment '最后点击我的赞时间',
   primary key (mobile)
);

alter table mobile_reply_review comment '用户查看评论表';

/*==============================================================*/
/* Table: notice                                                */
/*==============================================================*/
create table notice
(
   notice_id            int not null auto_increment comment '公告ID',
   notice_name          varchar(100) not null comment '公告标题',
   notice_content       text not null comment '公告正文',
   notice_status_id     tinyint not null comment '公告状态
            1 有效
            2 失效',
   notice_type_id       tinyint not null comment '公告类型
            1 通知
            2 公告',
   insert_date          timestamp default CURRENT_TIMESTAMP comment '入库时间',
   begin_time           timestamp,
   end_time             timestamp,
   primary key (notice_id)
);

alter table notice comment '公告表';

/*==============================================================*/
/* Table: pin_action                                            */
/*==============================================================*/
create table pin_action
(
   flash_sale_id        bigint not null auto_increment comment '抢单ID',
   owner_mobile         varchar(11) not null comment '抢单号码',
   flash_sale_date      timestamp not null comment '抢单时间',
   expired_date         timestamp comment '抢单有效期',
   createtable_date     timestamp comment '创建桌时间',
   primary key (flash_sale_id)
);

alter table pin_action comment '抢单表(只记录用户的有效成功抢单)';

/*==============================================================*/
/* Index: idx_pa_owner_mobile                                   */
/*==============================================================*/
create index idx_pa_owner_mobile on pin_action
(
   owner_mobile
);

/*==============================================================*/
/* Index: idx_pa_flash_sale_date                                */
/*==============================================================*/
create index idx_pa_flash_sale_date on pin_action
(
   flash_sale_date
);

/*==============================================================*/
/* Table: pin_action_hist                                       */
/*==============================================================*/
create table pin_action_hist
(
   hist_id              bigint not null auto_increment comment 'ID(自增)',
   flash_sale_id        bigint not null comment '抢单ID',
   owner_mobile         varchar(11) not null comment '抢单号码',
   action_date          timestamp not null default CURRENT_TIMESTAMP comment '变动时间',
   action_type          tinyint not null comment '变动动作:
            1 新增
            2 修改
            3 删除',
   action_desc          varchar(100) comment '变动描述',
   flash_sale_date      timestamp not null comment '抢单时间',
   createtable_date     timestamp comment '创建桌时间',
   primary key (hist_id)
);

alter table pin_action_hist comment '抢单历史表';

/*==============================================================*/
/* Table: pin_activity                                          */
/*==============================================================*/
create table pin_activity
(
   activity_id          int not null comment '活动ID',
   activity_name        varchar(100) not null comment '活动名称',
   activity_status      smallint not null comment '活动状态
            1 有效
            2 失效',
   start_week_day       smallint comment '活动开始于周几',
   end_week_day         smallint comment '活动结束于周几',
   start_hour           smallint comment '活动当天开始时间',
   end_hour             smallint comment '活动当天截止时间',
   quota                int comment '活动每天的抢单名额上限',
   cash_subsidy         int comment '活动每个抢单所补贴的现金数量(单位:元)',
   sale_person_num      int comment '活动有效拼桌要求的人数',
   persion_participate_cnt int comment '活动每人周期内参与抢单或拼单的次数',
   pin_action_invalidate int comment '活动抢单单有效期(单位:分钟)',
   pin_createtable_invalidate int comment '活动拼单有效期(单位:天)',
   visit_pv_cnt         bigint default 0 comment '活动累计参与人次数(点击量 包括抢桌、拼桌)',
   rule_desc            varchar(20000) comment '活动规则描述',
   primary key (activity_id)
);

alter table pin_activity comment '抢单活动表';

insert into pin_activity(
   activity_id                ,
   activity_name              ,
   activity_status            ,
   start_week_day             ,
   end_week_day               ,
   start_hour                 ,
   end_hour                   ,
   quota                      ,
   cash_subsidy               ,
   sale_person_num            ,
   persion_participate_cnt 		,
   pin_action_invalidate 			,
   pin_createtable_invalidate ,
   visit_pv_cnt  ,
   rule_desc      				
)
values(
   1,
   '抢桌活动',
   1,
   1,
   5,
   11,
   14,
   10,
   100,
   9,
   1,
   5,
   5,
   0,
   '' );
 commit;

/*==============================================================*/
/* Table: pin_createtable                                       */
/*==============================================================*/
create table pin_createtable
(
   flash_sale_id        bigint not null comment '抢单ID',
   act_desc             varchar(31) comment '拼单说明',
   act_type_id          tinyint not null comment '拼单类型ID',
   act_status_id        tinyint not null comment '拼单状态ID',
   consume_place_id     int not null comment '拼单地点ID',
   createtable_date     timestamp default CURRENT_TIMESTAMP comment '创建桌时间',
   expired_date         timestamp comment '抢单有效期',
   owner_mobile         varchar(11) not null comment '拼单对应的桌主',
   consume_pic          mediumblob comment '开桌合照',
   free_seat            tinyint comment '剩余席位',
   audit_user           varchar(20) comment '审核人',
   audit_time           timestamp comment '审核时间',
   audit_status         tinyint comment '审核状态',
   audit_comment        varchar(200) comment '审核意见',
   verification_code    varchar(20) comment '只给桌主提供,用于后续到商户消费',
   mo_sms_time          timestamp comment '商户上传消费短信时间',
   primary key (flash_sale_id)
);

alter table pin_createtable comment '拼单活动表';

/*==============================================================*/
/* Index: idx_pin_act_table_createtdate                         */
/*==============================================================*/
create index idx_pin_act_table_createtdate on pin_createtable
(
   createtable_date
);

/*==============================================================*/
/* Index: idx_pin_act_table_owner_mobile                        */
/*==============================================================*/
create index idx_pin_act_table_owner_mobile on pin_createtable
(
   owner_mobile
);

/*==============================================================*/
/* Table: pin_createtable_detail                                */
/*==============================================================*/
create table pin_createtable_detail
(
   flash_sale_id        bigint not null comment '抢单ID',
   mobile               varchar(11) not null comment '拼单人号码',
   join_date            timestamp not null default CURRENT_TIMESTAMP comment '拼单人加入时间',
   is_owner_mobile      tinyint not null comment '是否桌主
            1 是桌主
            0 非桌主',
   is_footmark          tinyint not null default 0 comment '是否用户阅读后转为足迹',
   primary key (flash_sale_id, mobile)
);

alter table pin_createtable_detail comment '拼单活动明细表';

/*==============================================================*/
/* Index: idx_pcd_mobile                                        */
/*==============================================================*/
create index idx_pcd_mobile on pin_createtable_detail
(
   mobile
);

/*==============================================================*/
/* Index: idx_pcd_joindate                                      */
/*==============================================================*/
create index idx_pcd_joindate on pin_createtable_detail
(
   join_date
);

/*==============================================================*/
/* Table: pin_createtable_detail_hist                           */
/*==============================================================*/
create table pin_createtable_detail_hist
(
   hist_id              bigint not null auto_increment comment 'ID(自增)',
   flash_sale_id        bigint not null comment '抢单ID',
   mobile               varchar(11) not null comment '拼单人号码',
   is_owner_mobile      tinyint not null comment '是否桌主
            1 是桌主
            0 非桌主',
   action_date          timestamp not null default CURRENT_TIMESTAMP comment '变动时间',
   action_type          tinyint not null comment '变动动作:
            1  加入
            2  退出
            3  被删除',
   action_desc          varchar(100) comment '变动描述',
   join_date            timestamp not null comment '拼单人加入时间',
   primary key (hist_id)
);

alter table pin_createtable_detail_hist comment '拼单活动明细变动历史表';

/*==============================================================*/
/* Index: idx_padth_mobile                                      */
/*==============================================================*/
create index idx_padth_mobile on pin_createtable_detail_hist
(
   mobile
);

/*==============================================================*/
/* Index: idx_padth_action_date                                 */
/*==============================================================*/
create index idx_padth_action_date on pin_createtable_detail_hist
(
   action_date
);

/*==============================================================*/
/* Index: idx_padth_flash_sale_id                               */
/*==============================================================*/
create index idx_padth_flash_sale_id on pin_createtable_detail_hist
(
   flash_sale_id
);

/*==============================================================*/
/* Table: pin_createtable_hist                                  */
/*==============================================================*/
create table pin_createtable_hist
(
   hist_id              bigint not null auto_increment comment 'ID(自增)',
   flash_sale_id        bigint not null comment '抢单ID',
   act_desc             varchar(31) comment '拼单说明',
   act_type_id          tinyint not null comment '拼单类型ID',
   act_status_id        tinyint not null comment '拼单状态ID',
   consume_place_id     int not null comment '拼单地点ID',
   owner_mobile         varchar(11) not null comment '拼单对应的桌主',
   consume_pic          mediumblob comment '开桌合照',
   action_date          timestamp not null default CURRENT_TIMESTAMP comment '变动时间',
   action_type          tinyint not null comment '变动动作:
            1 新增
            2 修改
            3 删除',
   action_desc          varchar(100) comment '变动描述',
   createtable_date     timestamp comment '创建桌时间',
   expired_date         timestamp comment '抢单有效期',
   audit_user           varchar(20) comment '审核人员',
   audit_time           timestamp comment '审核时间',
   audit_status         tinyint comment '审核状态',
   audit_comment        varchar(200) comment '审核意见',
   verification_code    varchar(20) comment '只给桌主提供,用于后续到商户消费',
   mo_sms_time          timestamp comment '商户上传消费短信时间',
   primary key (hist_id)
);

alter table pin_createtable_hist comment '拼单活动历史表';

/*==============================================================*/
/* Index: idx_pin_act_table_createtdate                         */
/*==============================================================*/
create index idx_pin_act_table_createtdate on pin_createtable_hist
(
   createtable_date
);

/*==============================================================*/
/* Table: sms_notice                                            */
/*==============================================================*/
create table sms_notice
(
   id                   bigint not null auto_increment comment 'ID',
   mobile               varchar(11) comment '手机号码',
   content              varchar(140) comment '下发内容',
   priority             smallint comment '优先级
            1 最高级
            2 次之',
   create_time          timestamp default CURRENT_TIMESTAMP comment '记录创建时间',
   send_status          smallint comment '发送状态
            0 待发送
            1 发送中
            2 已发送
            3 发送出错',
   send_result          varchar(100) comment '发送结果描述',
   send_time            timestamp comment '发送时间',
   retry_times          tinyint default 1 comment '重试次数',
   gw_sms_id            varchar(20) comment '企业下行消息标识ID',
   primary key (id)
);

alter table sms_notice comment '短信下发通知表';

/*==============================================================*/
/* Index: idx_sn_mobile                                         */
/*==============================================================*/
create index idx_sn_mobile on sms_notice
(
   mobile
);

/*==============================================================*/
/* Index: idx_sn_create_time                                    */
/*==============================================================*/
create index idx_sn_create_time on sms_notice
(
   create_time
);

/*==============================================================*/
/* Index: idx_sn_send_time                                      */
/*==============================================================*/
create index idx_sn_send_time on sms_notice
(
   send_time
);

/*==============================================================*/
/* Table: student_report                                        */
/*==============================================================*/
create table student_report
(
   Id                   int(11) not null auto_increment comment 'ID',
   msisdn               varchar(20) not null default '' comment '手机号',
   report_pic           mediumblob not null comment '报告图片名',
   result               int(1) not null default 0 comment '0：待审核；1：通过；2：未通过；',
   result_desc          varchar(255) default NULL comment '审核失败原因',
   result_back          int(1) not null default 0 comment '结果反馈状态 0：未反馈；1：已反馈',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
   create_user          varchar(20) comment '创建人',
   is_footmark          tinyint not null default 0 comment '是否用户阅读后转为足迹',
   primary key (Id)
);

alter table student_report comment '新生入学报道表';

/*==============================================================*/
/* Table: user_info                                             */
/*==============================================================*/
create table user_info
(
   mobile               varchar(11) not null comment '手机号',
   nickname             varchar(11) not null comment '昵称',
   sex                  char(1) comment '性别',
   head_show            mediumblob comment '头像',
   province             varchar(20) comment '老家所在省份',
   city                 varchar(50) comment '老家所在城市',
   reg_date             timestamp default CURRENT_TIMESTAMP comment '注册时间',
   primary key (mobile),
   unique key AK_unique_nickname (nickname)
);

alter table user_info comment '用户表';

INSERT INTO user_info VALUES ('admin', '管理员', '男', NULL, '北京', '北京', now());
commit;

/*==============================================================*/
/* Table: video_info                                            */
/*==============================================================*/
create table video_info
(
   video_id             bigint not null auto_increment comment '视频ID',
   video_name           varchar(50)  comment '视频名称',
   video_desc           varchar(255) comment '视频描述',
   video_type           tinyint comment '视频类型
            1 电影音乐
            2 娱乐八卦
            3 微博江湖
            4 其它',
   resource_path        varchar(255) not null comment '资源路径',
   video_pic            mediumblob comment '预览图 名称',
   show_time_long       bigint comment '播放时长(单位:秒)',
   play_cnt             bigint default 0 comment '视频点播累计次数',
   last_update_time     timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后更新时间',
   is_top_area          tinyint default 0 comment '是否置顶
            1 置顶
            0 非置顶',
   video_top_pic        mediumblob comment '置顶图片',
   video_status 		int(4) NOT NULL DEFAULT '0' COMMENT '状态，0，表示新增状态 1，表示在线状态 2，表示下线状态',
   primary key (video_id),
   unique key AK_resource_path (resource_path)
);

alter table video_info comment '视频信息';

/*==============================================================*/
/* Table: video_play                                            */
/*==============================================================*/
create table video_play
(
   video_id             bigint not null comment '视频ID',
   mobile               varchar(11) not null comment '点击号码',
   oper_date            timestamp not null default CURRENT_TIMESTAMP comment '点击时间',
   primary key (video_id, mobile, oper_date)
);

alter table video_play comment '视频播放信息';

/*==============================================================*/
/* Table: website_click_detail                                  */
/*==============================================================*/
create table website_click_detail
(
   id                   bigint not null comment '网站ID',
   mobile               varchar(11) not null comment '点击号码',
   nickname             varchar(8) not null comment '昵称',
   oper_date            timestamp not null default CURRENT_TIMESTAMP comment '点击时间',
   primary key (id, mobile, nickname, oper_date)
);

alter table website_click_detail comment '网站点击明细表';

/*==============================================================*/
/* Table: website_info                                          */
/*==============================================================*/
create table website_info
(
   id                   bigint not null auto_increment comment 'ID',
   title                varchar(100) comment '网站标题',
   type                 tinyint comment '网站类型
            1 常用
            2 新闻
            3 体育
            4 购物',
   url                  varchar(1000) comment '网站URL地址',
   picture              mediumblob comment '网站图标',
   content              varchar(500) comment '网站简介',
   oper_date            timestamp default CURRENT_TIMESTAMP comment '入库时间',
   oper_user            varchar(20) comment '入库人',
   primary key (id)
);

alter table website_info comment '网站信息表';

/*==============================================================*/
/* Table: wifi_sensitive_word                                   */
/*==============================================================*/
create table wifi_sensitive_word
(
   sensitive_word       varchar(20) not null,
   primary key (sensitive_word)
);

alter table wifi_sensitive_word comment '敏感词';

insert into wifi_sensitive_word values ('阿扁推翻');
insert into wifi_sensitive_word values ('阿宾');
insert into wifi_sensitive_word values ('阿賓');
insert into wifi_sensitive_word values ('挨了一炮');
insert into wifi_sensitive_word values ('爱液横流');
insert into wifi_sensitive_word values ('安街逆');
insert into wifi_sensitive_word values ('安局办公楼');
insert into wifi_sensitive_word values ('安局豪华');
insert into wifi_sensitive_word values ('安门事');
insert into wifi_sensitive_word values ('安眠藥');
insert into wifi_sensitive_word values ('案的准确');
insert into wifi_sensitive_word values ('八九民');
insert into wifi_sensitive_word values ('八九学');
insert into wifi_sensitive_word values ('八九政治');
insert into wifi_sensitive_word values ('把病人整');
insert into wifi_sensitive_word values ('把邓小平');
insert into wifi_sensitive_word values ('把学生整');
insert into wifi_sensitive_word values ('罢工门');
insert into wifi_sensitive_word values ('白黄牙签');
insert into wifi_sensitive_word values ('败培训');
insert into wifi_sensitive_word values ('办本科');
insert into wifi_sensitive_word values ('办理本科');
insert into wifi_sensitive_word values ('办理各种');
insert into wifi_sensitive_word values ('办理票据');
insert into wifi_sensitive_word values ('办理文凭');
insert into wifi_sensitive_word values ('办理真实');
insert into wifi_sensitive_word values ('办理证书');
insert into wifi_sensitive_word values ('办理资格');
insert into wifi_sensitive_word values ('办文凭');
insert into wifi_sensitive_word values ('办怔');
insert into wifi_sensitive_word values ('办证');
insert into wifi_sensitive_word values ('半刺刀');
insert into wifi_sensitive_word values ('辦毕业');
insert into wifi_sensitive_word values ('辦證');
insert into wifi_sensitive_word values ('谤罪获刑');
insert into wifi_sensitive_word values ('磅解码器');
insert into wifi_sensitive_word values ('磅遥控器');
insert into wifi_sensitive_word values ('宝在甘肃修');
insert into wifi_sensitive_word values ('保过答案');
insert into wifi_sensitive_word values ('报复执法');
insert into wifi_sensitive_word values ('爆发骚');
insert into wifi_sensitive_word values ('北省委门');
insert into wifi_sensitive_word values ('被打死');
insert into wifi_sensitive_word values ('被指抄袭');
insert into wifi_sensitive_word values ('被中共');
insert into wifi_sensitive_word values ('本公司担');
insert into wifi_sensitive_word values ('本无码');
insert into wifi_sensitive_word values ('无码');
insert into wifi_sensitive_word values ('苍井空');
insert into wifi_sensitive_word values ('毕业證');
insert into wifi_sensitive_word values ('变牌绝');
insert into wifi_sensitive_word values ('辩词与梦');
insert into wifi_sensitive_word values ('冰毒');
insert into wifi_sensitive_word values ('冰火毒');
insert into wifi_sensitive_word values ('冰火佳');
insert into wifi_sensitive_word values ('冰火九重');
insert into wifi_sensitive_word values ('冰火漫');
insert into wifi_sensitive_word values ('冰淫传');
insert into wifi_sensitive_word values ('冰在火上');
insert into wifi_sensitive_word values ('波推龙');
insert into wifi_sensitive_word values ('博彩娱');
insert into wifi_sensitive_word values ('博会暂停');
insert into wifi_sensitive_word values ('博园区伪');
insert into wifi_sensitive_word values ('不查都');
insert into wifi_sensitive_word values ('不查全');
insert into wifi_sensitive_word values ('不思四化');
insert into wifi_sensitive_word values ('布卖淫女');
insert into wifi_sensitive_word values ('部忙组阁');
insert into wifi_sensitive_word values ('部是这样');
insert into wifi_sensitive_word values ('才知道只生');
insert into wifi_sensitive_word values ('财众科技');
insert into wifi_sensitive_word values ('采花堂');
insert into wifi_sensitive_word values ('踩踏事');
insert into wifi_sensitive_word values ('苍山兰');
insert into wifi_sensitive_word values ('苍蝇水');
insert into wifi_sensitive_word values ('藏春阁');
insert into wifi_sensitive_word values ('藏獨');
insert into wifi_sensitive_word values ('操了嫂');
insert into wifi_sensitive_word values ('操嫂子');
insert into wifi_sensitive_word values ('策没有不');
insert into wifi_sensitive_word values ('插屁屁');
insert into wifi_sensitive_word values ('察象蚂');
insert into wifi_sensitive_word values ('拆迁灭');
insert into wifi_sensitive_word values ('车牌隐');
insert into wifi_sensitive_word values ('成人电');
insert into wifi_sensitive_word values ('成人卡通');
insert into wifi_sensitive_word values ('成人聊');
insert into wifi_sensitive_word values ('成人片');
insert into wifi_sensitive_word values ('成人视');
insert into wifi_sensitive_word values ('成人图');
insert into wifi_sensitive_word values ('成人文');
insert into wifi_sensitive_word values ('成人小');
insert into wifi_sensitive_word values ('城管灭');
insert into wifi_sensitive_word values ('惩公安');
insert into wifi_sensitive_word values ('惩贪难');
insert into wifi_sensitive_word values ('充气娃');
insert into wifi_sensitive_word values ('冲凉死');
insert into wifi_sensitive_word values ('抽着大中');
insert into wifi_sensitive_word values ('抽着芙蓉');
insert into wifi_sensitive_word values ('出成绩付');
insert into wifi_sensitive_word values ('出售发票');
insert into wifi_sensitive_word values ('出售军');
insert into wifi_sensitive_word values ('穿透仪器');
insert into wifi_sensitive_word values ('春水横溢');
insert into wifi_sensitive_word values ('纯度白');
insert into wifi_sensitive_word values ('纯度黄');
insert into wifi_sensitive_word values ('次通过考');
insert into wifi_sensitive_word values ('催眠水');
insert into wifi_sensitive_word values ('催情粉');
insert into wifi_sensitive_word values ('催情药');
insert into wifi_sensitive_word values ('催情藥');
insert into wifi_sensitive_word values ('挫仑');
insert into wifi_sensitive_word values ('达毕业证');
insert into wifi_sensitive_word values ('答案包');
insert into wifi_sensitive_word values ('答案提供');
insert into wifi_sensitive_word values ('打标语');
insert into wifi_sensitive_word values ('打错门');
insert into wifi_sensitive_word values ('打飞机专');
insert into wifi_sensitive_word values ('打死经过');
insert into wifi_sensitive_word values ('打死人');
insert into wifi_sensitive_word values ('打砸办公');
insert into wifi_sensitive_word values ('大鸡巴');
insert into wifi_sensitive_word values ('大雞巴');
insert into wifi_sensitive_word values ('大纪元');
insert into wifi_sensitive_word values ('大揭露');
insert into wifi_sensitive_word values ('大奶子');
insert into wifi_sensitive_word values ('大批贪官');
insert into wifi_sensitive_word values ('大肉棒');
insert into wifi_sensitive_word values ('大嘴歌');
insert into wifi_sensitive_word values ('代办发票');
insert into wifi_sensitive_word values ('代办各');
insert into wifi_sensitive_word values ('代办文');
insert into wifi_sensitive_word values ('代办学');
insert into wifi_sensitive_word values ('代办制');
insert into wifi_sensitive_word values ('代辦');
insert into wifi_sensitive_word values ('代表烦');
insert into wifi_sensitive_word values ('代開');
insert into wifi_sensitive_word values ('代考');
insert into wifi_sensitive_word values ('代理发票');
insert into wifi_sensitive_word values ('代理票据');
insert into wifi_sensitive_word values ('代您考');
insert into wifi_sensitive_word values ('代写毕');
insert into wifi_sensitive_word values ('代写论');
insert into wifi_sensitive_word values ('代孕');
insert into wifi_sensitive_word values ('贷办');
insert into wifi_sensitive_word values ('贷借款');
insert into wifi_sensitive_word values ('贷开');
insert into wifi_sensitive_word values ('戴海静');
insert into wifi_sensitive_word values ('当代七整');
insert into wifi_sensitive_word values ('当官要精');
insert into wifi_sensitive_word values ('当官在于');
insert into wifi_sensitive_word values ('党的官');
insert into wifi_sensitive_word values ('党后萎');
insert into wifi_sensitive_word values ('党前干劲');
insert into wifi_sensitive_word values ('刀架保安');
insert into wifi_sensitive_word values ('导的情人');
insert into wifi_sensitive_word values ('导叫失');
insert into wifi_sensitive_word values ('导人的最');
insert into wifi_sensitive_word values ('导人最');
insert into wifi_sensitive_word values ('导小商');
insert into wifi_sensitive_word values ('到花心');
insert into wifi_sensitive_word values ('得财兼');
insert into wifi_sensitive_word values ('的同修');
insert into wifi_sensitive_word values ('灯草和');
insert into wifi_sensitive_word values ('等级證');
insert into wifi_sensitive_word values ('等屁民');
insert into wifi_sensitive_word values ('等人老百');
insert into wifi_sensitive_word values ('等人是老');
insert into wifi_sensitive_word values ('等人手术');
insert into wifi_sensitive_word values ('邓爷爷转');
insert into wifi_sensitive_word values ('邓玉娇');
insert into wifi_sensitive_word values ('地产之歌');
insert into wifi_sensitive_word values ('地下先烈');
insert into wifi_sensitive_word values ('地震哥');
insert into wifi_sensitive_word values ('帝国之梦');
insert into wifi_sensitive_word values ('递纸死');
insert into wifi_sensitive_word values ('点数优惠');
insert into wifi_sensitive_word values ('电狗');
insert into wifi_sensitive_word values ('电话监');
insert into wifi_sensitive_word values ('电鸡');
insert into wifi_sensitive_word values ('甸果敢');
insert into wifi_sensitive_word values ('蝶舞按');
insert into wifi_sensitive_word values ('丁香社');
insert into wifi_sensitive_word values ('丁子霖');
insert into wifi_sensitive_word values ('顶花心');
insert into wifi_sensitive_word values ('东北独立');
insert into wifi_sensitive_word values ('东复活');
insert into wifi_sensitive_word values ('东京热');
insert into wifi_sensitive_word values ('東京熱');
insert into wifi_sensitive_word values ('洞小口紧');
insert into wifi_sensitive_word values ('都当警');
insert into wifi_sensitive_word values ('都当小姐');
insert into wifi_sensitive_word values ('都进中央');
insert into wifi_sensitive_word values ('毒蛇钻');
insert into wifi_sensitive_word values ('独立台湾');
insert into wifi_sensitive_word values ('赌球网');
insert into wifi_sensitive_word values ('短信截');
insert into wifi_sensitive_word values ('对日强硬');
insert into wifi_sensitive_word values ('多美康');
insert into wifi_sensitive_word values ('躲猫猫');
insert into wifi_sensitive_word values ('俄羅斯');
insert into wifi_sensitive_word values ('恶势力操');
insert into wifi_sensitive_word values ('恶势力插');
insert into wifi_sensitive_word values ('恩氟烷');
insert into wifi_sensitive_word values ('儿园惨');
insert into wifi_sensitive_word values ('儿园砍');
insert into wifi_sensitive_word values ('儿园杀');
insert into wifi_sensitive_word values ('儿园凶');
insert into wifi_sensitive_word values ('二奶大');
insert into wifi_sensitive_word values ('发牌绝');
insert into wifi_sensitive_word values ('发票出');
insert into wifi_sensitive_word values ('发票代');
insert into wifi_sensitive_word values ('发票销');
insert into wifi_sensitive_word values ('發票');
insert into wifi_sensitive_word values ('法车仑');
insert into wifi_sensitive_word values ('法伦功');
insert into wifi_sensitive_word values ('法轮');
insert into wifi_sensitive_word values ('法轮佛');
insert into wifi_sensitive_word values ('法维权');
insert into wifi_sensitive_word values ('法一轮');
insert into wifi_sensitive_word values ('法院给废');
insert into wifi_sensitive_word values ('法正乾');
insert into wifi_sensitive_word values ('反测速雷');
insert into wifi_sensitive_word values ('反雷达测');
insert into wifi_sensitive_word values ('反屏蔽');
insert into wifi_sensitive_word values ('范燕琼');
insert into wifi_sensitive_word values ('方迷香');
insert into wifi_sensitive_word values ('防电子眼');
insert into wifi_sensitive_word values ('防身药水');
insert into wifi_sensitive_word values ('房贷给废');
insert into wifi_sensitive_word values ('仿真枪');
insert into wifi_sensitive_word values ('仿真证');
insert into wifi_sensitive_word values ('诽谤罪');
insert into wifi_sensitive_word values ('费私服');
insert into wifi_sensitive_word values ('封锁消');
insert into wifi_sensitive_word values ('佛同修');
insert into wifi_sensitive_word values ('夫妻交换');
insert into wifi_sensitive_word values ('福尔马林');
insert into wifi_sensitive_word values ('福娃的預');
insert into wifi_sensitive_word values ('福娃頭上');
insert into wifi_sensitive_word values ('福香巴');
insert into wifi_sensitive_word values ('府包庇');
insert into wifi_sensitive_word values ('府集中领');
insert into wifi_sensitive_word values ('妇销魂');
insert into wifi_sensitive_word values ('附送枪');
insert into wifi_sensitive_word values ('复印件生');
insert into wifi_sensitive_word values ('复印件制');
insert into wifi_sensitive_word values ('富民穷');
insert into wifi_sensitive_word values ('富婆给废');
insert into wifi_sensitive_word values ('改号软件');
insert into wifi_sensitive_word values ('感扑克');
insert into wifi_sensitive_word values ('冈本真');
insert into wifi_sensitive_word values ('肛交');
insert into wifi_sensitive_word values ('肛门是邻');
insert into wifi_sensitive_word values ('岡本真');
insert into wifi_sensitive_word values ('钢针狗');
insert into wifi_sensitive_word values ('钢珠枪');
insert into wifi_sensitive_word values ('港澳博球');
insert into wifi_sensitive_word values ('港馬會');
insert into wifi_sensitive_word values ('港鑫華');
insert into wifi_sensitive_word values ('高就在政');
insert into wifi_sensitive_word values ('高考黑');
insert into wifi_sensitive_word values ('高莺莺');
insert into wifi_sensitive_word values ('搞媛交');
insert into wifi_sensitive_word values ('告长期');
insert into wifi_sensitive_word values ('告洋状');
insert into wifi_sensitive_word values ('格证考试');
insert into wifi_sensitive_word values ('各类考试');
insert into wifi_sensitive_word values ('各类文凭');
insert into wifi_sensitive_word values ('跟踪器');
insert into wifi_sensitive_word values ('工程吞得');
insert into wifi_sensitive_word values ('工力人');
insert into wifi_sensitive_word values ('公安错打');
insert into wifi_sensitive_word values ('公安网监');
insert into wifi_sensitive_word values ('公开小姐');
insert into wifi_sensitive_word values ('攻官小姐');
insert into wifi_sensitive_word values ('共狗');
insert into wifi_sensitive_word values ('共王储');
insert into wifi_sensitive_word values ('狗粮');
insert into wifi_sensitive_word values ('狗屁专家');
insert into wifi_sensitive_word values ('鼓动一些');
insert into wifi_sensitive_word values ('乖乖粉');
insert into wifi_sensitive_word values ('官商勾');
insert into wifi_sensitive_word values ('官也不容');
insert into wifi_sensitive_word values ('官因发帖');
insert into wifi_sensitive_word values ('光学真题');
insert into wifi_sensitive_word values ('跪真相');
insert into wifi_sensitive_word values ('滚圆大乳');
insert into wifi_sensitive_word values ('国际投注');
insert into wifi_sensitive_word values ('国家妓');
insert into wifi_sensitive_word values ('国家软弱');
insert into wifi_sensitive_word values ('国家吞得');
insert into wifi_sensitive_word values ('国库折');
insert into wifi_sensitive_word values ('国一九五七');
insert into wifi_sensitive_word values ('國內美');
insert into wifi_sensitive_word values ('哈药直销');
insert into wifi_sensitive_word values ('海访民');
insert into wifi_sensitive_word values ('豪圈钱');
insert into wifi_sensitive_word values ('号屏蔽器');
insert into wifi_sensitive_word values ('和狗交');
insert into wifi_sensitive_word values ('和狗性');
insert into wifi_sensitive_word values ('和狗做');
insert into wifi_sensitive_word values ('黑火药的');
insert into wifi_sensitive_word values ('红色恐怖');
insert into wifi_sensitive_word values ('红外透视');
insert into wifi_sensitive_word values ('紅色恐');
insert into wifi_sensitive_word values ('胡江内斗');
insert into wifi_sensitive_word values ('胡紧套');
insert into wifi_sensitive_word values ('胡錦濤');
insert into wifi_sensitive_word values ('胡适眼');
insert into wifi_sensitive_word values ('胡耀邦');
insert into wifi_sensitive_word values ('湖淫娘');
insert into wifi_sensitive_word values ('虎头猎');
insert into wifi_sensitive_word values ('华国锋');
insert into wifi_sensitive_word values ('华门开');
insert into wifi_sensitive_word values ('化学扫盲');
insert into wifi_sensitive_word values ('划老公');
insert into wifi_sensitive_word values ('还会吹萧');
insert into wifi_sensitive_word values ('还看锦涛');
insert into wifi_sensitive_word values ('环球证件');
insert into wifi_sensitive_word values ('换妻');
insert into wifi_sensitive_word values ('皇冠投注');
insert into wifi_sensitive_word values ('黄冰');
insert into wifi_sensitive_word values ('浑圆豪乳');
insert into wifi_sensitive_word values ('活不起');
insert into wifi_sensitive_word values ('火车也疯');
insert into wifi_sensitive_word values ('机定位器');
insert into wifi_sensitive_word values ('机号定');
insert into wifi_sensitive_word values ('机号卫');
insert into wifi_sensitive_word values ('机卡密');
insert into wifi_sensitive_word values ('机屏蔽器');
insert into wifi_sensitive_word values ('基本靠吼');
insert into wifi_sensitive_word values ('绩过后付');
insert into wifi_sensitive_word values ('激情电');
insert into wifi_sensitive_word values ('激情短');
insert into wifi_sensitive_word values ('激情妹');
insert into wifi_sensitive_word values ('激情炮');
insert into wifi_sensitive_word values ('级办理');
insert into wifi_sensitive_word values ('级答案');
insert into wifi_sensitive_word values ('急需嫖');
insert into wifi_sensitive_word values ('集体打砸');
insert into wifi_sensitive_word values ('集体腐');
insert into wifi_sensitive_word values ('挤乳汁');
insert into wifi_sensitive_word values ('擠乳汁');
insert into wifi_sensitive_word values ('佳静安定');
insert into wifi_sensitive_word values ('家一样饱');
insert into wifi_sensitive_word values ('家属被打');
insert into wifi_sensitive_word values ('甲虫跳');
insert into wifi_sensitive_word values ('甲流了');
insert into wifi_sensitive_word values ('奸成瘾');
insert into wifi_sensitive_word values ('兼职上门');
insert into wifi_sensitive_word values ('监听器');
insert into wifi_sensitive_word values ('监听王');
insert into wifi_sensitive_word values ('简易炸');
insert into wifi_sensitive_word values ('江胡内斗');
insert into wifi_sensitive_word values ('江太上');
insert into wifi_sensitive_word values ('江系人');
insert into wifi_sensitive_word values ('江贼民');
insert into wifi_sensitive_word values ('疆獨');
insert into wifi_sensitive_word values ('蒋彦永');
insert into wifi_sensitive_word values ('叫自慰');
insert into wifi_sensitive_word values ('揭贪难');
insert into wifi_sensitive_word values ('姐包夜');
insert into wifi_sensitive_word values ('姐服务');
insert into wifi_sensitive_word values ('姐兼职');
insert into wifi_sensitive_word values ('姐上门');
insert into wifi_sensitive_word values ('金扎金');
insert into wifi_sensitive_word values ('金钟气');
insert into wifi_sensitive_word values ('津大地震');
insert into wifi_sensitive_word values ('津地震');
insert into wifi_sensitive_word values ('进来的罪');
insert into wifi_sensitive_word values ('京地震');
insert into wifi_sensitive_word values ('京要地震');
insert into wifi_sensitive_word values ('经典谎言');
insert into wifi_sensitive_word values ('精子射在');
insert into wifi_sensitive_word values ('警察被');
insert into wifi_sensitive_word values ('警察的幌');
insert into wifi_sensitive_word values ('警察殴打');
insert into wifi_sensitive_word values ('警察说保');
insert into wifi_sensitive_word values ('警车雷达');
insert into wifi_sensitive_word values ('警方包庇');
insert into wifi_sensitive_word values ('警用品');
insert into wifi_sensitive_word values ('径步枪');
insert into wifi_sensitive_word values ('敬请忍');
insert into wifi_sensitive_word values ('究生答案');
insert into wifi_sensitive_word values ('九龙论坛');
insert into wifi_sensitive_word values ('九评共');
insert into wifi_sensitive_word values ('酒象喝汤');
insert into wifi_sensitive_word values ('酒像喝汤');
insert into wifi_sensitive_word values ('就爱插');
insert into wifi_sensitive_word values ('就要色');
insert into wifi_sensitive_word values ('举国体');
insert into wifi_sensitive_word values ('巨乳');
insert into wifi_sensitive_word values ('据说全民');
insert into wifi_sensitive_word values ('绝食声');
insert into wifi_sensitive_word values ('军长发威');
insert into wifi_sensitive_word values ('军刺');
insert into wifi_sensitive_word values ('军品特');
insert into wifi_sensitive_word values ('军用手');
insert into wifi_sensitive_word values ('开邓选');
insert into wifi_sensitive_word values ('开锁工具');
insert into wifi_sensitive_word values ('開碼');
insert into wifi_sensitive_word values ('開票');
insert into wifi_sensitive_word values ('砍杀幼');
insert into wifi_sensitive_word values ('砍伤儿');
insert into wifi_sensitive_word values ('康没有不');
insert into wifi_sensitive_word values ('康跳楼');
insert into wifi_sensitive_word values ('考答案');
insert into wifi_sensitive_word values ('考后付款');
insert into wifi_sensitive_word values ('考机构');
insert into wifi_sensitive_word values ('考考邓');
insert into wifi_sensitive_word values ('考联盟');
insert into wifi_sensitive_word values ('考前答');
insert into wifi_sensitive_word values ('考前答案');
insert into wifi_sensitive_word values ('考前付');
insert into wifi_sensitive_word values ('考设备');
insert into wifi_sensitive_word values ('考试包过');
insert into wifi_sensitive_word values ('考试保');
insert into wifi_sensitive_word values ('考试答案');
insert into wifi_sensitive_word values ('考试机构');
insert into wifi_sensitive_word values ('考试联盟');
insert into wifi_sensitive_word values ('考试枪');
insert into wifi_sensitive_word values ('考研考中');
insert into wifi_sensitive_word values ('考中答案');
insert into wifi_sensitive_word values ('磕彰');
insert into wifi_sensitive_word values ('克分析');
insert into wifi_sensitive_word values ('克千术');
insert into wifi_sensitive_word values ('克透视');
insert into wifi_sensitive_word values ('空和雅典');
insert into wifi_sensitive_word values ('孔摄像');
insert into wifi_sensitive_word values ('控诉世博');
insert into wifi_sensitive_word values ('控制媒');
insert into wifi_sensitive_word values ('口手枪');
insert into wifi_sensitive_word values ('骷髅死');
insert into wifi_sensitive_word values ('快速办');
insert into wifi_sensitive_word values ('矿难不公');
insert into wifi_sensitive_word values ('拉登说');
insert into wifi_sensitive_word values ('拉开水晶');
insert into wifi_sensitive_word values ('来福猎');
insert into wifi_sensitive_word values ('拦截器');
insert into wifi_sensitive_word values ('狼全部跪');
insert into wifi_sensitive_word values ('浪穴');
insert into wifi_sensitive_word values ('老虎机');
insert into wifi_sensitive_word values ('雷人女官');
insert into wifi_sensitive_word values ('类准确答');
insert into wifi_sensitive_word values ('黎阳平');
insert into wifi_sensitive_word values ('李洪志');
insert into wifi_sensitive_word values ('李咏曰');
insert into wifi_sensitive_word values ('理各种证');
insert into wifi_sensitive_word values ('理是影帝');
insert into wifi_sensitive_word values ('理证件');
insert into wifi_sensitive_word values ('理做帐报');
insert into wifi_sensitive_word values ('力骗中央');
insert into wifi_sensitive_word values ('力月西');
insert into wifi_sensitive_word values ('丽媛离');
insert into wifi_sensitive_word values ('利他林');
insert into wifi_sensitive_word values ('连发手');
insert into wifi_sensitive_word values ('聯繫電');
insert into wifi_sensitive_word values ('炼大法');
insert into wifi_sensitive_word values ('两岸才子');
insert into wifi_sensitive_word values ('两会代');
insert into wifi_sensitive_word values ('两会又三');
insert into wifi_sensitive_word values ('聊视频');
insert into wifi_sensitive_word values ('聊斋艳');
insert into wifi_sensitive_word values ('了件渔袍');
insert into wifi_sensitive_word values ('猎好帮手');
insert into wifi_sensitive_word values ('猎枪销');
insert into wifi_sensitive_word values ('猎槍');
insert into wifi_sensitive_word values ('獵槍');
insert into wifi_sensitive_word values ('领土拿');
insert into wifi_sensitive_word values ('流血事');
insert into wifi_sensitive_word values ('六合彩');
insert into wifi_sensitive_word values ('六死');
insert into wifi_sensitive_word values ('六四事');
insert into wifi_sensitive_word values ('六月联盟');
insert into wifi_sensitive_word values ('龙湾事件');
insert into wifi_sensitive_word values ('隆手指');
insert into wifi_sensitive_word values ('陆封锁');
insert into wifi_sensitive_word values ('陆同修');
insert into wifi_sensitive_word values ('氯胺酮');
insert into wifi_sensitive_word values ('乱奸');
insert into wifi_sensitive_word values ('乱伦类');
insert into wifi_sensitive_word values ('乱伦小');
insert into wifi_sensitive_word values ('亂倫');
insert into wifi_sensitive_word values ('伦理大');
insert into wifi_sensitive_word values ('伦理电影');
insert into wifi_sensitive_word values ('伦理毛');
insert into wifi_sensitive_word values ('伦理片');
insert into wifi_sensitive_word values ('轮功');
insert into wifi_sensitive_word values ('轮手枪');
insert into wifi_sensitive_word values ('论文代');
insert into wifi_sensitive_word values ('罗斯小姐');
insert into wifi_sensitive_word values ('裸聊网');
insert into wifi_sensitive_word values ('裸舞视');
insert into wifi_sensitive_word values ('落霞缀');
insert into wifi_sensitive_word values ('麻古');
insert into wifi_sensitive_word values ('麻果配');
insert into wifi_sensitive_word values ('麻果丸');
insert into wifi_sensitive_word values ('麻将透');
insert into wifi_sensitive_word values ('麻醉狗');
insert into wifi_sensitive_word values ('麻醉枪');
insert into wifi_sensitive_word values ('麻醉槍');
insert into wifi_sensitive_word values ('麻醉藥');
insert into wifi_sensitive_word values ('蟆叫专家');
insert into wifi_sensitive_word values ('卖地财政');
insert into wifi_sensitive_word values ('卖发票');
insert into wifi_sensitive_word values ('卖银行卡');
insert into wifi_sensitive_word values ('卖自考');
insert into wifi_sensitive_word values ('漫步丝');
insert into wifi_sensitive_word values ('忙爱国');
insert into wifi_sensitive_word values ('猫眼工具');
insert into wifi_sensitive_word values ('毛一鲜');
insert into wifi_sensitive_word values ('媒体封锁');
insert into wifi_sensitive_word values ('每周一死');
insert into wifi_sensitive_word values ('美艳少妇');
insert into wifi_sensitive_word values ('妹按摩');
insert into wifi_sensitive_word values ('妹上门');
insert into wifi_sensitive_word values ('门按摩');
insert into wifi_sensitive_word values ('门保健');
insert into wifi_sensitive_word values ('門服務');
insert into wifi_sensitive_word values ('氓培训');
insert into wifi_sensitive_word values ('蒙汗药');
insert into wifi_sensitive_word values ('迷幻型');
insert into wifi_sensitive_word values ('迷幻药');
insert into wifi_sensitive_word values ('迷幻藥');
insert into wifi_sensitive_word values ('迷昏口');
insert into wifi_sensitive_word values ('迷昏药');
insert into wifi_sensitive_word values ('迷昏藥');
insert into wifi_sensitive_word values ('迷魂香');
insert into wifi_sensitive_word values ('迷魂药');
insert into wifi_sensitive_word values ('迷魂藥');
insert into wifi_sensitive_word values ('迷奸药');
insert into wifi_sensitive_word values ('迷情水');
insert into wifi_sensitive_word values ('迷情药');
insert into wifi_sensitive_word values ('迷藥');
insert into wifi_sensitive_word values ('谜奸药');
insert into wifi_sensitive_word values ('蜜穴');
insert into wifi_sensitive_word values ('灭绝罪');
insert into wifi_sensitive_word values ('民储害');
insert into wifi_sensitive_word values ('民九亿商');
insert into wifi_sensitive_word values ('民抗议');
insert into wifi_sensitive_word values ('明慧网');
insert into wifi_sensitive_word values ('铭记印尼');
insert into wifi_sensitive_word values ('摩小姐');
insert into wifi_sensitive_word values ('母乳家');
insert into wifi_sensitive_word values ('木齐针');
insert into wifi_sensitive_word values ('幕没有不');
insert into wifi_sensitive_word values ('幕前戲');
insert into wifi_sensitive_word values ('内射');
insert into wifi_sensitive_word values ('南充针');
insert into wifi_sensitive_word values ('嫩穴');
insert into wifi_sensitive_word values ('嫩阴');
insert into wifi_sensitive_word values ('泥马之歌');
insert into wifi_sensitive_word values ('你的西域');
insert into wifi_sensitive_word values ('拟涛哥');
insert into wifi_sensitive_word values ('娘两腿之间');
insert into wifi_sensitive_word values ('妞上门');
insert into wifi_sensitive_word values ('浓精');
insert into wifi_sensitive_word values ('怒的志愿');
insert into wifi_sensitive_word values ('女被人家搞');
insert into wifi_sensitive_word values ('女激情');
insert into wifi_sensitive_word values ('女技师');
insert into wifi_sensitive_word values ('女人和狗');
insert into wifi_sensitive_word values ('女任职名');
insert into wifi_sensitive_word values ('女上门');
insert into wifi_sensitive_word values ('女優');
insert into wifi_sensitive_word values ('鸥之歌');
insert into wifi_sensitive_word values ('拍肩神药');
insert into wifi_sensitive_word values ('拍肩型');
insert into wifi_sensitive_word values ('牌分析');
insert into wifi_sensitive_word values ('牌技网');
insert into wifi_sensitive_word values ('炮的小蜜');
insert into wifi_sensitive_word values ('陪考枪');
insert into wifi_sensitive_word values ('配有消');
insert into wifi_sensitive_word values ('喷尿');
insert into wifi_sensitive_word values ('嫖俄罗');
insert into wifi_sensitive_word values ('嫖鸡');
insert into wifi_sensitive_word values ('平惨案');
insert into wifi_sensitive_word values ('平叫到床');
insert into wifi_sensitive_word values ('仆不怕饮');
insert into wifi_sensitive_word values ('普通嘌');
insert into wifi_sensitive_word values ('期货配');
insert into wifi_sensitive_word values ('奇迹的黄');
insert into wifi_sensitive_word values ('奇淫散');
insert into wifi_sensitive_word values ('骑单车出');
insert into wifi_sensitive_word values ('气狗');
insert into wifi_sensitive_word values ('气枪');
insert into wifi_sensitive_word values ('汽狗');
insert into wifi_sensitive_word values ('汽枪');
insert into wifi_sensitive_word values ('氣槍');
insert into wifi_sensitive_word values ('铅弹');
insert into wifi_sensitive_word values ('钱三字经');
insert into wifi_sensitive_word values ('枪出售');
insert into wifi_sensitive_word values ('枪的参');
insert into wifi_sensitive_word values ('枪的分');
insert into wifi_sensitive_word values ('枪的结');
insert into wifi_sensitive_word values ('枪的制');
insert into wifi_sensitive_word values ('枪货到');
insert into wifi_sensitive_word values ('枪决女犯');
insert into wifi_sensitive_word values ('枪决现场');
insert into wifi_sensitive_word values ('枪模');
insert into wifi_sensitive_word values ('枪手队');
insert into wifi_sensitive_word values ('枪手网');
insert into wifi_sensitive_word values ('枪销售');
insert into wifi_sensitive_word values ('枪械制');
insert into wifi_sensitive_word values ('枪子弹');
insert into wifi_sensitive_word values ('强权政府');
insert into wifi_sensitive_word values ('强硬发言');
insert into wifi_sensitive_word values ('抢其火炬');
insert into wifi_sensitive_word values ('切听器');
insert into wifi_sensitive_word values ('窃听器');
insert into wifi_sensitive_word values ('禽流感了');
insert into wifi_sensitive_word values ('勤捞致');
insert into wifi_sensitive_word values ('氢弹手');
insert into wifi_sensitive_word values ('清除负面');
insert into wifi_sensitive_word values ('清純壆');
insert into wifi_sensitive_word values ('情聊天室');
insert into wifi_sensitive_word values ('情妹妹');
insert into wifi_sensitive_word values ('情视频');
insert into wifi_sensitive_word values ('情自拍');
insert into wifi_sensitive_word values ('氰化钾');
insert into wifi_sensitive_word values ('氰化钠');
insert into wifi_sensitive_word values ('请集会');
insert into wifi_sensitive_word values ('请示威');
insert into wifi_sensitive_word values ('请愿');
insert into wifi_sensitive_word values ('琼花问');
insert into wifi_sensitive_word values ('区的雷人');
insert into wifi_sensitive_word values ('娶韩国');
insert into wifi_sensitive_word values ('全真证');
insert into wifi_sensitive_word values ('群奸暴');
insert into wifi_sensitive_word values ('群起抗暴');
insert into wifi_sensitive_word values ('群体性事');
insert into wifi_sensitive_word values ('绕过封锁');
insert into wifi_sensitive_word values ('惹的国');
insert into wifi_sensitive_word values ('人权律');
insert into wifi_sensitive_word values ('人体艺');
insert into wifi_sensitive_word values ('人游行');
insert into wifi_sensitive_word values ('人在云上');
insert into wifi_sensitive_word values ('人真钱');
insert into wifi_sensitive_word values ('认牌绝');
insert into wifi_sensitive_word values ('任于斯国');
insert into wifi_sensitive_word values ('柔胸粉');
insert into wifi_sensitive_word values ('肉洞');
insert into wifi_sensitive_word values ('肉棍');
insert into wifi_sensitive_word values ('如厕死');
insert into wifi_sensitive_word values ('乳交');
insert into wifi_sensitive_word values ('软弱的国');
insert into wifi_sensitive_word values ('赛后骚');
insert into wifi_sensitive_word values ('三挫');
insert into wifi_sensitive_word values ('三级片');
insert into wifi_sensitive_word values ('三秒倒');
insert into wifi_sensitive_word values ('三网友');
insert into wifi_sensitive_word values ('三唑');
insert into wifi_sensitive_word values ('骚妇');
insert into wifi_sensitive_word values ('骚浪');
insert into wifi_sensitive_word values ('骚穴');
insert into wifi_sensitive_word values ('骚逼');
insert into wifi_sensitive_word values ('骚嘴');
insert into wifi_sensitive_word values ('扫了爷爷');
insert into wifi_sensitive_word values ('色电影');
insert into wifi_sensitive_word values ('色妹妹');
insert into wifi_sensitive_word values ('色视频');
insert into wifi_sensitive_word values ('色小说');
insert into wifi_sensitive_word values ('杀指南');
insert into wifi_sensitive_word values ('山涉黑');
insert into wifi_sensitive_word values ('煽动不明');
insert into wifi_sensitive_word values ('煽动群众');
insert into wifi_sensitive_word values ('上门激');
insert into wifi_sensitive_word values ('烧公安局');
insert into wifi_sensitive_word values ('烧瓶的');
insert into wifi_sensitive_word values ('韶关斗');
insert into wifi_sensitive_word values ('韶关玩');
insert into wifi_sensitive_word values ('韶关旭');
insert into wifi_sensitive_word values ('射网枪');
insert into wifi_sensitive_word values ('涉嫌抄袭');
insert into wifi_sensitive_word values ('深喉冰');
insert into wifi_sensitive_word values ('神七假');
insert into wifi_sensitive_word values ('神韵艺术');
insert into wifi_sensitive_word values ('生被砍');
insert into wifi_sensitive_word values ('生踩踏');
insert into wifi_sensitive_word values ('生肖中特');
insert into wifi_sensitive_word values ('圣战不息');
insert into wifi_sensitive_word values ('盛行在舞');
insert into wifi_sensitive_word values ('尸博');
insert into wifi_sensitive_word values ('失身水');
insert into wifi_sensitive_word values ('失意药');
insert into wifi_sensitive_word values ('狮子旗');
insert into wifi_sensitive_word values ('十八等');
insert into wifi_sensitive_word values ('十大谎');
insert into wifi_sensitive_word values ('十大禁');
insert into wifi_sensitive_word values ('十个预言');
insert into wifi_sensitive_word values ('十类人不');
insert into wifi_sensitive_word values ('十七大幕');
insert into wifi_sensitive_word values ('实毕业证');
insert into wifi_sensitive_word values ('实体娃');
insert into wifi_sensitive_word values ('实学历文');
insert into wifi_sensitive_word values ('士康事件');
insert into wifi_sensitive_word values ('式粉推');
insert into wifi_sensitive_word values ('视解密');
insert into wifi_sensitive_word values ('是躲猫');
insert into wifi_sensitive_word values ('手变牌');
insert into wifi_sensitive_word values ('手答案');
insert into wifi_sensitive_word values ('手狗');
insert into wifi_sensitive_word values ('手机跟');
insert into wifi_sensitive_word values ('手机监');
insert into wifi_sensitive_word values ('手机窃');
insert into wifi_sensitive_word values ('手机追');
insert into wifi_sensitive_word values ('手拉鸡');
insert into wifi_sensitive_word values ('手木仓');
insert into wifi_sensitive_word values ('手槍');
insert into wifi_sensitive_word values ('守所死法');
insert into wifi_sensitive_word values ('兽交');
insert into wifi_sensitive_word values ('售步枪');
insert into wifi_sensitive_word values ('售纯度');
insert into wifi_sensitive_word values ('售单管');
insert into wifi_sensitive_word values ('售弹簧刀');
insert into wifi_sensitive_word values ('售防身');
insert into wifi_sensitive_word values ('售狗子');
insert into wifi_sensitive_word values ('售虎头');
insert into wifi_sensitive_word values ('售火药');
insert into wifi_sensitive_word values ('售假币');
insert into wifi_sensitive_word values ('售健卫');
insert into wifi_sensitive_word values ('售军用');
insert into wifi_sensitive_word values ('售猎枪');
insert into wifi_sensitive_word values ('售氯胺');
insert into wifi_sensitive_word values ('售麻醉');
insert into wifi_sensitive_word values ('售冒名');
insert into wifi_sensitive_word values ('售枪支');
insert into wifi_sensitive_word values ('售热武');
insert into wifi_sensitive_word values ('售三棱');
insert into wifi_sensitive_word values ('售手枪');
insert into wifi_sensitive_word values ('售五四');
insert into wifi_sensitive_word values ('售信用');
insert into wifi_sensitive_word values ('售一元硬');
insert into wifi_sensitive_word values ('售子弹');
insert into wifi_sensitive_word values ('售左轮');
insert into wifi_sensitive_word values ('书办理');
insert into wifi_sensitive_word values ('熟妇');
insert into wifi_sensitive_word values ('术牌具');
insert into wifi_sensitive_word values ('双管立');
insert into wifi_sensitive_word values ('双管平');
insert into wifi_sensitive_word values ('水阎王');
insert into wifi_sensitive_word values ('丝护士');
insert into wifi_sensitive_word values ('丝情侣');
insert into wifi_sensitive_word values ('丝袜保');
insert into wifi_sensitive_word values ('丝袜恋');
insert into wifi_sensitive_word values ('丝袜美');
insert into wifi_sensitive_word values ('丝袜妹');
insert into wifi_sensitive_word values ('丝袜网');
insert into wifi_sensitive_word values ('丝足按');
insert into wifi_sensitive_word values ('司长期有');
insert into wifi_sensitive_word values ('司法黑');
insert into wifi_sensitive_word values ('私房写真');
insert into wifi_sensitive_word values ('死法分布');
insert into wifi_sensitive_word values ('死要见毛');
insert into wifi_sensitive_word values ('四博会');
insert into wifi_sensitive_word values ('四大扯个');
insert into wifi_sensitive_word values ('四小码');
insert into wifi_sensitive_word values ('苏家屯集');
insert into wifi_sensitive_word values ('诉讼集团');
insert into wifi_sensitive_word values ('素女心');
insert into wifi_sensitive_word values ('速代办');
insert into wifi_sensitive_word values ('速取证');
insert into wifi_sensitive_word values ('酸羟亚胺');
insert into wifi_sensitive_word values ('蹋纳税');
insert into wifi_sensitive_word values ('太王四神');
insert into wifi_sensitive_word values ('泰兴幼');
insert into wifi_sensitive_word values ('泰兴镇中');
insert into wifi_sensitive_word values ('泰州幼');
insert into wifi_sensitive_word values ('贪官也辛');
insert into wifi_sensitive_word values ('探测狗');
insert into wifi_sensitive_word values ('涛共产');
insert into wifi_sensitive_word values ('涛一样胡');
insert into wifi_sensitive_word values ('特工资');
insert into wifi_sensitive_word values ('特码');
insert into wifi_sensitive_word values ('特上门');
insert into wifi_sensitive_word values ('体透视镜');
insert into wifi_sensitive_word values ('替考');
insert into wifi_sensitive_word values ('替人体');
insert into wifi_sensitive_word values ('天朝特');
insert into wifi_sensitive_word values ('天鹅之旅');
insert into wifi_sensitive_word values ('天推广歌');
insert into wifi_sensitive_word values ('田罢工');
insert into wifi_sensitive_word values ('田田桑');
insert into wifi_sensitive_word values ('田停工');
insert into wifi_sensitive_word values ('庭保养');
insert into wifi_sensitive_word values ('庭审直播');
insert into wifi_sensitive_word values ('通钢总经');
insert into wifi_sensitive_word values ('偷電器');
insert into wifi_sensitive_word values ('偷肃贪');
insert into wifi_sensitive_word values ('偷听器');
insert into wifi_sensitive_word values ('偷偷贪');
insert into wifi_sensitive_word values ('头双管');
insert into wifi_sensitive_word values ('透视功能');
insert into wifi_sensitive_word values ('透视镜');
insert into wifi_sensitive_word values ('透视扑');
insert into wifi_sensitive_word values ('透视器');
insert into wifi_sensitive_word values ('透视眼镜');
insert into wifi_sensitive_word values ('透视药');
insert into wifi_sensitive_word values ('透视仪');
insert into wifi_sensitive_word values ('秃鹰汽');
insert into wifi_sensitive_word values ('突破封锁');
insert into wifi_sensitive_word values ('突破网路');
insert into wifi_sensitive_word values ('推油按');
insert into wifi_sensitive_word values ('脱衣艳');
insert into wifi_sensitive_word values ('瓦斯手');
insert into wifi_sensitive_word values ('袜按摩');
insert into wifi_sensitive_word values ('外透视镜');
insert into wifi_sensitive_word values ('外围赌球');
insert into wifi_sensitive_word values ('湾版假');
insert into wifi_sensitive_word values ('万能钥匙');
insert into wifi_sensitive_word values ('万人骚动');
insert into wifi_sensitive_word values ('王立军');
insert into wifi_sensitive_word values ('王益案');
insert into wifi_sensitive_word values ('网民案');
insert into wifi_sensitive_word values ('网民获刑');
insert into wifi_sensitive_word values ('网民诬');
insert into wifi_sensitive_word values ('微型摄像');
insert into wifi_sensitive_word values ('围攻警');
insert into wifi_sensitive_word values ('围攻上海');
insert into wifi_sensitive_word values ('维汉员');
insert into wifi_sensitive_word values ('维权基');
insert into wifi_sensitive_word values ('维权人');
insert into wifi_sensitive_word values ('维权谈');
insert into wifi_sensitive_word values ('委坐船');
insert into wifi_sensitive_word values ('谓的和谐');
insert into wifi_sensitive_word values ('温家堡');
insert into wifi_sensitive_word values ('温切斯特');
insert into wifi_sensitive_word values ('温影帝');
insert into wifi_sensitive_word values ('溫家寶');
insert into wifi_sensitive_word values ('瘟加饱');
insert into wifi_sensitive_word values ('瘟假饱');
insert into wifi_sensitive_word values ('文凭证');
insert into wifi_sensitive_word values ('文强');
insert into wifi_sensitive_word values ('纹了毛');
insert into wifi_sensitive_word values ('闻被控制');
insert into wifi_sensitive_word values ('闻封锁');
insert into wifi_sensitive_word values ('瓮安');
insert into wifi_sensitive_word values ('我的西域');
insert into wifi_sensitive_word values ('我搞台独');
insert into wifi_sensitive_word values ('乌蝇水');
insert into wifi_sensitive_word values ('无耻语录');
insert into wifi_sensitive_word values ('无码专');
insert into wifi_sensitive_word values ('五套功');
insert into wifi_sensitive_word values ('五月天');
insert into wifi_sensitive_word values ('午夜电');
insert into wifi_sensitive_word values ('午夜极');
insert into wifi_sensitive_word values ('武警暴');
insert into wifi_sensitive_word values ('武警殴');
insert into wifi_sensitive_word values ('武警已增');
insert into wifi_sensitive_word values ('务员答案');
insert into wifi_sensitive_word values ('务员考试');
insert into wifi_sensitive_word values ('雾型迷');
insert into wifi_sensitive_word values ('西藏限');
insert into wifi_sensitive_word values ('西服进去');
insert into wifi_sensitive_word values ('希脏');
insert into wifi_sensitive_word values ('习进平');
insert into wifi_sensitive_word values ('习晋平');
insert into wifi_sensitive_word values ('席复活');
insert into wifi_sensitive_word values ('席临终前');
insert into wifi_sensitive_word values ('席指着护');
insert into wifi_sensitive_word values ('洗澡死');
insert into wifi_sensitive_word values ('喜贪赃');
insert into wifi_sensitive_word values ('先烈纷纷');
insert into wifi_sensitive_word values ('现大地震');
insert into wifi_sensitive_word values ('现金投注');
insert into wifi_sensitive_word values ('线透视镜');
insert into wifi_sensitive_word values ('限制言');
insert into wifi_sensitive_word values ('陷害案');
insert into wifi_sensitive_word values ('陷害罪');
insert into wifi_sensitive_word values ('相自首');
insert into wifi_sensitive_word values ('香港论坛');
insert into wifi_sensitive_word values ('香港马会');
insert into wifi_sensitive_word values ('香港一类');
insert into wifi_sensitive_word values ('香港总彩');
insert into wifi_sensitive_word values ('硝化甘');
insert into wifi_sensitive_word values ('小穴');
insert into wifi_sensitive_word values ('校骚乱');
insert into wifi_sensitive_word values ('协晃悠');
insert into wifi_sensitive_word values ('写两会');
insert into wifi_sensitive_word values ('泄漏的内');
insert into wifi_sensitive_word values ('新建户');
insert into wifi_sensitive_word values ('新疆叛');
insert into wifi_sensitive_word values ('新疆限');
insert into wifi_sensitive_word values ('新金瓶');
insert into wifi_sensitive_word values ('新唐人');
insert into wifi_sensitive_word values ('信访专班');
insert into wifi_sensitive_word values ('信接收器');
insert into wifi_sensitive_word values ('兴中心幼');
insert into wifi_sensitive_word values ('星上门');
insert into wifi_sensitive_word values ('行长王益');
insert into wifi_sensitive_word values ('形透视镜');
insert into wifi_sensitive_word values ('型手枪');
insert into wifi_sensitive_word values ('姓忽悠');
insert into wifi_sensitive_word values ('幸运码');
insert into wifi_sensitive_word values ('性爱日');
insert into wifi_sensitive_word values ('性福情');
insert into wifi_sensitive_word values ('性感少');
insert into wifi_sensitive_word values ('性推广歌');
insert into wifi_sensitive_word values ('胸主席');
insert into wifi_sensitive_word values ('徐玉元');
insert into wifi_sensitive_word values ('学骚乱');
insert into wifi_sensitive_word values ('学位證');
insert into wifi_sensitive_word values ('學生妹');
insert into wifi_sensitive_word values ('丫与王益');
insert into wifi_sensitive_word values ('烟感器');
insert into wifi_sensitive_word values ('严晓玲');
insert into wifi_sensitive_word values ('言被劳教');
insert into wifi_sensitive_word values ('言论罪');
insert into wifi_sensitive_word values ('盐酸曲');
insert into wifi_sensitive_word values ('颜射');
insert into wifi_sensitive_word values ('恙虫病');
insert into wifi_sensitive_word values ('姚明进去');
insert into wifi_sensitive_word values ('要人权');
insert into wifi_sensitive_word values ('要射精了');
insert into wifi_sensitive_word values ('要射了');
insert into wifi_sensitive_word values ('要泄了');
insert into wifi_sensitive_word values ('夜激情');
insert into wifi_sensitive_word values ('液体炸');
insert into wifi_sensitive_word values ('一小撮别');
insert into wifi_sensitive_word values ('遗情书');
insert into wifi_sensitive_word values ('蚁力神');
insert into wifi_sensitive_word values ('益关注组');
insert into wifi_sensitive_word values ('益受贿');
insert into wifi_sensitive_word values ('阴间来电');
insert into wifi_sensitive_word values ('陰唇');
insert into wifi_sensitive_word values ('陰道');
insert into wifi_sensitive_word values ('陰戶');
insert into wifi_sensitive_word values ('淫魔舞');
insert into wifi_sensitive_word values ('淫情女');
insert into wifi_sensitive_word values ('淫肉');
insert into wifi_sensitive_word values ('淫騷妹');
insert into wifi_sensitive_word values ('淫兽');
insert into wifi_sensitive_word values ('淫兽学');
insert into wifi_sensitive_word values ('淫水');
insert into wifi_sensitive_word values ('淫穴');
insert into wifi_sensitive_word values ('隐形耳');
insert into wifi_sensitive_word values ('隐形喷剂');
insert into wifi_sensitive_word values ('应子弹');
insert into wifi_sensitive_word values ('婴儿命');
insert into wifi_sensitive_word values ('咏妓');
insert into wifi_sensitive_word values ('用手枪');
insert into wifi_sensitive_word values ('幽谷三');
insert into wifi_sensitive_word values ('游精佑');
insert into wifi_sensitive_word values ('有奶不一');
insert into wifi_sensitive_word values ('右转是政');
insert into wifi_sensitive_word values ('幼齿类');
insert into wifi_sensitive_word values ('娱乐透视');
insert into wifi_sensitive_word values ('愚民同');
insert into wifi_sensitive_word values ('愚民政');
insert into wifi_sensitive_word values ('与狗性');
insert into wifi_sensitive_word values ('玉蒲团');
insert into wifi_sensitive_word values ('育部女官');
insert into wifi_sensitive_word values ('冤民大');
insert into wifi_sensitive_word values ('鸳鸯洗');
insert into wifi_sensitive_word values ('园惨案');
insert into wifi_sensitive_word values ('园发生砍');
insert into wifi_sensitive_word values ('园砍杀');
insert into wifi_sensitive_word values ('园凶杀');
insert into wifi_sensitive_word values ('园血案');
insert into wifi_sensitive_word values ('原一九五七');
insert into wifi_sensitive_word values ('原装弹');
insert into wifi_sensitive_word values ('袁腾飞');
insert into wifi_sensitive_word values ('晕倒型');
insert into wifi_sensitive_word values ('韵徐娘');
insert into wifi_sensitive_word values ('遭便衣');
insert into wifi_sensitive_word values ('遭到警');
insert into wifi_sensitive_word values ('遭警察');
insert into wifi_sensitive_word values ('遭武警');
insert into wifi_sensitive_word values ('择油录');
insert into wifi_sensitive_word values ('曾道人');
insert into wifi_sensitive_word values ('炸弹教');
insert into wifi_sensitive_word values ('炸弹遥控');
insert into wifi_sensitive_word values ('炸广州');
insert into wifi_sensitive_word values ('炸立交');
insert into wifi_sensitive_word values ('炸药的制');
insert into wifi_sensitive_word values ('炸药配');
insert into wifi_sensitive_word values ('炸药制');
insert into wifi_sensitive_word values ('张春桥');
insert into wifi_sensitive_word values ('找枪手');
insert into wifi_sensitive_word values ('找援交');
insert into wifi_sensitive_word values ('找政法委副');
insert into wifi_sensitive_word values ('赵紫阳');
insert into wifi_sensitive_word values ('针刺案');
insert into wifi_sensitive_word values ('针刺伤');
insert into wifi_sensitive_word values ('针刺事');
insert into wifi_sensitive_word values ('针刺死');
insert into wifi_sensitive_word values ('侦探设备');
insert into wifi_sensitive_word values ('真钱斗地');
insert into wifi_sensitive_word values ('真钱投注');
insert into wifi_sensitive_word values ('真善忍');
insert into wifi_sensitive_word values ('真实资格');
insert into wifi_sensitive_word values ('震惊一个民');
insert into wifi_sensitive_word values ('震其国土');
insert into wifi_sensitive_word values ('证到付款');
insert into wifi_sensitive_word values ('证件办');
insert into wifi_sensitive_word values ('证件集团');
insert into wifi_sensitive_word values ('证生成器');
insert into wifi_sensitive_word values ('证书办');
insert into wifi_sensitive_word values ('证一次性');
insert into wifi_sensitive_word values ('政府操');
insert into wifi_sensitive_word values ('政论区');
insert into wifi_sensitive_word values ('證件');
insert into wifi_sensitive_word values ('植物冰');
insert into wifi_sensitive_word values ('殖器护');
insert into wifi_sensitive_word values ('指纹考勤');
insert into wifi_sensitive_word values ('指纹膜');
insert into wifi_sensitive_word values ('指纹套');
insert into wifi_sensitive_word values ('至国家高');
insert into wifi_sensitive_word values ('志不愿跟');
insert into wifi_sensitive_word values ('制服诱');
insert into wifi_sensitive_word values ('制手枪');
insert into wifi_sensitive_word values ('制证定金');
insert into wifi_sensitive_word values ('制作证件');
insert into wifi_sensitive_word values ('中的班禅');
insert into wifi_sensitive_word values ('中共黑');
insert into wifi_sensitive_word values ('中国不强');
insert into wifi_sensitive_word values ('种公务员');
insert into wifi_sensitive_word values ('种学历证');
insert into wifi_sensitive_word values ('众像羔');
insert into wifi_sensitive_word values ('州惨案');
insert into wifi_sensitive_word values ('州大批贪');
insert into wifi_sensitive_word values ('州三箭');
insert into wifi_sensitive_word values ('宙最高法');
insert into wifi_sensitive_word values ('昼将近');
insert into wifi_sensitive_word values ('主席忏');
insert into wifi_sensitive_word values ('住英国房');
insert into wifi_sensitive_word values ('助考');
insert into wifi_sensitive_word values ('助考网');
insert into wifi_sensitive_word values ('专业办理');
insert into wifi_sensitive_word values ('专业代');
insert into wifi_sensitive_word values ('专业代写');
insert into wifi_sensitive_word values ('专业助');
insert into wifi_sensitive_word values ('转是政府');
insert into wifi_sensitive_word values ('赚钱资料');
insert into wifi_sensitive_word values ('装弹甲');
insert into wifi_sensitive_word values ('装枪套');
insert into wifi_sensitive_word values ('装消音');
insert into wifi_sensitive_word values ('着护士的胸');
insert into wifi_sensitive_word values ('着涛哥');
insert into wifi_sensitive_word values ('姿不对死');
insert into wifi_sensitive_word values ('资格證');
insert into wifi_sensitive_word values ('资料泄');
insert into wifi_sensitive_word values ('梓健特药');
insert into wifi_sensitive_word values ('字牌汽');
insert into wifi_sensitive_word values ('自己找枪');
insert into wifi_sensitive_word values ('自慰用');
insert into wifi_sensitive_word values ('自由圣');
insert into wifi_sensitive_word values ('自由亚');
insert into wifi_sensitive_word values ('总会美女');
insert into wifi_sensitive_word values ('足球玩法');
insert into wifi_sensitive_word values ('最牛公安');
insert into wifi_sensitive_word values ('醉钢枪');
insert into wifi_sensitive_word values ('醉迷药');
insert into wifi_sensitive_word values ('醉乙醚');
insert into wifi_sensitive_word values ('尊爵粉');
insert into wifi_sensitive_word values ('左转是政');
insert into wifi_sensitive_word values ('作弊器');
insert into wifi_sensitive_word values ('作各种证');
insert into wifi_sensitive_word values ('作硝化甘');
insert into wifi_sensitive_word values ('唑仑');
insert into wifi_sensitive_word values ('做爱小');
insert into wifi_sensitive_word values ('做原子弹');
insert into wifi_sensitive_word values ('做证件');
insert into wifi_sensitive_word values ('系统管理员');
commit;

/*==============================================================*/
/* Table: wifi_sysconfig                                        */
/*==============================================================*/
create table wifi_sysconfig
(
   config_para_code     varchar(100) not null comment '配置参数名称',
   config_para_value    varchar(200) not null comment '配置参数取值',
   config_para_units    varchar(100) not null comment '配置参数单位',
   config_para_desc     varchar(100) comment '配置参数描述',
   config_para_ifactive tinyint not null default 1 comment '配置参数是否生效',
   primary key (config_para_code)
);

alter table wifi_sysconfig comment '系统配置表';

INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_MAX_RETRY_TIMES',
   '3',
   '次',
   '短信下发通知出错最大重试次数,首次发送也计算在内',
   1);
   
INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_PIN_TABLE_DONE',
   '桌主&1开桌了！大家联系TA哦~记得提醒桌主上传大家的合照，审核通过后大家就又能参与下一次抢桌了。',
   '变量:昵称(号码)',
   '桌主确认开桌后，桌主、其余入伙人收到的通知短信',
   1);

INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_PIN_TABLE_DONE_OWNER_VERIFY_CODE',
   '你的抢桌开桌于&1开桌了，本次抢桌的验证码为：&2。此验证码仅用于商户验证，请妥善保存，不然就没有优惠了！不可与其他优惠一起使用哦~',
   '变量1:开桌日期;变量2:商户验证码',
   '桌主确认开桌后，桌主收到的验证码短信',
   1);
   
INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_OK',
   '验证通过。&1用户获得全额消费减免&2元，不可与其他优惠一起使用。',
   '变量1:桌主号码;变量2:每单减免金额',
   '用户前往消费，商户根据验证码进行验证（商户编辑短信：渠道码+验证码上行至1065xxxx）',
   1);
   
INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_USED',
   '验证失败。&1用户在&2已使用优惠。',
   '变量1:桌主号码;变量2:消费日期',
   '用户前往消费，商户根据验证码进行验证（商户编辑短信：渠道码+验证码上行至1065xxxx）',
   1);
   
INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_ERROR_CHANNEL',
   '验证失败。&1用户不能在此渠道使用。',
   '变量:桌主号码',
   '用户前往消费，商户根据验证码进行验证（商户编辑短信：渠道码+验证码上行至1065xxxx）',
   1);

INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_PASSWORD',
   '【中国移动免费wifi上网】&1（wifi上网验证码，中国移动）',
   '变量1:验证码',
   '短信验证码下发模板',
   1); 

INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_WEB_AUTH_URL',
   'http://10.0.1.253/cgi-bin/ace_web_auth.cgi',
   'URL',
   '认证鉴权URL',
   1);
         
COMMIT;

alter table app_click_detail add constraint FK_fk_app_info foreign key (app_id)
      references app_info (app_id) on delete restrict on update restrict;

alter table headline_content_attach add constraint FK_ref_attach_headline_content foreign key (headline_id)
      references headline_content (headline_id) on delete cascade on update restrict;

alter table headline_reply add constraint FK_ref_reply_headline_content foreign key (headline_id)
      references headline_content (headline_id) on delete cascade on update restrict;

alter table video_play add constraint FK_fk_video_info foreign key (video_id)
      references video_info (video_id) on delete restrict on update restrict;


DELIMITER $$
create trigger trig_insert_app_click_detail after insert
on app_click_detail for each row
begin
  update app_info
  set app_download_times=app_download_times+1
  where app_id=new.app_id;
end;
$$
DELIMITER ;


DELIMITER $$
create trigger trig_delete_on_headline_reply after delete
on headline_reply for each row
begin
  if (old.reply_type=1) then
     UPDATE headline_content
     SET    reply_num=if(reply_num-1<0,0,reply_num-1)
     WHERE  headline_id=old.headline_id;
  end if;
end;
$$
DELIMITER ;


delimiter $$
create trigger trig_insert_on_headline_reply after insert
on headline_reply for each row
begin
  if (new.reply_type=2) then
     UPDATE headline_content 
     SET    praise_num=praise_num+1
     WHERE  headline_id=new.headline_id;
  end if;
  if (new.reply_type=1) then
     UPDATE headline_content 
     SET    reply_num=reply_num+1
     WHERE  headline_id=new.headline_id;
  end if;  
end;
$$
delimiter ;


delimiter $$
create trigger trig_insert_video_play after insert
on video_play for each row
begin
  update video_info 
  set play_cnt=play_cnt+1
  where video_id=new.video_id;
end;
$$
delimiter ;

set foreign_key_checks=1;


