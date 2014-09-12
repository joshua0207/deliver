package com.order.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.order.mode.ShopAllPicture;
import com.order.mode.ShopIntroducePic;



public interface ShopIntroPicService {
	
	final Log log = LogFactory.getLog(ShopIntroPicService.class);
	
	/** 查詢店家介紹資料及Logo和長方型大圖所有資料*/
	public ShopIntroducePic queryByKey(String shopId);
		
	/** 查詢店家介紹是否新增過*/
	public Boolean queryByShopIsAdd(String shopId);
	
	/** 查詢店家介紹資料*/
	public ShopIntroducePic queryShopInfoByShopId(String shopId);
	
	/** 新增店家介紹資料*/
	public Boolean createShopIntroPic(ShopIntroducePic shopIntroducePic);
	
	/** 修改店家介紹資料*/
	public Boolean updateShopIntroPic(ShopIntroducePic shopIntroducePic);
	
	/** 查詢店家Logo資料*/
	public ShopIntroducePic queryShopLogoByShopId(String shopId);
	
	/** 新增店家LOGO資料*/
	public Boolean createShopLogoPic(ShopIntroducePic shopIntroducePic);
	
	/** 修改店家LOGO資料*/
	public Boolean updateShopLogoPic(ShopIntroducePic shopIntroducePic);
	
	/** 查詢店家長方型大圖資料*/
	public ShopIntroducePic queryRectangularBigPicByShopId(String shopId);
	
	/** 新增店家長方型大圖資料*/
	public Boolean createRectangularBigPic(ShopIntroducePic shopIntroducePic);
	
	/** 修改店家長方型大圖資料*/
	public Boolean updateRectangularBigPic(ShopIntroducePic shopIntroducePic);
	
	/** 新增店家所有照片資料*/
	public Boolean createShopAllPic(ShopAllPicture shopallpicture);
	
	/** 查詢店家所有上傳照片資料*/
	public List<ShopAllPicture> queryShopAllPicByShopId(String shopId);
	
	/** 修改店家照片上傳管理照片說明資料*/
	public Boolean updateShopPicMessage(ShopAllPicture shopAllPicture);
	
	/** 刪除店家照片上傳管理照片資料*/
	public Boolean deleteShopPic(ShopAllPicture shopAllPicture);
	
}
