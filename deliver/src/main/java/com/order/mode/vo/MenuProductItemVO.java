package com.order.mode.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/**	商品選項管理(MENU_PRODUCT_ITEM)
 *  
 *  MENU_ITME_ID		Item編號
 * 	SHOP_ID				店家代號
 *  MENU_ITME_NAME		選項名稱
 *  MENU_CHOICE			單/複選
 *  UPDATE_DATE			更新時間
 *  
 *  MENU_ITME_VALUE		選項值名稱
 *  COUNT				店家商品選項menu_item_type勾選的數量
 */
public class MenuProductItemVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String menuItmeId;
	private String shopId;
	private String menuItmeName;
	private String menuChoice;
	private Date updateDate;
	
	private String menuItmeValue;
	private String count;
	
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
	public String getMenuItmeName() {
		return menuItmeName;
	}
	public void setMenuItmeName(String menuItmeName) {
		this.menuItmeName = menuItmeName;
	}
	public String getMenuChoice() {
		return menuChoice;
	}
	public void setMenuChoice(String menuChoice) {
		this.menuChoice = menuChoice;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getMenuItmeValue() {
		return menuItmeValue;
	}
	public void setMenuItmeValue(String menuItmeValue) {
		this.menuItmeValue = menuItmeValue;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	
	
	
	
	
	

	
}