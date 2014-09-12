<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


				<link rel="stylesheet" type="text/css" href="<c:url value="/resources/cbg-include/css/thickbox.css"/>" />
				<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.4.3.min.js"/>"></script>
				<script type="text/javascript" src="<c:url value="/resources/js/jquery/thickbox.js"/>"></script>
               <!-- 要切出去的content -->    
                
			  <h3 class="h3_main">最新訊息</h3>    
              <ul>    
                <li><span class="text_orange">2009年03月25日</span>　網站公告最新消息。</li>    
                <li><span class="text_orange">2009年03月20日</span>　網站公告最新消息。</li>    
                <li><span class="text_orange">2009年03月10日</span>　網站公告最新消息。</li>   
                <li><span class="text_orange">2009年02月20日</span>　網站公告最新消息。</li>
              </ul>    
              <h3 class="h3_main">重要提醒</h3>	
                 <div class="feature">    
                    <h3>新進訂購單列表</h3>
                 </div>       
                 <%//@ include file="/jsp/shopadmin/shopordermanager/shopOrderManagerHomePageQuery.jsp" %>
                 
                 <br></br>
                
              <div class="feature">    
                <div>      <h3>待處理訂單</h3></div>    
                <div class="feature_text">    
                  <ul>    
                    <li><span class="text_orange">2009年04月07日</span> 姓名:XXX 網路訂餐狀態"處理中" </li>    
                    <li><span class="text_orange">2009年03月25日</span> 姓名:XXX 網路訂餐狀態"出貨中" </li>    
                    <li><span class="text_orange">2009年03月20日</span>　姓名:XXX 網路訂餐狀態"出貨中" </li>    
                    <li><span class="text_orange">2009年03月10日</span>　姓名:XXX 網路訂餐狀態"待處理" </li>    
                    <li><span class="text_orange">2009年02月20日</span>　姓名:XXX 網路訂餐狀態"處理中" </li>    
                  </ul>    
                </div>        
              </div>
             
               <!-- 要切出去的content end-->    