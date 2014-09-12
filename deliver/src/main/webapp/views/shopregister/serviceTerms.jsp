<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@ include file="/views/ssi/niceforms.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="label.serviceTerms" /></title>

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
				alert(msg);
			}
		});
	
	</script>

	</head>
	<body id="body">
      
      <div id="dialog"></div>
	
		<div class="heater">
			<div class="logo">
				<a href="http://deliver.mtaxi.com.tw/">
					<img src="<c:url value="/resources/cbg-include/images/logo/logoback.jpg"/>" width="238" height="94" border="0" />
				</a>			
			</div>
		</div>


	<script type="text/javascript">
			function formSubmitChk() {
				if (!$("#agreeChk").is(':checked')) {
					alert('<spring:message code="LAB1067"/>');
					return false;
				}
			}
		</script>
		
		<div class="serviceTermForm">
			<form name="rgsForm" id="rgsForm" onsubmit="return formSubmitChk();" action="shopbasemanager/shopregister" method="post">
				<br/>
				<h3><spring:message code="label.serviceTerms" /></h3>
				<br/>
				<div class="serviceTermForm_orange">
					<ul>
						<h3>
							<spring:message code="LAB1051"/>
						</h3>
						<li><b><spring:message code="LAB1052"/></b><br /> <span>
								<spring:message code="LAB1053"/>
								</span></li>
						<li><b><spring:message code="LAB1055"/></b><br /> <span>
								<spring:message code="LAB1054"/>
						</span></li>
						<li><b><spring:message code="LAB1056"/></b><br /> <span>
							   <spring:message code="LAB1057"/>
						</span></li>
						<li><b><spring:message code="LAB1058"/></b><br /> <span>
							   <spring:message code="LAB1059"/>
						</span></li>
						<li><b><spring:message code="LAB1060"/></b><br /> <span>
							   <spring:message code="LAB1061"/>
						</span></li>
						<li><b><spring:message code="LAB1062"/></b><br /> <span>
							   <spring:message code="LAB1063"/>
						</span></li>
						<li><b><spring:message code="LAB1064"/></b><br /> <span>
							   <spring:message code="LAB1065"/>
						</span></li>
					</ul>
				</div>
				<div class="shopDataBtnForm">
				<br/>
				<button src="<c:url value="/resources/cbg-include/images/shopbase/btn_register_send.png"/>" id="chkForm" class="shopData_btnSend"></button>
				<input type="checkbox" id="agreeChk" name="agreeChk" value="Y" /><spring:message code="LAB1066"/>
			</div>
				
			</form>
		
		</div>
			
		<br/>
		
		<!-- FOOTER start-->  
	      <%@ include file="/views/cbg-include/footer2.jsp" %> 
	    <!-- FOOTER end--> 

    </body>

</html>