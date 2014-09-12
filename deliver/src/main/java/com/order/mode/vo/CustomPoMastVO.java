package com.order.mode.vo;

import java.io.Serializable;


/**

ORDER_ID	訂購單編號
CUSTOM_ID	發起人會員代號
SHOP_USER_ID	店家代號
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

 */
public class CustomPoMastVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String orderId;	
	private String customId;	
	private String shopUserId;	
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
	private String TOKEN_ID = "";
	private String PROGRESS = "";
	
	
	
	
	public String getTOKEN_ID() {
		return TOKEN_ID;
	}

	public void setTOKEN_ID(String tOKENID) {
		TOKEN_ID = tOKENID;
	}

	//查詢留言是否留過言
	/*public boolean checkLeavemessage() {
		
		String orderId = this.getOrderId();//訂單編號
		boolean tn = false;
		//判斷此訂單是否留過言
		try{
		messageControlQueryDAO checkDao = new messageControlQueryDAO();
		String sql = checkDao.checkLeaveMessageSQL();
		tn = checkDao.checkLeaveMessageData(sql, orderId);
		checkDao.closeConnection();
		if(checkDao != null)checkDao.close();
		}catch(Exception ex){
			log.info("checkLeavemessage error "+ex);
		}
		return tn;
	}*/
	
	public String getOrderKind() {
		return orderKind;
	}
	public void setOrderKind(String orderKind) {
		this.orderKind = orderKind;
	}
	public String getDeliverFlag() {
		return deliverFlag;
	}
	public void setDeliverFlag(String deliverflag) {
		this.deliverFlag = deliverflag;
	}
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
	public String getShopUserId() {
		return shopUserId;
	}
	public void setShopUserId(String shopUserId) {
		this.shopUserId = shopUserId;
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
	public String getPROGRESS() {
		return PROGRESS;
	}

	public void setPROGRESS(String pROGRESS) {
		PROGRESS = pROGRESS;
	}

	public String getMessageInformTime() {
		return messageInformTime;
	}
	public void setMessageInformTime(String messageInformTime) {
		this.messageInformTime = messageInformTime;
	}
	
	

	

}
