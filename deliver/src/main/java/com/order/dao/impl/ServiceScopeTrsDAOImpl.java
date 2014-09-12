package com.order.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.ServiceScopeTrsDAO;
import com.order.mode.ServiceScopeCircle;


@Repository("serviceScopeTrsDAOTarget")
public class ServiceScopeTrsDAOImpl extends BaseTrsDAOImpl<ServiceScopeCircle, Map<String, Object>> implements ServiceScopeTrsDAO {

	
	@Override
	public Integer createServiceScopeCircle(ServiceScopeCircle serviceScopeCircle) {
		
		return this.getSqlSession().insert(getNameSpace() + ".createServiceScopeCircle", serviceScopeCircle);
	}

	@Override
	public Integer updateServiceScopeCircle(ServiceScopeCircle serviceScopeCircle) {
		
		return this.getSqlSession().update(getNameSpace() + ".updateServiceScopeCircle", serviceScopeCircle);
	}

	

	
	

	
}