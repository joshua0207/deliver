<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@ include file="/views/ssi/niceforms.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB0162" /></title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
		<%    
		  response.setHeader("Pragma","No-cache");    
		  response.setHeader("Cache-Control","no-cache");    
		  response.setDateHeader("Expires",   0);    
		%>
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

<link type="text/css" href="<c:url value="/resources/cbg-include/css/menuCategory.css"/>" rel="stylesheet"/>
<link type="text/css" href="<c:url value="/resources/cbg-include/css/niceforms-default.css"/>" rel="stylesheet" />
<link type="text/css" href="<c:url value="/resources/cbg-include/css/button.css"/>" rel="stylesheet"/>
</head>
	<body id="body" onLoad="pageOnload();">
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
                   
                   
						<h2 class="campaign_ttl"><spring:message code="LAB0162" /></h2>
						<br />
						<p align="center">
							<font color="red" size="3">
							${msg}
							</font>
						</p>
						<br />

						<div class="acForm">
							
								<div class="shopDataForm" style="width: 530px;">
									<span id="msg" style="color: #F00"> 
									</span>
								</div>
					
								<div class="shopDataForm" style="width: 530px;">
									<span class="imptTitle"></span>
									<spring:message code="LAB0180" />
								</div>
								<div class="shopDataForm" style="width: 530px;">
									<spring:message code="LAB0181" />
									
									<spring:message code="LAB0182" />
								</div>
								<div class="shopDataForm" style="width: 530px;">
									<table id="shopHolidayTab" class="table_form" border="0"
										align="center" cellspacing="0" cellpadding="0" width="100%">
										<tr>
											<th width="20%"></th>
											<td align="left"><span id="msg" style="color: #F00"> 
											</span></td>
										</tr>
										<tr>
											<td class="orangeSortTitle"><spring:message code="LAB0183" /></td>
											<td class="fillembg">${customPoMast.orderId}</td>
										</tr>
										<tr>
											<td class="orangeSortTitle"><spring:message code="LAB0184" /></td>
											<td class="fillembg">
											<c:if test="${customPoMast.orderSource eq '1'}">
												<spring:message code="LAB0185" />
											</c:if>
											<c:if test="${customPoMast.orderSource eq '2'}">
												<spring:message code="LAB0186" />
											</c:if>
											</td>
										</tr>
										<tr>
											<td class="orangeSortTitle"><spring:message code="LAB0169" /></td>
											<td class="fillembg">
											<c:if test="${customPoMast.orderKind eq '1'}">
												<spring:message code="LAB0174" />
											</c:if>
											</td>
										</tr>
										<tr>
											<td class="orangeSortTitle"><spring:message code="LAB0187" /></td>
											<td class="fillembg">${customPoMast.contactName}</td>
										</tr>
										<tr>
											<td class="orangeSortTitle"><spring:message code="LAB0188" /></td>
											<td class="fillembg">${customPoMast.contactPhone}</td>
										</tr>
										
										<tr>
											<td class="orangeSortTitle"><spring:message code="LAB0189" /></td>
											<td class="fillembg">${customPoMast.deliverAddress}</td>
										</tr>
										<tr>
											<td class="orangeSortTitle">
											<spring:message code="LAB0190" />
											  </td>
											<td class="fillembg">${customPoMast.deliverDateTime}</td>
										</tr>
										
										<tr>
											<td class="orangeSortTitle"><spring:message code="LAB0191" /></td>
											<td class="fillembg">${customPoMast.orderAmount}</td>
										</tr>
										<tr>
											<td class="orangeSortTitle"><spring:message code="LAB0192" /></td>
											<td class="fillembg">${customPoMast.shopmemo}</td>
										</tr>
									</table>
									<hr />
									<table class="table_form" border="0" align="center" cellspacing="0"
										cellpadding="0" width="100%">
										<tr>
											<td class="orangeSortTitle"><spring:message code="LAB0193" /></td>
											<td class="orangeSortTitle"><spring:message code="LAB0194" /></td>
											<td class="orangeSortTitle"><spring:message code="LAB0195" /></td>
											<td class="orangeSortTitle"><spring:message code="LAB0196" /></td>
											<td class="orangeSortTitle"><spring:message code="LAB0197" /></td>
										</tr>
										
										<c:set var="totalPrice" value="0"></c:set>
										<c:forEach items="${shopPoDetlList}" var="shopPoDetl" varStatus="s">
										<tr>
											<td class="embg" style="text-align: left">${shopPoDetl.itemName}</td>
											<td class="embg" style="text-align: center">${shopPoDetl.unitPrice}</td>
											<td class="embg" style="text-align: right">${shopPoDetl.qty}</td>
											<td class="embg" style="text-align: right">${shopPoDetl.amount}</td>
											<td class="embg" style="text-align: left">${shopPoDetl.itemDetl}</td>
											
											<c:set var="amount" value="${shopPoDetl.amount}"></c:set>
											<c:set var="totalPrice" value="${totalPrice+amount}"></c:set>
										</tr>
										</c:forEach>
										
										<tr>
											<td><spring:message code="LAB0198" /></td>
											<td></td>
											<td></td>
											<td></td>
											<td style="text-align: right">${totalPrice}</td>
										</tr>
									</table>
									
								</div>
								<br/>
								<center><button class="glay_b" type="button" value="<spring:message code="LAB0199" />" onclick="javascript:history.back(1);"><spring:message code="LAB0199" /></button></center>
							
						</div>
						<br/>
					
						<div class="clear_both"></div>
						<!-- <div id="divSendMailToUser" style="display: none">
							聯絡消費者
							<form name="mailForm" id="mailForm" action="/singleShop/sendMailToUser.action" method="post">
								<table id="sendMailToUserTab" class="table_form" border="0"
									align="center" cellspacing="0" cellpadding="0" width="100%">
									<tr>
					
										<td class="orangeSortTitle">訂購人E-mail</td>
										<td class="fillembg"></td>
									</tr>
					
									<tr>
					
										<td class="orangeSortTitle">mail內容</td>
										<td class="fillembg"><textarea id="mailContent"
												name="mailContent" rows="6" cols="50"></textarea></td>
									</tr>
									<tr>
					
										<td></td>
										<td align="center"><button type="button"
												onclick="sendMailToUserCheck();">傳送</button>
											<button type="button" onclick="closeDivSendMailToUser();">取消</button></td>
									</tr>
					
								</table>
								<input type="hidden" id="orderId" name="orderId"
									value='201405191453130242' />
							</form>
					
						</div> -->

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