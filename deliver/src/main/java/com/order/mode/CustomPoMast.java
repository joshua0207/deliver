package com.order.mode;

import java.io.Serializable;

/**
訂單主檔
ORDER_ID	訂購單編號
CUSTOM_ID	發起人會員代號
SHOP_ID	店家代號
DELIVER_ADDRESS	外送地址
CONTACT_PHONE	連絡電話
CONTACT_NAME	聯絡人姓名
ORDER_STATUS	訂單狀態
ORDER_CONFIRM_DATE	轉成有效訂單日期時間
ORDER_TYPE	訂購型態
MIN_ORDER_AMOUNT	最低訂購金額
ORDER_AMOUNT	訂單總金額
END_DATE_TIME	訂購截止日期時間
DELIVER_DATE_TIME	會員要求送達日期時間
CREATE_DATE	建立日期時間
UPDATE_DATE	最後異動日期時間
ONLINE_PAYMENT	線上付款
MEMO	備註說明
AUTO_INFORM	自動通知未訂購人員
MESSAGE_INFORM	訂購單通知店家方式
MESSAGE_INFORM_TIME	訂購單成功通知店家日期時間
ORDER_SOURCE		訂單來源
SHOPMEMO			備註說明(給店家)
 */
public class CustomPoMast implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String orderId;	
	private String customId;	
	private String shopId;	
	private String deliverAddress;	
	private String contactPhone;
	
	private String contactName;
	private String orderStatus;	
	private String orderConfirmDate;	
	private String orderType;	
	private String minOrderAmount;	
	
	private String orderAmount;	
	private String endDateTime;
	private String deliverDateTime;	
	private String createDate;
	private String updateDate;
	
	private String onlinePayment;	
	private String memo;	
	private String autoInform;
	private String messageInform;	
	private String messageInformTime;
	private String deliverFlag;
	private String orderKind;
	private String shopCreditId;
	private String auto_inform_flag;
	private String shopmemo;
	private String progress;
	private String progressDate;
	private String progressUpdateSource;
	private String androidInformTime;
	private String androidUserInformUpdate;
	private String lat;
	private String lng;
	private String posTransFlag;
	private String shopIsRead;
	private String shopCancel;
	
	private String orderSource;
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getDeliverAddress() {
		return deliverAddress;
	}
	public void setDeliverAddress(String deliverAddress) {
		this.deliverAddress = deliverAddress;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderConfirmDate() {
		return orderConfirmDate;
	}
	public void setOrderConfirmDate(String orderConfirmDate) {
		this.orderConfirmDate = orderConfirmDate;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getMinOrderAmount() {
		return minOrderAmount;
	}
	public void setMinOrderAmount(String minOrderAmount) {
		this.minOrderAmount = minOrderAmount;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getDeliverDateTime() {
		return deliverDateTime;
	}
	public void setDeliverDateTime(String deliverDateTime) {
		this.deliverDateTime = deliverDateTime;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getOnlinePayment() {
		return onlinePayment;
	}
	public void setOnlinePayment(String onlinePayment) {
		this.onlinePayment = onlinePayment;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAutoInform() {
		return autoInform;
	}
	public void setAutoInform(String autoInform) {
		this.autoInform = autoInform;
	}
	public String getMessageInform() {
		return messageInform;
	}
	public void setMessageInform(String messageInform) {
		this.messageInform = messageInform;
	}
	public String getMessageInformTime() {
		return messageInformTime;
	}
	public void setMessageInformTime(String messageInformTime) {
		this.messageInformTime = messageInformTime;
	}
	public String getDeliverFlag() {
		return deliverFlag;
	}
	public void setDeliverFlag(String deliverFlag) {
		this.deliverFlag = deliverFlag;
	}
	public String getOrderKind() {
		return orderKind;
	}
	public void setOrderKind(String orderKind) {
		this.orderKind = orderKind;
	}
	public String getShopCreditId() {
		return shopCreditId;
	}
	public void setShopCreditId(String shopCreditId) {
		this.shopCreditId = shopCreditId;
	}
	public String getAuto_inform_flag() {
		return auto_inform_flag;
	}
	public void setAuto_inform_flag(String auto_inform_flag) {
		this.auto_inform_flag = auto_inform_flag;
	}
	public String getShopmemo() {
		return shopmemo;
	}
	public void setShopmemo(String shopmemo) {
		this.shopmemo = shopmemo;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getProgressDate() {
		return progressDate;
	}
	public void setProgressDate(String progressDate) {
		this.progressDate = progressDate;
	}
	public String getProgressUpdateSource() {
		return progressUpdateSource;
	}
	public void setProgressUpdateSource(String progressUpdateSource) {
		this.progressUpdateSource = progressUpdateSource;
	}
	public String getAndroidInformTime() {
		return androidInformTime;
	}
	public void setAndroidInformTime(String androidInformTime) {
		this.androidInformTime = androidInformTime;
	}
	public String getAndroidUserInformUpdate() {
		return androidUserInformUpdate;
	}
	public void setAndroidUserInformUpdate(String androidUserInformUpdate) {
		this.androidUserInformUpdate = androidUserInformUpdate;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getPosTransFlag() {
		return posTransFlag;
	}
	public void setPosTransFlag(String posTransFlag) {
		this.posTransFlag = posTransFlag;
	}
	public String getShopIsRead() {
		return shopIsRead;
	}
	public void setShopIsRead(String shopIsRead) {
		this.shopIsRead = shopIsRead;
	}
	public String getShopCancel() {
		return shopCancel;
	}
	public void setShopCancel(String shopCancel) {
		this.shopCancel = shopCancel;
	}
	public String getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	
	
	
	

}
