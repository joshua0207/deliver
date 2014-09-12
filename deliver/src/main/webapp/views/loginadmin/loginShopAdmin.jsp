<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.order.util.Tools"%>
<%@include file="/views/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <%@include file="/views/meta.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="ShopLogin" /></title><!-- 店家登入 -->    

       <!-- header's css start-->
   	   <link href="<c:url value="/resources/cbg-include/css/login_css.css"/>" rel="stylesheet" type="text/css" />
       <!-- header's css end-->    
       
       <!-- yahoo 站長工具 Begin -->
        <!-- yahoo 站長工具 End -->
       
		<script language="javascript">
		<!--
			//var MSG0053 = "<bean:message key="MSG0053" />";
			
			$(function(){	
				var msgAlert = '<c:out value="${msgAlert}" />';
				if(msgAlert != null && msgAlert != ''){
					alert(msgAlert);
				}
			});
			
			 function checkData()
			{
				var htmlObjShopId;
				var htmlObjPw;
				var htmlObj;

				htmlObjShopId = document.getElementById("shopId");//帳號
				htmlObjPw = document.getElementById("password");//密碼
				
				//檢查是否有關鍵字或欄位是否空白
				if(  checkKeyWord(htmlObjShopId.value) && checkKeyWord(htmlObjPw.value) && onlyEngAndNum(htmlObjShopId.value) && onlyEngAndNum(htmlObjPw.value) && 
					htmlObjShopId.value.length >= 4 && htmlObjShopId.value.length <= 30 && htmlObjPw.value.length >= 4 && htmlObjPw.value.length <= 30){
					htmlObj = document.forms["theForm"];
					htmlObj.submit();
				} else{
					alert('<spring:message code="MSG0053" />');
				}

			    htmlObjShopId = null;
			    htmlObjPw = null;
			    
			}	 
			 
			 
			function forgetPw(){
				
				location.href = '<c:url value="/loginadmin/forgetpassword" />';
			}

		-->
		</script>
  </head>
  <body>
	    <div id="login_logo"><img src="<c:url value="/resources/cbg-include/images/logo/logo.gif" />" width="392" height="102"/></div>  
	    <div id="login_line">     
	      <div id="login_box">
	        <form action="loginadmin/loginChick" method="post" name="theForm" id="theForm" >       
                <div id="login_input">           
                    <p><spring:message code="useradmin" /> :<input name="shopId" id="shopId" type="text" size="24" maxlength="30" /></p>  <!-- 帳號 -->          
                    <spring:message code="password" /> :<input name="password" id="password" type="password" size="25" maxlength="30" /> <!-- 店家登入 -->  
                </div>  
                <br/>   
                <input name="login_submit" id="login_submit" type="button" value="<spring:message code="ShopLogin" />" onclick="checkData();"/>   
                <div id="login_forget_txt">
	                <input type="checkbox" id="chkRememberMe" name="chkRememberMe" value="chkRememberMe" /><spring:message code="RememberAccount" /><br />                   
					<a href="javascript:forgetPw();"><spring:message code="ForgetPw" /></a>
                </div>                                
	        </form>       
  
	      </div>     
	      <!-- 加入會員 -->
	      <div id="login_join">       
            <div id="login_join_btn">
            	<a href="shopbasemanager/serviceterms"><img src="<c:url value="/resources/cbg-include/images/logo/black.gif" />" width="55" height="55" border="0" /></a>
            </div>     
	      </div>      
	    </div>
	    <div id="login_text">Copyright</div>
	    <br/><br/>
	    ${msg}
  </body>
</html>