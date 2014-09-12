package com.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.DAOObjectNotFoundException;
import com.order.dao.MenuCategoryQueryDAO;
import com.order.mode.MenuCategory;
import com.order.mode.vo.MenuCategoryVO;


@Repository("menuCategoryQueryDAOTarget")
public class MenuCategoryQueryDAOImpl extends BaseQueryDAOImpl<MenuCategory, Map<String, Object>> implements MenuCategoryQueryDAO {

	
	@Override
	public List<MenuCategoryVO> findMenuCategoryByShopId(String shopId) throws DAOObjectNotFoundException {
	
		return this.getSqlSession().selectList(getNameSpace() + ".findMenuCategoryByShopId", shopId);
	}

	@Override
	public MenuCategory findMenuCategoryByMenuCatId(String menuCatId) throws DAOObjectNotFoundException {
		
		return this.getSqlSession().selectOne(getNameSpace() + ".findMenuCategoryByMenuCatId", menuCatId);
	}

	@Override
	public Integer findCountMenuProductByMenuCatId(String menuCatId, String shopId) throws DAOObjectNotFoundException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuCatId", menuCatId);
		map.put("shopId", shopId);
		return this.getSqlSession().selectOne(getNameSpace() + ".findCountMenuProductByMenuCatId", map);
	}

	@Override
	public List<MenuCategory> findAllByKey(String shopId) throws DAOObjectNotFoundException {
		
		return this.getSqlSession().selectList(getNameSpace() + ".findAllByKey", shopId);
	}

	

	

	
	

	

	
	
	

}