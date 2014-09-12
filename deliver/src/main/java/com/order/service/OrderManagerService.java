package com.order.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.order.dao.DAOObjectNotFoundException;
import com.order.mode.CustomPoMast;
import com.order.mode.ShopPoDetl;



public interface OrderManagerService {
	
	final Log log = LogFactory.getLog(OrderManagerService.class);
	
	
	/** 查詢店家的訂單資料(CUSTOM_PO_MAST) 
	 *  queryDate 	   查詢起始日期
	 *  queryDateEnd 查詢結束日期
	 */
	public List<CustomPoMast> queryCustomPoMastByShopIdAndDay(String shopId, String queryDate, String queryDateEnd, PageBounds pageBounds);
	
	
	/** 查詢店家的單筆訂單資料(CUSTOM_PO_MAST)*/
	public CustomPoMast queryCustomPoMastByOrderId(String orderId) throws DAOObjectNotFoundException;
	
	
	/** 查詢店家訂單明細資料(SHOP_PO_DETL)*/
	public List<ShopPoDetl> queryShopPoDetlByKey(String orderId,String shopId) throws DAOObjectNotFoundException;
}
