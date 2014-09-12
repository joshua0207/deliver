package com.order.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.WebSettingTrsDAO;
import com.order.mode.ShopMemData;


@Repository("webSettingTrsDAOTarget")
public class WebSettingTrsDAOImpl extends BaseTrsDAOImpl<ShopMemData, Map<String, Object>> implements WebSettingTrsDAO {

	@Override
	public Integer updateSendSetting(ShopMemData shopMemData) {
		
		return this.getSqlSession().update(getNameSpace() + ".updateSendSetting", shopMemData);
	}

	@Override
	public Integer updateSendOrderNotice(ShopMemData shopMemData) {
		
		return this.getSqlSession().update(getNameSpace() + ".updateSendOrderNotice", shopMemData);
	}

	
	
	
	

	
}