package com.order.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.order.mode.vo.LoginAdminVO;
import com.order.mode.vo.ShopBaseManagerVO;
import com.order.service.WebSettingService;
import com.order.util.ImageResizeUtil;
import com.order.util.SysParameterUtil;
import com.order.util.UploadUtil;



@Controller
@RequestMapping("/websetting")
public class WebFunctionSettingController extends BaseController {

	@Autowired
	private WebSettingService webSettingService;
	
	@Autowired
	private MessageSource lng;
	
	@Autowired
	private SysParameterUtil sys;
	
	@Autowired
	private UploadUtil upload;
	
	@Autowired
	private ImageResizeUtil image;
	
	
	//外送功能設定首頁
	@RequestMapping(value = "/shop/sendsetting", method = RequestMethod.GET)
	public String SendSetting(Model model, HttpServletRequest req, HttpSession s)  throws Exception {
	
		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = StringUtils.trimToEmpty(vo.getShopId());
		
		//查詢店家外送功能設定資料
		ShopBaseManagerVO shopBaseVO = webSettingService.querySendSettingByShopId(shopId);
		model.addAttribute("shopBaseVO", shopBaseVO);
		
		return "shopsendsetting/webSendSetting";
	}
	
	
	//儲存外送功能設定
	@RequestMapping(value = "/shop/savesendsetting", method = RequestMethod.POST)
	public String saveSendSetting(Model model, @RequestParam String sendFlag, @RequestParam String sendOnlineOrder,
			@RequestParam String sendLeadTime, @RequestParam String sendMiniMoney,
			HttpServletRequest req, HttpSession s, Locale locale)  throws Exception {
		
		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = StringUtils.trimToEmpty(vo.getShopId());
		String ref = "";
		
		//查詢外送功能設定是否新增過-店家主檔是否有此筆店家
		boolean isAdd = webSettingService.querySendSettingByShopIdChk(shopId);
		if(isAdd){
			//儲存更新
			boolean isUp = webSettingService.updateSendSetting(sendFlag, sendOnlineOrder, sendLeadTime, sendMiniMoney, shopId);
			if(isUp){
				ref = lng.getMessage("MSG0017", null, locale);
			}else{
				ref = lng.getMessage("MSG0018", null, locale);
			}
			
		}else{
			//查不到此店家
			ref = lng.getMessage("MSG0049", null, locale);
		}
		
		//查詢店家外送功能設定資料
		ShopBaseManagerVO shopBaseVO = webSettingService.querySendSettingByShopId(shopId);
		
		model.addAttribute("msg", ref);
		model.addAttribute("shopBaseVO", shopBaseVO);
		
		return "shopsendsetting/webSendSetting";
	}
	
	
	//訂單通知方式設定首頁
	@RequestMapping(value = "/shop/ordernotice", method = RequestMethod.GET)
	public String OrderNotice(Model model, HttpServletRequest req, HttpSession s)  throws Exception {
	
		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = StringUtils.trimToEmpty(vo.getShopId());
		
		//查詢訂單通知方式設定資料
		ShopBaseManagerVO shopBaseVO = webSettingService.querySendOrderNoticeByShopId(shopId);
		model.addAttribute("shopBaseVO", shopBaseVO);
		
		return "shopordernotice/shopOrderNotice";
	}
	
	
	//儲存訂單通知方式設定
	@RequestMapping(value = "/shop/saveordernotice", method = RequestMethod.POST)
	public String SaveOrderNotice(Model model, @RequestParam(required=false) String [] notice,
			@RequestParam(required=false) String mail, @RequestParam(required=false) String phone,
			HttpServletRequest req, HttpSession s, Locale locale)  throws Exception {
	
		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = StringUtils.trimToEmpty(vo.getShopId());
		String ref = "";
		
		//儲存訂單通知方式設定資料
		boolean isSave = webSettingService.updateSendOrderNotice(notice, mail, phone, shopId);
		if(isSave){
			ref = lng.getMessage("MSG0017", null, locale);
		}else{
			ref = lng.getMessage("MSG0018", null, locale);
		}
		
		//查詢訂單通知方式設定資料
		ShopBaseManagerVO shopBaseVO = webSettingService.querySendOrderNoticeByShopId(shopId);
		
		model.addAttribute("msg", ref);
		model.addAttribute("shopBaseVO", shopBaseVO);
		
		return "shopordernotice/shopOrderNotice";
	}
	
	
	
	
	
	

}