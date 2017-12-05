/** 头条签到贴 **/
/**/
select DISTINCT nickname 昵称,mobile 手机号码 from headline_reply h where h.headline_id=290  and DATE_FORMAT(
    h.reply_date,
    '%Y-%m-%d %H:%i:%s'
  ) >= DATE_FORMAT('2014-10-09 00:00:00', '%Y-%m-%d %H:%i:%s') 
  AND DATE_FORMAT(
    h.reply_date,
    '%Y-%m-%d %H:%i:%s'
  ) < DATE_FORMAT('2014-10-09 16:00:00', '%Y-%m-%d %H:%i:%s') ;