package com.order.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.order.dao.ShopIntroPicTrsDAO;
import com.order.mode.ShopAllPicture;
import com.order.mode.ShopIntroducePic;


@Repository("shopIntroPicTrsDAOTarget")
public class ShopIntroPicTrsDAOImpl extends BaseTrsDAOImpl<ShopIntroducePic, Map<String, Object>> implements ShopIntroPicTrsDAO {

	@Override
	public Integer createShopIntroPic(ShopIntroducePic shopIntroducePic) {
		
		return this.getSqlSession().insert(getNameSpace() + ".createShopIntroPic", shopIntroducePic);
	}

	@Override
	public Integer updateShopIntroPic(ShopIntroducePic shopIntroducePic) {
		
		return this.getSqlSession().update(getNameSpace() + ".updateShopIntroPic", shopIntroducePic);
	}

	@Override
	public Integer createShopLogoPic(ShopIntroducePic shopIntroducePic) {
		
		return this.getSqlSession().insert(getNameSpace() + ".createShopLogoPic", shopIntroducePic);
	}

	@Override
	public Integer updateShopLogoPic(ShopIntroducePic shopIntroducePic) {
		
		return this.getSqlSession().update(getNameSpace() + ".updateShopLogoPic", shopIntroducePic);
	}

	@Override
	public Integer createRectangularBigPic(ShopIntroducePic shopIntroducePic) {
		
		
		return this.getSqlSession().insert(getNameSpace() + ".createRectangularBigPic", shopIntroducePic);
	}

	@Override
	public Integer updateRectangularBigPic(ShopIntroducePic shopIntroducePic) {
	
		
		return this.getSqlSession().update(getNameSpace() + ".updateRectangularBigPic", shopIntroducePic);
	}

	@Override
	public Integer createShopAllPic(ShopAllPicture shopallpicture) {
		
		return this.getSqlSession().insert(getNameSpace() + ".createShopAllPic", shopallpicture);
	}

	@Override
	public Integer updateShopPicMessage(ShopAllPicture shopAllPicture) {
		
		return this.getSqlSession().update(getNameSpace() + ".updateShopPicMessage", shopAllPicture);
	}

	@Override
	public Integer deleteShopPic(ShopAllPicture shopAllPicture) {
		
		return this.getSqlSession().update(getNameSpace() + ".deleteShopPic", shopAllPicture);
	}

	
	

	
}