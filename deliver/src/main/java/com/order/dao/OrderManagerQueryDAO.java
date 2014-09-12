package com.order.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.order.mode.CustomPoMast;
import com.order.mode.ShopIntroducePic;
import com.order.mode.ShopPoDetl;


public interface OrderManagerQueryDAO extends BaseQueryDAO<ShopIntroducePic, Map<String, Object>> {
	
	/** 查詢店家的訂單資料(CUSTOM_PO_MAST) 
	 * queryDate 	   查詢起始日期
	 *  queryDateEnd 查詢結束日期
	 */
	public List<CustomPoMast> findCustomPoMastByShopIdAndDay(String shopId, String queryDate, String queryDateEnd, PageBounds pageBounds) throws DAOObjectNotFoundException;
	
	
	/** 查詢店家的單筆訂單資料(CUSTOM_PO_MAST)*/
	public CustomPoMast findCustomPoMastByOrderId(String orderId) throws DAOObjectNotFoundException;
	
	/** 查詢店家訂單明細資料(SHOP_PO_DETL)*/
	public List<ShopPoDetl> findShopPoDetlByKey(String orderId,String shopId) throws DAOObjectNotFoundException;
	
	
}