<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB0161" /></title>

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
	
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/menuCategory.css"/>" rel="stylesheet"/>
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/niceforms-default.css"/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/button.css"/>" rel="stylesheet"/>
	
	<link href="<c:url value="/resources/js/timedate/hot-sneaks/jquery-ui.css"/>" rel="stylesheet" type="text/css" />
	<script src="<c:url value="/resources/js/timedate/jquery-ui-1.8.6.custom.min.js"/>"></script>
	<script src="<c:url value="/resources/js/timedate/jquery.ui.datepicker-zh-TW.js"/>"></script>
	
	<link href="<c:url value="/resources/cbg-include/css/controlPager.css"/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.controlPager.js"/>"></script>
	
	<script type="text/javascript">
	
		$(function(){	
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				//alert(msg);
			}
			
			$('#datepicker').datepicker({
				//yearRange : "2014:2034",
				changeMonth : true,
				changeYear : true,
				dateFormat : 'yy/mm/dd'
			});
			$('#datepickerE').datepicker({
				//yearRange : "2014:2034",
				changeMonth : true,
				changeYear : true,
				dateFormat : 'yy/mm/dd'
			});
			
			var page = '<c:out value="${pageNumber}" />'; //目前的頁數
			var count = '<c:out value="${pageTotal}" />'; //總頁數
			$("#pager").pager({ pagenumber: page, pagecount: count, buttonClickCallback: PageClick });
		});
		
		
		function btnQuery() {
			var datepicker = $('#datepicker').val();
			var datepickerE = $('#datepickerE').val();
			$('#pageNumber').val(1);//從第一頁

			if (datepicker == "" || datepickerE == "") {
				alert('<spring:message code="LAB0163" />');
				return false;
			} else if (datepicker > datepickerE) {
				alert('<spring:message code="LAB0164" />');
				return false;
			} else {
				document.orderListForm.submit();
			}
		}
		
		function orderDetal(orderId) {
			$('#orderId').val(orderId);
			document.orderDetalForm.submit();
		}
		
		
		function PageClick(pageclickednumber) {
			//var date1 =  $('#datepicker').val();
		    //var date2 =  $('#datepickerE').val();
		    $('#pageNumber').val(pageclickednumber);
		    document.orderListForm.submit();
			//window.location.href="/shopOrderManagerAction.do?method=orderManagerQuery&currentPageNo=" + pageclickednumber + "&datepicker=" + date1 + "&datepickerE=" + date2; 
		}
	
	</script>

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
                   
                	   <form name="orderDetalForm" id="orderDetalForm" action="ordermanager/shop/ordermanagerdetal" method="post">
							<input type="hidden" id="orderId" name="orderId">
						</form>
						
						<h2 class="campaign_ttl"><spring:message code="LAB0161" /></h2>
						<br />
						<p align="center">
							<font color="red" size="3">
							${msg}
							</font>
						</p>
						<br />
						
						<div class="shopDataForm">
							<table id="shopHolidayTab" class="table_form" border="0"
								align="center" cellspacing="0" cellpadding="0" width="100%">
								<tr>
									<td><form name="orderListForm" id="orderListForm" action="ordermanager/shop/queryordermanager" method="post">
											<spring:message code="LAB0165" /> <input type="text" id="datepicker"
												name="datepicker" size="8"
												value="${datepicker}" readonly></input>
											&nbsp; ~ &nbsp;<spring:message code="LAB0166" /> <input type="text" id="datepickerE"
												name="datepickerE" size="8"
												value="${datepickerE}" readonly></input>
											<button class="glay_b" type="button" id="btn01" name="btn01"
												value="<spring:message code="LAB0167" />" onclick="btnQuery();"><spring:message code="LAB0167" /></button>
					
										<input type="hidden" id="pageNumber" name="pageNumber" />
										</form></td>
								</tr>
							</table>
						</div>
						<br />
					
						<div class="shopDataForm" style="width: 550px;">
							<table id="shopHolidayTab" class="table_form" border="0"
								align="center" cellspacing="0" cellpadding="0" width="100%">
								<tr>
									<td><spring:message code="LAB0168" /></td>
									<td><spring:message code="LAB0169" /></td>
									<td><spring:message code="LAB0170" /></td>
									<td><spring:message code="LAB0171" /></td>
									<!-- <td>外送地址</td> -->
									<!-- <td>預定送達時間</td> -->
									<td><spring:message code="LAB0172" /></td>
									<td><spring:message code="LAB0173" /></td>
								</tr>
					
								<c:forEach items="${customPoMastList}" var="customPoMast" varStatus="s">
								<tr>
								<td class="embg">
								<a href="javascript:orderDetal('${customPoMast.orderId}');">
									${customPoMast.orderId}
								</a>
								</td>
		
								<td class="embg">
								<c:if test="${customPoMast.orderKind eq 1}">
									<spring:message code="LAB0174" />
								</c:if>
								</td>
								<td class="embg">${customPoMast.contactName}</td>
								<td class="embg">${customPoMast.contactPhone}</td>
								<%-- <td class="embg">${customPoMast.deliverAddress}</td> --%>
								<%-- <td class="embg">${customPoMast.deliverDateTime}</td> --%>
								<td class="embg">
								<c:if test="${customPoMast.orderStatus eq 1}">
									<spring:message code="LAB0175" />
								</c:if>
								<c:if test="${customPoMast.orderStatus eq 2}">
									<spring:message code="LAB0176" />
								</c:if>
								<c:if test="${customPoMast.orderStatus eq 3}">
									<spring:message code="LAB0177" />
								</c:if>
								</td>
								<td class="embg">${customPoMast.orderConfirmDate}</td>
							</tr>
										
								</c:forEach>
								
							</table>
							
							<c:if test="${null == customPoMastList || empty customPoMastList}">
							        <c:choose>
							        	<c:when test="${chk eq 'first'}">
							        		<center><spring:message code="LAB0178" /></center>
							        	</c:when>
							        	<c:otherwise>
							        		<center><spring:message code="LAB0179" /></center>
							        	</c:otherwise>
							        </c:choose>
							</c:if>
							
						</div>
						
						<div id="pager"></div><div style="clear: both;"></div>

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