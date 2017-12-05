/** 头条日常 **/
/*1, '灌水';2, '新闻';3,'八卦';4,'招聘'; 5,'求助'; 6,'公告'*/
SELECT 
  t.id '栏目',
  t.hs '发贴量',
  t.rs '评论量',
 t.ps '点赞量'
FROM
  (SELECT 
    COUNT(h.headline_id) hs,
    SUM(h.reply_num) rs,
		SUM(h.praise_num) ps,
    h.content_type_id id 
  FROM
    headline_content h 
  WHERE DATE_FORMAT(
      h.create_date,
      '%Y-%m-%d %H:%i:%s'
    ) >= DATE_FORMAT('2014-11-04', '%Y-%m-%d %H:%i:%s') 
    AND DATE_FORMAT(
      h.create_date,
      '%Y-%m-%d %H:%i:%s'
    ) < DATE_FORMAT('2014-11-05', '%Y-%m-%d %H:%i:%s') and h.content_type_id!=6
  GROUP BY h.content_type_id) t ;

