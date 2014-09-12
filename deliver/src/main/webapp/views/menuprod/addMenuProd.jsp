<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB0107" /></title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
	<style>
		
	</style>
	
	
	
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
	
	<script type="text/javascript">
	
		$(function(){	
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				//alert(msg);
			}
			
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

		/* $(function() {
			$("#delProdPath").click(function() {
				delProdPath();
			});
		});

		function delProdPath() {
			if (confirm('<spring:message code="LAB0120" />')) {
				$.ajax({
					type : "POST",
					url : "/singleShop/delProdPath.action?prodId=" + $('#prodId').val(),
					data : {},
					dataType : "json",
					error : function() {

					},
					success : function(data) {
						if (data.success) {
							alert(data.msg);
							$("#uploPath").empty();
						} else {
							alert(data.msg);
						}
					}
				});
			}
		} */
		
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
                   
                   
						<h2 class="campaign_ttl"><spring:message code="LAB0107" /></h2>
						<br />
						<%-- <p align="center">
							<font color="red" size="3">
							${msg}
							</font>
						</p>
						<br /> --%>
						
						<form name="form" id="form" action="menuprod/shop/createProd" enctype="multipart/form-data" method="post">
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
										<td class="fillembg"><input type="text" name="prodName" size="20" maxlength="100" id="prodName" class="textOrangeFom"></input>
											<!-- <input name="prodId" type="hidden" id="prodId"></input> -->
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
										 <!-- <input name="prodPath" type="hidden" id="form_prodPath"></input> -->
											<!-- <div id="uploPath"></div>-->
											<spring:message code="LAB0124" /> <br /> <spring:message code="LAB0125" /><input type="file" id="prodPath" name="prodPath"
											name="image" size="50" onchange="chkfile(this);" /></td>
									</tr>
					
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0126" /></td>
										<td class="fillembg"><input type="text" name="prodPrice" size="5" maxlength="10" id="prodPrice"></input></td>
									</tr>
					
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0127" /></td>
										<td class="fillembg"><input type="text" name="onsalePrice" size="5" maxlength="10" id="onsalePrice"></input>
											<button type="button" onclick="samePrice();"><spring:message code="LAB0128" /></button><spring:message code="LAB0129" />
										</td>
									</tr>
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0130" /></td>
										<td class="fillembg"><input type="text" name="sort" size="3" maxlength="3" id="sort" value="0"></input></td>
									</tr>
					
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0131" /></td>
										<td class="fillembg"> 
											<input type="radio" id="topProd" name="topProd" value="Y"><spring:message code="LAB0068" />
											<input type="radio" id="topProd" name="topProd" value="N" checked><spring:message code="LAB0069" />
										</td>
									</tr>
					
									<tr>
										<td class="embg">&nbsp;&nbsp;<spring:message code="LAB0132" /></td>
										<td class="fillembg"><textarea rows="3" cols="80"
												name="prodDesc" id="prodDesc"  maxlength="100"></textarea></td>
									</tr>
					
									<tr>
										<td class="embg">&nbsp;&nbsp;<spring:message code="LAB0133" /></td>
										<td class="fillembg"><textarea rows="5" cols="80" maxlength="500"
												name="prodDescDetl" id="prodDescDetl"></textarea></td>
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
											<input type="radio" id="deleteFlag" name="deleteFlag" value="Y"><spring:message code="LAB0068" />
											<input type="radio" id="deleteFlag" name="deleteFlag" value="N" checked><spring:message code="LAB0069" />
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