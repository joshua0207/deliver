package com.order.dao;

import java.util.Map;

import com.order.mode.ShopMemData;
import com.order.mode.vo.MyShopTypeIdVO;
import com.order.mode.vo.ShopBaseManagerVO;


public interface ShopBaseTrsDAO extends BaseTrsDAO<ShopMemData, Map<String, Object>> {
	
	
	/** 修改店家資料*/
	public Integer updateShopDataAll(ShopBaseManagerVO shopBaseManagerVO);
	
	/**刪除店家所擁有的店家類別*/
	public Integer deleteMyShopTypeId(String shopId);
	
	/**新增店家所擁有的店家類別*/
	public Integer insertMyShopTypeId(MyShopTypeIdVO myShopTypeIdVO);
	
	/** 更新店家密碼 */
	public Integer updatePassword(String shopId, String password, String newPassword);
	
	/** 新增店家資料*/
	public Integer createShopDataAll(ShopBaseManagerVO shopBaseManagerVO);
	
	
}