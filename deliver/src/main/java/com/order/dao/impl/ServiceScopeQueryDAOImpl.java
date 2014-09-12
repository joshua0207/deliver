package com.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.DAOObjectNotFoundException;
import com.order.dao.ServiceScopeQueryDAO;
import com.order.mode.ServiceScopeCircle;


@Repository("serviceScopeQueryDAOTarget")
public class ServiceScopeQueryDAOImpl extends BaseQueryDAOImpl<ServiceScopeCircle, Map<String, Object>> implements ServiceScopeQueryDAO {

	@Override
	public Integer findServiceScopeByShopIdChk(String shopId) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		return this.getSqlSession().selectOne(getNameSpace() + ".findServiceScopeByShopIdChk", map);
	}

	@Override
	public List<ServiceScopeCircle> findServiceScopeByShopId(String shopId) throws DAOObjectNotFoundException {
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		return this.getSqlSession().selectList(getNameSpace() + ".findServiceScopeByShopId", map);
	}

	
	

	

	
	
	

}