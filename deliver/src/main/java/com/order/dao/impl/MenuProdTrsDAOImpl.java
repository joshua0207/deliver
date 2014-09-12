package com.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.MenuProdTrsDAO;
import com.order.mode.MenuItemType;
import com.order.mode.MenuProduct;


@Repository("menuProdTrsDAOTarget")
public class MenuProdTrsDAOImpl extends BaseTrsDAOImpl<MenuProduct, Map<String, Object>> implements MenuProdTrsDAO {

	@Override
	public Integer createMenuProduct(MenuProduct menuProduct) {
		
		
		return this.getSqlSession().insert(getNameSpace() + ".createMenuProduct", menuProduct);
	}

	@Override
	public Integer createMenuItemType(List<MenuItemType> menuItemTypeList) {
		
		
		return this.getSqlSession().insert(getNameSpace() + ".createMenuItemType", menuItemTypeList);
	}

	@Override
	public Integer updateMenuProductById(MenuProduct menuProduct) {
		
		
		return this.getSqlSession().update(getNameSpace() + ".updateMenuProductById", menuProduct);
	}

	@Override
	public Integer updateMenuProductByKey(MenuProduct menuProduct) {
		
		return this.getSqlSession().update(getNameSpace() + ".updateMenuProductByKey", menuProduct);
	}

	@Override
	public Integer deleteMenuItemTypeById(String menuProdId, String shopId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuProdId", menuProdId);
		map.put("shopId", shopId);
		return this.getSqlSession().delete(getNameSpace() + ".deleteMenuItemTypeById", map);
	}

	@Override
	public Integer deleteMenuProductByProdId(String menuProdId, String shopId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuProdId", menuProdId);
		map.put("shopId", shopId);
		return this.getSqlSession().delete(getNameSpace() + ".deleteMenuProductByProdId", map);
	}

	
	
	
}