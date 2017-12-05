
/**视频观看排行**/
SELECT vi.video_name '视频名称',COUNT(vp.mobile) '观看量' FROM video_info vi,video_play vp
WHERE vi.video_id = vp.video_id
AND DATE_FORMAT(
    vp.oper_date,
    '%Y-%m-%d %H:%i:%s'
  ) >= DATE_FORMAT('2014-10-12', '%Y-%m-%d %H:%i:%s') 
  AND DATE_FORMAT(
    vp.oper_date,
    '%Y-%m-%d %H:%i:%s'
  ) < DATE_FORMAT('2014-10-20', '%Y-%m-%d %H:%i:%s') 
GROUP BY vi.video_name
ORDER BY COUNT(vp.mobile) DESC 
LIMIT 10 ;