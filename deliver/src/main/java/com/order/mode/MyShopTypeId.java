package com.order.mode;

import java.io.Serializable;

/** 店家所擁有的店家類別*/
public class MyShopTypeId implements Serializable{

private static final long serialVersionUID = 1L;
	
	private String shopId;
	private String shopTypeId;
	
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopTypeId() {
		return shopTypeId;
	}
	public void setShopTypeId(String shopTypeId) {
		this.shopTypeId = shopTypeId;
	}
}
