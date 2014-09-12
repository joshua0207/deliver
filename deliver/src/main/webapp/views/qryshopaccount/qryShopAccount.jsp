<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ include file="/views/ssi/outSendMoney.jsp" %>
<%@ include file="/views/ssi/niceforms.jsp"%>
<%@ include file="/views/ssi/qryShop.jsp" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->

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
                        <h3><spring:message code="UpdateAccountPwd" /></h3>
                        <br/>
                        <form id="rgsForm" name="rgsForm" method="post">
                        <div class="shopDataForm">
                            <table id="tb" border="0" cellspacing="0" cellpadding="0" width="100%" >
                                <tr>
                                    <th><spring:message code="LAB0017" /></th>        
                                    <td align="left"><span id="msg" style="color:#F00">
                                        ${msg}
                                    </span></td>
                                </tr>
                                <tr>
                                    <td class="embg">*&nbsp;<spring:message code="UserId" /></td>
                                    <td class="fillembg">${loginVo.shopId}<input type="hidden" id="shopId" name="shopId" value="${loginVo.shopId}"></input></td>
                                </tr>
                                
                                <tr>
                                    <td class="embg">*&nbsp;<spring:message code="LAB1049" /></td>
                                    <td class="fillembg"><input type="password" maxlength="30" name="oldPassword" id="oldPassword" /></td>
                                </tr>
                                
                                <tr>
                                    <td>*&nbsp;<spring:message code="LAB1050" /><br/><spring:message code="LAB0050" /></td>
                                    <td class="fillembg"><input type="password"  maxlength="30" name="newPassword" id="newPassword" /></td>
                                </tr>  
                                <tr>
                                    <td class="embg">*&nbsp;<spring:message code="LAB0051" /></td>
                                    <td class="fillembg"><input type="password" maxlength="30" name="confirmPassword" id="confirmPassword" /></td>
                                </tr>
                               
                            </table>
                        </div>    
                        </form>
                        <!--.shopDataForm end-->
                        <div class="shopDataBtnForm">
                            <button src="resources/cbg-include/images/shopbase/btn_register_send.png" id="chkForm" class="shopData_btnSend" ></button>
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