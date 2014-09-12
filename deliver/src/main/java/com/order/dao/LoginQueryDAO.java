package com.order.dao;

import java.util.Map;

import com.order.mode.ShopMemData;
import com.order.mode.vo.LoginAdminVO;


public interface LoginQueryDAO extends BaseQueryDAO<LoginAdminVO, Map<String, Object>> {
	
	
	/** 查詢登入資料*/
	public LoginAdminVO findByShopidPassword(String shopId, String password) throws DAOObjectNotFoundException;
	

	/** 忘記密碼查詢店家密碼、Email、手機 */
	public ShopMemData findForgetPasswdByShopId(String shopId) throws DAOObjectNotFoundException;
	
	
}