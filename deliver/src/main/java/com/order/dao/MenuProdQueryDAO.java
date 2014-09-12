package com.order.dao;

import java.util.List;
import java.util.Map;

import com.order.mode.MenuProduct;
import com.order.mode.vo.MenuProductVO;


public interface MenuProdQueryDAO extends BaseQueryDAO<MenuProduct, Map<String, Object>> {
	
	
	/** 查詢商品管理(MENU_PRODUCT)所有資料，及查詢是否有訂單使用回傳count */
	public List<MenuProductVO> findMenuProductByShopId(String shopId) throws DAOObjectNotFoundException;
	
	
	/** 查詢單一商品管理(MENU_PRODUCT)資料 */
	public MenuProductVO findMenuProductByMenuProdId(String menuProdId) throws DAOObjectNotFoundException;
	
	/** 查詢menu商品選項呈現勾選的商品選項Item Type資料(MENU_ITEM_TYPE)，只回傳MENU_ITME_ID相加用,隔開字串  */
	public String findMenuItemTypeByKey(String menuProdId, String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢商品管理對應 SHOP_PO_DETL(店家訂單明細)查此商品是否訂購過，如訂購數量大於0，則此商品不可刪除 */
	public Integer findShopPoDetlByKey(String menuProdId, String shopId) throws DAOObjectNotFoundException;
	
}