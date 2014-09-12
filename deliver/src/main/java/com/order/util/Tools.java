package com.order.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;


@Component
public class Tools
{
	private static final Log log = LogFactory.getLog("Tools.class");
	
	//將值寫入cookie,預設一天
	public void setCookie(HttpServletResponse res,String cookieName,String cookieVal){
		Cookie cookieTemp = new Cookie(cookieName,cookieVal) ;
		
		cookieTemp.setMaxAge(60 * 60 * 1);//秒*分*日
		
		res.addCookie(cookieTemp);
	}
	
	//將值寫入cookie,包含時間
	public void setCookie(HttpServletResponse res,String cookieName,String cookieVal,int intDay){
		Cookie cookieTemp = new Cookie(cookieName,cookieVal);
		
		cookieTemp.setMaxAge(60 * 60 * intDay);//秒*分*日
		
		res.addCookie(cookieTemp);
	}	
	
	//取得cookie值
	public String getCookie(HttpServletRequest req,String cookieName){
		String strTemp = "";
		
		Cookie cks[] = req.getCookies();

		for(int i = 0;i < cks.length; i++) {
		
			if( cks[i].getName().equals(cookieName) ){
				strTemp = cks[i].getValue();
				break;
			}
		}
		
		return strTemp;
	}
	
	//移除cookie值
	public void removeCookie(HttpServletResponse res, HttpServletRequest req ,String cookieName){
		Cookie[] cookies = req.getCookies();
		   if (cookies!= null)
		   {
		      for (int i = 0; i< cookies.length; i++)
		      {
		          if (cookies[i].getName().equals(cookieName))
		          {
		              Cookie cookie = new Cookie(cookieName,"");//这边得用”",不能用null
//		              cookie.setPath("/");//设置成跟写入cookies一样的
//		              cookie.setDomain(“.wangwz.com”);//设置成跟写入cookies一样的
		              res.addCookie(cookie);
		          }
		      }
		   }
		
//		Cookie cookieTemp = new Cookie(cookieName,null);      //假如要刪除名稱為cookieName的Cookie
//		cookieTemp.setMaxAge(0);//設為0->刪除
//		cookieTemp.setPath("/"); //項目所有目錄均有效，這句很關鍵，否則不敢保證刪除
//		res.addCookie(cookieTemp);//重新寫入，將覆蓋之前的
	}	
	
	
	/**
     * 获取cookie的值
     * @param req
     * @param name
     * @return
     */
    public static String getName(HttpServletRequest req,String name) {
        Cookie cookie = get(req,name);
        String cookieVal = (null == cookie) ? null : cookie.getValue();
        return cookieVal;
    }
    public static Cookie get(HttpServletRequest req,String name) {
        Map<String,Cookie> cookieMap = _readCookieMap(req);
        if(cookieMap.containsKey(name)) {
            return (Cookie)cookieMap.get(name);
        } else {
            return null;
        }
    }
    private static Map<String,Cookie> _readCookieMap(HttpServletRequest req) {
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = req.getCookies();
        if(null != cookies) {
            for(Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
    
	
	public boolean chkEngOrNum(String strValue) throws Exception
	{
		boolean blChk = false;
		
		if(strValue.matches("^[A-Za-z0-9]+$"))//限定英文字母及數字  
			blChk = true;
		
		return blChk;
	}		
	
	public boolean chkNumber(String strValue) throws Exception
	{
		boolean blChk = false;
		
		if(strValue.matches("[0-9]*"))//限定數字 
			blChk = true;
		
		return blChk;
	}		
	
	//檢查欄位是否為空白或null及長度是否太短及是否用到了關鍵字
	public boolean chkReqValue(HttpServletRequest req,String strFieldValue,int intMinLength,int intMaxLength)throws Exception
	{//
		//log.info("---chkReqValue start---");
		boolean blnCorrect = true;
		
		if( StringUtils.isNotBlank(req.getParameter(strFieldValue)) && 
				new String(req.getParameter(strFieldValue)).trim().length() >= intMinLength &&
				new String(req.getParameter(strFieldValue)).trim().length() <= intMaxLength )
		{
			
			String strTemp = String.valueOf(req.getParameter(strFieldValue)).toLowerCase();
			String strAry[] = {"update ","delete ","insert ","drop ","select ","--","*",
					" like "," where ","="," from ","shutdown ","Truncate ","%"," and "," or ","'","\"","<",">"};							
			//blnCorrect = true;	
			
			for(String strKeyWord:strAry)
			{	
				//if( strTemp.equalsIgnoreCase(strKeyWord))
				//之所以不用equalsIgnoreCase是因必須檢查使用者所輸入的資料是否含以上關鍵字，只要有出現就不行.ex.aa or 1==1<---出現"="就不行;
				//equalsIgnoreCase是比對整個字串用的，不適合用來檢查關鍵字 
				if( strTemp.toLowerCase().indexOf(strKeyWord) != -1 )	
				{					
					blnCorrect = false;					
					break;
				}	
				
			}	
		}
		else
			blnCorrect = false;
		
		//log.info("chkReqValue: " + strFieldValue + " Result: " + blnCorrect );
		
		return blnCorrect;
	}
	//檢查欄位是否用到了關鍵字
	public boolean chkKeyword(HttpServletRequest req,String strFieldValue)throws Exception
	{//
		//log.info("---chkReqValue start---");
		boolean blnCorrect = false;
		
		if( StringUtils.isNotBlank(req.getParameter(strFieldValue)) )
		{
			String strTemp = String.valueOf(req.getParameter(strFieldValue)).toLowerCase();
			String strAry[] = {"update ","delete ","insert ","drop ","select ","--","*",
					" like "," where ","="," from ","shutdown ","Truncate ","%"," and "," or ","'","\"","<",">"};							
			blnCorrect = true;	
			
			for(String strKeyWord:strAry)
			{	
				//if( strTemp.toLowerCase().indexOf(strKeyWord) != -1 )
				if( strTemp.equalsIgnoreCase(strKeyWord))	
				{					
					blnCorrect = false;
					break;
				}	
				
			}	
		}
		else
			blnCorrect = false;
		
		//log.info("chkReqValue: " + strFieldValue + " Result: " + blnCorrect );
		
		return blnCorrect;
	}
	//將含有特殊字元的字串去除特殊字元後回轉 ex.11;22(33.44 => aryList.get(0)= 11,aryList.get(1)= 22......
	public ArrayList<String> delSign(String str) {

		String newStr = "";
		String strAryTemp[];
		ArrayList<String> aryListTemp = new ArrayList<String>();
		
		try{			
			for (int i = 0; i < str.length(); i++){
				
				String strTemp = str.substring(i,i+1);
				
				if (strTemp.matches("[\\u4E00-\\u9FA5]+")){   //中文
					newStr += strTemp;
				}else{
				
					byte[] c = strTemp.getBytes();
				
					for (int j = 0;j<c.length;j++){
							if (64<c[j] && c[j]< 91)     //A至Z
								newStr += strTemp;
							else  if (c[j]==46)         //.
								newStr += strTemp;
							else if (48 <= c[j] && c[j]<=57)   //0至9
								newStr += strTemp;
							else if (97 <= c[j] && c[j] <= 122)    //a至z
								newStr += strTemp;
							else{
								newStr += ";";//先將所有特殊字元全數轉換成分號
								continue;
							}//else
					}//for
				}//else
			}//for
			
			strAryTemp = newStr.split(";");//依分號做切割
			for(String strTemp:strAryTemp){
				if(strTemp != "" && strTemp.length() > 0){//若分號與分號間不等於空，則將資料存入ArrayList中
					aryListTemp.add(strTemp);
				}//if	
			}//for	
		}//try
		catch(Exception e){
			System.out.println(e.toString());
		}//catch
	
		strAryTemp = null;
		
		return aryListTemp;

	}		

	 
	  /**
	   * 驗証公司統編是否符合現行規則.
	   * @param strIdentNum 傳入所要驗証的公司統編.
	   * @return 回傳公司統編是否正確, true 代表正確.
	   */
	  public boolean chkIdentNum(String strIdentNum)
	  {
		  	int[] CHK_NUM = {1, 2, 1, 2, 1, 2, 4, 1};
		  	boolean bln = false;
		  	int intTemp = 0;
		  	
	        try
	        {
	            int intSum = 0;
	 
	            //log.info("len:" + strIdentNum.length());
	            if( strIdentNum.length() == 8 )
	            {	
		            for(int i = 0; i < CHK_NUM.length; i++)
		            {
		                //公司統編與邏輯乘數相乘.
		                int intMultiply; 
		                int intAddition;
		                
		                if( i == 5 )
		                	intTemp = Integer.parseInt(strIdentNum.substring(i, i + 1));
		                
		                intMultiply = Integer.parseInt(strIdentNum.substring(i, i + 1)) * CHK_NUM[i];
		                intAddition = ((intMultiply / 10) + (intMultiply % 10));//將相乘的結果, 取十位數及個位數相加.
		                intSum += intAddition;
		                //log.info("intAddition:" + intAddition);
	
		            }
		            //log.info("intTemp:" + intTemp);
		            //判斷總和的餘數, 假使為 0 公司統編正確回傳 true, 其它值則反之.
		            if (intSum % 10 == 0) 
		            	bln = true;
		            else if (intTemp == 7 && (intSum + 1) % 10 == 0) 
		            	bln = true;
	            }
	            return bln;

	        }
	        catch(Throwable e)
	        {
	            //如果 strIdentNum 參數為 null, 或者不是八位數, 或為其它非數值字串, 均傳回 false.
	            return false;
	        }
	  }
	  

	
}
