package com.order.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.MenuCategoryTrsDAO;
import com.order.mode.MenuCategory;


@Repository("menuCategoryTrsDAOTarget")
public class MenuCategoryTrsDAOImpl extends BaseTrsDAOImpl<MenuCategory, Map<String, Object>> implements MenuCategoryTrsDAO {

	@Override
	public Integer createMenuCategory(MenuCategory menuCategory) {
		
		return this.getSqlSession().insert(getNameSpace() + ".createMenuCategory", menuCategory);
	}

	@Override
	public Integer updateMenuCategory(MenuCategory menuCategory) {
		
		return this.getSqlSession().update(getNameSpace() + ".updateMenuCategory", menuCategory);
	}

	@Override
	public Integer deleteMenuCategory(String menuCatId) {
		
		return this.getSqlSession().delete(getNameSpace() + ".deleteMenuCategory", menuCatId);
	}

	
	
	

	
}