<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB0085" /></title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
	<style>
		
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
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.maxlength.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/validate/jquery.validate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/validate/messages_zh_TW.js"/>"></script>
	
	<script type="text/javascript">
	
		$(function(){	
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				//alert(msg);
			}
			
			event: 'blur', $('#form').validate({
				rules : {
					categoryName : {
						required : true
					},
					sort : {
						required : true,
						digits : true
					},
					hideFlag : {
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
			
			$("form").submit();
		}
		
		
		function cancel(){
			location.href='<c:url value="/menucategory/shop/category" />';
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
                   
						<h2 class="campaign_ttl"><spring:message code="LAB0085" /></h2>
						<br />
						
						<form name="form" id="form" action="menucategory/shop/createcategory" method="post">
							<div class="shopDataForm">
								<table id="tb" border="0" cellspacing="0" cellpadding="0"
									width="100%">
									<tr>
										<th></th>
										<td align="left"><span id="msg" style="color: #F00"> 
										</span></td>
									</tr>
					
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0100" /></td>
										<td class="fillembg"><input type="text" name="categoryName" size="20" id="categoryName" class="textOrangeFom"></input>
										</td>
									</tr>
									<tr>
										<td class="embg"><label></label>&nbsp;<spring:message code="LAB0101" /></td>
										<td class="fillembg">
										<textarea name="categoryNote" id="categoryNote"  rows="5" cols="50"></textarea>
										</td>
									</tr>
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0102" /></td>
										<td class="fillembg"><input type="text" name="sort" maxlength="2" id="form_sort" value="0"></input>
										</td>
									</tr>
									<tr>
										<td class="embg"><label>*</label>&nbsp;<spring:message code="LAB0103" /></td>
										<td class="fillembg"> 
												<input type="radio" id="hideFlag" name="hideFlag" value="Y" /><spring:message code="LAB0068" />
												<input type="radio" id="hideFlag" name="hideFlag" value="N" checked="checked"/><spring:message code="LAB0069" />
										</td>
									</tr>
								</table>
							</div>
					
						</form>
					
						<br />
						
						<center>
						<button onclick="submit();" ><spring:message code="LAB1030" /></button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button onclick="cancel();" type="button"><spring:message code="LAB0091" /></button>
						</center>
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