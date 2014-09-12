package com.order.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.WebSettingQueryDAO;
import com.order.dao.WebSettingTrsDAO;
import com.order.mode.ShopMemData;
import com.order.mode.vo.ShopBaseManagerVO;
import com.order.service.WebSettingService;



@Service
public class WebSettingServiceImpl implements WebSettingService {
	
	@Autowired
	private WebSettingQueryDAO webSettingQueryDAO;
	
	@Autowired
	private WebSettingTrsDAO webSettingTrsDAO;
	
//	@Resource
//	private DataSourceTransactionManager transactionManager;
//	private DefaultTransactionDefinition def;
	
	
	@Override
	public Boolean querySendSettingByShopIdChk(String shopId) {
		
		boolean isCorrect = false; 
	    
    	int count  = webSettingQueryDAO.findSendSettingByShopIdChk(shopId);
    	if(count >0 ){
			isCorrect = true;
		}
		return isCorrect;
	}


	@Override
	public Boolean updateSendSetting(String sendFlag, String sendOnlineOrder, String sendLeadTime, String sendMiniMoney, String shopId) {
		
		boolean isCorrect = false; 
		
		ShopMemData shopMemData = new ShopMemData();
		shopMemData.setShopId(shopId);
		shopMemData.setSendFunction(sendFlag);
		shopMemData.setSendOnlineOrder(sendOnlineOrder);
		shopMemData.setSendWaitTime(sendLeadTime);
		shopMemData.setSendMinAmount(sendMiniMoney);
    	int count  = webSettingTrsDAO.updateSendSetting(shopMemData);
    	if(count >0 ){
			isCorrect = true;
		}
    	return isCorrect;
	}


	@Override
	public ShopBaseManagerVO querySendSettingByShopId(String shopId) {
		
		return webSettingQueryDAO.findSendSettingByShopId(shopId);
	}


	@Override
	public Boolean updateSendOrderNotice(String [] notice, String mail, String phone, String shopId) {
		
		boolean isCorrect = false; 
		
		ShopMemData shopMemData = new ShopMemData();
		shopMemData.setShopId(shopId);
		String noticeStr = "";
		if(notice != null && notice.length >0 ){
			for(int i=0;i<notice.length;i++){
				noticeStr += notice[i] + ","; //外送收取訂單方式多選
			}
			if(noticeStr.indexOf("E") != -1){//e-mail勾選才須存Email
				shopMemData.setSendEmail(mail);
			}else{
				shopMemData.setSendEmail("");
			}
			if(noticeStr.indexOf("S") != -1){//簡訊勾選才須存簡訊手機號碼
				shopMemData.setSendSms(phone);
			}else{
				shopMemData.setSendSms("");
			}
			if(noticeStr.indexOf("M") != -1){//行動裝置勾選才須存行動裝置欄位為Y
				shopMemData.setSendAndroid("Y");
			}else{
				shopMemData.setSendAndroid("N");
			}
			shopMemData.setSendOrderWay(noticeStr);
		}else{
			shopMemData.setSendOrderWay(noticeStr);
			shopMemData.setSendEmail("");
			shopMemData.setSendSms("");
			shopMemData.setSendAndroid("N");
		}
		
    	int count  = webSettingTrsDAO.updateSendOrderNotice(shopMemData);
    	if(count >0 ){
			isCorrect = true;
		}
    	
    	return isCorrect;
	}


	@Override
	public ShopBaseManagerVO querySendOrderNoticeByShopId(String shopId) {
		
		return webSettingQueryDAO.findSendOrderNoticeByShopId(shopId);
	}


	
	
	
	
	
	
	
}
