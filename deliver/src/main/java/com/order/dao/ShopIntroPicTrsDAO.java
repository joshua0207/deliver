package com.order.dao;

import java.util.Map;

import com.order.mode.ShopAllPicture;
import com.order.mode.ShopIntroducePic;


public interface ShopIntroPicTrsDAO extends BaseTrsDAO<ShopIntroducePic, Map<String, Object>> {
	
	
	/** 新增店家介紹資料*/
	public Integer createShopIntroPic(ShopIntroducePic shopIntroducePic);
	
	/** 修改店家介紹資料*/
	public Integer updateShopIntroPic(ShopIntroducePic shopIntroducePic);
	
	/** 新增店家LOGO資料*/
	public Integer createShopLogoPic(ShopIntroducePic shopIntroducePic);
	
	/** 修改店家LOGO資料*/
	public Integer updateShopLogoPic(ShopIntroducePic shopIntroducePic);
	
	/** 新增店家長方型大圖資料*/
	public Integer createRectangularBigPic(ShopIntroducePic shopIntroducePic);
	
	/** 修改店家長方型大圖資料*/
	public Integer updateRectangularBigPic(ShopIntroducePic shopIntroducePic);
	
	/** 新增店家所有照片資料*/
	public Integer createShopAllPic(ShopAllPicture shopallpicture);
	
	/** 修改店家照片上傳管理照片說明資料*/
	public Integer updateShopPicMessage(ShopAllPicture shopAllPicture);
	
	/** 刪除店家照片上傳管理照片資料*/
	public Integer deleteShopPic(ShopAllPicture shopAllPicture);
	
	
}