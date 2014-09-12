package com.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.DAOObjectNotFoundException;
import com.order.dao.MenuProdQueryDAO;
import com.order.mode.MenuProduct;
import com.order.mode.vo.MenuProductVO;


@Repository("menuProdQueryDAOTarget")
public class MenuProdQueryDAOImpl extends BaseQueryDAOImpl<MenuProduct, Map<String, Object>> implements MenuProdQueryDAO {

	@Override
	public List<MenuProductVO> findMenuProductByShopId(String shopId) throws DAOObjectNotFoundException {
		
		return this.getSqlSession().selectList(getNameSpace() + ".findMenuProductByShopId", shopId);
	}

	@Override
	public MenuProductVO findMenuProductByMenuProdId(String menuProdId) throws DAOObjectNotFoundException {
		
		return this.getSqlSession().selectOne(getNameSpace() + ".findMenuProductByMenuProdId", menuProdId);
	}

	@Override
	public String findMenuItemTypeByKey(String menuProdId, String shopId) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuProdId", menuProdId);
		map.put("shopId", shopId);
		return this.getSqlSession().selectOne(getNameSpace() + ".findMenuItemTypeByKey", map);
	}

	@Override
	public Integer findShopPoDetlByKey(String menuProdId, String shopId) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuProdId", menuProdId);
		map.put("shopId", shopId);
		return this.getSqlSession().selectOne(getNameSpace() + ".findShopPoDetlByKey", map);
	}

	
	
	
	

}