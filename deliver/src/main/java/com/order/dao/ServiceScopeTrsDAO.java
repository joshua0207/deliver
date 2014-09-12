package com.order.dao;

import java.util.Map;

import com.order.mode.ServiceScopeCircle;


public interface ServiceScopeTrsDAO extends BaseTrsDAO<ServiceScopeCircle, Map<String, Object>> {
	
	/** 新增圓型服務範圍*/
	public Integer createServiceScopeCircle(ServiceScopeCircle serviceScopeCircle);
	
	/** 修改圓型服務範圍*/
	public Integer updateServiceScopeCircle(ServiceScopeCircle serviceScopeCircle);
	
	
}