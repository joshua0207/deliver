<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@ include file="/views/ssi/niceforms.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB0047" /></title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
	
	<%--另一個日期 <link href="<c:url value="/resources/js/timedate/jquery-ui-1.10.2.custom.min.css"/>" rel="stylesheet" type="text/css" />
	<script src="<c:url value="/resources/js/timedate/jquery-ui-1.10.2.custom.min.js"/>"></script> --%>
	<link href="<c:url value="/resources/js/timedate/hot-sneaks/jquery-ui.css"/>" rel="stylesheet" type="text/css" />
	<script src="<c:url value="/resources/js/timedate/jquery-ui-1.8.6.custom.min.js"/>"></script>
	<script src="<c:url value="/resources/js/timedate/jquery.ui.datepicker-zh-TW.js"/>"></script>
	<style type="text/css">
		.saveCancelBtn{
			height:27px;
			margin-bottom:10px;
		}
		.shopDataForm{
			margin:0 auto;
			width:500px;
			height:auto;
			border:#fb9512 1px solid;
			font-size:13px;
			coloe:#757575;
		}
		
	</style>

	
	<script type="text/javascript">
	
		$(function() {
			
			var msg = '<c:out value="${msg}" />';
			if (msg != null && msg != '') {
				//alert(msg);
			}
			
			$('#addtr').click(function() {
								var No = parseInt($(".table_form tbody tr:last").find('td:first').text()) + 1;
								if (isNaN(No)) {
									No = 1;
								}
								if (parseInt($(".table_form tbody tr").length) >= 1) {
									$('#rmtr').show();
								}

								var s1 = '<script type="text/javascript">';
								var s2 = "  $(function() {$( '#holidayData"+ No + "' ).datepicker({yearRange: '2014:2034',changeMonth: true,changeYear: true,    dateFormat : 'yy-mm-dd'});});  ";
								var s3 = "<\/script>";
								var str = '<td >' + No+ '</td><td class="embg3"><spring:message code="LAB0049"/></td>';
								str = str+ '<td class="fillembg">'+ s1+ s2+ s3+ '<input type="text" id="holidayData'+ No+ '" name="holidayData"  onclick="datepicker(holidayData' + No + ');"></td>';

								$(".table_form tbody").append('<tr>' + str + '</tr>');
			});

			$('#rmtr').click(function() {
				$(".table_form tbody tr:last").detach();
				if (parseInt($(".table_form tbody tr").length) <= 1) {
					//$('#rmtr').hide();
				}
			});
	
		});
	
		function check() {
			var holidayDataVar = document.shopHolidayForm.holidayData;
			if (typeof holidayDataVar != 'undefined') {
				if (typeof holidayDataVar.value != 'undefined') {
	
					if (holidayDataVar.length > 0) {
						for ( var i = 0; i < holidayDataVar.length; i++) {
							if (holidayDataVar[i].value == "") {
								window.alert('<spring:message code="LAB0052"/> ' + (i + 1) + '<spring:message code="LAB0053"/>');
								return;
							}
						}
					} else {
						if ((holidayDataVar.value) == "") {
							window.alert('<spring:message code="LAB0054"/>');
							return;
						}
					}
	
				} else {
					for ( var i = 0; i < holidayDataVar.length; i++) {
						if (holidayDataVar[i].value == "") {
							window.alert('<spring:message code="LAB0052"/> ' + (i + 1) + '<spring:message code="LAB0053"/>');
							return;
						}
					}
				}
			} else {
				//alert('4項目 1，必須有值!');
				//return;
			}
	
			document.shopHolidayForm.submit();
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
                   
						<h2 class="campaign_ttl"><spring:message code="LAB0047" /></h2>
						<p align="center">
							<font color="red" size="3">
							${msg}
							</font>
						</p>

						<table align="center">
							<tr align="center">
								<td>
									<button name="addtr" id="addtr"><spring:message code="LAB1025"/></button>
								</td>
								<td>
									<button onclick="check();"><spring:message code="LAB1030"/></button>
								</td>
								<td>
									<button name="rmtr" id="rmtr"><spring:message code="LAB0055"/></button>
								</td>
							</tr>
						</table>
						
						<div class="shopDataForm">
							<form name="shopHolidayForm" id="shopHolidayForm" action="servicetime/shop/addholiday" method="post">
								<table id="shopHolidayTab" class="table_form" border="0"
									cellspacing="0" cellpadding="0" width="100%">
						
									<tbody>
										
										<c:forEach items="${holiDayList}" var="holiday" varStatus="s">
										<tr>
											<td>${s.index+1}</td>
											<td class="embg3"><spring:message code="LAB0049"/></td>
											<td class="fillembg">
											<script type="text/javascript">
												$(function() {
													$('#holidayData${s.index+1}').datepicker({
														yearRange : "2014:2034",
														changeMonth : true,
														changeYear : true,
														dateFormat : 'yy-mm-dd'
													});
												});
											</script> 
											<input type="text" id="holidayData${s.index+1}" name="holidayData" value="${holiday.holiDate}"></td>
										</tr>
										</c:forEach>
										
									</tbody>
									
								</table>
							</form>
						</div>

					</div>   <!--<div class="serviceTermForm">-->                 
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