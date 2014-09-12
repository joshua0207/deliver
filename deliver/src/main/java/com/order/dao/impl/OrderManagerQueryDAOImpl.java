package com.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.order.dao.DAOObjectNotFoundException;
import com.order.dao.OrderManagerQueryDAO;
import com.order.mode.CustomPoMast;
import com.order.mode.ShopIntroducePic;
import com.order.mode.ShopPoDetl;


@Repository("orderManagerQueryDAOTarget")
public class OrderManagerQueryDAOImpl extends BaseQueryDAOImpl<ShopIntroducePic, Map<String, Object>> implements OrderManagerQueryDAO {

	@Override
	public List<CustomPoMast> findCustomPoMastByShopIdAndDay(String shopId, String queryDate, String queryDateEnd, PageBounds pageBounds) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		map.put("queryDate", queryDate+" 00:00:00");
		map.put("queryDateEnd", queryDateEnd+ " 23:59:59");
		return this.getSqlSession().selectList(getNameSpace() + ".findCustomPoMastByShopIdAndDay", map, pageBounds);
	}

	
	@Override
	public CustomPoMast findCustomPoMastByOrderId(String orderId) throws DAOObjectNotFoundException {
		
		
		return this.getSqlSession().selectOne(getNameSpace() + ".findCustomPoMastByOrderId", orderId);
	}


	@Override
	public List<ShopPoDetl> findShopPoDetlByKey(String orderId, String shopId) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("shopId", shopId);
		return this.getSqlSession().selectList(getNameSpace() + ".findShopPoDetlByKey", map);
	}

	
	
	
	

}