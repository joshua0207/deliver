package com.order.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.order.mode.SendTimeDay;
import com.order.mode.ServiceTimeDay;
import com.order.mode.ShopHoliDay;



public interface ServiceTimeService {
	
	final Log log = LogFactory.getLog(ServiceTimeService.class);
	
	/** 刪除營業時間管理*/
	public Boolean deleteServiceTimeDay(ServiceTimeDay serviceTimeDay);
	
	/** 新增營業時間管理*/
	public Boolean createServiceTimeDay(String shopId, String strWeek, String listTimeTwentyFourStart, String listTimeMinuteStart, String listTimeTwentyFourEnd, String listTimeMinuteEnd);

	/** 查詢營業時間管理*/
	public List<ServiceTimeDay> queryServiceTimeDayByShopId(String shopId);
	
	/** 刪除外送時間管理*/
	public Boolean deleteSendTimeDay(SendTimeDay sendTimeDay);
	
	/** 新增外送時間管理*/
	public Boolean createSendTimeDay(String shopId, String strWeek, String listTimeTwentyFourStart, String listTimeMinuteStart, String listTimeTwentyFourEnd, String listTimeMinuteEnd);
	
	/** 查詢外送時間管理*/
	public List<SendTimeDay> querySendTimeDayByShopId(String shopId);
	
	/** 刪除公休日管理*/
	public Boolean deleteHoliDay(ShopHoliDay shopHoliDay);
	
	/** 新增公休日管理*/
	public Boolean createHoliDay(String [] holidayData, String shopId);
	
	/** 查詢公休日管理*/
	public List<ShopHoliDay> queryHoliDayByShopId(String shopId);
	
}
