package com.order.dao;

import java.util.List;
import java.util.Map;

import com.order.mode.MenuItemType;
import com.order.mode.MenuProduct;


public interface MenuProdTrsDAO extends BaseTrsDAO<MenuProduct, Map<String, Object>> {
	
	/** 新增商品管理 (MENU_PRODUCT)*/
	public Integer createMenuProduct(MenuProduct menuProduct);
	
	/** 新增商品管理 (MENU_ITEM_TYPE (menu商品選項呈現勾選的商品選項ItemType))*/
	public Integer createMenuItemType(List<MenuItemType> menuItemTypeList);
	
	/** 修改商品管理 (MENU_PRODUCT)- 刪除照片把檔案名稱清空*/
	public Integer updateMenuProductById(MenuProduct menuProduct);
	
	/** 修改商品管理 (MENU_PRODUCT)*/
	public Integer updateMenuProductByKey(MenuProduct menuProduct);
	
	/** 刪除商品管理 (MENU_ITEM_TYPE (menu商品選項呈現勾選的商品選項ItemType))*/
	public Integer deleteMenuItemTypeById(String menuProdId, String shopId);
	
	/** 刪除商品管理 (MENU_PRODUCT)*/
	public Integer deleteMenuProductByProdId(String menuProdId, String shopId);
	
}