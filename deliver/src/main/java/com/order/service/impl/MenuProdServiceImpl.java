package com.order.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.order.dao.MenuProdQueryDAO;
import com.order.dao.MenuProdTrsDAO;
import com.order.mode.MenuItemType;
import com.order.mode.MenuProduct;
import com.order.mode.vo.MenuProductVO;
import com.order.service.MenuProdService;
import com.order.util.TimeMachine;



@Service
public class MenuProdServiceImpl implements MenuProdService {
	
	@Autowired
	private MenuProdQueryDAO menuProdQueryDAO;
	
	@Autowired
	private MenuProdTrsDAO menuProdTrsDAO;
	
	
	@Resource
	private DataSourceTransactionManager transactionManager;
	private DefaultTransactionDefinition def;

	@Override
	public Boolean createMenuProductAndItemType(String prodName, String categoryId,
			Integer prodPrice, Integer onsalePrice, Integer sort, String topProd,
			String prodDesc, String prodDescDetl, String[] prodOptionId,
			String deleteFlag, String picName, String shopId) {
		
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
		
		boolean isCorrect = false;
		int count1 = 0;
		TimeMachine id = new TimeMachine();
		
		 try {
			//新增MENU_PRODUCT (商品管理)
			String prodId = id.serial("prodId", 0);
			MenuProduct menuProduct = new MenuProduct();
			menuProduct.setMenuProdId(prodId);
			menuProduct.setMenuCatId(categoryId);
			menuProduct.setShopId(shopId);
			menuProduct.setMenuProdName(prodName);
			menuProduct.setMenuProdComm(prodDesc);
			menuProduct.setMenuProdCommDetail(prodDescDetl);
			menuProduct.setPicName(picName);
			menuProduct.setMenuPrice(prodPrice);
			menuProduct.setOnsalePrice(onsalePrice);
			menuProduct.setSort(sort);
			menuProduct.setHotProduct(topProd);
			menuProduct.setHideFlag(deleteFlag);
	        int count = menuProdTrsDAO.createMenuProduct(menuProduct);
	        
	        if(count >0){
	        	//新增MENU_ITEM_TYPE (商品管理-勾選的商品選項)
	        	if(prodOptionId != null && prodOptionId.length >0){
	        		//有勾選
		        	List<MenuItemType> menuItemTypeList = new ArrayList<MenuItemType>();
		        	for(int i=0;i<prodOptionId.length;i++){
			        	MenuItemType menuItemType = new MenuItemType();
			        	menuItemType.setMenuTypeId(id.serial("typeId", i));
			        	menuItemType.setMenuProdId(prodId);
			        	menuItemType.setMenuItmeId(prodOptionId[i]);
			        	menuItemType.setShopId(shopId);
			        	menuItemTypeList.add(menuItemType);
			        	menuItemType = null;
		        	}
		        	count1 = menuProdTrsDAO.createMenuItemType(menuItemTypeList);
	        	}else{
	        		//沒勾選-不存MENU_ITEM_TYPE
	        		count1 = 1;
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
	public Map<String, List<MenuProductVO>> queryMenuProductByShopId(String shopId) {
		
		List<MenuProductVO> menuProductList = menuProdQueryDAO.findMenuProductByShopId(shopId);
		
		//結構化MAP轉為單一MenuCatId對應List商品
		Map<String, List<MenuProductVO>> menuProductMap = this.toMap(menuProductList);
		
		return menuProductMap;
	}
	
	
	private Map<String, List<MenuProductVO>> toMap(List<MenuProductVO> menuProductList) {
		Map<String, List<MenuProductVO>> menuProductMap = new HashMap<String, List<MenuProductVO>>();
		for( MenuProductVO menuProduct  : menuProductList){
			List<MenuProductVO> menuProductVOList =  menuProductMap.get(menuProduct.getMenuCatId());
			if( menuProductVOList == null){
				menuProductVOList = new ArrayList<MenuProductVO>();
			}
			menuProductVOList.add(menuProduct);
			
			menuProductMap.put(menuProduct.getMenuCatId(),menuProductVOList);
		}
		return menuProductMap;
	}

	@Override
	public MenuProductVO queryMenuProductByMenuProdId(String menuProdId) {
		
		
		return menuProdQueryDAO.findMenuProductByMenuProdId(menuProdId);
	}

	@Override
	public String queryMenuItemTypeByKey(String menuProdId, String shopId) {
		
		return menuProdQueryDAO.findMenuItemTypeByKey(menuProdId, shopId);
	}

	@Override
	public Boolean updateMenuProductById(MenuProduct menuProduct) {
		
		boolean isCorrect = false; 
	    
    	int count  = menuProdTrsDAO.updateMenuProductById(menuProduct);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}

	@Override
	public Boolean updateMenuProductByKey(String menuProdId, String prodName, String categoryId,
			Integer prodPrice, Integer onsalePrice, Integer sort,
			String topProd, String prodDesc, String prodDescDetl,
			String[] prodOptionId, String deleteFlag, String picName,
			String shopId) {
		
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
		
		boolean isCorrect = false;
		int count2 = 0;
		TimeMachine id = new TimeMachine();
		
		 try {
			//修改MENU_PRODUCT (商品管理)
			MenuProduct menuProduct = new MenuProduct();
			menuProduct.setMenuProdId(menuProdId);
			menuProduct.setMenuCatId(categoryId);
			menuProduct.setShopId(shopId);
			menuProduct.setMenuProdName(prodName);
			menuProduct.setMenuProdComm(prodDesc);
			menuProduct.setMenuProdCommDetail(prodDescDetl);
			menuProduct.setPicName(picName);
			menuProduct.setMenuPrice(prodPrice);
			menuProduct.setOnsalePrice(onsalePrice);
			menuProduct.setSort(sort);
			menuProduct.setHotProduct(topProd);
			menuProduct.setHideFlag(deleteFlag);
	        int count = menuProdTrsDAO.updateMenuProductByKey(menuProduct);
	        
	        if(count >0){
	        	//先刪除原來所有MENU_ITEM_TYPE (商品管理-勾選的商品選項)
	        	menuProdTrsDAO.deleteMenuItemTypeById(menuProdId, shopId);
	        	
	        	//新增MENU_ITEM_TYPE (商品管理-勾選的商品選項)
	        	if(prodOptionId != null && prodOptionId.length >0){
	        		//有勾選
		        	List<MenuItemType> menuItemTypeList = new ArrayList<MenuItemType>();
		        	for(int i=0;i<prodOptionId.length;i++){
			        	MenuItemType menuItemType = new MenuItemType();
			        	menuItemType.setMenuTypeId(id.serial("typeId", i));
			        	menuItemType.setMenuProdId(menuProdId);
			        	menuItemType.setMenuItmeId(prodOptionId[i]);
			        	menuItemType.setShopId(shopId);
			        	menuItemTypeList.add(menuItemType);
			        	menuItemType = null;
		        	}
		        	count2 = menuProdTrsDAO.createMenuItemType(menuItemTypeList);
	        	}else{
	        		//沒勾選-不存MENU_ITEM_TYPE
	        		count2 = 1;
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
	public Boolean deleteMenuProduct(String menuProdId, String shopId) {
		def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    TransactionStatus status = transactionManager.getTransaction(def);
		
		boolean isCorrect = false;
	
		try {
				//刪除商品管理 (MENU_ITEM_TYPE (menu商品選項呈現勾選的商品選項ItemType))
				menuProdTrsDAO.deleteMenuItemTypeById(menuProdId, shopId);
				
				//刪除商品管理 (MENU_PRODUCT)
				int count  = menuProdTrsDAO.deleteMenuProductByProdId(menuProdId, shopId);
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
	public Boolean queryShopPoDetlByKey(String menuProdId, String shopId) {
		boolean isCorrect = false; 
	    
    	int count  = menuProdQueryDAO.findShopPoDetlByKey(menuProdId, shopId);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}

	
	
	
	
	
	
	
}
