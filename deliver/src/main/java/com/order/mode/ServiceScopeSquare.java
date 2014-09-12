package com.order.mode;

import java.io.Serializable;

/**

	SHOP_ID 店家ID
	FIRST_DOT_LAT 左上方的緯度(Y)
	FIRST_DOT_LNG 左上方的經度(X)
	LAST_DOT_LAT 右下方的緯度(Y)
	LAST_DOT_LNG 右下方的經度(X)


 */

public class ServiceScopeSquare  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String shopId;
	private double firstDotLat;
	private double firstDotLng;
	private double lastDotLat;
	private double lastDotLng;	
	

	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public double getFirstDotLat() {
		return firstDotLat;
	}
	public void setFirstDotLat(double firstDotLat) {
		this.firstDotLat = firstDotLat;
	}
	public double getFirstDotLng() {
		return firstDotLng;
	}
	public void setFirstDotLng(double firstDotLng) {
		this.firstDotLng = firstDotLng;
	}
	public double getLastDotLat() {
		return lastDotLat;
	}
	public void setLastDotLat(double lastDotLat) {
		this.lastDotLat = lastDotLat;
	}
	public double getLastDotLng() {
		return lastDotLng;
	}
	public void setLastDotLng(double lastDotLng) {
		this.lastDotLng = lastDotLng;
	}


}
