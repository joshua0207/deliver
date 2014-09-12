package com.order.mode;

import java.io.Serializable;
import java.util.Date;

/** 外送時間管理
 *  SEND_ID					編號
 *  SHOP_ID					店家代號
 *  SEND_TIME_BEGIN			外送時段_起
 *  SEND_MINUTE_BEGIN		外送時段分鐘_起
 *  SEND_TIME_END			外送時段_終
 *  SEND_MINUTE_END			外送時段分鐘_終
 *  SEND_DAY				每週外送時間
 *  UPDATE_DATE				更新時間
 */
public class SendTimeDay implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String sendId;
	private String shopId;
	private String sendTimeBegin;
	private String sendMinuteBegin;
	private String sendTimeEnd;
	private String sendMinuteEnd;
	private String sendDay;
	private Date updateDate;
	
	
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getSendTimeBegin() {
		return sendTimeBegin;
	}
	public void setSendTimeBegin(String sendTimeBegin) {
		this.sendTimeBegin = sendTimeBegin;
	}
	public String getSendMinuteBegin() {
		return sendMinuteBegin;
	}
	public void setSendMinuteBegin(String sendMinuteBegin) {
		this.sendMinuteBegin = sendMinuteBegin;
	}
	public String getSendTimeEnd() {
		return sendTimeEnd;
	}
	public void setSendTimeEnd(String sendTimeEnd) {
		this.sendTimeEnd = sendTimeEnd;
	}
	public String getSendMinuteEnd() {
		return sendMinuteEnd;
	}
	public void setSendMinuteEnd(String sendMinuteEnd) {
		this.sendMinuteEnd = sendMinuteEnd;
	}
	public String getSendDay() {
		return sendDay;
	}
	public void setSendDay(String sendDay) {
		this.sendDay = sendDay;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
	
	
	
	
	

}
