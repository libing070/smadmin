/**本周评论数量排行榜**/
SELECT 
  h.nickname '用户名',
  h.mobile '手机号',
  COUNT(h.reply_id) '评论量' 
FROM
  headline_reply h 
WHERE DATE_FORMAT(
    h.reply_date,
    '%Y-%m-%d %H:%i:%s'
  ) >= DATE_FORMAT('2014-10-17', '%Y-%m-%d %H:%i:%s') 
  AND DATE_FORMAT(
    h.reply_date,
    '%Y-%m-%d %H:%i:%s'
  ) < DATE_FORMAT('2014-10-24', '%Y-%m-%d %H:%i:%s') 
GROUP BY h.nickname,
  h.mobile 
ORDER BY COUNT(h.reply_id) DESC 
LIMIT 10 ;

