<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB0082" /></title>

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
	
	<%-- 另一種藍色刪除視窗 <link type="text/css" href="<c:url value="/resources/js/jquery/css/redmond/jquery-ui-1.10.3.custom.min.css" />" rel="stylesheet"/>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.9.1.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.10.3.custom.min.js" />"></script> --%>
	
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
					"確定刪除" : function() {
						e();
					},
					"取消" : function() {
						$(this).dialog('close');
						return false;
					}
				}
			});
		};
		
		function addCategory(){
			location.href='<c:url value="/menucategory/shop/addcategory" />';
		}
		
		function editCategory(str) {
			$('#categoryId').val(str);
			$("#dataForm").submit();
		}
		
		function deleteCategory(categoryId, prodNum) {
			if (prodNum >0) {
				alert('<spring:message code="LAB0087" />'+prodNum+'<spring:message code="LAB0088" />');
				return;
			} else {
				$("#dialog").text('<spring:message code="LAB0089" />').dialog({
				      resizable: false,
				      height:140,
				      modal: true,
				      buttons: {
				        '<spring:message code="LAB0090" />': function() {
				        	
				        	$('#menuCatId').val(categoryId);
							$("#delDataForm").submit();
				        
				        },
				        '<spring:message code="LAB0091" />' : function() {
				          $(this).dialog('close');
				        }
				      }
				 });
			}
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
						<h2 class="campaign_ttl"><spring:message code="LAB0082" /></h2>
						<br />
						<p align="center">
							<font color="red" size="3">
							${msg}
							</font>
						</p>
						<br />

							<div align="center">
								<button type="button" onclick="addCategory();"><spring:message code="LAB1025" /></button>
							</div>
							<br/>
							<div class="shopDataForm">
								<table>
									<tr>
										<th class="orangeBg"><spring:message code="LAB0092" /></th>
										<th class="orangeBg"><spring:message code="LAB0093" /></th>
										<th class="orangeBg"><spring:message code="LAB0094" /></th>
										<th class="orangeBg"><spring:message code="LAB0095" /></th>
										<th class="orangeBg"><spring:message code="LAB0096" /></th>
										<th class="orangeBg"><spring:message code="LAB0097" /></th>
									</tr>
									
									<c:forEach items="${menuCategoryList}" var="menuCategory" varStatus="s">
										<tr id="row_itemId_1401171148326500">
											<td class="embg">${menuCategory.menuCatName}</td>
											<td class="embg">${menuCategory.sort}</td>
											<td class="embg">
											<c:if test="${menuCategory.hideFlag eq 'Y'}">
											<spring:message code="LAB0068" />
											</c:if>
											<c:if test="${menuCategory.hideFlag eq 'N'}">
											<spring:message code="LAB0069" />
											</c:if>
											</td>
											<td class="embg">${menuCategory.productCount}</td>
											<td class="embg"><a href="javascript:void(0)" onclick="editCategory('${menuCategory.menuCatId}');" class="button"><spring:message code="LAB0098" /></a></td>
											<td class="embg"><a href="javascript:void(0)" onclick="deleteCategory('${menuCategory.menuCatId}','${menuCategory.productCount}');" class="button"><spring:message code="LAB0099" /></a></td>
										</tr>
									</c:forEach>
										
									
								</table>
							</div>
							
							<form name="dataForm" id="dataForm" action="menucategory/shop/editcategory" method="post">
								<input type="hidden" id="categoryId" name="categoryId">
							</form>
							
							<form name="delDataForm" id="delDataForm" action="menucategory/shop/deletecategory" method="post">
								<input type="hidden" id="menuCatId" name="menuCatId">
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