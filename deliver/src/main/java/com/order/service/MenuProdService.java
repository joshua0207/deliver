package com.order.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.order.mode.MenuProduct;
import com.order.mode.vo.MenuProductVO;



public interface MenuProdService {
	
	final Log log = LogFactory.getLog(MenuProdService.class);
	

	/** 新增商品管理 (MENU_PRODUCT and MENU_ITEM_TYPE)*/
	public Boolean createMenuProductAndItemType(String prodName, String categoryId, Integer prodPrice, Integer onsalePrice, Integer sort,
			String topProd, String prodDesc, String prodDescDetl, String prodOptionId [], String deleteFlag, String picName,  String shopId);
	
	/** 查詢商品管理(MENU_PRODUCT)所有資料，及查詢是否有訂單使用回傳count */
	public Map<String, List<MenuProductVO>> queryMenuProductByShopId(String shopId);
	
	/** 查詢單一商品管理(MENU_PRODUCT)資料 */
	public MenuProductVO queryMenuProductByMenuProdId(String menuProdId);
	
	/** 查詢menu商品選項呈現勾選的商品選項Item Type資料(MENU_ITEM_TYPE)，只回傳MENU_ITME_ID相加用,隔開字串  */
	public String queryMenuItemTypeByKey(String menuProdId, String shopId);
	
	/** 修改商品管理 (MENU_PRODUCT)- 刪除照片把檔案名稱清空*/
	public Boolean updateMenuProductById(MenuProduct menuProduct);
	
	/** 修改商品管理 (MENU_PRODUCT AND MENU_ITEM_TYPE)*/
	public Boolean updateMenuProductByKey(String menuProdId, String prodName, String categoryId, Integer prodPrice, Integer onsalePrice, Integer sort,
			String topProd, String prodDesc, String prodDescDetl, String prodOptionId [], String deleteFlag, String picName,  String shopId);
	
	/** 刪除商品管理所有資料 (MENU_PRODUCT AND MENU_ITEM_TYPE)*/
	public Boolean deleteMenuProduct(String menuProdId, String shopId);
	
	/** 查詢商品管理對應 SHOP_PO_DETL(店家訂單明細)查此商品是否訂購過，如訂購數量大於0，則此商品不可刪除 
	 * Boolean (true數量大於0)，false(數量等於0)
	 */
	public Boolean queryShopPoDetlByKey(String menuProdId, String shopId);
	
}
