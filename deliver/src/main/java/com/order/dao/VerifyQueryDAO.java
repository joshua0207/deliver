package com.order.dao;

import java.util.Map;

import com.order.mode.CustomPoSms;
import com.order.mode.vo.ShopBaseManagerVO;


public interface VerifyQueryDAO extends BaseQueryDAO<ShopBaseManagerVO, Map<String, Object>> {
	
	
	/** 查詢店家認證SHOP_PARAMETER, EMAIL_AUTH */
	public ShopBaseManagerVO findParameterAuthByShopId(String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢該簡訊是否存在DB中*/
	public CustomPoSms findSmsByMsgIdPhoneNum(String msgId, String phoneNum) throws DAOObjectNotFoundException;
	
	
}