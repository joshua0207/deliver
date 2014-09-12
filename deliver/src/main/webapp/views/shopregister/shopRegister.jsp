<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@ include file="/views/ssi/niceforms.jsp"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><spring:message code="label.register" /></title>

		<!-- header's css start-->
			<%@ include file="/views/cbg-include/header-css.html" %> 
		<!-- header's css end-->
		
	<link type="text/css" href="<c:url value="/resources/cbg-include/css/login.css"/>" rel="stylesheet"/>
	
	<link type="text/css" href="<c:url value="/resources/js/jquery/css/ui-lightness/jquery-ui-1.10.2.custom.min.css"/>" rel="stylesheet"/>
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.9.1.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-ui-1.10.2.custom.min.js"/>"></script>
	
	<%-- <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/js/jquery/validate/css/screen.css"/>" /> --%>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery/validate/jquery.validate.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.maxlength.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/validate/messages_zh_TW.js"/>"></script> 
	
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
	<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/addr.js"/>"></script> 
    <script type="text/javascript" src="<c:url value="/resources/js/googleMap/static.js"/>"></script> 
    <script type="text/javascript" src="<c:url value="/resources/js/googleMap/googleMapFunction.js"/>"></script> 
	<script src="<c:url value="/resources/cbg-include/Scripts/colors.js"/>" type="text/javascript" ></script>
	
	
	<!-- ============================以半徑換算取得其他所有值=========Begin=============================== -->
	<%-- <link href="<c:url value="/resources/js/googleMap/srvMngDrawScope.css"/>" rel="stylesheet" type="text/css" /> 
	<link href="<c:url value="/resources/js/googleMap/shopTarget.css"/>" rel="stylesheet" type="text/css" /> --%>
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
	<!-- ============================以半徑換算取得其他所有值=========Begin=============================== -->
	
	<script>
	window.alert = function(msg) {
		$('#dialog').text(msg).dialog({
			closeText: "hide",
			closeOnEscape:true,
			modal:true,		
			buttons : {
				OK :function(){
					$(this).dialog('close');
				}
			}
		});
	};
	</script>
	
	</head>
	<body id="body">
     
    <div id="dialog"></div>
    
	<div class="heater">
		<div class="logo">
			<a href="http://.com.tw/">
				<img src="<c:url value="/resources/cbg-include/images/logo/logoback.jpg"/>" width="238" height="94" border="0" />
			</a>			
		</div>
	</div>
		
	<script type="text/javascript">
		
		$(function(){	
			var msg = '<c:out value="${msg}" />';
			if(msg != null && msg != ''){
				alert(msg);
			}
			
			pageOnload();
			addrOnload();
			setValToField();
		});
		
	
		//=============================以半徑換算取得其他所有值=========Begin===============================
		//取得資料庫資料，因該資料存放於hidden欄位中，故以此種方式取值
		function getInitData(strOp) {
			switch (strOp) {
			case 'strAllScopeData':
				return $("#strAllScopeData").val();
				break;
			case 'dblShopInitLat':
				return parseFloat($("#addrLat").val());
				break;
			case 'dblShopInitLng':
				return parseFloat($("#addrLng").val());
				break;
			case 'strFncOpenOrClose':
				return $("#strFncOpenOrClose").val();
				break;
			case 'strMapName':
				return 'map';
				break;
			case 'strCoName':
				return $("#coName").val();
				break;
			}

		}
	
		//取出原先使用者所繪製的圖型(mapOnLoad()裡會呼叫此function)
		function redrawScopeData() {
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
		}
		
		
		//以半徑換算取得其他所有值
		function directSaveCircleScope( dstnc ){
			mapOnLoad();//初始化基本參數物件建立
			tmpCircle();
			if (sNowClickScopeObj.polygon.getBounds().getNorthEast().lng() > sNowClickScopeObj.polygon.getBounds().getSouthWest().lng()) {
				sNowClickScopeObj.dblMaxLng = sNowClickScopeObj.polygon.getBounds().getNorthEast().lng();
				sNowClickScopeObj.dblMinLng = sNowClickScopeObj.polygon.getBounds().getSouthWest().lng();
			} else {
				sNowClickScopeObj.dblMaxLng = sNowClickScopeObj.polygon.getBounds().getSouthWest().lng();
				sNowClickScopeObj.dblMinLng = sNowClickScopeObj.polygon.getBounds().getNorthEast().lng();
			}
			if (sNowClickScopeObj.polygon.getBounds().getNorthEast().lat() > sNowClickScopeObj.polygon.getBounds().getSouthWest().lat()) {
				sNowClickScopeObj.dblMaxLat = sNowClickScopeObj.polygon.getBounds().getNorthEast().lat();
				sNowClickScopeObj.dblMinLat = sNowClickScopeObj.polygon.getBounds().getSouthWest().lat();
			} else {
				sNowClickScopeObj.dblMaxLat = sNowClickScopeObj.polygon.getBounds().getSouthWest().lat();
				sNowClickScopeObj.dblMinLat = sNowClickScopeObj.polygon.getBounds().getNorthEast().lat();
			}
			
		    var circleCenter = sNowClickScopeObj.floatCenter;
			var circleTan = sNowClickScopeObj.floatTan;
			var distance = sNowClickScopeObj.floatDistance;
			var maxLng = sNowClickScopeObj.dblMaxLng;
			var minLng = sNowClickScopeObj.dblMinLng;
			var maxLat = sNowClickScopeObj.dblMaxLat;
			var minLat = sNowClickScopeObj.dblMinLat;
			
			//半徑換算有7個值回傳後端
			$("#circleCenter").val(circleCenter);
			$("#circleTan").val(circleTan);
			$("#distance").val(distance);
			$("#maxLng").val(maxLng);
			$("#minLng").val(minLng);
			$("#maxLat").val(maxLat);
			$("#minLat").val(minLat);
			
		}
		//=============================以半徑換算取得其他所有值=========End===============================
		
		function formSubmit() {
			var geocoder = new google.maps.Geocoder();
			var aryStr = new Array();
			var strTemp = "";
			var dblInitLat = 0;
			var dblInitLng = 0;
			var strAllShopTypeName = '';
	
			aryStr[aryStr.length] = $('#cityAddrL').val();
			aryStr[aryStr.length] = $('#cantonAddrL').val();
			aryStr[aryStr.length] = $('#detailAddrL').val();
			strTemp = aryStr.join("");
			geocoder.geocode({
				'address' : strTemp
			}, function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					var location;
	
					location = results[0].geometry.location;
					dblInitLat = location.lat();
					dblInitLng = location.lng();
					$('#addrLat').val(dblInitLat);
					$('#addrLng').val(dblInitLng);
					//以半徑換算取得其他所有值
					directSaveCircleScope( $("#distance").val() );
					//搜尋關鍵字用
					strAllShopTypeName = getTitleFormChkBoxByName("myShopTypeId");
					$('#strAllShopTypeName').val(strAllShopTypeName);
					
					//新增店家
					$('#dataForm').submit();
				} else if (status == google.maps.GeocoderStatus.ZERO_RESULTS) {
					alert('<spring:message code="LAB1068"/>');
				}
			});
		}
		
		function chkCoNum() {
			var blChk = false;
			var strCoNum = $("#coNum").val() + "";
			if (strCoNum.trim() == "" || checkIdentNum(strCoNum)) {
				blChk = true;
			} else {
				alert('<spring:message code="LAB1069"/>');
			}
	
			return blChk;
		}
		
		
		function pageOnload() {
			
			//訂餐專線同電話號碼
			$('#equalsTel').click(function() {
					setValueByAtoB_chk('tel', 'telOrder', 'equalsTel');
			});
			
			//發票類別
			$(".chkInvoice").click(function() {
				var id = $(this).val();
				if (id == 1) {//買受人名稱
					$("#divInvoiceData").slideDown();
				} else if (id == 0) {
					$("#divInvoiceData").slideUp();
				}
			});
	
			$("#chkForm").click(function() {
				if ($("#checkThisId").val() == "Y") {
					alert('<spring:message code="LAB1070"/>');
					return;
				}
				if (chkCoNum()) {
				} else {
					return false;
				}
				
				if (!(/^09\d{8}$/).test(dataForm.mobilePhone.value)) {
					alert('<spring:message code="LAB1071"/>');
					return false;
				}
	
				if ((dataForm.cityAddrL.value) == "") {
					alert('<spring:message code="LAB1072"/>');
					return false;
				}
				if ((dataForm.cantonAddrL.value) == "") {
					alert('<spring:message code="LAB1072"/>');
					return false;
				}
	
				if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(dataForm.email.value))) {
					alert('<spring:message code="LAB1073"/>');
					return false;
				}
				
				if ((dataForm.distance.value) == "") {
					dataForm.distance.value = 0;
				}
				
				if($("input[name=invoiceType]:checked").val()==1){
					if((dataForm.invoiceName.value) == ""){
						alert('<spring:message code="LAB1099"/>');
						return false;
					}
				}
				
				if ($("#dataForm").valid()) {
					$("#chkForm").attr("disabled", "disabled");
					formSubmit();
				}
	
			});
	
			$.validator.addMethod("chkEngOrNum", function(value, element) {
				var re = /^([a-zA-Z0-9]+)$/;
				return this.optional(element) || (re.test(value));
			});
			$.validator.addMethod("notEqualTo", function(value, element, param) {
				return value != $(param).val();
			});
			event: 'blur', $('#dataForm').validate({
				rules : {
					shopId : {
						required : true,
						rangelength : [ 4, 30 ]
					},
					userPw : {
						required : true,
						rangelength : [ 4, 30 ],
						equalTo : '#cnfUserPw',
						chkEngOrNum : true,
						notEqualTo : '#shopId'
					},
					cnfUserPw : {
						required : true,
						rangelength : [ 4, 30 ],
						equalTo : '#userPw',
						chkEngOrNum : true
					},
					coName : {
						required : true,
						rangelength : [ 2, 30 ]
					},
					coPeople : {
						required : true,
						rangelength : [ 2, 30 ]
					},
					detailAddrL : {
						required : true,
						rangelength : [ 5, 100 ]
					},
					tel : {
						required : true,
						rangelength : [ 5, 20 ],
						digits : true
					},
					telOrder : {
						required : true,
						rangelength : [ 5, 20 ],
						digits : true
					},
					mobilePhone : {
						required : true,
						rangelength : [ 10, 13 ],
						digits : true
					},
					email : {
						required : true
					},
					myShopTypeId : {
						required : true
					},
					distance : {
						rangelength : [ 1, 5 ],
						digits : true
					},
				},
				messages : {
					userPw : {
						chkEngOrNum : '<spring:message code="LAB1074"/>',
						equalTo : '<spring:message code="LAB1075"/>',
						notEqualTo : '<spring:message code="LAB1076"/>'
					},
					cnfUserPw : {
						chkEngOrNum : '<spring:message code="LAB1074"/>',
						equalTo : '<spring:message code="LAB1077"/>'
					},
					myShopTypeId : {
						required : '<spring:message code="LAB1078"/>'
					}
				},
				errorPlacement: function(error, element) { 
				    if ( element.is(":radio") ) 
				        error.appendTo ( element.parent() ); 
				    else if ( element.is(":checkbox") ) 
				        error.appendTo ( element.parent() ); 
				    else if ( element.is("input[name=captcha]") ) 
				        error.appendTo ( element.parent() ); 
				    else 
				        error.insertAfter(element); 
				} 
			});
	
		}
	
		function checkShopId() {
			
			var shopId = $("#shopId").val();
			if (shopId.length >= 4) {
				
				$.ajax({
					type : "POST",
					url : "shopbasemanager/chkshopid",
					data : {
						"shopId" : $("#shopId").val()
					},
					dataType : "json",
					error : function() {
	
					},
					success : function(data) {
						if (data.success) {
							alert(data.msg);
							$("#checkThisId").val("Y");
						} else {
							//alert(data.msg);
							$("#checkThisId").val("N");
						}
					}
				});
			}
		}
	
		/* function chkInvoiceField() {
			var blChk = false;
			var id = $("input[name=invoiceType]:checked").val();
			if (id == 1) {
				if ($("#invoiceName").val().trim() == "") {
					alert("請輸入買受人名稱");
				} else if ($("#identNum").val() == "" || !checkIdentNum($("#identNum").val())) {
					alert("統一編號有誤，煩請重新確認");
				} else {
					blChk = true;
				}
			} else if (id == 0) {
				blChk = true;
			}
	
			return blChk;
		} */
		
		
		//地址初始化
		function addrOnload() {
			initCounty("cityAddrL");
			$('#cityAddrL').change(function() {
				changeZone('cityAddrL', 'cantonAddrL');
			});
		}
		
		//地址連動
		function setValToField()
		{		
			setOpSlcedByKeyWord("cityAddrL",'');
			changeZone("cityAddrL","cantonAddrL");	
			setOptionSelected("cantonAddrL",'');	
		}

</script>

<!--serviceTermForm begin-->
<div class="serviceTermForm">
	<form name="dataForm" id="dataForm" action="shopbasemanager/shopregistercreate" method="post">
		<!-- class="niceform"-->
		<div class="shopDataForm" style="width: 700px;">
			<table border="0" cellspacing="0" cellpadding="0" width="699px">
				<tr>
					<td class="fillembg"><spring:message code="LAB1079"/>
					<!-- <input type="hidden" id="onlyNet" name="onlyNet" value="N"> -->
					</td>
				</tr>
			</table>
		</div>

		<h3><spring:message code="LAB1081"/></h3>
		<div align="center">
			<span id="msg" style="color: #F00"> 
			</span>
		</div>

		<div class="shopDataForm" style="width: 700px;">
			<table border="0" cellspacing="0" cellpadding="0" width="699px">
				<tr id="onlyNetN">
					<th colspan="2" style="text-align: center;"><spring:message code="LAB1080"/><br />
						<spring:message code="LAB1082"/>
					</th>
				</tr>
				<tr>
					<!-- 店家id -->
					<td class="embg"><label>*</label><spring:message code="LAB1083"/></td>
					<td class="fillembg">
					<input type="text" id="shopId" name="shopId" maxlength="30" onBlur="checkShopId()" /> 
					<input type="hidden" id="checkThisId" name="checkThisId"></td>
				</tr>
				<tr>
					<!-- 密碼 -->
					<td><label>*</label><spring:message code="LAB1084"/></td>
					<td class="fillembg">
					<input type="password" id="userPw" name="userPw" maxlength="30" /></td>
				</tr>
				<tr>
					<td class="embg"><label>*</label><spring:message code="LAB1085"/></td>
					<td class="fillembg">
					<input type="password" id="cnfUserPw" name="cnfUserPw" maxlength="30" /></td>
				</tr>
				<tr>
					<!-- 店家名稱 -->
					<td><label>*</label><spring:message code="LAB1086"/></td>
					<td class="fillembg">
					<input type="text" id="coName" name="coName" maxlength="30" /></td>
				</tr>
				<tr>
					<!-- 統一編號 -->
					<td class="embg"><label id="lblCoNumOrPID"></label><span
						id="coNumText"><spring:message code="LAB1087"/></span></td>
					<td class="fillembg">
					<input type="text" id="coNum" name="coNum" maxlength="20" /></td>
				</tr>
				<tr>
					<!-- 申請人 姓名-->
					<td><label>*</label><spring:message code="LAB1088"/></td>
					<td class="fillembg">
					<input type="text" id="coPeople" name="coPeople" maxlength="30" /></td>
				</tr>
				
				<tr>
					<!-- 申請人 手機 -->
					<td class="embg"><label>*</label><spring:message code="LAB1089"/></td>
					<td class="fillembg">
					<input type="text" id="mobilePhone" name="mobilePhone" maxlength="13" /></td>
				</tr>
				
				<tr>
                    <td><label>*</label><spring:message code="LAB1090"/></td>
                    <td class="fillembg">
                    <input type="text"  maxlength="13" name="tel" id="tel" value="" style="width:100px;"/>
                    </td>
                </tr>
                
                <tr>
                    <td class="embg"><label>*</label><spring:message code="TelOrder" />
                      	   <br/><input type="checkbox" id="equalsTel" name="equalsTel"/>
                           <spring:message code="EqualsTel" />
                       </td>
                       <td class="fillembg"><input type="text"  maxlength="13" name="telOrder" id="telOrder" value="" style="width:100px;"/></td>
                    </tr>

				<tr><!-- 聯絡地址 -->
		            <td><label>*</label><spring:message code="LAB1091"/>&nbsp;
		            </td>
		            <td class="fillembg">
						<select class="search_sel" id="cityAddrL" name="cityAddrL" size="1" ></select>
		                <select class="search_sel" id="cantonAddrL" name="cantonAddrL" size="1" ></select> 
                        <input type="text" style="width:200px;"  maxlength="100" size="20" name="detailAddrL" id="detailAddrL"  />   
						<input type="hidden" id="addrLat" name="addrLat" value="0">
						<input type="hidden" id="addrLng" name="addrLng" value="0">
		            </td>
		        </tr>

				<tr>
					<!-- email -->
					<td class="embg"><label>*</label><spring:message code="LAB1092"/></td>
					<td class="fillembg">
					<input type="text" id="email" name="email" maxlength="60" style="width: 170px;"/> 
					<!-- <input type="hidden" id="blChkEmail" value="false"> -->
					</td>
				</tr>
				<tr>
					<td><spring:message code="LAB1093"/></td>
					<td class="fillembg">
					<input type="text" id="distance" name="distance" size="5" maxlength="5" value="0" /></td>
				</tr>
				
				 <tr><!-- 店家類別 -->
		            <td class="embg"><label>*</label><spring:message code="LAB1094"/></td>
		            <td class="fillembg">
		                <%								
							if( StringUtils.isNotBlank( (String)request.getAttribute("strListShopType")) ) 									
								out.println( (String)request.getAttribute("strListShopType") );
						%>                                 
		            </td>
				</tr>  
				
				<tr id="invoiceTypeItem">
					<!-- 發票類別 -->
					<td><label>*</label><spring:message code="LAB1095"/></td>
					<td class="fillembg"><label> <!-- 二聯發票  --> 
					<input type="radio" id="invoiceType" name="invoiceType" value="0" class="chkInvoice" checked="checked" /><spring:message code="LAB1096"/>
					</label> <label> <!-- 三聯發票 --> 
					<input type="radio" id="invoiceType" name="invoiceType" value="1" class="chkInvoice" /><spring:message code="LAB1097"/>
					</label>
					<div style="display: none" id="divInvoiceData" name="divInvoiceData">
					<label><spring:message code="LAB1098"/></label>
					<!-- 受買人: -->
					<label><input type="text" id="invoiceName" name="invoiceName" /></label>
					</div></td>
				</tr>
			</table>
		</div>
		<input type="hidden" id="circleCenter" name="circleCenter" value="" />
		<input type="hidden" id="circleTan" name="circleTan" value="" />
		<input type="hidden" id="maxLng" name="maxLng" value="" />
		<input type="hidden" id="minLng" name="minLng" value="" />
		<input type="hidden" id="maxLat" name="maxLat" value="" />
		<input type="hidden" id="minLat" name="minLat" value="" />
		<input type="hidden" id="strAllShopTypeName" name="strAllShopTypeName" value="" />
	</form>
	<!--.shopDataForm end-->
	<div class="shopDataBtnForm">
		<button src="<c:url value="/resources/cbg-include/images/shopbase/btn_register_send.png"/>" id="chkForm" class="shopData_btnSend"></button>
	</div>
</div>
<div id="map"></div>
<!--serviceTermForm end-->
	
	<!-- FOOTER start-->  
      <%@ include file="/views/cbg-include/footer2.jsp" %> 
    <!-- FOOTER end--> 
    
    </body>

</html>