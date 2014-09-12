package com.order.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.order.dao.DAOObjectNotFoundException;
import com.order.dao.OrderManagerQueryDAO;
import com.order.dao.OrderManagerTrsDAO;
import com.order.mode.CustomPoMast;
import com.order.mode.ShopPoDetl;
import com.order.service.OrderManagerService;



@Service
public class OrderManagerServiceImpl implements OrderManagerService {
	
	@Autowired
	private OrderManagerQueryDAO orderManagerQueryDAO;
	
	@Autowired
	private OrderManagerTrsDAO orderManagerTrsDAO;

//	@Resource
//	private DataSourceTransactionManager transactionManager;
//	private DefaultTransactionDefinition def;
	
	@Override
	public List<CustomPoMast> queryCustomPoMastByShopIdAndDay(String shopId, String queryDate, String queryDateEnd, PageBounds pageBounds) {
		
		
		return orderManagerQueryDAO.findCustomPoMastByShopIdAndDay(shopId, queryDate, queryDateEnd, pageBounds);
	}

	@Override
	public CustomPoMast queryCustomPoMastByOrderId(String orderId) throws DAOObjectNotFoundException {
		
		
		return orderManagerQueryDAO.findCustomPoMastByOrderId(orderId);
	}

	@Override
	public List<ShopPoDetl> queryShopPoDetlByKey(String orderId, String shopId) throws DAOObjectNotFoundException {
		
		
		return orderManagerQueryDAO.findShopPoDetlByKey(orderId, shopId);
	}
	
	
	


	
	
}
