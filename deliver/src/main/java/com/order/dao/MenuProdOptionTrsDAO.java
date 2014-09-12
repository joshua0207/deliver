package com.order.dao;

import java.util.List;
import java.util.Map;

import com.order.mode.MenuProductItem;
import com.order.mode.MenuProductOptionValue;


public interface MenuProdOptionTrsDAO extends BaseTrsDAO<MenuProductItem, Map<String, Object>> {
	
	
	/** 新增商品選項管理 (MENU_PRODUCT_ITEM)*/
	public Integer createMenuProductItem(MenuProductItem menuProductItem);
	
	/** 新增商品選項管理 (MENU_PRODUCT_OPTION_VALUE)*/
	public Integer createMenuProductOptionValue(List<MenuProductOptionValue> menuProductOptionValueList);
	
	/** 修改商品選項管理 (MENU_PRODUCT_ITEM)*/
	public Integer updateMenuProductItem(MenuProductItem menuProductItem);
	
	/** 刪除商品選項管理 (MENU_PRODUCT_OPTION_VALUE)*/
	public Integer deleteMenuProductOptionValue(String menuItmeId);
	
	/** 刪除商品選項管理 (MENU_PRODUCT_ITEM)*/
	public Integer deleteMenuProductItem(String menuItmeId);
	
}