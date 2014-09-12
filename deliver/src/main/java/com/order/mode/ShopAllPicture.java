package com.order.mode;

import java.io.Serializable;
import java.util.Date;


/**
   店家照片上傳管理
 * UPLO_ID		 		編號
 * SHOP_ID 				店家代號
 * UPLO_PATH			照片檔名
 * UPLO_DESCRP			照片簡述說明
 * UPLO_TRANS_DATE		上傳日期
 */
public class ShopAllPicture implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String uploId;
	private String shopId;
	private String uploPath;
	private String uploDescrp;
	private Date uploTransDate;
	
	
	public String getUploId() {
		return uploId;
	}
	public void setUploId(String uploId) {
		this.uploId = uploId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getUploPath() {
		return uploPath;
	}
	public void setUploPath(String uploPath) {
		this.uploPath = uploPath;
	}
	public String getUploDescrp() {
		return uploDescrp;
	}
	public void setUploDescrp(String uploDescrp) {
		this.uploDescrp = uploDescrp;
	}
	public Date getUploTransDate() {
		return uploTransDate;
	}
	public void setUploTransDate(Date uploTransDate) {
		this.uploTransDate = uploTransDate;
	}
	
	
	
	
	
	
	
	
}
