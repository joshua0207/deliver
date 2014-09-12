package com.order.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.order.mode.ServiceScopeCircle;



public interface ServiceScopeService {
	
	final Log log = LogFactory.getLog(ServiceScopeService.class);
	
	/** 查詢圓型服務範圍是否新增過*/
	public Boolean queryServiceScopeByShopIdChk(String shopId);
	
	/** 新增圓型服務範圍*/
	public Boolean createServiceScopeCircle(String shopId, String circleCenter, String circleTan, double distance, double maxLat, double maxLng, double minLat, double minLng);
	
	/** 修改圓型服務範圍*/
	public Boolean updateServiceScopeCircle(String shopId, String circleCenter, String circleTan, double distance, double maxLat, double maxLng, double minLat, double minLng);
	
	/** 查詢圓型服務範圍資料*/
	public ServiceScopeCircle queryServiceScopeByShopId(String shopId);
	
}
