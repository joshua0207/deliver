package com.order.mode.vo;

import java.io.Serializable;

/**
 * @author Joshua
 *
 *SYSTEM_VALUE_ID      編號
 *SYSTEM_ID            系統別
 *SYSTEM_PARAMETER_ID  系統參數代號
 *SYSTEM_VALUE         參數值
 *SYSTEM_REMARK        備註說明
 *UPDATE_DATE          最後異動日期
 *UPDATE_NAME          最後異動人員
 */

public class SysParameterVO  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String SYSTEM_VALUE_ID;
	private String SYSTEM_ID;
	private String SYSTEM_PARAMETER_ID;
	private String SYSTEM_VALUE;
	private String SYSTEM_REMARK;
	private String UPDATE_DATE;
	private String UPDATE_NAME;
	
	
	public String getSYSTEM_VALUE_ID() {
		return SYSTEM_VALUE_ID;
	}
	public void setSYSTEM_VALUE_ID(String sYSTEMVALUEID) {
		SYSTEM_VALUE_ID = sYSTEMVALUEID;
	}
	public String getSYSTEM_ID() {
		return SYSTEM_ID;
	}
	public void setSYSTEM_ID(String sYSTEMID) {
		SYSTEM_ID = sYSTEMID;
	}
	public String getSYSTEM_PARAMETER_ID() {
		return SYSTEM_PARAMETER_ID;
	}
	public void setSYSTEM_PARAMETER_ID(String sYSTEMPARAMETERID) {
		SYSTEM_PARAMETER_ID = sYSTEMPARAMETERID;
	}
	public String getSYSTEM_VALUE() {
		return SYSTEM_VALUE;
	}
	public void setSYSTEM_VALUE(String sYSTEMVALUE) {
		SYSTEM_VALUE = sYSTEMVALUE;
	}
	public String getSYSTEM_REMARK() {
		return SYSTEM_REMARK;
	}
	public void setSYSTEM_REMARK(String sYSTEMREMARK) {
		SYSTEM_REMARK = sYSTEMREMARK;
	}
	public String getUPDATE_DATE() {
		return UPDATE_DATE;
	}
	public void setUPDATE_DATE(String uPDATEDATE) {
		UPDATE_DATE = uPDATEDATE;
	}
	public String getUPDATE_NAME() {
		return UPDATE_NAME;
	}
	public void setUPDATE_NAME(String uPDATENAME) {
		UPDATE_NAME = uPDATENAME;
	}
	
	

}
