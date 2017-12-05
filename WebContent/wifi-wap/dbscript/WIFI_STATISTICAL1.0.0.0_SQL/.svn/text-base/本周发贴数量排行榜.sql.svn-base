/**本周发贴数量排行榜**/

SELECT 
  h.nickname '用户名',
  h.mobile '手机号',
	COUNT(h.headline_id)  '发贴量',
	SUM(h.reply_num+h.praise_num) '回复量'
  
FROM
  headline_content h 
WHERE DATE_FORMAT(
    h.create_date,
    '%Y-%m-%d %H:%i:%s'
  ) >= DATE_FORMAT('2014-10-24', '%Y-%m-%d %H:%i:%s') 
  AND DATE_FORMAT(
    h.create_date,
    '%Y-%m-%d %H:%i:%s'
  ) < DATE_FORMAT('2014-10-31', '%Y-%m-%d %H:%i:%s') 
GROUP BY h.nickname,
  h.mobile 
ORDER BY COUNT(h.headline_id) DESC 
LIMIT 10 ;





