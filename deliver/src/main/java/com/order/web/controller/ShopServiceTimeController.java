package com.order.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.order.mode.SendTimeDay;
import com.order.mode.ServiceTimeDay;
import com.order.mode.ShopHoliDay;
import com.order.mode.vo.LoginAdminVO;
import com.order.service.ServiceTimeService;
import com.order.util.ImageResizeUtil;
import com.order.util.SysParameterUtil;
import com.order.util.UploadUtil;



@Controller
@RequestMapping("/servicetime")
public class ShopServiceTimeController extends BaseController {

	@Autowired
	private ServiceTimeService serviceTimeService;
	
	@Autowired
	private MessageSource lng;
	
	@Autowired
	private SysParameterUtil sys;
	
	@Autowired
	private UploadUtil upload;
	
	@Autowired
	private ImageResizeUtil image;
	
	
	//營業時間管理首頁
	@RequestMapping(value = "/shop/servicetimeday", method = RequestMethod.GET)
	public String serviceTimeDay(Model model, HttpServletRequest req, HttpSession s)  throws HttpSessionRequiredException ,Exception {

		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = StringUtils.trimToEmpty(vo.getShopId());
		
		//查詢營業時間
		List<ServiceTimeDay> serviceTimeList = serviceTimeService.queryServiceTimeDayByShopId(shopId);
		model.addAttribute("serviceTimeList",serviceTimeList);
		
		
		return "servicetimeday/serviceTime";
	}
	
	
	
	//儲存營業時間管理
	@RequestMapping(value = "/shop/addservicetimeday", method = RequestMethod.POST)
	public String addServiceTimeDay(Model model, 
			@RequestParam(required=false) String strWeek,
			@RequestParam(required=false) String listTimeTwentyFourStart, @RequestParam(required=false) String listTimeMinuteStart, 
			@RequestParam(required=false) String listTimeTwentyFourEnd, @RequestParam(required=false) String listTimeMinuteEnd,
			HttpServletRequest req, HttpSession s, Locale locale)  throws HttpSessionRequiredException ,Exception {

		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = StringUtils.trimToEmpty(vo.getShopId());
		
		boolean isCorrect = false;
		String resFlag = "";
		//if(!strWeek.equals("") && listTimeTwentyFourStart != null && listTimeMinuteStart != null && listTimeTwentyFourEnd != null && listTimeMinuteEnd!= null){
			
		//新增修改刪除營業時間
		isCorrect = serviceTimeService.createServiceTimeDay(shopId, strWeek, listTimeTwentyFourStart, listTimeMinuteStart, listTimeTwentyFourEnd, listTimeMinuteEnd);
		if(isCorrect){
			resFlag = lng.getMessage("MSG0017", null, locale);
		}else{
			resFlag = lng.getMessage("MSG0018", null, locale);
		}
		//}
		
		//查詢營業時間
		List<ServiceTimeDay> serviceTimeList = serviceTimeService.queryServiceTimeDayByShopId(shopId);
		model.addAttribute("serviceTimeList",serviceTimeList);
		model.addAttribute("msg", resFlag);
		
		return "servicetimeday/serviceTime";
	}

	
	
	//外送時間管理首頁
	@RequestMapping(value = "/shop/sendtimeday", method = RequestMethod.GET)
	public String sendTimeDay(Model model, HttpServletRequest req, HttpSession s)  throws HttpSessionRequiredException ,Exception {

		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = StringUtils.trimToEmpty(vo.getShopId());
		
		//查詢外送時間
		List<SendTimeDay> sendTimeList = serviceTimeService.querySendTimeDayByShopId(shopId);
		model.addAttribute("sendTimeList",sendTimeList);
		
		
		return "sendtimeday/sendTime";
	}
	
	
	//儲存外送時間管理
	@RequestMapping(value = "/shop/addsendtimeday", method = RequestMethod.POST)
	public String addSendTimeDay(Model model, 
			@RequestParam(required=false) String strWeek,
			@RequestParam(required=false) String listTimeTwentyFourStart, @RequestParam(required=false) String listTimeMinuteStart, 
			@RequestParam(required=false) String listTimeTwentyFourEnd, @RequestParam(required=false) String listTimeMinuteEnd,
			HttpServletRequest req, HttpSession s, Locale locale)  throws HttpSessionRequiredException ,Exception {

		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = StringUtils.trimToEmpty(vo.getShopId());
		
		boolean isCorrect = false;
		String resFlag = "";
		//if(!strWeek.equals("") && listTimeTwentyFourStart != null && listTimeMinuteStart != null && listTimeTwentyFourEnd != null && listTimeMinuteEnd!= null){
			
		//新增修改刪除外送時間
		isCorrect = serviceTimeService.createSendTimeDay(shopId, strWeek, listTimeTwentyFourStart, listTimeMinuteStart, listTimeTwentyFourEnd, listTimeMinuteEnd);
		if(isCorrect){
			resFlag = lng.getMessage("MSG0017", null, locale);
		}else{
			resFlag = lng.getMessage("MSG0018", null, locale);
		}
		//}
		
		//查詢外送時間
		List<SendTimeDay> sendTimeList = serviceTimeService.querySendTimeDayByShopId(shopId);
		model.addAttribute("sendTimeList",sendTimeList);
		model.addAttribute("msg", resFlag);
		
		return "sendtimeday/sendTime";
	}
	
	
	//公休日管理首頁
	@RequestMapping(value = "/shop/holiday", method = RequestMethod.GET)
	public String shopHoliDay(Model model, HttpServletRequest req, HttpSession s)  throws HttpSessionRequiredException ,Exception {

		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = StringUtils.trimToEmpty(vo.getShopId());
		
		//查詢公休日
		List<ShopHoliDay> holiDayList = serviceTimeService.queryHoliDayByShopId(shopId);
		model.addAttribute("holiDayList",holiDayList);
		
		return "shopholiday/holiDay";
	}
	
	
	//儲存公休日管理
	@RequestMapping(value = "/shop/addholiday", method = RequestMethod.POST)
	public String addShopHoliDay(Model model, @RequestParam(required=false) String [] holidayData, 
			HttpServletRequest req, HttpSession s, Locale locale)  throws HttpSessionRequiredException ,Exception {

		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = StringUtils.trimToEmpty(vo.getShopId());
		
		boolean isCorrect = false;
		String resFlag = "";
			
		//新增修改刪除公休日
		isCorrect = serviceTimeService.createHoliDay(holidayData, shopId);
		if(isCorrect){
			resFlag = lng.getMessage("MSG0017", null, locale);
		}else{
			resFlag = lng.getMessage("MSG0018", null, locale);
		}
		
		//查詢公休日
		List<ShopHoliDay> holiDayList = serviceTimeService.queryHoliDayByShopId(shopId);
		model.addAttribute("holiDayList",holiDayList);
		model.addAttribute("msg", resFlag);
		
		return "shopholiday/holiDay";
	}
	
	
	
	//改變頁面傳的Date參數類型
	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	

}