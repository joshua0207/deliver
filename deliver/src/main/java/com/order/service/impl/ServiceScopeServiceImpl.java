package com.order.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.ServiceScopeQueryDAO;
import com.order.dao.ServiceScopeTrsDAO;
import com.order.mode.ServiceScopeCircle;
import com.order.service.ServiceScopeService;
import com.order.util.TimeMachine;



@Service
public class ServiceScopeServiceImpl implements ServiceScopeService {
	
	@Autowired
	private ServiceScopeQueryDAO serviceScopeQueryDAO;
	
	@Autowired
	private ServiceScopeTrsDAO serviceScopeTrsDAO;
	
//	@Resource
//	private DataSourceTransactionManager transactionManager;
//	private DefaultTransactionDefinition def;
	
	@Override
	public Boolean queryServiceScopeByShopIdChk(String shopId) {
		
		boolean isCorrect = false; 
	    
    	int count  = serviceScopeQueryDAO.findServiceScopeByShopIdChk(shopId);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}
	
	@Override
	public Boolean createServiceScopeCircle(String shopId, String circleCenter,
			String circleTan, double distance, double maxLat, double maxLng,
			double minLat, double minLng) {
		
		TimeMachine id = new TimeMachine();
		
		boolean isCorrect = false;
		
		ServiceScopeCircle	scopecircle = new ServiceScopeCircle();
		scopecircle.setCircleId(id.serial("circle", 0));
		scopecircle.setShopId(shopId);
		scopecircle.setCircleCenter(circleCenter);
		scopecircle.setCircleTan(circleTan);
        scopecircle.setDistance(distance);
        scopecircle.setMaxLat(maxLat);
        scopecircle.setMaxLng(maxLng);
        scopecircle.setMinLat(minLat);
        scopecircle.setMinLng(minLng);
        
        int count = serviceScopeTrsDAO.createServiceScopeCircle(scopecircle);
        if(count>0){
        	isCorrect = true;
        }
        
		return isCorrect;
	}
	
	
	@Override
	public Boolean updateServiceScopeCircle(String shopId, String circleCenter,
			String circleTan, double distance, double maxLat, double maxLng,
			double minLat, double minLng) {
		
		boolean isCorrect = false;
		
		ServiceScopeCircle	scopecircle = new ServiceScopeCircle();
		scopecircle.setShopId(shopId);
		scopecircle.setCircleCenter(circleCenter);
		scopecircle.setCircleTan(circleTan);
        scopecircle.setDistance(distance);
        scopecircle.setMaxLat(maxLat);
        scopecircle.setMaxLng(maxLng);
        scopecircle.setMinLat(minLat);
        scopecircle.setMinLng(minLng);
		
        int count = serviceScopeTrsDAO.updateServiceScopeCircle(scopecircle);
        if(count>0){
        	isCorrect = true;
        }
        
		return isCorrect;
	}

	
	@Override
	public ServiceScopeCircle queryServiceScopeByShopId(String shopId) {
		ServiceScopeCircle serviceScopeCircle = new ServiceScopeCircle();
		List<ServiceScopeCircle> serviceScopeCircleList = serviceScopeQueryDAO.findServiceScopeByShopId(shopId);
		if(serviceScopeCircleList != null && serviceScopeCircleList.size() >0){
			serviceScopeCircle = serviceScopeCircleList.get(0);
		}
		return serviceScopeCircle;
	}
	
	
	
	
	
	
}
