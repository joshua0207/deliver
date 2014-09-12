<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB0105" /></title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
	<style>
		table#list {
			border-spacing: 0px;
			width: 100%;
		}
		
		table#list td {
			padding: 0px;
		}
		
		#error {
			color: #D8000C;
			background-color: #FFBABA;
			display: none;
		}
		
		#item {
			display: none;
		}
		
		.itemValueDiv {
			margin-top: 7px;
		}
	</style>
	
	<%-- <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/js/jquery/validate/css/screen.css"/>" /> --%>
	<%-- <script src="<c:url value="/resources/js/jquery/validate/jquery.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/js/jquery/validate/jquery.validate.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/js/jquery/validate/cmxforms.js"/>" type="text/javascript"></script>
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
	<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/addr.js"/>"></script> 
    <script type="text/javascript" src="<c:url value="/resources/js/googleMap/static.js"/>"></script> 
    <script type="text/javascript" src="<c:url value="/resources/js/googleMap/googleMapFunction.js"/>"></script> 
	<script src="<c:url value="/resources/cbg-include/Scripts/colors.js"/>" type="text/javascript" ></script>	 --%>
	
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/menuCategory.css"/>" rel="stylesheet"/>
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/niceforms-default.css"/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/button.css"/>" rel="stylesheet"/>
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/validate/jquery.validate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.maxlength.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/validate/messages_zh_TW.js"/>"></script> 
	
	<script type="text/javascript">
	
		$(function(){	
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				//alert(msg);
			}
			
			//載入單/複選
			setChkBoxChkedByVal("prodOptionSingle",'<c:out value="${menuProductItem.menuChoice}" />');
			
			//$("#radio").buttonset();

			 $('#addItemBtn').click(function() {
				$("#item").children("div").clone().appendTo("#addItem");
			}); 
			
			
			event: 'blur', $('#dataForm').validate({
				rules : {
					prodOptionName : {
						required : true
					},
					prodOptionSingle : {
						required : true
					}
				},
				
				errorPlacement: function(error, element) { 
				    if ( element.is(":radio") ) 
				        error.appendTo ( element.parent() ); 
				    else if ( element.is(":checkbox") ) 
				        error.appendTo ( element.parent() ); 
				    else if ( element.is("input[name=captcha]") ) 
				        error.appendTo ( element.parent() ); 
				    else 
				        error.insertAfter(element); 
				} 
			
			});
		});
		
		
		function delItem(obj) {
			$(obj).parent().parent().parent().parent().remove();
		}

		function save() {
			var prodOptionItemNameVar = document.dataForm.prodOptionItemName;
			if (typeof prodOptionItemNameVar != 'undefined') {
				if (typeof prodOptionItemNameVar.value != 'undefined') {

					if (prodOptionItemNameVar.length > 0) {
						for (var i = 0; i < prodOptionItemNameVar.length; i++) {
							if (prodOptionItemNameVar[i].value == "") {
								alert('<spring:message code="LAB0142" />' + (i + 1) + '<spring:message code="LAB0143" />');
								return;
							}
						}
					} else {
						if ((prodOptionItemNameVar.value) == "") {
							alert('<spring:message code="LAB0144" />');
							return;
						}
					}

				} else {
					for (var i = 0; i < prodOptionItemNameVar.length; i++) {
						if (prodOptionItemNameVar[i].value == "") {
							alert('<spring:message code="LAB0142" />' + (i + 1) + '<spring:message code="LAB0143" />');
							return;
						}
					}
				}
			} else {
				alert('<spring:message code="LAB0144" />');
				return;
			}

			var priceVar = document.dataForm.price;
			if (typeof priceVar != 'undefined') {
				if (typeof priceVar.value != 'undefined') {

					if (priceVar.length > 0) {
						for (var j = 0; j < priceVar.length; j++) {
							if (priceVar[j].value == "") {
								alert('<spring:message code="LAB0142" />' + (j + 1) + '<spring:message code="LAB0145" />');
								return;
							}
							if (!checkThisNumber(priceVar[j].value)) {
								alert('<spring:message code="LAB0142" />' + (j + 1) + '<spring:message code="LAB0146" />');
								return;
							}
						}
					} else {
						if ((priceVar.value) == "") {
							alert('<spring:message code="LAB0147" />');
							return;
						}
						if (!checkThisNumber(priceVar.value)) {
							alert('<spring:message code="LAB0148" />');
							return;
						}
					}
				} else {
					for (var j = 0; j < priceVar.length; j++) {
						if (priceVar[j].value == "") {
							alert('<spring:message code="LAB0142" />' + (j + 1) + '<spring:message code="LAB0145" />');
							return;
						}
						if (!checkThisNumber(priceVar[j].value)) {
							alert('<spring:message code="LAB0142" />' + (j + 1) + '<spring:message code="LAB0146" />');
							return;
						}
					}
				}
			} else {
				alert('<spring:message code="LAB0147" />');
				return;
			}

			var sortVar = document.dataForm.sort;
			if (typeof sortVar != 'undefined') {
				if (typeof sortVar.value != 'undefined') {

					if (sortVar.length > 0) {
						for (var j = 0; j < sortVar.length; j++) {
							if (sortVar[j].value == "") {
								alert('<spring:message code="LAB0142" />' + (j + 1) + '<spring:message code="LAB0149" />');
								return;
							}
							if (!checkThisNumber(sortVar[j].value)) {
								alert('<spring:message code="LAB0142" />' + (j + 1) + '<spring:message code="LAB0150" />');
								return;
							}
						}
					} else {
						if ((sortVar.value) == "") {
							alert('<spring:message code="LAB0151" />');
							return;
						}
						if (!checkThisNumber(sortVar.value)) {
							alert('<spring:message code="LAB0152" />');
							return;
						}
					}

				} else {
					for (var j = 0; j < sortVar.length; j++) {
						if (sortVar[j].value == "") {
							alert('<spring:message code="LAB0142" />' + (j + 1) + '<spring:message code="LAB0149" />');
							return;
						}
						if (!checkThisNumber(sortVar[j].value)) {
							alert('<spring:message code="LAB0142" />' + (j + 1) + '<spring:message code="LAB0150" />');
							return;
						}
					}
				}
			} else {
				alert('<spring:message code="LAB0151" />');
				return;
			}

			var isDefaultStrVar = document.dataForm.isDefaultStr;
			var singleObj = $("input[name='prodOptionSingle']:checked").val();

			var isDefault = "";
			if (typeof isDefaultStrVar != 'undefined') {
				if (typeof isDefaultStrVar.value != 'undefined') {

					if (sortVar.length > 0) {
						var cehckIsDefaultStr = 0;
						for (var j = 0; j < isDefaultStrVar.length; j++) {

							if (isDefaultStrVar[j].checked == true) {
								cehckIsDefaultStr = cehckIsDefaultStr + 1;
								isDefault = isDefault + "Y,";
							} else {
								isDefault = isDefault + "N,";
							}

						}

						if (singleObj == "1") {
							/* if (cehckIsDefaultStr > 1) {
								alert("此商品選項為[單選]，故預設值只可以選一個!");
								return;
							} */
						} else if (singleObj == "2") {

						}
					} else {
						if (isDefaultStrVar.checked == true) {
							cehckIsDefaultStr = cehckIsDefaultStr + 1;
							isDefault = isDefault + "Y,";
						} else {
							isDefault = isDefault + "N,";
						}
					}

				} else {
					var cehckIsDefaultStr = 0;
					for (var j = 0; j < isDefaultStrVar.length; j++) {

						if (isDefaultStrVar[j].checked == true) {
							cehckIsDefaultStr = cehckIsDefaultStr + 1;
							isDefault = isDefault + "Y,";
						} else {
							isDefault = isDefault + "N,";
						}

					}

					if (singleObj == "1") {
						/* if (cehckIsDefaultStr > 1) {
							alert("此商品選項為[單選]，故預設值只可以選一個!");
							return;
						} */
					} else if (singleObj == "2") {

					}
				}
			} else {
			}
			
			$('#isDefault').val(isDefault);
			$("form").submit();
		}

		function cancel() {
			location.href = '<c:url value="/menuprodoption/shop/prodoption" />';
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
                   
                   
						<h2 class="campaign_ttl"><spring:message code="LAB0105" /></h2>
						<br />
						<%-- <p align="center">
							<font color="red" size="3">
							${msg}
							</font>
						</p>
						<br /> --%>

						<form name="dataForm" id="dataForm" action="menuprodoption/shop/updateprodoption" method="post">

							<div class="shopDataForm" style="width:100%;">
								<table id="tb" border="0" cellspacing="1" cellpadding="1"
									width="100%">
									<tr>
										<th></th>
										<td align="left"><span id="msg" style="color: #F00"> 
										</span></td>
									</tr>
					
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0153" /></td>
										<td class="fillembg"><input type="text" name="prodOptionName" id="prodOptionName" size="20" maxlength="30" id="dataForm_prodOption_name" class="textOrangeFom" value="${menuProductItem.menuItmeName}"></input>
										<!-- <input name="prodOption.prodOptionId" type="hidden" id="dataForm_prodOption_prodOptionId"></input> 
										<input type="hidden" id="prodOptionId" name="prodOptionId" value=""></td> -->
									</tr>
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0154" /></td>
										<td class="fillembg"> 
												<input type="radio" id="prodOptionSingle" name="prodOptionSingle" value="1" checked="checked"><spring:message code="LAB0140" />
												<input type="radio" id="prodOptionSingle" name="prodOptionSingle" value="2"><spring:message code="LAB0141" />
										</td>
									</tr>
									<!-- <tr>
										<td class="embg">&nbsp;POS 機選項代碼:</td>
										<td class="fillembg"><input type="text" name="prodOption.mappPosId" size="20" maxlength="30" id="dataForm_prodOption_mappPosId" class="textOrangeFom"></input></td>
									</tr> -->
								</table>
							</div>
							<input type="hidden" id="menuItmeId" name="menuItmeId" value="${menuProductItem.menuItmeId}"/>
					
					
							<div class="shopDataForm" style="width:100%;">
								<table id="list">
									<tr>
										<th class="orangeBg" style="text-align: center;">
											<div style="margin: 4px;">
												<a href="javascript:void(0);" class="button" id="addItemBtn" ><spring:message code="LAB0155" /></a>
											</div>
										</th>
									</tr>
									<tr>
									
								<th class="fillembg" id="addItem">
										<c:forEach items="${menuProductOptionValueList}" var="menuProductOptionValue" varStatus="s">
												<div id="item2">
													<table border="0" cellspacing="2" cellpadding="2" width="100%">
														<tr>
															<td><spring:message code="LAB0156" /> <input type="text" name="prodOptionItemName" id="prodOptionItemName" size="20" maxlength="30" value="${menuProductOptionValue.menuItmeValue}"></td>
															<td><spring:message code="LAB0157" />
															<select name="prefix" id="prefix">
																<c:choose>
																	<c:when test="${menuProductOptionValue.menuProdPrefeix eq '+'}">
																		<option value="+" selected="selected">+</option>
																		<option value="-">-</option>
																	</c:when>
																	<c:when test="${menuProductOptionValue.menuProdPrefeix eq '-'}">
																		<option value="+">+</option>
																		<option value="-" selected="selected">-</option>
																	</c:when>
																</c:choose>
															</select> 
															<input type="text" name="price" size="5" maxlength="5" value="${menuProductOptionValue.menuProdPrice}" id="price" style="width:30px"></input>
															</td>
															<td><spring:message code="LAB0158" /> <input type="text" name="sort" size="2" maxlength="2" id="sort" style="width:50px" value="${menuProductOptionValue.sort}"></input></td>
															<td><spring:message code="LAB0135" />
															<c:choose>
																	<c:when test="${menuProductOptionValue.hideFlag eq 'Y'}">
																		<input type="checkbox" id="isDefaultStr" name="isDefaultStr" value="Y" checked="checked">
																	</c:when>
																	<c:when test="${menuProductOptionValue.hideFlag eq 'N'}">
																		<input type="checkbox" id="isDefaultStr" name="isDefaultStr" value="Y">
																	</c:when>
															</c:choose>
															</td>
															<td>
																<input type="hidden" id="menuOptionId" name="menuOptionId" value="${menuProductOptionValue.menuOptionId}">
															</td>
															<td><a href="javascript:void(0)" onclick="delItem(this);" class="button"><spring:message code="LAB0159" /></a></td>
														</tr>
													</table>
												</div>
										</c:forEach>
									</th>
										
									</tr>
								</table>
							</div>
							<input type="hidden" id="isDefault" name="isDefault" value="">
						</form>
						
						<div id="item">
							<div id="item2">
								<table border="0" cellspacing="2" cellpadding="2" width="100%">
									<tr>
										<td><spring:message code="LAB0156" /> <input type="text" name="prodOptionItemName" id="prodOptionItemName" size="20" maxlength="30" value=""></td>
										<td><spring:message code="LAB0157" /> <select name="prefix" id="prefix"><option value="+">+</option><option value="-">-</option></select> <input type="text" name="price" size="5" maxlength="5" value="0" id="price" style="width:30px"></input></td>
										<td><spring:message code="LAB0158" /> <input type="text" name="sort" size="2" maxlength="2" value="0" id="sort" style="width:50px"></input></td>
										<!-- <td>POS 機細項代碼： <input type="text" name="prodOptionItemPosId" size="10" maxlength="30" id="prodOptionItemPosId" style="width:50px"></input></td> -->
										<td><spring:message code="LAB0135" /> <input type="checkbox" id="isDefaultStr" name="isDefaultStr" value="Y">
										</td>
										<td><a href="javascript:void(0)" onclick="delItem(this);" class="button"><spring:message code="LAB0159" /></a></td>
									</tr>
								</table>
							</div>
						</div>
						<br />
						<table align="center">
							<tr align="center">
								<td>
									<div class="saveCancelBtn">
										<ul>
											<li><button type="button" onclick="save();"><spring:message code="LAB0160" /></button></li>
											<li><button type="button" onclick="cancel();"><spring:message code="LAB0091" /></button></li>
										</ul>
									</div>
								</td>
							</tr>
						</table>
		
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