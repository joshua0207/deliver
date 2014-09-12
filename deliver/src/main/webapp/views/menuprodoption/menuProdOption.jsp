<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB0083" /></title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
	<style>
	table {
		border-spacing: 0px;
		width: 100%;
	}
	
	td {
		padding: 0px;
	}

	</style>
	
	<link type="text/css" href="<c:url value="/resources/js/jquery/css/ui-lightness/jquery-ui-1.10.2.custom.min.css" />" rel="stylesheet"/>
	<%-- <script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.9.1.js" />"></script> --%>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.10.2.custom.min.js" />"></script>
	
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/menuCategory.css"/>" rel="stylesheet"/>
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/niceforms-default.css"/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/button.css"/>" rel="stylesheet"/>
	<script type="text/javascript">
	
		$(function(){	
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				//alert(msg);
			}
		});
		
		window.alert = function(msg) {
			$('#dialog').text(msg).dialog({
				closeText : "hide",
				closeOnEscape : true,
				modal : true,
				buttons : {
					OK : function() {
						$(this).dialog('close');
					}
				}
			});
		};

		window.confirm = function(msg, e) {
			$("#dialog").text(msg).dialog({
				resizable : false,
				height : 140,
				modal : true,
				buttons : {
					'<spring:message code="LAB0090" />' : function() {
						e();
					},
					'<spring:message code="LAB0091" />' : function() {
						$(this).dialog('close');
						return false;
					}
				}
			});
		};
		
		function check(str) {
			dataForm.prodOptionId.value = str;
			$("#dataForm").submit();
		}

		function checkDelete(prodOptionId, prodNum) {
			if(prodNum >0){
				alert('<spring:message code="LAB0106" />');
				return;
			}else{
				$("#dialog").text('<spring:message code="LAB0089" />').dialog({
				      resizable: false,
				      height:140,
				      modal: true,
				      buttons: {
				        '<spring:message code="LAB0090" />': function() {
				        	delForm.prodOptionId.value = prodOptionId;
							$("#delForm").submit();
				        },
				        '<spring:message code="LAB0091" />' : function() {
				          $(this).dialog('close');
				        }
				      }
				 });
			}
		}
		
		function addProdOption(){
			
			location.href='<c:url value="/menuprodoption/shop/addprodoption" />';
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
                   
                   		<div id="dialog"></div>
						<h2 class="campaign_ttl"><spring:message code="LAB0083" /></h2>
						<br />
						<p align="center">
							<font color="red" size="3">
							${msg}
							</font>
						</p>
						<br />
						<div align="center">
							<button onclick="addProdOption();">
								<spring:message code="LAB1025" />
							</button>
						</div>
						<br>
						<div align="center">
							<font color="red"><spring:message code="LAB0136" /></font>
						</div>
						<br>
						<p align="center">
							<font color="red"></font>
						</p>
						<div class="shopDataForm" style="width:100%">
							<table id="tb" border="0" cellspacing="2" cellpadding="3"  width="100%">
								<tr>
									<th width="20%"><spring:message code="LAB0137" /></th>
									<th width="10%"><spring:message code="LAB0138" /></th>
									<th width="40%"><spring:message code="LAB0139" /></th>
									<th width="15%"></th>
									<th width="15%"></th>
								</tr>
					
								<c:forEach items="${menuProductItemList}" var="menuProductItem" varStatus="s">
								<tr>
									<td class="embg">${menuProductItem.menuItmeName}</td>
									<c:if test="${menuProductItem.menuChoice eq '1'}">
										<td class="fillembg"><spring:message code="LAB0140" /></td>
									</c:if>
									<c:if test="${menuProductItem.menuChoice eq '2'}">
										<td class="fillembg"><spring:message code="LAB0141" /></td>
									</c:if>
									
									<td class="fillembg">${menuProductItem.menuItmeValue}</td>
									<td class="fillembg"  >
										<a href="javascript:void(0)" onclick="check('${menuProductItem.menuItmeId}');" class="button"><spring:message code="LAB0098" /></a>
									</td>
									<td class="fillembg" >
										<a href="javascript:void(0)" onclick="checkDelete('${menuProductItem.menuItmeId}','${menuProductItem.count}');" class="button"><spring:message code="LAB0099" /></a>
									</td>
								</tr>
								</c:forEach>
								
							</table>
						</div>
						<form name="dataForm" id="dataForm" action="menuprodoption/shop/editprodoption" method="post">
							<input type="hidden" id="prodOptionId" name="prodOptionId">
						</form>
						<form name="delForm" id="delForm" action="menuprodoption/shop/deleteitemoptionvalue" method="post">
							<input type="hidden" id="prodOptionId" name="prodOptionId">
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