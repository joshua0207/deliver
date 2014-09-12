<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@ include file="/views/ssi/niceforms.jsp"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB1022" /></title>

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
	
		var timeList = new Array();
		var timeObject = new Object;
		$(function(){	
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				//alert(msg);
			}
			
			//載入每週外送時間
			for(var i=0;i<timeList.length;i++){
				setChkBoxChkedByVal("listWeek_"+i,timeList[i]);
			}
			
		});
	
		//檢查使用者勾選的日期時間是否正確
		function check() {
			if (!chkChkBoxed()) {
				alert('<spring:message code="LAB1024" />');
				//} else if (!checkTime()) {
				//	alert("請檢查起始時間是否在終止時間之前");
			} else {
				var hdnObj = $("<input>").attr("type", "hidden");
	
				hdnObj.attr("id", "strWeek").attr("name", "strWeek");
				hdnObj.val(getCheckedVal());
				//alert(getCheckedVal());
				$('#updateSendTimeForm').append(hdnObj);
				$("#updateSendTimeForm").submit();
			}
		}
	
		function checkTime() {//檢查起紿時間是否在終止時間之前
			var firstTime = "";//ex. 1030
			var secondTime = "";//ex. 1200
			var blChk = true;
			var startHourObj, startMinuteObj;
			var endHourObj, endMinuteObj;
	
			startHourObj = $("select[name='listTimeTwentyFourStart']");//起始－小時
			startMinuteObj = $("select[name='listTimeMinuteStart']");//起始－分鐘
			endHourObj = $("select[name='listTimeTwentyFourEnd']");//終止－小時
			endMinuteObj = $("select[name='listTimeMinuteEnd']");//終止－分鐘
	
			for (var i = 0; i < startHourObj.length; i++) {
				firstTime = startHourObj.get(i).value + "" + startMinuteObj.get(i).value;
				secondTime = endHourObj.get(i).value + "" + endMinuteObj.get(i).value;
				//alert(parseFloat(secondTime) + " " + parseFloat(firstTime));
				if (parseFloat(secondTime) <= parseFloat(firstTime)) {
					blChk = false;
					break;
				}
			}
	
			return blChk;
		}
	
		//檢查寫入資料庫的資料是否至少都有勾選一天當營業日
		function chkChkBoxed() {
			var strName = "";
			var blChk = true;
			var checkHaveItem = false;
			var i=1;
			var j= false;
			var objectValue = new Array();
			jQuery.each(jQuery('input[type=checkbox]'), function() {
				if(i>7){
					if(j){
						objectValue[objectValue.length] = true;
					}else{
						objectValue[objectValue.length] = false;
					}
					j = false;
					i=1;
				}
				if (this.name.indexOf("listWeek") != -1) {
					checkHaveItem = true;
					strName = this.name;
					if (strName == this.name) {
						if(i==1 || i==2 || i==3 || i==4 || i==5 || i==6 || i==7){
							if (this.checked) {
								j=true;
							}
						}
						i++;
					} 
				} else {
				}
			});
			for(var k=0;k<objectValue.length;k++){
				if(objectValue[k] == false){
					blChk = false;
				}
			}
			if (!blChk && !checkHaveItem) {
				blChk = true;
			}
			return blChk;
		}
		
		
		//檢查寫入資料庫的資料是否至少都有勾選一天當營業日 ==> 原版但有問題只勾星期一會過不了
		/* function chkChkBoxed() {
			var strName = "";
			var blChk = false;
			var checkHaveItem = false;
			jQuery.each(jQuery('input[type=checkbox]'), function() {
	
				//alert("ss:" + this.name.indexOf("listWeek"));
				if (this.name.indexOf("listWeek") != -1) {
					checkHaveItem = true;
					if (strName == "") {
						strName = this.name;
					}
					if (strName == this.name) {
						if (this.checked) {
							blChk = true;
						}
					} else {
						if (!blChk) {
							return false;
						}
						strName = this.name;
						blChk = false;
					}
					//alert(this.name.indexOf("listWeek")+",1this.name:" + this.name + ",strName:" + strName + ",this.checked:" + this.checked + ",blChk:" + blChk);
				} else {
					//alert(this.name.indexOf("listWeek")+",2this.name:" + this.name + ",strName:" + strName + ",this.checked:" + this.checked + ",blChk:" + blChk);
				}
			});
	
			if (!blChk && !checkHaveItem) {
				blChk = true;
			}
			return blChk;
		} */

	
		//令checkbox的值以群族的方式存在
		function getCheckedVal() {
			var strNameTemp = "";//紀錄上一筆的物件名稱
			var strAryResult = new Array();//暫存
			var strReturn = "";//回傳值
	
			strAryResult[strAryResult.length] = "";
	
			jQuery.each(jQuery('input[type=checkbox]'), function() {
				if (this.name.indexOf("listWeek") != -1) {//檢查該checkbox是否為營業日
	
					if (strNameTemp == "")
						strNameTemp = this.name;
	
					if (this.checked) {
						//若名稱相同則將該該營業日設定為同一群族，否則以逗號分隔
						if (strNameTemp == this.name) {
							strAryResult[strAryResult.length] = this.value+",";
						} else {
							strAryResult[strAryResult.length] = ";";
							strNameTemp = this.name;
							strAryResult[strAryResult.length] = this.value+",";
						}
					}
				}
	
			});
			strReturn = strAryResult.join("");//回傳值
			strAryResult = null;//釋放記憶體
	
			return strReturn;
		}
	
		//新增營業時間
		function addDate() {
			var sendTimeWeekTr;
			var sendTimeListTr;
			var sendTimeChkBox;
			var temp;
	
			//先分別烤貝"營業時間_周"的上一層Tr及"營業時間"的上一層Tr
			sendTimeWeekTr = $("#sendTimeTabTemp").children().children("tr:first-child").clone();
			sendTimeListTr = $("#sendTimeTabTemp").children().children("tr:first-child").next("tr").clone();
			sendTimeListTr2 = $("#sendTimeTabTemp").children().children("tr:first-child").next("tr").next("tr").clone();
	
			//取得該Tr中的checkbox
			sendTimeChkBox = sendTimeWeekTr.children("td:last-child");
			
			//更改checkbox的id及name，名字只要別和其它的checkbox重覆即可
			sendTimeChkBox.children().each(function(index) {
				if ($(this).is(':checkbox')) {
					temp = "listWeek_" + $("#sendTimeTab").children().children("tr").length;
					//alert(temp + "-" + index);
					//alert(temp);
					$(this).attr("id", temp + "-" + index);
					$(this).attr("name", temp);
				}
			});
			//新增至列表中
			sendTimeWeekTr.appendTo($("#sendTimeTab"));
			sendTimeListTr.appendTo($("#sendTimeTab"));
			sendTimeListTr2.appendTo($("#sendTimeTab"));
		}
	
		//刪除營業時間
		function delDate(thisObj) {
			//if ($(thisObj).parent().parent().parent().children("tr").length > 3) {
			$(thisObj).parent().parent().next("tr").next("tr").remove();
			$(thisObj).parent().parent().next("tr").remove();
			$(thisObj).parent().parent().remove();
			//} else{
			//alert("此為最後一筆營業時間，不可刪除");
			//}
	
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
                   
                   
						<h2 class="campaign_ttl"><spring:message code="LAB1022" /></h2>
						<br />
						<p align="center">
							<font color="red" size="3">
							${msg}
							</font>
						</p>
						<br />


						<div align="center">
							<button onclick="addDate();"><spring:message code="LAB1025" /></button>
						</div>
						<br />
						<div class="shopDataForm">
							<form name="updateSendTimeForm" id="updateSendTimeForm"
								action="servicetime/shop/addsendtimeday" method="post">
								
								<table id="sendTimeTab" border="0" cellspacing="0"
									cellpadding="0" width="100%">
									
									
									<c:forEach items="${sendTimeList}" var="time" varStatus="s">
									<script type="text/javascript">
										timeObject = '${time.sendDay}';
										timeList[timeList.length] = timeObject;
									</script>
									
									<tr>
										<td class="embg3"><spring:message code="LAB1027" /></td>
										<td class="fillembg"><input type="checkbox"
											name="listWeek_${s.index}" value="1" ><spring:message code="LAB1031"/><input
											type="checkbox" name="listWeek_${s.index}" value="2" ><spring:message code="LAB1032"/>
											<input type="checkbox" name="listWeek_${s.index}" value="3" ><spring:message code="LAB1033"/>
											<input type="checkbox" name="listWeek_${s.index}" value="4" ><spring:message code="LAB1034"/>
											<input type="checkbox" name="listWeek_${s.index}" value="5" ><spring:message code="LAB1035"/>
											<input type="checkbox" name="listWeek_${s.index}" value="6" ><spring:message code="LAB1036"/>
											<input type="checkbox" name="listWeek_${s.index}" value="7" ><spring:message code="LAB1037"/>
											<button class="deleteBtn" onclick="delDate(this);"
												type="button"></button></td>
									</tr>
									<tr>
										<td class="embg3"><font size="2"><spring:message code="LAB1029"/></font></td>
										<td class="fillembg">
										<select name="listTimeTwentyFourStart" value="07"
											id="listTimeTwentyFourStart" >
												<c:forEach var="i" begin="0" end="23">
													<c:choose>
														<c:when test="${i < 10}">
															<c:choose>
																<c:when test="${time.sendTimeBegin == i}">
																	<option value="0${i}" selected="selected">0${i}</option>
																</c:when>
																<c:otherwise>
																	<option value="0${i}">0${i}</option>
																</c:otherwise>
															</c:choose>
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${time.sendTimeBegin == i}">
																	<option value="${i}" selected="selected">${i}</option>
																</c:when>
																<c:otherwise>
																	<option value="${i}">${i}</option>
																</c:otherwise>
															</c:choose>
													    </c:otherwise>
													</c:choose>
												</c:forEach>
											</select> ：
											<select name="listTimeMinuteStart" value="30"
											id="listTimeMinuteStart">
												<c:choose>
													<c:when test="${time.sendMinuteBegin == 30}">
														<option value="00">00</option>
														<option value="30" selected="selected">30</option>
													</c:when>
													<c:otherwise>
														<option value="00" selected="selected">00</option>
														<option value="30">30</option>
													</c:otherwise>
												</c:choose>
											</select> ～ 
											<select name="listTimeTwentyFourEnd" value="22"
											id="listTimeTwentyFourEnd">
												<c:forEach var="i" begin="0" end="23">
													<c:choose>
														<c:when test="${i < 10}">
															<c:choose>
																<c:when test="${time.sendTimeEnd == i}">
																	<option value="0${i}" selected="selected">0${i}</option>
																</c:when>
																<c:otherwise>
																	<option value="0${i}">0${i}</option>
																</c:otherwise>
															</c:choose>
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${time.sendTimeEnd == i}">
																	<option value="${i}" selected="selected">${i}</option>
																</c:when>
																<c:otherwise>
																	<option value="${i}">${i}</option>
																</c:otherwise>
															</c:choose>
													    </c:otherwise>
													</c:choose>
												</c:forEach>
											</select> ：
											<select name="listTimeMinuteEnd" value="00" id="listTimeMinuteEnd">
												<c:choose>
													<c:when test="${time.sendMinuteEnd == 30}">
														<option value="00">00</option>
														<option value="30" selected="selected">30</option>
													</c:when>
													<c:otherwise>
														<option value="00" selected="selected">00</option>
														<option value="30">30</option>
													</c:otherwise>
												</c:choose>
											</select>
										</td>
									</tr>
									<tr>
										<td colspan="2"></td>
									</tr>
									</c:forEach>

								</table>
							</form>
						</div>

						<br />
						<div align="center">
							<button onclick="check();"><spring:message code="LAB1030"/></button>
						</div>

						<table id="sendTimeTabTemp" border="0" cellspacing="0"
							style="display: none" cellpadding="0" width="100%">
							<tr>
								<td class="embg3"><spring:message code="LAB1027" /></td>
								<td class="fillembg"><input type="checkbox" name="xx_99"
									value="1" checked><spring:message code="LAB1031"/><input type="checkbox"
									name="xx_99" value="2"><spring:message code="LAB1032"/> <input type="checkbox"
									name="xx_99" value="3"><spring:message code="LAB1033"/><input type="checkbox"
									name="xx_99" value="4"><spring:message code="LAB1034"/><input type="checkbox"
									name="xx_99" value="5"><spring:message code="LAB1035"/><input type="checkbox"
									name="xx_99" value="6"><spring:message code="LAB1036"/><input type="checkbox"
									name="xx_99" value="7"><spring:message code="LAB1037"/>
									<button class="deleteBtn" onclick="delDate(this);"
										type="button"></button></td>
							</tr>
							<tr>
								<td class="embg3"><font size="2"><spring:message code="LAB1029"/></font></td>
								<td class="fillembg"><select name="listTimeTwentyFourStart"
									id="listTimeTwentyFourStart"><option value="00">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option></select> ： <select
									name="listTimeMinuteStart" id="listTimeMinuteStart"><option
											value="00">00</option>
										<option value="30">30</option></select> ～ <select
									name="listTimeTwentyFourEnd" id="listTimeTwentyFourEnd"><option
											value="00">00</option>
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option></select> ： <select
									name="listTimeMinuteEnd" id="listTimeMinuteEnd"><option
											value="00">00</option>
										<option value="30">30</option></select></td>
							</tr>
							<tr>
								<td colspan="2"></td>
							</tr>
						</table>


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