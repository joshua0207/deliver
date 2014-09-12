<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@ include file="/views/ssi/niceforms.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="label.register.finish" /></title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/login.css"/>" rel="stylesheet"/>
	
	<link type="text/css" href="<c:url value="/resources/js/jquery/css/ui-lightness/jquery-ui-1.10.2.custom.min.css"/>" rel="stylesheet"/>
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.9.1.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.10.2.custom.min.js"/>"></script>
	<script>
	window.alert = function(msg) {
		$('#dialog').text(msg).dialog({
			closeText: "hide",
			closeOnEscape:true,
			modal:true,		
			buttons : {
				OK :function(){
					$(this).dialog('close');
				}
			}
		});
	};
	</script>
	
	<style type="text/css">
		
	</style>
	
	<script type="text/javascript">
	
		$(function(){	
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				//alert(msg);
			}
		});
	
	</script>

	</head>
	<body id="body" onload="reciprocal('reciprocal',10,'http://${serverName}/');">
      
      <div id="dialog"></div>
	
		<div class="heater">
			<div class="logo">
				<a href="http://deliver.mtaxi.com.tw/">
					<img src="<c:url value="/resources/cbg-include/images/logo/logoback.jpg"/>" width="238" height="94" border="0" />
				</a>			
			</div>
		</div>

		<div class="serviceTermForm">
			
		  <p></p>
                    <h1 style="color: blue;"><spring:message code="label.register.finish" /></h1>
                    
                    <br/>
                    <h2 style="color: red;"><c:out value="${msg}" /></h2>
                    
					
					<br/><br/> <br/><br/> <br/><br/> <br/><br/> <br/>
		                <span id="reciprocal">5</span><spring:message code="LAB0200"/>
		                <br/> <br/>
		                <h2>
		                <span><a href="http://${serverName}/"><spring:message code="Links"/></a></span>
		                </h2>
		           
		
		</div>
		<br/><br/> <br/><br/> <br/><br/> <br/><br/> <br/><br/> <br/><br/> <br/><br/> <br/><br/> <br/><br/> <br/>
		
		<!-- FOOTER start-->  
	      <%@ include file="/views/cbg-include/footer2.jsp" %> 
	    <!-- FOOTER end--> 

    </body>

</html>