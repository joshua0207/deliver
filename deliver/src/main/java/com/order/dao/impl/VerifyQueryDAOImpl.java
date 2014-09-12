package com.order.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.DAOObjectNotFoundException;
import com.order.dao.VerifyQueryDAO;
import com.order.mode.CustomPoSms;
import com.order.mode.vo.ShopBaseManagerVO;


@Repository("verifyQueryDAOTarget")
public class VerifyQueryDAOImpl extends BaseQueryDAOImpl<ShopBaseManagerVO, Map<String, Object>> implements VerifyQueryDAO {

	

	@Override
	public ShopBaseManagerVO findParameterAuthByShopId(String shopId) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		return this.getSqlSession().selectOne(getNameSpace() + ".findParameterAuthByShopId", map);
	}

	
	@Override
	public CustomPoSms findSmsByMsgIdPhoneNum(String msgId, String phoneNum) throws DAOObjectNotFoundException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msgId", msgId);
		map.put("phoneNum", phoneNum);
		return this.getSqlSession().selectOne(getNameSpace() + ".findSmsByMsgIdPhoneNum", map);
	}
	
	

}