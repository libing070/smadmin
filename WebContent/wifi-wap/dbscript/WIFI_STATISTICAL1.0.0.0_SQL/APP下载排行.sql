

/**APP下载排行**/
SELECT ai.app_name '名称',COUNT(ac.mobile) '下载量' FROM app_info ai,app_click_detail ac
WHERE ai.app_id = ac.app_id
AND DATE_FORMAT(
    ac.oper_date,
    '%Y-%m-%d %H:%i:%s'
  ) >= DATE_FORMAT('2014-10-12', '%Y-%m-%d %H:%i:%s') 
  AND DATE_FORMAT(
    ac.oper_date,
    '%Y-%m-%d %H:%i:%s'
  ) < DATE_FORMAT('2014-10-20', '%Y-%m-%d %H:%i:%s') 
GROUP BY ai.app_name
ORDER BY COUNT(ac.mobile) DESC 
LIMIT 10 