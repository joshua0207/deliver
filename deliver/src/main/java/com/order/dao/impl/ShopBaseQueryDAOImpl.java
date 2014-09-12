package com.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.DAOObjectNotFoundException;
import com.order.dao.ShopBaseQueryDAO;
import com.order.mode.MyShopTypeId;
import com.order.mode.ShopMemData;
import com.order.mode.ShopType;
import com.order.mode.vo.ShopBaseManagerVO;


@Repository("shopBaseQueryDAOTarget")
public class ShopBaseQueryDAOImpl extends BaseQueryDAOImpl<ShopMemData, Map<String, Object>> implements ShopBaseQueryDAO {

	@Override
	public ShopMemData findAllByIdPassword(String shopId, String password)
			throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		map.put("password", password);
		return this.getSqlSession().selectOne(getNameSpace() + ".findAllByIdPassword", map);
	}
	
	@Override
	public ShopBaseManagerVO findAllByIdPasswordForVO(String shopId, String password) throws DAOObjectNotFoundException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		map.put("password", password);
		return this.getSqlSession().selectOne(getNameSpace() + ".findAllByIdPasswordForVO", map);
	}

	@Override
	public List<ShopType> findAllShopType() throws DAOObjectNotFoundException {
		
		return this.getSqlSession().selectList(getNameSpace() + ".findAllShopType");
	}

	@Override
	public List<MyShopTypeId> findShopTypeByShopId(String shopId) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		return this.getSqlSession().selectList(getNameSpace() + ".findShopTypeByShopId", map);
	}

	@Override
	public ShopMemData chkShopLogin(String shopId, String password) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		map.put("password", password);
		return this.getSqlSession().selectOne(getNameSpace() + ".chkShopLogin", map);
	}

	@Override
	public Integer chkOldPassword(String shopId, String password) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		map.put("password", password);
		return this.getSqlSession().selectOne(getNameSpace() + ".chkOldPassword", map);
	}

	@Override
	public Integer checkShopId(String shopId) throws DAOObjectNotFoundException {
		

		return this.getSqlSession().selectOne(getNameSpace() + ".checkShopId", shopId);
	}

	

	

	
	
	

}