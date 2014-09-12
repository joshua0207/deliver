package com.order.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.LoginQueryDAO;
import com.order.dao.LoginTrsDAO;
import com.order.mode.ShopMemData;
import com.order.mode.vo.LoginAdminVO;
import com.order.service.LoginService;
import com.order.util.SecretUtil;



@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginQueryDAO loginQueryDAO;
	
	@Autowired
	private LoginTrsDAO loginTrsDAO;
	
	@Autowired
	private SecretUtil sert;

	@Override
	public LoginAdminVO queryFun(String shopId, String password) {
		
		String encryptPwd = "";
		
		try {
			encryptPwd = sert.encrypt(password);
			
		} catch (Exception e) {
			log.error(e);
		}
		return loginQueryDAO.findByShopidPassword(shopId, encryptPwd);
	}

	@Override
	public ShopMemData queryForgetPasswdByShopId(String shopId) {
		

		return loginQueryDAO.findForgetPasswdByShopId(shopId);
	}

//	@Override
//	public Car findByKey(String id) {
//		
//		return loginQueryDAO.findByKey(id);
//	}
//	
//	@Override
//	public Car findByKey1(String id) {
//		
//		return loginTrsDAO.findByKey(id);
//	}
	
	

	
	
}
