package com.order.dao;

import java.util.Map;

import com.order.mode.Car;
import com.order.mode.vo.LoginAdminVO;


public interface LoginTrsDAO extends BaseTrsDAO<LoginAdminVO, Map<String, Object>> {
	
	
	public Car findByKey(String id) throws DAOObjectNotFoundException;
	
	
	
}