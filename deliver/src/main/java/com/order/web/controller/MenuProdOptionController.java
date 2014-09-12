package com.order.web.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.order.mode.MenuProductItem;
import com.order.mode.MenuProductOptionValue;
import com.order.mode.vo.LoginAdminVO;
import com.order.mode.vo.MenuProductItemVO;
import com.order.service.MenuProdOptionService;
import com.order.util.ImageResizeUtil;
import com.order.util.SysParameterUtil;
import com.order.util.UploadUtil;



@Controller
@RequestMapping("/menuprodoption")
public class MenuProdOptionController extends BaseController {

	@Autowired
	private MenuProdOptionService menuProdOptionService;
	
	@Autowired
	private MessageSource lng;
	
	@Autowired
	private SysParameterUtil sys;
	
	@Autowired
	private UploadUtil upload;
	
	@Autowired
	private ImageResizeUtil image;
	
	
	//首頁
	@RequestMapping(value = "/shop/prodoption", method = RequestMethod.GET)
	public String ProdOption(Model model, HttpServletRequest req, HttpSession s)  throws Exception {
	
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		
		//商品選項管理List頁面查詢資料
		List<MenuProductItemVO> menuProductItemList = menuProdOptionService.queryMenuProdOptionAndValueByShopId(shopId);
		
		model.addAttribute("menuProductItemList", menuProductItemList);
		
		return "menuprodoption/menuProdOption";
	}
	
	
	//新增商品選項管理頁面
	@RequestMapping(value = "/shop/addprodoption", method = RequestMethod.GET)
	public String addProdOption(Model model, HttpServletRequest req, HttpSession s)  throws Exception {
	
		
		return "menuprodoption/addMenuProdOption";
	}
	
	
	//新增商品選項管理
	@RequestMapping(value = "/shop/createprodoption", method = RequestMethod.POST)
	public String createProdOption(Model model, @RequestParam String prodOptionName, @RequestParam String prodOptionSingle,
			@RequestParam String [] prodOptionItemName, @RequestParam String [] prefix, @RequestParam Integer [] price,
			@RequestParam Integer [] sort, @RequestParam String isDefault,
			HttpServletRequest req, HttpSession s, Locale locale)  throws Exception {
	
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		String ref = ""; 
		
		//新增
		boolean isCorrect = menuProdOptionService.createMenuProductItemAndOptionValue(prodOptionName, prodOptionSingle, 
				prodOptionItemName, prefix, price, sort, isDefault, shopId);
		if(isCorrect){
			ref = lng.getMessage("MSG0002", null, locale);
		}else{
			ref = lng.getMessage("MSG0003", null, locale);
		}
		
		//商品選項管理List頁面查詢資料
		List<MenuProductItemVO> menuProductItemList = menuProdOptionService.queryMenuProdOptionAndValueByShopId(shopId);
		
		model.addAttribute("msg", ref);
		model.addAttribute("menuProductItemList", menuProductItemList);
		
		return "menuprodoption/menuProdOption";
	}
	
	
	//修改商品選項管理頁面
	@RequestMapping(value = "/shop/editprodoption", method = RequestMethod.POST)
	public String editProdOption(Model model, @RequestParam String prodOptionId,
			HttpServletRequest req, HttpSession s)  throws Exception {

		//查詢商品選項管理(MENU_PRODUCT_ITEM)資料
		MenuProductItem menuProductItem = menuProdOptionService.queryMenuProductItemByMenuItmeId(prodOptionId);
		model.addAttribute("menuProductItem", menuProductItem);
		
		//查詢商品選項管理(MENU_PRODUCT_OPTION_VALUE)資料
		List<MenuProductOptionValue> menuProductOptionValueList = menuProdOptionService.queryMenuProductOptionValueByMenuItmeId(prodOptionId);
		model.addAttribute("menuProductOptionValueList", menuProductOptionValueList);
		
		return "menuprodoption/editMenuProdOption";
	}
	
	
	//修改商品選項管理
	@RequestMapping(value = "/shop/updateprodoption", method = RequestMethod.POST)
	public String updateProdOption(Model model, @RequestParam String prodOptionName, @RequestParam String prodOptionSingle,
			@RequestParam String [] prodOptionItemName, @RequestParam String [] prefix, @RequestParam Integer [] price,
			@RequestParam Integer [] sort, @RequestParam String isDefault,@RequestParam String menuItmeId,
			HttpServletRequest req, HttpSession s, Locale locale)  throws Exception {
		
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		String ref = ""; 
		
		//修改
		boolean isCorrect = menuProdOptionService.updateMenuProductItemAndOptionValue(prodOptionName, prodOptionSingle, 
				prodOptionItemName, prefix, price, sort, isDefault, menuItmeId, shopId);
		if(isCorrect){
			ref = lng.getMessage("MSG0004", null, locale);
		}else{
			ref = lng.getMessage("MSG0005", null, locale);
		}
		
		//商品選項管理List頁面查詢資料
		List<MenuProductItemVO> menuProductItemList = menuProdOptionService.queryMenuProdOptionAndValueByShopId(shopId);
		
		model.addAttribute("msg", ref);
		model.addAttribute("menuProductItemList", menuProductItemList);
		
		return "menuprodoption/menuProdOption";
	}
	
	
	//刪除商品選項管理
	@RequestMapping(value = "/shop/deleteitemoptionvalue", method = RequestMethod.POST)
	public String deleteProductItemOptionValue(Model model, @RequestParam String prodOptionId,
			HttpServletRequest req, HttpSession s, Locale locale)  throws Exception {
		
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		String ref = ""; 
		
		//查詢商品選項管理底下menu商品選項呈現勾選的Item Type產品數量
		int count = menuProdOptionService.queryCountMenuItemTypeByKey(prodOptionId, shopId);
		
		if(count >0 ){
			
			ref = lng.getMessage("MSG0064", null, locale);
			
		}else{
			//刪除商品選項管理
			boolean isCorrect = menuProdOptionService.deleteMenuProductItemAndOptionValue(prodOptionId, shopId);
			if(isCorrect){
				ref = lng.getMessage("delSuccess", null, locale);
			}else{
				ref = lng.getMessage("delError", null, locale);
			}
		}
		
		//商品選項管理List頁面查詢資料
		List<MenuProductItemVO> menuProductItemList = menuProdOptionService.queryMenuProdOptionAndValueByShopId(shopId);
		
		model.addAttribute("msg", ref);
		model.addAttribute("menuProductItemList", menuProductItemList);
		
		return "menuprodoption/menuProdOption";
		
	}
	
	
	
	
	
	

}