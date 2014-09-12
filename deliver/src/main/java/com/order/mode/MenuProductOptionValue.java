package com.order.mode;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/**	商品選項管理(MENU_PRODUCT_OPTION_VALUE)
 *  
 *  MENU_OPTION_ID			option value編號
 * 	MENU_ITME_ID			Item編號
 *  SHOP_ID					店家代號
 *  MENU_ITME_VALUE			選項值名稱
 *  MENU_PROD_PRICE			加減價
 *  MENU_PROD_PREFEIX		加減
 *  SORT					排序
 *  HIDE_FLAG				是否隱藏
 *  UPDATE_DATE				更新時間
 */
public class MenuProductOptionValue implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String menuOptionId;
	private String menuItmeId;
	private String shopId;
	private String menuItmeValue;
	private Integer menuProdPrice;
	private String menuProdPrefeix;
	private Integer sort;
	private String hideFlag;
	private Date updateDate;
	
	
	public String getMenuOptionId() {
		return menuOptionId;
	}
	public void setMenuOptionId(String menuOptionId) {
		this.menuOptionId = menuOptionId;
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
	public String getMenuItmeValue() {
		return menuItmeValue;
	}
	public void setMenuItmeValue(String menuItmeValue) {
		this.menuItmeValue = menuItmeValue;
	}
	public Integer getMenuProdPrice() {
		return menuProdPrice;
	}
	public void setMenuProdPrice(Integer menuProdPrice) {
		this.menuProdPrice = menuProdPrice;
	}
	public String getMenuProdPrefeix() {
		return menuProdPrefeix;
	}
	public void setMenuProdPrefeix(String menuProdPrefeix) {
		this.menuProdPrefeix = menuProdPrefeix;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getHideFlag() {
		return hideFlag;
	}
	public void setHideFlag(String hideFlag) {
		this.hideFlag = hideFlag;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	
	

	
}