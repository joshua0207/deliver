package com.order.mode;

import java.io.Serializable;
import java.util.Date;


/**
   店家介紹、logo、長方型大圖
 * SHOP_INFO_ID 		編號
 * SHOP_ID 				店家代號
 * SHOP_CONTENT			店家詳述
 * SHOP_ACTIVITY_NEWS	活動資訊
 * SHOP_MEMO			店家小叮嚀
 * SHOP_INFO_DATE		店家資訊上傳日期
 * SHOP_LOGO_PATH		店家LOGO圖片檔名
 * SHOP_LOGO_DATE		LOGO圖片上傳日期
 * SHOP_BIG_PATH		長方型大圖照片檔名
 * SHOP_BIG_DATE		長方型大圖上傳日期
 */
public class ShopIntroducePic implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String shopInfoId;
	private String shopId;
	private String shopContent;
	private String shopActivityNews;
	private String shopMemo;
	private Date shopInfoDate;
	private String shopLogoPath;
	private Date shopLogoDate;
	private String shopBigPath;
	private Date shopBigDate;
	
	
	public String getShopInfoId() {
		return shopInfoId;
	}
	public void setShopInfoId(String shopInfoId) {
		this.shopInfoId = shopInfoId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopContent() {
		return shopContent;
	}
	public void setShopContent(String shopContent) {
		this.shopContent = shopContent;
	}
	public String getShopActivityNews() {
		return shopActivityNews;
	}
	public void setShopActivityNews(String shopActivityNews) {
		this.shopActivityNews = shopActivityNews;
	}
	public String getShopMemo() {
		return shopMemo;
	}
	public void setShopMemo(String shopMemo) {
		this.shopMemo = shopMemo;
	}
	public Date getShopInfoDate() {
		return shopInfoDate;
	}
	public void setShopInfoDate(Date shopInfoDate) {
		this.shopInfoDate = shopInfoDate;
	}
	public String getShopLogoPath() {
		return shopLogoPath;
	}
	public void setShopLogoPath(String shopLogoPath) {
		this.shopLogoPath = shopLogoPath;
	}
	public Date getShopLogoDate() {
		return shopLogoDate;
	}
	public void setShopLogoDate(Date shopLogoDate) {
		this.shopLogoDate = shopLogoDate;
	}
	public String getShopBigPath() {
		return shopBigPath;
	}
	public void setShopBigPath(String shopBigPath) {
		this.shopBigPath = shopBigPath;
	}
	public Date getShopBigDate() {
		return shopBigDate;
	}
	public void setShopBigDate(Date shopBigDate) {
		this.shopBigDate = shopBigDate;
	}
	
	
	
	
	
	
	
}
