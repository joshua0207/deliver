<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ include file="/views/ssi/outSendMoney.jsp" %>
<%@ include file="/views/ssi/niceforms.jsp"%>
<%@ include file="/views/ssi/shopMng.jsp" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->

	</head>
	<body id="body" onLoad="pageOnload();setValToField();">
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
                        <h3><spring:message code="UpdateShopData" /></h3>
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
                                    <td class="fillembg">${shopBaseData.shopId}<input type="hidden" id="shopId" name="shopId" value="${shopBaseData.shopId}"></input></td>
                                </tr>
                                
                                <tr>
                                    <td>*&nbsp;<spring:message code="CompanyName" /></td>
                                    <td class="fillembg"><input type="text"  maxlength="30" name="coName" id="coName"  value="${shopBaseData.coName}"/></td>
                                </tr>  
                                <tr>
                                    <td class="embg">&nbsp;<spring:message code="CompanyNumber" /></td>
                                    <td class="fillembg"><input type="text" maxlength="20" name="coNum" id="coNum" value="${shopBaseData.coNum}" /></td>
                                </tr>
                                <tr>
                                    <td>*&nbsp;<spring:message code="CompanyPeople" /></td>
                                    <td class="fillembg"><input type="text"  maxlength="30" name="coPeople" id="coPeople" value="${shopBaseData.coPeople}" /></td>
                                </tr>  
                              
                                <tr>
                                    <td class="embg">&nbsp;<spring:message code="AddrL" /><br>
                                    </td>
                                    <td class="fillembg">
                                        <select class="search_sel" id="cityAddrL" name="cityAddrL" size="1" ></select>
                                        <select class="search_sel" id="cantonAddrL" name="cantonAddrL" size="1" ></select> 
                                        <input type="text"  maxlength="100" size="20" name="detailAddrL" id="detailAddrL" style="width: 150px;" value="${shopBaseData.addrLDetail}" />                           
                                    	<input type="hidden" id="addrCLat" name="addrCLat" value="0">
                                        <input type="hidden" id="addrCLng" name="addrCLng" value="0">
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td>*&nbsp;<spring:message code="Tel" /><br></td>
                                    <td class="fillembg"><input type="text"  maxlength="13" name="tel" id="tel" value="${shopBaseData.tel}" /></td>
                                </tr>
                        
                                <tr>
                                    <td class="embg">*&nbsp;<spring:message code="TelOrder" />
                                   	   <br/><input type="checkbox" id="equalsTel" name="equalsTel"/>
                                        <spring:message code="EqualsTel" />
                                    </td>
                                    <td class="fillembg"><input type="text"  maxlength="20" name="telOrder" id="telOrder" value="${shopBaseData.telOrder}" /></td>
                                </tr>
                                
                                <tr>
                                    <td>*&nbsp;<spring:message code="MobilePhone" /></td>
                                    <td class="fillembg"><input type="text" maxlength="13" name="mobilePhone" id="mobilePhone" value="${shopBaseData.mobilePhone}" /></td>
                                </tr>  
                        
                                
                                <tr>
                                    <td  class="embg">&nbsp;<spring:message code="Email" /></td>
                                    <td class="fillembg">
                                        <input type="text"  maxlength="60" name="email" id="email" style="width: 150px;" value="${shopBaseData.email}" />
                                        <input type="hidden" id="blChkEmail" value="false">
                                    </td>
                                </tr>  
                                
			 					<tr>
						        	<td>&nbsp;<spring:message code="InvoiceName" /></td>
						        	<td class="fillembg">
										<input type="text" name="invoiceName" id="invoiceName" value="${shopBaseData.invoiceName}" />
						        	</td>			        
								</tr> 	                                
                                <tr>
                                    <td class="embg">&nbsp;<spring:message code="ShopType" /></td>
                                    <td class="shopstyle">
                                        ${strShopType}
                                    </td>
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