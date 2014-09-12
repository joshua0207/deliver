package com.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.DAOObjectNotFoundException;
import com.order.dao.ServiceTimeQueryDAO;
import com.order.mode.SendTimeDay;
import com.order.mode.ServiceTimeDay;
import com.order.mode.ShopHoliDay;


@Repository("serviceTimeQueryDAOTarget")
public class ServiceTimeQueryDAOImpl extends BaseQueryDAOImpl<ServiceTimeDay, Map<String, Object>> implements ServiceTimeQueryDAO {

	
	@Override
	public List<ServiceTimeDay> findServiceTimeDayByShopId(String shopId) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		return this.getSqlSession().selectList(getNameSpace() + ".findServiceTimeDayByShopId", map);
	}

	@Override
	public List<SendTimeDay> findSendTimeDayByShopId(String shopId)	throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		return this.getSqlSession().selectList(getNameSpace() + ".findSendTimeDayByShopId", map);
	}

	@Override
	public List<ShopHoliDay> findHoliDayByShopId(String shopId) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		return this.getSqlSession().selectList(getNameSpace() + ".findHoliDayByShopId", map);
	}

	

	
	
	

}