package com.order.util;


import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

@Component
public class Language {
	
	private ResourceBundle rb;

	public Language()
	{	
//		Locale locale = new Locale("UTF-8");
//		Locale locale = Locale.getDefault(); // get zh_CN  
//    	Locale.setDefault(Locale.TAIWAN); 
		try{
			
			
//			Locale locale1 = new Locale(Locale.CHINESE, Locale.TAIWAN);
			rb = ResourceBundle.getBundle("conf.i18n.message");
		}catch(Exception e){
			rb = ResourceBundle.getBundle("conf.i18n.message");
		}		
	}
	
	public void setPath(String strPath)
	{
		rb = ResourceBundle.getBundle(strPath);
	}
	
	public String getStr(String strKey) throws Exception
	{
		String str = "";
		
		try
		{
//			str = new String(rb.getString(strKey).getBytes("utf-8"));
//			str = rb.getString(strKey);
//			str = new String(rb.getString(strKey).getBytes("utf-8"),"ISO-8859-1");
			//str = new String(rb.getString(strKey));
			str = new String(rb.getString(strKey).getBytes("ISO-8859-1"),"utf-8");
			//str = new String(rb.getString(strKey).getBytes("ISO-8859-1"));
		}catch(Exception ex){
			str = "";
	        throw ex;
		}
		return str;
	}
}
