<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="LAB1021" /></title>

	<link href="<c:url value="/resources/cbg-include/css/login_css.css"/>" rel="stylesheet" type="text/css" />
		
	<script type="text/javascript">
	
	$(function(){	
		var msgAlert = '<c:out value="${msgAlert}" />';
		if(msgAlert != null && msgAlert != ''){
			alert(msgAlert);
		}
	});
	
	function checkData(){
		
		  var loginId = $("#account").val();			  
		  var choice = $('input[name=choice]:checked').val();

	        if(loginId == ""){
	           alert('請輸入帳號!!');
	        } else {	
	        	
	        	document.forgetPwForm.submit();
				 
	        }
		}
	
	</script>

	</head>
	<body id="body">
	<div id="body">  
    <div id="login_logo"><img src="<c:url value="/resources/cbg-include/images/logo/logo.jpg"/>" width="392" height="102" alt="2626外送外帶網" /></div>  
    <div id="login_line">     
      <div id="login_box_forget">
        <form action="loginadmin/sendforgetpw" method="post" name="forgetPwForm" id="forgetPwForm">       
          <div id="login_input_forget">       
            <input name="account" id="account" type="text" size="23" value="" />                    
          </div>    
          <div id="choice_forget">   
                              收取方式 : 
            <input type="radio"  name="choice" id="choice" value="E" checked="checked"/> E-Mail 
            <span style="display:none"><input type="radio" name="choice" id="choice" value="S" />簡訊</span>
            <input name="login"  type="button" id="login_submit_forget" value="確認送出" onclick="checkData();"/>
          </div>     
        </form>     
      </div>     
      <div id="login_forget"></div>      
    </div>
    <div id="login_text"><spring:message code="Copyright"/></div>
  </div>
    </body>

</html>