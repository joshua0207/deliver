<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@ include file="/views/ssi/niceforms.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="LAB0056" /></title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
	<style type="text/css">
		
	</style>
	
	<link href="<c:url value="/resources/js/googleMap/srvMngDrawScope.css"/>" rel="stylesheet" type="text/css" /> 
	<link href="<c:url value="/resources/js/googleMap/shopTarget.css"/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/googleMap/MyOverLayer.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/googleMap/static.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/googleMap/Shape.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/googleMap/Circle.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/googleMap/CircleFnc.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/googleMap/Dot.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/googleMap/Irregular.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/googleMap/IrregularFnc.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/googleMap/Square.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/googleMap/SquareFnc.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/googleMap/srvMngDrawScopeFnc.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/ajax.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/googleMap/googleMapFunction.js"/>"></script>
	
	<script type="text/javascript">
		<!--
		/* $(function(){
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				//alert(msg);
			}
		}); */
		
		$(document).ready(function() {
			mapOnLoad(false);//初始化地圖
			//以下，初始化畫圖工具
			$('#visibleBtn').click(function() {
				hiddenAllDiv();
			});
			$('#saveScope').click(function() {
				//saveScope();
				delScope();
				directSaveScope();
			});

			$('#hand').click(function() {
				initToolbarStatus();
				initDrawStatus();
				return false;
			});
			$('#drawSquare').click(function() {
				chkStatus('drawSquare', 'drawSquare');
				setTextToTips();
				return false;
			});
			$('#drawCircle').click(function() {
				chkStatus('drawCircle', 'drawCircle');
				setTextToTips();
				return false;
			});
			$('#drawIrregular').click(function() {
				chkStatus('drawIrregular', 'drawIrregular');
				setTextToTips();
				return false;
			});
			$('#squareHelp').click(function() {
				drawToolsHelp('Square', $(this));
				return false;
			});
			$('#circleHelp').click(function() {
				drawToolsHelp('Circle', $(this));
				return false;
			});
			$('#irregularHelp').click(function() {
				drawToolsHelp('Irregular', $(this));
				return false;
			});
			$('#distance').mousedown(function() {
				if (sNowClickScopeObj == null) {
					setElObjDisable("saveScope", true);
					chgDabImg("saveScope", true);
				}
			});
			hiddenAllDiv();
		});

		//取出原先使用者所繪製的圖型
		function redrawScopeData() {
			if ($("#serviceScopeType").val() == "Circle") {
				if ($("#shopId").val() != "") {
					var newCircle = new Circle();
					var aryListFirst = $("#circleCenter").val().split(",");
					var aryListLast = $("#circleTan").val().split(",");

					newCircle.id = $("#shopId").val();
					newCircle.gObjMarkerFirst = createMarker(aryListFirst[0],
							aryListFirst[1]);
					newCircle.gObjMarkerLast = createMarker(aryListLast[0],
							aryListLast[1]);
					newCircle.floatCenter = $("#circleCenter").val();
					newCircle.floatTan = $("#circleTan").val();
					newCircle.floatDistance = parseInt($("#distance").val());
					addCircleScopeFromDB(newCircle);
					setObjToNowClick(newCircle);
					newCircle = null;
				}

			} else if ($("#serviceScopeType").val() == "Square") {
				if ($("#shopId").val() != "") {
					var newSquare = new Square();

					newSquare.id = $("#shopId").val();
					newSquare.gObjMarkerFirst = createMarker($("#firstDotLat")
							.val(), $("#firstDotLng").val(), false);
					newSquare.gObjMarkerLast = createMarker($("#lastDotLat").val(),
							$("#lastDotLng").val(), false);
					//alert($("#firstDotLat").val());
					addSquareScopeFromDB(newSquare);
					setObjToNowClick(newSquare);
					newSquare = null;
				}

			} else if ($("#serviceScopeType").val() == "Irregular") {
				if ($("#shopId").val() != "") {
					var aryListDotLatLng = new Array();
					var total;
					var newIrregular = new Irregular();
					var aryLatLng;

					//alert($("#dotLatLng").val());
					aryListDotLatLng = $("#dotLatLng").val().split(";");
					total = $("#total").val();
					newIrregular.id = $("#shopId").val();

					for (var j = 0; j < total; j++) {
						var gObjMarker;

						aryLatLng = aryListDotLatLng[j].split(",");
						gObjMarker = createMarker(aryLatLng[0], aryLatLng[1]);
						newIrregular.aryMarker.push(gObjMarker);

						gObjMarker = null;
						aryLatLng = null;
					}
					//alert(newIrregular);
					drawIrregular(newIrregular);
					setObjToNowClick(newIrregular);
					newIrregular = null;
				}
			}
		}

		function closeHelp() {
			if ($('#swfTeach').is(":visible"))
				$('#swfTeach').hide();
			return false;
		}
		//繪圖說明
		function drawToolsHelp(strHelpName, thisObj) {
			if (strHelpName == 'Square' || strHelpName == 'Circle'
					|| strHelpName == 'Irregular') {
				if (!$('#swfTeach').is(":visible"))
					$('#swfTeach').show();
				switch (strHelpName) {
				case 'Square':
					//$('#swfTeach').load('/explanationSquare.action');
					break;
				case 'Circle':
					//$('#swfTeach').load('/explanationCircle.action');
					break;
				case 'Irregular':
					//$('#swfTeach').load('/explanationIrregular.action');
					break;
				}
				$('#swfTeach').css("left", thisObj.offset().left + "px");
			}

		}	
		//取得資料庫資料，因該資料存放於hidden欄位中，故以此種方式取值
		function getInitData(strOp) {
			switch (strOp) {
			case 'strAllScopeData':
				return $("#strAllScopeData").val();
				break;
			case 'dblShopInitLat':
				return parseFloat($("#dblShopInitLat").val());
				break;
			case 'dblShopInitLng':
				return parseFloat($("#dblShopInitLng").val());
				break;
			case 'strFncOpenOrClose':
				return $("#strFncOpenOrClose").val();
				break;
			case 'strMapName':
				return 'map';
				break;
			case 'strCoName':
				return $("#strCoName").val();
				break;
			}

		}

		-->
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
                   
						<h2 class="campaign_ttl"><spring:message code="LAB0056" /></h2>
						<br />
						<p align="center">
							<font id="msg" color="red" size="3">
							${msg}
							</font>
						</p>
						<br />
							
						<!-- 圓型 Circle -->
						<input type="hidden" id="shopId" value='${serviceScopeCircle.shopId}' />
						<input type="hidden" id="circleCenter" value='${serviceScopeCircle.circleCenter}' />
						<input type="hidden" id="circleTan" value='${serviceScopeCircle.circleTan}' />
					
						<!-- 不規則型 Irregular -->
						<!-- <input type="hidden" id="shopId" value='' />
						<input type="hidden" id="dotLatLng" value='' />
						<input type="hidden" id="total" value='' /> -->
					
						<!-- 方型 Square -->
						<!-- <input type="hidden" id="shopId" value='' />
						<input type="hidden" id="firstDotLat" value='' />
						<input type="hidden" id="firstDotLng" value='' />
						<input type="hidden" id="lastDotLat" value='' />
						<input type="hidden" id="lastDotLng" value='' /> -->
						
						<input type="hidden" id="dblShopInitLat" value="${loginVO.addrCLat}" />
						<input type="hidden" id="dblShopInitLng" value="${loginVO.addrCLng}" />
						<input type="hidden" id="strFncOpenOrClose" value="Y" />
						<input type="hidden" id="strCoName" value="${loginVO.coName}" />
						<input type="hidden" id="serviceScopeType" value="${loginVO.serviceScopeType}" />
						
						<div id="map"></div><!-- <div id="map" style="width:530px;height:400px;"></div> -->
						<div id="toolbarDiv" class="toolbarDiv">
							<div id="drawTool" class="paintForm" > 
								<dl>
								<!-- 
						   			<dt><img src="resources/cbg-include/images/shopServiceScope/g_tool.png" width="67" height="26"/></dt>
						   	
						   			<dd class="srv_dd">
						           		<a href="#" id="hand">
						           			<img src="resources/cbg-include/images/shopServiceScope/g_move.png" width="76" height="26" border="0" />
						           		</a>
						       		</dd>
						   	
							        <dd class="srv_dd">
							            <a href="#" id="drawSquare"><img src="resources/cbg-include/images/shopServiceScope/g_btn1.png" width="98" height="26" border="0" title="矩型範圍"/></a>
							            <a id="squareHelp" href="#"><img src="resources/cbg-include/images/shopServiceScope/g_help.png" width="35" height="26" border="0" title="help"/></a>
							        </dd>
							        <dd class="srv_dd">
							        	<a href="#" id="drawCircle"><img src="resources/cbg-include/images/shopServiceScope/g_btn2.png" width="98" height="26" border="0" title="圓型範圍"/></a>
							        	<a id="circleHelp" href="#"><img src="resources/cbg-include/images/shopServiceScope/g_help.png" width="35" height="26" border="0" title="help"/></a>
							        </dd>
							        <dd class="srv_dd">
							        	<a href="#" id="drawIrregular"><img src="resources/cbg-include/images/shopServiceScope/g_btn3.png" width="122" height="26" border="0" title="不規則型範圍"/></a>
							        	<a id="irregularHelp" href="#"><img src="resources/cbg-include/images/shopServiceScope/g_help.png" width="35" height="26" border="0" title="help"/></a>
							        </dd>
							         -->
							         
							    <spring:message code="LAB0057" />
								<input type="text" name="distance" id="distance" value="${serviceScopeCircle.distance}"/>
								<spring:message code="LAB0061" />
						   		</dl>
								<div style="clear:both;"></div>     
							</div>
							
							<button type="button"  name="saveScope" id="saveScope"><spring:message code="LAB1030" /></button>

						</div><!--<div id="toolbarDiv">-->	
						<br/><br/><br/><br/>
						
						<div class="disableDiv" id="setFuction"></div>		     
						                          
						<!-- swf  teach start-->
						<div id="swfTeach"  class="swfTeach"></div>
						<!-- swf  teach end-->
						
						

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