package com.order.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.order.mode.ShopMemData;
import com.order.mode.vo.LoginAdminVO;


public interface LoginService {
	
	final Log log = LogFactory.getLog(LoginService.class);
	
	/** 查詢登入資料*/
	public LoginAdminVO queryFun(String shopId, String password);
	
	
	/** 忘記密碼查詢店家密碼、Email、手機 */
	public ShopMemData queryForgetPasswdByShopId(String shopId);
	
	
}
