package com.order.mode.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/**	商品分類管理(MENU_CATEGORY)
 *  
 *  MENU_CAT_ID			分類編號
 * 	SHOP_ID				店家代號
 *  MENU_CAT_NAME		分類名稱
 *  MENU_CAT_NOTE		分類描述
 *  SORT				排序
 *  HIDE_FLAG			是否隱藏
 *  UPDATE_DATE			更新時間
 *  productCount		查詢底下MENU_PRODUCT產品的數量
 */
public class MenuCategoryVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String menuCatId;
	private String shopId;
	private String menuCatName;
	private String menuCatNote;
	private Integer sort;
	private String hideFlag;
	private Date updateDate;
	
	private Integer productCount;
	
	
	public String getMenuCatId() {
		return menuCatId;
	}
	public void setMenuCatId(String menuCatId) {
		this.menuCatId = menuCatId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getMenuCatName() {
		return menuCatName;
	}
	public void setMenuCatName(String menuCatName) {
		this.menuCatName = menuCatName;
	}
	public String getMenuCatNote() {
		return menuCatNote;
	}
	public void setMenuCatNote(String menuCatNote) {
		this.menuCatNote = menuCatNote;
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
	
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

	
}