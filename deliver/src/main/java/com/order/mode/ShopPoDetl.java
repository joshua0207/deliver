package com.order.mode;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/**	SHOP_PO_DETL (店家訂單明細)
 *  SHOP_PO_DETL_ID			編號
 * 	ORDER_ID				訂購單編號
 *  SHOP_ID					店家代號
 *  ITEM_NAME				產品名稱
 *  ITEM_ID					產品代號
 *  ITEM_DETL				產品訂購細項
 *  QTY						訂購數量小計
 *  UNIT_PRICE				產品單價
 *  AMOUNT					訂購金額小計
 *  UPDATE_DATE				更新時間
 */
public class ShopPoDetl implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String shopPoDetlDd;
	private String orderId;
	private String shopId;
	private String itemName;
	private String itemId;
	private String itemDetl;
	private Integer qty;
	private Integer unitPrice;
	private Integer amount;
	private Date updateDate;
	
	
	public String getShopPoDetlDd() {
		return shopPoDetlDd;
	}
	public void setShopPoDetlDd(String shopPoDetlDd) {
		this.shopPoDetlDd = shopPoDetlDd;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemDetl() {
		return itemDetl;
	}
	public void setItemDetl(String itemDetl) {
		this.itemDetl = itemDetl;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	
	

	
}