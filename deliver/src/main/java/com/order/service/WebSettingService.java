package com.order.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.order.mode.vo.ShopBaseManagerVO;



public interface WebSettingService {
	
	final Log log = LogFactory.getLog(WebSettingService.class);
	
	/** 查詢外送功能設定是否新增過-店家主檔是否有此筆店家*/
	public Boolean querySendSettingByShopIdChk(String shopId);
	
	/** 更新外送功能設定資料
	 *	sendFlag 		是否開啟外送
	 *	sendOnlineOrder	外送線上訂購功能	
	 *	sendLeadTime	外送等待時間 
	 *	sendMiniMoney	 外送最小金額
	 */
	public Boolean updateSendSetting(String sendFlag, String sendOnlineOrder, String sendLeadTime, String sendMiniMoney, String shopId);

	
	/** 查詢外送功能設定資料 */
	public ShopBaseManagerVO querySendSettingByShopId(String shopId);
	
	/** 更新訂單通知方式設定資料
	 *	notice	外送收取訂單方式  
	 *	mail	e-mail勾選才須存Email
	 *	phone	簡訊勾選才須存簡訊手機號碼
	 */
	public Boolean updateSendOrderNotice(String [] notice, String mail, String phone, String shopId);
	
	/** 查詢訂單通知方式設定資料 */
	public ShopBaseManagerVO querySendOrderNoticeByShopId(String shopId);
	
}
