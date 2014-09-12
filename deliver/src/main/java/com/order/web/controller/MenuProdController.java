package com.order.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.order.mode.MenuCategory;
import com.order.mode.MenuProduct;
import com.order.mode.MenuProductItem;
import com.order.mode.vo.LoginAdminVO;
import com.order.mode.vo.MenuProductVO;
import com.order.service.MenuCategoryService;
import com.order.service.MenuProdOptionService;
import com.order.service.MenuProdService;
import com.order.util.ImageResizeUtil;
import com.order.util.SysParameterUtil;
import com.order.util.TimeMachine;
import com.order.util.UploadUtil;
import com.oreilly.servlet.MultipartRequest;



@Controller
@RequestMapping("/menuprod")
public class MenuProdController extends BaseController {

	@Autowired
	private MenuProdService menuProdService;
	
	@Autowired
	private MenuCategoryService menuCategoryService;
	
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
	
	
	//商品管理首頁
	@RequestMapping(value = "/shop/prod", method = RequestMethod.GET)
	public String Prod(Model model, @RequestParam(required=false) String menuCatId,
		   HttpServletRequest req, HttpSession s)  throws Exception {
	
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		
		//查詢店家所有商品分類管理資料
		List<MenuCategory> menuCategoryList = menuCategoryService.queryAllByKey(shopId);
		
		//查詢商品管理(MENU_PRODUCT)所有資料，及查詢是否有訂單使用回傳count
		Map<String, List<MenuProductVO>> menuProductMap = menuProdService.queryMenuProductByShopId(shopId);
		
		model.addAttribute("menuCategoryList", menuCategoryList);
		model.addAttribute("menuProductMap", menuProductMap);
		
		//頁面圖片要顯示的資料
		String serverName = req.getServerName();//網域
		String directory = sys.getSysPrmValue("deliver", "menuProdCatalog");// menu產品照片資料夾名稱目錄
		String domainPicPath = sys.getSysPrmValue("deliver", "domainPicPath");// 取得存放路徑域名
		model.addAttribute("serverName", serverName);
		model.addAttribute("directory", directory);
		model.addAttribute("domainPicPath", domainPicPath);
		model.addAttribute("categoryId", menuCatId);
		
		return "menuprod/menuProd";
	}
	
	//新增商品管理頁面
	@RequestMapping(value = "/shop/addProd", method = RequestMethod.GET)
	public String addProd(Model model, @RequestParam(required=false) String menuCatId,
		   HttpServletRequest req, HttpSession s)  throws Exception {

		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
	
		//查詢店家所有商品分類管理資料
		List<MenuCategory> menuCategoryList = menuCategoryService.queryAllByKey(shopId);
		model.addAttribute("menuCategoryList", menuCategoryList);
		
		//查詢商品選項管理(MENU_PRODUCT_ITEM)所有資料
		List<MenuProductItem> menuProductItemList = menuProdOptionService.queryAllByShopId(shopId); 
		model.addAttribute("menuProductItemList", menuProductItemList);
		
		model.addAttribute("menuCatId", menuCatId);
		
		return "menuprod/addMenuProd";
	}
	
	
	//新增商品管理
	@RequestMapping(value = "/shop/createProd", method = RequestMethod.POST)
	public String createProd(Model model, HttpServletRequest req, HttpSession s, Locale locale)  throws Exception {
		
		log.info("新增商品管理：");
		String serverName = req.getServerName();
		LoginAdminVO storeVo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = storeVo.getShopId();//店家ID
		log.info("shopId*********:" + shopId);
		String picture = "" ;
		if(serverName.equals("localhost")){
			picture = "c://xampp/htdocs/img/";
		}else{
			picture = sys.getSysPrmValue("deliver", "picture");// 取得存放路徑
		}
		String directory = sys.getSysPrmValue("deliver", "menuProdCatalog");// menu產品照片資料夾名稱目錄

			File f;
			long file_size_max = 2097152;// 2m => 1024*1024*2
			HashMap<String, Object> hm = new HashMap<String, Object>();
			boolean insertData = false;
			String fileName = "";
			String subfileName = "";
			int fileCount = 0;
			String resultValue = "";
			String categoryId = "";
//			String path = "";//前台顯示圖片串的路徑
			TimeMachine id = new TimeMachine();
			String filNowTime = id.getNowTime();// 存進資料庫的檔名
			String shrinks = filNowTime + "_menu_80.jpg";// 縮圖後的檔名80*61
			String shrinksTwo = filNowTime + "_menu_90.jpg";// 縮圖後的檔名90*69
			String shrinksThree = filNowTime + "_menu_152.jpg";// 縮圖後的檔名152*114
			String shrinksMay = filNowTime + "_menu_162.jpg";// 縮圖後的檔名162*124
			String shrinksSix = filNowTime + "_menu_250.jpg";// 縮圖後的檔名250*191
			String shrinksSeven = filNowTime + "_menu_325.jpg";// 縮圖後的檔名325*216
			String shrinksEight = filNowTime + "_menu_800.jpg";// 縮圖後的檔名800*600

			try {
				hm = upload.upload(req, picture + shopId + directory, "logo");

				fileName = String.valueOf(hm.get("fileName"));// 檔名名稱
				fileCount = Integer.parseInt(String.valueOf(hm.get("fileCount")));// 上傳成功數
				subfileName = String.valueOf(hm.get("subfileName"));// 副檔名名稱
				MultipartRequest mulit = (MultipartRequest) hm.get("mulit");
				String prodName = StringUtils.trimToEmpty(mulit.getParameter("prodName"));
				categoryId = StringUtils.trimToEmpty(mulit.getParameter("categoryId"));
				Integer prodPrice = Integer.parseInt(mulit.getParameter("prodPrice"));
				Integer onsalePrice = Integer.parseInt(mulit.getParameter("onsalePrice"));
				Integer sort = Integer.parseInt(mulit.getParameter("sort"));
				String topProd = StringUtils.trimToEmpty(mulit.getParameter("topProd"));
				String prodDesc = StringUtils.trimToEmpty(mulit.getParameter("prodDesc"));
				String prodDescDetl = StringUtils.trimToEmpty(mulit.getParameter("prodDescDetl"));
				String prodOptionId [] = mulit.getParameterValues("prodOptionId");
				String deleteFlag = mulit.getParameter("deleteFlag");

				if (fileCount > 0) { // 是否上傳圖片成功
					log.info("新增上傳圖檔資料：");
					
					if(subfileName.equals("jpg") || subfileName.equals("png") || subfileName.equals("gif")){//判斷副檔名

						f = new File(picture + shopId + directory, fileName);
						if (f.length() < file_size_max) {// 上傳圖檔是否超過2m

							// 縮圖
							String upPath = picture + shopId + directory + fileName;// 縮圖前的路徑加檔名
							boolean tt = false;
							boolean tu = false;
							boolean tu3 = false;
							boolean tu4 = false;
							boolean tu5 = false;
							boolean tu6 = false;
							boolean th = false;
							tt = image.processImageResize(upPath, "JPEG", 80, 61, picture + shopId + directory + shrinks);
							log.info("縮圖80*61是否成功:" + tt);
							tu = image.processImageResize(upPath, "JPEG", 90, 69, picture + shopId + directory + shrinksTwo);
							log.info("縮圖90*69是否成功:" + tu);
							tu3 = image.processImageResize(upPath, "JPEG", 152, 114, picture + shopId + directory + shrinksThree);
							log.info("縮圖152*114是否成功:" + tu3);
							tu4 = image.processImageResize(upPath, "JPEG", 162, 124, picture + shopId + directory + shrinksMay);
							log.info("縮圖162*124是否成功:" + tu4);
							tu5 = image.processImageResize(upPath, "JPEG", 250, 191, picture + shopId + directory + shrinksSix);
							log.info("縮圖250*191是否成功:" + tu5);
							tu6 = image.processImageResize(upPath, "JPEG", 325, 216, picture + shopId + directory + shrinksSeven);
							log.info("縮圖325*216是否成功:" + tu6);
							th = image.processImageResize(upPath, "JPEG", 800, 600, picture + shopId + directory + shrinksEight);
							log.info("縮圖800*600是否成功:" + th);
							f.delete();// 刪除原始還沒縮圖的圖檔
							
							if (tt && tu && tu3 && tu4 && tu5 && tu6 && th) {
								//縮圖成功寫進DB
								insertData = menuProdService.createMenuProductAndItemType(prodName, categoryId, prodPrice, onsalePrice, sort, topProd, prodDesc, prodDescDetl, prodOptionId, deleteFlag, filNowTime, shopId);
								
								if (insertData) {
									//新增成功
									resultValue = lng.getMessage("MSG0002", null, locale);
//									path = this.getPicturePath(shopId, filNowTime, serverName, directory);//前台要顯示的圖片路徑
									
								} else {
									//新增DB不成功
									this.deleteUpFile(shrinks, serverName, shopId, directory);// 刪除縮圖後的圖檔 80*61
									this.deleteUpFile(shrinksTwo, serverName, shopId, directory);// 刪除縮圖後的圖檔 90*69
									this.deleteUpFile(shrinksThree, serverName, shopId, directory);// 刪除縮圖後的圖檔 152*114
									this.deleteUpFile(shrinksMay, serverName, shopId, directory);// 刪除縮圖後的圖檔 162*124
									this.deleteUpFile(shrinksSix, serverName, shopId, directory);// 刪除縮圖後的圖檔 250*191
									this.deleteUpFile(shrinksSeven, serverName, shopId, directory);// 刪除縮圖後的圖檔 325*216
									this.deleteUpFile(shrinksEight, serverName, shopId, directory);// 刪除縮圖後的圖檔 800*600
									resultValue = lng.getMessage("MSG0003", null, locale);
								}
							} else {
								//縮圖不成功
								this.deleteUpFile(shrinks, serverName, shopId, directory);// 刪除縮圖後的圖檔 80*61
								this.deleteUpFile(shrinksTwo, serverName, shopId, directory);// 刪除縮圖後的圖檔 90*69
								this.deleteUpFile(shrinksThree, serverName, shopId, directory);// 刪除縮圖後的圖檔 152*114
								this.deleteUpFile(shrinksMay, serverName, shopId, directory);// 刪除縮圖後的圖檔 162*124
								this.deleteUpFile(shrinksSix, serverName, shopId, directory);// 刪除縮圖後的圖檔 250*191
								this.deleteUpFile(shrinksSeven, serverName, shopId, directory);// 刪除縮圖後的圖檔 325*216
								this.deleteUpFile(shrinksEight, serverName, shopId, directory);// 刪除縮圖後的圖檔 800*600
								resultValue = lng.getMessage("MSG0003", null, locale);
							}
						} else {
							//圖檔超過2m
							this.deleteUpFile(fileName, serverName, shopId, directory);// 刪除上傳的圖檔
							resultValue = lng.getMessage("picSizeTooBig", null, locale);
						}
					}else{
						//副檔名不合
						this.deleteUpFile(fileName, serverName, shopId, directory);// 刪除上傳的圖檔
						resultValue = lng.getMessage("fileErroe", null, locale);
					}
				}

				if (fileCount == 0) {
					log.info("沒選擇圖檔：");
					//沒選擇圖檔直接寫進DB
					filNowTime = "";
					insertData = menuProdService.createMenuProductAndItemType(prodName, categoryId, prodPrice, onsalePrice, sort, topProd, prodDesc, prodDescDetl, prodOptionId, deleteFlag, filNowTime, shopId);
					if (insertData) {
						//新增成功
						resultValue = lng.getMessage("MSG0002", null, locale);
//						path = "";//前台要顯示的圖片路徑
					}else{
						//新增DB不成功
						resultValue = lng.getMessage("MSG0003", null, locale);
					}
				}

			} catch (IOException ex) {
				resultValue = lng.getMessage("errorUpPic", null, locale)+ex.toString();
				log.error(lng.getMessage("errorUpPic", null, locale)+ ex);
				
			}
			f = null;
			hm = null;
			id = null;
			storeVo = null;
			model.addAttribute("msg", resultValue);
//			model.addAttribute("path", path);
			
			//回查詢頁面把資料帶回頁面
			//查詢店家所有商品分類管理資料
			List<MenuCategory> menuCategoryList = menuCategoryService.queryAllByKey(shopId);
			//查詢商品管理(MENU_PRODUCT)所有資料，及查詢是否有訂單使用回傳count
			Map<String, List<MenuProductVO>> menuProductMap = menuProdService.queryMenuProductByShopId(shopId);
			model.addAttribute("menuCategoryList", menuCategoryList);
			model.addAttribute("menuProductMap", menuProductMap);
			model.addAttribute("categoryId", categoryId);
			
			//頁面圖片要顯示的資料
			String domainPicPath = sys.getSysPrmValue("deliver", "domainPicPath");// 取得存放路徑域名
			model.addAttribute("serverName", serverName);
			model.addAttribute("directory", directory);
			model.addAttribute("domainPicPath", domainPicPath);
		
		return "menuprod/menuProd";
	}
	
	
	//修改商品管理頁面
	@RequestMapping(value = "/shop/editProd", method = RequestMethod.POST)
	public String editProd(Model model, @RequestParam(required=false) String menuCatId,
			@RequestParam String prodId, HttpServletRequest req, HttpSession s)  throws Exception {
		
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		
		//查詢店家所有商品分類管理資料
		List<MenuCategory> menuCategoryList = menuCategoryService.queryAllByKey(shopId);
		model.addAttribute("menuCategoryList", menuCategoryList);
		
		//查詢商品選項管理(MENU_PRODUCT_ITEM)所有資料
		List<MenuProductItem> menuProductItemList = menuProdOptionService.queryAllByShopId(shopId); 
		model.addAttribute("menuProductItemList", menuProductItemList);
		
		//查詢單一商品管理(MENU_PRODUCT)資料
		MenuProductVO menuProduct = menuProdService.queryMenuProductByMenuProdId(prodId);
		model.addAttribute("menuProduct", menuProduct);
		
		//查詢menu商品選項呈現勾選的商品選項Item Type資料(MENU_ITEM_TYPE)，只回傳MENU_ITME_ID相加用,隔開字串
		String menuItmeId = menuProdService.queryMenuItemTypeByKey(prodId, shopId);
		model.addAttribute("menuItmeId", menuItmeId);
		
		//頁面圖片要顯示的資料
		String serverName = req.getServerName();//網域
		String directory = sys.getSysPrmValue("deliver", "menuProdCatalog");// menu產品照片資料夾名稱目錄
		String domainPicPath = sys.getSysPrmValue("deliver", "domainPicPath");// 取得存放路徑域名
		model.addAttribute("serverName", serverName);
		model.addAttribute("directory", directory);
		model.addAttribute("domainPicPath", domainPicPath);
		model.addAttribute("menuCatId", menuCatId);
		
		return "menuprod/editMenuProd";
	}
	
	
	//商品管理頁面-刪除商品圖片
	@ResponseBody
	@RequestMapping(value = "/shop/deleteprodpic", method = RequestMethod.POST)
	public Map<String, Object> deleteProdPic(Model model, @RequestParam String prodId, 
			@RequestParam String picName, HttpServletRequest req, HttpSession s, Locale locale)  throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		log.info("刪除Menu照片：");
		
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		log.info("shopId*********:" + shopId);
		String serverName = req.getServerName();
		boolean error = false;
		String shrinks = picName + "_menu_80.jpg";// 縮圖後的檔名80*61
		String shrinksTwo = picName + "_menu_90.jpg";// 縮圖後的檔名90*69
		String shrinksThree = picName + "_menu_152.jpg";// 縮圖後的檔名152*114
		String shrinksMay = picName + "_menu_162.jpg";// 縮圖後的檔名162*124
		String shrinksSix = picName + "_menu_250.jpg";// 縮圖後的檔名250*191
		String shrinksSeven = picName + "_menu_325.jpg";// 縮圖後的檔名325*216
		String shrinksEight = picName + "_menu_800.jpg";// 縮圖後的檔名800*600
		String directory = sys.getSysPrmValue("deliver", "menuProdCatalog");// menu產品照片資料夾名稱目錄
		String resultValue = "";

			boolean count = false;
			boolean chkDel;
			MenuProduct menuProduct = new MenuProduct();
			menuProduct.setMenuProdId(prodId);
			menuProduct.setPicName("");//檔名
			chkDel = menuProdService.updateMenuProductById(menuProduct);

			if (chkDel) {
				this.deleteUpFile(shrinks, serverName, shopId, directory);// 刪除縮圖後的圖檔 80*61
				this.deleteUpFile(shrinksTwo, serverName, shopId, directory);// 刪除縮圖後的圖檔 90*69
				this.deleteUpFile(shrinksThree, serverName, shopId, directory);// 刪除縮圖後的圖檔 152*114
				this.deleteUpFile(shrinksMay, serverName, shopId, directory);// 刪除縮圖後的圖檔 162*124
				this.deleteUpFile(shrinksSix, serverName, shopId, directory);// 刪除縮圖後的圖檔 250*191
				this.deleteUpFile(shrinksSeven, serverName, shopId, directory);// 刪除縮圖後的圖檔 325*216
				this.deleteUpFile(shrinksEight, serverName, shopId, directory);// 刪除縮圖後的圖檔 800*600
				count = true;
				error = false;
				resultValue = lng.getMessage("delSuccess", null, locale);
			} else {
				error = true;
				count = false;
				resultValue = lng.getMessage("delError", null, locale);
			}
			
			jsonMap.put("success", count);
			jsonMap.put("error", error);
			jsonMap.put("msg", resultValue);

		return jsonMap;
	}
	
	
	//修改商品管理
	@RequestMapping(value = "/shop/updateprod", method = RequestMethod.POST)
	public String updateProd(Model model, HttpServletRequest req, HttpSession s, Locale locale)  throws Exception {
		
		log.info("修改商品管理：");
		String serverName = req.getServerName();
		LoginAdminVO storeVo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = storeVo.getShopId();//店家ID
		log.info("shopId*********:" + shopId);
		String picture = "";
		if(serverName.equals("localhost")){
			picture = "c://xampp/htdocs/img/";
		}else{
			picture = sys.getSysPrmValue("deliver", "picture");// 取得存放路徑
		}
		String directory = sys.getSysPrmValue("deliver", "menuProdCatalog");// menu產品照片資料夾名稱目錄

			File f;
			long file_size_max = 2097152;// 2m => 1024*1024*2
			HashMap<String, Object> hm = new HashMap<String, Object>();
			boolean updateData = false;
			String fileName = "";
			String subfileName = "";
			int fileCount = 0;
			String resultValue = "";
			String categoryId = "";
//				String path = "";//前台顯示圖片串的路徑
			TimeMachine id = new TimeMachine();
			String filNowTime = id.getNowTime();// 存進資料庫的檔名
			String shrinks = filNowTime + "_menu_80.jpg";// 縮圖後的檔名80*61
			String shrinksTwo = filNowTime + "_menu_90.jpg";// 縮圖後的檔名90*69
			String shrinksThree = filNowTime + "_menu_152.jpg";// 縮圖後的檔名152*114
			String shrinksMay = filNowTime + "_menu_162.jpg";// 縮圖後的檔名162*124
			String shrinksSix = filNowTime + "_menu_250.jpg";// 縮圖後的檔名250*191
			String shrinksSeven = filNowTime + "_menu_325.jpg";// 縮圖後的檔名325*216
			String shrinksEight = filNowTime + "_menu_800.jpg";// 縮圖後的檔名800*600

			try {
				hm = upload.upload(req, picture + shopId + directory, "logo");

				fileName = String.valueOf(hm.get("fileName"));// 檔名名稱
				fileCount = Integer.parseInt(String.valueOf(hm.get("fileCount")));// 上傳成功數
				subfileName = String.valueOf(hm.get("subfileName"));// 副檔名名稱
				MultipartRequest mulit = (MultipartRequest) hm.get("mulit");
				String prodId = StringUtils.trimToEmpty(mulit.getParameter("prodId"));
				String picName = StringUtils.trimToEmpty(mulit.getParameter("picName"));
				String prodName = StringUtils.trimToEmpty(mulit.getParameter("prodName"));
				categoryId = StringUtils.trimToEmpty(mulit.getParameter("categoryId"));
				Integer prodPrice = Integer.parseInt(mulit.getParameter("prodPrice"));
				Integer onsalePrice = Integer.parseInt(mulit.getParameter("onsalePrice"));
				Integer sort = Integer.parseInt(mulit.getParameter("sort"));
				String topProd = StringUtils.trimToEmpty(mulit.getParameter("topProd"));
				String prodDesc = StringUtils.trimToEmpty(mulit.getParameter("prodDesc"));
				String prodDescDetl = StringUtils.trimToEmpty(mulit.getParameter("prodDescDetl"));
				String prodOptionId [] = mulit.getParameterValues("prodOptionId");
				String deleteFlag = mulit.getParameter("deleteFlag");

				if (fileCount > 0) { // 是否上傳圖片成功
					log.info("修改上傳圖檔資料：");
					
					if(subfileName.equals("jpg") || subfileName.equals("png") || subfileName.equals("gif")){//判斷副檔名

						f = new File(picture + shopId + directory, fileName);
						if (f.length() < file_size_max) {// 上傳圖檔是否超過2m

							// 縮圖
							String upPath = picture + shopId + directory + fileName;// 縮圖前的路徑加檔名
							boolean tt = false;
							boolean tu = false;
							boolean tu3 = false;
							boolean tu4 = false;
							boolean tu5 = false;
							boolean tu6 = false;
							boolean th = false;
							tt = image.processImageResize(upPath, "JPEG", 80, 61, picture + shopId + directory + shrinks);
							log.info("縮圖80*61是否成功:" + tt);
							tu = image.processImageResize(upPath, "JPEG", 90, 69, picture + shopId + directory + shrinksTwo);
							log.info("縮圖90*69是否成功:" + tu);
							tu3 = image.processImageResize(upPath, "JPEG", 152, 114, picture + shopId + directory + shrinksThree);
							log.info("縮圖152*114是否成功:" + tu3);
							tu4 = image.processImageResize(upPath, "JPEG", 162, 124, picture + shopId + directory + shrinksMay);
							log.info("縮圖162*124是否成功:" + tu4);
							tu5 = image.processImageResize(upPath, "JPEG", 250, 191, picture + shopId + directory + shrinksSix);
							log.info("縮圖250*191是否成功:" + tu5);
							tu6 = image.processImageResize(upPath, "JPEG", 325, 216, picture + shopId + directory + shrinksSeven);
							log.info("縮圖325*216是否成功:" + tu6);
							th = image.processImageResize(upPath, "JPEG", 800, 600, picture + shopId + directory + shrinksEight);
							log.info("縮圖800*600是否成功:" + th);
							f.delete();// 刪除原始還沒縮圖的圖檔
							
							if (tt && tu && tu3 && tu4 && tu5 && tu6 && th) {
								//縮圖成功寫進DB
								updateData = menuProdService.updateMenuProductByKey(prodId, prodName, categoryId, prodPrice, onsalePrice, sort, topProd, prodDesc, prodDescDetl, prodOptionId, deleteFlag, filNowTime, shopId);
								
								if (updateData) {
									//修改成功
									resultValue = lng.getMessage("MSG0004", null, locale);
//									path = this.getPicturePath(shopId, filNowTime, serverName, directory);//前台要顯示的圖片路徑
									this.deleteUpFile(picName + "_menu_80.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 80*61
									this.deleteUpFile(picName + "_menu_90.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 90*69
									this.deleteUpFile(picName + "_menu_152.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 152*114
									this.deleteUpFile(picName + "_menu_162.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 162*124
									this.deleteUpFile(picName + "_menu_250.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 250*191
									this.deleteUpFile(picName + "_menu_325.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 325*216
									this.deleteUpFile(picName + "_menu_800.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 800*600
								} else {
									//修改DB不成功
									this.deleteUpFile(shrinks, serverName, shopId, directory);// 刪除縮圖後的圖檔 80*61
									this.deleteUpFile(shrinksTwo, serverName, shopId, directory);// 刪除縮圖後的圖檔 90*69
									this.deleteUpFile(shrinksThree, serverName, shopId, directory);// 刪除縮圖後的圖檔 152*114
									this.deleteUpFile(shrinksMay, serverName, shopId, directory);// 刪除縮圖後的圖檔 162*124
									this.deleteUpFile(shrinksSix, serverName, shopId, directory);// 刪除縮圖後的圖檔 250*191
									this.deleteUpFile(shrinksSeven, serverName, shopId, directory);// 刪除縮圖後的圖檔 325*216
									this.deleteUpFile(shrinksEight, serverName, shopId, directory);// 刪除縮圖後的圖檔 800*600
									resultValue = lng.getMessage("MSG0005", null, locale);
								}
							} else {
								//縮圖不成功
								this.deleteUpFile(shrinks, serverName, shopId, directory);// 刪除縮圖後的圖檔 80*61
								this.deleteUpFile(shrinksTwo, serverName, shopId, directory);// 刪除縮圖後的圖檔 90*69
								this.deleteUpFile(shrinksThree, serverName, shopId, directory);// 刪除縮圖後的圖檔 152*114
								this.deleteUpFile(shrinksMay, serverName, shopId, directory);// 刪除縮圖後的圖檔 162*124
								this.deleteUpFile(shrinksSix, serverName, shopId, directory);// 刪除縮圖後的圖檔 250*191
								this.deleteUpFile(shrinksSeven, serverName, shopId, directory);// 刪除縮圖後的圖檔 325*216
								this.deleteUpFile(shrinksEight, serverName, shopId, directory);// 刪除縮圖後的圖檔 800*600
								resultValue = lng.getMessage("MSG0005", null, locale);
							}
						} else {
							//圖檔超過2m
							this.deleteUpFile(fileName, serverName, shopId, directory);// 刪除上傳的圖檔
							resultValue = lng.getMessage("picSizeTooBig", null, locale);
						}
					}else{
						//副檔名不合
						this.deleteUpFile(fileName, serverName, shopId, directory);// 刪除上傳的圖檔
						resultValue = lng.getMessage("fileErroe", null, locale);
					}
				}

				if (fileCount == 0) {
					log.info("沒選擇圖檔：");
					//沒選擇圖檔直接寫進DB
					filNowTime = "";
					updateData = menuProdService.updateMenuProductByKey(prodId, prodName, categoryId, prodPrice, onsalePrice, sort, topProd, prodDesc, prodDescDetl, prodOptionId, deleteFlag, picName, shopId);
					if (updateData) {
						//修改成功
						resultValue = lng.getMessage("MSG0004", null, locale);
//							path = "";//前台要顯示的圖片路徑
					}else{
						//修改DB不成功
						resultValue = lng.getMessage("MSG0005", null, locale);
					}
				}

			} catch (IOException ex) {
				resultValue = lng.getMessage("errorUpPic", null, locale)+ex.toString();
				log.error(lng.getMessage("errorUpPic", null, locale)+ ex);
				
			}
			f = null;
			hm = null;
			id = null;
			storeVo = null;
			model.addAttribute("msg", resultValue);
//				model.addAttribute("path", path);
			
			//回查詢頁面把資料帶回頁面
			//查詢店家所有商品分類管理資料
			List<MenuCategory> menuCategoryList = menuCategoryService.queryAllByKey(shopId);
			//查詢商品管理(MENU_PRODUCT)所有資料，及查詢是否有訂單使用回傳count
			Map<String, List<MenuProductVO>> menuProductMap = menuProdService.queryMenuProductByShopId(shopId);
			model.addAttribute("menuCategoryList", menuCategoryList);
			model.addAttribute("menuProductMap", menuProductMap);
			model.addAttribute("categoryId",categoryId);
			
			//頁面圖片要顯示的資料
			String domainPicPath = sys.getSysPrmValue("deliver", "domainPicPath");// 取得存放路徑域名
			model.addAttribute("serverName", serverName);
			model.addAttribute("directory", directory);
			model.addAttribute("domainPicPath", domainPicPath);
		
		return "menuprod/menuProd";
	}
	
	
	//刪除商品管理
	@RequestMapping(value = "/shop/deleteprod", method = RequestMethod.POST)
	public String deleteProd(Model model, @RequestParam String prodId, @RequestParam String picName,
			@RequestParam String menuCatId, HttpServletRequest req, HttpSession s, Locale locale)  throws Exception {
		
		log.info("刪除商品管理：");
		String serverName = req.getServerName();
		String directory = sys.getSysPrmValue("deliver", "menuProdCatalog");// menu產品照片資料夾名稱目錄
		LoginAdminVO storeVo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = storeVo.getShopId();//店家ID
		log.info("shopId*********:" + shopId);
		String resultValue = "";
		
		//判斷是否有訂單在使用
		Boolean chkDel = menuProdService.queryShopPoDetlByKey(prodId, shopId);
		if(chkDel){
			
			//此商品有訂單下訂過，所以不可刪除
			resultValue = lng.getMessage("MSG0065", null, locale);
			
		}else{
			Boolean isDel = menuProdService.deleteMenuProduct(prodId, shopId);
			if(isDel){
				//刪除DB成功
				//刪除縮圖
				this.deleteUpFile(picName + "_menu_80.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 80*61
				this.deleteUpFile(picName + "_menu_90.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 90*69
				this.deleteUpFile(picName + "_menu_152.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 152*114
				this.deleteUpFile(picName + "_menu_162.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 162*124
				this.deleteUpFile(picName + "_menu_250.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 250*191
				this.deleteUpFile(picName + "_menu_325.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 325*216
				this.deleteUpFile(picName + "_menu_800.jpg", serverName, shopId, directory);// 刪除原先縮圖後的圖檔 800*600
				
				resultValue = lng.getMessage("delSuccess", null, locale);
				
			}else{
				//刪除失敗
				resultValue = lng.getMessage("delError", null, locale);
			}
		}
		
		model.addAttribute("msg", resultValue);
		
		//回查詢頁面把資料帶回頁面
		//查詢店家所有商品分類管理資料
		List<MenuCategory> menuCategoryList = menuCategoryService.queryAllByKey(shopId);
		//查詢商品管理(MENU_PRODUCT)所有資料，及查詢是否有訂單使用回傳count
		Map<String, List<MenuProductVO>> menuProductMap = menuProdService.queryMenuProductByShopId(shopId);
		model.addAttribute("menuCategoryList", menuCategoryList);
		model.addAttribute("menuProductMap", menuProductMap);
		model.addAttribute("categoryId",menuCatId);
		
		//頁面圖片要顯示的資料
		String domainPicPath = sys.getSysPrmValue("deliver", "domainPicPath");// 取得存放路徑域名
		model.addAttribute("serverName", serverName);
		model.addAttribute("directory", directory);
		model.addAttribute("domainPicPath", domainPicPath);
		return "menuprod/menuProd";
	}
	
	
	//刪除上傳的檔案
	public void deleteUpFile(String fileName, String serverName, String shopId, String directory) throws Exception {
		String picture = "";
		if(serverName.equals("localhost")){
			picture = "c://xampp/htdocs/img/";
		}else{
			picture = sys.getSysPrmValue("deliver", "picture");// 取得存放路徑
		}
		
		File f;
		f = new File(picture + shopId + directory, fileName);// 刪除圖檔
		f.delete();
		f = null;
	}
	
	
	//圖片路徑+圖片檔名
	public String getPicturePath(String shopId, String fileName, String serverName, String directory) throws Exception{
		String domainPicPath = "";
		if(serverName.equals("localhost")){
			domainPicPath = "http://localhost:8080";
		}else{
			domainPicPath = sys.getSysPrmValue("deliver", "domainPicPath");// 取得存放路徑域名
		}

		StringBuilder sb = new StringBuilder();
		try {
			sb.append(domainPicPath);
			sb.append("/img/");
			sb.append(shopId);
			sb.append(directory);
			sb.append(fileName+"_menu_90.jpg");	
		} catch (Exception e) {
			log.error("picturePath  error:" +sb.toString()+ e);
		}
		return sb.toString();
	}
	
	
	
	
	
	

}