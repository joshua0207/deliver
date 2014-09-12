package com.order.dao;

import java.util.Map;

import com.order.mode.ShopMemData;
import com.order.mode.vo.ShopBaseManagerVO;


public interface WebSettingQueryDAO extends BaseQueryDAO<ShopMemData, Map<String, Object>> {
	
	
	/** 查詢外送功能設定是否新增過-店家主檔是否有此筆店家*/
	public Integer findSendSettingByShopIdChk(String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢外送功能設定資料 */
	public ShopBaseManagerVO findSendSettingByShopId(String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢訂單通知方式設定資料 */
	public ShopBaseManagerVO findSendOrderNoticeByShopId(String shopId) throws DAOObjectNotFoundException;
	
}