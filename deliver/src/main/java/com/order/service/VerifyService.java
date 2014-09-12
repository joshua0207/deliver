package com.order.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.order.mode.ShopMemData;
import com.order.mode.vo.ShopBaseManagerVO;
import com.order.mode.vo.SmsPOVO;



public interface VerifyService {
	
	final Log log = LogFactory.getLog(VerifyService.class);
	
	/** 查詢店家認證SHOP_PARAMETER, EMAIL_AUTH */
	public ShopBaseManagerVO queryParameterAuthByShopId(String shopId);
	
	/** 修改會員EMAIL已認證通過EMAIL_AUTH為Y */
	public Boolean updateShopBaseAuth(ShopMemData shopMemData);
	
	/** 查詢該簡訊是否存在DB中*/
	public String [] querySmsByMsgIdPhoneNum(String msgId, String phoneNum);
	
	/** 更新簡訊最新狀態資料 */
	public String updateSmsData(SmsPOVO smsPOVO);
	
	/** 更新訂單主檔簡訊通知欄位*/
	public String updateCustomPOMastMsgInfoTime(String orderID, String finishTime);
	
	/** 新增簡訊傳送狀態資料*/
	public String insertCustomPOSmsData(SmsPOVO smsPOVO);
	
	/** 修改會員手機已認證通過PHONE_AUTH為Y */
	public Boolean updateShopBasePhoneAuth(ShopMemData shopMemData);
	
}
