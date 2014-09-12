package com.order.mode;

import java.io.Serializable;
import java.util.Date;

/** 公休日管理
 *  HOLI_ID					編號
 *  SHOP_ID					店家代號
 *  HOLI_DATE				公休日
 *  UPDATE_DATE				更新時間
 */
public class ShopHoliDay implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String holiId;
	private String shopId;
	private String holiDate;
	private Date updateDate;
	
	
	public String getHoliId() {
		return holiId;
	}
	public void setHoliId(String holiId) {
		this.holiId = holiId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getHoliDate() {
		return holiDate;
	}
	public void setHoliDate(String holiDate) {
		this.holiDate = holiDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
	
	
	
	
	
	

}
