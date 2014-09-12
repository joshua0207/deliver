<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@ include file="/views/ssi/outSendMoney.jsp" %>
<%@ include file="/views/ssi/niceforms.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB0063" /></title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
	<style type="text/css">
		
		
	</style>
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
	<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/addr.js"/>"></script> 
    <script type="text/javascript" src="<c:url value="/resources/js/googleMap/static.js"/>"></script> 
    <script type="text/javascript" src="<c:url value="/resources/js/googleMap/googleMapFunction.js"/>"></script> 
	<script src="<c:url value="/resources/cbg-include/Scripts/colors.js"/>" type="text/javascript" ></script>
	<script type="text/javascript">
		
		$(function(){	
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				//alert(msg);
			}
			
			//載入訂單通知方式設定資料
			setChkBoxChkedByVal("notice",'<c:out value="${shopBaseVO.sendOrderWay}" />');
			
			pageOnload();
		});
		
		//隱藏TR
		/* function hide_tr(str) {
			var result_style = document.getElementById(str).style;
			result_style.display = 'none';
		}
	
		//顯示TR
		function show_tr(str) {
			var result_style = document.getElementById(str).style;
			result_style.display = 'table-row';
		}
	
		function checkShowHide_tr(typeStr, str) {
			var result_style = document.getElementById(str).style;
			//alert("result_style.display:" + result_style.display);
			if (result_style.display == "table-row") {
				result_style.display = 'none';
			} else {
				result_style.display = 'table-row';
			}
		} */
	
		function pageOnload() {
	
			//E -> e-mail
			//S ->簡訊
			//M ->行動裝置
			//儲存格式:E,S,M
			$("#chkWay").click(function() {
				var noticeObj = document.dataForm.notice;
				var noticeStr = "";
				
				for (var i = 0; i < noticeObj.length; i++) {
					if (noticeObj[i].checked) {
						noticeStr += noticeObj[i].value + ",";
					}
				}
				
				if ((noticeStr.indexOf("E") >= 0)) {
					if (document.dataForm.mail.value == "") {
						alert('<spring:message code="LAB0074" />');
						return false;
					}
				}
				if ((noticeStr.indexOf("S") >= 0)) {
					//alert("11document.dataForm.phone.value:" + document.dataForm.phone.value);
					if (document.dataForm.phone.value == "") {
						alert('<spring:message code="LAB0075" />');
						return false;
					}
				}
				if ((noticeStr.indexOf("M") >= 0)) {
					//if (document.dataForm.phone.value == "") {
					//	alert("行動裝置必須有值!");
					//	return false;
					//}
				}
				if ((noticeStr.indexOf("E") < 0)) {
					document.dataForm.mail.value = "";
				}
				//if ((noticeStr.indexOf("2") < 0) && (noticeStr.indexOf("3") < 0)) {
				if ((noticeStr.indexOf("S") < 0)) {
					document.dataForm.phone.value = "";
				}
	
				$('#dataForm').submit();
			});
	
			event: 'blur', $('#dataForm').validate({
				rules : {
					phone : {
						rangelength : [ 10, 13 ],
						digits : true
					},
					mail : {
						email : true
					}
				},
				messages : {}
			});
		}
		
	</script>
	
	</head>
	<body id="body">
        <div id="wrapper">

            <!-- header's jsp start-->  
            <%@ include file="/views/cbg-include/header.jsp" %> 
            <%@ include file="/views/cbg-include/wrap_top.jsp" %> 
            <!-- header's jsp end-->  

			
            <div id="content_wrap">
                <div id="content">  
					
                    <!-- left's jsp start-->                            
                    <%@ include file="/views/cbg-include/left.jsp" %> 
                    <!-- left's jsp end-->    

                 <div id="cont_right">  
                        
                   <div class="serviceTermForm">
                   
                   
						<h2 class="campaign_ttl"><spring:message code="LAB0063" /></h2>
						<br />
						<p align="center">
							<font color="red" size="3">
							${msg}
							</font>
						</p>
						<br />

						<form name="dataForm" id="dataForm" action="websetting/shop/saveordernotice" method="post">
							<div class="shopDataForm">
				
								<table id="tb" border="0" cellspacing="0" cellpadding="0"
									width="100%">
									<tr>
										<th colspan="2"><spring:message code="LAB0076" /></th>
									</tr>
									<tr>
										<td class="embg">
										<input type="checkbox" name="notice" id="notice" value="E"/>
										<label for="radio2"></label><spring:message code="LAB0077" /></td>
										<td class="embg"><spring:message code="LAB0079" />
										<input type="text" id="mail"name="mail" value="${shopBaseVO.sendEmail}" size="10" maxlength="100" style="width: 150px;"/></td>
									</tr>
				
									<tr style="display:none">
										<td class="embg">
										<input type="checkbox" name="notice" id="notice" value="S"/>
										<label for="radio2"></label><spring:message code="LAB0078" /></td>
										<td class="embg"><spring:message code="LAB0080" />
										<input type="text" id="phone" name="phone" value="${shopBaseVO.sendSms}" size="10" maxlength="13" />
									</tr>
				
									<tr>
										<td class="embg">
										<input type="checkbox" name="notice" id="notice" value="M" />
										<label for="radio2"></label><spring:message code="LAB0081" /></td>
										<td class="embg" colspan="3"></td>
									</tr>
				
				
								</table>
				
				
							</div>
							<br />
							<div align="center">
								<button id="chkWay" type="button"><spring:message code="LAB1030" /></button>
							</div>
						</form>
										
						

					</div>     <!--<div class="serviceTermForm">-->                 
                </div>  
				<div class="clear_both"></div>
				</div>   
			</div>
        </div>

        <!-- FOOTER start-->  
        <%@ include file="/views/cbg-include/footer.jsp" %> 
        <!-- FOOTER end-->  

    </body>
    
</html>