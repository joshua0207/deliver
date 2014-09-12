package com.order.mode.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**

 * ORDER_ID;           //訂單編號
 * PHONE_NUM;          //手機號碼
 * UPDATE_DATE;        //更新日期 
 */

public class SmsPOVO extends SMSRtnVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String ORDER_ID;           //訂單編號
	private String PHONE_NUM;          //手機號碼
	private String UPDATE_DATE;        //更新日期
	private String RELATION_ID;        //對應訂單編號

	
	
	public String getRELATION_ID() {
		return RELATION_ID;
	}
	
	public void setRELATION_ID(String rELATIONID) {
		RELATION_ID = rELATIONID;
	}
	
	public String getORDER_ID() {
		return ORDER_ID;
	}
	
	public void setORDER_ID(String oRDERID) {
		ORDER_ID = StringUtils.trimToEmpty(oRDERID);
	}
	
	public String getPHONE_NUM() {
		return PHONE_NUM;
	}
	public void setPHONE_NUM(String pHONENUM) {
		PHONE_NUM = StringUtils.trimToEmpty(pHONENUM);
	}
	public String getUPDATE_DATE() {
		return UPDATE_DATE;
	}
	public void setUPDATE_DATE(String uPDATEDATE) {
		UPDATE_DATE = StringUtils.trimToEmpty(uPDATEDATE);
	}
	

	
	
	
	
	
}
