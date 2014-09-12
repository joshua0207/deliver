package com.order.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestParam;

import com.order.mode.MenuProductItem;
import com.order.mode.MenuProductOptionValue;
import com.order.mode.vo.MenuProductItemVO;



public interface MenuProdOptionService {
	
	final Log log = LogFactory.getLog(MenuProdOptionService.class);
	

	/** 新增商品選項管理 (MENU_PRODUCT_ITEM) And (MENU_PRODUCT_OPTION_VALUE)*/
	public Boolean createMenuProductItemAndOptionValue(String prodOptionName, String prodOptionSingle, String [] prodOptionItemName, 
			String [] prefix , Integer [] price, @RequestParam Integer [] sort,  String isDefault, String shopId);
	
	/** 商品選項管理List頁面查詢資料 */
	public List<MenuProductItemVO> queryMenuProdOptionAndValueByShopId(String shopId);
	
	/** 查詢商品選項管理(MENU_PRODUCT_ITEM)資料 */
	public MenuProductItem queryMenuProductItemByMenuItmeId(String menuItmeId);

	/** 查詢商品選項管理(MENU_PRODUCT_OPTION_VALUE)資料 */
	public List<MenuProductOptionValue> queryMenuProductOptionValueByMenuItmeId(String menuItmeId);
	
	/** 修改商品選項管理 (MENU_PRODUCT_ITEM) And (MENU_PRODUCT_OPTION_VALUE)*/
	public Boolean updateMenuProductItemAndOptionValue(String prodOptionName, String prodOptionSingle, String [] prodOptionItemName, 
			String [] prefix , Integer [] price, @RequestParam Integer [] sort,  String isDefault, String menuItmeId, String shopId);
	
	/** 刪除商品選項管理前查詢MENU_ITEM_TYPE (menu商品選項呈現勾選的Item Type)下產品數量 */
	public Integer queryCountMenuItemTypeByKey (String menuItmeId, String shopId);
	
	/** 刪除商品選項管理 (MENU_PRODUCT_ITEM and MENU_PRODUCT_OPTION_VALUE)*/
	public Boolean deleteMenuProductItemAndOptionValue(String menuItmeId, String shopId);
	
	/** 查詢商品選項管理(MENU_PRODUCT_ITEM)所有資料 */
	public List<MenuProductItem> queryAllByShopId(String shopId);
	
	
}
