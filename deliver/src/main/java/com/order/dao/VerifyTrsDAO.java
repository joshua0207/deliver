package com.order.dao;

import java.util.Map;

import com.order.mode.ShopMemData;
import com.order.mode.vo.ShopBaseManagerVO;
import com.order.mode.vo.SmsPOVO;


public interface VerifyTrsDAO extends BaseTrsDAO<ShopBaseManagerVO, Map<String, Object>> {
	
	
	/** 修改會員EMAIL已認證通過EMAIL_AUTH為Y */
	public Boolean updateShopBaseAuth(ShopMemData shopMemData);
	
	/** 更新簡訊最新狀態資料 */
	public String updateSmsData(SmsPOVO smsPOVO);
	
	/** 更新訂單主檔簡訊通知欄位*/
	public String updateCustomPOMastMsgInfoTime(String orderID, String finishTime);
	
	/** 新增簡訊傳送狀態資料*/
	public String insertCustomPOSmsData(SmsPOVO smsPOVO);
	
	/** 修改會員手機已認證通過PHONE_AUTH為Y */
	public Boolean updateShopBasePhoneAuth(ShopMemData shopMemData);
	
	
}