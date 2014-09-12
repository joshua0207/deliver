package com.order.web.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.order.mode.ServiceScopeCircle;
import com.order.mode.vo.LoginAdminVO;
import com.order.service.ServiceScopeService;
import com.order.util.ImageResizeUtil;
import com.order.util.SysParameterUtil;
import com.order.util.UploadUtil;



@Controller
@RequestMapping("/servicescope")
public class ShopServiceScopeController extends BaseController {

	@Autowired
	private ServiceScopeService serviceScopeService;
	
	@Autowired
	private MessageSource lng;
	
	@Autowired
	private SysParameterUtil sys;
	
	@Autowired
	private UploadUtil upload;
	
	@Autowired
	private ImageResizeUtil image;
	
	
	//外送服務區域管理首頁
	@RequestMapping(value = "/shop/mapscope", method = RequestMethod.GET)
	public String shopServiceScope(Model model, HttpServletRequest req, HttpSession s)  throws HttpSessionRequiredException ,Exception {

		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		model.addAttribute("loginVO", loginVO);
		
		//查詢圓型服務範圍資料
		ServiceScopeCircle serviceScopeCircle = serviceScopeService.queryServiceScopeByShopId(shopId);
		model.addAttribute("serviceScopeCircle", serviceScopeCircle);
		
		return "shopservicescope/serviceScope";
	}
	
	
	//新增修改外送服務區域管理
	@ResponseBody
	@RequestMapping(value = "/shop/addmapscope", method = RequestMethod.POST)
	public Map<String, Object> addServiceScope(Model model, @RequestParam(required=false) String circleCenter,
			@RequestParam(required=false) String circleTan, @RequestParam(required=false) double distance,
			@RequestParam(required=false) double maxLat, @RequestParam(required=false) double maxLng,
			@RequestParam(required=false) double minLat, @RequestParam(required=false) double minLng,
			HttpServletRequest req, HttpSession s, Locale locale)  throws Exception {
	        
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			boolean chkAdd = false;
			boolean isFalg = false;
			String resultFlg = lng.getMessage("MSG0018", null, locale);
			LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
			String shopId = loginVO.getShopId();
		
	        //查詢是否新增過
			chkAdd = serviceScopeService.queryServiceScopeByShopIdChk(shopId);
			
			if(chkAdd){
				//更新
				isFalg = serviceScopeService.updateServiceScopeCircle(shopId, circleCenter, circleTan, distance, maxLat, maxLng, minLat, minLng);
				if(isFalg){
					resultFlg = lng.getMessage("MSG0004", null, locale);
				}else{
					resultFlg = lng.getMessage("MSG0005", null, locale);
				}
			}else{
				//新增
				isFalg = serviceScopeService.createServiceScopeCircle(shopId, circleCenter, circleTan, distance, maxLat, maxLng, minLat, minLng);
				if(isFalg){
					resultFlg = lng.getMessage("MSG0002", null, locale);
				}else{
					resultFlg = lng.getMessage("MSG0003", null, locale);
				}
			}
			
			
			jsonMap.put("msg", resultFlg);
			jsonMap.put("shopId", shopId);

		return jsonMap;
	}
	
	
	

}