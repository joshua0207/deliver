package com.order.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.VerifyTrsDAO;
import com.order.mode.ShopMemData;
import com.order.mode.vo.ShopBaseManagerVO;
import com.order.mode.vo.SmsPOVO;


@Repository("verifyTrsDAOTarget")
public class VerifyTrsDAOImpl extends BaseTrsDAOImpl<ShopBaseManagerVO, Map<String, Object>> implements VerifyTrsDAO {

	
	@Override
	public Boolean updateShopBaseAuth(ShopMemData shopMemData) {
		
		boolean resFlag = false;
		int count = this.getSqlSession().update(getNameSpace() + ".updateShopBaseAuth", shopMemData);
		if(count >0){
			resFlag=true;
		}
		
		return resFlag;
	}

	@Override
	public String updateSmsData(SmsPOVO smsPOVO) {
		
		String resFlag = "N";
		int count = this.getSqlSession().update(getNameSpace() + ".updateSmsData", smsPOVO);
		if(count >0){
			resFlag="Y";
		}
		return resFlag;
	}

	@Override
	public String updateCustomPOMastMsgInfoTime(String orderID, String finishTime) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderID", orderID);
		map.put("finishTime", finishTime);
		String resFlag = "N";
		int count = this.getSqlSession().update(getNameSpace() + ".updateCustomPOMastMsgInfoTime", map);
		if(count >0){
			resFlag="Y";
		}
		return resFlag;
	}

	@Override
	public String insertCustomPOSmsData(SmsPOVO smsPOVO) {
		
		String resFlag = "N";
		int count = this.getSqlSession().update(getNameSpace() + ".insertCustomPOSmsData", smsPOVO);
		if(count >0){
			resFlag="Y";
		}
		return resFlag;
	}

	@Override
	public Boolean updateShopBasePhoneAuth(ShopMemData shopMemData) {
		
		boolean resFlag = false;
		int count = this.getSqlSession().update(getNameSpace() + ".updateShopBasePhoneAuth", shopMemData);
		if(count >0){
			resFlag=true;
		}
		
		return resFlag;
	}

	
}