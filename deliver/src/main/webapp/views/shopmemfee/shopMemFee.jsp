<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@ include file="/views/ssi/niceforms.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB1021" /></title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
	<style type="text/css">
		
		}
	</style>
	
	<link href="<c:url value="/resources/js/timedate/hot-sneaks/jquery-ui.css"/>" rel="stylesheet" type="text/css" />
	<script src="<c:url value="/resources/js/timedate/jquery-ui-1.8.6.custom.min.js"/>"></script>
	<script src="<c:url value="/resources/js/timedate/jquery.ui.datepicker-zh-TW.js"/>"></script>
	<script type="text/javascript">
	
		$(function(){	
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				//alert(msg);
			}
			
			$('#holidayData3').datepicker({
				yearRange : "2014:2034",
				changeMonth : true,
				changeYear : true,
				dateFormat : 'yy-mm-dd'
			});
		});
		
		
	
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
                   
                   
						<h2 class="campaign_ttl"><spring:message code="LAB1021" /></h2>
						<br />
						<p align="center">
							<font color="red" size="3">
							${msg}
							</font>
						</p>
						<br />


						

						


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