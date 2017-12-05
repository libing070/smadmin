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
   account              varchar(11) not null default '�˺�',
   password             varchar(50) not null comment '����',
   nickname             varchar(50) not null comment '�ǳ�',
   createTime           timestamp not null default '0000-00-00 00:00:00' comment '����ʱ��',
   roles                varchar(50) default NULL comment '��ɫ',
   primary key (id)
);

INSERT INTO SHIROUSER_MANAGE VALUES ('1','admin','00b7691d86d96aebd21dd9e138f90840','admin','2014-08-13 19:26:32','1');
commit;

/*==============================================================*/
/* Table: act_status                                            */
/*==============================================================*/
create table act_status
(
   act_status_id        tinyint not null comment 'ƴ��״̬ID',
   act_status_name      varchar(50) not null comment 'ƴ��״̬',
   primary key (act_status_id)
);

alter table act_status comment 'ƴ���״̬��';

insert into act_status values (1,'���������');
insert into act_status values (2,'�ѷ���(���ͨ��)');
insert into act_status values (3,'����ʧ��(��˲�ͨ��)');
insert into act_status values (4,'�ѹ���Ч��');
insert into act_status values (5,'�ѳɵ�');
insert into act_status values (6,'�ϴ����մ����');
insert into act_status values (7,'�ϴ�������˲�ͨ��');
insert into act_status values (8,'�����');
insert into act_status values (9,'���ύ���ѷ�Ʊ���첹��');
insert into act_status values (10,'�ѽ�ɢ');
commit;

/*==============================================================*/
/* Table: act_type                                              */
/*==============================================================*/
create table act_type
(
   act_type_id          tinyint not null comment 'ƴ������ID',
   act_type_name        varchar(50) not null comment 'ƴ������',
   act_type_picture     varchar(200) comment 'ƴ�����Ͷ�Ӧͼ��·��',
   primary key (act_type_id)
);

alter table act_type comment 'ƴ������ͱ�';

insert into act_type values (1,'����','');
insert into act_type values (2,'�˶�','');
insert into act_type values (3,'����','');
commit;

/*==============================================================*/
/* Table: app_click_detail                                      */
/*==============================================================*/
create table app_click_detail
(
   app_id               bigint not null comment 'Ӧ��ID',
   mobile               varchar(11) not null comment '�������',
   oper_date            timestamp not null default CURRENT_TIMESTAMP comment '���ʱ��',
   primary key (app_id, mobile, oper_date)
);

alter table app_click_detail comment 'Ӧ�õ����ϸ��';

/*==============================================================*/
/* Table: app_info                                              */
/*==============================================================*/
create table app_info
(
   app_id               bigint not null auto_increment comment 'Ӧ��ID',
   app_name             varchar(30)  comment 'Ӧ������',
   app_desc             varchar(255) comment '����',
   app_type             tinyint comment 'Ӧ�÷���
            1 ��Ϸ
            2 �罻
            3 ����
            4 ����
            5 ����',
   app_for_system       varchar(255) comment 'Ӧ��֧��ϵͳ',
   icon                 mediumblob  comment 'Ӧ��ͼ��',
   app_file_url         varchar(1024) not null comment 'Ӧ������URL',
   app_version          varchar(50) comment '�汾��Ϣ',
   app_size             int comment 'Ӧ�ô�С(�ֽ�)',
   app_download_times   bigint default 0 comment '���ش���',
   app_star_level       tinyint comment '�Ƽ��Ǽ�',
   app_developer        varchar(255) comment 'Ӧ�ÿ�����',
   app_demo_pic1        mediumblob comment 'Ӧ�ý����ͼ1',
   app_demo_pic2        mediumblob comment 'Ӧ�ý����ͼ2',
   app_demo_pic3        mediumblob comment 'Ӧ�ý����ͼ3',
   app_demo_pic4        mediumblob comment 'Ӧ�ý����ͼ4',
   last_update_time     timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '������ʱ��',
   is_top_area          tinyint default 0 comment '�Ƿ��ö�
            1 �ö�
            0 ���ö�',
   app_top_pic          mediumblob comment '�ö�ͼƬ',
   app_status			int default 0 comment '״̬��0����ʾ����״̬ 1����ʾ����״̬ 2����ʾ����״̬',
   click_times			int default 0 comment 'app�����',
   primary key (app_id)
);

alter table app_info comment 'Ӧ�ñ�';

/*==============================================================*/
/* Table: auth_device_account_info                              */
/*==============================================================*/
create table auth_device_account_info
(
   user_account         varchar(10) not null comment '��֤�˺�',
   user_passwd          varchar(6) not null comment '��֤�˺�����',
   used_flag            tinyint default 0 comment '��֤�豸�˺�ʹ��״��
            0 δʹ��
            1 ��ʹ��',
   primary key (user_account)
);

alter table auth_device_account_info comment '��֤�豸�˺ű�';

/*==============================================================*/
/* Table: consume_place                                         */
/*==============================================================*/
create table consume_place
(
   consume_place_id     int not null auto_increment comment 'ID',
   consume_place_name   varchar(100) not null comment '�ص�����',
   consume_place_addr   varchar(200) not null comment '�ص����ڵ�ַ',
   consume_place_desc   varchar(500) comment '�ص�����',
   act_type_id          tinyint not null comment 'ƴ������ID',
   publish_date         timestamp default CURRENT_TIMESTAMP comment '����ʱ��',
   publish_user         varchar(100) comment '������',
   business_code        varchar(6) not null comment '�̼���������',
   contacts             varchar(20) comment '�̼���ϵ��',
   contact_phone        varchar(20) comment '�̼���ϵ�绰',
   order_phone          varchar(20) comment '�̼�Ԥ���绰',
   primary key (consume_place_id)
);

alter table consume_place comment 'ƴ���ص��';

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
('���׸�',
 'ѧ��·26��',
 '��ǩ��������',
 1,
 '����Ա',
 '010001',
 '����',
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
('�ֵ������Բ���',
 '��԰·132��',
 '��ǩ��������',
 1,
 '����Ա',
 '010002',
 '����',
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
('��������',
 '��԰·126��',
 '��ǩ��������',
 1,
 '����Ա', 
 '010003',
 '֣��',
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
('���ִ�������',
 '��԰·45��',
 '��ǩ��������',
 1,
 '����Ա', 
 '010004',
 '������',
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
('���������',
 '��԰·148��',
 '��ǩ��������',
 1,
 '����Ա', 
 '010005',
 '��ǿ',
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
('������',
 '��԰·31��������ѧ����',
 '��ǩ��������',
 1,
 '����Ա', 
 '010006',
 '֣��',
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
('�ҺϷ���',
 '��԰��21���������Ŷ����ͬ20��',
 '��ǩ��������',
 1,
 '����Ա', 
 '010007',
 '��Ⱥ',
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
('����̨���',
 'ѧ��·26�� ���׸���4¥',
 '��ǩ��������',
 3,
 '����Ա', 
 '030001',
 '������',
 '13901298870',
 '13901298870'); 
commit;

/*==============================================================*/
/* Table: headline_content                                      */
/*==============================================================*/
create table headline_content
(
   headline_id          bigint not null auto_increment comment 'ͷ��ID',
   mobile               varchar(11) not null comment '����
            ��content_type_id=6��ʾ����ʱ,
            �˴��ĺ���Ϊ���������˵ĺ���
            ��������� ����ʾ�û�������ĺ���',
   nickname             varchar(11) comment '�ǳ�',
   content_type_id      smallint not null comment 'ͷ������
            1 ��ˮ
            2 ����
            3 ����
            4 ��Ƹ
            5 ����
            6 ����',
   title                varchar(100) comment '�滻���дʺ��ͷ������',
   orig_title           varchar(100) comment 'ԭʼͷ������',
   content              varchar(10000) comment '�滻���дʺ��ͷ������',
   orig_content         varchar(10000) comment 'ԭʼͷ������',
   oper_date            timestamp default CURRENT_TIMESTAMP comment '����ʱ��',
   create_date 			timestamp default CURRENT_TIMESTAMP comment '��������',
   audit_user           varchar(20) comment '�����',
   audit_time           timestamp comment '���ʱ��',
   audit_comment        varchar(200) comment '������',
   status               smallint default 0 comment 'ͷ��״̬
            0 ��ʼδ���
            1 ���δͨ��
            2 ���ͨ��
            3 ��ɾ��',
   top_number           bigint comment '�ö����
            1��ʾ ����ö� 2 ���  չʾʱ�򰴴�����ҳ��',
   if_support_comment   tinyint default 1 comment '�Ƿ��������
            1 ����
            2 ������',
   reply_num            int default 0 comment '����������',
   praise_num           int default 0 comment '���µ�����',
   click_num            int DEFAULT 0 COMMENT '���µ����',
   primary key (headline_id)
);

alter table headline_content comment 'ͷ��';

/*==============================================================*/
/* Table: headline_content_attach                               */
/*==============================================================*/
create table headline_content_attach
(
   picture_serialnum    bigint not null auto_increment comment 'ͷ����ӦͼƬID',
   picture              mediumblob comment 'ͷ����ӦͼƬ',
   headline_id          bigint not null comment 'ͷ��ID',
   primary key (picture_serialnum)
);

alter table headline_content_attach comment 'ͷ������';

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
   reply_id             bigint not null auto_increment comment '����ID',
   headline_id          bigint not null comment 'ͷ��ID',
   mobile               varchar(11) not null comment '����',
   nickname             varchar(11) comment '�ǳ�',
   reply_type           tinyint comment '��������
            1 ����
            2 ����',
   content              varchar(140) comment '�滻���дʺ�Ļظ�����',
   orig_content         varchar(140) comment 'ԭʼ�ظ�����',
   parent_reply_id      bigint not null comment '����ID��Ӧ�ϼ�����ID
            0 ��ʾ �������Ѿ��Ƕ����ڵ�',
   parent_mobile        varchar(11) not null comment '����ID��Ӧ�ϼ�����',
   reply_date           timestamp default CURRENT_TIMESTAMP comment '����ʱ��',
   audit_user           varchar(20) comment '�����',
   audit_time           timestamp comment '���ʱ��',
   audit_comment        varchar(200) comment '������',
   status               smallint default 0 comment '״̬
            0 ��ʼδ���
            1 ���δͨ��
            2 ���ͨ��
            3 ��ɾ��',
   primary key (reply_id)
);

alter table headline_reply comment '����';

/*==============================================================*/
/* Table: hometown                                              */
/*==============================================================*/
create table hometown
(
   province_id          tinyint not null comment 'ʡID',
   province_name        varchar(30) comment 'ʡ��',
   city_id              smallint not null comment '����ID',
   city_name            varchar(30) comment '����',
   primary key (province_id, city_id)
);

alter table hometown comment '�����';

insert into hometown (province_id, province_name, city_id, city_name)
values (1, '����', 1, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (2, '�Ϻ�', 2, '�Ϻ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (3, '���', 3, '���');
insert into hometown (province_id, province_name, city_id, city_name)
values (4, '����', 4, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '������', 477, '���˰���');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '������', 156, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '������', 157, '������');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '������', 158, '�׸�');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '������', 159, '�ں�');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '������', 160, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '������', 161, '��ľ˹');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '������', 162, 'ĵ����');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '������', 163, '��̨��');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '������', 164, '�������');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '������', 165, '˫Ѽɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '������', 167, '�绯');
insert into hometown (province_id, province_name, city_id, city_name)
values (5, '������', 169, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '����', 523, '��ԭ');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '����', 531, '�ӱ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '����', 223, '�׳�');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '����', 224, '��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '����', 225, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '����', 231, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '����', 232, '��Դ');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '����', 234, '��ƽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (6, '����', 235, 'ͨ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 286, '��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 288, '��Ϫ');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 289, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 290, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 291, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 292, '��˳');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 293, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 295, '��«��');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 297, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 298, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 299, '�̽�');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 300, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 301, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (7, '����', 305, 'Ӫ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '���ɹ�', 465, '��������');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '���ɹ�', 481, '������˹');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '���ɹ�', 668, '���ױ���');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '���ɹ�', 552, '�����첼');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '���ɹ�', 556, '�˰���');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '���ɹ�', 675, '�����׶�');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '���ɹ�', 677, '���ֹ�����');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '���ɹ�', 306, '��ͷ');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '���ɹ�', 307, '���');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '���ɹ�', 310, '���ͺ���');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '���ɹ�', 314, 'ͨ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (8, '���ɹ�', 315, '�ں�');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '�ӱ�', 108, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '�ӱ�', 110, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '�ӱ�', 111, '�е�');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '�ӱ�', 114, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '�ӱ�', 116, '��ˮ');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '�ӱ�', 117, '�ȷ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '�ӱ�', 119, '�ػʵ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '�ӱ�', 122, 'ʯ��ׯ');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '�ӱ�', 123, '��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '�ӱ�', 126, '��̨');
insert into hometown (province_id, province_name, city_id, city_name)
values (9, '�ӱ�', 127, '�żҿ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 545, '��Դ');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 129, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 131, '�ױ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 133, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 134, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 135, '���');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 137, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 138, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 139, 'ƽ��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 140, '���');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 141, '����Ͽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 142, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 147, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 148, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 149, '���');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 151, '֣��');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 152, 'פ���');
insert into hometown (province_id, province_name, city_id, city_name)
values (10, '����', 153, '�ܿ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 537, '�Ƹ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 538, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 541, '��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 542, '�麣');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 54, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 56, '��ݸ');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 57, '��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 60, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 61, '��Դ');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 64, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 65, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 66, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 70, 'ï��');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 71, '÷��');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 74, '��Զ');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 75, '��ͷ');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 76, '��β');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 77, '�ع�');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 78, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 82, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (11, '�㶫', 84, 'տ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 482, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 495, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 498, '�Ƹ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 501, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 560, '��ũ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 561, 'Ǳ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 174, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 175, '��ʩ');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 178, '��ʯ');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 179, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 180, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 182, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 187, 'ʮ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 188, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 190, '�人');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 193, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 194, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 195, 'Т��');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 196, '�˲�');
insert into hometown (province_id, province_name, city_id, city_name)
values (12, '����', 200, '�Ƹ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 348, '̩��');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 350, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 351, 'Ϋ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 354, '��̨');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 356, '��ׯ');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 358, '�Ͳ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 588, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 328, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 329, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 330, '��Ӫ');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 332, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 333, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 334, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 337, '�ĳ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 339, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 342, '�ൺ');
insert into hometown (province_id, province_name, city_id, city_name)
values (13, 'ɽ��', 345, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '�㽭', 431, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '�㽭', 432, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '�㽭', 433, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '�㽭', 437, '��');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '�㽭', 439, '��ˮ');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '�㽭', 441, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '�㽭', 442, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '�㽭', 444, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '�㽭', 446, '̨��');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '�㽭', 449, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (14, '�㽭', 457, '��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 473, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 558, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 5, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 6, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 9, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 10, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 11, '�Ϸ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 12, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 13, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 14, '��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 15, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 16, '��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 17, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 19, 'ͭ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 20, '�ߺ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (15, '����', 21, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '����', 539, '��');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '����', 241, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '����', 246, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '����', 253, '���Ƹ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '����', 254, '�Ͼ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '����', 255, '��ͨ');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '����', 259, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '����', 260, '��Ǩ');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '����', 263, '̩��');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '����', 265, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '����', 269, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '����', 270, '�γ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (16, '����', 271, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '����', 533, '�˴�');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '����', 272, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '����', 273, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '����', 274, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '����', 275, '������');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '����', 277, '�Ž�');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '����', 280, '�ϲ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '����', 281, 'Ƽ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '����', 282, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '����', 283, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (17, '����', 285, 'ӥ̶');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 416, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 417, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 418, '�ն�');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 420, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 421, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 422, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 424, '��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 425, '��Ϫ');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 426, '��ͨ');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 469, '��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 478, '�º�');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 479, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 497, '���');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 506, '�ٲ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 513, 'ŭ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (18, '����', 559, '��˫����');
insert into hometown (province_id, province_name, city_id, city_name)
values (19, '����', 681, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (19, '����', 320, '��ԭ');
insert into hometown (province_id, province_name, city_id, city_name)
values (19, '����', 322, 'ʯ��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (19, '����', 323, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (19, '����', 324, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '�ຣ', 490, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '�ຣ', 491, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '�ຣ', 492, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '�ຣ', 494, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '�ຣ', 499, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '�ຣ', 536, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '�ຣ', 573, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (20, '�ຣ', 326, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, 'ɽ��', 371, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, 'ɽ��', 372, '��ͬ');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, 'ɽ��', 374, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, 'ɽ��', 375, '�ٷ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, 'ɽ��', 376, '˷��');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, 'ɽ��', 377, '̫ԭ');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, 'ɽ��', 378, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, 'ɽ��', 379, '��Ȫ');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, 'ɽ��', 381, '�˳�');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, 'ɽ��', 500, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (21, 'ɽ��', 509, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '����', 359, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '����', 360, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '����', 361, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '����', 364, 'ͭ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '����', 365, 'μ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '����', 366, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '����', 367, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '����', 368, '�Ӱ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '����', 369, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (22, '����', 522, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 543, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 201, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 202, '��ɳ');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 203, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 204, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 206, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 212, '¦��');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 214, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 215, '��̶');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 217, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 218, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 219, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 220, '�żҽ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (23, '����', 222, '����������');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '����', 26, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '����', 28, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '����', 30, '��ƽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '����', 31, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '����', 32, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '����', 33, 'Ȫ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '����', 34, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '����', 37, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (24, '����', 39, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 480, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 486, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 508, '¤��');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 518, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 41, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 43, '������');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 44, '���');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 45, '��Ȫ');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 46, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 47, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 48, 'ƽ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 49, '��ˮ');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 50, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (25, '����', 52, '��Ҵ');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 383, '�ɶ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 384, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 386, '��Ԫ');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 387, '��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 388, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 389, 'üɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 390, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 391, '�ϳ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 392, '�ڽ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 393, '��֦��');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 395, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 397, '�Ű�');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 398, '�˱�');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 399, '�Թ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 464, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 467, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 476, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 488, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 489, '�㰲');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 504, '��ɽ');
insert into hometown (province_id, province_name, city_id, city_name)
values (26, '�Ĵ�', 544, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 483, '���Ǹ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 484, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 562, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 563, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 564, '���');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 85, '��ɫ');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 86, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 88, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 89, '�ӳ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 90, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 91, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 93, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 94, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (27, '����', 95, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '����', 470, '�Ͻ�');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '����', 514, 'ǭ����');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '����', 516, 'ǭ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '����', 517, 'ǭ����');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '����', 526, 'ͭ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '����', 96, '��˳');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '����', 99, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '����', 101, '����ˮ');
insert into hometown (province_id, province_name, city_id, city_name)
values (28, '����', 102, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (29, '����', 493, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (29, '����', 565, '��ɳ');
insert into hometown (province_id, province_name, city_id, city_name)
values (29, '����', 103, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '����', 505, '��֥');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '����', 512, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '����', 519, '�տ���');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '����', 521, 'ɽ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '����', 22, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '����', 23, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (30, '����', 24, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 400, '������');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 401, '����̩');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 402, '������');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 403, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 404, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 405, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 406, '��ʲ');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 407, '��������');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 408, 'ͼľ���');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 409, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 410, 'ʯ����');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 411, '����');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 412, '��³��');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 413, '��³ľ��');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 414, '��������');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 471, '��������');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 472, '��������');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 503, '�����');
insert into hometown (province_id, province_name, city_id, city_name)
values (31, '�½�', 532, '����');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',701,'̨����');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',702,'������');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',703,'��¡��');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',704,'̨����');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',705,'̨����');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',706,'������');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',707,'������');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',708,'̨����');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',709,'������');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',710,'̨����');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',711,'̨����');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',712,'������');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',713,'������');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',714,'������');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',715,'��Դ��');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',716,'������');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',717,'�û���');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',718,'��Ͷ��');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',719,'������');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',720,'������');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',721,'̨����');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',722,'������');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (32,'̨��',723,'�����');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (33,'���',801,'���');
INSERT INTO hometown (province_id, province_name, city_id, city_name)
VALUES (34,'����',901,'����');

commit;

/*==============================================================*/
/* Table: mobile_reply_review                                   */
/*==============================================================*/
create table mobile_reply_review
(
   mobile               varchar(11) not null comment '����',
   last_click_reply_time datetime comment '������ҵ�����ʱ��',
   last_click_praise_time datetime comment '������ҵ���ʱ��',
   primary key (mobile)
);

alter table mobile_reply_review comment '�û��鿴���۱�';

/*==============================================================*/
/* Table: notice                                                */
/*==============================================================*/
create table notice
(
   notice_id            int not null auto_increment comment '����ID',
   notice_name          varchar(100) not null comment '�������',
   notice_content       text not null comment '��������',
   notice_status_id     tinyint not null comment '����״̬
            1 ��Ч
            2 ʧЧ',
   notice_type_id       tinyint not null comment '��������
            1 ֪ͨ
            2 ����',
   insert_date          timestamp default CURRENT_TIMESTAMP comment '���ʱ��',
   begin_time           timestamp,
   end_time             timestamp,
   primary key (notice_id)
);

alter table notice comment '�����';

/*==============================================================*/
/* Table: pin_action                                            */
/*==============================================================*/
create table pin_action
(
   flash_sale_id        bigint not null auto_increment comment '����ID',
   owner_mobile         varchar(11) not null comment '��������',
   flash_sale_date      timestamp not null comment '����ʱ��',
   expired_date         timestamp comment '������Ч��',
   createtable_date     timestamp comment '������ʱ��',
   primary key (flash_sale_id)
);

alter table pin_action comment '������(ֻ��¼�û�����Ч�ɹ�����)';

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
   hist_id              bigint not null auto_increment comment 'ID(����)',
   flash_sale_id        bigint not null comment '����ID',
   owner_mobile         varchar(11) not null comment '��������',
   action_date          timestamp not null default CURRENT_TIMESTAMP comment '�䶯ʱ��',
   action_type          tinyint not null comment '�䶯����:
            1 ����
            2 �޸�
            3 ɾ��',
   action_desc          varchar(100) comment '�䶯����',
   flash_sale_date      timestamp not null comment '����ʱ��',
   createtable_date     timestamp comment '������ʱ��',
   primary key (hist_id)
);

alter table pin_action_hist comment '������ʷ��';

/*==============================================================*/
/* Table: pin_activity                                          */
/*==============================================================*/
create table pin_activity
(
   activity_id          int not null comment '�ID',
   activity_name        varchar(100) not null comment '�����',
   activity_status      smallint not null comment '�״̬
            1 ��Ч
            2 ʧЧ',
   start_week_day       smallint comment '���ʼ���ܼ�',
   end_week_day         smallint comment '��������ܼ�',
   start_hour           smallint comment '����쿪ʼʱ��',
   end_hour             smallint comment '������ֹʱ��',
   quota                int comment '�ÿ���������������',
   cash_subsidy         int comment '�ÿ���������������ֽ�����(��λ:Ԫ)',
   sale_person_num      int comment '���Чƴ��Ҫ�������',
   persion_participate_cnt int comment '�ÿ�������ڲ���������ƴ���Ĵ���',
   pin_action_invalidate int comment '���������Ч��(��λ:����)',
   pin_createtable_invalidate int comment '�ƴ����Ч��(��λ:��)',
   visit_pv_cnt         bigint default 0 comment '��ۼƲ����˴���(����� ����������ƴ��)',
   rule_desc            varchar(20000) comment '���������',
   primary key (activity_id)
);

alter table pin_activity comment '�������';

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
   '�����',
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
   flash_sale_id        bigint not null comment '����ID',
   act_desc             varchar(31) comment 'ƴ��˵��',
   act_type_id          tinyint not null comment 'ƴ������ID',
   act_status_id        tinyint not null comment 'ƴ��״̬ID',
   consume_place_id     int not null comment 'ƴ���ص�ID',
   createtable_date     timestamp default CURRENT_TIMESTAMP comment '������ʱ��',
   expired_date         timestamp comment '������Ч��',
   owner_mobile         varchar(11) not null comment 'ƴ����Ӧ������',
   consume_pic          mediumblob comment '��������',
   free_seat            tinyint comment 'ʣ��ϯλ',
   audit_user           varchar(20) comment '�����',
   audit_time           timestamp comment '���ʱ��',
   audit_status         tinyint comment '���״̬',
   audit_comment        varchar(200) comment '������',
   verification_code    varchar(20) comment 'ֻ�������ṩ,���ں������̻�����',
   mo_sms_time          timestamp comment '�̻��ϴ����Ѷ���ʱ��',
   primary key (flash_sale_id)
);

alter table pin_createtable comment 'ƴ�����';

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
   flash_sale_id        bigint not null comment '����ID',
   mobile               varchar(11) not null comment 'ƴ���˺���',
   join_date            timestamp not null default CURRENT_TIMESTAMP comment 'ƴ���˼���ʱ��',
   is_owner_mobile      tinyint not null comment '�Ƿ�����
            1 ������
            0 ������',
   is_footmark          tinyint not null default 0 comment '�Ƿ��û��Ķ���תΪ�㼣',
   primary key (flash_sale_id, mobile)
);

alter table pin_createtable_detail comment 'ƴ�����ϸ��';

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
   hist_id              bigint not null auto_increment comment 'ID(����)',
   flash_sale_id        bigint not null comment '����ID',
   mobile               varchar(11) not null comment 'ƴ���˺���',
   is_owner_mobile      tinyint not null comment '�Ƿ�����
            1 ������
            0 ������',
   action_date          timestamp not null default CURRENT_TIMESTAMP comment '�䶯ʱ��',
   action_type          tinyint not null comment '�䶯����:
            1  ����
            2  �˳�
            3  ��ɾ��',
   action_desc          varchar(100) comment '�䶯����',
   join_date            timestamp not null comment 'ƴ���˼���ʱ��',
   primary key (hist_id)
);

alter table pin_createtable_detail_hist comment 'ƴ�����ϸ�䶯��ʷ��';

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
   hist_id              bigint not null auto_increment comment 'ID(����)',
   flash_sale_id        bigint not null comment '����ID',
   act_desc             varchar(31) comment 'ƴ��˵��',
   act_type_id          tinyint not null comment 'ƴ������ID',
   act_status_id        tinyint not null comment 'ƴ��״̬ID',
   consume_place_id     int not null comment 'ƴ���ص�ID',
   owner_mobile         varchar(11) not null comment 'ƴ����Ӧ������',
   consume_pic          mediumblob comment '��������',
   action_date          timestamp not null default CURRENT_TIMESTAMP comment '�䶯ʱ��',
   action_type          tinyint not null comment '�䶯����:
            1 ����
            2 �޸�
            3 ɾ��',
   action_desc          varchar(100) comment '�䶯����',
   createtable_date     timestamp comment '������ʱ��',
   expired_date         timestamp comment '������Ч��',
   audit_user           varchar(20) comment '�����Ա',
   audit_time           timestamp comment '���ʱ��',
   audit_status         tinyint comment '���״̬',
   audit_comment        varchar(200) comment '������',
   verification_code    varchar(20) comment 'ֻ�������ṩ,���ں������̻�����',
   mo_sms_time          timestamp comment '�̻��ϴ����Ѷ���ʱ��',
   primary key (hist_id)
);

alter table pin_createtable_hist comment 'ƴ�����ʷ��';

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
   mobile               varchar(11) comment '�ֻ�����',
   content              varchar(140) comment '�·�����',
   priority             smallint comment '���ȼ�
            1 ��߼�
            2 ��֮',
   create_time          timestamp default CURRENT_TIMESTAMP comment '��¼����ʱ��',
   send_status          smallint comment '����״̬
            0 ������
            1 ������
            2 �ѷ���
            3 ���ͳ���',
   send_result          varchar(100) comment '���ͽ������',
   send_time            timestamp comment '����ʱ��',
   retry_times          tinyint default 1 comment '���Դ���',
   gw_sms_id            varchar(20) comment '��ҵ������Ϣ��ʶID',
   primary key (id)
);

alter table sms_notice comment '�����·�֪ͨ��';

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
   msisdn               varchar(20) not null default '' comment '�ֻ���',
   report_pic           mediumblob not null comment '����ͼƬ��',
   result               int(1) not null default 0 comment '0������ˣ�1��ͨ����2��δͨ����',
   result_desc          varchar(255) default NULL comment '���ʧ��ԭ��',
   result_back          int(1) not null default 0 comment '�������״̬ 0��δ������1���ѷ���',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '����ʱ��',
   create_user          varchar(20) comment '������',
   is_footmark          tinyint not null default 0 comment '�Ƿ��û��Ķ���תΪ�㼣',
   primary key (Id)
);

alter table student_report comment '������ѧ������';

/*==============================================================*/
/* Table: user_info                                             */
/*==============================================================*/
create table user_info
(
   mobile               varchar(11) not null comment '�ֻ���',
   nickname             varchar(11) not null comment '�ǳ�',
   sex                  char(1) comment '�Ա�',
   head_show            mediumblob comment 'ͷ��',
   province             varchar(20) comment '�ϼ�����ʡ��',
   city                 varchar(50) comment '�ϼ����ڳ���',
   reg_date             timestamp default CURRENT_TIMESTAMP comment 'ע��ʱ��',
   primary key (mobile),
   unique key AK_unique_nickname (nickname)
);

alter table user_info comment '�û���';

INSERT INTO user_info VALUES ('admin', '����Ա', '��', NULL, '����', '����', now());
commit;

/*==============================================================*/
/* Table: video_info                                            */
/*==============================================================*/
create table video_info
(
   video_id             bigint not null auto_increment comment '��ƵID',
   video_name           varchar(50)  comment '��Ƶ����',
   video_desc           varchar(255) comment '��Ƶ����',
   video_type           tinyint comment '��Ƶ����
            1 ��Ӱ����
            2 ���ְ���
            3 ΢������
            4 ����',
   resource_path        varchar(255) not null comment '��Դ·��',
   video_pic            mediumblob comment 'Ԥ��ͼ ����',
   show_time_long       bigint comment '����ʱ��(��λ:��)',
   play_cnt             bigint default 0 comment '��Ƶ�㲥�ۼƴ���',
   last_update_time     timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '������ʱ��',
   is_top_area          tinyint default 0 comment '�Ƿ��ö�
            1 �ö�
            0 ���ö�',
   video_top_pic        mediumblob comment '�ö�ͼƬ',
   video_status 		int(4) NOT NULL DEFAULT '0' COMMENT '״̬��0����ʾ����״̬ 1����ʾ����״̬ 2����ʾ����״̬',
   primary key (video_id),
   unique key AK_resource_path (resource_path)
);

alter table video_info comment '��Ƶ��Ϣ';

/*==============================================================*/
/* Table: video_play                                            */
/*==============================================================*/
create table video_play
(
   video_id             bigint not null comment '��ƵID',
   mobile               varchar(11) not null comment '�������',
   oper_date            timestamp not null default CURRENT_TIMESTAMP comment '���ʱ��',
   primary key (video_id, mobile, oper_date)
);

alter table video_play comment '��Ƶ������Ϣ';

/*==============================================================*/
/* Table: website_click_detail                                  */
/*==============================================================*/
create table website_click_detail
(
   id                   bigint not null comment '��վID',
   mobile               varchar(11) not null comment '�������',
   nickname             varchar(8) not null comment '�ǳ�',
   oper_date            timestamp not null default CURRENT_TIMESTAMP comment '���ʱ��',
   primary key (id, mobile, nickname, oper_date)
);

alter table website_click_detail comment '��վ�����ϸ��';

/*==============================================================*/
/* Table: website_info                                          */
/*==============================================================*/
create table website_info
(
   id                   bigint not null auto_increment comment 'ID',
   title                varchar(100) comment '��վ����',
   type                 tinyint comment '��վ����
            1 ����
            2 ����
            3 ����
            4 ����',
   url                  varchar(1000) comment '��վURL��ַ',
   picture              mediumblob comment '��վͼ��',
   content              varchar(500) comment '��վ���',
   oper_date            timestamp default CURRENT_TIMESTAMP comment '���ʱ��',
   oper_user            varchar(20) comment '�����',
   primary key (id)
);

alter table website_info comment '��վ��Ϣ��';

/*==============================================================*/
/* Table: wifi_sensitive_word                                   */
/*==============================================================*/
create table wifi_sensitive_word
(
   sensitive_word       varchar(20) not null,
   primary key (sensitive_word)
);

alter table wifi_sensitive_word comment '���д�';

insert into wifi_sensitive_word values ('�����Ʒ�');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('���e');
insert into wifi_sensitive_word values ('����һ��');
insert into wifi_sensitive_word values ('��Һ����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���ְ칫¥');
insert into wifi_sensitive_word values ('���ֺ���');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����ˎ');
insert into wifi_sensitive_word values ('����׼ȷ');
insert into wifi_sensitive_word values ('�˾���');
insert into wifi_sensitive_word values ('�˾�ѧ');
insert into wifi_sensitive_word values ('�˾�����');
insert into wifi_sensitive_word values ('�Ѳ�����');
insert into wifi_sensitive_word values ('�ѵ�Сƽ');
insert into wifi_sensitive_word values ('��ѧ����');
insert into wifi_sensitive_word values ('�չ���');
insert into wifi_sensitive_word values ('�׻���ǩ');
insert into wifi_sensitive_word values ('����ѵ');
insert into wifi_sensitive_word values ('�챾��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('����Ʊ��');
insert into wifi_sensitive_word values ('������ƾ');
insert into wifi_sensitive_word values ('������ʵ');
insert into wifi_sensitive_word values ('����֤��');
insert into wifi_sensitive_word values ('�����ʸ�');
insert into wifi_sensitive_word values ('����ƾ');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('��֤');
insert into wifi_sensitive_word values ('��̵�');
insert into wifi_sensitive_word values ('�k��ҵ');
insert into wifi_sensitive_word values ('�k�C');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('��ң����');
insert into wifi_sensitive_word values ('���ڸ�����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����ִ��');
insert into wifi_sensitive_word values ('����ɧ');
insert into wifi_sensitive_word values ('��ʡί��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ָ��Ϯ');
insert into wifi_sensitive_word values ('���й�');
insert into wifi_sensitive_word values ('����˾��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('�Ծ���');
insert into wifi_sensitive_word values ('��ҵ�C');
insert into wifi_sensitive_word values ('���ƾ�');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���ڻ���');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������ͣ');
insert into wifi_sensitive_word values ('��԰��α');
insert into wifi_sensitive_word values ('���鶼');
insert into wifi_sensitive_word values ('����ȫ');
insert into wifi_sensitive_word values ('��˼�Ļ�');
insert into wifi_sensitive_word values ('������Ů');
insert into wifi_sensitive_word values ('��æ���');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('��֪��ֻ��');
insert into wifi_sensitive_word values ('���ڿƼ�');
insert into wifi_sensitive_word values ('�ɻ���');
insert into wifi_sensitive_word values ('��̤��');
insert into wifi_sensitive_word values ('��ɽ��');
insert into wifi_sensitive_word values ('��Ӭˮ');
insert into wifi_sensitive_word values ('�ش���');
insert into wifi_sensitive_word values ('�ت�');
insert into wifi_sensitive_word values ('����ɩ');
insert into wifi_sensitive_word values ('��ɩ��');
insert into wifi_sensitive_word values ('��û�в�');
insert into wifi_sensitive_word values ('��ƨƨ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��Ǩ��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���˵�');
insert into wifi_sensitive_word values ('���˿�ͨ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����Ƭ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����ͼ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����С');
insert into wifi_sensitive_word values ('�ǹ���');
insert into wifi_sensitive_word values ('�͹���');
insert into wifi_sensitive_word values ('��̰��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���Ŵ���');
insert into wifi_sensitive_word values ('����ܽ��');
insert into wifi_sensitive_word values ('���ɼ���');
insert into wifi_sensitive_word values ('���۷�Ʊ');
insert into wifi_sensitive_word values ('���۾�');
insert into wifi_sensitive_word values ('��͸����');
insert into wifi_sensitive_word values ('��ˮ����');
insert into wifi_sensitive_word values ('���Ȱ�');
insert into wifi_sensitive_word values ('���Ȼ�');
insert into wifi_sensitive_word values ('��ͨ����');
insert into wifi_sensitive_word values ('����ˮ');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('����ҩ');
insert into wifi_sensitive_word values ('����ˎ');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('���ҵ֤');
insert into wifi_sensitive_word values ('�𰸰�');
insert into wifi_sensitive_word values ('���ṩ');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('��ɻ�ר');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���Ұ칫');
insert into wifi_sensitive_word values ('�󼦰�');
insert into wifi_sensitive_word values ('���u��');
insert into wifi_sensitive_word values ('���Ԫ');
insert into wifi_sensitive_word values ('���¶');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����̰��');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('���췢Ʊ');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����ѧ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���k');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('���_');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('����Ʊ');
insert into wifi_sensitive_word values ('����Ʊ��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��д��');
insert into wifi_sensitive_word values ('��д��');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('����Ҫ��');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('���Ĺ�');
insert into wifi_sensitive_word values ('����ή');
insert into wifi_sensitive_word values ('��ǰ�ɾ�');
insert into wifi_sensitive_word values ('���ܱ���');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('����ʧ');
insert into wifi_sensitive_word values ('���˵���');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��С��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�òƼ�');
insert into wifi_sensitive_word values ('��ͬ��');
insert into wifi_sensitive_word values ('�Ʋݺ�');
insert into wifi_sensitive_word values ('�ȼ��C');
insert into wifi_sensitive_word values ('��ƨ��');
insert into wifi_sensitive_word values ('�����ϰ�');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('��үүת');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('�ز�֮��');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�۹�֮��');
insert into wifi_sensitive_word values ('��ֽ��');
insert into wifi_sensitive_word values ('�����Ż�');
insert into wifi_sensitive_word values ('�繷');
insert into wifi_sensitive_word values ('�绰��');
insert into wifi_sensitive_word values ('�缦');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('���谴');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�|����');
insert into wifi_sensitive_word values ('��С�ڽ�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����С��');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����̨��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���Ž�');
insert into wifi_sensitive_word values ('����ǿӲ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��èè');
insert into wifi_sensitive_word values ('���_˹');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��԰��');
insert into wifi_sensitive_word values ('��԰��');
insert into wifi_sensitive_word values ('��԰ɱ');
insert into wifi_sensitive_word values ('��԰��');
insert into wifi_sensitive_word values ('���̴�');
insert into wifi_sensitive_word values ('���ƾ�');
insert into wifi_sensitive_word values ('��Ʊ��');
insert into wifi_sensitive_word values ('��Ʊ��');
insert into wifi_sensitive_word values ('��Ʊ��');
insert into wifi_sensitive_word values ('�lƱ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���׹�');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('���ַ�');
insert into wifi_sensitive_word values ('��άȨ');
insert into wifi_sensitive_word values ('��һ��');
insert into wifi_sensitive_word values ('��Ժ����');
insert into wifi_sensitive_word values ('����Ǭ');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('���״��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('����ҩˮ');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('����֤');
insert into wifi_sensitive_word values ('�̰���');
insert into wifi_sensitive_word values ('��˽��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ͬ��');
insert into wifi_sensitive_word values ('���޽���');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('���޵��A');
insert into wifi_sensitive_word values ('�����^��');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('��ӡ����');
insert into wifi_sensitive_word values ('��ӡ����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���Ÿ���');
insert into wifi_sensitive_word values ('�ĺ����');
insert into wifi_sensitive_word values ('���˿�');
insert into wifi_sensitive_word values ('�Ա���');
insert into wifi_sensitive_word values ('�ؽ�');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���빷');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('�۰Ĳ���');
insert into wifi_sensitive_word values ('���R��');
insert into wifi_sensitive_word values ('�����A');
insert into wifi_sensitive_word values ('�߾�����');
insert into wifi_sensitive_word values ('�߿���');
insert into wifi_sensitive_word values ('��ݺݺ');
insert into wifi_sensitive_word values ('���½�');
insert into wifi_sensitive_word values ('�泤��');
insert into wifi_sensitive_word values ('����״');
insert into wifi_sensitive_word values ('��֤����');
insert into wifi_sensitive_word values ('���࿼��');
insert into wifi_sensitive_word values ('������ƾ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�����̵�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('����С��');
insert into wifi_sensitive_word values ('����С��');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('��ƨר��');
insert into wifi_sensitive_word values ('�Ķ�һЩ');
insert into wifi_sensitive_word values ('�ԹԷ�');
insert into wifi_sensitive_word values ('���̹�');
insert into wifi_sensitive_word values ('��Ҳ����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ѧ����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��Բ����');
insert into wifi_sensitive_word values ('����Ͷע');
insert into wifi_sensitive_word values ('���Ҽ�');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('�����̵�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��һ������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ҩֱ��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ȦǮ');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('�͹���');
insert into wifi_sensitive_word values ('�͹���');
insert into wifi_sensitive_word values ('�͹���');
insert into wifi_sensitive_word values ('�ڻ�ҩ��');
insert into wifi_sensitive_word values ('��ɫ�ֲ�');
insert into wifi_sensitive_word values ('����͸��');
insert into wifi_sensitive_word values ('�tɫ��');
insert into wifi_sensitive_word values ('�����ڶ�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���\��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ҫ��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ͷ��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���ſ�');
insert into wifi_sensitive_word values ('��ѧɨä');
insert into wifi_sensitive_word values ('���Ϲ�');
insert into wifi_sensitive_word values ('���ᴵ��');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('����֤��');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('�ʹ�Ͷע');
insert into wifi_sensitive_word values ('�Ʊ�');
insert into wifi_sensitive_word values ('��Բ����');
insert into wifi_sensitive_word values ('���');
insert into wifi_sensitive_word values ('��Ҳ��');
insert into wifi_sensitive_word values ('����λ��');
insert into wifi_sensitive_word values ('���Ŷ�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('���帯');
insert into wifi_sensitive_word values ('����֭');
insert into wifi_sensitive_word values ('�D��֭');
insert into wifi_sensitive_word values ('�Ѿ�����');
insert into wifi_sensitive_word values ('��һ����');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('�׳���');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('��ְ����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����ը');
insert into wifi_sensitive_word values ('�����ڶ�');
insert into wifi_sensitive_word values ('��̫��');
insert into wifi_sensitive_word values ('��ϵ��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����ο');
insert into wifi_sensitive_word values ('��̰��');
insert into wifi_sensitive_word values ('���ҹ');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('���ְ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��Ҫ����');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('���챻');
insert into wifi_sensitive_word values ('����Ļ�');
insert into wifi_sensitive_word values ('����Ź��');
insert into wifi_sensitive_word values ('����˵��');
insert into wifi_sensitive_word values ('�����״�');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('����Ʒ');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������̳');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('�Ͱ���');
insert into wifi_sensitive_word values ('��Ҫɫ');
insert into wifi_sensitive_word values ('�ٹ���');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('��˵ȫ��');
insert into wifi_sensitive_word values ('��ʳ��');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('��Ʒ��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����ѡ');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('�_�a');
insert into wifi_sensitive_word values ('�_Ʊ');
insert into wifi_sensitive_word values ('��ɱ��');
insert into wifi_sensitive_word values ('���˶�');
insert into wifi_sensitive_word values ('��û�в�');
insert into wifi_sensitive_word values ('����¥');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('���󸶿�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ǰ��');
insert into wifi_sensitive_word values ('��ǰ��');
insert into wifi_sensitive_word values ('��ǰ��');
insert into wifi_sensitive_word values ('���豸');
insert into wifi_sensitive_word values ('���԰���');
insert into wifi_sensitive_word values ('���Ա�');
insert into wifi_sensitive_word values ('���Դ�');
insert into wifi_sensitive_word values ('���Ի���');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('���п���');
insert into wifi_sensitive_word values ('���д�');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('�˷���');
insert into wifi_sensitive_word values ('��ǧ��');
insert into wifi_sensitive_word values ('��͸��');
insert into wifi_sensitive_word values ('�պ��ŵ�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('����ý');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���ٰ�');
insert into wifi_sensitive_word values ('���Ѳ���');
insert into wifi_sensitive_word values ('����˵');
insert into wifi_sensitive_word values ('����ˮ��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ȫ����');
insert into wifi_sensitive_word values ('��Ѩ');
insert into wifi_sensitive_word values ('�ϻ���');
insert into wifi_sensitive_word values ('����Ů��');
insert into wifi_sensitive_word values ('��׼ȷ��');
insert into wifi_sensitive_word values ('����ƽ');
insert into wifi_sensitive_word values ('���־');
insert into wifi_sensitive_word values ('��ӽԻ');
insert into wifi_sensitive_word values ('�����֤');
insert into wifi_sensitive_word values ('����Ӱ��');
insert into wifi_sensitive_word values ('��֤��');
insert into wifi_sensitive_word values ('�����ʱ�');
insert into wifi_sensitive_word values ('��ƭ����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�M�');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('����Ƶ');
insert into wifi_sensitive_word values ('��ի��');
insert into wifi_sensitive_word values ('�˼�����');
insert into wifi_sensitive_word values ('�Ժð���');
insert into wifi_sensitive_word values ('��ǹ��');
insert into wifi_sensitive_word values ('�Ԙ�');
insert into wifi_sensitive_word values ('�C��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��Ѫ��');
insert into wifi_sensitive_word values ('���ϲ�');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('�����¼�');
insert into wifi_sensitive_word values ('¡��ָ');
insert into wifi_sensitive_word values ('½����');
insert into wifi_sensitive_word values ('½ͬ��');
insert into wifi_sensitive_word values ('�Ȱ�ͪ');
insert into wifi_sensitive_word values ('�Ҽ�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����С');
insert into wifi_sensitive_word values ('�y��');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�����Ӱ');
insert into wifi_sensitive_word values ('����ë');
insert into wifi_sensitive_word values ('����Ƭ');
insert into wifi_sensitive_word values ('�ֹ�');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('���Ĵ�');
insert into wifi_sensitive_word values ('��˹С��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ϼ׺');
insert into wifi_sensitive_word values ('���');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�齫͸');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('���혌');
insert into wifi_sensitive_word values ('����ˎ');
insert into wifi_sensitive_word values ('��ר��');
insert into wifi_sensitive_word values ('���ز���');
insert into wifi_sensitive_word values ('����Ʊ');
insert into wifi_sensitive_word values ('�����п�');
insert into wifi_sensitive_word values ('���Կ�');
insert into wifi_sensitive_word values ('����˿');
insert into wifi_sensitive_word values ('æ����');
insert into wifi_sensitive_word values ('è�۹���');
insert into wifi_sensitive_word values ('ëһ��');
insert into wifi_sensitive_word values ('ý�����');
insert into wifi_sensitive_word values ('ÿ��һ��');
insert into wifi_sensitive_word values ('�����ٸ�');
insert into wifi_sensitive_word values ('�ð�Ħ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�Ű�Ħ');
insert into wifi_sensitive_word values ('�ű���');
insert into wifi_sensitive_word values ('�T����');
insert into wifi_sensitive_word values ('å��ѵ');
insert into wifi_sensitive_word values ('�ɺ�ҩ');
insert into wifi_sensitive_word values ('�Ի���');
insert into wifi_sensitive_word values ('�Ի�ҩ');
insert into wifi_sensitive_word values ('�Ի�ˎ');
insert into wifi_sensitive_word values ('�Ի��');
insert into wifi_sensitive_word values ('�Ի�ҩ');
insert into wifi_sensitive_word values ('�Ի�ˎ');
insert into wifi_sensitive_word values ('�Ի���');
insert into wifi_sensitive_word values ('�Ի�ҩ');
insert into wifi_sensitive_word values ('�Ի�ˎ');
insert into wifi_sensitive_word values ('�Լ�ҩ');
insert into wifi_sensitive_word values ('����ˮ');
insert into wifi_sensitive_word values ('����ҩ');
insert into wifi_sensitive_word values ('��ˎ');
insert into wifi_sensitive_word values ('�ռ�ҩ');
insert into wifi_sensitive_word values ('��Ѩ');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�񴢺�');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����ӡ��');
insert into wifi_sensitive_word values ('ĦС��');
insert into wifi_sensitive_word values ('ĸ���');
insert into wifi_sensitive_word values ('ľ����');
insert into wifi_sensitive_word values ('Ļû�в�');
insert into wifi_sensitive_word values ('Ļǰ��');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('�ϳ���');
insert into wifi_sensitive_word values ('��Ѩ');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('����֮��');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('���θ�');
insert into wifi_sensitive_word values ('������֮��');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('Ũ��');
insert into wifi_sensitive_word values ('ŭ��־Ը');
insert into wifi_sensitive_word values ('Ů���˼Ҹ�');
insert into wifi_sensitive_word values ('Ů����');
insert into wifi_sensitive_word values ('Ů��ʦ');
insert into wifi_sensitive_word values ('Ů�˺͹�');
insert into wifi_sensitive_word values ('Ů��ְ��');
insert into wifi_sensitive_word values ('Ů����');
insert into wifi_sensitive_word values ('Ů��');
insert into wifi_sensitive_word values ('Ÿ֮��');
insert into wifi_sensitive_word values ('�ļ���ҩ');
insert into wifi_sensitive_word values ('�ļ���');
insert into wifi_sensitive_word values ('�Ʒ���');
insert into wifi_sensitive_word values ('�Ƽ���');
insert into wifi_sensitive_word values ('�ڵ�С��');
insert into wifi_sensitive_word values ('�㿼ǹ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('�ζ���');
insert into wifi_sensitive_word values ('�μ�');
insert into wifi_sensitive_word values ('ƽ�Ұ�');
insert into wifi_sensitive_word values ('ƽ�е���');
insert into wifi_sensitive_word values ('�Ͳ�����');
insert into wifi_sensitive_word values ('��ͨ��');
insert into wifi_sensitive_word values ('�ڻ���');
insert into wifi_sensitive_word values ('�漣�Ļ�');
insert into wifi_sensitive_word values ('����ɢ');
insert into wifi_sensitive_word values ('�ﵥ����');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('��ǹ');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('��ǹ');
insert into wifi_sensitive_word values ('�☌');
insert into wifi_sensitive_word values ('Ǧ��');
insert into wifi_sensitive_word values ('Ǯ���־�');
insert into wifi_sensitive_word values ('ǹ����');
insert into wifi_sensitive_word values ('ǹ�Ĳ�');
insert into wifi_sensitive_word values ('ǹ�ķ�');
insert into wifi_sensitive_word values ('ǹ�Ľ�');
insert into wifi_sensitive_word values ('ǹ����');
insert into wifi_sensitive_word values ('ǹ����');
insert into wifi_sensitive_word values ('ǹ��Ů��');
insert into wifi_sensitive_word values ('ǹ���ֳ�');
insert into wifi_sensitive_word values ('ǹģ');
insert into wifi_sensitive_word values ('ǹ�ֶ�');
insert into wifi_sensitive_word values ('ǹ����');
insert into wifi_sensitive_word values ('ǹ����');
insert into wifi_sensitive_word values ('ǹе��');
insert into wifi_sensitive_word values ('ǹ�ӵ�');
insert into wifi_sensitive_word values ('ǿȨ����');
insert into wifi_sensitive_word values ('ǿӲ����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�ⵯ��');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('�弃��');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����Ƶ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�軯��');
insert into wifi_sensitive_word values ('�軯��');
insert into wifi_sensitive_word values ('�뼯��');
insert into wifi_sensitive_word values ('��ʾ��');
insert into wifi_sensitive_word values ('��Ը');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('Ȣ����');
insert into wifi_sensitive_word values ('ȫ��֤');
insert into wifi_sensitive_word values ('Ⱥ�鱩');
insert into wifi_sensitive_word values ('Ⱥ�𿹱�');
insert into wifi_sensitive_word values ('Ⱥ������');
insert into wifi_sensitive_word values ('�ƹ�����');
insert into wifi_sensitive_word values ('�ǵĹ�');
insert into wifi_sensitive_word values ('��Ȩ��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('����Ǯ');
insert into wifi_sensitive_word values ('���ƾ�');
insert into wifi_sensitive_word values ('����˹��');
insert into wifi_sensitive_word values ('���ط�');
insert into wifi_sensitive_word values ('�ⶴ');
insert into wifi_sensitive_word values ('���');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�齻');
insert into wifi_sensitive_word values ('�����Ĺ�');
insert into wifi_sensitive_word values ('����ɧ');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('����Ƭ');
insert into wifi_sensitive_word values ('���뵹');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('ɧ��');
insert into wifi_sensitive_word values ('ɧ��');
insert into wifi_sensitive_word values ('ɧѨ');
insert into wifi_sensitive_word values ('ɧ��');
insert into wifi_sensitive_word values ('ɧ��');
insert into wifi_sensitive_word values ('ɨ��үү');
insert into wifi_sensitive_word values ('ɫ��Ӱ');
insert into wifi_sensitive_word values ('ɫ����');
insert into wifi_sensitive_word values ('ɫ��Ƶ');
insert into wifi_sensitive_word values ('ɫС˵');
insert into wifi_sensitive_word values ('ɱָ��');
insert into wifi_sensitive_word values ('ɽ���');
insert into wifi_sensitive_word values ('ɿ������');
insert into wifi_sensitive_word values ('ɿ��Ⱥ��');
insert into wifi_sensitive_word values ('���ż�');
insert into wifi_sensitive_word values ('�չ�����');
insert into wifi_sensitive_word values ('��ƿ��');
insert into wifi_sensitive_word values ('�عض�');
insert into wifi_sensitive_word values ('�ع���');
insert into wifi_sensitive_word values ('�ع���');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('���ӳ�Ϯ');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('���߼�');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����̤');
insert into wifi_sensitive_word values ('��Ф����');
insert into wifi_sensitive_word values ('ʥս��Ϣ');
insert into wifi_sensitive_word values ('ʢ������');
insert into wifi_sensitive_word values ('ʬ��');
insert into wifi_sensitive_word values ('ʧ��ˮ');
insert into wifi_sensitive_word values ('ʧ��ҩ');
insert into wifi_sensitive_word values ('ʨ����');
insert into wifi_sensitive_word values ('ʮ�˵�');
insert into wifi_sensitive_word values ('ʮ���');
insert into wifi_sensitive_word values ('ʮ���');
insert into wifi_sensitive_word values ('ʮ��Ԥ��');
insert into wifi_sensitive_word values ('ʮ���˲�');
insert into wifi_sensitive_word values ('ʮ�ߴ�Ļ');
insert into wifi_sensitive_word values ('ʵ��ҵ֤');
insert into wifi_sensitive_word values ('ʵ����');
insert into wifi_sensitive_word values ('ʵѧ����');
insert into wifi_sensitive_word values ('ʿ���¼�');
insert into wifi_sensitive_word values ('ʽ����');
insert into wifi_sensitive_word values ('�ӽ���');
insert into wifi_sensitive_word values ('�Ƕ�è');
insert into wifi_sensitive_word values ('�ֱ���');
insert into wifi_sensitive_word values ('�ִ�');
insert into wifi_sensitive_word values ('�ֹ�');
insert into wifi_sensitive_word values ('�ֻ���');
insert into wifi_sensitive_word values ('�ֻ���');
insert into wifi_sensitive_word values ('�ֻ���');
insert into wifi_sensitive_word values ('�ֻ�׷');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ľ��');
insert into wifi_sensitive_word values ('�֘�');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('�޽�');
insert into wifi_sensitive_word values ('�۲�ǹ');
insert into wifi_sensitive_word values ('�۴���');
insert into wifi_sensitive_word values ('�۵���');
insert into wifi_sensitive_word values ('�۵��ɵ�');
insert into wifi_sensitive_word values ('�۷���');
insert into wifi_sensitive_word values ('�۹���');
insert into wifi_sensitive_word values ('�ۻ�ͷ');
insert into wifi_sensitive_word values ('�ۻ�ҩ');
insert into wifi_sensitive_word values ('�ۼٱ�');
insert into wifi_sensitive_word values ('�۽���');
insert into wifi_sensitive_word values ('�۾���');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('���Ȱ�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ð��');
insert into wifi_sensitive_word values ('��ǹ֧');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��һԪӲ');
insert into wifi_sensitive_word values ('���ӵ�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�츾');
insert into wifi_sensitive_word values ('���ƾ�');
insert into wifi_sensitive_word values ('˫����');
insert into wifi_sensitive_word values ('˫��ƽ');
insert into wifi_sensitive_word values ('ˮ����');
insert into wifi_sensitive_word values ('˿��ʿ');
insert into wifi_sensitive_word values ('˿����');
insert into wifi_sensitive_word values ('˿�ౣ');
insert into wifi_sensitive_word values ('˿����');
insert into wifi_sensitive_word values ('˿����');
insert into wifi_sensitive_word values ('˿����');
insert into wifi_sensitive_word values ('˿����');
insert into wifi_sensitive_word values ('˿�㰴');
insert into wifi_sensitive_word values ('˾������');
insert into wifi_sensitive_word values ('˾����');
insert into wifi_sensitive_word values ('˽��д��');
insert into wifi_sensitive_word values ('�����ֲ�');
insert into wifi_sensitive_word values ('��Ҫ��ë');
insert into wifi_sensitive_word values ('�Ĳ���');
insert into wifi_sensitive_word values ('�Ĵ󳶸�');
insert into wifi_sensitive_word values ('��С��');
insert into wifi_sensitive_word values ('�ռ��ͼ�');
insert into wifi_sensitive_word values ('���ϼ���');
insert into wifi_sensitive_word values ('��Ů��');
insert into wifi_sensitive_word values ('�ٴ���');
insert into wifi_sensitive_word values ('��ȡ֤');
insert into wifi_sensitive_word values ('�����ǰ�');
insert into wifi_sensitive_word values ('̣��˰');
insert into wifi_sensitive_word values ('̫������');
insert into wifi_sensitive_word values ('̩����');
insert into wifi_sensitive_word values ('̩������');
insert into wifi_sensitive_word values ('̩����');
insert into wifi_sensitive_word values ('̰��Ҳ��');
insert into wifi_sensitive_word values ('̽�⹷');
insert into wifi_sensitive_word values ('�ι���');
insert into wifi_sensitive_word values ('��һ����');
insert into wifi_sensitive_word values ('�ع���');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��͸�Ӿ�');
insert into wifi_sensitive_word values ('�濼');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�쳯��');
insert into wifi_sensitive_word values ('���֮��');
insert into wifi_sensitive_word values ('���ƹ��');
insert into wifi_sensitive_word values ('��չ�');
insert into wifi_sensitive_word values ('����ɣ');
insert into wifi_sensitive_word values ('��ͣ��');
insert into wifi_sensitive_word values ('ͥ����');
insert into wifi_sensitive_word values ('ͥ��ֱ��');
insert into wifi_sensitive_word values ('ͨ���ܾ�');
insert into wifi_sensitive_word values ('͵���');
insert into wifi_sensitive_word values ('͵��̰');
insert into wifi_sensitive_word values ('͵����');
insert into wifi_sensitive_word values ('͵͵̰');
insert into wifi_sensitive_word values ('ͷ˫��');
insert into wifi_sensitive_word values ('͸�ӹ���');
insert into wifi_sensitive_word values ('͸�Ӿ�');
insert into wifi_sensitive_word values ('͸����');
insert into wifi_sensitive_word values ('͸����');
insert into wifi_sensitive_word values ('͸���۾�');
insert into wifi_sensitive_word values ('͸��ҩ');
insert into wifi_sensitive_word values ('͸����');
insert into wifi_sensitive_word values ('ͺӥ��');
insert into wifi_sensitive_word values ('ͻ�Ʒ���');
insert into wifi_sensitive_word values ('ͻ����·');
insert into wifi_sensitive_word values ('���Ͱ�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��˹��');
insert into wifi_sensitive_word values ('�ఴĦ');
insert into wifi_sensitive_word values ('��͸�Ӿ�');
insert into wifi_sensitive_word values ('��Χ����');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('����Կ��');
insert into wifi_sensitive_word values ('����ɧ��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���永');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('΢������');
insert into wifi_sensitive_word values ('Χ����');
insert into wifi_sensitive_word values ('Χ���Ϻ�');
insert into wifi_sensitive_word values ('ά��Ա');
insert into wifi_sensitive_word values ('άȨ��');
insert into wifi_sensitive_word values ('άȨ��');
insert into wifi_sensitive_word values ('άȨ̸');
insert into wifi_sensitive_word values ('ί����');
insert into wifi_sensitive_word values ('ν�ĺ�г');
insert into wifi_sensitive_word values ('�¼ұ�');
insert into wifi_sensitive_word values ('����˹��');
insert into wifi_sensitive_word values ('��Ӱ��');
insert into wifi_sensitive_word values ('�ؼҌ�');
insert into wifi_sensitive_word values ('���ӱ�');
insert into wifi_sensitive_word values ('���ٱ�');
insert into wifi_sensitive_word values ('��ƾ֤');
insert into wifi_sensitive_word values ('��ǿ');
insert into wifi_sensitive_word values ('����ë');
insert into wifi_sensitive_word values ('�ű�����');
insert into wifi_sensitive_word values ('�ŷ���');
insert into wifi_sensitive_word values ('�Ͱ�');
insert into wifi_sensitive_word values ('�ҵ�����');
insert into wifi_sensitive_word values ('�Ҹ�̨��');
insert into wifi_sensitive_word values ('��Ӭˮ');
insert into wifi_sensitive_word values ('�޳���¼');
insert into wifi_sensitive_word values ('����ר');
insert into wifi_sensitive_word values ('���׹�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ҹ��');
insert into wifi_sensitive_word values ('��ҹ��');
insert into wifi_sensitive_word values ('�侯��');
insert into wifi_sensitive_word values ('�侯Ź');
insert into wifi_sensitive_word values ('�侯����');
insert into wifi_sensitive_word values ('��Ա��');
insert into wifi_sensitive_word values ('��Ա����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������ȥ');
insert into wifi_sensitive_word values ('ϣ��');
insert into wifi_sensitive_word values ('ϰ��ƽ');
insert into wifi_sensitive_word values ('ϰ��ƽ');
insert into wifi_sensitive_word values ('ϯ����');
insert into wifi_sensitive_word values ('ϯ����ǰ');
insert into wifi_sensitive_word values ('ϯָ�Ż�');
insert into wifi_sensitive_word values ('ϴ����');
insert into wifi_sensitive_word values ('ϲ̰��');
insert into wifi_sensitive_word values ('���ҷ׷�');
insert into wifi_sensitive_word values ('�ִ����');
insert into wifi_sensitive_word values ('�ֽ�Ͷע');
insert into wifi_sensitive_word values ('��͸�Ӿ�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�ݺ���');
insert into wifi_sensitive_word values ('�ݺ���');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�����̳');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���һ��');
insert into wifi_sensitive_word values ('����ܲ�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('СѨ');
insert into wifi_sensitive_word values ('Уɧ��');
insert into wifi_sensitive_word values ('Э����');
insert into wifi_sensitive_word values ('д����');
insert into wifi_sensitive_word values ('й©����');
insert into wifi_sensitive_word values ('�½���');
insert into wifi_sensitive_word values ('�½���');
insert into wifi_sensitive_word values ('�½���');
insert into wifi_sensitive_word values ('�½�ƿ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�ŷ�ר��');
insert into wifi_sensitive_word values ('�Ž�����');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�г�����');
insert into wifi_sensitive_word values ('��͸�Ӿ�');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('�պ���');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�԰���');
insert into wifi_sensitive_word values ('�Ը���');
insert into wifi_sensitive_word values ('�Ը���');
insert into wifi_sensitive_word values ('���ƹ��');
insert into wifi_sensitive_word values ('����ϯ');
insert into wifi_sensitive_word values ('����Ԫ');
insert into wifi_sensitive_word values ('ѧɧ��');
insert into wifi_sensitive_word values ('ѧλ�C');
insert into wifi_sensitive_word values ('�W����');
insert into wifi_sensitive_word values ('Ѿ������');
insert into wifi_sensitive_word values ('�̸���');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�Ա��ͽ�');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('�没');
insert into wifi_sensitive_word values ('Ҧ����ȥ');
insert into wifi_sensitive_word values ('Ҫ��Ȩ');
insert into wifi_sensitive_word values ('Ҫ�侫��');
insert into wifi_sensitive_word values ('Ҫ����');
insert into wifi_sensitive_word values ('Ҫй��');
insert into wifi_sensitive_word values ('ҹ����');
insert into wifi_sensitive_word values ('Һ��ը');
insert into wifi_sensitive_word values ('һС���');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('���ע��');
insert into wifi_sensitive_word values ('���ܻ�');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('ꎴ�');
insert into wifi_sensitive_word values ('ꎵ�');
insert into wifi_sensitive_word values ('ꎑ�');
insert into wifi_sensitive_word values ('��ħ��');
insert into wifi_sensitive_word values ('����Ů');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('���}��');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('����ѧ');
insert into wifi_sensitive_word values ('��ˮ');
insert into wifi_sensitive_word values ('��Ѩ');
insert into wifi_sensitive_word values ('���ζ�');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('Ӧ�ӵ�');
insert into wifi_sensitive_word values ('Ӥ����');
insert into wifi_sensitive_word values ('ӽ��');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('�Ĺ���');
insert into wifi_sensitive_word values ('�ξ���');
insert into wifi_sensitive_word values ('���̲�һ');
insert into wifi_sensitive_word values ('��ת����');
insert into wifi_sensitive_word values ('�׳���');
insert into wifi_sensitive_word values ('����͸��');
insert into wifi_sensitive_word values ('����ͬ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�빷��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����Ů��');
insert into wifi_sensitive_word values ('ԩ���');
insert into wifi_sensitive_word values ('ԧ��ϴ');
insert into wifi_sensitive_word values ('԰�Ұ�');
insert into wifi_sensitive_word values ('԰������');
insert into wifi_sensitive_word values ('԰��ɱ');
insert into wifi_sensitive_word values ('԰��ɱ');
insert into wifi_sensitive_word values ('԰Ѫ��');
insert into wifi_sensitive_word values ('ԭһ������');
insert into wifi_sensitive_word values ('ԭװ��');
insert into wifi_sensitive_word values ('Ԭ�ڷ�');
insert into wifi_sensitive_word values ('�ε���');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�⵽��');
insert into wifi_sensitive_word values ('�⾯��');
insert into wifi_sensitive_word values ('���侯');
insert into wifi_sensitive_word values ('����¼');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('ը����');
insert into wifi_sensitive_word values ('ը��ң��');
insert into wifi_sensitive_word values ('ը����');
insert into wifi_sensitive_word values ('ը����');
insert into wifi_sensitive_word values ('ըҩ����');
insert into wifi_sensitive_word values ('ըҩ��');
insert into wifi_sensitive_word values ('ըҩ��');
insert into wifi_sensitive_word values ('�Ŵ���');
insert into wifi_sensitive_word values ('��ǹ��');
insert into wifi_sensitive_word values ('��Ԯ��');
insert into wifi_sensitive_word values ('������ί��');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��̰�');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('��̽�豸');
insert into wifi_sensitive_word values ('��Ǯ����');
insert into wifi_sensitive_word values ('��ǮͶע');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('��ʵ�ʸ�');
insert into wifi_sensitive_word values ('��һ����');
insert into wifi_sensitive_word values ('�������');
insert into wifi_sensitive_word values ('֤������');
insert into wifi_sensitive_word values ('֤����');
insert into wifi_sensitive_word values ('֤������');
insert into wifi_sensitive_word values ('֤������');
insert into wifi_sensitive_word values ('֤���');
insert into wifi_sensitive_word values ('֤һ����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�C��');
insert into wifi_sensitive_word values ('ֲ���');
insert into wifi_sensitive_word values ('ֳ����');
insert into wifi_sensitive_word values ('ָ�ƿ���');
insert into wifi_sensitive_word values ('ָ��Ĥ');
insert into wifi_sensitive_word values ('ָ����');
insert into wifi_sensitive_word values ('�����Ҹ�');
insert into wifi_sensitive_word values ('־��Ը��');
insert into wifi_sensitive_word values ('�Ʒ���');
insert into wifi_sensitive_word values ('����ǹ');
insert into wifi_sensitive_word values ('��֤����');
insert into wifi_sensitive_word values ('����֤��');
insert into wifi_sensitive_word values ('�еİ���');
insert into wifi_sensitive_word values ('�й���');
insert into wifi_sensitive_word values ('�й���ǿ');
insert into wifi_sensitive_word values ('�ֹ���Ա');
insert into wifi_sensitive_word values ('��ѧ��֤');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('�ݲҰ�');
insert into wifi_sensitive_word values ('�ݴ���̰');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('����߷�');
insert into wifi_sensitive_word values ('�罫��');
insert into wifi_sensitive_word values ('��ϯ��');
insert into wifi_sensitive_word values ('סӢ����');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('רҵ����');
insert into wifi_sensitive_word values ('רҵ��');
insert into wifi_sensitive_word values ('רҵ��д');
insert into wifi_sensitive_word values ('רҵ��');
insert into wifi_sensitive_word values ('ת������');
insert into wifi_sensitive_word values ('׬Ǯ����');
insert into wifi_sensitive_word values ('װ����');
insert into wifi_sensitive_word values ('װǹ��');
insert into wifi_sensitive_word values ('װ����');
insert into wifi_sensitive_word values ('�Ż�ʿ����');
insert into wifi_sensitive_word values ('���θ�');
insert into wifi_sensitive_word values ('�˲�����');
insert into wifi_sensitive_word values ('�ʸ��C');
insert into wifi_sensitive_word values ('����й');
insert into wifi_sensitive_word values ('������ҩ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�Լ���ǹ');
insert into wifi_sensitive_word values ('��ο��');
insert into wifi_sensitive_word values ('����ʥ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�ܻ���Ů');
insert into wifi_sensitive_word values ('�����淨');
insert into wifi_sensitive_word values ('��ţ����');
insert into wifi_sensitive_word values ('���ǹ');
insert into wifi_sensitive_word values ('����ҩ');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('�����');
insert into wifi_sensitive_word values ('��ת����');
insert into wifi_sensitive_word values ('������');
insert into wifi_sensitive_word values ('������֤');
insert into wifi_sensitive_word values ('��������');
insert into wifi_sensitive_word values ('����');
insert into wifi_sensitive_word values ('����С');
insert into wifi_sensitive_word values ('��ԭ�ӵ�');
insert into wifi_sensitive_word values ('��֤��');
insert into wifi_sensitive_word values ('ϵͳ����Ա');
commit;

/*==============================================================*/
/* Table: wifi_sysconfig                                        */
/*==============================================================*/
create table wifi_sysconfig
(
   config_para_code     varchar(100) not null comment '���ò�������',
   config_para_value    varchar(200) not null comment '���ò���ȡֵ',
   config_para_units    varchar(100) not null comment '���ò�����λ',
   config_para_desc     varchar(100) comment '���ò�������',
   config_para_ifactive tinyint not null default 1 comment '���ò����Ƿ���Ч',
   primary key (config_para_code)
);

alter table wifi_sysconfig comment 'ϵͳ���ñ�';

INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_MAX_RETRY_TIMES',
   '3',
   '��',
   '�����·�֪ͨ����������Դ���,�״η���Ҳ��������',
   1);
   
INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_PIN_TABLE_DONE',
   '����&1�����ˣ������ϵTAŶ~�ǵ����������ϴ���ҵĺ��գ����ͨ�����Ҿ����ܲ�����һ�������ˡ�',
   '����:�ǳ�(����)',
   '����ȷ�Ͽ���������������������յ���֪ͨ����',
   1);

INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_PIN_TABLE_DONE_OWNER_VERIFY_CODE',
   '�������������&1�����ˣ�������������֤��Ϊ��&2������֤��������̻���֤�������Ʊ��棬��Ȼ��û���Ż��ˣ������������Ż�һ��ʹ��Ŷ~',
   '����1:��������;����2:�̻���֤��',
   '����ȷ�Ͽ����������յ�����֤�����',
   1);
   
INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_OK',
   '��֤ͨ����&1�û����ȫ�����Ѽ���&2Ԫ�������������Ż�һ��ʹ�á�',
   '����1:��������;����2:ÿ��������',
   '�û�ǰ�����ѣ��̻�������֤�������֤���̻��༭���ţ�������+��֤��������1065xxxx��',
   1);
   
INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_USED',
   '��֤ʧ�ܡ�&1�û���&2��ʹ���Żݡ�',
   '����1:��������;����2:��������',
   '�û�ǰ�����ѣ��̻�������֤�������֤���̻��༭���ţ�������+��֤��������1065xxxx��',
   1);
   
INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_PIN_TABLE_CONSUME_VERIFY_ERROR_CHANNEL',
   '��֤ʧ�ܡ�&1�û������ڴ�����ʹ�á�',
   '����:��������',
   '�û�ǰ�����ѣ��̻�������֤�������֤���̻��༭���ţ�������+��֤��������1065xxxx��',
   1);

INSERT INTO wifi_sysconfig(
   config_para_code     ,
   config_para_value    ,
   config_para_units    ,
   config_para_desc     ,
   config_para_ifactive )
VALUES (
   'WIFI_SMS_NOTICE_PASSWORD',
   '���й��ƶ����wifi������&1��wifi������֤�룬�й��ƶ���',
   '����1:��֤��',
   '������֤���·�ģ��',
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
   '��֤��ȨURL',
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


