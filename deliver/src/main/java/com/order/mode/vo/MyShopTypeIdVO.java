package com.order.mode.vo;

import java.io.Serializable;

public class MyShopTypeIdVO implements Serializable{

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
