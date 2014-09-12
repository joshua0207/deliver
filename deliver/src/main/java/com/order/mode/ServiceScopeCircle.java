package com.order.mode;

import java.io.Serializable;
import java.util.Date;

/**
CIRCLE_ID 		圓形編號	
SHOP_ID			店家帳號	
CIRCLE_CENTER 	圓心	
CIRCLE_TAN		圓形相切點
DISTANCE 		半徑	
MAX_LNG			方型範圍最大經度
MAX_LAT			方型範圍最大緯度
MIN_LNG			方型範圍最小經度
MIN_LAT			方型範圍最小緯度
UPDATE_DATE		更新時間
 */

public class ServiceScopeCircle  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String circleId;
	private String shopId;
	private String circleCenter;
	private String circleTan;
	private double distance;
	private double maxLng;
	private double maxLat;
	private double minLng;
	private double minLat;
	private Date updateDate;
	
	
	public String getCircleId() {
		return circleId;
	}
	public void setCircleId(String circleId) {
		this.circleId = circleId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getCircleCenter() {
		return circleCenter;
	}
	public void setCircleCenter(String circleCenter) {
		this.circleCenter = circleCenter;
	}

	public void setCircleTan(String circleTan) {
		this.circleTan = circleTan;
	}	
	public String getCircleTan() {
		return circleTan;
	}	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
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
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
}
