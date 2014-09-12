package com.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.DAOObjectNotFoundException;
import com.order.dao.MenuProdOptionQueryDAO;
import com.order.mode.MenuProductItem;
import com.order.mode.MenuProductOptionValue;
import com.order.mode.vo.MenuProductItemVO;


@Repository("menuProdOptionQueryDAOTarget")
public class MenuProdOptionQueryDAOImpl extends BaseQueryDAOImpl<MenuProductItem, Map<String, Object>> implements MenuProdOptionQueryDAO {

	@Override
	public List<MenuProductItemVO> findMenuProdOptionAndValueByShopId(String shopId) throws DAOObjectNotFoundException {
		
		
		return this.getSqlSession().selectList(getNameSpace() + ".findMenuProdOptionAndValueByShopId", shopId);
	}

	@Override
	public MenuProductItem findMenuProductItemByMenuItmeId(String menuItmeId) throws DAOObjectNotFoundException {
		
		
		return this.getSqlSession().selectOne(getNameSpace() + ".findMenuProductItemByMenuItmeId", menuItmeId);
	}

	@Override
	public List<MenuProductOptionValue> findMenuProductOptionValueByMenuItmeId(String menuItmeId) throws DAOObjectNotFoundException {
		
		
		return this.getSqlSession().selectList(getNameSpace() + ".findMenuProductOptionValueByMenuItmeId", menuItmeId);
	}

	@Override
	public Integer findCountMenuItemTypeByKey(String menuItmeId, String shopId) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuItmeId", menuItmeId);
		map.put("shopId", shopId);
		return this.getSqlSession().selectOne(getNameSpace() + ".findCountMenuItemTypeByKey", map);
	}

	@Override
	public List<MenuProductItem> findAllByShopId(String shopId) throws DAOObjectNotFoundException {
		
		return this.getSqlSession().selectList(getNameSpace() + ".findAllByShopId", shopId);
	}

	

	
	
	

}