package com.order.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.ShopBaseTrsDAO;
import com.order.mode.ShopMemData;
import com.order.mode.vo.MyShopTypeIdVO;
import com.order.mode.vo.ShopBaseManagerVO;


@Repository("shopBaseTrsDAOTarget")
public class ShopBaseTrsDAOImpl extends BaseTrsDAOImpl<ShopMemData, Map<String, Object>> implements ShopBaseTrsDAO {

	
	@Override
	public Integer updateShopDataAll(ShopBaseManagerVO shopBaseManagerVO) {
		
		return this.getSqlSession().update(getNameSpace() + ".updateShopDataAll", shopBaseManagerVO);
	}

	@Override
	public Integer deleteMyShopTypeId(String shopId) {
		
		return this.getSqlSession().delete(getNameSpace() + ".deleteMyShopTypeId", shopId);
	}

	@Override
	public Integer insertMyShopTypeId(MyShopTypeIdVO myShopTypeIdVO) {
		
		return this.getSqlSession().insert(getNameSpace() + ".insertMyShopTypeId", myShopTypeIdVO);
	}

	@Override
	public Integer updatePassword(String shopId, String password, String newPassword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		map.put("password", password);
		map.put("newPassword", newPassword);
		return this.getSqlSession().update(getNameSpace() + ".updatePassword", map);
	}

	@Override
	public Integer createShopDataAll(ShopBaseManagerVO shopBaseManagerVO) {
		
		
		return this.getSqlSession().insert(getNameSpace() + ".createShopDataAll", shopBaseManagerVO);
	}

	

	
}