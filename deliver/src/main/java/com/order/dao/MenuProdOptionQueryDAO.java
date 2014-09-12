package com.order.dao;

import java.util.List;
import java.util.Map;

import com.order.mode.MenuProductItem;
import com.order.mode.MenuProductOptionValue;
import com.order.mode.vo.MenuProductItemVO;


public interface MenuProdOptionQueryDAO extends BaseQueryDAO<MenuProductItem, Map<String, Object>> {
	
	
	/** 商品選項管理List頁面查詢資料 */
	public List<MenuProductItemVO> findMenuProdOptionAndValueByShopId(String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢商品選項管理(MENU_PRODUCT_ITEM)資料 */
	public MenuProductItem findMenuProductItemByMenuItmeId(String menuItmeId) throws DAOObjectNotFoundException;

	/** 查詢商品選項管理(MENU_PRODUCT_OPTION_VALUE)資料 */
	public List<MenuProductOptionValue> findMenuProductOptionValueByMenuItmeId(String menuItmeId) throws DAOObjectNotFoundException;
	
	/** 查詢MENU_ITEM_TYPE (menu商品選項呈現勾選的Item Type)下產品數量 */
	public Integer findCountMenuItemTypeByKey (String menuItmeId, String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢商品選項管理(MENU_PRODUCT_ITEM)所有資料 */
	public List<MenuProductItem> findAllByShopId(String shopId) throws DAOObjectNotFoundException;
	
}