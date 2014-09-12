package com.order.dao;

import java.util.List;
import java.util.Map;

import com.order.mode.ServiceScopeCircle;


public interface ServiceScopeQueryDAO extends BaseQueryDAO<ServiceScopeCircle, Map<String, Object>> {
	
	
	/** 查詢圓型服務範圍是否新增過*/
	public Integer findServiceScopeByShopIdChk(String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢圓型服務範圍資料*/
	public List<ServiceScopeCircle> findServiceScopeByShopId(String shopId) throws DAOObjectNotFoundException;
	
}