package com.order.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.MenuProdOptionTrsDAO;
import com.order.mode.MenuProductItem;
import com.order.mode.MenuProductOptionValue;


@Repository("menuProdOptionTrsDAOTarget")
public class MenuProdOptionTrsDAOImpl extends BaseTrsDAOImpl<MenuProductItem, Map<String, Object>> implements MenuProdOptionTrsDAO {

	@Override
	public Integer createMenuProductItem(MenuProductItem menuProductItem) {
		
		return this.getSqlSession().insert(getNameSpace() + ".createMenuProductItem", menuProductItem);
	}

	@Override
	public Integer createMenuProductOptionValue(List<MenuProductOptionValue> menuProductOptionValueList) {
		
		return this.getSqlSession().insert(getNameSpace() + ".createMenuProductOptionValue", menuProductOptionValueList);
	}

	@Override
	public Integer updateMenuProductItem(MenuProductItem menuProductItem) {
		
		return this.getSqlSession().update(getNameSpace() + ".updateMenuProductItem", menuProductItem);
	}

	@Override
	public Integer deleteMenuProductOptionValue(String menuItmeId) {
		
		return this.getSqlSession().delete(getNameSpace() + ".deleteMenuProductOptionValue", menuItmeId);
	}

	@Override
	public Integer deleteMenuProductItem(String menuItmeId) {
		
		return this.getSqlSession().delete(getNameSpace() + ".deleteMenuProductItem", menuItmeId);
	}

	
	
	
	

	
}