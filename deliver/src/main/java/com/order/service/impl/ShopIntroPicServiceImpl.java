package com.order.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.ShopIntroPicQueryDAO;
import com.order.dao.ShopIntroPicTrsDAO;
import com.order.mode.ShopAllPicture;
import com.order.mode.ShopIntroducePic;
import com.order.service.ShopIntroPicService;



@Service
public class ShopIntroPicServiceImpl implements ShopIntroPicService {
	
	@Autowired
	private ShopIntroPicQueryDAO shopIntroPicQueryDAO;
	
	@Autowired
	private ShopIntroPicTrsDAO shopIntroPicTrsDAO;
	
//	@Resource
//	private DataSourceTransactionManager transactionManager;
//	private DefaultTransactionDefinition def;
	
	
	@Override
	public ShopIntroducePic queryByKey(String shopId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopId", shopId);
		return shopIntroPicQueryDAO.findByKey(map);
	}
	
	@Override
	public Boolean queryByShopIsAdd(String shopId) {
		
		boolean isCorrect = false; 
	    
    	int count  = shopIntroPicQueryDAO.findByShopIsAdd(shopId);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}

	@Override
	public ShopIntroducePic queryShopInfoByShopId(String shopId) {
		ShopIntroducePic shopIntroducePic = new ShopIntroducePic();
		List<ShopIntroducePic> shopIntroducePicList = shopIntroPicQueryDAO.findShopInfoByShopId(shopId);
		if(shopIntroducePicList != null && shopIntroducePicList.size() >0){
			shopIntroducePic = shopIntroducePicList.get(0);
		}
		return shopIntroducePic;
	}

	@Override
	public Boolean createShopIntroPic(ShopIntroducePic shopIntroducePic) {
		
		boolean isCorrect = false; 
		    
    	int count  = shopIntroPicTrsDAO.createShopIntroPic(shopIntroducePic);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}

	@Override
	public Boolean updateShopIntroPic(ShopIntroducePic shopIntroducePic) {
		
		boolean isCorrect = false; 
	    
    	int count  = shopIntroPicTrsDAO.updateShopIntroPic(shopIntroducePic);
    	if(count >0 ){
			isCorrect = true;
		}
    	return isCorrect;
	}

	@Override
	public ShopIntroducePic queryShopLogoByShopId(String shopId) {
		ShopIntroducePic shopIntroducePic = new ShopIntroducePic();
		List<ShopIntroducePic> shopIntroducePicList = shopIntroPicQueryDAO.findShopLogoByShopId(shopId);
		if(shopIntroducePicList != null && shopIntroducePicList.size() >0){
			shopIntroducePic = shopIntroducePicList.get(0);
		}
		return shopIntroducePic;
	}

	@Override
	public Boolean createShopLogoPic(ShopIntroducePic shopIntroducePic) {
		
		boolean isCorrect = false; 
	    
    	int count  = shopIntroPicTrsDAO.createShopLogoPic(shopIntroducePic);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}

	@Override
	public Boolean updateShopLogoPic(ShopIntroducePic shopIntroducePic) {
		
		boolean isCorrect = false; 
	    
    	int count  = shopIntroPicTrsDAO.updateShopLogoPic(shopIntroducePic);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}

	@Override
	public ShopIntroducePic queryRectangularBigPicByShopId(String shopId) {
		ShopIntroducePic shopIntroducePic = new ShopIntroducePic();
		List<ShopIntroducePic> shopIntroducePicList = shopIntroPicQueryDAO.findRectangularBigPicByShopId(shopId);
		if(shopIntroducePicList != null && shopIntroducePicList.size() >0){
			shopIntroducePic = shopIntroducePicList.get(0);
		}
		return shopIntroducePic;
	}

	@Override
	public Boolean createRectangularBigPic(ShopIntroducePic shopIntroducePic) {
		
		boolean isCorrect = false; 
	    
    	int count  = shopIntroPicTrsDAO.createRectangularBigPic(shopIntroducePic);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}

	@Override
	public Boolean updateRectangularBigPic(ShopIntroducePic shopIntroducePic) {
		
		boolean isCorrect = false; 
	    
    	int count  = shopIntroPicTrsDAO.updateRectangularBigPic(shopIntroducePic);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}

	@Override
	public Boolean createShopAllPic(ShopAllPicture shopallpicture) {
		
		boolean isCorrect = false; 
	    
    	int count  = shopIntroPicTrsDAO.createShopAllPic(shopallpicture);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}

	@Override
	public List<ShopAllPicture> queryShopAllPicByShopId(String shopId) {
		
		
		return shopIntroPicQueryDAO.findShopAllPicByShopId(shopId);
	}

	@Override
	public Boolean updateShopPicMessage(ShopAllPicture shopAllPicture) {
		
		boolean isCorrect = false; 
	    
    	int count  = shopIntroPicTrsDAO.updateShopPicMessage(shopAllPicture);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}

	@Override
	public Boolean deleteShopPic(ShopAllPicture shopAllPicture) {
		
		boolean isCorrect = false; 
	    
    	int count  = shopIntroPicTrsDAO.deleteShopPic(shopAllPicture);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}
	
	
	


	
	
}
