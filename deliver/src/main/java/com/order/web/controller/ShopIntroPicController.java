package com.order.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.order.mode.ShopAllPicture;
import com.order.mode.ShopIntroducePic;
import com.order.mode.vo.LoginAdminVO;
import com.order.service.ShopIntroPicService;
import com.order.util.ImageResizeUtil;
import com.order.util.SysParameterUtil;
import com.order.util.TimeMachine;
import com.order.util.UploadUtil;



@Controller
@RequestMapping("/shopintropic")
public class ShopIntroPicController extends BaseController {

	@Autowired
	private ShopIntroPicService shopIntroPicService;
	
	@Autowired
	private MessageSource lng;
	
	@Autowired
	private SysParameterUtil sys;
	
	@Autowired
	private UploadUtil upload;
	
	@Autowired
	private ImageResizeUtil image;
	
	//店家介紹首頁
	@RequestMapping(value = "/shop/shopintro", method = RequestMethod.GET)
	public String shopintro(Model model, HttpServletRequest req, HttpSession s)  throws HttpSessionRequiredException ,Exception {

		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		
		//查詢店家介紹
		ShopIntroducePic shopIntroducePic = shopIntroPicService.queryShopInfoByShopId(shopId);
		model.addAttribute(shopIntroducePic);
		
		return "shopintropic/shopIntroducePic";
	}
	
	
	//儲存店家介紹
	@RequestMapping(value = "/shop/shopintrosave", method = RequestMethod.POST)
	public String shopintroSave(@ModelAttribute ShopIntroducePic shopIntroPic,  Model model, @RequestParam(required=false) String shopContent
			,@RequestParam(required=false) String shopActivityNews, @RequestParam(required=false) String shopMemo, HttpSession s 
			, Locale locale)  throws Exception {
		
		boolean isAdd = false;
		boolean isCorrect = false;
		String resultVale = "";
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		TimeMachine id = new TimeMachine();
		
		//先查詢是否有新增過店家介紹
		isAdd = shopIntroPicService.queryByShopIsAdd(shopId);
		if(isAdd){
			//修改
			isCorrect = shopIntroPicService.updateShopIntroPic(shopIntroPic);
			if(isCorrect){
				//修改成功
				resultVale = lng.getMessage("MSG0004", null, locale);;
			}else{
				//修改失敗
				resultVale = lng.getMessage("MSG0005", null, locale);;
			}
		}else{
			//新增
			shopIntroPic.setShopInfoId(id.serial("introPic", 0));
			shopIntroPic.setShopId(shopId);
			isCorrect = shopIntroPicService.createShopIntroPic(shopIntroPic);
			if(isCorrect){
				//新增成功
				resultVale = lng.getMessage("MSG0002", null, locale);;
			}else{
				//新增失敗
				resultVale = lng.getMessage("MSG0003", null, locale);;
			}
		}
		
		//查詢店家介紹
		ShopIntroducePic shopIntroducePic = shopIntroPicService.queryShopInfoByShopId(shopId);
		model.addAttribute(shopIntroducePic);
		model.addAttribute("msg", resultVale);

		return "shopintropic/shopIntroducePic";
	}
	
	
//================================店家LOGO圖片上傳==========Begin===============================================
	
	//店家LOGO圖片上傳首頁
	@RequestMapping(value = "/shop/shoplogo", method = RequestMethod.GET)
	public String shopLogo(Model model, HttpServletRequest req, HttpSession s) throws HttpSessionRequiredException , Exception {

		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		String serverName = req.getServerName();
		String path = "";
		
		//查詢店家Logo圖片
		// 提出店家照片原來的Path檔名
		ShopIntroducePic shopIntroPic = shopIntroPicService.queryShopLogoByShopId(shopId);
		if(shopIntroPic != null){
			String logoFileName = shopIntroPic.getShopLogoPath();
			if(logoFileName != null && !logoFileName.equals("")){
				path = this.getPicturePath(shopId, logoFileName, serverName);
			}
		}
		
		model.addAttribute("path", path);
		
		return "shoplogo/shopLogo";
	}
	
	
	//店家LOGO圖片上傳
	@RequestMapping(value = "/shop/shoplogoup", method = RequestMethod.POST)
	public String shopLogoUp(Model model, HttpServletRequest req, HttpServletResponse res, HttpSession s, Locale locale) throws IOException , Exception {

		log.info("店家LOGO圖片上傳：");
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
		String storeLogoPath = sys.getSysPrmValue("deliver", "storeLogoPath");// 取得店家Logo上傳資料夾名稱

			File f;
			long file_size_max = 2097152;// 2m => 1024*1024*2
			HashMap<String, Object> hm = new HashMap<String, Object>();
			boolean isAdd = false;
			boolean insertData = false;
			boolean updateData = false;
			String fileName = "";
			String subfileName = "";
			int fileCount = 0;
			String resultValue = "";
			String path = "";//前台顯示圖片串的路徑
			TimeMachine id = new TimeMachine();
			String filNowTime = id.getNowTime();// 存進資料庫的檔名
			String shrinks = filNowTime + "_logo_100.jpg";// 縮圖後的檔名100*100
			String shrinksTwo = filNowTime + "_logo_295.jpg";// 縮圖後的檔名295*295
			String shrinksSeven = filNowTime + "_logo_800.jpg";// 縮圖後的檔名800*600

			try {
				//先查詢是否有新增過店家LOGO圖片
				isAdd = shopIntroPicService.queryByShopIsAdd(shopId);
				if (!isAdd) {// 檢查無店家

					hm = upload.upload(req, picture + shopId + storeLogoPath, "logo");

					fileName = String.valueOf(hm.get("fileName"));// 檔名名稱
					fileCount = Integer.parseInt(String.valueOf(hm.get("fileCount")));// 上傳成功數
					subfileName = String.valueOf(hm.get("subfileName"));// 副檔名名稱

					if (fileCount > 0) { // 是否上傳圖片成功
						log.info("新增上傳圖檔資料：");
						
						if(subfileName.equals("jpg") || subfileName.equals("png") || subfileName.equals("gif")){//判斷副檔名

							f = new File(picture + shopId + storeLogoPath, fileName);
							if (f.length() < file_size_max) {// 上傳圖檔是否超過4m
	
								// 縮圖
								String upPath = picture + shopId + storeLogoPath + fileName;// 縮圖前的路徑加檔名
								boolean tt = false;
								boolean tu = false;
								boolean th = false;
								tt = image.processImageResize(upPath, "JPEG", 100, 100, picture + shopId + storeLogoPath + shrinks);
								log.info("縮圖100*100是否成功:" + tt);
								tu = image.processImageResize(upPath, "JPEG", 295, 295, picture + shopId + storeLogoPath + shrinksTwo);
								log.info("縮圖295*295是否成功:" + tu);
								th = image.processImageResize(upPath, "JPEG", 800, 600, picture + shopId + storeLogoPath + shrinksSeven);
								log.info("縮圖800*600是否成功:" + th);
								f.delete();// 刪除原始還沒縮圖的圖檔
	
								if (tt && tu && th) {
									//縮圖成功寫進DB
									ShopIntroducePic shopIntroPic = new ShopIntroducePic();
									shopIntroPic.setShopInfoId(id.serial("introPic", 0));
									shopIntroPic.setShopId(shopId);
									shopIntroPic.setShopLogoPath(filNowTime);//檔名名稱
									insertData = shopIntroPicService.createShopLogoPic(shopIntroPic);
									
									if (insertData) {
										//新增成功
										resultValue = lng.getMessage("MSG0002", null, locale);
										path = this.getPicturePath(shopId, filNowTime, serverName);//前台要顯示的圖片路徑
										
									} else {
										//新增DB不成功
										this.deleteLogoFile(shrinks, serverName, shopId);// 刪除縮圖後的圖檔 100*100
										this.deleteLogoFile(shrinksTwo, serverName, shopId);// 刪除縮圖後的圖檔 295*295
										this.deleteLogoFile(shrinksSeven, serverName, shopId);// 刪除縮圖後的圖檔 800*600
										resultValue = lng.getMessage("MSG0003", null, locale);
									}
								} else {
									//縮圖不成功
									this.deleteLogoFile(shrinks, serverName, shopId);// 刪除縮圖後的圖檔 100*100
									this.deleteLogoFile(shrinksTwo, serverName, shopId);// 刪除縮圖後的圖檔 295*295
									this.deleteLogoFile(shrinksSeven, serverName, shopId);// 刪除縮圖後的圖檔 800*600
									resultValue = lng.getMessage("MSG0003", null, locale);
								}
							} else {
								
								this.deleteLogoFile(fileName, serverName, shopId);// 刪除上傳的圖檔
								resultValue = lng.getMessage("picSizeTooBig", null, locale);
							}
						}else{
							this.deleteLogoFile(fileName, serverName, shopId);// 刪除上傳的圖檔
							resultValue = lng.getMessage("fileErroe", null, locale);
						}
					}

					if (fileCount == 0) {
						
						log.info("沒選擇圖檔：");
						resultValue = lng.getMessage("chkSelectPic", null, locale);
					}

				} else {
					
					log.info("修改上傳店家LOGO ：");
					//先取出店家LOGO原來的Path檔名
					ShopIntroducePic shopIntroPic = shopIntroPicService.queryShopLogoByShopId(shopId);
					String logoId = shopIntroPic.getShopInfoId();
					String uploPath = shopIntroPic.getShopLogoPath();//原來檔名
					String shrinks2 = uploPath + "_logo_100.jpg"; //原來縮圖後的圖檔 100*100
					String shrinksTwo2 = uploPath + "_logo_295.jpg"; //原來縮圖後的圖檔 295*295
					String shrinksSeven2 = uploPath + "_logo_800.jpg"; //原來縮圖後的圖檔 800*600
					if(uploPath != null && !uploPath.equals("")){
						path = this.getPicturePath(shopId, uploPath, serverName);//前台要顯示的圖片路徑
					}
					//上傳圖片
					hm = upload.upload(req, picture + shopId + storeLogoPath, "logo");
					fileName = String.valueOf(hm.get("fileName"));// 檔案名稱
					fileCount = Integer.parseInt(String.valueOf(hm.get("fileCount")));// 上傳成功數
					subfileName = String.valueOf(hm.get("subfileName"));// 副檔名名稱

					if (fileCount > 0) { // 是否上傳圖片成功
						
						if(subfileName.equals("jpg") || subfileName.equals("png") || subfileName.equals("gif")){//判斷副檔名

							log.info("修改上傳所有資料：");
							f = new File(picture + shopId + storeLogoPath, fileName);
							if (f.length() < file_size_max) { // 上傳圖檔是否超過4m
	
								// 縮圖
								String upPath = picture + shopId + storeLogoPath + fileName;// 縮圖前的路徑加檔名
								boolean tt = false;
								boolean tu = false;
								boolean th = false;
								tt = image.processImageResize(upPath, "JPEG", 100, 100, picture + shopId + storeLogoPath + shrinks);
								log.info("縮圖100*100是否成功:" + tt);
								tu = image.processImageResize(upPath, "JPEG", 295, 295, picture + shopId + storeLogoPath + shrinksTwo);
								log.info("縮圖295*295是否成功:" + tu);
								th = image.processImageResize(upPath, "JPEG", 800, 600, picture + shopId + storeLogoPath + shrinksSeven);
								log.info("縮圖800*600是否成功:" + th);
								f.delete();// 刪除原始還沒縮圖的圖檔
	
								if (tt && tu && th) {
									//縮圖成功寫進DB
									ShopIntroducePic shopInfoPic = new ShopIntroducePic();
									shopInfoPic.setShopInfoId(logoId);
									shopInfoPic.setShopId(shopId);
									shopInfoPic.setShopLogoPath(filNowTime);// 檔名名稱
									updateData = shopIntroPicService.updateShopLogoPic(shopInfoPic);
	
									if (updateData) {
										path = this.getPicturePath(shopId, filNowTime, serverName);//前台要顯示的圖片路徑
										//修改成功刪除之前的縮圖圖檔
										this.deleteLogoFile(shrinks2, serverName, shopId);// 刪除之前縮圖後的圖檔 100*100
										this.deleteLogoFile(shrinksTwo2, serverName, shopId);// 刪除之前縮圖後的圖檔 295*295
										this.deleteLogoFile(shrinksSeven2, serverName, shopId);// 刪除之前縮圖後的圖檔 800*600
										resultValue = lng.getMessage("MSG0004", null, locale);
									} else {
										//修改進DB失敗
										this.deleteLogoFile(shrinks, serverName, shopId);// 刪除縮圖後的圖檔 100*100
										this.deleteLogoFile(shrinksTwo, serverName, shopId);// 刪除縮圖後的圖檔 295*295
										this.deleteLogoFile(shrinksSeven, serverName, shopId);// 刪除縮圖後的圖檔 800*600
										resultValue = lng.getMessage("MSG0005", null, locale);
									}
								} else {
									//縮圖失敗
									this.deleteLogoFile(shrinks, serverName, shopId);// 刪除縮圖後的圖檔 100*100
									this.deleteLogoFile(shrinksTwo, serverName, shopId);// 刪除縮圖後的圖檔 295*295
									this.deleteLogoFile(shrinksSeven, serverName, shopId);// 刪除縮圖後的圖檔 800*600
									resultValue = lng.getMessage("MSG0005", null, locale);
								}
							} else {
								this.deleteLogoFile(fileName, serverName, shopId);// 刪除上傳的圖檔
								resultValue = lng.getMessage("picSizeTooBig", null, locale);
							}
							
						}else{
							this.deleteLogoFile(fileName, serverName, shopId);// 刪除上傳的圖檔
							resultValue = lng.getMessage("fileErroe", null, locale);
						}
					}

					if (fileCount == 0) {
						log.info("沒選擇檔案：");
						resultValue = lng.getMessage("chkSelectPic", null, locale);
					}
				}
			} catch (IOException ex) {
				resultValue = lng.getMessage("errorUpPic", null, locale)+ex.toString();
				log.error(lng.getMessage("errorUpPic", null, locale) + ex);
				
			}
			f = null;
			hm = null;
			id = null;
			storeVo = null;
			model.addAttribute("msg", resultValue);
			model.addAttribute("path", path);
			
		return "shoplogo/shopLogo";
	}
	
	
	//刪除上傳的檔案
	public void deleteLogoFile(String fileName, String serverName, String shopId) throws Exception {
		String picture = "";
		if(serverName.equals("localhost")){
			picture = "c://xampp/htdocs/img/";
		}else{
			picture = sys.getSysPrmValue("deliver", "picture");// 取得存放路徑
		}
		String storeLogoPath = sys.getSysPrmValue("deliver", "storeLogoPath");// 取得店家Logo上傳資料夾名稱
		
		File f;
		f = new File(picture + shopId + storeLogoPath, fileName);// 刪除之前圖檔
		f.delete();
		f = null;
	}
	
	//刪除店家LOGO圖片
	@ResponseBody
	@RequestMapping(value = "/shop/shoplogodel", method = RequestMethod.POST)
	public Map<String, Object> shopLogoDelete(Model model, HttpServletRequest req, HttpSession s, Locale locale) throws Exception {
		String msgAlert = "";
		boolean updateData = false;
		String serverName = req.getServerName();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		LoginAdminVO storeVo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = storeVo.getShopId();//店家ID
		//判斷店家資訊是否新增過
		boolean chkAdd = shopIntroPicService.queryByShopIsAdd(shopId);
		if(chkAdd){
			//查詢Logo圖檔名
			ShopIntroducePic shopIntroPic = shopIntroPicService.queryShopLogoByShopId(shopId);
			String shopInfoId = shopIntroPic.getShopInfoId();
			String logoFileName = shopIntroPic.getShopLogoPath();
			String shrinks = logoFileName + "_logo_100.jpg";// 縮圖後的檔名100*100
			String shrinksTwo = logoFileName + "_logo_295.jpg";// 縮圖後的檔名295*295
			String shrinksSeven = logoFileName + "_logo_800.jpg";// 縮圖後的檔名800*600
			if(logoFileName != null && !logoFileName.equals("")){
				
				//清除DBLogo寫進""
				//縮圖成功寫進DB
				ShopIntroducePic shopInfoPic = new ShopIntroducePic();
				shopInfoPic.setShopInfoId(shopInfoId);
				shopInfoPic.setShopId(shopId);
				shopInfoPic.setShopLogoPath("");// 檔名名稱
				updateData = shopIntroPicService.updateShopLogoPic(shopInfoPic);
				if(updateData){
					this.deleteLogoFile(shrinks, serverName, shopId);// 刪除縮圖後的圖檔 100*100
					this.deleteLogoFile(shrinksTwo, serverName, shopId);// 刪除縮圖後的圖檔 295*295
					this.deleteLogoFile(shrinksSeven, serverName, shopId);// 刪除縮圖後的圖檔 800*600
					msgAlert = lng.getMessage("delSuccess", null, locale);
				}else{
					msgAlert = lng.getMessage("delError", null, locale);
				}
				
			}else{
				//沒新增過資料
				msgAlert = lng.getMessage("logoUpIsDel", null, locale);
			}
			
		}else{
			//沒新增過
			msgAlert = lng.getMessage("logoUpIsDel", null, locale);
		}
		
			jsonMap.put("msg", msgAlert);
			
		return jsonMap;
	}
	
	
	//Logo圖片路徑+圖片檔名
	public String getPicturePath(String shopId, String logoFileName, String serverName) throws Exception{
		String storeLogoPath = "";
		storeLogoPath  = sys.getSysPrmValue("deliver", "storeLogoPath");
		String domainPicPath = "";
		if(serverName.equals("localhost")){
			domainPicPath = "http://localhost:8080";
		}else{
			domainPicPath = sys.getSysPrmValue("deliver", "domainPicPath");// 取得存放路徑域名
//			domainPicPath = sys.getCPValue("domainPicPath");// 取得存放路徑域名
		}

		StringBuilder sb = new StringBuilder();
		try {
			sb.append(domainPicPath);
			sb.append("/img/");
			sb.append(shopId);
			sb.append(storeLogoPath);
			sb.append(logoFileName+"_logo_295.jpg");	
		} catch (Exception e) {
			log.error("picturePath  error:" +sb.toString()+ e);
		}
		return sb.toString();
	}
	
	

//================================店家LOGO圖片上傳==========End===============================================
	
//================================店家長方型大圖片上傳==========Begin===============================================
	//店家長方型大圖圖片上傳首頁
	@RequestMapping(value = "/shop/rectangularbigpic", method = RequestMethod.GET)
	public String rectangularBigPic(Model model, HttpServletRequest req, HttpSession s) throws HttpSessionRequiredException , Exception {

		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		String serverName = req.getServerName();
		String path = "";
		
		//查詢店家Logo圖片
		// 提出店家照片原來的Path檔名
		ShopIntroducePic shopIntroPic = shopIntroPicService.queryRectangularBigPicByShopId(shopId);
		if(shopIntroPic != null){
			String bigFileName = shopIntroPic.getShopBigPath();
			if(bigFileName != null && !bigFileName.equals("")){
				path = this.getBigPicturePath(shopId, bigFileName, serverName);
			}
		}
		
		model.addAttribute("path", path);
		
		return "rectangularbigpic/rectangularBigPic";
	}
	
	//店家長方型大圖圖片上傳
	@RequestMapping(value = "/shop/shopbigup", method = RequestMethod.POST)
	public String shopBigUp(Model model, HttpServletRequest req, HttpServletResponse res, HttpSession s, Locale locale) throws IOException , Exception {

		log.info("店家長方型大圖圖片上傳：");
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
		String storeBigPath = sys.getSysPrmValue("deliver", "rectangularBigPicPath");// 取得店家長方型大圖上傳資料夾名稱

			File f;
			long file_size_max = 2097152;// 2m => 1024*1024*2
			HashMap<String, Object> hm = new HashMap<String, Object>();
			boolean isAdd = false;
			boolean insertData = false;
			boolean updateData = false;
			String fileName = "";
			String subfileName = "";
			int fileCount = 0;
			String resultValue = "";
			String path = "";//前台顯示圖片串的路徑
			TimeMachine id = new TimeMachine();
			String filNowTime = id.getNowTime();// 存進資料庫的檔名
			String shrinks = filNowTime + "_big_1120.jpg";// 縮圖後的檔名1120*425
			String shrinksSeven = filNowTime + "_big_800.jpg";// 縮圖後的檔名800*600

			try {
				//先查詢是否有新增過店家圖片
				isAdd = shopIntroPicService.queryByShopIsAdd(shopId);
				if (!isAdd) {// 檢查無店家

					hm = upload.upload(req, picture + shopId + storeBigPath, "big");

					fileName = String.valueOf(hm.get("fileName"));// 檔名名稱
					fileCount = Integer.parseInt(String.valueOf(hm.get("fileCount")));// 上傳成功數
					subfileName = String.valueOf(hm.get("subfileName"));// 副檔名名稱

					if (fileCount > 0) { // 是否上傳圖片成功
						log.info("新增上傳圖檔資料：");
						
						if(subfileName.equals("jpg") || subfileName.equals("png") || subfileName.equals("gif")){//判斷副檔名

							f = new File(picture + shopId + storeBigPath, fileName);
							if (f.length() < file_size_max) {// 上傳圖檔是否超過2m
	
								// 縮圖
								String upPath = picture + shopId + storeBigPath + fileName;// 縮圖前的路徑加檔名
								boolean tt = false;
								boolean th = false;
								tt = image.processImageResize(upPath, "JPEG", 1120, 425, picture + shopId + storeBigPath + shrinks);
								log.info("縮圖1120*425是否成功:" + tt);
								th = image.processImageResize(upPath, "JPEG", 800, 600, picture + shopId + storeBigPath + shrinksSeven);
								log.info("縮圖800*600是否成功:" + th);
								f.delete();// 刪除原始還沒縮圖的圖檔
	
								if (tt && th) {
									//縮圖成功寫進DB
									ShopIntroducePic shopIntroPic = new ShopIntroducePic();
									shopIntroPic.setShopInfoId(id.serial("introPic", 0));
									shopIntroPic.setShopId(shopId);
									shopIntroPic.setShopBigPath(filNowTime);//檔名名稱
									insertData = shopIntroPicService.createRectangularBigPic(shopIntroPic);
									
									if (insertData) {
										//新增成功
										resultValue = lng.getMessage("MSG0002", null, locale);
										path = this.getBigPicturePath(shopId, filNowTime, serverName);//前台要顯示的圖片路徑
										
									} else {
										//新增DB不成功
										this.deleteBigFile(shrinks, serverName, shopId);// 刪除縮圖後的圖檔 1120*425
										this.deleteBigFile(shrinksSeven, serverName, shopId);// 刪除縮圖後的圖檔 800*600
										resultValue = lng.getMessage("MSG0003", null, locale);
									}
								} else {
									//縮圖不成功
									this.deleteBigFile(shrinks, serverName, shopId);// 刪除縮圖後的圖檔 1120*425
									this.deleteBigFile(shrinksSeven, serverName, shopId);// 刪除縮圖後的圖檔 800*600
									resultValue = lng.getMessage("MSG0003", null, locale);
								}
							} else {
								this.deleteBigFile(fileName, serverName, shopId);// 刪除上傳的圖檔
								resultValue = lng.getMessage("picSizeTooBig", null, locale);
							}
						}else{
							this.deleteBigFile(fileName, serverName, shopId);// 刪除上傳的圖檔
							resultValue = lng.getMessage("fileErroe", null, locale);
						}
					}

					if (fileCount == 0) {
						
						log.info("沒選擇圖檔：");
						resultValue = lng.getMessage("chkSelectPic", null, locale);
					}

				} else {
					
					log.info("修改上傳店家長方型大圖 ：");
					//先取出店家原來的Path檔名
					ShopIntroducePic shopIntroPic = shopIntroPicService.queryRectangularBigPicByShopId(shopId);
					String shopInfoId = shopIntroPic.getShopInfoId();
					String uploPath = shopIntroPic.getShopBigPath();//原來檔名
					String shrinks2 = uploPath + "_big_1120.jpg"; //原來縮圖後的圖檔 1120*425
					String shrinksSeven2 = uploPath + "_big_800.jpg"; //原來縮圖後的圖檔 800*600
					if(uploPath != null && !uploPath.equals("")){
						path = this.getBigPicturePath(shopId, uploPath, serverName);//前台要顯示的圖片路徑
					}
					//上傳圖片
					hm = upload.upload(req, picture + shopId + storeBigPath, "big");
					fileName = String.valueOf(hm.get("fileName"));// 檔案名稱
					fileCount = Integer.parseInt(String.valueOf(hm.get("fileCount")));// 上傳成功數
					subfileName = String.valueOf(hm.get("subfileName"));// 副檔名名稱

					if (fileCount > 0) { // 是否上傳圖片成功
						
						if(subfileName.equals("jpg") || subfileName.equals("png") || subfileName.equals("gif")){//判斷副檔名

							log.info("修改上傳所有資料：");
							f = new File(picture + shopId + storeBigPath, fileName);
							if (f.length() < file_size_max) { // 上傳圖檔是否超過2m
	
								// 縮圖
								String upPath = picture + shopId + storeBigPath + fileName;// 縮圖前的路徑加檔名
								boolean tt = false;
								boolean th = false;
								tt = image.processImageResize(upPath, "JPEG", 1120, 425, picture + shopId + storeBigPath + shrinks);
								log.info("縮圖1120*425是否成功:" + tt);
								th = image.processImageResize(upPath, "JPEG", 800, 600, picture + shopId + storeBigPath + shrinksSeven);
								log.info("縮圖800*600是否成功:" + th);
								f.delete();// 刪除原始還沒縮圖的圖檔
	
								if (tt && th) {
									//縮圖成功寫進DB
									ShopIntroducePic shopInfoPic = new ShopIntroducePic();
									shopInfoPic.setShopInfoId(shopInfoId);
									shopInfoPic.setShopId(shopId);
									shopInfoPic.setShopBigPath(filNowTime);// 檔名名稱
									updateData = shopIntroPicService.updateRectangularBigPic(shopInfoPic);
	
									if (updateData) {
										path = this.getBigPicturePath(shopId, filNowTime, serverName);//前台要顯示的圖片路徑
										//修改成功刪除之前的縮圖圖檔
										this.deleteBigFile(shrinks2, serverName, shopId);// 刪除之前縮圖後的圖檔 1120*425
										this.deleteBigFile(shrinksSeven2, serverName, shopId);// 刪除之前縮圖後的圖檔 800*600
										resultValue = lng.getMessage("MSG0004", null, locale);
									} else {
										//修改進DB失敗
										this.deleteBigFile(shrinks, serverName, shopId);// 刪除縮圖後的圖檔 1120*425
										this.deleteBigFile(shrinksSeven, serverName, shopId);// 刪除縮圖後的圖檔 800*600
										resultValue = lng.getMessage("MSG0005", null, locale);
									}
								} else {
									//縮圖失敗
									this.deleteBigFile(shrinks, serverName, shopId);// 刪除縮圖後的圖檔 1120*425
									this.deleteBigFile(shrinksSeven, serverName, shopId);// 刪除縮圖後的圖檔 800*600
									resultValue = lng.getMessage("MSG0005", null, locale);
								}
							} else {
								this.deleteBigFile(fileName, serverName, shopId);// 刪除上傳的圖檔
								resultValue = lng.getMessage("picSizeTooBig", null, locale);
							}
							
						}else{
							this.deleteBigFile(fileName, serverName, shopId);// 刪除上傳的圖檔
							resultValue = lng.getMessage("fileErroe", null, locale);
						}
					}

					if (fileCount == 0) {
						log.info("沒選擇檔案：");
						resultValue = lng.getMessage("chkSelectPic", null, locale);
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
			model.addAttribute("path", path);
			
			return "rectangularbigpic/rectangularBigPic";
	}
	
	
	//刪除店家長方型大圖圖片
	@ResponseBody
	@RequestMapping(value = "/shop/shopbigdel", method = RequestMethod.POST)
	public Map<String, Object> shopBigDelete(Model model, HttpServletRequest req, HttpSession s, Locale locale) throws Exception {
		String msgAlert = "";
		boolean updateData = false;
		String serverName = req.getServerName();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		LoginAdminVO storeVo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = storeVo.getShopId();//店家ID
		//判斷店家資訊是否新增過
		boolean chkAdd = shopIntroPicService.queryByShopIsAdd(shopId);
		if(chkAdd){
			//查詢長方型大圖圖檔名
			ShopIntroducePic shopIntroPic = shopIntroPicService.queryRectangularBigPicByShopId(shopId);
			String shopInfoId = shopIntroPic.getShopInfoId();
			String shopFileName = shopIntroPic.getShopBigPath();
			String shrinks = shopFileName + "_big_1120.jpg";// 縮圖後的檔名1120*425
			String shrinksSeven = shopFileName + "_big_800.jpg";// 縮圖後的檔名800*600
			if(shopFileName != null && !shopFileName.equals("")){
				
				//清除DB寫進""
				//縮圖成功寫進DB
				ShopIntroducePic shopInfoPic = new ShopIntroducePic();
				shopInfoPic.setShopInfoId(shopInfoId);
				shopInfoPic.setShopId(shopId);
				shopInfoPic.setShopBigPath("");// 檔名名稱
				updateData = shopIntroPicService.updateRectangularBigPic(shopInfoPic);
				if(updateData){
					this.deleteBigFile(shrinks, serverName, shopId);// 刪除縮圖後的圖檔 1120*425
					this.deleteBigFile(shrinksSeven, serverName, shopId);// 刪除縮圖後的圖檔 800*600
					msgAlert = lng.getMessage("delSuccess", null, locale);
				}else{
					msgAlert = lng.getMessage("delError", null, locale);
				}
				
			}else{
				//沒新增過資料
				msgAlert = lng.getMessage("logoUpIsDel", null, locale);
			}
			
		}else{
			//沒新增過
			msgAlert = lng.getMessage("logoUpIsDel", null, locale);
		}
		
			jsonMap.put("msg", msgAlert);
			
		return jsonMap;
	}
	
	
	//刪除上傳長方型大圖的檔案
	public void deleteBigFile(String fileName, String serverName, String shopId) throws Exception {
		String picture = "";
		if(serverName.equals("localhost")){
			picture = "c://xampp/htdocs/img/";
		}else{
			picture = sys.getSysPrmValue("deliver", "picture");// 取得存放路徑
		}
		String storeBigPath = sys.getSysPrmValue("deliver", "rectangularBigPicPath");// 取得店家上傳資料夾名稱
		
		File f;
		f = new File(picture + shopId + storeBigPath, fileName);// 刪除之前圖檔
		f.delete();
		f = null;
	}
	
	
	//長方型大圖圖片路徑+圖片檔名
	public String getBigPicturePath(String shopId, String bigFileName, String serverName) throws Exception{
		String storebigPath = "";
		storebigPath  = sys.getSysPrmValue("deliver", "rectangularBigPicPath");
		String domainPicPath = "";
		if(serverName.equals("localhost")){
			domainPicPath = "http://localhost:8080";
		}else{
			domainPicPath = sys.getSysPrmValue("deliver", "domainPicPath");// 取得存放路徑域名
//			domainPicPath = sys.getCPValue("domainPicPath");// 取得存放路徑域名
		}

		StringBuilder sb = new StringBuilder();
		try {
			sb.append(domainPicPath);
			sb.append("/img/");
			sb.append(shopId);
			sb.append(storebigPath);
			sb.append(bigFileName+"_big_1120.jpg");	
		} catch (Exception e) {
			log.error("picturePath  error:" +sb.toString()+ e);
		}
		return sb.toString();
	}
	
//================================店家長方型大圖片上傳==========End===============================================
	
//================================店家所有照片上傳管理==========Begin===============================================
	//店家所有照片上傳首頁
	@RequestMapping(value = "/shop/shopallpic", method = RequestMethod.GET)
	public String shopAllPicture(Model model, HttpServletRequest req, HttpSession s) throws Exception {

		
		return "shopallpicture/shopAllPicture";
	}
	
	//導編輯店家所有照片上傳頁面
	@RequestMapping(value = "/shop/storeallpicturecore", method = RequestMethod.GET)
	public String storeAllPictureCore(Model model, HttpServletRequest req, HttpSession s) throws Exception {

		
		return "shopallpicture/shopStoreAllPictureCore";
	}
	
	
	//店家所有照片上傳
	@ResponseBody
	@RequestMapping(value = "/shop/shopallpictureup", method = RequestMethod.POST )
	public Map<String, Object> shopAllPictureUp(Model model, HttpServletRequest req, HttpSession s, Locale locale) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		log.info("上傳店家所有照片：");
		
		String serverName = req.getServerName();
		String picture = "";
		if(serverName.equals("localhost")){
			picture = "c://xampp/htdocs/img/";
		}else{
			picture = sys.getSysPrmValue("deliver", "picture");// 取得存放路徑
		}
		String pictureAllPath = sys.getSysPrmValue("deliver", "pictureAllPath");// 取得店家所有照片上傳資料夾名稱
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		log.info("shopId*********:" + shopId);
			
			HashMap<String, Object> hm = new HashMap<String, Object>();
			long file_size_max = 2097152;// 2m => 1024*1024*2
			String pictureDescrp = "";
			int fileCount = 0;// 上傳成功數
			String fileName = "";// 檔名 名稱
			String subfileName = "";
			String resultValue = lng.getMessage("MSG0007", null, locale);
			boolean insertData;
			TimeMachine id = new TimeMachine();
			String filNowTime = id.getNowTime();// 存進資料庫的檔名
			String shrinks = filNowTime + "_photo_425.jpg";// 縮圖後的檔名
			String shrinksSeven = filNowTime + "_photo_800.jpg";// 縮圖後的檔名

			hm = upload.upload(req, picture + shopId + pictureAllPath, "photo");
			// 檔名 名稱
			fileName = String.valueOf(hm.get("fileName"));
			// 上傳成功數
			fileCount = Integer.parseInt(String.valueOf(hm.get("fileCount")));
			subfileName = String.valueOf(hm.get("subfileName"));// 副檔名名稱

			if (fileCount > 0) {
				
				if(subfileName.equals("jpg") || subfileName.equals("png") || subfileName.equals("gif")){//判斷副檔名
	
					File f = new File(picture + shopId + pictureAllPath, fileName);
					if (f.length() < file_size_max) { // 上傳圖檔是否超過2m
	
						// 縮圖
						String upPath = picture + shopId + pictureAllPath + fileName;// 縮圖前的路徑加檔名
						boolean tt = false;
						boolean th = false;
						tt = image.processImageResize(upPath, "JPEG", 425, 349, picture + shopId + pictureAllPath + shrinks);
						th = image.processImageResize(upPath, "JPEG", 800, 600, picture + shopId + pictureAllPath + shrinksSeven);
						f.delete();// 刪除原始還沒縮圖的圖檔
						log.info("縮圖是否成功:" + tt);
						log.info("縮圖是否成功:" + th);
	
						if (tt && th) {
							//寫進DB
							ShopAllPicture shopAllPicture = new ShopAllPicture();
							shopAllPicture.setUploId(id.serial("photo", 0));
							shopAllPicture.setShopId(shopId);
							shopAllPicture.setUploPath(filNowTime);//檔名
							shopAllPicture.setUploDescrp(pictureDescrp);
							insertData = shopIntroPicService.createShopAllPic(shopAllPicture);
							
							if (insertData) {
								//上傳成功
								resultValue = lng.getMessage("MSG0006", null, locale);
							} else {
								//寫進DB失敗
								this.deleteAllPictureFile(shrinks, serverName, shopId);// 刪除縮圖後的圖檔 1120*425
								this.deleteAllPictureFile(shrinksSeven, serverName, shopId);// 刪除縮圖後的圖檔 800*600
								resultValue = lng.getMessage("MSG0007", null, locale);
							}
						} else {
							//縮圖失敗
							this.deleteAllPictureFile(shrinks, serverName, shopId);// 刪除縮圖後的圖檔 1120*425
							this.deleteAllPictureFile(shrinksSeven, serverName, shopId);// 刪除縮圖後的圖檔 800*600
							resultValue = lng.getMessage("MSG0007", null, locale);
						}
						
					} else {
						this.deleteBigFile(fileName, serverName, shopId);// 刪除上傳的圖檔
						resultValue = lng.getMessage("MSG0001", null, locale);
					}
					
				}else{
					this.deleteBigFile(fileName, serverName, shopId);// 刪除上傳的圖檔
					resultValue = lng.getMessage("fileErroe", null, locale);
				}
			}else{
				if (fileCount == 0) {
					log.info("沒選擇檔案：");
					resultValue = lng.getMessage("chkSelectPic", null, locale);
				}
			}

			
			id = null;
			hm = null;
			jsonMap.put("msg", resultValue);
		
		return jsonMap;
	}
	
	//修改店家照片上傳管理照片說明資料
	@ResponseBody
	@RequestMapping(value = "/shop/updatepicmessage", method = RequestMethod.POST )
	public Map<String, Object> updatePicMessage(Model model, @RequestParam(required=false) String path, @RequestParam(required=false) String name, HttpServletRequest req, HttpSession s) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		log.info("修改照片說明：");
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		log.info("shopId*********:" + shopId);
		boolean error = false;
		boolean count = false;
		boolean insertData;
			
			ShopAllPicture shopAllPicture = new ShopAllPicture();
			shopAllPicture.setShopId(shopId);
			shopAllPicture.setUploPath(path);//檔名
			shopAllPicture.setUploDescrp(name);//訊息
			insertData = shopIntroPicService.updateShopPicMessage(shopAllPicture);
			
			if (insertData) {
				count = true;
				error = false;
			} else {
				error = true;
				count = false;
			}
			
			jsonMap.put("success", count);
			jsonMap.put("error", error);

			shopAllPicture = null;

		return jsonMap;
	}
	
	//刪除店家照片管理上傳的照片
	@ResponseBody
	@RequestMapping(value = "/shop/deleteshoppic", method = RequestMethod.POST )
	public Map<String, Object> deleteShopPic(Model model, @RequestParam(required=false) String path, HttpServletRequest req, HttpSession s) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
	
		log.info("刪除店家照片：");
		
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		log.info("shopId*********:" + shopId);
		String serverName = req.getServerName();
		boolean error = false;
		String shrinks = path + "_photo_425.jpg";// 縮圖後的檔名
		String shrinksSeven = path + "_photo_800.jpg";// 縮圖後的檔名

			boolean count = false;
			boolean insertData;
			ShopAllPicture shopAllPicture = new ShopAllPicture();
			shopAllPicture.setShopId(shopId);
			shopAllPicture.setUploPath(path);//檔名
			insertData = shopIntroPicService.deleteShopPic(shopAllPicture);

			if (insertData) {
				this.deleteAllPictureFile(shrinks, serverName, shopId);// 刪除縮圖後的圖檔 1120*425
				this.deleteAllPictureFile(shrinksSeven, serverName, shopId);// 刪除縮圖後的圖檔 800*600
				count = true;
				error = false;
			} else {
				error = true;
				count = false;
			}
			
			jsonMap.put("success", count);
			jsonMap.put("error", error);

			shopAllPicture = null;
		
		return jsonMap;
	}
	
	
	//載入店家所有照片
	@ResponseBody
	@RequestMapping(value = "/shop/loadallpic", method = RequestMethod.POST , headers="Accept=application/json")
	public Map<String, Object> loadAllPic(Model model, HttpServletRequest req, HttpSession s) throws Exception {
		
		log.info("載入店家所有照片：");

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String serverName = req.getServerName();
		LoginAdminVO loginVO = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = loginVO.getShopId();
		log.info("shopId*********:" + shopId);

			// 提出資料庫店家照片原來的Path檔名
			List<ShopAllPicture> picList = shopIntroPicService.queryShopAllPicByShopId(shopId);

			String fileName[] = new String[picList.size()];
			String filePath[] = new String[picList.size()];
			String titleText[] = new String[picList.size()];
			int i=0;
			for(ShopAllPicture modelPic : picList){
				fileName[i] = modelPic.getUploPath();
				filePath[i] = this.getAllPicturePath(shopId, fileName[i], serverName);
				titleText[i] = modelPic.getUploDescrp();
				i++;
			}
			
			jsonMap.put("filename", fileName);
			jsonMap.put("thumb", filePath);
			jsonMap.put("title", titleText);
			
			fileName = null;
			filePath = null;
			titleText = null;
		
		return jsonMap;
	}
	
	//刪除店家所有照片圖圖檔
	public void deleteAllPictureFile(String fileName, String serverName, String shopId) throws Exception {
		String picture = "";
		if(serverName.equals("localhost")){
			picture = "c://xampp/htdocs/img/";
		}else{
			picture = sys.getSysPrmValue("deliver", "picture");// 取得存放路徑
		}
		String pictureAllPath = sys.getSysPrmValue("deliver", "pictureAllPath");// 取得店家上傳資料夾名稱
		
		File f;
		f = new File(picture + shopId + pictureAllPath, fileName);// 刪除之前圖檔
		f.delete();
		f = null;
	}
	
	//店家所有照片圖片路徑+圖片檔名
	public String getAllPicturePath(String shopId, String fileName, String serverName) throws Exception{
		String storePath = "";
		storePath  = sys.getSysPrmValue("deliver", "pictureAllPath");
		String domainPicPath = "";
		if(serverName.equals("localhost")){
			domainPicPath = "http://localhost:8080";
		}else{
			domainPicPath = sys.getSysPrmValue("deliver", "domainPicPath");// 取得存放路徑域名
//			domainPicPath = sys.getCPValue("domainPicPath");// 取得存放路徑域名
		}

		StringBuilder sb = new StringBuilder();
		try {
			sb.append(domainPicPath);
			sb.append("/img/");
			sb.append(shopId);
			sb.append(storePath);
			sb.append(fileName+"_photo_425.jpg");	
		} catch (Exception e) {
			log.error("picturePath  error:" +sb.toString()+ e);
		}
		return sb.toString();
	}
	
//================================店家所有照片上傳管理==========End===============================================

}