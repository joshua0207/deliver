package com.order.dao;

import java.util.List;
import java.util.Map;

import com.order.mode.ShopAllPicture;
import com.order.mode.ShopIntroducePic;


public interface ShopIntroPicQueryDAO extends BaseQueryDAO<ShopIntroducePic, Map<String, Object>> {
	
	
	/** 查詢店家介紹資料及Logo和長方型大圖所有資料*/
	@Override
	public ShopIntroducePic findByKey(Map<String, Object> k) throws DAOObjectNotFoundException;
	
	/** 查詢店家介紹是否新增過*/
	public Integer findByShopIsAdd(String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢店家介紹資料*/
	public List<ShopIntroducePic> findShopInfoByShopId(String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢店家Logo資料*/
	public List<ShopIntroducePic> findShopLogoByShopId(String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢店家長方型大圖資料*/
	public List<ShopIntroducePic> findRectangularBigPicByShopId(String shopId) throws DAOObjectNotFoundException;
	
	/** 查詢店家所有上傳照片資料*/
	public List<ShopAllPicture> findShopAllPicByShopId(String shopId) throws DAOObjectNotFoundException;
	
}