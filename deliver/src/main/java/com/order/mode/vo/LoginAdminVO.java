package com.order.mode.vo;
import java.io.Serializable;
import java.util.Date;


/**
 @author Joshua
 
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
 * EMAIL_AUTH  Email認證
 * PHONE_AUTH  手機認證
 * ADDR_C_LNG		店家地址經度
 * ADDR_C_LAT		店家地址緯度
 * SERVICE_SCOPE_TYPE	MAP地圖服務搜尋類型

	外送功能開啟	SEND_FUNCTION
	外送線上訂購功能開啟	SEND_ONLINE_ORDER
	外送訂單用E-mail	SEND_EMAIL
	外送訂單用SMS	SEND_SMS
	外送訂單用FAX	SEND_FAX
	外送不使用金流訂購電話	SEND_ORDER_PHONE
	外送不使用金流備註事項	SEND_MEMO

 */

public class LoginAdminVO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String shopId;
	private String userPw;	
	private String coName;	
	private String coNum;	
	private String coPeople;	
	private String addrL;	
	private String addrC;	
	private String tel;	
	private String mobilePhone;	
	private String telOrder;	
	private String email;	
//	private String myShopTypeId;	
	private Date crDate;	
	private Date beginDate;	
	private Date endDate;		
	private int shopLevel;
	private String source;	
	private String emailAuth;
	private String phoneAuth;
	private String addrCLng;
	private String addrCLat;
	private String serviceScopeType;

	private String sendFunction;	
	private String sendOnlineOrder;	
	private String sendEmail;	
	private String sendSms;	
	private String sendFax;	
	
	private String sendOrderPhone;
	private String sendMemo;	
	
	
	//以下兩筆資料loginShopLevelName、loginShopEndDate並不存在於店家資料表
	//會寫在此vo主要是為了方便管理session,
	private String loginShopLevelName;
	private String loginShopEndDate;
	
	
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
	
	/*public String getMyShopTypeId() {
		return myShopTypeId;
	}
	public void setMyShopTypeId(String myShopTypeId) {				
		this.myShopTypeId =  myShopTypeId;		
	}*/
	
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
	public String getEmailAuth() {
		return emailAuth;
	}
	public void setEmailAuth(String emailAuth) {
		this.emailAuth = emailAuth;
	}
	public String getPhoneAuth() {
		return phoneAuth;
	}
	public void setPhoneAuth(String phoneAuth) {
		this.phoneAuth = phoneAuth;
	}
	public String getAddrCLng() {
		return addrCLng;
	}
	public void setAddrCLng(String addrCLng) {
		this.addrCLng = addrCLng;
	}
	public String getAddrCLat() {
		return addrCLat;
	}
	public void setAddrCLat(String addrCLat) {
		this.addrCLat = addrCLat;
	}
	public String getServiceScopeType() {
		return serviceScopeType;
	}
	public void setServiceScopeType(String serviceScopeType) {
		this.serviceScopeType = serviceScopeType;
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
	public String getSendFax() {
		return sendFax;
	}
	public void setSendFax(String sendFax) {
		this.sendFax = sendFax;
	}
	public String getSendOrderPhone() {
		return sendOrderPhone;
	}
	public void setSendOrderPhone(String sendOrderPhone) {
		this.sendOrderPhone = sendOrderPhone;
	}
	public String getSendMemo() {
		return sendMemo;
	}
	public void setSendMemo(String sendMemo) {
		this.sendMemo = sendMemo;
	}
	
	public String getLoginShopLevelName() {
		return loginShopLevelName;
	}
	public void setLoginShopLevelName(String loginShopLevelName) {
		this.loginShopLevelName = loginShopLevelName;
	}
	public String getLoginShopEndDate() {
		return loginShopEndDate;
	}
	public void setLoginShopEndDate(String loginShopEndDate) {
		this.loginShopEndDate = loginShopEndDate;
	}

	
}
