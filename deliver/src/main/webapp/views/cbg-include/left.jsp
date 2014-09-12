<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>

<%
/* String hostName = request.getServerName();
String headHttps = "";
String headHttp = "";
sysParameterUtil spuz = new sysParameterUtil();
String secrtName = StringUtils.trimToEmpty(spuz.getCPValue("secrtName").trim());
spuz=null;
 
  if(hostName.equalsIgnoreCase(secrtName)){
	  headHttps = "https://" + hostName;
	  headHttp = "http://" + hostName;        
  } else {
	  headHttps = "http://" + hostName;
	  headHttp = "http://" + hostName;     
  }   */
%>

	          <div id="cont_left">    
		            <h2><spring:message code="LAB0000"/></h2>                 
		            <ul>                     
		              <li><a href="<c:url value="/shopbasemanager/shop/manager"/>"><spring:message code="LAB0006"/></a></li>   
		              <li><a href="<c:url value="/shopbasemanager/shop/account"/>"><spring:message code="LAB0007"/></a></li>
		              <li><a href="<c:url value="/emailmobileverify/shop/verifypage"/>"><spring:message code="LAB0008"/></a></li>
		              <li><a href="<c:url value="/shopintropic/shop/shopintro"/>"><spring:message code="LAB0009"/></a></li>       
		              <li><a href="<c:url value="/shopintropic/shop/shoplogo"/>"><spring:message code="LAB0010"/></a></li>
		              <li><a href="<c:url value="/shopintropic/shop/rectangularbigpic"/>"><spring:message code="LAB0011"/></a></li>
		              <li><a href="<c:url value="/shopintropic/shop/shopallpic"/>"><spring:message code="LAB0012"/></a></li>       
		              <li><a href="<c:url value="/servicetime/shop/servicetimeday"/>"><spring:message code="LAB0013"/></a></li>
		              <li><a href="<c:url value="/servicetime/shop/holiday"/>"><spring:message code="LAB0014"/></a></li>
		              <li><a href="<c:url value="/servicetime/shop/sendtimeday"/>"><spring:message code="LAB0015"/></a></li>                    
		              <li><a href="<c:url value="/servicescope/shop/mapscope"/>"><spring:message code="LAB0016"/></a></li>  
		            </ul>
		            <h2><spring:message code="LAB0001"/></h2>                 
		            <ul>                     
		              <li><a href="<c:url value="/websetting/shop/sendsetting"/>"><spring:message code="LAB0019"/></a></li>
		              <li><a href="<c:url value="/websetting/shop/ordernotice"/>"><spring:message code="LAB0020"/></a></li>                                   
		            </ul>
		            <h2><spring:message code="LAB0002"/></h2>                 
		            <ul>                   
		              <li><a href="<c:url value="/menucategory/shop/category"/>"><spring:message code="LAB0021"/></a></li>                     
		              <li><a href="<c:url value="/menuprodoption/shop/prodoption"/>"><spring:message code="LAB0022"/></a></li>                     
		              <li><a href="<c:url value="/menuprod/shop/prod"/>"><spring:message code="LAB0023"/></a></li>                     
		            </ul>
		            <h2><spring:message code="LAB0003"/></h2>                 
		            <ul>                     
		              <li><a href="<c:url value="/ordermanager/shop/ordermanager"/>"><spring:message code="LAB0024"/></a></li>                     
		            </ul>
		           <!--  <h2><spring:message code="LAB0004"/></h2>                 
		            <ul>                     
		              <li><a href="javascript:alert('製作中，請耐心等候');"><spring:message code="LAB0025"/></a></li>                     
		            </ul> -->
		            <!-- <h2>訂位功能設定</h2>                 
		            <ul>                     
		              <li><a href="javascript:alert('製作中，請耐心等候');">訂位日期時間人數設定</a></li>
		              <li><a href="javascript:alert('製作中，請耐心等候');">訂位基本資料設定</a></li>
		              <li><a href="javascript:alert('製作中，請耐心等候');">查詢訂位人數</a></li>
		              <li><a href="javascript:alert('製作中，請耐心等候');">餐廳圖文上傳</a></li>                                
		            </ul>-->       
		            <div id="contact_box">      <h3><spring:message code="LAB0005"/></h3>                     
		              <p>                
		                <span class="text_orange">Free dial.                 </span> (02)-7777777 <br />                           
		                <span class="text_orange">e-mail.                 </span>                  
		                	<a href="mailto:mil@mil.com">deliver@deliver.com.tw</a><br />                           
		                <span class="text_orange"><spring:message code="LAB0026"/>                 </span> <spring:message code="LAB0027"/>               
		              </p>                 
		            </div>           
         	  </div>       