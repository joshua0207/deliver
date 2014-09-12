package com.order.mode;

import java.io.Serializable;
import java.util.Date;

/** 營業時間管理
 *  SERVICE_ID				編號
 *  SHOP_ID					店家代號
 *  SERVICE_TIME_BEGIN		營業時段_起
 *  SERVICE_MINUTE_BEGIN	營業時段分鐘_起
 *  SERVICE_TIME_END		營業時段_終
 *  SERVICE_MINUTE_END		營業時段分鐘_終
 *  SERVICE_DAY				每週營業時間
 *  UPDATE_DATE				更新時間
 */
public class ServiceTimeDay implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String serviceId;
	private String shopId;
	private String serviceTimeBegin;
	private String serviceMinuteBegin;
	private String serviceTimeEnd;
	private String serviceMinuteEnd;
	private String serviceDay;
	private Date updateDate;
	
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getServiceTimeBegin() {
		return serviceTimeBegin;
	}
	public void setServiceTimeBegin(String serviceTimeBegin) {
		this.serviceTimeBegin = serviceTimeBegin;
	}
	public String getServiceMinuteBegin() {
		return serviceMinuteBegin;
	}
	public void setServiceMinuteBegin(String serviceMinuteBegin) {
		this.serviceMinuteBegin = serviceMinuteBegin;
	}
	public String getServiceTimeEnd() {
		return serviceTimeEnd;
	}
	public void setServiceTimeEnd(String serviceTimeEnd) {
		this.serviceTimeEnd = serviceTimeEnd;
	}
	public String getServiceMinuteEnd() {
		return serviceMinuteEnd;
	}
	public void setServiceMinuteEnd(String serviceMinuteEnd) {
		this.serviceMinuteEnd = serviceMinuteEnd;
	}
	public String getServiceDay() {
		return serviceDay;
	}
	public void setServiceDay(String serviceDay) {
		this.serviceDay = serviceDay;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
	
	
	

}
