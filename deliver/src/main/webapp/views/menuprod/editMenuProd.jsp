<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/metaNoBase.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB0108" /></title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/menuCategory.css"/>" rel="stylesheet"/>
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/niceforms-default.css"/>" rel="stylesheet" />
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/button.css"/>" rel="stylesheet"/>
	
	<%-- <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/js/jquery/validate/css/screen.css"/>" /> --%>
	<link type="text/css" href="<c:url value="/resources/js/jquery/css/ui-lightness/jquery-ui-1.10.2.custom.min.css"/>" rel="stylesheet"/>
	<%-- <script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.9.1.js"/>"></script> --%>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.10.2.custom.min.js"/>"></script>
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/validate/jquery.validate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.maxlength.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/validate/messages_zh_TW.js"/>"></script> 
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/fancyzoom/css/jquery.lightbox-0.5.css"/>" media="screen" />
	<script type="text/javascript" src="<c:url value="/resources/js/fancyzoom/js/jquery.lightbox-0.5.js"/>"></script>
	
	<style type="text/css">
			.deleteBtn{
				width:61px;
				height:27px;
				display:block;
				text-indent:-9999px;
				background-image:url(../../resources/cbg-include/images/deleteBtn.png);
				background-repeat:no-repeat;
				border:none 0;
				cursor:pointer;
				margin-bottom:5px;
			}
	</style>
	<script type="text/javascript">
	
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
		
		$(function(){
			var actionMsg = jQuery( "#actionMsg" ).val();
			if( actionMsg != null && actionMsg != "" ){
				alert( actionMsg );
			}
		});
	
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
	
		$(function(){	
			
			$('a.lightbox').lightBox();
			
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				//alert(msg);
			}
			
			//載入類別的值
			setOptionSelected("categoryId",'<c:out value="${menuProduct.menuCatId}" />');
			
			//載入商品選項的值
			setChkBoxChkedByVal("prodOptionId",'<c:out value="${menuItmeId}" />');
			
			event: 'blur', $("form").validate({
				rules : {
					"prodName" : "required",
					"categoryId" : "required",
					"prodPrice" : {
						required : true,
						digits : true
					},
					"onsalePrice" : {
						required : true,
						digits : true
					},
					sort:{
						required : true,
						digits : true
					},
					topProd:{
						required : true
					},
					deleteFlag:{
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
			
			
			$("#delProdPath").click(function() {
				delProdPath();
			});
			
			
		});
		
		function submit() {
			
			var filePath = $('#prodPath').val();
			if(filePath != ''){
				 var pot=filePath.lastIndexOf("."); 
				 if(pot!=-1){  
			          ext=filePath.substring(pot);  
			          reg = /.(jpg|JPG|png|PNG|gif|GIF)$/;
			          if (!ext.match(reg)){
			                alert('<spring:message code="LAB1004"/>');
			                return false;
			          }
			      }else{
			    	  alert('<spring:message code="LAB1004"/>');
			    	  return false;
			      }  
			}
			
			if ($('#prodDesc').val().length > 100) {
				alert('<spring:message code="LAB0118" />');
				return;
			}
			if ($('#prodDescDetl').val().length > 500) {
				alert('<spring:message code="LAB0119" />');
				return;
			}

			$("form").submit();
		}

		function samePrice() {
			$('#onsalePrice').val($('#prodPrice').val());
		}


		function delProdPath() {
			$("#dialog").text('<spring:message code="LAB0120" />').dialog({
			      resizable: false,
			      height:140,
			      modal: true,
			      buttons: {
			        '<spring:message code="LAB0090" />': function() {
			        	
			        	 $.ajax({
					   			type: "POST",
					   			url: '<c:url value="/menuprod/shop/deleteprodpic"/>' ,
					   			data: {"prodId":$('#prodId').val(), "picName":$('#picName').val()},
					   			dataType: 'json',
					   			error: function(xhr, textStatus, errorThrown) {
										alert('An error occurred! ' + ( errorThrown ? errorThrown : xhr.status ));
					   			},
					   			success: function(data, textStatus){
					   				if(textStatus=='success'){
					   					
					   					if (data.success) {
											alert(data.msg);
											$("#uploPath").empty();
											$('#picName').val('');
										} else {
											alert(data.msg);
										}
										
					   				} else {
					   					alert('<spring:message code="delError"/>');
					   				}
					   			},
					   			complete: function(data){
					   				//alert('complete');
					   			}
					   	}); 
			        	
			        },
			        '<spring:message code="LAB0091" />' : function() {
			          $(this).dialog('close');
			        }
			      }
			 });
		}
		
		function chkfile(o){
            reg = /.(jpg|JPG|png|PNG|gif|GIF)$/;
            if (!o.value.match(reg)){
                alert('<spring:message code="LAB1004"/>');
                o.focus();
                //location.href='<c:url value="/menuprod/shop/addProd" />';
                return false;
            }
        }
		
		function cancel(){
			var menuCatId = '<c:out value="${menuCatId}" />';
			location.href='<c:url value="/menuprod/shop/prod?menuCatId='+menuCatId+'" />';
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
                   
                   		<div id="dialog"></div>
						<h2 class="campaign_ttl"><spring:message code="LAB0108" /></h2>
						<br />
						<%-- <p align="center">
							<font color="red" size="3">
							${msg}
							</font>
						</p>
						<br /> --%>
						
						<form name="form" id="form" action="<c:url value="/menuprod/shop/updateprod"/>" enctype="multipart/form-data" method="post">
							<div class="shopDataForm" style="width: 530px;">
								<table id="tb" border="0" cellspacing="0" cellpadding="0"
									width="100%">
									<tr>
										<th></th>
										<td align="left"><span id="msg" style="color: #F00"> 
										</span></td>
									</tr>
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0121" /></td>
										<td class="fillembg"><input type="text" name="prodName" size="20" maxlength="100" id="prodName" class="textOrangeFom" value="${menuProduct.menuProdName}"></input>
										</td>
									</tr>
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0122" /></td>
										<td class="fillembg"><select name="categoryId" id="categoryId" size="1">
												<c:forEach items="${menuCategoryList}" var="menuCategory" varStatus="s">
													<option value="${menuCategory.menuCatId}">${menuCategory.menuCatName}</option>
												</c:forEach>
										</select></td>
									</tr>
					
									<tr>
										<td class="embg">&nbsp;&nbsp;<spring:message code="LAB0123" /></td>
										<td class="fillembg">
											<input name="prodPath" type="hidden" value="111213183601129" id="form_prodPath"></input>
											<c:if test="${not empty menuProduct.picName}">
												<div id="uploPath">
														<a class="lightbox" href="${menuProduct.getPicturePath800(serverName,domainPicPath,directory)}" title="${menuProduct.menuProdName}<br/>${menuProduct.menuProdComm}"><img src="${menuProduct.getPicturePath(serverName,domainPicPath,directory)}" /></a>
														<button type="button" id="delProdPath" class="deleteBtn"></button>
												</div>
											</c:if>
											<input type="hidden" name="prodId" id="prodId" value="${menuProduct.menuProdId}"></input>
											<input type="hidden" name="picName" id="picName" value="${menuProduct.picName}"></input>
											<spring:message code="LAB0124" /> <br/> <spring:message code="LAB0125" />
											<input type="file" id="prodPath" name="prodPath" name="image" size="50" onchange="chkfile(this);" /></td>
									</tr>
					
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0126" /></td>
										<td class="fillembg"><input type="text" name="prodPrice" size="5" maxlength="10" id="prodPrice" value="${menuProduct.menuPrice}"></input></td>
									</tr>
					
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0127" /></td>
										<td class="fillembg"><input type="text" name="onsalePrice" size="5" maxlength="10" id="onsalePrice" value="${menuProduct.onsalePrice}"></input>
											<button type="button" onclick="samePrice();"><spring:message code="LAB0128" /></button><spring:message code="LAB0129" />
										</td>
									</tr>
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0130" /></td>
										<td class="fillembg"><input type="text" name="sort" size="3" maxlength="3" id="sort" value="${menuProduct.sort}"></input></td>
									</tr>
					
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0131" /></td>
										<td class="fillembg">
											<c:if test="${menuProduct.hotProduct eq 'Y'}">
												<input type="radio" id="topProd" name="topProd" value="Y" checked><spring:message code="LAB0068" />
												<input type="radio" id="topProd" name="topProd" value="N"><spring:message code="LAB0069" />
											</c:if>
											<c:if test="${menuProduct.hotProduct eq 'N'}">
												<input type="radio" id="topProd" name="topProd" value="Y"><spring:message code="LAB0068" />
												<input type="radio" id="topProd" name="topProd" value="N" checked><spring:message code="LAB0069" />
											</c:if>
										</td>
									</tr>
					
									<tr>
										<td class="embg">&nbsp;&nbsp;<spring:message code="LAB0132" /></td>
										<td class="fillembg">
										<textarea rows="3" cols="80" name="prodDesc" id="prodDesc"  maxlength="100">${menuProduct.menuProdComm}</textarea></td>
									</tr>
					
									<tr>
										<td class="embg">&nbsp;&nbsp;<spring:message code="LAB0133" /></td>
										<td class="fillembg">
										<textarea rows="5" cols="80" maxlength="500" name="prodDescDetl" id="prodDescDetl">${menuProduct.menuProdCommDetail}</textarea></td>
									</tr>
									<tr>
										<td class="embg">&nbsp;&nbsp;<spring:message code="LAB0134" /></td>
										<td class="fillembg">
											<c:forEach items="${menuProductItemList}" var="menuProductItem" varStatus="s">
												<input type="checkbox" name="prodOptionId" value="${menuProductItem.menuItmeId}">${menuProductItem.menuItmeName}
											</c:forEach>
										</td>
									</tr>
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0135" /></td>
										<td class="fillembg"> 
											<c:if test="${menuProduct.hideFlag eq 'Y'}">
												<input type="radio" id="deleteFlag" name="deleteFlag" value="Y" checked><spring:message code="LAB0068" />
												<input type="radio" id="deleteFlag" name="deleteFlag" value="N"><spring:message code="LAB0069" />
											</c:if>
											<c:if test="${menuProduct.hideFlag eq 'N'}">
												<input type="radio" id="deleteFlag" name="deleteFlag" value="Y"><spring:message code="LAB0068" />
												<input type="radio" id="deleteFlag" name="deleteFlag" value="N" checked><spring:message code="LAB0069" />
											</c:if>
										</td>
									</tr>
								</table>
							</div>
					
							<input name="menuId" type="hidden" id="form_menuId"></input>
						</form>
					
						<br />
					
						<table align="center">
							<tr align="center">
								<td>
									<div class="saveCancelBtn">
										<ul>
											<li>
												<button onclick="submit();"><spring:message code="LAB1030" /></button>
											</li>
											<li>
												<button onclick="cancel();"><spring:message code="LAB0091" /></button>
											</li>
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