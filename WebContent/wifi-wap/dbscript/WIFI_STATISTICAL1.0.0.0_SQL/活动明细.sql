

/**活动明细
** 活动角色，1是抢桌，0是拼桌

** select pc.act_desc '活动名称',p.join_date '日期', u.nickname '用户名',u.mobile '手机号',p.is_owner_mobile '活动角色' 
** from pin_createtable_detail p,user_info u,pin_createtable pc  
** where p.mobile = u.mobile  and p.flash_sale_id=pc.flash_sale_id 
** and date_format(p.join_date, '%Y-%m-%d %H:%i:%s') >=
                       date_format('2014-10-20', '%Y-%m-%d %H:%i:%s') and date_format(p.join_date, '%Y-%m-%d %H:%i:%s') <
                       date_format('2014-10-28', '%Y-%m-%d %H:%i:%s') order by   p.join_date desc**/
                       
  /**活动明细
** **/
		select  p.flash_sale_id '编号',p.act_desc '活动名称',dt.mobile '参与人手机号码', ui.nickname '昵称',6-p.free_seat '参与人数',  a.act_status_name '当前状态',dt.join_date '加入时间',dt.is_owner_mobile '桌主'
		  from user_info ui, pin_createtable_detail dt,pin_createtable p,act_status a
		 where ui.mobile = dt.mobile and p.flash_sale_id = dt.flash_sale_id and a.act_status_id=p.act_status_id			
			order by p.flash_sale_id                     
                       