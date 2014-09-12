package com.order.mode;

import java.io.Serializable;

/** 訂單簡訊回傳資料檔
 *  ORDER_ID         訂單編號
 *  MSG_ID           簡訊業者回傳的簡訊序號
 *  STATUS_CODE      回傳的代號
 *  PHONE_NUM        手機號碼
 *  UPDATE_DATE      更新時間
 *  STATUS_CODE_DESC 代碼說明
 *  RELATION_ID 對應訂單編號
 */

public class CustomPoSms implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String orderId;
	private String msgId;
	private String statusCode;
	private String phoneNum;
	private String updateDate;
	private String statusCodeDesc;
	private String relationId;
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getStatusCodeDesc() {
		return statusCodeDesc;
	}
	public void setStatusCodeDesc(String statusCodeDesc) {
		this.statusCodeDesc = statusCodeDesc;
	}
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
	
	
}
