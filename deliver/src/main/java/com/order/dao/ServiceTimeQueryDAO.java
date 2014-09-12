package com.order.dao;

import java.util.List;
import java.util.Map;

import com.order.mode.SendTimeDay;
import com.order.mode.ServiceTimeDay;
import com.order.mode.ShopHoliDay;


public interface ServiceTimeQueryDAO extends BaseQueryDAO<ServiceTimeDay, Map<String, Object>> {
	
	
	/** 查詢營業時間管理*/
	public List<ServiceTimeDay> findServiceTimeDayByShopId(String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢外送時間管理*/
	public List<SendTimeDay> findSendTimeDayByShopId(String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢公休日管理*/
	public List<ShopHoliDay> findHoliDayByShopId(String shopId) throws DAOObjectNotFoundException;
	
}