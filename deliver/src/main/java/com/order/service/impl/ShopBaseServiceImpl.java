package com.order.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.order.dao.ServiceScopeTrsDAO;
import com.order.dao.ShopBaseQueryDAO;
import com.order.dao.ShopBaseTrsDAO;
import com.order.mode.MyShopTypeId;
import com.order.mode.ServiceScopeCircle;
import com.order.mode.ShopMemData;
import com.order.mode.ShopType;
import com.order.mode.vo.MyShopTypeIdVO;
import com.order.mode.vo.ShopBaseManagerVO;
import com.order.service.ShopBaseService;
import com.order.util.SecretUtil;



@Service
public class ShopBaseServiceImpl implements ShopBaseService {
	
	@Autowired
	private ShopBaseQueryDAO shopBaseQueryDAO;
	
	@Autowired
	private ShopBaseTrsDAO shopBaseTrsDAO;
	
	@Autowired
	private ServiceScopeTrsDAO serviceScopeTrsDAO;
	
	@Resource
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	
	@Autowired
	private SecretUtil sert;
	
	@Override
	public ShopMemData queryShopDataAll(String shopId, String password) {
		
		return shopBaseQueryDAO.findAllByIdPassword(shopId, password);
	}
	
	@Override
	public ShopBaseManagerVO queryShopDataAllForVO(String shopId, String password) {
		
		return shopBaseQueryDAO.findAllByIdPasswordForVO(shopId, password);
	}

	@Override
	public List<ShopType> queryShopTypeAll() {
		
		return shopBaseQueryDAO.findAllShopType();
	}

	@Override
	public List<MyShopTypeId> queryShopType(String shopId) {
		
		return shopBaseQueryDAO.findShopTypeByShopId(shopId);
	}

	@Override
	public Boolean updateShopDataAll(ShopBaseManagerVO shopBaseManagerVO, ArrayList<MyShopTypeIdVO> myShopTypeIdVoList) {
		
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
	   
	    boolean isCorrect = false; 
	    try {
			//更新店家基本主檔
			
			int count = shopBaseTrsDAO.updateShopDataAll(shopBaseManagerVO);
			
			if( myShopTypeIdVoList.size() > 0 ){
				
				//先刪除店家所擁有的店家類別
				shopBaseTrsDAO.deleteMyShopTypeId(shopBaseManagerVO.getShopId());
				
				MyShopTypeIdVO myShopTypeIdVO = null;
				
				for(int i = 0; i < myShopTypeIdVoList.size();i++){
					
					//新增店家所擁有的店家類別
					myShopTypeIdVO = (MyShopTypeIdVO)myShopTypeIdVoList.get(i);
					shopBaseTrsDAO.insertMyShopTypeId(myShopTypeIdVO);
					
				}				
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
	public ShopMemData chkShopLogin(String shopId, String password)  {
		
		String encryptPwd = "";
		
		try {
			encryptPwd = sert.encrypt(password);
			
		} catch (Exception e) {
			log.error(e);
		}
		
		return shopBaseQueryDAO.chkShopLogin(shopId, encryptPwd);
	}

	
	
	@Override
	public Integer chkOldPassword(String shopId, String password) {
		
		String encryptPwd = "";
		
		try {
			
			encryptPwd = sert.encrypt(password);
			
		} catch (Exception e) {
			log.error(e);
		}
		
		return shopBaseQueryDAO.chkOldPassword(shopId, encryptPwd);
	}

	@Override
	public Boolean updatePassword(String shopId, String password, String newPassword) {
		
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
	    
	    boolean isCorrect = false; 
	    
	    try {
	    	
	    	int count  = shopBaseTrsDAO.updatePassword(shopId, sert.encrypt(password), sert.encrypt(newPassword));
	    	if(count >0 ){
				isCorrect = true;
			}
	    	
	    	transactionManager.commit(status);
	    	
	    }catch (Exception ex) {
	    	log.info(ex.toString());
	    	transactionManager.rollback(status);
	    }
	    
		return isCorrect;
	}

	@Override
	public Boolean checkShopId(String shopId) {
		
		boolean isCorrect = false; 
	    
    	int count  = shopBaseQueryDAO.checkShopId(shopId);
    	
    	if(count >0 ){
			isCorrect = true;
		}
    	
		return isCorrect;
	}

	@Override
	public Boolean createShopDataAll(ShopBaseManagerVO shopBaseManagerVO, ArrayList<MyShopTypeIdVO> myShopTypeIdVoList,
		   ServiceScopeCircle scopeCircle) {
		
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
	   
	    boolean isCorrect = false; 
	    int count = 0 ;
	    int count2 = 0;
	    int count3 = 0;
	    try {
	    	
			//新增店家基本主檔
			count = shopBaseTrsDAO.createShopDataAll(shopBaseManagerVO);
			
			if(count >0 ){
				if( myShopTypeIdVoList.size() > 0 ){
					
					MyShopTypeIdVO myShopTypeIdVO = null;
					for(int i = 0; i < myShopTypeIdVoList.size();i++){
						
						//新增店家所擁有的店家類別
						myShopTypeIdVO = (MyShopTypeIdVO)myShopTypeIdVoList.get(i);
						count2 = count2 + shopBaseTrsDAO.insertMyShopTypeId(myShopTypeIdVO);
					}				
				}
			}
			
			//新增圓形MAP服務範圍
			if(count2 >0 ){
				count3 = serviceScopeTrsDAO.createServiceScopeCircle(scopeCircle);
			}
			
			if(count3 >0 ){
				transactionManager.commit(status);
				isCorrect = true;
			}else{
				transactionManager.rollback(status);
			}
		    
	    }
	    catch (Exception ex) {
	    	log.info(ex.toString());
	    	transactionManager.rollback(status);
	    }
		
		return isCorrect;
	}

	

	

	
	
	

	
	
}
