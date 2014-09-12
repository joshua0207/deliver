package com.order.mode;

import java.io.Serializable;
import java.util.Date;


/**
   店家主檔
 * SHOP_ID 帳號
 * USER PW 密碼	
 * CO NAME 店名/公司全名
 * CO NUM 統一編號
 * CO PEOPLE 聯絡人
 * ADDR L 聯絡地址
 * ADDR C 營業地址
 * TEL 電話	
 * MOBILE_PHONE 手機
 * TEL_ORDER 訂餐專線
 * EMAIL E-mail
 * MY SHOP TYPE ID 選擇店家類別
 * CR DATE 註冊日期
 * BEGIN DATE 生效日期
 * END DATE 到期日
 * SHOP_LEVEL　會員等級
 * SOURCE　來源
 * ADDRCLAT 營業地址緯度
 * ADDRCLNG 營業地址經度
 * ADDR C_CITY 營業地址所處城市
 * ADDR C_CANTON 營業地址所處鄉鎮縣區
 * ADDR C_DETAIL 營業詳細地址
 * ADDR L_CITY 聯絡地址所處城市
 * ADDR L_CANTON 聯絡地址所處鄉鎮縣區
 * ADDR L_DETAIL 聯絡詳細地址
 * INVOICE_TYPE　開立給店家的發票類別
 * IDENT_NUM　開立給店家發票時所需的統編
 * COMPANY_NAME　開立給店家發票時所需的買受人名稱  
 * SHOP_PARAMETER EMAIL註冊確認參數
 * EMAIL_AUTH  Email認證是否通過
 * MOBILE_PARAMETER 手機註冊確認參數
 * PHONE_AUTH 手機認證是否通過
 * SEND_FUNCTION	外送功能開啟
 * SEND_ONLINE_ORDER	外送線上訂購功能開啟
 * SEND_ORDER_WAY		外送收取訂單方式
 * SEND_EMAIL			外送訂單用E-mail
 * SEND_SMS				外送訂單用SMS手機號碼
 * SEND_ANDROID			外送訂單用平版電腦
 * SEND_WAIT_TIME		外送等待時間
 * SEND_MIN_AMOUNT		外送最小金額
 */
public class ShopMemData implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String shopId;
	private String userPw;	
	private String coName;	
	private String coNum;	
	private String coPeople;	
	private String addrL;	
	private String addrLCity;	
	private String addrLCanton;
	private String addrLDetail;		
	private String addrC;	
	private String addrCCity;
	private String addrCCanton;
	private String addrCDetail;	
	private String tel;	
	private String mobilePhone;	
	private String telOrder;	
	private String email;	
	//private String myShopTypeId;	
	private Date crDate;	
	private Date beginDate;	
	private Date endDate;		
	private int shopLevel;
	private String source;	
	private double addrCLat;
	private double addrCLng;	
	private String invoiceType;
	private String identNum;
	private String invoiceName;		
	private String shopParameter; 
	private String emailAuth;
	private String mobileParameter;
	private String phoneAuth;
	private String sendFunction;
	private String sendOnlineOrder;
	private String sendOrderWay;
	private String sendEmail;
	private String sendSms;
	private String sendAndroid;
	private String sendWaitTime;
	private String sendMinAmount;

	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getCoName() {
		return coName;
	}
	public void setCoName(String coName) {
		this.coName = coName;
	}
	public String getCoNum() {
		return coNum;
	}
	public void setCoNum(String coNum) {
		this.coNum = coNum;
	}
	public String getCoPeople() {
		return coPeople;
	}
	public void setCoPeople(String coPeople) {
		this.coPeople = coPeople;
	}
	public String getAddrL() {
		return addrL;
	}
	public void setAddrL(String addrL) {
		this.addrL = addrL;
	}
	public String getAddrC() {
		return addrC;
	}
	public void setAddrC(String addrC) {
		this.addrC = addrC;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getTelOrder() {
		return telOrder;
	}
	public void setTelOrder(String telOrder) {
		this.telOrder = telOrder;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/*
	public String getMyShopTypeId() {
		return myShopTypeId;
	}
	public void setMyShopTypeId(String myShopTypeId) {				
		this.myShopTypeId =  myShopTypeId;		
	}
	*/
	public Date getCrDate() {
		return crDate;
	}
	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getShopLevel() {
		return shopLevel;
	}
	public void setShopLevel(int shopLevel) {
		this.shopLevel = shopLevel;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	public double getAddrCLat() {
		return addrCLat;
	}
	public void setAddrCLat(double addrCLat) {
		this.addrCLat = addrCLat;
	}
	public double getAddrCLng() {
		return addrCLng;
	}
	public void setAddrCLng(double addrCLng) {
		this.addrCLng = addrCLng;
	}


	public String getAddrLCity() {
		return addrLCity;
	}
	public void setAddrLCity(String addrLCity) {
		this.addrLCity = addrLCity;
	}
	public String getAddrLCanton() {
		return addrLCanton;
	}
	public void setAddrLCanton(String addrLCanton) {
		this.addrLCanton = addrLCanton;
	}
	public String getAddrLDetail() {
		return addrLDetail;
	}
	public void setAddrLDetail(String addrLDetail) {
		this.addrLDetail = addrLDetail;
	}
	public String getAddrCCity() {
		return addrCCity;
	}
	public void setAddrCCity(String addrCCity) {
		this.addrCCity = addrCCity;
	}
	public String getAddrCCanton() {
		return addrCCanton;
	}
	public void setAddrCCanton(String addrCCanton) {
		this.addrCCanton = addrCCanton;
	}
	public String getAddrCDetail() {
		return addrCDetail;
	}
	public void setAddrCDetail(String addrCDetail) {
		this.addrCDetail = addrCDetail;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getIdentNum() {
		return identNum;
	}
	public void setIdentNum(String identNum) {
		this.identNum = identNum;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getShopParameter() {
		return shopParameter;
	}
	public void setShopParameter(String shopParameter) {
		this.shopParameter = shopParameter;
	}
	public String getEmailAuth() {
		return emailAuth;
	}
	public void setEmailAuth(String emailAuth) {
		this.emailAuth = emailAuth;
	}
	public String getMobileParameter() {
		return mobileParameter;
	}
	public void setMobileParameter(String mobileParameter) {
		this.mobileParameter = mobileParameter;
	}
	public String getPhoneAuth() {
		return phoneAuth;
	}
	public void setPhoneAuth(String phoneAuth) {
		this.phoneAuth = phoneAuth;
	}
	public String getSendFunction() {
		return sendFunction;
	}
	public void setSendFunction(String sendFunction) {
		this.sendFunction = sendFunction;
	}
	public String getSendOnlineOrder() {
		return sendOnlineOrder;
	}
	public void setSendOnlineOrder(String sendOnlineOrder) {
		this.sendOnlineOrder = sendOnlineOrder;
	}
	public String getSendOrderWay() {
		return sendOrderWay;
	}
	public void setSendOrderWay(String sendOrderWay) {
		this.sendOrderWay = sendOrderWay;
	}
	public String getSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	public String getSendSms() {
		return sendSms;
	}
	public void setSendSms(String sendSms) {
		this.sendSms = sendSms;
	}
	public String getSendAndroid() {
		return sendAndroid;
	}
	public void setSendAndroid(String sendAndroid) {
		this.sendAndroid = sendAndroid;
	}
	public String getSendWaitTime() {
		return sendWaitTime;
	}
	public void setSendWaitTime(String sendWaitTime) {
		this.sendWaitTime = sendWaitTime;
	}
	public String getSendMinAmount() {
		return sendMinAmount;
	}
	public void setSendMinAmount(String sendMinAmount) {
		this.sendMinAmount = sendMinAmount;
	}
	
	
	
	
}
