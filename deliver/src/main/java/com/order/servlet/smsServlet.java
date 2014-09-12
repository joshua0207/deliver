package com.order.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.order.web.controller.EmailMobileVerifyController;


public class smsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "Text/Plain";  //三竹簡訊要求回傳的Type
    private static final Log log = LogFactory.getLog(smsServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	response.setContentType(CONTENT_TYPE);
		
		PrintWriter out = response.getWriter();		
		String content = "";
		EmailMobileVerifyController smsrRceive = new EmailMobileVerifyController();
		String rtnValue = "N";
		
		
        try {
        	log.info("三竹簡訊回傳 MsgID:" + request.getParameter("msgid"));
        	log.info("三竹簡訊回傳 PhoneNum:" + request.getParameter("dstaddr"));
        	log.info("三竹簡訊回傳 UpdateDate:" + request.getParameter("donetime"));
        	log.info("三竹簡訊回傳 StatusFlag:" + request.getParameter("StatusFlag"));
        	
        	
        	//將資料寫入資料庫中
        	rtnValue = smsrRceive.smsDataReceive(StringUtils.trimToEmpty(request.getParameter("msgid")), 
        			                             StringUtils.trimToEmpty(request.getParameter("dstaddr")),
        			                             StringUtils.trimToEmpty(request.getParameter("donetime")), 
        			                             StringUtils.trimToEmpty(request.getParameter("StatusFlag")));
       	
        	content = "magicid=sms_gateway_rpack" + "\n" + "msgid=" + StringUtils.trimToEmpty(request.getParameter("msgid")) + "\n";
        	
        	if(rtnValue.equals("Y")){ //正常接收後, 將回傳值回拋給三竹簡訊廠商
        	   out.println(content);	
        	}   	            	
        } catch(Exception e){
            log.error(e.getMessage());
        }
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request,response);
    }

    public void destroy() {
    }
}