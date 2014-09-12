package com.order.service.impl;


import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.order.dao.VerifyQueryDAO;
import com.order.dao.VerifyTrsDAO;
import com.order.mode.CustomPoSms;
import com.order.mode.ShopMemData;
import com.order.mode.vo.ShopBaseManagerVO;
import com.order.mode.vo.SmsPOVO;
import com.order.service.VerifyService;



@Service
public class VerifyServiceImpl implements VerifyService {
	
	@Autowired
	private VerifyQueryDAO verifyQueryDAO;
	
	@Autowired
	private VerifyTrsDAO verifyTrsDAO;
	
	@Resource
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	
	
	
	@Override
	public ShopBaseManagerVO queryParameterAuthByShopId(String shopId) {
		
		
		return verifyQueryDAO.findParameterAuthByShopId(shopId);
	}

	@Override
	public Boolean updateShopBaseAuth(ShopMemData shopMemData) {
		
		return verifyTrsDAO.updateShopBaseAuth(shopMemData);
	}

	@Override
	public String[] querySmsByMsgIdPhoneNum(String msgId, String phoneNum) {
		
		String[] rtnValue = new String[2];
		CustomPoSms customPoSms = verifyQueryDAO.findSmsByMsgIdPhoneNum(msgId, phoneNum);
		if(customPoSms != null){
			
			rtnValue[0] = "Y";
			rtnValue[1] = StringUtils.trimToEmpty(customPoSms.getRelationId());
		}
		return rtnValue;
	}

	@Override
	public String updateSmsData(SmsPOVO smsPOVO) {
		
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
		
	    String rtnValue="N";
		
		try {
			
			rtnValue = verifyTrsDAO.updateSmsData(smsPOVO);
			
			transactionManager.commit(status);
			
		} catch (TransactionException e) {
			log.info(e.toString());
	    	transactionManager.rollback(status);
		}
		
		return rtnValue;
	}

	@Override
	public String updateCustomPOMastMsgInfoTime(String orderID, String finishTime) {
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
		
	    String rtnValue="N";
		
		try {
			
			rtnValue = verifyTrsDAO.updateCustomPOMastMsgInfoTime(orderID, finishTime);
			
			transactionManager.commit(status);
			
		} catch (TransactionException e) {
			log.info(e.toString());
	    	transactionManager.rollback(status);
		}
		
		return rtnValue;
	}

	@Override
	public String insertCustomPOSmsData(SmsPOVO smsPOVO) {
		
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
		
	    String rtnValue="N";
		
		try {
			
			rtnValue = verifyTrsDAO.insertCustomPOSmsData(smsPOVO);
			
			transactionManager.commit(status);
			
		} catch (TransactionException e) {
			log.info(e.toString());
	    	transactionManager.rollback(status);
		}
		
		return rtnValue;
	}

	@Override
	public Boolean updateShopBasePhoneAuth(ShopMemData shopMemData) {
		
		return verifyTrsDAO.updateShopBasePhoneAuth(shopMemData);
	}

	
	
}
