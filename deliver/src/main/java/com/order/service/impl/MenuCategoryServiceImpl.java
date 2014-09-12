package com.order.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.MenuCategoryQueryDAO;
import com.order.dao.MenuCategoryTrsDAO;
import com.order.mode.MenuCategory;
import com.order.mode.vo.MenuCategoryVO;
import com.order.service.MenuCategoryService;
import com.order.util.TimeMachine;



@Service
public class MenuCategoryServiceImpl implements MenuCategoryService {
	
	@Autowired
	private MenuCategoryQueryDAO menuCategoryQueryDAO;
	
	@Autowired
	private MenuCategoryTrsDAO menuCategoryTrsDAO;
	
//	@Resource
//	private DataSourceTransactionManager transactionManager;
//	private DefaultTransactionDefinition def;

	@Override
	public Boolean createMenuCategory(String categoryName, String categoryNote, Integer sort, String hideFlag, String shopId) {
		
		boolean isCorrect = false;
		
		TimeMachine id = new TimeMachine();
		
		MenuCategory menuCategory = new MenuCategory();
		menuCategory.setMenuCatId(id.serial("cateid", 0));
		menuCategory.setShopId(shopId);
		menuCategory.setMenuCatName(categoryName);
		menuCategory.setMenuCatNote(categoryNote);
		menuCategory.setSort(sort);
		menuCategory.setHideFlag(hideFlag);
        int count = menuCategoryTrsDAO.createMenuCategory(menuCategory);
        if(count>0){
        	isCorrect = true;
        }
        
		return isCorrect;
	}

	@Override
	public List<MenuCategoryVO> queryMenuCategoryByShopId(String shopId) {
		
		return menuCategoryQueryDAO.findMenuCategoryByShopId(shopId);
	}

	@Override
	public MenuCategory queryMenuCategoryByMenuCatId(String menuCatId) {
		
		return menuCategoryQueryDAO.findMenuCategoryByMenuCatId(menuCatId);
	}

	@Override
	public Boolean updateMenuCategory(String menuCatId, String categoryName, String categoryNote, Integer sort, String hideFlag, String shopId) {
		
		boolean isCorrect = false;
		
		MenuCategory menuCategory = new MenuCategory();
		menuCategory.setMenuCatId(menuCatId);
		menuCategory.setShopId(shopId);
		menuCategory.setMenuCatName(categoryName);
		menuCategory.setMenuCatNote(categoryNote);
		menuCategory.setSort(sort);
		menuCategory.setHideFlag(hideFlag);
        int count = menuCategoryTrsDAO.updateMenuCategory(menuCategory);
        if(count>0){
        	isCorrect = true;
        }
        
		return isCorrect;
	}

	@Override
	public Integer queryCountMenuProductByMenuCatId(String menuCatId, String shopId) {
		
		return menuCategoryQueryDAO.findCountMenuProductByMenuCatId(menuCatId, shopId);
	}

	@Override
	public Boolean deleteMenuCategory(String menuCatId) {
		
		boolean isCorrect = false;
		
        int count = menuCategoryTrsDAO.deleteMenuCategory(menuCatId);
        if(count>0){
        	isCorrect = true;
        }
        
        return isCorrect;
	}

	@Override
	public List<MenuCategory> queryAllByKey(String shopId) {
		
		return menuCategoryQueryDAO.findAllByKey(shopId);
	}
	

	
	
	
	
	
	
	
}
