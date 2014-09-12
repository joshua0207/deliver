package com.order.dao;

import java.util.List;
import java.util.Map;

import com.order.mode.MyShopTypeId;
import com.order.mode.ShopMemData;
import com.order.mode.ShopType;
import com.order.mode.vo.ShopBaseManagerVO;


public interface ShopBaseQueryDAO extends BaseQueryDAO<ShopMemData, Map<String, Object>> {
	
	/** 查詢店家所有資料 */
	public ShopMemData findAllByIdPassword(String shopId, String password) throws DAOObjectNotFoundException;
	
	/** 查詢店家所有資料 */
	public ShopBaseManagerVO findAllByIdPasswordForVO(String shopId, String password) throws DAOObjectNotFoundException;
	
	/** 查詢所有店家類別*/
	public List<ShopType> findAllShopType() throws DAOObjectNotFoundException;
	
	/** 查詢店家所擁有的店家類別*/
	public List<MyShopTypeId> findShopTypeByShopId(String shopId) throws DAOObjectNotFoundException;
	
	/** 檢查店家名稱密碼 */
	public ShopMemData chkShopLogin(String shopId, String password) throws DAOObjectNotFoundException;
	
	/** 檢查確認舊密碼 */
	public Integer chkOldPassword(String shopId, String password) throws DAOObjectNotFoundException;
	
	/** 檢查確認店家Id是否已有人註冊*/
	public Integer checkShopId(String shopId) throws DAOObjectNotFoundException;
	
	
	
}