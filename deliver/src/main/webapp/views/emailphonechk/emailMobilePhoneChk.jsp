<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@ include file="/views/ssi/niceforms.jsp"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
		<script type="text/javascript">
		
			$(function(){	
				var msgAlert = '<c:out value="${msgAlert}" />';
				if(msgAlert != null && msgAlert != ''){
					alert(msgAlert);
				}
			});
		
			function setSmsAuth(){
				
				$('#sendSmsAuth').attr('disabled','disabled');
				
				$.ajax({
		   			type: "POST",
		   			url: 'emailmobileverify/shop/smsauthen',
		   			data: '',
		   			dataType: 'json',
		   			error: function(xhr, textStatus, errorThrown) {
							alert('An error occurred! ' + ( errorThrown ? errorThrown : xhr.status ));
		   			},
		   			success: function(data, textStatus){
		   				if(textStatus=='success'){
		   					 alert(data.msgAlert);
		   					 if(data.chkSms == 'Y'){
		   					 	$('#inputSmsAuth').slideDown();
		   				 	 	$('#sendSmsAuth').slideUp();
		   					 }else{
		   						$('#sendSmsAuth').attr('disabled',false);
		   					 }
		   				} else {
		   					alert('<spring:message code="MSG0038" />');
		   					$('#sendSmsAuth').attr('disabled',false);
		   					//alert(data['message']);
		   				}
		   			},
		   			complete: function(data){
		   				//alert('complete');
		   			}
		   		});
			}
			
			function chkSmsAuth(){
				
				$.ajax({
		   			type: "POST",
		   			url: 'emailmobileverify/shop/registersmsauthen',
		   			data:  $('#sentToSmsChk').serialize(),
		   			dataType: 'json',
		   			error: function(xhr, textStatus, errorThrown) {
							alert('An error occurred! ' + ( errorThrown ? errorThrown : xhr.status ));
		   			},
		   			success: function(data, textStatus){
		   				if(textStatus=='success'){
		   					 alert(data.msgAlert);
		   					 if(data.chkSms){
			   					 location.href='<c:url value="/emailmobileverify/shop/verifypage" />';
		   					 }
		   				} else {
		   					alert(data.msgAlert);
		   					//alert(data['message']);
		   				}
		   			},
		   			complete: function(data){
		   				//alert('complete');
		   			}
		   		});
				
			}
			
			
			function setEmailAuth(){
				
				$('#sendEmailAuth').attr('disabled','disabled');
				
				$.ajax({
		   			type: "POST",
		   			url: 'emailmobileverify/shop/emailauth',
		   			data: '',
		   			dataType: 'json',
		   			error: function(xhr, textStatus, errorThrown) {
							alert('An error occurred! ' + ( errorThrown ? errorThrown : xhr.status ));
		   			},
		   			success: function(data, textStatus){
		   				if(textStatus=='success'){
		   					 alert(data.msgAlert);
		   					 if(data.chkEmail){
		   						 location.href='<c:url value="/emailmobileverify/shop/verifypage" />';
		   					 }else{
			   					 $('#sendEmailAuth').attr('disabled',false);
		   					 }
		   				} else {
		   					alert(data.msgAlert);
		   					$('#sendEmailAuth').attr('disabled',false);
		   					//alert(data['message']);
		   				}
		   			},
		   			complete: function(data){
		   				//alert('complete');
		   			}
		   		});
				
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
                   
                        <h2 class="campaign_ttl"><spring:message code="AccountVerify" /></h2>
							<div class="shopDataForm">
								<table id="tb" border="0" cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<td align="center" colspan="2"><font size="3" color="red"><spring:message code="AccountVerify" /></font></td>
									</tr>
									<tr>
										<td class="embg"><spring:message code="LAB0032"/></td>
										<td class="fillembg"><spring:message code="LAB0033"/></td>
									</tr>
							
									<tr style="display:none">
										<c:choose>
											<c:when test="${phoneAuth eq 'Y'}">
												<td class="embg">
													<img src="resources/cbg-include/images/shopbase/chk.png" />
												 </td>
												 <td class="fillembg"><spring:message code="LAB0034"/>  
													<spring:message code="LAB0035"/>
												 </td>
											</c:when>
											<c:otherwise>
												<td class="embg">
													<img src="resources/cbg-include/images/shopbase/noChk.png" />
												 </td>
												 <td class="fillembg"><spring:message code="LAB0034"/>  
													<spring:message code="LAB0036"/>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<button id="sendSmsAuth" type="button" onclick="setSmsAuth();"><spring:message code="LAB0037"/></button>
													<br/>
													<br/>
													<div style="display:none" id="inputSmsAuth">
													<font color="red">
													<spring:message code="LAB0038"/></font>
													   <form id="sentToSmsChk">
														<input type="text" name="smsAuth" id="smsAuth" maxlength="10" />
														<input type="button" value="<spring:message code="LAB0039"/>" onclick="chkSmsAuth();">
													   </form>
													</div>
												 </td>
											</c:otherwise>
										</c:choose>
									</tr>
							
									<tr>
										<c:choose>
											<c:when test="${emailAuth eq 'Y'}">
												<td class="embg">
														<img src="resources/cbg-include/images/shopbase/chk.png" />
													 </td>
												<td class="fillembg"><spring:message code="LAB0040"/>  
													<spring:message code="LAB0035"/>
												</td>
											</c:when>
											<c:otherwise>
												<td class="embg">
														<img src="resources/cbg-include/images/shopbase/noChk.png" />
													 </td>
												<td class="fillembg"><spring:message code="LAB0040"/>  
													<spring:message code="LAB0036"/>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<button id="sendEmailAuth" type="button" onclick="setEmailAuth();"><spring:message code="LAB0037"/></button>
													<br/>
													<br/>
												</td>
											</c:otherwise>
										</c:choose>
									</tr>
									
								</table>
							</div>
							                        

                                                 
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