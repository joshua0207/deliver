package com.order.mode;

import java.io.Serializable;

/**

SHOP_ID 店家帳號	
DOT_LAT_LNG 點位資料	
TOTAL 點位資料總數量	
方型範圍最大經度	MaxLng
方型範圍最大緯度	MaxLat
方型範圍最小經度	MinLng
方型範圍最小緯度	MinLat



 */

public class ServiceScopeIrregular  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String shopId;
	private String dotLatLng;
	private int total;
	private double maxLng;
	private double maxLat;
	private double minLng;
	private double minLat;


	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getDotLatLng() {
		return dotLatLng;
	}
	public void setDotLatLng(String dotLatLng) {
		this.dotLatLng = dotLatLng;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getMaxLng() {
		return maxLng;
	}
	public void setMaxLng(double maxLng) {
		this.maxLng = maxLng;
	}
	public double getMaxLat() {
		return maxLat;
	}
	public void setMaxLat(double maxLat) {
		this.maxLat = maxLat;
	}
	public double getMinLng() {
		return minLng;
	}
	public void setMinLng(double minLng) {
		this.minLng = minLng;
	}
	public double getMinLat() {
		return minLat;
	}
	public void setMinLat(double minLat) {
		this.minLat = minLat;
	}

	
	
	
}
