package com.order.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//所有功能攔截、過濾器 (攔截  shop 開頭的路徑)，多增加攔截loginVo Session 設定檔於dispatcher-servlet.xml
public class SecurityInterceptorAll implements HandlerInterceptor{
	
	final Log log = LogFactory.getLog(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler) throws Exception {
		log.info("preHandle Shop");
		
		//沒登入Session和登入資料loginVo Session，導回登入頁面
		HttpSession s = req.getSession(false);
		if(s == null || s.getAttribute("loginHomeSession") == null || s.getAttribute("loginVo") == null ){
			res.sendRedirect("../../loginadmin/sessiontimeout");
			return false;
		}
		
		return true;
	}


	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler, ModelAndView view) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object handler, Exception e)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
