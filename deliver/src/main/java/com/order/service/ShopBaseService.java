package com.order.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.order.mode.MyShopTypeId;
import com.order.mode.ServiceScopeCircle;
import com.order.mode.ShopMemData;
import com.order.mode.ShopType;
import com.order.mode.vo.MyShopTypeIdVO;
import com.order.mode.vo.ShopBaseManagerVO;



public interface ShopBaseService {
	
	final Log log = LogFactory.getLog(ShopBaseService.class);
	
	/** 查詢店家所有資料 */
	public ShopMemData queryShopDataAll(String shopId, String password);
	
	/** 查詢店家所有資料 */
	public ShopBaseManagerVO queryShopDataAllForVO(String shopId, String password);
	
	/** 查詢所有店家類別*/
	public List<ShopType> queryShopTypeAll();
	
	/** 查詢店家所擁有的店家類別*/
	public List<MyShopTypeId> queryShopType(String shopId);
	
	/** 修改店家資料*/
	public Boolean updateShopDataAll(ShopBaseManagerVO shopBaseManagerVO, ArrayList<MyShopTypeIdVO> myShopTypeIdVoList);
	
	/** 檢查店家名稱密碼 */
	public ShopMemData chkShopLogin(String shopId, String password) ;
	
	/** 檢查確認舊密碼 */
	public Integer chkOldPassword(String shopId, String password);
	
	/** 更新店家密碼 */
	public Boolean updatePassword(String shopId, String password, String newPassword);
	
	/** 檢查確認店家Id是否已有人註冊*/
	public Boolean checkShopId(String shopId);
	
	/** 新增店家資料*/
	public Boolean createShopDataAll(ShopBaseManagerVO shopBaseManagerVO, ArrayList<MyShopTypeIdVO> myShopTypeIdVoList, ServiceScopeCircle scopeCircle);
	
	
}
