package com.order.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.order.mode.ShopMemData;
import com.order.mode.vo.LoginAdminVO;
import com.order.mode.vo.SMSRtnVO;
import com.order.mode.vo.SmsPOVO;
import com.order.service.LoginService;
import com.order.service.VerifyService;
import com.order.util.JSONUtils;
import com.order.util.SMSVO;
import com.order.util.SecretUtil;
import com.order.util.SmsUtil;
import com.order.util.SysParameterUtil;
import com.order.util.TimeMachine;
import com.order.util.Tools;
import com.order.util.email.Email;
import com.order.util.email.MailUtil;



@Controller
@RequestMapping("/loginadmin")
public class LoginadminController extends BaseController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private Tools tls;
	
	@Autowired
	private SysParameterUtil spu;
	
	@Autowired
	private MessageSource lng;
	
	@Autowired
	private SecretUtil sert;
	
	@Autowired
	private JSONUtils jsonTool;
	
	@Autowired
	private SysParameterUtil sysutil;
	
	@Autowired
	private MailUtil mailUtil;
	
	@Autowired
	private VerifyService verifyService;

//	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private static final int IntHours = 1;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model, HttpSession session)
			throws HttpSessionRequiredException {


		return "redirect:/loginadmin/login";
	}
	
	//檢查使用者是否需重新輸入帳密
	@RequestMapping(value = "/login",  method = {RequestMethod.GET, RequestMethod.POST})
	public String loginShopAdmin(Model model, HttpSession s,
			HttpServletRequest req, HttpServletResponse res, Locale locale) throws Exception {
		
		//檢查使用者是否需重新輸入帳密
//		HttpSession s = req.getSession(false);
		String strShopId = "";
		String strPassword = "";
		LoginAdminVO vo = null;	
//		String strLevelName = "";//會員層級名稱
//		String strEndDate = "";//該會員到期日		
		String strPage = "loginadmin/loginShopAdmin";//記錄需跳轉到那一個頁面
//		String secrtName = "";//需加密的網域
		Tools tls = new Tools();
		
		if( s.getAttribute("loginVo") != null){	
		   try {
			    vo = (LoginAdminVO)s.getAttribute("loginVo");
			
			    strShopId = vo.getShopId();
			    strPassword = vo.getUserPw();
			    
			    vo = loginService.queryFun(strShopId, sert.decrypt(strPassword)); //查詢店家資料//因店家這段時間可能已進行付款，故在此重新取得店家資料
			
			    if( vo != null){//若為null代表此帳號可能已被另一人更改
				   //取得會員層級卡名 start
//				   strLevelName = getLevelName(vo.getShopLevel());
//				   vo.setLoginShopLevelName(strLevelName);
				   //取得會員層級卡名 end
				
				   //取得到期日 start
//				   strEndDate = getEndDate(vo);		
//				   vo.setLoginShopEndDate(strEndDate);	
				   
				   s.setMaxInactiveInterval(60 * 60 * IntHours);
				   s.setAttribute("loginVo",vo);//重新寫入session
				   
				   //設定登入Session
				   s.setAttribute("loginHomeSession", "loginHomeSession");
				   s.setMaxInactiveInterval(60 * 60 * IntHours);
				
				   //檢查是否超過七天付款期限
				   /*if( chkExpirationDate(vo.getBeginDate(),vo.getEndDate(),req) ){
						
						if(req.getServerName().equalsIgnoreCase(secrtName)){
						   //res.sendRedirect("http://" + req.getServerName() + "/loginShopAdminAction.do");
						   strPage = "https";
						} else { 
						   strPage = "shopAdminIndex";
						}
				   } else {
					   strPage = "shopMemFeeQuery";
				   }*/
				   
				   strPage = "loginadmin/shopAdminIndex";
				   
			   } else {//帳號可能已由另一人更改，故須移除session及cookie
				   s.removeAttribute("loginVo");
				   tls.removeCookie(res, req, "ShopId");
				   tls.removeCookie(res, req, "Password");
				   //移除登入Session
				   s.removeAttribute("loginHomeSession");
			   }
		   } catch(Exception e){
			   log.error("loginShopAdmin:"+e.getMessage());
		   }
		   
		} else {
			
			try {
//			     secrtName = StringUtils.trimToEmpty(spu.getCPValue("secrtName").trim());			
			     vo = new LoginAdminVO();
			
			     strShopId = tls.getCookie(req, "ShopId");
			     strPassword = tls.getCookie(req, "Password");	
			
			     if( strShopId != "" && strPassword != "" && strShopId.length() > 0 && strPassword.length() > 0 ){				
				    vo = loginService.queryFun(strShopId, sert.decrypt(strPassword));
				
				    //若vo = null代表帳號或密碼有誤，因這時值是由cookie取得，代表帳密有所變動，所以須將cookie中的值移除，以免網頁不停reload
				    if(vo == null){
					   tls.removeCookie(res, req, "ShopId");
					   tls.removeCookie(res, req, "Password");
					   //移除登入Session
					   s.removeAttribute("loginHomeSession");
					   model.addAttribute("msg", lng.getMessage("MSG0014", null, locale));
					   strPage = "loginadmin/loginShopAdmin";
						
					   return strPage;
				    } else {	
					   //取得會員層級卡名 start
//					   strLevelName = getLevelName(vo.getShopLevel());
//					   vo.setLoginShopLevelName(strLevelName);
					   //取得會員層級卡名 end
					
					   //取得到期日 start
//					   strEndDate = getEndDate(vo);		
//					   vo.setLoginShopEndDate(strEndDate);
					   //取得到期日 end		
					   
					   s.setMaxInactiveInterval(60 * 60 * IntHours);
					   s.setAttribute("loginVo", vo);//寫入session
					   
					   //設定登入Session
					   s.setAttribute("loginHomeSession", "loginHomeSession");
					   s.setMaxInactiveInterval(60 * 60 * IntHours);
	
					   //檢查是否超過七天付款期限
					  /* if(chkExpirationDate(vo.getBeginDate(),vo.getEndDate(),req) ){
												
						  if(req.getServerName().equalsIgnoreCase(secrtName)){
						     //res.sendRedirect("http://" + req.getServerName() + "/loginShopAdminAction.do");
							 strPage = "https";
						  } else {			
							 strPage = "shopAdminIndex";
						  }
					   } else {
						  strPage = "shopMemFeeQuery";
					   }*/
					   
					   strPage = "loginadmin/shopAdminIndex";
				     }
			     } else {
			    	 strPage = "loginadmin/loginShopAdmin";
			     }
			} catch(Exception e){
				log.error("loginShopAdmin:"+e.getMessage());
			}
		}
		
		
		return strPage;
	}
	
	//檢查帳密是否正確
	@RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginShopCheck(@ModelAttribute("shopId") String shopId,
			@ModelAttribute("password") String password, @ModelAttribute("chkRememberMe") String chkRememberMe,
			Model model, HttpSession s,
			HttpServletRequest req, HttpServletResponse res, Locale locale) throws HttpSessionRequiredException,
			Exception {
		
        log.info("home");
//        HttpSession s = req.getSession(false);
		Tools tls = new Tools();
		LoginAdminVO vo = new LoginAdminVO();
		String strPage = "loginadmin/loginShopAdmin";// 指定要導到那個頁面
//		String strEndDate = "";// 到期日
//		String secrtName = StringUtils.trimToEmpty(spu.getCPValue("secrtName").trim());	
		
		//沒登入Session，導回登入頁面
		if(s.getAttribute("loginHomeSession") == null ){
			strPage = "loginadmin/loginShopAdmin";
			return strPage;
		}
		
		if (s.getAttribute("loginVo") == null) {// 之所以檢查session是為防止使用者於網址列重新按下enter
//			if( req.getMethod().equals("POST") ){
				
				if (tls.chkEngOrNum(shopId) && tls.chkEngOrNum(password)
						&& !tls.chkReqValue(req, shopId, 4, 30)
						&& !tls.chkReqValue(req, password, 4, 30)) {

					vo = loginService.queryFun(shopId, password); //查詢店家資料
					// 若vo = null代表帳號或密碼有誤
					if (vo == null) {
						//移除Cookie
						tls.removeCookie(res, req, "ShopId");
						tls.removeCookie(res, req, "Password");
						//移除登入Session
						s.removeAttribute("loginHomeSession");
						model.addAttribute("msg", lng.getMessage("MSG0014", null, locale));
						strPage = "loginadmin/loginShopAdmin";
						return strPage;
					} else {

						//取得會員層級卡名 start
//						strLevelName = getLevelName(vo.getShopLevel());
//						vo.setLoginShopLevelName(strLevelName);
						//取得會員層級卡名 end
						
						// 取得到期日 start
//						strEndDate = getEndDate(vo);
//						vo.setLoginShopEndDate(strEndDate);
						// log.info("strEndDate: " + strEndDate );
						// 取得到期日 end
						
						s.setMaxInactiveInterval(60 * 60 * IntHours);
						s.setAttribute("loginVo", vo);//重新寫入session
						
						if( StringUtils.isNotBlank( chkRememberMe ) ){
							//將帳密寫入cookie
							tls.setCookie(res,"ShopId",shopId,30);//30天
							tls.setCookie(res,"Password",sert.encrypt(password),30);//30天
						}
						
						//檢查是否超過七天付款期限，若超出回傳false
						/*if( chkExpirationDate(vo.getBeginDate(),vo.getEndDate(),req) ){
							if(req.getServerName().equalsIgnoreCase(secrtName))
								strPage = "https";
							else 		
								strPage = "shopAdminIndex";
						}else
							strPage = "shopMemFeeQuery";//付款頁面
*/
						strPage = "loginadmin/shopAdminIndex";
					}
				}
//			}
		}else{
			
			vo = (LoginAdminVO)s.getAttribute("loginVo");
			String shopId1 = vo.getShopId();
			String password1 = vo.getUserPw();
			
			String encPassword = sert.encrypt(password);//加密比對
			
			//因帳密和loginVo的session帳密傳進來輸入的不對，以新帳密所以重新檢查帳密
			if(!shopId.equals(shopId1) || !encPassword.equals(password1)){//密碼檢核和原來不對，進行下一步檢核
				//重新檢查是否有此帳號密碼
				if (tls.chkEngOrNum(shopId) && tls.chkEngOrNum(password)
						&& !tls.chkReqValue(req, shopId, 4, 30)
						&& !tls.chkReqValue(req, password, 4, 30)) {
	
					vo = loginService.queryFun(shopId, password); //查詢店家資料
					// 若vo = null代表帳號或密碼有誤
					if (vo == null) {
						//移除Cookie
						tls.removeCookie(res, req, "ShopId");
						tls.removeCookie(res, req, "Password");
						//移除登入Session
						s.removeAttribute("loginHomeSession");
						model.addAttribute("msg", lng.getMessage("MSG0014", null, locale));
						strPage = "loginadmin/loginShopAdmin";
						
						return strPage;
					}else{
						//以新帳密登入重新取得資料 =〉指和loginVo session不同帳密
						
						//取得會員層級卡名 start
//						strLevelName = getLevelName(vo.getShopLevel());
//						vo.setLoginShopLevelName(strLevelName);
						//取得會員層級卡名 end
						
						// 取得到期日 start
//						strEndDate = getEndDate(vo);
//						vo.setLoginShopEndDate(strEndDate);
						// log.info("strEndDate: " + strEndDate );
						// 取得到期日 end
						
						s.setMaxInactiveInterval(60 * 60 * IntHours);
						s.setAttribute("loginVo", vo);//重新寫入session
						
						if( StringUtils.isNotBlank( chkRememberMe ) ){
							//將帳密寫入cookie
							tls.setCookie(res,"ShopId",shopId,30);//30天
							tls.setCookie(res,"Password",sert.encrypt(password),30);//30天
						}
						
						//檢查是否超過七天付款期限，若超出回傳false
						/*if( chkExpirationDate(vo.getBeginDate(),vo.getEndDate(),req) ){
							if(req.getServerName().equalsIgnoreCase(secrtName))
								strPage = "https";
							else 		
								strPage = "shopAdminIndex";
						}else
							strPage = "shopMemFeeQuery";//付款頁面
*/
						strPage = "loginadmin/shopAdminIndex";
						
						return strPage;
					}
				}
			}
				
			//以原有的loginVo session帳密登入進行取資料
			
			vo = loginService.queryFun(shopId1, sert.decrypt(password1));//因店家這段時間可能已進行付款，故在此重新取得店家資料
			
			//取得會員層級卡名 start
//			strLevelName = getLevelName(vo.getShopLevel());
//			vo.setLoginShopLevelName(strLevelName);
			//取得會員層級卡名 end
			
			//取得到期日 start
//			strEndDate = getEndDate(vo);		
//			vo.setLoginShopEndDate(strEndDate);			
			s.setMaxInactiveInterval(60 * 60 * IntHours);
			s.setAttribute("loginVo",vo);//重新寫入session
			
			//檢查是否超過七天付款期限，若超出回傳false
			/*if( chkExpirationDate(vo.getBeginDate(),vo.getEndDate(),req) ){
				if(req.getServerName().equalsIgnoreCase(secrtName))
					strPage = "https";
				else 		
					strPage = "shopAdminIndex";
			}	
			else
				strPage = "shopMemFeeQuery"; //付款頁面 */	
			
			strPage = "loginadmin/shopAdminIndex";
		}

		return strPage;
	}
	
	
	//從畫面帳密登入設定登入Session
	@RequestMapping(value = "/loginChick", method = RequestMethod.POST)
	public String loginSetSession(@RequestParam String shopId, @RequestParam String password,
			@RequestParam(required=false) String chkRememberMe, RedirectAttributes attr,
			Model model, HttpServletRequest req, HttpServletResponse res, HttpSession s) throws Exception {

		//設定登入Session
		s.setAttribute("loginHomeSession", "loginHomeSession");
		s.setMaxInactiveInterval(60 * 60 * IntHours);
		attr.addFlashAttribute("shopId", shopId);  
        attr.addFlashAttribute("password", password);
        attr.addFlashAttribute("chkRememberMe", chkRememberMe); 
		
		return "redirect:/loginadmin/home";
	}
	
	//登出
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession s, HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		Tools tls = new Tools();
				
		if( s.getAttribute("loginVo") != null ){
			s.removeAttribute("loginVo");
			tls.removeCookie(res, req, "ShopId");
			tls.removeCookie(res, req, "Password");
			//移除登入Session
			s.removeAttribute("loginHomeSession");
		}
		
		return "loginadmin/loginShopAdmin";
	}
	
	

	// 取得到期日
	public String getEndDate(LoginAdminVO vo, Locale locale) throws Exception {
		// log.info("end date: " + vo.getEndDate());
		// Date now = new Date();
		Calendar newEndDate = Calendar.getInstance();
		String strEndDate = "";
//		Language lng = new Language();

		// 如果到期日為null或到期日等於起始日，代表店家未付款，這時到期日用開始日往後加七天，代表給予店家的付款期間
		if (vo.getEndDate() == null
				|| (vo.getEndDate().getTime() == vo.getBeginDate().getTime())) {
			newEndDate.setTime(vo.getBeginDate());// 開始日
			// newEndDate.add(Calendar.MONTH, 1);//一個月，原先試用期
			newEndDate.add(Calendar.DATE, 30);// 加30天，若下次登入檢查超過此日期則一律強制導到付款頁面

		} else
			newEndDate.setTime(vo.getEndDate());// 結束日

		strEndDate = newEndDate.get(Calendar.YEAR) + lng.getMessage("Year", null, locale) 
				+ (newEndDate.get(Calendar.MONTH) + 1) + lng.getMessage("Month", null, locale) 
				+ newEndDate.get(Calendar.DATE) + lng.getMessage("Date", null, locale);

		lng = null;
		// now = null;

		return strEndDate;
	}
	
	
	//檢查是否超過七天付款期限，若超出回傳false
	public boolean chkExpirationDate(Date startDate,Date endDate,HttpServletRequest req)throws Exception{
		boolean blChk = true;
		Date now = new Date();
		
		if( endDate == null || startDate.getTime() == endDate.getTime() ){//若結束時間是null則代表店家未進行付款動作
			
			Calendar nowDate = Calendar.getInstance();//目前時間
			Calendar newEndDate = Calendar.getInstance();//結束時間
			LoginAdminVO vo = null;
			HttpSession s = req.getSession(false);
			
			nowDate.setTime(now);
			
			vo = (LoginAdminVO)s.getAttribute("loginVo");
			newEndDate.setTime(vo.getBeginDate());//開始日
			newEndDate.add(Calendar.DATE, 30);//加7天，若下次登入檢查超過此日期則一律強制導到付款頁面
			
			//log.info("nowDate.getTimeInMillis() " + nowDate.getTimeInMillis() + " newEndDate.getTimeInMillis() " + newEndDate.getTimeInMillis());
			if( nowDate.getTimeInMillis() > newEndDate.getTimeInMillis() ) 
				blChk = false;	
		}
		else{
			
			if( now.getTime() > endDate.getTime() )//若現在時間大於結束時間代表已過期需重新續約 
				blChk = false;				
		}
			
		return blChk;
	}
	
	
	//導忘記密碼頁面
	@RequestMapping(value = "/forgetpassword",  method = RequestMethod.GET)
	public String forgetPassword(Model model, HttpSession s,
		   HttpServletRequest req, HttpServletResponse res, Locale locale) throws Exception {
		
		return "forgetpasswd/forgetPasswd";
	}
	
	
	//發送忘記密碼
	@RequestMapping(value = "/sendforgetpw",  method = RequestMethod.POST)
	public String sendForgetPasswd(Model model, @RequestParam String account, 
		   @RequestParam String choice, HttpSession s,
		   HttpServletRequest req, HttpServletResponse res, Locale locale) throws Exception {
		
		   String resultFlag = "";
		   String rtnValue = "";
		   String passwd = "";
		   TimeMachine id = new TimeMachine();
		   String serverName = req.getServerName();
		   ShopMemData shopmemdata = loginService.queryForgetPasswdByShopId(account);
		   if(shopmemdata != null){
			
		   try {
			 passwd = sert.decrypt(shopmemdata.getUserPw());
		   } catch (Exception e) {
				e.printStackTrace();
				resultFlag = lng.getMessage("label.forgetPw.pwError", null, locale);
		   }
		   
		   if(choice.equals("E")){
			   
			    String email = shopmemdata.getEmail();
				if(email != null && !email.equals("")){
					
					//發送Email
					Email mailVO = new Email();
					mailVO = this.forgetPassWdMailData(shopmemdata, serverName, passwd);
					rtnValue = mailUtil.sendHTMLMail(mailVO);
					
					if(rtnValue.equals("Y")) {//發送MAIL成功
						resultFlag = lng.getMessage("label.forgetPw.emailSuccess", null, locale);
					}else{
						resultFlag = lng.getMessage("label.forgetPw.emailError", null, locale);
					}
					
				}else{
					resultFlag = lng.getMessage("label.forgetPw.email", null, locale);
				}
			}else if(choice.equals("S")){
				
				String phone = shopmemdata.getMobilePhone();
				if(phone != null && !phone.equals("")){
					
					//發送簡訊
					SmsUtil sms = new SmsUtil();		
					SMSRtnVO smsRtnVo = new SMSRtnVO();    //簡訊廠商回傳值放置的VO
					SmsPOVO smspoVo = new SmsPOVO();
						
					SMSVO smsvo = new SMSVO();     //傳送簡訊必要資料的VO
					smsvo = sms.getUrlPara(sysutil);      //抓取SMS主機參數資料
					
//					String coName = URLEncoder.encode(shopmemdata.getCoName(),"utf-8");
					smsvo.setDestNameValue(account);//收訊人名稱
					smsvo.setDestPhoneNumValue(phone); //收訊人手機號碼		
					smsvo.setSMBodyValue("大都會外送網 店家"+shopmemdata.getCoName()+"忘記的密碼為："+passwd); //簡訊內容		
							
					smsRtnVo = sms.sendSMS(smsvo);   //發送簡訊
																
					log.info("簡訊http傳送狀態:" + smsRtnVo.getHttpRtnCode());
					log.info("簡訊傳送狀態碼:" + smsRtnVo.getStatusCode());		
					log.info("簡訊傳送狀態說明:" + smsRtnVo.getStatusCodeDesc());
					
						if(smsRtnVo.getStatusCode().equals("0") || smsRtnVo.getStatusCode().equals("1") || 
						   smsRtnVo.getStatusCode().equals("2") || smsRtnVo.getStatusCode().equals("3")){//發送成功後, 將接收到的資料寫到DB中
							
							smspoVo.setORDER_ID(id.serial("smsAuthShop", 0));			
							smspoVo.setPHONE_NUM(phone);			
							smspoVo.setMsgId(smsRtnVo.getMsgId());
							smspoVo.setStatusCode(smsRtnVo.getStatusCode());
							smspoVo.setStatusCodeDesc(smsRtnVo.getStatusCode());
							rtnValue = verifyService.insertCustomPOSmsData(smspoVo);
							
						}else{
							rtnValue = "N";
						}
					
					if(rtnValue.equals("Y")) {//發送簡訊成功
						
						resultFlag = lng.getMessage("label.sms.success", null, locale);
					}else{
						
						resultFlag = lng.getMessage("label.sms.error", null, locale);
					}
					
				}else{
					
					resultFlag = lng.getMessage("label.forgetPw.phone", null, locale);
				}
			}	
			
		}else{
			resultFlag = lng.getMessage("label.accountError", null, locale);
		}
		
		model.addAttribute("msgAlert", resultFlag);
		
		return "forgetpasswd/forgetPasswd";
	}
	
	
	//Send Mail 忘記密碼Email重新發送密碼
	@SuppressWarnings({ "finally"})
	private Email forgetPassWdMailData(ShopMemData shopBase, String serverName, String passwd)
	{
		Email email = new Email();
		String templatePath = "";
		File file; 
		StringBuffer tempContent = new StringBuffer();
		String strTmp;
		String content;
		
		try
		{
			email.setMailServer(sysutil.getSysPrmValue("deliver", "MailServer"));  //抓取SMTP Server DNS 名稱
			email.setMailFrom(sysutil.getSysPrmValue("deliver", "EmailFrom"));    //抓取寄件者 E-Mail
			if(serverName.equals("localhost")){
				 templatePath= "C://email_send/forgetPasswordMailPath.html";   
			}else{
				templatePath = sysutil.getSysPrmValue("deliver", "forgetPasswordEmailTemplatePath");//抓取EMail 內容的範本檔
			}
		    log.info("Mail 內容的範本路徑:"+templatePath);
		    email.setSubject("大都會外送網--店家會員忘記密碼信函");
		    email.setMailTo(shopBase.getEmail());
		    
		    file = new File(templatePath); 
		    if(file.exists()){
		       InputStreamReader isr = new InputStreamReader(new FileInputStream(templatePath), "UTF-8");  
		       BufferedReader FileRead = new BufferedReader(isr);  
//		       BufferedReader FileRead = new BufferedReader(new FileReader(templatePath));
		       		       
		       while((strTmp = FileRead.readLine())!=null)
		       {
		    	   strTmp = strTmp.replaceAll("<<001>>", shopBase.getShopId().substring(0,2) + "**** "+shopBase.getCoName()); //替換範本檔內的特定字串
		    	   strTmp = strTmp.replaceAll("<<002>>", passwd + "");
		    	   tempContent.append(strTmp);
		       } 
		       content = tempContent.toString();		       
   	           FileRead.close();
   	           email.setContent(content);
		    } else {
		      log.error(templatePath + " 不存在!!");	
		    }		    
		}
		catch (Exception ex)
		{
			log.error(ex.getMessage());
		} finally
		{
		  return email;
		}
	}
	
	
	
	//導回登入頁面
	@RequestMapping(value = "/sessiontimeout", method = RequestMethod.GET)
	public String sessiontimeout(Model model) throws HttpSessionRequiredException {

		return "loginadmin/loginShopAdmin";
	}
	
	
	//改變頁面傳的Date參數類型
	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	
	
	//回傳Json格式
	/*@ResponseBody
	@RequestMapping(value = "/json", method = RequestMethod.GET ) //,headers="Accept=application/json" or ,headers="Accept=application/xml"
	public Map<String, Object> json(Model model, HttpServletRequest req,  HttpServletResponse res,  HttpSession session ) throws Exception{
				
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("pathParameter", "22");
		jsonMap.put("q", "33");
		String json1 = jsonTool.toJSON(jsonMap);
        
		return jsonMap;
	}*/
	
	
	//跳轉頁
/*	public void result (HttpServletResponse res,String result) throws Exception{
	
		PrintWriter out = res.getWriter();	
		out.println("<script type=\"text/javascript\">");
		out.println("alert(\""+result+"\");");
		out.println("window.location.href=\"http://www.2626.com.tw\"");
		out.println("</script>");
		out.close();
		out=null;
	}*/
	
	
	

}