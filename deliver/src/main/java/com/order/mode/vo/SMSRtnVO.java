package com.order.mode.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class SMSRtnVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int HttpRtnCode = 0;         //Http 回傳代碼 Ex : 200 表示成功, 400表示無效 , 500表示內部伺服器錯誤 ...
	private String MsgId = "";           //簡訊業者回傳的簡訊序號
	private String StatusCode = "";      //簡訊業者回傳狀態碼
	private String StatusCodeDesc = "";   //簡訊業者回傳狀態碼說明
	private long AccountPoint = 0;       //三竹簡訊回傳的剩餘點數
	private String Duplicate = "N";      //是否為重複發送簡訊
	
		

	public String getStatusCodeDesc() {
		return StatusCodeDesc;
	}
	public void setStatusCodeDesc(String statuscode) {
		
		if(statuscode.equalsIgnoreCase("*")){
			StatusCodeDesc = "系統發生錯誤, 請聯絡簡訊業者";
		} else if(statuscode.equalsIgnoreCase("a")){
			StatusCodeDesc = "簡訊發送功能暫時停止服務，請稍候再試";	
		} else if(statuscode.equalsIgnoreCase("b")){
			StatusCodeDesc = "簡訊發送功能暫時停止服務，請稍候再試";
		} else if(statuscode.equalsIgnoreCase("c")){
			StatusCodeDesc = "請輸入帳號";
		} else if(statuscode.equalsIgnoreCase("d")){
			StatusCodeDesc = "請輸入密碼";
		} else if(statuscode.equalsIgnoreCase("e")){
			StatusCodeDesc = "帳號、密碼錯誤";
		} else if(statuscode.equalsIgnoreCase("f")){
			StatusCodeDesc = "帳號已過期";
		} else if(statuscode.equalsIgnoreCase("h")){
			StatusCodeDesc = "帳號已被停用";
		} else if(statuscode.equalsIgnoreCase("k")){
			StatusCodeDesc = "無效的連線位址";
		} else if(statuscode.equalsIgnoreCase("m")){
			StatusCodeDesc = "必須變更密碼，在變更密碼前，無法使用簡訊發送服務";
		} else if(statuscode.equalsIgnoreCase("n")){
			StatusCodeDesc = "密碼已逾期，在變更密碼前，將無法使用簡訊發送服務";
		} else if(statuscode.equalsIgnoreCase("p")){
			StatusCodeDesc = "沒有權限使用外部Http程式";
		} else if(statuscode.equalsIgnoreCase("r")){
			StatusCodeDesc = "系統暫停服務，請稍後再試";
		} else if(statuscode.equalsIgnoreCase("s")){
			StatusCodeDesc = "帳務處理失敗，無法發送簡訊";
		} else if(statuscode.equalsIgnoreCase("t")){
			StatusCodeDesc = "簡訊已過期";
		} else if(statuscode.equalsIgnoreCase("u")){
			StatusCodeDesc = "簡訊內容不得為空白";
		} else if(statuscode.equalsIgnoreCase("v")){
			StatusCodeDesc = "無效的手機號碼";
		} else if(statuscode.equalsIgnoreCase("0")){
			StatusCodeDesc = "預約傳送中";
		} else if(statuscode.equalsIgnoreCase("1")){
			StatusCodeDesc = "已送達業者";
		} else if(statuscode.equalsIgnoreCase("2")){
			StatusCodeDesc = "已送達業者";
		} else if(statuscode.equalsIgnoreCase("3")){
			StatusCodeDesc = "已送達業者";
		} else if(statuscode.equalsIgnoreCase("4")){
			StatusCodeDesc = "已送達手機";
		} else if(statuscode.equalsIgnoreCase("5")){
			StatusCodeDesc = "內容有錯誤";
		} else if(statuscode.equalsIgnoreCase("6")){
			StatusCodeDesc = "門號有錯誤";
		} else if(statuscode.equalsIgnoreCase("7")){
			StatusCodeDesc = "簡訊已停用";
		} else if(statuscode.equalsIgnoreCase("8")){
			StatusCodeDesc = "逾時無送達";
		} else if(statuscode.equalsIgnoreCase("9")){
			StatusCodeDesc = "預約已取消";
		}		
	}
	
	public int getHttpRtnCode() {
		return HttpRtnCode;
	}
	public void setHttpRtnCode(int httpRtnCode) {
		HttpRtnCode = httpRtnCode;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = StringUtils.trimToEmpty(msgId);
	}
	public String getStatusCode() {
		return StatusCode;
	}
	public void setStatusCode(String statusCode) {
		StatusCode = StringUtils.trimToEmpty(statusCode);
	}

	public long getAccountPoint() {
		return AccountPoint;
	}
	public void setAccountPoint(long accountPoint) {
		AccountPoint = accountPoint;
	}
	public String getDuplicate() {
		return Duplicate;
	}
	public void setDuplicate(String duplicate) {
		Duplicate = StringUtils.trimToEmpty(duplicate);
	}
	
	
	
	
	
	
}
