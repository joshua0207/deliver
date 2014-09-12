package com.order.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.order.dao.ServiceTimeQueryDAO;
import com.order.dao.ServiceTimeTrsDAO;
import com.order.mode.SendTimeDay;
import com.order.mode.ServiceTimeDay;
import com.order.mode.ShopHoliDay;
import com.order.service.ServiceTimeService;
import com.order.util.TimeMachine;



@Service
public class ServiceTimeServiceImpl implements ServiceTimeService {
	
	@Autowired
	private ServiceTimeQueryDAO serviceTimeQueryDAO;
	
	@Autowired
	private ServiceTimeTrsDAO serviceTimeTrsDAO;
	
	@Resource
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	
	@Override
	public Boolean deleteServiceTimeDay(ServiceTimeDay serviceTimeDay) {
		
		boolean isCorrect = false; 
	    
    	int count  = serviceTimeTrsDAO.deleteServiceTimeDay(serviceTimeDay);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}
	
	
	@Override
	public Boolean createServiceTimeDay(String shopId, String strWeek,
			String listTimeTwentyFourStart, String listTimeMinuteStart,
			String listTimeTwentyFourEnd, String listTimeMinuteEnd) {
		
		TimeMachine id = new TimeMachine();
		
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
	   
	    int count=0;
	    boolean isCorrect = false; 
	    try {
	    	
	    	ServiceTimeDay timeDay = null;
	    	String week [] = null;
	    	String timeStart [] = null;
	    	String timeMinuteStart [] = null;
	    	String timeEnd [] = null;
	    	String timeMinuteEnd [] = null;
	    	if(strWeek != null && !strWeek.equals("")){
	    		week = strWeek.split(";");
	    	}
	    	if(listTimeTwentyFourStart != null && !listTimeTwentyFourStart.equals("")){
	    		timeStart = listTimeTwentyFourStart.split(",");
	    	}
	    	if(listTimeMinuteStart != null && !listTimeMinuteStart.equals("")){
	    		timeMinuteStart = listTimeMinuteStart.split(",");
	    	}
	    	if(listTimeTwentyFourEnd != null && !listTimeTwentyFourEnd.equals("")){
	    		timeEnd = listTimeTwentyFourEnd.split(",");
	    	}
	    	if(listTimeMinuteEnd != null && !listTimeMinuteEnd.equals("")){
	    		timeMinuteEnd = listTimeMinuteEnd.split(",");
	    	}
			
	    	if(week != null && week.length >0 ){
	    		//先刪除原先營業時間
	    		timeDay = new ServiceTimeDay();
	    		timeDay.setShopId(shopId);
	    		serviceTimeTrsDAO.deleteServiceTimeDay(timeDay);
	    		timeDay = null;
	    		//新增
	    		for(int i=0;i<week.length;i++){
	    			timeDay = new ServiceTimeDay();
	    			timeDay.setServiceId(id.serial("timeday", i));
	    			timeDay.setShopId(shopId);
	    			timeDay.setServiceTimeBegin(timeStart[i]);
	    			timeDay.setServiceMinuteBegin(timeMinuteStart[i]);
	    			timeDay.setServiceTimeEnd(timeEnd[i]);
	    			timeDay.setServiceMinuteEnd(timeMinuteEnd[i]);
	    			timeDay.setServiceDay(week[i]);
	    			count = count + serviceTimeTrsDAO.createServiceTimeDay(timeDay);//寫入DB
	    			timeDay=null;
	    		}
	    	}else{
	    		//刪除原先營業時間
	    		timeDay = new ServiceTimeDay();
	    		timeDay.setShopId(shopId);
	    		count = serviceTimeTrsDAO.deleteServiceTimeDay(timeDay);
	    		timeDay = null;
	    	}
			
			if(count >0 ){
				isCorrect = true;
			}
		    
			transactionManager.commit(status);
	    }
	    catch (Exception ex) {
	    	log.info(ex.toString());
	    	transactionManager.rollback(status);
	    }
		
		return isCorrect;
	}


	@Override
	public List<ServiceTimeDay> queryServiceTimeDayByShopId(String shopId) {
		
		return serviceTimeQueryDAO.findServiceTimeDayByShopId(shopId);
	}


	@Override
	public Boolean deleteSendTimeDay(SendTimeDay sendTimeDay) {
		
		boolean isCorrect = false; 
	    
    	int count  = serviceTimeTrsDAO.deleteSendTimeDay(sendTimeDay);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}


	@Override
	public Boolean createSendTimeDay(String shopId, String strWeek,
			String listTimeTwentyFourStart, String listTimeMinuteStart,
			String listTimeTwentyFourEnd, String listTimeMinuteEnd) {
		
		TimeMachine id = new TimeMachine();
		
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
	   
	    int count=0;
	    boolean isCorrect = false; 
	    try {
	    	
	    	SendTimeDay timeDay = null;
	    	String week [] = null;
	    	String timeStart [] = null;
	    	String timeMinuteStart [] = null;
	    	String timeEnd [] = null;
	    	String timeMinuteEnd [] = null;
	    	if(strWeek != null && !strWeek.equals("")){
	    		week = strWeek.split(";");
	    	}
	    	if(listTimeTwentyFourStart != null && !listTimeTwentyFourStart.equals("")){
	    		timeStart = listTimeTwentyFourStart.split(",");
	    	}
	    	if(listTimeMinuteStart != null && !listTimeMinuteStart.equals("")){
	    		timeMinuteStart = listTimeMinuteStart.split(",");
	    	}
	    	if(listTimeTwentyFourEnd != null && !listTimeTwentyFourEnd.equals("")){
	    		timeEnd = listTimeTwentyFourEnd.split(",");
	    	}
	    	if(listTimeMinuteEnd != null && !listTimeMinuteEnd.equals("")){
	    		timeMinuteEnd = listTimeMinuteEnd.split(",");
	    	}
			
	    	if(week != null && week.length >0 ){
	    		//先刪除原先外送時間
	    		timeDay = new SendTimeDay();
	    		timeDay.setShopId(shopId);
	    		serviceTimeTrsDAO.deleteSendTimeDay(timeDay);
	    		timeDay = null;
	    		//新增
	    		for(int i=0;i<week.length;i++){
	    			timeDay = new SendTimeDay();
	    			timeDay.setSendId(id.serial("sendday", i));
	    			timeDay.setShopId(shopId);
	    			timeDay.setSendTimeBegin(timeStart[i]);
	    			timeDay.setSendMinuteBegin(timeMinuteStart[i]);
	    			timeDay.setSendTimeEnd(timeEnd[i]);
	    			timeDay.setSendMinuteEnd(timeMinuteEnd[i]);
	    			timeDay.setSendDay(week[i]);
	    			count = count + serviceTimeTrsDAO.createSendTimeDay(timeDay);//寫入DB
	    			timeDay=null;
	    		}
	    	}else{
	    		//刪除原先外送時間
	    		timeDay = new SendTimeDay();
	    		timeDay.setShopId(shopId);
	    		count = serviceTimeTrsDAO.deleteSendTimeDay(timeDay);
	    		timeDay = null;
	    	}
			
			if(count >0 ){
				isCorrect = true;
			}
		    
			transactionManager.commit(status);
	    }
	    catch (Exception ex) {
	    	log.info(ex.toString());
	    	transactionManager.rollback(status);
	    }
		
		return isCorrect;
	}


	@Override
	public List<SendTimeDay> querySendTimeDayByShopId(String shopId) {
		
		return serviceTimeQueryDAO.findSendTimeDayByShopId(shopId);
	}


	@Override
	public Boolean deleteHoliDay(ShopHoliDay shopHoliDay) {
		
		boolean isCorrect = false; 
	    
    	int count  = serviceTimeTrsDAO.deleteHoliDay(shopHoliDay);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}


	@Override
	public Boolean createHoliDay(String [] holidayData, String shopId) {
		
		TimeMachine id = new TimeMachine();
		
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
	   
	    int count=0;
	    boolean isCorrect = false; 
	    try {
	    	
	    	ShopHoliDay shopHoliDay =null;
	    	if(holidayData != null && holidayData.length >0 ){
	    		//先刪除原先公休日
	    		shopHoliDay = new ShopHoliDay();
	    		shopHoliDay.setShopId(shopId);
	    		serviceTimeTrsDAO.deleteHoliDay(shopHoliDay);
	    		shopHoliDay = null;
	    		//新增
	    		for(int i=0;i<holidayData.length;i++){
	    			shopHoliDay = new ShopHoliDay();
	    			shopHoliDay.setHoliId(id.serial("holiday", i));
		    		shopHoliDay.setShopId(shopId);
		    		shopHoliDay.setHoliDate(holidayData[i]);
		    		count = count + serviceTimeTrsDAO.createHoliDay(shopHoliDay);//寫入DB
		    		shopHoliDay = null;
	    		}
	    		
	    	}else{
	    		//先刪除原先公休日
	    		shopHoliDay = new ShopHoliDay();
	    		shopHoliDay.setShopId(shopId);
	    		count = serviceTimeTrsDAO.deleteHoliDay(shopHoliDay);
	    		shopHoliDay = null;
	    	}
			
			if(count >0 ){
				isCorrect = true;
			}
		    
			transactionManager.commit(status);
	    }
	    catch (Exception ex) {
	    	log.info(ex.toString());
	    	transactionManager.rollback(status);
	    }
		
		return isCorrect;
	}


	@Override
	public List<ShopHoliDay> queryHoliDayByShopId(String shopId) {
		
		
		return serviceTimeQueryDAO.findHoliDayByShopId(shopId);
	}

	
	

	
	
}
