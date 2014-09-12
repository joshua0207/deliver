<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@ include file="/views/ssi/niceforms.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB0062" /></title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
	<style type="text/css">
		
		
	</style>
		
	<%-- <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/js/jquery/validate/css/screen.css"/>" /> --%>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/validate/jquery.validate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.maxlength.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/validate/messages_zh_TW.js"/>"></script> 
	
	<script type="text/javascript">
	
		$(function(){	
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				//alert(msg);
			}
			
			pageOnload();
		});
		
		function pageOnload() {
			
			$("#chkForm").click(function() {
				
				if ($("#dataForm").valid()) {
					$("#chkForm").attr("disabled", "disabled");
				}

				if ((dataForm.sendLeadTime.value) == "") {
					alert('<spring:message code="LAB0064" />');
					return false;
				}
				if ((dataForm.sendMiniMoney.value) == "") {
					alert('<spring:message code="LAB0065" />');
					return false;
				}

				$('#dataForm').submit();
			});

			event: 'blur', $('#dataForm').validate({
				rules : {
					sendLeadTime : {
						required : true,
						rangelength : [ 1, 4 ],
						digits : true
					},
					sendMiniMoney : {
						required : true,
						rangelength : [ 1, 4 ],
						digits : true
					}
				},
				messages : {
					listCategory : {
						required : '<spring:message code="LAB0066" />'
					}
				}
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
                   
						<h2 class="campaign_ttl"><spring:message code="LAB0062" /></h2>
						<br />
						<p align="center">
							<font color="red" size="3">
							${msg}
							</font>
						</p>
						<br />
						
						<form name="dataForm" id="dataForm" action="websetting/shop/savesendsetting" method="post">
							<div class="shopDataForm">
								<table id="tb" border="0" cellspacing="0" cellpadding="0"
									width="100%">
									<tr>
										<th></th>
										<td align="left"><span id="msg" style="color: #F00"> 
										</span></td>
									</tr>
				
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0067" /></td>
										<td class="fillembg">
										<c:choose>
											<c:when test="${shopBaseVO.sendFunction == 'Y'}">
												<input type="radio" id="sendFlag" name="sendFlag" value="Y" checked><spring:message code="LAB0068" />
												<input type="radio" id="sendFlag" name="sendFlag" value="N"><spring:message code="LAB0069" />
											</c:when>
											<c:otherwise>
												<input type="radio" id="sendFlag" name="sendFlag" value="Y"><spring:message code="LAB0068" />
												<input type="radio" id="sendFlag" name="sendFlag" value="N" checked><spring:message code="LAB0069" />
											</c:otherwise>
										</c:choose>
										</td>
									</tr>
				
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0070" /></td>
										<td class="fillembg">
											<c:choose>
												<c:when test="${shopBaseVO.sendOnlineOrder == 'Y'}">
													<input type="radio" id="sendOnlineOrder" name="sendOnlineOrder" value="Y" checked><spring:message code="LAB0068" />
													<input type="radio" id="sendOnlineOrder" name="sendOnlineOrder" value="N"><spring:message code="LAB0069" /><spring:message code="LAB0071" />
												</c:when>
												<c:otherwise>
													<input type="radio" id="sendOnlineOrder" name="sendOnlineOrder" value="Y"><spring:message code="LAB0068" />
													<input type="radio" id="sendOnlineOrder" name="sendOnlineOrder" value="N" checked><spring:message code="LAB0069" /><spring:message code="LAB0071" />
												</c:otherwise>
											</c:choose>
										 </td>
									</tr>
				
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0072" /></td>
										<td class="fillembg">
										<input type="text" maxlength="4" name="sendLeadTime" id="sendLeadTime" value="${shopBaseVO.sendWaitTime}" style="width: 30px" />
										</td>
									</tr>
				
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0073" /></td>
										<td class="fillembg"><input type="text" maxlength="4" name="sendMiniMoney" id="sendMiniMoney" value="${shopBaseVO.sendMinAmount}" style="width: 30px" />
										</td>
									</tr>
				
								</table>
							</div>
							<br />
							<div align="center">
								<button id="chkForm" type="button"><spring:message code="LAB1030" /></button>
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