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
		
			$(function() {
				$("#chkForm").click(function() {
					
					if ($('#shopActivityNews').val().length > 200) {
						window.alert('活動資訊，請小於200字!');
						return false;
					}
		
					document.form1.submit();
				});
			});
			
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
							<spring:message code="ShopIntroduce" />
						</h2>
						<form name="form1" id="form1" action="shopintropic/shop/shopintrosave" method="post">
						<div class="shopDataForm">

							

								<table id="tb" border="0" cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<th></th>
										<td align="left">
											<span id="msg" style="color: #F00">${msg}</span>
										</td>
									</tr>

									<tr>
										<td class="embg">&nbsp;<spring:message code="ShopDescDetl"/></td>
										<td class="fillembg">
										<textarea rows="10" cols="50" name="shopContent" id="shopContent">${shopIntroducePic.shopContent}</textarea>
										
										</td>
									</tr>

									<tr>
										<td class="embg">&nbsp;<spring:message code="ActiveNews"/></td>
										<td class="fillembg">
											<textarea rows="3" cols="50" name="shopActivityNews" id="shopActivityNews">${shopIntroducePic.shopActivityNews}</textarea>
										</td>
									</tr>

									<tr>
										<td class="embg">&nbsp;<spring:message code="ShopMemo"/></td>
										<td class="fillembg">
										<textarea rows="10" cols="50" name="shopMemo" id="shopMemo">${shopIntroducePic.shopMemo}</textarea>
					                  </td>
									</tr>

								</table>
								<br/>
							
						</div>
						<br/>
						<div align="center">
						    <input type="hidden" name="shopInfoId" value="${shopIntroducePic.shopInfoId}"/> 
						    <input type="hidden" name="shopId" value="${shopIntroducePic.shopId}"/>
							<button type="button" id="chkForm"><spring:message code="Save"/></button>
						</div>
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