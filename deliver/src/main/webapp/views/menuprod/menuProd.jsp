<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/metaNoBase.jsp"%>
<%@ page import="com.order.mode.vo.MenuProductVO"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB0084" /></title>

		<!-- header's css start-->
		<%@ include file="/views/cbg-include/header-css.html" %>
		<!-- header's css end-->
		
	<style type="text/css">
		
		
	</style>

	<link type="text/css" href="<c:url value="/resources/js/jquery/css/ui-lightness/jquery-ui-1.10.2.custom.min.css"/>" rel="stylesheet"/>
	<%-- <script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.9.1.js"/>"></script> --%>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.10.2.custom.min.js"/>"></script>
	
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/menuCategory.css"/>" rel="stylesheet"/>
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/niceforms-default.css"/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/button.css"/>" rel="stylesheet"/>
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/fancyzoom/css/jquery.lightbox-0.5.css"/>" media="screen" />
	<script type="text/javascript" src="<c:url value="/resources/js/fancyzoom/js/jquery.lightbox-0.5.js"/>"></script>
	
	<%-- <script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.maxlength.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/validate/jquery.validate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/validate/messages_zh_TW.js"/>"></script> --%>

	<%-- <script type="text/javascript" src="<c:url value="/resources/js/menuJs/jquery.ui.core.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/menuJs/jquery.ui.widget.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/menuJs/jquery.ui.tabs.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/menuJs/jquery.ui.mouse.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/menuJs/jquery.ui.sortable.js"/>"></script> --%>
	
	<script type="text/javascript">
	
		$(function(){
			
			$('a.lightbox').lightBox();
			
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				//alert(msg);
			}
			
			$("#tabs").tabs({
				event:"click",
				collapsible : true
			});

			$("input[name='prodOfMenu']:not(:checked)").each(function(i, obj) {
				var pId = obj.id.split("[")[1].split("]")[0];
				$('#price\\[' + pId + '\\]').prop("disabled", "disabled");
				$('#onsalePrice\\[' + pId + '\\]').prop("disabled", "disabled");
			});

			$("input[name='prodOfMenu']").change(function() {
				if ($(this).prop("checked")) {
					console.log("1");
					var pId = $(this).prop("id").split("[")[1].split("]")[0];
					console.log("pId: " + pId);
					console.log("price[pId]: " + '#price\\[' + pId + '\\]');
					console.log("onsalePrice[pId]: " + '#onsalePrice\\[' + pId + '\\]');
					$('#price\\[' + pId + '\\]').prop('disabled', false);
					$('#onsalePrice\\[' + pId + '\\]').prop('disabled', false);
				} else {
					console.log("2");
					var pId = $(this).prop("id").split("[")[1].split("]")[0];
					console.log("pId: " + pId);
					console.log("price[pId]: " + '#price\\[' + pId + '\\]');
					console.log("onsalePrice[pId]: " + '#onsalePrice\\[' + pId + '\\]');
					$('#price\\[' + pId + '\\]').prop("disabled", true);
					$('#onsalePrice\\[' + pId + '\\]').prop("disabled", true);
				}
			});
		});
		
		window.alert = function(msg) {
			$('#dialog').text(msg).dialog({
				closeText: "hide",
				closeOnEscape:true,
				modal:true,		
				buttons : {
					'OK' :function(){
						$(this).dialog('close');
					}
				}
			});
		};
		$(function(){
			var actionMsg = jQuery( "#actionMsg" ).val();
			if( actionMsg != null && actionMsg != "" ){
				alert( actionMsg );
			}
		});
		
		function addProd(){
			
			location.href='<c:url value="/menuprod/shop/addProd?menuCatId='+menuCatId+'" />';
		}

		function edit(str) {
			
			dataForm.menuCatId.value = menuCatId;
			dataForm.prodId.value = str;
			$("#dataForm").submit();
		}

		function listProdSubmit() {
			var result;
			result = getMenuCheckBoxAndPrice();
			jQuery.getJSON("modListProd.action", {
				modMenuProdList : result,
				menuId : ""
			}).success(function(response) {
				if (response.info.success == true) {
					alert('<spring:message code="LAB1017" />');
				} else {
					alert('<spring:message code="LAB1018" />');
				}
			}).error(function(response) {
				alert('<spring:message code="LAB1018" />');
			});
		}

		// prodId1,price1,onsalePrice1:prodId2,price2,onsalePrice2 ...
		function getMenuCheckBoxAndPrice() {
			var tmpResult = "";
			var tmpProdId = "";
			var tmpPrice = "";
			var tmpOnsalePrice = "";
			jQuery.each($('input[name="prodOfMenu"][type=checkbox]:checked'), function(index, obj) {
				tmpName = obj.name;
				if (index > 0)
					tmpResult += ":";
				tmpProdId = obj.value;
				tmpPrice = $('input[id="price\\[' + obj.value + '\\]"][type=text]').val();
				tmpOnsalePrice = $('input[id="onsalePrice\\[' + obj.value + '\\]"][type=text]').val();

				//tmpName = tmpName.substring( tmpName.indexOf("[")+1, tmpName.indexOf("]") )
				tmpResult += tmpProdId + "," + tmpPrice + "," + tmpOnsalePrice;
			});
			return tmpResult;
		}

		function checkDelete(prodId, picName, orderCount, menuCatId) {
			
			if(orderCount >0){
				alert('<spring:message code="LAB0109" />');
				return;
			}else{
				$("#dialog").text('<spring:message code="LAB1005" />').dialog({
				      resizable: false,
				      height:140,
				      modal: true,
				      buttons: {
				        '<spring:message code="LAB0090" />': function() {
				        	
				        	delForm.prodId.value = prodId;
							delForm.picName.value = picName;
							delForm.menuCatId.value = menuCatId;
							//alert("delForm.prodId.value:" + delForm.prodId.value);
							$("#delForm").submit();
				        	
				        },
				        '<spring:message code="LAB0091" />' : function() {
				          $(this).dialog('close');
				        }
				      }
				 });
			}
		
		}
		
		var menuCatId;
		function getMenuCatId(catId){
			
			menuCatId = catId;
		} 
		
		
		window.location.hash = '<c:out value="${categoryId}" />';
	
	</script>

	</head>
	<body id="body" >
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
                   
                   
						<h2 class="campaign_ttl"><spring:message code="LAB0084" /></h2>
						<br />
						<p align="center">
							<font color="red" size="3">
							${msg}
							</font>
						</p>
						<br />

						<div id="dialog"></div>
						<div align="center">
							<button type="button" onclick="addProd();"><spring:message code="LAB1025" /></button>
						</div>
						
						<br>
						
						<div id="tabs">
							<ul>
								<c:forEach items="${menuCategoryList}" var="menuCategory">
								<li><a href="#${menuCategory.menuCatId}" onclick="getMenuCatId('${menuCategory.menuCatId}');">${menuCategory.menuCatName}</a></li>
								</c:forEach>
							</ul>
							
							<%boolean data = false; %>
							<c:forEach items="${menuCategoryList}" var="menuCategory">
								<div id="${menuCategory.menuCatId}">
								
									<c:forEach items="${menuProductMap}" var="productMap">
											<c:choose>
												
												<c:when test="${menuCategory.menuCatId eq productMap.key}">
														<!-- <div align="center">有資料</div> -->
														<div class="shopDataForm" style="width: 500px;">
														<table>
															<tr>
																<th width="3%"></th>
																<th width="6%" style="text-align: center;"><spring:message code="LAB0110" /></th>
																<th width="8%" style="text-align: center;"><spring:message code="LAB0111" /></th>
																<th width="3%" style="text-align: center;"><spring:message code="LAB0112" /></th>
																<th width="3%" style="text-align: center;"><spring:message code="LAB0113" /></th>
																<th width="3%" style="text-align: center;"><spring:message code="LAB0114" /></th>
																<th width="7%" style="text-align: center;"><spring:message code="LAB0115" /></th>
																<th width="10%" style="text-align: center;"><spring:message code="LAB0116" /></th>
															</tr>
																
															 <c:forEach items="${productMap.value}" var="productList" varStatus="s">
																<tr id="${productList.menuProdId}">
																<td class="embg">${s.index+1}</td>
																	<td class="embg">
																	<c:if test="${not empty productList.picName}">
																		<a class="lightbox" href="${productList.getPicturePath800(serverName,domainPicPath,directory)}" title="${productList.menuProdName}<br/>${productList.menuProdComm}"><img src="${productList.getPicturePath(serverName,domainPicPath,directory)}" width="40" height="40" /></a>
																	</c:if>
																	</td>
																	<td class="embg">${productList.menuProdName}</td>
																	<td class="embg">${productList.menuPrice}</td>
																	<td class="embg">${productList.onsalePrice}</td>
																	<td class="embg">${productList.sort}</td>
																	<td class="embg" style="text-align: center;">
																	<c:if test="${productList.hideFlag eq 'Y'}"><font color="red"><spring:message code="LAB0068" /></font></c:if>
																	<c:if test="${productList.hideFlag eq 'N'}"><spring:message code="LAB0069" /></c:if>
																	</td>
																	<td class="embg">
																	<center>
																	<a href="javascript:void(0);" onclick="edit('${productList.menuProdId}');" class="button"><spring:message code="LAB0098" /></a> 
																	<br/><br/>
																	<a href="javascript:void(0);" onclick="checkDelete('${productList.menuProdId}','${productList.picName}','${productList.orderCount}','${productList.menuCatId}');" class="button"><spring:message code="LAB0099" /></a>
																	</center>	
																	</td>
																</tr>
															  </c:forEach>
														</table>
													</div>
														
													<%data = true; %>
												</c:when>
												
											</c:choose>
									</c:forEach>
									
									<%if(!data){ %>
										<div align="center"><spring:message code="LAB0117" /></div>
									<%}%>
									<%data = false; %>
									
								</div>
							</c:forEach>
					
						</div>
						
						<form name="dataForm" id="dataForm" action="<c:url value="/menuprod/shop/editProd"/>" method="post">
							<input type="hidden" id="menuCatId" name="menuCatId">
							<input type="hidden" id="prodId" name="prodId">
						</form>
						<form name="delForm" id="delForm" action="<c:url value="/menuprod/shop/deleteprod"/>" method="post">
							<input type="hidden" id="prodId" name="prodId">
							<input type="hidden" id="picName" name="picName">
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