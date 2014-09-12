package com.order.dao;

import java.util.List;
import java.util.Map;

import com.order.mode.MenuCategory;
import com.order.mode.vo.MenuCategoryVO;


public interface MenuCategoryQueryDAO extends BaseQueryDAO<MenuCategory, Map<String, Object>> {
	
	
	/** 查詢店家所有商品分類管理資料 */
	public List<MenuCategoryVO> findMenuCategoryByShopId(String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢單一商品分類管理資料 */
	public MenuCategory findMenuCategoryByMenuCatId (String menuCatId) throws DAOObjectNotFoundException;
	
	/** 查詢商品分類管理底下產品數量 */
	public Integer findCountMenuProductByMenuCatId (String menuCatId, String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢店家所有商品分類管理資料 */
	public List<MenuCategory> findAllByKey(String shopId) throws DAOObjectNotFoundException;
	
	
}