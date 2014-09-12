package com.order.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.order.mode.ShopMemData;
import com.order.mode.vo.LoginAdminVO;
import com.order.mode.vo.SMSRtnVO;
import com.order.mode.vo.ShopBaseManagerVO;
import com.order.mode.vo.SmsPOVO;
import com.order.service.LoginService;
import com.order.service.ShopBaseService;
import com.order.service.VerifyService;
import com.order.util.SMSVO;
import com.order.util.SecretUtil;
import com.order.util.SmsUtil;
import com.order.util.SysParameterUtil;
import com.order.util.TimeMachine;
import com.order.util.email.Email;
import com.order.util.email.MailUtil;



@Controller
@RequestMapping("/emailmobileverify")
public class EmailMobileVerifyController extends BaseController {
	
	@Autowired
	private ShopBaseService shopBaseService;
	
	@Autowired
	private VerifyService verifyService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MailUtil mailUtil;
	
	@Autowired
	private SysParameterUtil sysutil;
	
	@Autowired
	private MessageSource lng;
	
	@Autowired
	private SecretUtil sert;
	
	private static final int IntHours = 1;
	
	
	//會員驗證狀態頁面
	@RequestMapping(value = "/shop/verifypage", method = RequestMethod.GET)
	public String verifypage(Model model, HttpServletRequest req, HttpSession s)  throws Exception {
		
		
		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		
		
		String shopId = vo.getShopId();
		String userPw = sert.decrypt(vo.getUserPw());
		vo = loginService.queryFun(shopId, userPw); //查詢店家資料，為因應Email驗證完後，重新點擊驗證狀態，重新抓取新的資料
		
		s.setMaxInactiveInterval(60 * 60 * IntHours);
		s.setAttribute("loginVo", vo);//重新寫入session
		
		if(vo != null){
			model.addAttribute("emailAuth", vo.getEmailAuth());
			model.addAttribute("phoneAuth", vo.getPhoneAuth());
		}else{
			model.addAttribute("emailAuth", "");
			model.addAttribute("phoneAuth", "");
		}
		
		return "emailphonechk/emailMobilePhoneChk";
	}

//===========================================重新發送Email認證============Begin=========================================
	
	//重新發送店家會員Email認證信
	@ResponseBody
	@RequestMapping(value = "/shop/emailauth", method = RequestMethod.POST)
	public Map<String, Object> emailAuth(Model model, HttpSession s, HttpServletRequest req, Locale locale) throws Exception {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String rtnValue = "";
		String serverName = req.getServerName();
		ShopBaseManagerVO shopBaseVO = new ShopBaseManagerVO();
		
		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = StringUtils.trimToEmpty(vo.getShopId());
		String password = StringUtils.trimToEmpty(vo.getUserPw());
		
		shopBaseVO = shopBaseService.queryShopDataAllForVO(shopId, password);//查詢店家資料
		
		//Send Mail 會員認證信
		Email mailVO = new Email();
		mailVO = this.genMailData(shopBaseVO, serverName);
		rtnValue = mailUtil.sendHTMLMail(mailVO);
		if(rtnValue.equals("Y")) {//發送MAIL成功
			jsonMap.put("chkEmail", true);
			log.info("發送MAIL驗證成功:=>"+lng.getMessage("MSG0030", null, locale));
			jsonMap.put("msgAlert", lng.getMessage("MSG0030", null, locale));
		}else{
			log.info("發送MAIL驗證失敗:=>"+lng.getMessage("MSG0030", null, locale));
			jsonMap.put("chkEmail", false);
			jsonMap.put("msgAlert", lng.getMessage("MSG0031", null, locale));
		}
	    
		return jsonMap;
	}
	
	
	//Send Mail 發送店家會員註冊認證信函-內容資料
	@SuppressWarnings({ "finally" })
	private Email genMailData(ShopBaseManagerVO shopBaseVO, String serverName)
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
				templatePath= "C://email_send/shop-email-register.html";  
			}else{
				templatePath = sysutil.getSysPrmValue("deliver", "registerMailTemplatePath");//抓取EMail 內容的範本檔
			}
		    log.info("Mail 內容的範本路徑:"+templatePath);
		    email.setSubject("大都會外送網--店家會員EMAIL認證信函");
		    email.setMailTo(shopBaseVO.getEmail());
		    
		    file = new File(templatePath); 
		    if(file.exists()){
		       InputStreamReader isr = new InputStreamReader(new FileInputStream(templatePath), "UTF-8");  
			   BufferedReader FileRead = new BufferedReader(isr);  
//			       BufferedReader FileRead = new BufferedReader(new FileReader(templatePath));
		       		       
		       while((strTmp = FileRead.readLine())!=null)
		       {
		    	   strTmp = strTmp.replaceAll("<<001>>", shopBaseVO.getCoName() + ""); //替換範本檔內的特定字串
		    	   strTmp = strTmp.replaceAll("<<002>>", shopBaseVO.getRegisterAuthen(serverName) + "");
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
	
	
	//確認Eamil註冊信修改DB Auth變為Y
	/**
	 * @param model
	 * @param s
	 * @param account
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registerAuthen", method = RequestMethod.GET)
	public String registerAuthen(Model model, HttpSession s, @RequestParam("account") String account, 
			@RequestParam("parameter") String parameter , HttpServletRequest req, Locale locale) throws Exception {
		log.info("會員確認Auth  Method：registerAuthen");
		
		String rtnValue = "";
		String retFlag = lng.getMessage("MSG0034", null, locale);
		String serverName = req.getServerName();
		Email email = new Email();
		String shopParameter = "";
		String emailAuth = "";
		boolean resFlag = false;
		ShopBaseManagerVO shopBaseVO = verifyService.queryParameterAuthByShopId(account);//查詢店家認證資料
		if(shopBaseVO != null){
			shopParameter = StringUtils.trimToEmpty(shopBaseVO.getShopParameter());
			emailAuth = StringUtils.trimToEmpty(shopBaseVO.getEmailAuth());
		}
            
        if(parameter.equals(shopParameter) && emailAuth.equals("N")){
        	//修改會員已認證通過Auth為Y
        	ShopMemData shopMemData = new ShopMemData();
        	shopMemData.setShopId(account);
        	shopMemData.setEmailAuth("Y");
        	resFlag = verifyService.updateShopBaseAuth(shopMemData);
				if(resFlag){
					//會員認證通過，發送通過信件
					email = this.registerSuccessMailData(shopBaseVO, serverName);
					rtnValue = mailUtil.sendHTMLMail(email);
					if(rtnValue.equals("Y")) {//發送MAIL成功
						retFlag = lng.getMessage("MSG0032", null, locale);
					}else{
						retFlag = lng.getMessage("MSG0033", null, locale);
					}
					
					
				}else{
					retFlag = lng.getMessage("MSG0034", null, locale);
				}
        }else if(parameter.equals(shopParameter) && emailAuth.equals("Y")){ 
        	retFlag = lng.getMessage("MSG0035", null, locale);
        }else{ 
        	retFlag = lng.getMessage("MSG0034", null, locale);
        }
        
        if(rtnValue.equals("Y")) {//MAIL成功
        	model.addAttribute("msgAlert", retFlag);
		}else{
			model.addAttribute("msgAlert", retFlag);
		}
        
		//帳號啟動成功-導回登入頁面
		return "loginadmin/loginShopAdmin";
	}
	
	
	
	
	//Send Mail 店家Email認證成功通知成功-內容資料
	@SuppressWarnings({ "finally" })
	private Email registerSuccessMailData(ShopBaseManagerVO shopBaseVO, String serverName)
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
				 templatePath= "C://email_send/registerSuccessMailPath.html";   
			}else{
				templatePath = sysutil.getSysPrmValue("deliver", "registerSuccessMailPath");//抓取EMail 內容的範本檔
			}
		    log.info("Mail 內容的範本路徑:"+templatePath);
		    email.setSubject("大都會外送網--店家會員EMAIL認證成功信函");
		    email.setMailTo(shopBaseVO.getEmail());
		    
		    file = new File(templatePath); 
		    if(file.exists()){
		       InputStreamReader isr = new InputStreamReader(new FileInputStream(templatePath), "UTF-8");  
		       BufferedReader FileRead = new BufferedReader(isr);  
//			       BufferedReader FileRead = new BufferedReader(new FileReader(templatePath));
		       		       
		       while((strTmp = FileRead.readLine())!=null)
		       {
		    	   strTmp = strTmp.replaceAll("<<001>>", shopBaseVO.getShopId() + ""); //替換範本檔內的特定字串
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
//===========================================重新發送Email認證============End=========================================	
	
//===========================================重新發送簡訊認證============Begin=========================================
	//重新發送簡訊認證碼
	@ResponseBody
	@RequestMapping(value = "/shop/smsauthen", method = RequestMethod.POST)
	public Map<String, Object> smsAuthen(Model model, HttpSession s , Locale locale) throws Exception {
		
		TimeMachine id = new TimeMachine();
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String rtnValue = "";
		
		ShopBaseManagerVO shopBaseVO = new ShopBaseManagerVO();
		
		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = StringUtils.trimToEmpty(vo.getShopId());
		String password = StringUtils.trimToEmpty(vo.getUserPw());
		shopBaseVO = shopBaseService.queryShopDataAllForVO(shopId, password);//查詢店家資料
		String coName = StringUtils.trimToEmpty(shopBaseVO.getCoName());//店家名稱
		String mobilePhone = StringUtils.trimToEmpty(shopBaseVO.getMobilePhone());//手機號碼
		
		//發送簡訊認證碼
		SmsUtil sms = new SmsUtil();		
		SMSRtnVO smsRtnVo = new SMSRtnVO();    //簡訊廠商回傳值放置的VO
		SmsPOVO smspoVo = new SmsPOVO();
			
		SMSVO smsvo = new SMSVO();     //傳送簡訊必要資料的VO
		smsvo = sms.getUrlPara(sysutil);      //抓取SMS主機參數資料
		
		coName = URLEncoder.encode(coName,"utf-8");
		smsvo.setDestNameValue(coName);//收訊人名稱
		smsvo.setDestPhoneNumValue(mobilePhone); //收訊人手機號碼		
		smsvo.setSMBodyValue("大都會外送網 店家會員簡訊認證碼為："+shopBaseVO.getMobileParameter()); //簡訊內容		
				
		smsRtnVo = sms.sendSMS(smsvo);   //發送簡訊
													
		log.info("簡訊http傳送狀態:" + smsRtnVo.getHttpRtnCode());
		log.info("簡訊傳送狀態碼:" + smsRtnVo.getStatusCode());		
		log.info("簡訊傳送狀態說明:" + smsRtnVo.getStatusCodeDesc());
		
			if(smsRtnVo.getStatusCode().equals("0") || smsRtnVo.getStatusCode().equals("1") || 
			   smsRtnVo.getStatusCode().equals("2") || smsRtnVo.getStatusCode().equals("3")){//發送成功後, 將接收到的資料寫到DB中
				
				smspoVo.setORDER_ID(id.serial("smsAuthShop", 0));			
				smspoVo.setPHONE_NUM(mobilePhone);			
				smspoVo.setMsgId(smsRtnVo.getMsgId());
				smspoVo.setStatusCode(smsRtnVo.getStatusCode());
				smspoVo.setStatusCodeDesc(smsRtnVo.getStatusCode());
				rtnValue = verifyService.insertCustomPOSmsData(smspoVo);
				
			}else{
				rtnValue = "N";
			}
		
		if(rtnValue.equals("Y")) {//發送簡訊成功
			jsonMap.put("chkSms", rtnValue);
			jsonMap.put("msgAlert", lng.getMessage("MSG0037", null, locale));
		}else{
			jsonMap.put("chkSms", rtnValue);
			jsonMap.put("msgAlert", lng.getMessage("MSG0038", null, locale));
		}
			
		return jsonMap;
	}
	
	
	//重新發送簡訊認證碼後 =>進行驗證 比對簡訊驗證碼
	@ResponseBody
	@RequestMapping(value = "/shop/registersmsauthen", method = RequestMethod.POST)
	public Map<String, Object> registerSmsAuthen(Model model, HttpSession s, HttpServletResponse res, @RequestParam("smsAuth") String smsAuth 
			, Locale locale) throws Exception {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		boolean rtnValue = false;
		String msgAlert = "";
		String mobileParameter = "";
		String phoneAuth ="";
		LoginAdminVO vo = (LoginAdminVO)s.getAttribute("loginVo");
		String shopId = StringUtils.trimToEmpty(vo.getShopId());
		ShopBaseManagerVO shopBaseVO = verifyService.queryParameterAuthByShopId(shopId);//查詢店家認證資料
		if(shopBaseVO != null){
			mobileParameter = StringUtils.trimToEmpty(shopBaseVO.getMobileParameter());
			phoneAuth = StringUtils.trimToEmpty(shopBaseVO.getPhoneAuth());
		}
            
        if(smsAuth.equals(mobileParameter) && phoneAuth.equals("N")){
        	
        	//修改會員已認證通過Auth為Y
        	ShopMemData shopMemData = new ShopMemData();
        	shopMemData.setShopId(shopId);
        	shopMemData.setPhoneAuth("Y");
        	rtnValue = verifyService.updateShopBasePhoneAuth(shopMemData);
        	
			if(rtnValue){
				//更新session phoneAuth
				vo.setPhoneAuth("Y");
				s.setMaxInactiveInterval(60 * 60 * IntHours);
				s.setAttribute("loginVo", vo);
				msgAlert = lng.getMessage("MSG0039", null, locale);
			}else{
				msgAlert = lng.getMessage("MSG0040", null, locale);
			}
			
        }else if(smsAuth.equals(mobileParameter) && phoneAuth.equals("Y")){
        	msgAlert = lng.getMessage("MSG0041", null, locale);
        }else{ 
        	msgAlert = lng.getMessage("MSG0042", null, locale);
        }
        
        if(rtnValue) {//認證成功
			jsonMap.put("chkSms", rtnValue);
			jsonMap.put("msgAlert", msgAlert);
		}else{
			jsonMap.put("chkSms", rtnValue);
			jsonMap.put("msgAlert", msgAlert);
		}
			
		return jsonMap;
	}
	
//===========================================重新發送簡訊認證============End=========================================
	
	/**  接收三竹簡訊回傳資料(使用Servlet 方式接收資料)	  	
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	public String smsDataReceive(String msgId, String phoneNum, String updateDate, String satausFlag) throws Exception {
		
		SmsPOVO smspoVo = new SmsPOVO();
		String queryRtnValue[];
		String rtnValue = "N";
					
		smspoVo.setPHONE_NUM(phoneNum);			
		smspoVo.setMsgId(msgId);
		smspoVo.setUPDATE_DATE(updateDate);
		smspoVo.setStatusCode(satausFlag);
		smspoVo.setStatusCodeDesc(satausFlag);
			
		queryRtnValue = verifyService.querySmsByMsgIdPhoneNum(msgId, phoneNum); //先查詢該簡訊是否存在DB中
		
		if(queryRtnValue[0].equalsIgnoreCase("Y")){ //簡訊資料有存在DB中
			rtnValue = verifyService.updateSmsData(smspoVo);  //Update 簡訊最新狀態資料
			if(!queryRtnValue[1].equals("")){ //有訂單編號資料,需要再更新訂單主檔	
				verifyService.updateCustomPOMastMsgInfoTime(queryRtnValue[1], updateDate);
			}
		} else {
			log.error("簡訊資料不存在DB中, 無法更新!!");
			log.error("MSG ID:" + msgId + ", Phone Number:" + phoneNum);
		}
			
		smspoVo = null;
		return rtnValue;
			
	}
	
	

}