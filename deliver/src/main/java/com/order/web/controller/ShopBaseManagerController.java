package com.order.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.order.mode.MyShopTypeId;
import com.order.mode.ServiceScopeCircle;
import com.order.mode.ShopMemData;
import com.order.mode.ShopType;
import com.order.mode.vo.LoginAdminVO;
import com.order.mode.vo.MyShopTypeIdVO;
import com.order.mode.vo.ShopBaseManagerVO;
import com.order.service.ShopBaseService;
import com.order.util.SecretUtil;
import com.order.util.SysParameterUtil;
import com.order.util.TimeMachine;
import com.order.util.Tools;
import com.order.util.email.Email;
import com.order.util.email.MailUtil;



@Controller
@RequestMapping("/shopbasemanager")
public class ShopBaseManagerController extends BaseController {

	@Autowired
	private ShopBaseService shopBaseService;
	
	@Autowired
	private Tools tls;
	
	@Autowired
	private MessageSource lng;
	
	@Autowired
	private SecretUtil sert;
	
	private static final int IntHours = 1;
	
	@Autowired
	private SysParameterUtil sysutil;
	
	@Autowired
	private MailUtil mailUtil;

	@RequestMapping(value = "/shop/manager", method = RequestMethod.GET)
	public String managerPage(Model model, HttpServletRequest req, HttpSession session, Locale locale)  throws HttpSessionRequiredException ,Exception {
		
		//查詢店家資料
		String strMsg = "";
		ShopMemData shopModel = null;
		LoginAdminVO lgAdminVO = (LoginAdminVO)session.getAttribute("loginVo");
		String strShopId = lgAdminVO.getShopId();
		String strUserPw= lgAdminVO.getUserPw();
			
		shopModel = shopBaseService.queryShopDataAll(strShopId, strUserPw);
				
				if( shopModel == null ){//密碼錯誤
					strMsg = lng.getMessage("MSG0049", null, locale);//密碼錯誤
					model.addAttribute("msg", strMsg);
				}	
				else{
					StringBuffer strBufShopType = null;
					
					strBufShopType = getShopType(req);
					model.addAttribute("strShopType", strBufShopType);
					model.addAttribute("shopBaseData", shopModel);
				}	
				
		shopModel = null;

		return "shopbasemanager/shopBaseManager";
	}
	
	
	/** 查詢店家類別*/
	public StringBuffer getShopType(HttpServletRequest req)throws Exception
	{
		List<ShopType> shopType = new ArrayList<ShopType>();//所有的店家類別 
		List<MyShopTypeId> myShopTypeId = new ArrayList<MyShopTypeId>();//該店家所擁有的店家類別
        StringBuffer strBufTemp = new StringBuffer();	
        ShopType shopTypeVO = null;
        MyShopTypeId myTypeVo = null;
		HttpSession session = req.getSession(false); 
		LoginAdminVO lgAdminVO = (LoginAdminVO)session.getAttribute("loginVo");
		String strShopId = lgAdminVO.getShopId();        

        int intBreak = 3;		
        String checked = "";
			
        //先至資料表取得所有店家類別
        shopType = shopBaseService.queryShopTypeAll();
		//再至資料表取得該店家所擁有的店家類別
        myShopTypeId = shopBaseService.queryShopType(strShopId);
        
        if( shopType.size() > 0 )
        {
        	strBufTemp.append("<table><tr>");
            for(int i = 0; i < shopType.size(); i++)
            {
                shopTypeVO = (ShopType)shopType.get(i);
                
                if( myShopTypeId.size() > 0 )
                {
                	for(int j = 0 ;j < myShopTypeId.size();j++)
                	{
                		myTypeVo =  (MyShopTypeId)myShopTypeId.get(j);
                	
                        if( shopTypeVO.getShopTypeId().equals(myTypeVo.getShopTypeId()) )
                        {	
                        	checked = "checked='checked'";
                        	break;
                        }	
                        else
                        	checked = "";       
                        
                        myTypeVo = null;
                	}
                }

                strBufTemp.append("<td class=\"shopstylefood\">")
                .append("<input type='checkbox' id='myShopTypeId' title='" + shopTypeVO.getShopTypeName() + "' name='myShopTypeId' ")
                .append(checked)
                .append(" value='" + shopTypeVO.getShopTypeId() + "'" + " >")
                .append(shopTypeVO.getShopTypeName())
                .append("</td>");						
                
                if( i > 0 && ((i+1) % intBreak) == 0)
                    strBufTemp.append("</tr><tr>");
                shopTypeVO = null;
                
            }
        
            strBufTemp.append("</tr></table>");	

        }			
		
		return strBufTemp;
	}
	
	
	/** 修改店家資料 */
	@RequestMapping(value = "/shop/updateShopData", method = RequestMethod.POST)
	public String updateShopData(Model model, HttpServletRequest req, HttpSession session, Locale locale)  throws Exception {
		
		//log.info("---checkShopData start---");	
//		if( !checkLogin(mapping,form,req,res) )
//			return mapping.findForward("loginShopAdmin");//loginShopAdmin			
		
		HashMap<String,Object> map = null;
		String strAryMyShopTypeId[] = null;
		StringBuilder strBldKeyword = new StringBuilder();//存放預設關鍵字，(店家名稱、店家類別)
		String strMsg = "";		
		boolean blnChkShopData = false;
		boolean blnUpdShopData = false;
		ArrayList<MyShopTypeIdVO> myShopTypeIdVoList = new ArrayList<MyShopTypeIdVO>();
		MyShopTypeIdVO myVO = null;
		ShopBaseManagerVO shopBaseManagerVO = null;
		String strPage = "loginadmin/index";
		//PrintWriter out = res.getWriter();
		LoginAdminVO lgAdminVO = (LoginAdminVO)session.getAttribute("loginVo");
		String strShopId = lgAdminVO.getShopId();	
		StringBuffer strBufShopType = null;
		ShopMemData modelVo = null;
			
		if( req.getMethod().equals("POST"))
		{
				map = chkShopValue(req, locale);

				strMsg = (String)map.get("msg");
				blnChkShopData = (Boolean)map.get("bl");
				
				//true代表資料檢查無誤
				if( blnChkShopData )
				{
					//log.info("lat: " + req.getParameter("addrCLat"));
					//log.info("lng: " + req.getParameter("addrCLng"));
					shopBaseManagerVO = new ShopBaseManagerVO();//	
					shopBaseManagerVO.setShopId( strShopId );			
//					shopBaseManagerVO.setUserPw(req.getParameter("userPw"));//
					shopBaseManagerVO.setCoName(req.getParameter("coName"));//
					shopBaseManagerVO.setCoNum(req.getParameter("coNum"));
					shopBaseManagerVO.setCoPeople(req.getParameter("coPeople"));
					shopBaseManagerVO.setAddrL(req.getParameter("cityAddrL"),req.getParameter("cantonAddrL"),req.getParameter("detailAddrL"));
//					shopBaseManagerVO.setAddrC(req.getParameter("cityAddrC"),req.getParameter("cantonAddrC"),req.getParameter("detailAddrC"));
					shopBaseManagerVO.setTel(req.getParameter("tel"));
					shopBaseManagerVO.setMobilePhone(req.getParameter("mobilePhone"));
					shopBaseManagerVO.setTelOrder(req.getParameter("telOrder"));
					shopBaseManagerVO.setEmail(req.getParameter("email"));
					shopBaseManagerVO.setAddrCLat(req.getParameter("addrCLat"));
					shopBaseManagerVO.setAddrCLng(req.getParameter("addrCLng"));
					strAryMyShopTypeId = req.getParameterValues("myShopTypeId");
					
					shopBaseManagerVO.setAddrLCity(req.getParameter("cityAddrL"));
					shopBaseManagerVO.setAddrLCanton( StringUtils.trimToEmpty(req.getParameter("cantonAddrL")) );
					shopBaseManagerVO.setAddrLDetail(req.getParameter("detailAddrL"));
//					shopBaseManagerVO.setAddrCCity(req.getParameter("cityAddrC"));
//					shopBaseManagerVO.setAddrCCanton( StringUtils.trimToEmpty(req.getParameter("cantonAddrC")) );
//					shopBaseManagerVO.setAddrCDetail(req.getParameter("detailAddrC"));	
//					shopBaseManagerVO.setInvoiceType(req.getParameter("invoiceType"));	
//					shopBaseManagerVO.setIdentNum(StringUtils.trimToEmpty((String)req.getParameter("identNum")));	
					shopBaseManagerVO.setInvoiceName(StringUtils.trimToEmpty((String)req.getParameter("invoiceName")));					
					
					//前台使用者搜尋用的關鍵字 start
					strBldKeyword.append(req.getParameter("coName"))//存放店家名稱
						.append(";")
						.append(shopBaseManagerVO.getAddrL())
						.append(";")						
						.append(req.getParameter("strAllShopTypeName"));//存放有有勾選的店家類別名稱
					shopBaseManagerVO.setKeyword(strBldKeyword.toString());
					//前台使用者搜尋用的關鍵字 end					
					
					for(int i = 0; i < strAryMyShopTypeId.length; i++)
					{
						myVO = new MyShopTypeIdVO();
						myVO.setShopId(strShopId);
						myVO.setShopTypeId(strAryMyShopTypeId[i]);
						
						myShopTypeIdVoList.add(myVO);
						myVO = null;
					}	
		
					//shopBaseManagerVO.setMyShopTypeId(strAllMyShopTypeId);//加入店家類別
					//Modify data for shop
					//修改店家資料
					blnUpdShopData = shopBaseService.updateShopDataAll(shopBaseManagerVO, myShopTypeIdVoList);
					
					if(blnUpdShopData){				
						strMsg = lng.getMessage("MSG0004", null, locale);//Update success
									
						//檢查店家名稱密碼	
						modelVo = shopBaseService.chkShopLogin(lgAdminVO.getShopId(), sert.decrypt(lgAdminVO.getUserPw()));
						
						//更新login vo
						//loginAdminVO loginVo = (loginAdminVO)session.getAttribute("loginVo");
						
//						lgAdminVO.setUserPw(req.getParameter("userPw"));
						session.setMaxInactiveInterval(60 * 60 * IntHours);
						session.setAttribute("loginVo", lgAdminVO);
					
					}else{
						//更新資料錯誤，檢查店家名稱密碼
						modelVo = shopBaseService.chkShopLogin(lgAdminVO.getShopId(), sert.decrypt(lgAdminVO.getUserPw()));
						strMsg = lng.getMessage("MSG0005", null, locale);//Update failed
					}	
					
				}else{
					//資料檢查錯誤，檢查店家名稱密碼
					modelVo = shopBaseService.chkShopLogin(lgAdminVO.getShopId(),sert.decrypt(lgAdminVO.getUserPw()));										
				}
				strBufShopType = getShopType(req);
				req.setAttribute("strShopType", strBufShopType.toString());
				req.setAttribute("shopBaseData", modelVo);
				req.setAttribute("msg",strMsg);//錯誤訊息				
				
				strPage = "shopbasemanager/shopBaseManager";
		}

		strBldKeyword = null;
		myVO = null;
		shopBaseManagerVO = null;
		modelVo = null;
		strBufShopType = null;
		
		return strPage;
	}
	
	
	public HashMap<String,Object> chkShopValue(HttpServletRequest req, Locale locale)throws Exception
	{
		String strMsg = "";			
		boolean bl = false;
		HashMap<String,Object> map = new HashMap<String,Object>();
		String strCoNum = "";//統一編號
//		String strInvoiceName = "";//買受人姓名
//		String strIdentNum = "";//買受人所要求的統一編號	
//		HttpSession session = req.getSession(false);
//		LoginAdminVO lgAdminVO = (LoginAdminVO)session.getAttribute("loginVo");
				
//		if( !tls.chkReqValue(req,"userPw",4,100) )
//			strMsg = lng.getStr("MSG0020");//密碼長度不正確		    
//		else if( !tls.chkEngOrNum( req.getParameter("userPw") ) )
//			strMsg = lng.getStr("MSG0053");//密碼僅限英文及數字			
//		else if( !req.getParameter("userPw").equals(req.getParameter("cnfUserPw")) )
//			strMsg = lng.getStr("MSG0016");//兩次輸入密碼未一致	
//		else if( req.getParameter("userPw").equalsIgnoreCase(lgAdminVO.getShopId()) )
//			strMsg = lng.getStr("MSG0062");//帳號與密碼不可相同		
//		else 
		if( !tls.chkReqValue(req,"coName",1,30) )
			strMsg = lng.getMessage("MSG0023", null, locale);//店名	
		else if( !tls.chkReqValue(req,"coPeople",1,30) )
			strMsg = lng.getMessage("MSG0024", null, locale);//聯絡人	
		else if( !tls.chkReqValue(req,"cityAddrL",3,100) || !tls.chkReqValue(req,"detailAddrL",3,100) )
			strMsg = lng.getMessage("MSG0025", null, locale);//店家地址
//		else if( !tls.chkReqValue(req,"cityAddrC",3,100) || !tls.chkReqValue(req,"detailAddrC",3,100) )
//			strMsg = lng.getMessage("MSG0031", null, locale);.getStr("MSG0026");//營業地址
		else if( !tls.chkReqValue(req,"tel",3,13) )
			strMsg = lng.getMessage("MSG0027", null, locale);//電話
		else if( !tls.chkReqValue(req,"mobilePhone",10,13) )
			strMsg = lng.getMessage("MSG0028", null, locale);//手機號碼
		else if( !tls.chkReqValue(req,"telOrder",3,13) )
			strMsg = lng.getMessage("MSG0029", null, locale);//訂餐專線		
		else if( !tls.chkReqValue(req,"myShopTypeId",1,200) )
			strMsg = lng.getMessage("MSG0010", null, locale);//店家類別	
		else if( !tls.chkKeyword(req,"strAllShopTypeName") )//搜尋關鍵字用
			strMsg = lng.getMessage("MSG0010", null, locale);//店家類別
		else//檢查買受人姓名及其統編
		{
			/*if( req.getParameter("invoiceType").equals("1") ){//三聯發票
				strInvoiceName = StringUtils.trimToEmpty((String)req.getParameter("invoiceName"));
				strIdentNum = StringUtils.trimToEmpty((String)req.getParameter("identNum"));
				
				if( strInvoiceName.length() == 0 )//買受人名字不得為空
					strMsg = lng.getStr("MSG0057");//請輸入買受人名稱
				else if( strIdentNum.length() == 0 || !tls.chkIdentNum(strIdentNum) )//檢查統一編號
					strMsg = lng.getStr("MSG0022");//統一編號有誤，煩請重新確認						
				else
					bl = true;
			}else if( req.getParameter("invoiceType").equals("0") )
				bl = true;*/
			
			bl = true;
			if( bl )
			{
				bl = false;
				
				strCoNum = StringUtils.trimToEmpty((String)req.getParameter("coNum"));//檢查該公司統編(非開給買受人的統編)
				/*if( strCoNum.length() == 0 || tls.chkIdentNum(strCoNum) )
						 bl = true;					
				else
					strMsg = lng.getStr("MSG0022");//統一編號有誤，煩請重新確認	
				 */		
				//如果有輸入統一編號則檢查
				if(!strCoNum.equals("") && strCoNum.length() > 0){
					if(tls.chkIdentNum(strCoNum)){
						bl = true;
					}else{
						strMsg = lng.getMessage("MSG0022", null, locale);//統一編號有誤，煩請重新確認	
					}
				}else{
					bl = true;
				}
				
			}//if	

		}//else
			
		map.put("bl", bl);
		map.put("msg", strMsg);
		
		return map;
	}
	
	
	//帳號密碼修改頁面
	@RequestMapping(value = "/shop/account", method = RequestMethod.GET)
	public String accountPage(Model model, HttpServletRequest req, HttpSession session)  throws Exception {
		
		
		return "qryshopaccount/qryShopAccount";
	}
	
	
	//密碼資料確認和修改
	@RequestMapping(value = "/shop/updateAccountPwd", method = RequestMethod.POST)
	public String updateShopAccount(Model model, HttpServletRequest req, HttpSession session, Locale locale)  throws Exception {
		
//		String oldPassword1 = StringUtils.trimToEmpty((String)req.getParameter("oldPassword"));
//		String newPassword1 = StringUtils.trimToEmpty((String)req.getParameter("newPassword"));
//		String confirmPassword1 = StringUtils.trimToEmpty((String)req.getParameter("confirmPassword"));
		
		String strMsg = "";
		LoginAdminVO lgAdminVO = (LoginAdminVO)session.getAttribute("loginVo");
		String strShopId = lgAdminVO.getShopId();
		String strPage = "loginadmin/index";
		boolean checkPwd = false;
		boolean blnUpdShopPwd = false;
		ShopMemData modelVo = null;
		
		if( req.getMethod().equals("POST"))
		{		
			if( !tls.chkReqValue(req,"oldPassword",4,30) ){
				strMsg = lng.getMessage("MSG1020", null, locale);//密碼長度不正確
				model.addAttribute("msg", strMsg);
			}	
			else
			{	
				//查詢原先密碼是否正確
				String userPw = StringUtils.trimToEmpty((String)req.getParameter("oldPassword"));
				int count = shopBaseService.chkOldPassword(strShopId, userPw);
				if(count == 0 || count >1){//密碼錯誤
					strMsg = lng.getMessage("MSG0049", null, locale);//密碼錯誤
					model.addAttribute("msg", strMsg);
				}else{
					
					//檢核新密碼
					if( !tls.chkReqValue(req,"newPassword",4,100) ){
						strMsg = lng.getMessage("MSG0020", null, locale);//密碼長度不正確		    
					}else if( !tls.chkEngOrNum( req.getParameter("newPassword") ) ){
						strMsg = lng.getMessage("MSG0053", null, locale);//密碼僅限英文及數字			
					}else if( !req.getParameter("newPassword").equals(req.getParameter("confirmPassword")) ){
						strMsg = lng.getMessage("MSG0016", null, locale);//兩次輸入密碼未一致	
					}else if( req.getParameter("newPassword").equalsIgnoreCase(lgAdminVO.getShopId()) ){
						strMsg = lng.getMessage("MSG0062", null, locale);//帳號與密碼不可相同	
					}else{
						checkPwd = true;
					}
					
					//更新密碼
					if(checkPwd){
						String newPassword = StringUtils.trimToEmpty((String)req.getParameter("newPassword"));
						
						//更新密碼
						blnUpdShopPwd = shopBaseService.updatePassword(strShopId, userPw, newPassword);
						
						//以新密碼檢查店家名稱密碼	
						modelVo = shopBaseService.chkShopLogin(lgAdminVO.getShopId(), newPassword);
						
						if(blnUpdShopPwd && modelVo != null){
							//更新login vo
							lgAdminVO.setUserPw(sert.encrypt(newPassword));
							session.setMaxInactiveInterval(60 * 60 * IntHours);
							session.setAttribute("loginVo", lgAdminVO);
							strMsg = lng.getMessage("MSG0004", null, locale);//Update success
						}else{
							strMsg = lng.getMessage("MSG0005", null, locale);//密碼錯誤
						}
					}
				}	
			}
			
			model.addAttribute("msg", strMsg);//錯誤訊息	
			strPage = "qryshopaccount/qryShopAccount";
		}
		
		return strPage;
	}
	
	
	//店家註冊條款頁面
	@RequestMapping(value = "/serviceterms", method = RequestMethod.GET)
	public String serviceTerms(Model model, HttpServletRequest req, HttpSession session, Locale locale)  throws Exception {
		
		
		
		return "shopregister/serviceTerms";
	}
	
	
	//店家註冊頁面
	@RequestMapping(value = "/shopregister", method = RequestMethod.POST)
	public String shopRegister(Model model, @RequestParam String agreeChk,
		   HttpServletRequest req, HttpSession session, Locale locale)  throws Exception {
		
		String result = "shopregister/serviceTerms";
		
		if(agreeChk.equals("Y")){
			
			session.setAttribute("shopTerms", "shopTerms");
			
			List<ShopType> shopTypeList = new ArrayList<ShopType>();//所有的店家類別 
			ShopType shopTypeVO = null;
			StringBuilder strBud = new StringBuilder();//輸出字串用
			int intBreak = 3;//每三筆店家類別便斷行
			
			//先至資料表取得所有店家類別
			shopTypeList = shopBaseService.queryShopTypeAll();
			
			strBud.append("<table><tr>");//Output all type of shop
			
			for(int i = 0; i < shopTypeList.size();i++)
			{
				shopTypeVO = (ShopType)shopTypeList.get(i);
	
				strBud.append("<td class=\"shopstylefood\">");
				strBud.append("<input type='checkbox' id='myShopTypeId' title='" + shopTypeVO.getShopTypeName() + "' name='myShopTypeId'");
				strBud.append(" value='" + shopTypeVO.getShopTypeId() + "'" + " />");
				strBud.append( shopTypeVO.getShopTypeName());
				strBud.append("</td>");						
				
				if( i > 0 && ( ( i + 1 ) % intBreak == 0) )
					strBud.append("</tr><tr>");
				shopTypeVO = null;
				
			}
		
			strBud.append("</tr></table>");		
			
			model.addAttribute("strListShopType", strBud.toString());
			
			result = "shopregister/shopRegister";
		}
		
		return result;
	}
	
	
	//檢查註冊id
	@ResponseBody
	@RequestMapping(value = "/chkshopid", method = RequestMethod.POST)
	public Map<String, Object> chkShopId(Model model, @RequestParam(required=false) String shopId,
		   HttpServletRequest req, HttpSession session, Locale locale)  throws Exception {
		   
		Map<String, Object> map = new HashMap<String, Object>();
		String strMsg = "";
		boolean blnCorrect = false;		

		blnCorrect = shopBaseService.checkShopId(shopId.toLowerCase());
		
		if(blnCorrect){
			strMsg = lng.getMessage("label.existing", null, locale);//此帳號已存在
		}
		
		map.put("success", blnCorrect);
		map.put("msg", strMsg);
		
		return map;
	}
	
	//店家註冊
	@RequestMapping(value = "/shopregistercreate", method = RequestMethod.POST)
	public String shopRegisterCreate(Model model, 
		   HttpServletRequest req, HttpSession session, Locale locale)  throws Exception {
		
		if(session.getAttribute("shopTerms") == null ){
		
			//反回店家註冊條款頁面
			model.addAttribute("msg", lng.getMessage("MSG0093", null, locale));
			return "shopregister/serviceTerms";		
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		String strAryMyShopTypeId[] = null;
		StringBuilder strBldKeyword = new StringBuilder();//存放預設關鍵字，(店家名稱、店家類別)
		ArrayList<MyShopTypeIdVO> myShopTypeIdVoList = new ArrayList<MyShopTypeIdVO>();
		MyShopTypeIdVO myVO = null;
		ShopBaseManagerVO shopBaseManagerVO = null;
		ServiceScopeCircle scopeCircle = null;
		String strMsg = "";		
		String resultMsg = "";
		String resultFlag = "";
		boolean blnChkShopData = false;
		boolean insertShopData = false;
		TimeMachine id = null;
		String serverName = req.getServerName();
		if(serverName.equals("localhost")){
			serverName = "localhost/deliver";
		}
			
		if( req.getMethod().equals("POST"))
		{
				map = chkRgsValue(req, locale);//檢核
				
				strMsg = (String)map.get("msg");
				blnChkShopData = (Boolean)map.get("bl");
				
				//true代表資料檢查無誤
				if( blnChkShopData )
				{
					shopBaseManagerVO = new ShopBaseManagerVO();
					shopBaseManagerVO.setShopId( req.getParameter("shopId").toLowerCase());			
					shopBaseManagerVO.setUserPw(sert.encrypt(req.getParameter("userPw")));			
					shopBaseManagerVO.setCoName(req.getParameter("coName"));
					shopBaseManagerVO.setCoNum(req.getParameter("coNum"));
					shopBaseManagerVO.setCoPeople(req.getParameter("coPeople"));
					shopBaseManagerVO.setMobilePhone(req.getParameter("mobilePhone"));
					shopBaseManagerVO.setTel(req.getParameter("tel"));
					shopBaseManagerVO.setAddrL(req.getParameter("cityAddrL"),req.getParameter("cantonAddrL"),req.getParameter("detailAddrL"));
//					shopBaseManagerVO.setAddrC(req.getParameter("cityAddrC"),req.getParameter("cantonAddrC"),req.getParameter("detailAddrC"));
					shopBaseManagerVO.setTelOrder(req.getParameter("telOrder"));
					shopBaseManagerVO.setEmail(req.getParameter("email"));	
					shopBaseManagerVO.setAddrCLat(req.getParameter("addrLat"));
					shopBaseManagerVO.setAddrCLng(req.getParameter("addrLng"));
					shopBaseManagerVO.setSource("Internet");
					shopBaseManagerVO.setAddrLCity(req.getParameter("cityAddrL"));
					shopBaseManagerVO.setAddrLCanton( StringUtils.trimToEmpty(req.getParameter("cantonAddrL")) );
					shopBaseManagerVO.setAddrLDetail(req.getParameter("detailAddrL"));
//					shopBaseManagerVO.setAddrCCity(req.getParameter("cityAddrC"));
//					shopBaseManagerVO.setAddrCCanton( StringUtils.trimToEmpty(req.getParameter("cantonAddrC")) );
//					shopBaseManagerVO.setAddrCDetail(req.getParameter("detailAddrC"));
					shopBaseManagerVO.setInvoiceType(req.getParameter("invoiceType"));	
//					shopBaseManagerVO.setIdentNum(StringUtils.trimToEmpty((String)req.getParameter("identNum")));	
					shopBaseManagerVO.setInvoiceName(StringUtils.trimToEmpty((String)req.getParameter("invoiceName")));	
					shopBaseManagerVO.setServiceScopeType("Circle");
					
					//前台使用者搜尋用的關鍵字 start
					strBldKeyword.append(req.getParameter("coName"))//存放店家名稱
						.append(";")
						.append(shopBaseManagerVO.getAddrL())
						.append(";")
						.append(req.getParameter("strAllShopTypeName"));//存放有有勾選的店家類別名稱
					shopBaseManagerVO.setKeyword(strBldKeyword.toString());
					//前台使用者搜尋用的關鍵字 end
					
					//Email認證碼
					UUID uuid = UUID.randomUUID(); 
					shopBaseManagerVO.setShopParameter(uuid.toString());
					//簡訊認證碼
					String authCode = shopBaseManagerVO.getRegSN();
					shopBaseManagerVO.setMobileParameter(authCode);
					
					//店家類別
					strAryMyShopTypeId = req.getParameterValues("myShopTypeId");
					for(int i = 0; i < strAryMyShopTypeId.length; i++)
					{
						myVO = new MyShopTypeIdVO();
						myVO.setShopId( req.getParameter("shopId").toLowerCase() );
						myVO.setShopTypeId( strAryMyShopTypeId[i] );
						myShopTypeIdVoList.add(myVO);
					}	
					
					//搜尋範-Map經緯度資料-外送服務區域
					id = new TimeMachine();
					scopeCircle = new ServiceScopeCircle();
					scopeCircle.setCircleId(id.serial("circle", 0));
					scopeCircle.setShopId(req.getParameter("shopId").toLowerCase());
					scopeCircle.setCircleCenter(req.getParameter("circleCenter"));
					scopeCircle.setCircleTan(req.getParameter("circleTan"));
					scopeCircle.setDistance(Double.parseDouble(req.getParameter("distance")));
					scopeCircle.setMaxLng(Double.parseDouble(req.getParameter("maxLng")));
					scopeCircle.setMaxLat(Double.parseDouble(req.getParameter("maxLat")));
					scopeCircle.setMinLng(Double.parseDouble(req.getParameter("minLng")));
					scopeCircle.setMinLat(Double.parseDouble(req.getParameter("minLat")));
					
					//新增店家資料
					insertShopData = shopBaseService.createShopDataAll(shopBaseManagerVO, myShopTypeIdVoList, scopeCircle);
					if(insertShopData){
						//註冊成功
						//店家會員註冊完成,發送Mail通知相關人員
						Email mailVO = new Email();
						mailVO = this.shopRegisterSuccessSendMailToManager(shopBaseManagerVO, locale);
						String rtnValue = mailUtil.sendHTMLMail(mailVO);
						
//						if(rtnValue.equals("Y")) {//發送MAIL成功
//							resultFlag = lng.getMessage("label.forgetPw.emailSuccess", null, locale);
//						}else{
//							resultFlag = lng.getMessage("label.forgetPw.emailError", null, locale);
//						}
						
						 log.info("店家會員註冊完成,發送Mail通知相關人員 Send Mail Result:" + rtnValue); 
						
						resultMsg = lng.getMessage("MSG0091", null, locale);//註冊成功
						resultFlag = "shopregister/shopRegisterFinish";//完成頁面
					}else{
						//註冊失敗
						resultMsg = lng.getMessage("MSG0092", null, locale);
						resultFlag = "shopregister/serviceTerms";
					}
					
				}else{
					//資料檢查錯誤
					resultMsg = strMsg;
					resultFlag = "shopregister/serviceTerms";
				}
		}else{
			//不是以POST進入，反回
			resultMsg = lng.getMessage("MSG0094", null, locale);
			resultFlag = "shopregister/serviceTerms";
		}
		
		model.addAttribute("serverName", serverName);
		model.addAttribute("msg", resultMsg);
		
		return resultFlag;
	}
	
	
	public Map<String, Object> chkRgsValue(HttpServletRequest req, Locale locale)throws Exception
	{
		String strMsg = "";		
		String strUserPw = "";//密碼
		String strCnfUserPw = "";//確認密碼	
		String strCoNum = "";//統一編號
		String strInvoiceName = "";//買受人姓名
		boolean bl = false;
		Map<String, Object> map = new HashMap<String, Object>();
		Tools tls = new Tools();
		String shopId = StringUtils.trimToEmpty(req.getParameter("shopId"));
			
		if( !tls.chkReqValue(req,"shopId",4,30) )
			strMsg = lng.getMessage("MSG0019", null, locale);//帳號長度有問題
		else if( !tls.chkEngOrNum( req.getParameter("shopId") ) )
			strMsg = lng.getMessage("MSG0077", null, locale);//帳號僅限英文及數字
		else if( !tls.chkReqValue(req,"userPw",4,30) )
			strMsg = lng.getMessage("MSG0020", null, locale);//密碼長度不正確
		else if( !tls.chkEngOrNum( req.getParameter("userPw") ) )
			strMsg = lng.getMessage("MSG0078", null, locale);//密碼僅限英文及數字		
		else if( !tls.chkReqValue(req,"coName",1,30) )
			strMsg = lng.getMessage("MSG0023", null, locale);//店名	
		else if( !tls.chkReqValue(req,"coPeople",1,30) )
			strMsg = lng.getMessage("MSG0024", null, locale);//聯絡人	
		else if( !tls.chkReqValue(req,"cityAddrL",3,100) || !tls.chkReqValue(req,"detailAddrL",3,100) )
			strMsg = lng.getMessage("MSG0025", null, locale);//聯絡地址
		//else if( !tls.chkReqValue(req,"cityAddrC",3,100) || !tls.chkReqValue(req,"detailAddrC",3,100) )
			//strMsg = lng.getMessage("MSG0026", null, locale);//營業地址
		else if( !tls.chkReqValue(req,"tel",8,10) )
			strMsg = lng.getMessage("MSG0027", null, locale);//電話
		else if( !tls.chkNumber(req.getParameter("tel")) )
			strMsg = lng.getMessage("MSG0079", null, locale);//電話僅限數字
		else if( !tls.chkReqValue(req,"mobilePhone",10,10) )
			strMsg = lng.getMessage("MSG0028", null, locale);//手機號碼
		else if( !tls.chkNumber(req.getParameter("mobilePhone")) )
			strMsg = lng.getMessage("MSG0081", null, locale);//手機號碼僅限數字		
		else if( !tls.chkReqValue(req,"telOrder",8,10) )
			strMsg = lng.getMessage("MSG0029", null, locale);//訂餐專線	
		else if( !tls.chkNumber(req.getParameter("telOrder")) )
			strMsg = lng.getMessage("MSG0080", null, locale);//訂餐專線僅限數字	
		else if( req.getParameterValues("myShopTypeId").length == 0 )
			strMsg = lng.getMessage("MSG0010", null, locale);//店家類別
		else if( !tls.chkKeyword(req,"strAllShopTypeName") )//搜尋關鍵字用
			strMsg = lng.getMessage("MSG0010", null, locale);//店家類別
		else//檢查買受人姓名及其統編
		{
			if( req.getParameter("invoiceType").equals("1") ){//三聯發票
				strInvoiceName = StringUtils.trimToEmpty((String)req.getParameter("invoiceName"));
//				strIdentNum = StringUtils.trimToEmpty((String)req.getParameter("identNum"));
				
				if( strInvoiceName.length() == 0 )//買受人名字不得為空
					strMsg = lng.getMessage("MSG0087", null, locale);//請輸入買受人名稱
//				else if( strIdentNum.length() == 0 || !tls.chkIdentNum(strIdentNum) )//檢查統一編號
//					strMsg = lng.getMessage("MSG0022", null, locale);//統一編號有誤，煩請重新確認	
				else
					bl = true;
			}	
			else if( req.getParameter("invoiceType").equals("0") )//二聯發票
				bl = true;
				
			if(bl){
				
				bl = false;
				strCoNum = StringUtils.trimToEmpty((String)req.getParameter("coNum"));//檢查該公司統編(非開給買受人的統編)
				if( strCoNum.length() == 0 || tls.chkIdentNum(strCoNum) ){
					
					strUserPw = req.getParameter("userPw");//密碼
					strCnfUserPw = req.getParameter("cnfUserPw");//確認密碼	
					
					boolean blnCorrect = false;		
					blnCorrect = shopBaseService.checkShopId(shopId.toLowerCase());
					
					 if(blnCorrect)
						 strMsg = lng.getMessage("label.existing", null, locale);//帳號已存在
					 else if( !strUserPw.equals(strCnfUserPw) )
						 strMsg = lng.getMessage("MSG0016", null, locale);//兩次輸入密碼未一致	
					 else if( strUserPw.equalsIgnoreCase(req.getParameter("shopId")) )
						 strMsg = lng.getMessage("MSG0090", null, locale);//帳號與密碼不可相同						 
					 else
						 bl = true;
					 
				}else{
					strMsg = lng.getMessage("MSG0022", null, locale);//統一編號有誤，煩請重新確認
				}
			}	

		}

		tls = null;
		map.put("bl", bl);
		map.put("msg", strMsg);
		
		return map;
	}
	
	
	//店家會員註冊完成,發送Mail通知相關人員	
	private Email shopRegisterSuccessSendMailToManager(ShopBaseManagerVO shopBaseManagerVO, Locale locale)
	{
		Email email = new Email();
		String templatePath = "";
		StringBuffer tempContent = new StringBuffer();
		
			email.setMailServer(sysutil.getSysPrmValue("deliver", "MailServer"));  //抓取SMTP Server DNS 名稱
			email.setMailFrom(sysutil.getSysPrmValue("deliver", "EmailFrom"));    //抓取寄件者 E-Mail
			String SendMailTo = sysutil.getSysPrmValue("deliver", "shopRegisterSuccessSendMailTo");// 抓取相關人員E-Mail
		    log.info("Mail 內容的範本路徑:"+templatePath);
		    email.setSubject(lng.getMessage("MSG0095", null, locale));
		    email.setMailTo(SendMailTo);
		    tempContent = new StringBuffer();
	        tempContent.append(lng.getMessage("MSG0096", null, locale) + shopBaseManagerVO.getShopId() + "<br>");
	        tempContent.append(lng.getMessage("MSG0097", null, locale) + shopBaseManagerVO.getCoName() + "<br>");
	        tempContent.append(lng.getMessage("MSG0098", null, locale) + shopBaseManagerVO.getTel() + "<br>");
	        tempContent.append(lng.getMessage("MSG0099", null, locale) + shopBaseManagerVO.getMobilePhone() + "<br>");
	        tempContent.append(lng.getMessage("MSG0100", null, locale) + shopBaseManagerVO.getCoPeople() + "<br>");
	        tempContent.append(lng.getMessage("MSG0101", null, locale) + shopBaseManagerVO.getSource() + "<br>");
//	        tempContent.append("會員等級 : " + shopLevel + "<br>");
	        email.setContent(tempContent.toString());
		
		return email;
	}
	
	
	//店家註冊完成頁
	/*@RequestMapping(value = "/shopregisterfinish", method = RequestMethod.GET)
	public String shopRegisterFinish(Model model, 
		   HttpServletRequest req, HttpSession session, Locale locale)  throws Exception {
		
		String serverName = req.getServerName();
		if(serverName.equals("localhost")){
			serverName = "localhost/deliver";
		}
		String resultMsg = lng.getMessage("MSG0091", null, locale);//註冊成功
		model.addAttribute("msg", resultMsg);
		model.addAttribute("serverName", serverName);
		
		return "shopregister/shopRegisterFinish";
	}*/
	
	
	
	//改變頁面傳的Date參數類型
	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}