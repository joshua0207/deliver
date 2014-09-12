<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%//@ include file="/views/ssi/niceforms.jsp"%>
<%@include file="/views/ssi/shopAllPicture.jsp"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
		<style type="text/css">

            .img { 
                max-width:117px; 
                width:110px; 
                width:expression(document.body.clientWidth>600?"600px":"auto"); 
                overflow:hidden; 
            } 
            .disableDiv{display:none;}
        </style>

		<link href="<c:url value="/resources/cbg-include/css/set.css"/>" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript">
		
			var message = new Array();//宣告讀取的訊息
	        var name = new Array();//宣告讀取的檔名
			$(function() {
					
				 //自動載入照片
				 $.ajax({
			   			type: "POST",
			   			url: 'shopintropic/shop/loadallpic',
			   			data: '',
			   			dataType: 'json',
			   			error: function(xhr, textStatus, errorThrown) {
								alert('An error occurred! ' + ( errorThrown ? errorThrown : xhr.status ));
			   			},
			   			success: function(json, textStatus){
			   				if(textStatus=='success'){
			   					
			   					html = '<div>';
			                    html += '<table border="0" width="10"  style="word-break:break-all;word-wrap:break-word">';
			                    html += '<tr>';
			                    if (json != null && json.thumb.length >0 ) {
					
			                        for ( var i = 0; i < json.thumb.length; i++) {
						
			                            message [i]   = json.title[i]; //把讀取的訊息放在message裡
			                            name [i] = json.filename[i];//把讀取的檔名放到 name裡
			                        
			                            if(i%4==0){
			                                html += '<tr>';  
			                            }
			                            html += '<td><img class="img" src="' + json.thumb[i] + '"/><div align="left" style="width:120px; border:0px solid; overflow:hidden; text-overflow:ellipsis;"><br/><font color="#0066FF"><spring:message code="LAB1019"/></font><br/>'+ json.title[i] +'</div>';
			                        }
					
			                    }
			                    html += '</table>';
			                    html += '</div>';
				
			                    $('#column_right').html(html);
			   					
			   				} else {
			   					alert('<spring:message code="LAB1012"/>');
			   					window.location.reload();
			   				}
			   			},
			   			complete: function(data){
			   				//alert('complete');
			   			}
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
                        <h2 class="campaign_ttl"><spring:message code="LAB1009"/></h2>

                        <!-- message start-->	
                        <div><input name="editImageUpload" type="button" value="<spring:message code="LAB1010"/>" onclick="image_upload();"/></div> 		   
                        <div id="column_right"></div>
                        <!-- message end-->
                        
                	 <br/>
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