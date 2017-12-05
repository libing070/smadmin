/**抢桌活动**/
/*日期	桌主名	手机号	商户名称	商户编号	状态*/
SELECT 
  20140930 '日期',
  u.nickname '桌主名',
  u.mobile '手机号',
  cp.consume_place_name '商户名称',
  cp.business_code '商户编号',
  a.act_status_name '状态' 
FROM
  pin_createtable_detail pd,
  user_info u,
  consume_place cp,
  act_status a,
  pin_createtable c 
WHERE u.mobile = pd.mobile 
  AND pd.is_owner_mobile = 1 
  AND cp.consume_place_id = c.consume_place_id 
  AND pd.mobile = c.owner_mobile 
  AND a.act_status_id = c.act_status_id 
  AND (
    (
      DATE_FORMAT(
        c.createtable_date,
        '%Y-%m-%d %H:%i:%s'
      ) >= DATE_FORMAT('20130827', '%Y-%m-%d %H:%i:%s') 
      AND DATE_FORMAT(
        c.createtable_date,
        '%Y-%m-%d %H:%i:%s'
      ) < DATE_FORMAT('20140828', '%Y-%m-%d %H:%i:%s')
    ) 
    OR (
      a.act_status_id = 8 
      AND DATE_FORMAT(
        c.mo_sms_time,
        '%Y-%m-%d %H:%i:%s'
      ) >= DATE_FORMAT('20130827', '%Y-%m-%d %H:%i:%s') 
      AND DATE_FORMAT(
        c.mo_sms_time,
        '%Y-%m-%d %H:%i:%s'
      ) < DATE_FORMAT('20140828', '%Y-%m-%d %H:%i:%s')
    ) 
    OR (
      a.act_status_id = 2 
      AND DATE_FORMAT(
        c.audit_time,
        '%Y-%m-%d %H:%i:%s'
      ) >= DATE_FORMAT('20130827', '%Y-%m-%d %H:%i:%s') 
      AND DATE_FORMAT(
        c.audit_time,
        '%Y-%m-%d %H:%i:%s'
      ) < DATE_FORMAT('20140828', '%Y-%m-%d %H:%i:%s')
    )
  ) ;

