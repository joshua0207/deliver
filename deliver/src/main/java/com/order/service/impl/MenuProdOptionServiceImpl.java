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

import com.order.dao.MenuProdOptionQueryDAO;
import com.order.dao.MenuProdOptionTrsDAO;
import com.order.mode.MenuProductItem;
import com.order.mode.MenuProductOptionValue;
import com.order.mode.vo.MenuProductItemVO;
import com.order.service.MenuProdOptionService;
import com.order.util.TimeMachine;



@Service
public class MenuProdOptionServiceImpl implements MenuProdOptionService {
	
	@Autowired
	private MenuProdOptionQueryDAO menuProdOptionQueryDAO;
	
	@Autowired
	private MenuProdOptionTrsDAO menuProdOptionTrsDAO;
	
	@Resource
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;

	@Override
	public Boolean createMenuProductItemAndOptionValue(String prodOptionName,
			String prodOptionSingle, String[] prodOptionItemName,
			String[] prefix, Integer [] price, Integer[] sort, String isDefault, String shopId) {
		
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
		
		boolean isCorrect = false;
		int count1 = 0;
		TimeMachine id = new TimeMachine();
		
		 try {
			//新增MENU_PRODUCT_ITEM (商品選項管理)
			String menuItmeId = id.serial("itmeId", 0);
			MenuProductItem menuProductItem = new MenuProductItem();
			menuProductItem.setMenuItmeId(menuItmeId);
			menuProductItem.setShopId(shopId);
			menuProductItem.setMenuItmeName(prodOptionName);
			menuProductItem.setMenuChoice(prodOptionSingle);
	        int count = menuProdOptionTrsDAO.createMenuProductItem(menuProductItem);
	        
	        if(count >0){
	        	//新增MENU_PRODUCT_OPTION_VALUE  (商品選項管理)
	        	List<MenuProductOptionValue> optionValueList = new ArrayList<MenuProductOptionValue>();
	        	if(prodOptionItemName != null && prodOptionItemName.length >0){
	        		for(int i=0;i<prodOptionItemName.length;i++){
	        			MenuProductOptionValue productOptionValue = new MenuProductOptionValue();
	        			String [] hideFlag = isDefault.split(",");
	        			productOptionValue.setMenuOptionId(id.serial("optionId", i));
	        			productOptionValue.setMenuItmeId(menuItmeId);
	        			productOptionValue.setShopId(shopId);
	        			productOptionValue.setMenuItmeValue(prodOptionItemName[i]);
	        			productOptionValue.setMenuProdPrice(price[i]);
	        			productOptionValue.setMenuProdPrefeix(prefix[i]);
	        			productOptionValue.setSort(sort[i]);
	        			productOptionValue.setHideFlag(hideFlag[i]);
	        			optionValueList.add(productOptionValue);
	        			productOptionValue = null;
	        		}
	        		
	        		count1 = menuProdOptionTrsDAO.createMenuProductOptionValue(optionValueList);
	        	}
	        }
	        
	        
	        if(count1>0){
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
	public List<MenuProductItemVO> queryMenuProdOptionAndValueByShopId(String shopId) {
		
		return menuProdOptionQueryDAO.findMenuProdOptionAndValueByShopId(shopId);
	}

	@Override
	public MenuProductItem queryMenuProductItemByMenuItmeId(String menuItmeId) {
		
		return menuProdOptionQueryDAO.findMenuProductItemByMenuItmeId(menuItmeId);
	}

	@Override
	public List<MenuProductOptionValue> queryMenuProductOptionValueByMenuItmeId(String menuItmeId) {
		
		return menuProdOptionQueryDAO.findMenuProductOptionValueByMenuItmeId(menuItmeId);
	}

	@Override
	public Boolean updateMenuProductItemAndOptionValue(String prodOptionName,
			String prodOptionSingle, String[] prodOptionItemName,
			String[] prefix, Integer[] price, Integer[] sort, String isDefault,
			String menuItmeId, String shopId) {
		
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
		
		boolean isCorrect = false;
		int count2 = 0;
		TimeMachine id = new TimeMachine();
		
		 try {
			//修改MENU_PRODUCT_ITEM (商品選項管理)
			MenuProductItem menuProductItem = new MenuProductItem();
			menuProductItem.setMenuItmeId(menuItmeId);
			menuProductItem.setShopId(shopId);
			menuProductItem.setMenuItmeName(prodOptionName);
			menuProductItem.setMenuChoice(prodOptionSingle);
	        int count = menuProdOptionTrsDAO.updateMenuProductItem(menuProductItem);
	        
	        if(count >0){
	        	
	        	//刪除原先MENU_PRODUCT_OPTION_VALUE (商品選項管理)
	        	int count1 = menuProdOptionTrsDAO.deleteMenuProductOptionValue(menuItmeId);
	        	
	        	if(count1 >0){
	        		
	        		//新增MENU_PRODUCT_OPTION_VALUE (商品選項管理)
		        	List<MenuProductOptionValue> optionValueList = new ArrayList<MenuProductOptionValue>();
		        	if(prodOptionItemName != null && prodOptionItemName.length >0){
		        		for(int i=0;i<prodOptionItemName.length;i++){
		        			MenuProductOptionValue productOptionValue = new MenuProductOptionValue();
		        			String [] hideFlag = isDefault.split(",");
		        			productOptionValue.setMenuOptionId(id.serial("optionId", i));
		        			productOptionValue.setMenuItmeId(menuItmeId);
		        			productOptionValue.setShopId(shopId);
		        			productOptionValue.setMenuItmeValue(prodOptionItemName[i]);
		        			productOptionValue.setMenuProdPrice(price[i]);
		        			productOptionValue.setMenuProdPrefeix(prefix[i]);
		        			productOptionValue.setSort(sort[i]);
		        			productOptionValue.setHideFlag(hideFlag[i]);
		        			optionValueList.add(productOptionValue);
		        			productOptionValue = null;
		        		}
		        		
		        		count2 = menuProdOptionTrsDAO.createMenuProductOptionValue(optionValueList);
		        		
		        	}
	        		
	        	}
	        	
	        }
	        
	        if(count2>0){
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
	public Integer queryCountMenuItemTypeByKey(String menuItmeId, String shopId) {
		
		return menuProdOptionQueryDAO.findCountMenuItemTypeByKey(menuItmeId, shopId);
	}

	@Override
	public Boolean deleteMenuProductItemAndOptionValue(String menuItmeId, String shopId) {
		
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
		
		boolean isCorrect = false;
		int count1 = 0;
		
		 try {
			//刪除MENU_PRODUCT_ITEM (商品選項管理)
	        int count = menuProdOptionTrsDAO.deleteMenuProductItem(menuItmeId);
	        
	        if(count >0){
	        	
	        	//刪除MENU_PRODUCT_OPTION_VALUE (商品選項管理)
	        	count1 = menuProdOptionTrsDAO.deleteMenuProductOptionValue(menuItmeId);
	        	
	        }
	        
	        if(count1>0){
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
	public List<MenuProductItem> queryAllByShopId(String shopId) {
		
		return menuProdOptionQueryDAO.findAllByShopId(shopId);
	}
	

}
