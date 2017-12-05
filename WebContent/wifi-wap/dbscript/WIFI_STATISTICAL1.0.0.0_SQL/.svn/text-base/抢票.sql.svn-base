select hr.mobile,u.nickname,hr.reply_date from headline_content h,headline_reply hr,user_info u where u.mobile=hr.mobile and hr.headline_id=h.headline_id and DATE_FORMAT(
      hr.reply_date,
      '%Y-%m-%d %H:%i:%s'
    ) < DATE_FORMAT('2014-11-18', '%Y-%m-%d %H:%i:%s') and hr.headline_id=665 GROUP BY hr.mobile  ORDER BY hr.reply_date DESC