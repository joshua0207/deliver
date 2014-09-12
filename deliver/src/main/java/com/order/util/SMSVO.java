package com.order.util;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class SMSVO implements Serializable{
	
	private String UserName = "";             //發送簡訊的帳號參數
	private String UserPasswd = "";           //發送簡訊的密碼參數
	private String DestPhoneNum = "";         //收訊人電話參數
	private String Encoding = "";             //編碼方式 參數: Big5 , Unicode , UTF8
	private String DestName = "";             //收訊人名稱參數
	private String SendTime = "";             //傳送時間參數
	private String SMBody = "";               //簡訊內容參數
	private String statusCode = "";           //簡訊業者回覆狀態值
	private String MsgId = "";                //簡訊業者回覆的簡訊序號
	private String smsResponseUrl = "";       //簡訊業者Http 網址
	
	private String DestPhoneNumValue = "";    //收訊人電話實際存放的值
	private String EncodingValue = "Big5";    //編碼方式實際存放的值 : Big5 , Unicode , UTF8
	private String DestNameValue = "";        //收訊人名稱實際存放的值
	private String SendTimeValue = "";        //傳送時間實際存放的值
	private String SMBodyValue = "";          //簡訊內容實際存放的值

	
		

	public String getSmsResponseUrl() {
		return smsResponseUrl;
	}
	public void setSmsResponseUrl(String smsResponseUrl) {
		this.smsResponseUrl = StringUtils.trimToEmpty(smsResponseUrl);
	}

	public String getDestPhoneNumValue() {
		return DestPhoneNumValue;
	}
	public void setDestPhoneNumValue(String destPhoneNumValue) {
		DestPhoneNumValue = StringUtils.trimToEmpty(destPhoneNumValue);
	}
	public String getEncodingValue() {
		return EncodingValue;
	}
	public void setEncodingValue(String encodingValue) {
		EncodingValue = StringUtils.trimToEmpty(encodingValue);
	}
	public String getDestNameValue() {
		return DestNameValue;
	}
	public void setDestNameValue(String destNameValue) {
		DestNameValue = StringUtils.trimToEmpty(destNameValue);
	}
	public String getSendTimeValue() {
		return SendTimeValue;
	}
	public void setSendTimeValue(String sendTimeValue) {
		SendTimeValue = StringUtils.trimToEmpty(sendTimeValue);
	}
	public String getSMBodyValue() {
		return SMBodyValue;
	}
	public void setSMBodyValue(String sMBodyValue) {
		SMBodyValue = sMBodyValue;
	}

	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = StringUtils.trimToEmpty(userName);
	}
	public String getUserPasswd() {
		return UserPasswd;
	}
	public void setUserPasswd(String userPasswd) {
		UserPasswd = StringUtils.trimToEmpty(userPasswd);
	}
	public String getDestPhoneNum() {
		return DestPhoneNum;
	}
	public void setDestPhoneNum(String destPhoneNum) {
		DestPhoneNum = StringUtils.trimToEmpty(destPhoneNum);
	}
	public String getEncoding() {
		return Encoding;
	}
	public void setEncoding(String encoding) {
		Encoding = StringUtils.trimToEmpty(encoding);
	}
	public String getDestName() {
		return DestName;
	}
	public void setDestName(String destName) {
		DestName = StringUtils.trimToEmpty(destName);
	}
	public String getSendTime() {
		return SendTime;
	}
	public void setSendTime(String sendTime) {
		SendTime = StringUtils.trimToEmpty(sendTime);
	}
	public String getSMBody() {
		return SMBody;
	}
	public void setSMBody(String sMBody) {
		SMBody = StringUtils.trimToEmpty(sMBody);
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = StringUtils.trimToEmpty(statusCode);
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = StringUtils.trimToEmpty(msgId);
	}
	
	
	
	
	
}
