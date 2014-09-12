package com.order.dao;

import java.util.Map;

import com.order.mode.SendTimeDay;
import com.order.mode.ServiceTimeDay;
import com.order.mode.ShopHoliDay;


public interface ServiceTimeTrsDAO extends BaseTrsDAO<ServiceTimeDay, Map<String, Object>> {
	
	
	/** 刪除營業時間管理*/
	public Integer deleteServiceTimeDay(ServiceTimeDay serviceTimeDay);
	
	/** 新增營業時間管理*/
	public Integer createServiceTimeDay(ServiceTimeDay serviceTimeDay);
	
	/** 刪除外送時間管理*/
	public Integer deleteSendTimeDay(SendTimeDay sendTimeDay);
	
	/** 新增外送時間管理*/
	public Integer createSendTimeDay(SendTimeDay sendTimeDay);
	
	/** 刪除公休日管理*/
	public Integer deleteHoliDay(ShopHoliDay shopHoliDay);
	
	/** 新增公休日管理*/
	public Integer createHoliDay(ShopHoliDay shopHoliDay);
	
	
}