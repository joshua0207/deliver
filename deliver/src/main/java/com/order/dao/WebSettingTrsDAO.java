package com.order.dao;

import java.util.Map;

import com.order.mode.ShopMemData;


public interface WebSettingTrsDAO extends BaseTrsDAO<ShopMemData, Map<String, Object>> {
	
	
	/** 更新外送功能設定資料*/
	public Integer updateSendSetting(ShopMemData shopMemData);
	
	/** 更新訂單通知方式設定資料*/
	public Integer updateSendOrderNotice(ShopMemData shopMemData);
	
	
}