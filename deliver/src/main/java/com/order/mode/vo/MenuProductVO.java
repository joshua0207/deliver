package com.order.mode.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**	商品管理(MENU_PRODUCT)
 *  
 *  MENU_PROD_ID			產品編號
 * 	MENU_CAT_ID				分類編號
 *  SHOP_ID					店家代號
 *  MENU_PROD_NAME			產品名稱
 *  MENU_PROD_COMM			產品簡述
 *  MENU_PROD_COMM_DETAIL	產品詳述
 *  PIC_NAME				圖片名稱
 *  MENU_PRICE				價格
 *  ONSALE_PRICE			特價
 *  SORT					排序
 *  HOT_PRODUCT				熱門商品
 *  HIDE_FLAG				是否隱藏
 *  UPDATE_DATE				更新時間
 *  
 *  ORDER_COUNT				查看SHOP_PO_DETL (店家訂單明細)是否有定過單，如有下訂過count大於0，則此筆商品不可刪除。
 */
public class MenuProductVO implements Serializable {
	
	final Log log = LogFactory.getLog(getClass());
	
	private static final long serialVersionUID = 1L;
	
	private String menuProdId;
	private String menuCatId;
	private String shopId;
	private String menuProdName;
	private String menuProdComm;
	private String menuProdCommDetail;
	private String picName;
	private Integer menuPrice;
	private Integer onsalePrice;
	private Integer sort;
	private String hotProduct;
	private String hideFlag;
	private Date updateDate;
	
	private Integer orderCount;
	
	private String picturePath;
	
	private String picturePath162;
	
	
	/**
	 * 圖片路徑+圖片檔名 90
	 * @param serverName	網域，用來判斷是否在正式機或local
	 * @param domainPicPath 取得存放路徑域名
	 * @param directory	menu產品照片資料夾名稱目錄
	 * @return
	 */
	public String getPicturePath(String serverName, String domainPicPath, String directory) {
		if(serverName.equals("localhost")){
			domainPicPath = "http://localhost:8080";
		}
		StringBuilder sb = new StringBuilder();
		try {
			sb.append(domainPicPath);
			sb.append("/img/");
			sb.append(this.shopId);
			sb.append(directory);
			sb.append(this.picName+"_menu_90.jpg");	
		} catch (Exception e) {
			log.error("getPicturePath error:" +sb.toString()+ e);
		}
		
		picturePath = sb.toString();
		
		return picturePath;
	}
	
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	
	
	public String getPicturePath800(String serverName, String domainPicPath, String directory) {
		
		if(serverName.equals("localhost")){
			domainPicPath = "http://localhost:8080";
		}
		StringBuilder sb = new StringBuilder();
		try {
			sb.append(domainPicPath);
			sb.append("/img/");
			sb.append(this.shopId);
			sb.append(directory);
			sb.append(this.picName+"_menu_800.jpg");	
		} catch (Exception e) {
			log.error("getPicturePath error:" +sb.toString()+ e);
		}
		
		picturePath162 = sb.toString();
		
		return picturePath162;
	}

	public void setPicturePath162(String picturePath162) {
		this.picturePath162 = picturePath162;
	}

	
	
	
	public String getMenuProdId() {
		return menuProdId;
	}

	public void setMenuProdId(String menuProdId) {
		this.menuProdId = menuProdId;
	}

	public String getMenuCatId() {
		return menuCatId;
	}

	public void setMenuCatId(String menuCatId) {
		this.menuCatId = menuCatId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getMenuProdName() {
		return menuProdName;
	}

	public void setMenuProdName(String menuProdName) {
		this.menuProdName = menuProdName;
	}

	public String getMenuProdComm() {
		return menuProdComm;
	}

	public void setMenuProdComm(String menuProdComm) {
		this.menuProdComm = menuProdComm;
	}

	public String getMenuProdCommDetail() {
		return menuProdCommDetail;
	}

	public void setMenuProdCommDetail(String menuProdCommDetail) {
		this.menuProdCommDetail = menuProdCommDetail;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public Integer getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(Integer menuPrice) {
		this.menuPrice = menuPrice;
	}

	public Integer getOnsalePrice() {
		return onsalePrice;
	}

	public void setOnsalePrice(Integer onsalePrice) {
		this.onsalePrice = onsalePrice;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getHotProduct() {
		return hotProduct;
	}

	public void setHotProduct(String hotProduct) {
		this.hotProduct = hotProduct;
	}

	public String getHideFlag() {
		return hideFlag;
	}

	public void setHideFlag(String hideFlag) {
		this.hideFlag = hideFlag;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Log getLog() {
		return log;
	}

	public String getPicturePath() {
		return picturePath;
	}
	
	
	
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	
	
	
	

	
}