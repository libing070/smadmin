/**本周点赞排行榜**/
SELECT 
  h.orig_title '贴子标题',
  h.praise_num '点赞数量',
  h.nickname '发贴人' 
FROM
  headline_content h 
WHERE DATE_FORMAT(
    h.oper_date,
    '%Y-%m-%d %H:%i:%s'
  ) >= DATE_FORMAT('2014-10-17', '%Y-%m-%d %H:%i:%s') 
  AND DATE_FORMAT(
    h.oper_date,
    '%Y-%m-%d %H:%i:%s'
  ) < DATE_FORMAT('2014-10-24', '%Y-%m-%d %H:%i:%s') 
GROUP BY h.nickname,
  h.mobile 
ORDER BY h.praise_num DESC 
LIMIT 10 ;
