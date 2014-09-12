package com.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.DAOObjectNotFoundException;
import com.order.dao.ShopIntroPicQueryDAO;
import com.order.mode.ShopAllPicture;
import com.order.mode.ShopIntroducePic;


@Repository("shopIntroPicQueryDAOTarget")
public class ShopIntroPicQueryDAOImpl extends BaseQueryDAOImpl<ShopIntroducePic, Map<String, Object>> implements ShopIntroPicQueryDAO {

	@Override
	public ShopIntroducePic findByKey(Map<String, Object> k) throws DAOObjectNotFoundException {
		
		return super.findByKey(k);
	}

	@Override
	public Integer findByShopIsAdd(String shopId) throws DAOObjectNotFoundException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		return this.getSqlSession().selectOne(getNameSpace() + ".findByShopIsAdd", map);
	}

	@Override
	public List<ShopIntroducePic> findShopInfoByShopId(String shopId) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		return this.getSqlSession().selectList(getNameSpace() + ".findShopInfoByShopId", map);
	}

	@Override
	public List<ShopIntroducePic> findShopLogoByShopId(String shopId) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		return this.getSqlSession().selectList(getNameSpace() + ".findShopLogoByShopId", map);
	}

	@Override
	public List<ShopIntroducePic> findRectangularBigPicByShopId(String shopId) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		return this.getSqlSession().selectList(getNameSpace() + ".findRectangularBigPicByShopId", map);
	}

	@Override
	public List<ShopAllPicture> findShopAllPicByShopId(String shopId) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		return this.getSqlSession().selectList(getNameSpace() + ".findShopAllPicByShopId", map);
	}
	
	
	

}