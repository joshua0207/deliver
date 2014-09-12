package com.order.dao;

import java.util.Map;

import com.order.mode.MenuCategory;


public interface MenuCategoryTrsDAO extends BaseTrsDAO<MenuCategory, Map<String, Object>> {
	
	
	/** 新增商品分類管理*/
	public Integer createMenuCategory(MenuCategory menuCategory);
	
	/** 修改商品分類管理*/
	public Integer updateMenuCategory(MenuCategory menuCategory);
	
	/** 刪除商品分類管理*/
	public Integer deleteMenuCategory(String menuCatId);
	
	
}