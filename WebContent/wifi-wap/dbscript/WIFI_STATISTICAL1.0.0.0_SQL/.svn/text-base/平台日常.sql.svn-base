/**平台日常**/
select 20140827 '日期', sum(r.newusers) '新用户', sum(r.activeusers) '活跃用户'
  from (select count(distinct u.mobile) newusers, 0 activeusers
          from user_info u
         where date_format(u.sure_regDate, '%Y-%m-%d %H:%i:%s') >=
               date_format('2014-11-02', '%Y-%m-%d %H:%i:%s')
           and date_format(u.sure_regDate, '%Y-%m-%d %H:%i:%s') <
               date_format('2014-11-03', '%Y-%m-%d %H:%i:%s')   /*在当日新增用户*/  
         union all      
         select  0 newusers, count(distinct u.mobile) activeusers
          from user_info u
         where date_format(u.reg_date, '%Y-%m-%d %H:%i:%s') >=
               date_format('2014-11-02', '%Y-%m-%d %H:%i:%s')
           and date_format(u.reg_date, '%Y-%m-%d %H:%i:%s') <
               date_format('2014-11-03', '%Y-%m-%d %H:%i:%s')      /*在当日更新资料用户*/
        union all
        select 0 newusers, count(distinct t.mobile) activeusers
          from (select distinct h.mobile
                  from user_info u, headline_reply h
                 where u.mobile = h.mobile                   
                   and date_format(h.reply_date, '%Y-%m-%d %H:%i:%s') >=
                       date_format('2014-11-02', '%Y-%m-%d %H:%i:%s')
                   and date_format(h.reply_date, '%Y-%m-%d %H:%i:%s') <
                       date_format('2014-11-03', '%Y-%m-%d %H:%i:%s')/**在当日发表评论或赞的用户*/
                
                union all
                
                select distinct p.mobile
                  from user_info u, pin_createtable_detail p
                 where u.mobile = p.mobile
                   and date_format(p.join_date, '%Y-%m-%d %H:%i:%s') >=
                       date_format('2014-11-02', '%Y-%m-%d %H:%i:%s')
                   and date_format(p.join_date, '%Y-%m-%d %H:%i:%s') <
                       date_format('2014-11-03', '%Y-%m-%d %H:%i:%s')/**在当日参加活动的用户**/
							union all
                
                select distinct u.mobile
                  from user_info u, headline_content hc
                 where u.mobile = hc.mobile
                   and date_format(hc.create_date, '%Y-%m-%d %H:%i:%s') >=
                       date_format('2014-11-02', '%Y-%m-%d %H:%i:%s')
                   and date_format(hc.create_date, '%Y-%m-%d %H:%i:%s') <
                       date_format('2014-11-03', '%Y-%m-%d %H:%i:%s')/**在当日发帖的用户**/
) t 
) r
