package com.order.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.ServiceTimeTrsDAO;
import com.order.mode.SendTimeDay;
import com.order.mode.ServiceTimeDay;
import com.order.mode.ShopHoliDay;


@Repository("serviceTimeTrsDAOTarget")
public class ServiceTimeTrsDAOImpl extends BaseTrsDAOImpl<ServiceTimeDay, Map<String, Object>> implements ServiceTimeTrsDAO {

	@Override
	public Integer deleteServiceTimeDay(ServiceTimeDay serviceTimeDay) {
	
		return this.getSqlSession().delete(getNameSpace() + ".deleteServiceTimeDay", serviceTimeDay);
	}
	
	@Override
	public Integer createServiceTimeDay(ServiceTimeDay serviceTimeDay) {
		
		return this.getSqlSession().insert(getNameSpace() + ".createServiceTimeDay", serviceTimeDay);
	}

	@Override
	public Integer deleteSendTimeDay(SendTimeDay sendTimeDay) {
		
		return this.getSqlSession().delete(getNameSpace() + ".deleteSendTimeDay", sendTimeDay);
	}

	@Override
	public Integer createSendTimeDay(SendTimeDay sendTimeDay) {
		
		return this.getSqlSession().insert(getNameSpace() + ".createSendTimeDay", sendTimeDay);
	}

	@Override
	public Integer deleteHoliDay(ShopHoliDay shopHoliDay) {
		
		return this.getSqlSession().delete(getNameSpace() + ".deleteHoliDay", shopHoliDay);
	}

	@Override
	public Integer createHoliDay(ShopHoliDay shopHoliDay) {
		
		return this.getSqlSession().insert(getNameSpace() + ".createHoliDay", shopHoliDay);
	}

	

	
	

	
}