package com.order.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.order.mode.MenuCategory;
import com.order.mode.vo.MenuCategoryVO;



public interface MenuCategoryService {
	
	final Log log = LogFactory.getLog(MenuCategoryService.class);
	
	/** 新增商品分類管理*/
	public Boolean createMenuCategory(String categoryName, String categoryNote, Integer sort, String hideFlag, String shopId);
	
	/** 查詢所有商品分類管理資料 */
	public List<MenuCategoryVO> queryMenuCategoryByShopId(String shopId);
	
	/** 查詢單一商品分類管理資料 */
	public MenuCategory queryMenuCategoryByMenuCatId (String menuCatId);
	
	/** 修改商品分類管理*/
	public Boolean updateMenuCategory(String menuCatId, String categoryName, String categoryNote, Integer sort, String hideFlag, String shopId);
	
	/** 查詢商品分類管理底下產品數量資料 */
	public Integer queryCountMenuProductByMenuCatId (String menuCatId, String shopId);
	
	/** 刪除商品分類管理*/
	public Boolean deleteMenuCategory(String menuCatId);
	
	/** 查詢店家所有商品分類管理資料 */
	public List<MenuCategory> queryAllByKey(String shopId);
	
}
