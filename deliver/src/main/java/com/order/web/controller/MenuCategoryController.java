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

import com.order.mode.MenuCategory;
import com.order.mode.vo.LoginAdminVO;
import com.order.mode.vo.MenuCategoryVO;
import com.order.service.MenuCategoryService;
import com.order.util.ImageResizeUtil;
import com.order.util.SysParameterUtil;
import com.order.util.UploadUtil;



@Controller
@RequestMapping("/menucategory")
public class MenuCategoryController extends BaseController {

	@Autowired
	private MenuCategoryService menuCategoryService;
	
	@Autowired
	private MessageSource lng;
	
	@Autowired
	private SysParameterUtil sys;
	
	@Autowired
	private UploadUtil upload;
	
	@Autowired
	private ImageResizeUtil image;
	
	
	//商品分類管理首頁
	@RequestMapping(value = "/shop/category", method = RequestMethod.GET)
	public String category(Model model, HttpServletRequest req, HttpSession s)  throws Exception {
	
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();	
		
		//查詢商品分類管理
		List<MenuCategoryVO> menuCategoryList = menuCategoryService.queryMenuCategoryByShopId(shopId);
		
		model.addAttribute("menuCategoryList", menuCategoryList);
		//
		return "menucategory/menuCategory";
	}
	
	
	//商品分類管理新增頁面
	@RequestMapping(value = "/shop/addcategory", method = RequestMethod.GET)
	public String addCategory(Model model, HttpServletRequest req, HttpSession s)  throws Exception {
	
		
		return "menucategory/addMenuCategory";
	}
	
	//新增商品分類管理
	@RequestMapping(value = "/shop/createcategory", method = RequestMethod.POST)
	public String createCategory(Model model, @RequestParam String categoryName, 
			@RequestParam(required=false) String categoryNote, @RequestParam Integer sort,
			@RequestParam String hideFlag,
			HttpServletRequest req, HttpSession s, Locale locale)  throws Exception {
	
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		String ref = ""; 
		
		//新增
		boolean isCorrect = menuCategoryService.createMenuCategory(categoryName, categoryNote, sort, hideFlag, shopId);
		if(isCorrect){
			ref = lng.getMessage("MSG0002", null, locale);
		}else{
			ref = lng.getMessage("MSG0003", null, locale);
		}
		
		
		//查詢商品分類管理
		List<MenuCategoryVO> menuCategoryList = menuCategoryService.queryMenuCategoryByShopId(shopId);
		
		model.addAttribute("msg", ref);
		model.addAttribute("menuCategoryList", menuCategoryList);
		
		return "menucategory/menuCategory";
	}
	
	
	//商品分類管理修改頁面
	@RequestMapping(value = "/shop/editcategory", method = RequestMethod.POST)
	public String editCategory(Model model, @RequestParam String categoryId, HttpServletRequest req, HttpSession s)  throws Exception {
	
		//查詢單一商品分類管理資料 
		MenuCategory menuCategory = menuCategoryService.queryMenuCategoryByMenuCatId(categoryId);
		model.addAttribute("menuCategory", menuCategory);
		
		return "menucategory/editMenuCategory";
	}
	
	
	//修改商品分類管理
	@RequestMapping(value = "/shop/updatecategory", method = RequestMethod.POST)
	public String updateCategory(Model model, @RequestParam String menuCatId, 
			@RequestParam String categoryName,  @RequestParam(required=false) String categoryNote, 
			@RequestParam Integer sort, @RequestParam String hideFlag,
			HttpServletRequest req, HttpSession s, Locale locale)  throws Exception {
	
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		String ref = ""; 
		
		//修改商品分類管理
		boolean isCorrect = menuCategoryService.updateMenuCategory(menuCatId, categoryName, categoryNote, sort, hideFlag, shopId);
		if(isCorrect){
			ref = lng.getMessage("MSG0004", null, locale);
		}else{
			ref = lng.getMessage("MSG0005", null, locale);
		}
		
		//查詢商品分類管理
		List<MenuCategoryVO> menuCategoryList = menuCategoryService.queryMenuCategoryByShopId(shopId);
		
		model.addAttribute("msg", ref);
		model.addAttribute("menuCategoryList", menuCategoryList);
		
		return "menucategory/menuCategory";
	}
	
	
	//刪除商品分類管理
	@RequestMapping(value = "/shop/deletecategory", method = RequestMethod.POST)
	public String deleteCategory(Model model, @RequestParam String menuCatId,  HttpServletRequest req, HttpSession s, 
			Locale locale)  throws Exception {
		
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		String ref = ""; 
		
		//查詢商品分類管理底下產品數量
		int count = menuCategoryService.queryCountMenuProductByMenuCatId(menuCatId, shopId);
		
		if(count >0 ){
			
			ref = lng.getMessage("MSG0063", null, locale);
			
		}else{
			//刪除商品分類管理
			boolean isCorrect = menuCategoryService.deleteMenuCategory(menuCatId);
			if(isCorrect){
				ref = lng.getMessage("delSuccess", null, locale);
			}else{
				ref = lng.getMessage("delError", null, locale);
			}
		}
		
		//查詢商品分類管理
		List<MenuCategoryVO> menuCategoryList = menuCategoryService.queryMenuCategoryByShopId(shopId);
		
		model.addAttribute("msg", ref);
		model.addAttribute("menuCategoryList", menuCategoryList);
		
		return "menucategory/menuCategory";
	}
	
	
	
	

}