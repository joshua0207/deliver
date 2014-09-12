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
		
		<style type="text/css">
			.deleteBtn{
				width:61px;
				height:27px;
				display:block;
				text-indent:-9999px;
				background-image:url(resources/cbg-include/images/deleteBtn.png);
				background-repeat:no-repeat;
				border:none 0;
				cursor:pointer;
				margin-bottom:5px;
			}
		</style>
		<script type="text/javascript">
		
			$(function(){	
				var msg = '<c:out value="${msg}" />';
				if(msg != null && msg != ''){
					alert(msg);
				}
			});
		
		
			$(function() {
				$("#delShopBig").click(function() {
					delShopBig();
				});
			});
			
			function delShopBig() {
				 if (confirm('<spring:message code="LAB1005"/>')) {
					
					 $.ajax({
			   			type: "POST",
			   			url: 'shopintropic/shop/shopbigdel',
			   			data: '',
			   			dataType: 'json',
			   			error: function(xhr, textStatus, errorThrown) {
								alert('An error occurred! ' + ( errorThrown ? errorThrown : xhr.status ));
			   			},
			   			success: function(data, textStatus){
			   				if(textStatus=='success'){
			   					alert(data.msg);
			   					location.href='<c:url value="/shopintropic/shop/rectangularbigpic" />';
			   				} else {
			   					alert('<spring:message code="delError"/>');
			   				}
			   			},
			   			complete: function(data){
			   				//alert('complete');
			   			}
			   		}); 
			   			
				}else{
				
				} 
			}
		
		    function checkThing() {
				var result = true;
				if ($("#shopLogoFile").val() == "") {
					alert('<spring:message code="LAB1003"/>');
					result = false;
				}
				return result;
			}
			
		    function chkfile(o){
                reg = /.(jpg|JPG|png|PNG|gif|GIF)$/;
                if (!o.value.match(reg)){
                    alert('<spring:message code="LAB1004"/>');
                    o.focus();
                    location.href='<c:url value="/shopintropic/shop/rectangularbigpic" />';
                    return false;
                }
            }
			
			/* function chkfile(o) {
				reg = /.(jpg|JPG|png|PNG|gif|GIF)$/;
				var filename = $("#" + o.id + "_");
				if (!o.value.match(reg)) {
					alert('請上傳.jpg,.png,.gif的檔案 !!!');
					o.value = "";
					filename.val("");
					return false;
				} else {
					filename.val(o.value);
				}
			} */
			
			

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

						<h2 class="campaign_ttl">
							<spring:message code="LAB1006" />
						</h2>
						<div align="center">
							<h2>
								<font color="#6633FF"><spring:message code="LAB1007"/></font>
							</h2>
						</div>
						<br /> <br />
						<div class="shopDataForm">
								<table id="tb" border="0" cellspacing="0" cellpadding="0"
									width="100%">
									
									<tr>
										<td>

											<div id="uploPath">

												<img src="${path}" />
												<button type="button" id="delShopBig" class="deleteBtn"></button>
												
											</div>
											<%-- <div align="center"><span id="msg" style="color: #F00">${msg}</span></div> --%>
										</td>
										
									</tr>
									<tr>
										<td class="embg">

											<form method="post" action="shopintropic/shop/shopbigup" enctype="multipart/form-data" onsubmit="return checkThing();"> 
											<br/> <br/> 
											<spring:message code="LAB1001"/>
											<input type="file" id="shopLogoFile" name="image" size="50" onchange="chkfile(this);" /> <br/>
												<br /> <spring:message code="LAB1008"/> <br/>
												
												<br/>
												<button type="submit"><spring:message code="Save"/></button>
											</form>
										</td>
									</tr>
								</table>

								<br/>
							
						</div>
						<br/>

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