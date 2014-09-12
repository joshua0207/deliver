<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%//@ page import="util.sysParameter.sysparameterutil.sysParameterUtil"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%	  
//sysParameterUtil spu = new sysParameterUtil();
/* String secrtName = StringUtils.trimToEmpty(spu.getCPValue("secrtName").trim());
spu=null;
String hostName = request.getServerName();

if(hostName.equalsIgnoreCase(secrtName)){	   
   response.sendRedirect("https://" + hostName + "/loginShopAdminAction.do");	   
} else { */
   response.sendRedirect("loginadmin/index");
//}        	  
%>
