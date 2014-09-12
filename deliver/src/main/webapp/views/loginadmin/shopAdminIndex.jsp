<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <%@include file="/views/meta.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><spring:message code="LAB0042"/></title>
       <!-- header's css start-->
   	    <%@ include file="/views/cbg-include/header-css.html" %> 
       <!-- header's css end-->
  </head>
   
  <body>
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
            <h2 class="campaign_ttl"><spring:message code="LAB0043"/></h2>  
            <img src="<c:url value="/resources/cbg-include/images/usestep.jpg" />"/>
            <div class="company_info">  
              <div style="vertical-align: top;"><img src="resources/cbg-include/images/logo/img_01.jpg" alt="img_01" width="190" height="140" class="img_border" /></div>         
              <div class="company_info_text"><h3><spring:message code="LAB0044"/></h3> <p><spring:message code="LAB0045"/></p> </div>  
            </div>
            <div id="content_main">  
				
              <%@ include file="/views/loginadmin/shopAdminIndexContent.jsp" %>   
               
            </div>    
          </div>  
          <div class="clear_both"></div>
        </div>   
      </div>
      
    <!-- FOOTER start-->  
     <%@ include file="/views/cbg-include/footer.jsp" %> 
    <!-- FOOTER end-->  
      
    </div>
  </body>
</html>