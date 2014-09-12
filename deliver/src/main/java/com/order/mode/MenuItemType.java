package com.order.mode;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;


/**	商品管理(MENU_ITEM_TYPE (menu商品選項呈現勾選的商品選項Item Type))
 *  
 *  MENU_TYPE_ID			ITEM TYPE編號
 * 	MENU_PROD_ID			產品編號
 *  MENU_ITME_ID			Item編號
 *  SHOP_ID					店家代號
 *  UPDATE_DATE				更新時間
 */
public class MenuItemType implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String menuTypeId;
	private String menuProdId;
	private String menuItmeId;
	private String shopId;
	private String updateDate;
	
	
	public String getMenuTypeId() {
		return menuTypeId;
	}
	public void setMenuTypeId(String menuTypeId) {
		this.menuTypeId = menuTypeId;
	}
	public String getMenuProdId() {
		return menuProdId;
	}
	public void setMenuProdId(String menuProdId) {
		this.menuProdId = menuProdId;
	}
	public String getMenuItmeId() {
		return menuItmeId;
	}
	public void setMenuItmeId(String menuItmeId) {
		this.menuItmeId = menuItmeId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	
	
	
	
	
	

	
}