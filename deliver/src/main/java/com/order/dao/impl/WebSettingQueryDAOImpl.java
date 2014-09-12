package com.order.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.DAOObjectNotFoundException;
import com.order.dao.WebSettingQueryDAO;
import com.order.mode.ShopMemData;
import com.order.mode.vo.ShopBaseManagerVO;


@Repository("webSettingQueryDAOTarget")
public class WebSettingQueryDAOImpl extends BaseQueryDAOImpl<ShopMemData, Map<String, Object>> implements WebSettingQueryDAO {

	@Override
	public Integer findSendSettingByShopIdChk(String shopId) throws DAOObjectNotFoundException {
		
		return this.getSqlSession().selectOne(getNameSpace() + ".findSendSettingByShopIdChk", shopId);
	}

	@Override
	public ShopBaseManagerVO findSendSettingByShopId(String shopId) throws DAOObjectNotFoundException {
		
		return this.getSqlSession().selectOne(getNameSpace() + ".findSendSettingByShopId", shopId);
	}

	@Override
	public ShopBaseManagerVO findSendOrderNoticeByShopId(String shopId) throws DAOObjectNotFoundException {
		
		return this.getSqlSession().selectOne(getNameSpace() + ".findSendOrderNoticeByShopId", shopId);
	}

	

	
	

	

	
	
	

}