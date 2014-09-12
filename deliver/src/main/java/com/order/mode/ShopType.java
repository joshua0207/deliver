package com.order.mode;

import java.io.Serializable;

/** 店家類別 */
public class ShopType implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String shopTypeId;
	private String shopTypeName;
	
	public String getShopTypeId() {
		return shopTypeId;
	}
	public void setShopTypeId(String shopTypeId) {
		this.shopTypeId = shopTypeId;
	}
	public String getShopTypeName() {
		return shopTypeName;
	}
	public void setShopTypeName(String shopTypeName) {
		this.shopTypeName = shopTypeName;
	}

}
