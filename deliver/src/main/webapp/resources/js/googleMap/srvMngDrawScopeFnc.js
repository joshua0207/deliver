var map = null;
var isIE = document.all;
var mySquare = new Square();
var myCircle = new Circle();
var myIrregular = new Irregular();
var strDrawStatus = "";
var strDrawStatusTemp = "hand";
var strToolbarInitBgColor = "#ffebd1";
var strToolbarClickBgColor = "#799EFF";
var sNowClickScopeObj = null;
var dblInitLat = 25.0018,dblInitLng = 121.3009;
var intZoom = 12;
var strTips = "No Data";
var floatMouseLng;
var floatMouseLat;
var intIrrDotTotal = 10;
var latlngDiv = null;
var tipsDiv = null;
var dstDiv = null;
var strLatLngTxt = "";
var aryStrLatLngTxt = new Array();
var gNowLatLng;
var imgPathHeader = "";
var imgMapIcon = imgPathHeader + "resources/cbg-include/images/shophome/map_icon3.jpg";
var imgGMove = imgPathHeader + "resources/cbg-include/images/shopServiceScope/g_move.png";
var imgGBtn = imgPathHeader + "resources/cbg-include/images/shopServiceScope/g_btn1.png";
var imgGBtn2 = imgPathHeader + "resources/cbg-include/images/shopServiceScope/g_btn2.png";
var imgGBtn3 = imgPathHeader + "resources/cbg-include/images/shopServiceScope/g_btn3.png";
var imgGMoveOnclick = imgPathHeader + "resources/cbg-include/images/shopServiceScope/g_move_onclick.png";
var imgGBtn1Onclick = imgPathHeader + "resources/cbg-include/images/shopServiceScope/g_btn1_onclick.png";
var imgGBtn2Onclick = imgPathHeader + "resources/cbg-include/images/shopServiceScope/g_btn2_onclick.png";
var imgGBtn3Onclick = imgPathHeader + "resources/cbg-include/images/shopServiceScope/g_btn3_onclick.png";
var imgPaintArea = imgPathHeader + "resources/cbg-include/images/shopServiceScope/paint_area.png";
var imgReback = imgPathHeader + "resources/cbg-include/images/shopServiceScope/reback.png";
var imgSaveScope = imgPathHeader + "resources/cbg-include/images/shopServiceScope/savearea.png";
var ImgSaveScopeDab = imgPathHeader + "resources/cbg-include/images/shopServiceScope/saveareaDisabled.png";
var ImgDelScope = imgPathHeader + "resources/cbg-include/images/shopServiceScope/cancelarea.png";
var ImgDelScopeDab = imgPathHeader + "resources/cbg-include/images/shopServiceScope/cancelareaDisabled.png";
//國際化語言
var langs = new Array(); 
langs["zh_CH"]=new Array(); 
langs["en_US"]=new Array(); 
langs["zh_CH"]["ClickDrawMsg"]="點擊地圖"; 
langs["zh_CH"]["ClickDrawStartDotMsg"]="點擊地圖繪製起點"; 
langs["zh_CH"]["ClickDrawEndDotMsg"]="點擊地圖繪製終點"; 
langs["zh_CH"]["CanStillBeAddedMsg"]="尚可新增"; 
langs["zh_CH"]["StartDotMsg"]="起點"; 
langs["zh_CH"]["EndDotMsg"]="終點"; 
langs["zh_CH"]["DotMsg"]="點"; 
langs["zh_CH"]["OrClickCToCloseMsg"]="或按c結束"; 
langs["zh_CH"]["IrregularMsg"]="不規則型"; 
langs["zh_CH"]["CircleMsg"]="圓型";
langs["zh_CH"]["SquareMsg"]="矩型";
langs["zh_CH"]["BackMsg"]="返回";
langs["zh_CH"]["DrawScopeMsg"]="繪製區域範圍";
langs["zh_CH"]["MeterMsg"]="公尺";
langs["zh_CH"]["MSG0032"]="確定刪除?";
langs["zh_CH"]["MSG0033"]="確定儲存?";
langs["zh_CH"]["MSG0042"]="每個店家僅限儲存一筆範圍資料";
langs["zh_CH"]["MSG0043"]="請先繪製區域";
langs["zh_CH"]["MSG0044"]="請至少新增3點";
langs["zh_CH"]["MSG0051"]="請先開啟外送功能";
langs["zh_CH"]["MSG0052"]="與原點距離";
//English
langs["en_US"]["ClickDrawMsg"]="點擊地圖"; 
langs["en_US"]["ClickDrawStartDotMsg"]="點擊地圖繪製起點"; 
langs["en_US"]["ClickDrawEndDotMsg"]="點擊地圖繪製終點"; 
langs["en_US"]["CanStillBeAddedMsg"]="尚可新增"; 
langs["en_US"]["StartDotMsg"]="起點"; 
langs["en_US"]["EndDotMsg"]="終點"; 
langs["en_US"]["DotMsg"]="點"; 
langs["en_US"]["OrClickCToCloseMsg"]="或按c結束"; 
langs["en_US"]["IrregularMsg"]="不規則型"; 
langs["en_US"]["CircleMsg"]="圓型";
langs["en_US"]["SquareMsg"]="矩型";
langs["en_US"]["BackMsg"]="返回";
langs["en_US"]["DrawScopeMsg"]="繪製區域範圍";
langs["en_US"]["MeterMsg"]="公尺";
langs["en_US"]["MSG0032"]="確定刪除?";
langs["en_US"]["MSG0033"]="sure,Save?";
langs["en_US"]["MSG0042"]="每個店家僅限儲存一筆範圍資料";
langs["en_US"]["MSG0043"]="請先繪製區域";
langs["en_US"]["MSG0044"]="請至少新增3點";
langs["en_US"]["MSG0051"]="請先開啟外送功能";
langs["en_US"]["MSG0052"]="與原點距離";
//用法： 
var lang = "zh_CH";
//var lang = "en_US";
//alert(langs[lang]["keyName"]); 

var ClickDrawMsg = langs[lang]["ClickDrawMsg"];
var ClickDrawStartDotMsg = langs[lang]["ClickDrawStartDotMsg"];
var ClickDrawEndDotMsg = langs[lang]["ClickDrawEndDotMsg"];
var CanStillBeAddedMsg = langs[lang]["CanStillBeAddedMsg"];
var StartDotMsg = langs[lang]["StartDotMsg"];
var EndDotMsg = langs[lang]["EndDotMsg"];
var DotMsg = langs[lang]["DotMsg"];
var OrClickCToCloseMsg =langs[lang]["OrClickCToCloseMsg"];
var IrregularMsg = langs[lang]["IrregularMsg"];
var CircleMsg = langs[lang]["CircleMsg"];
var SquareMsg = langs[lang]["SquareMsg"];
var BackMsg = langs[lang]["BackMsg"];
var DrawScopeMsg = langs[lang]["DrawScopeMsg"];
var MeterMsg = langs[lang]["MeterMsg"];
var MSG0032 = langs[lang]["MSG0032"];
var MSG0033 = langs[lang]["MSG0033"];
var MSG0042 = langs[lang]["MSG0042"];
var MSG0043 = langs[lang]["MSG0043"];
var	MSG0044 = langs[lang]["MSG0044"];
var	MSG0051 = langs[lang]["MSG0051"];
var	MSG0052 = langs[lang]["MSG0052"];
var dblShopInitLat = 0;
var dblShopInitLng = 0;


function mapOnLoad(reloadMap) {
		var gMarkerShopIcon;
		var strTemp = "";
		var gWinInfo;
		var htmlMapObj;
		var gObjLatLng;
		var options;
		//var strAllScopeData = "";
		//var dblShopInitLat = 0;
		//var dblShopInitLng = 0;
		var strMapName = "";
		var gObjLatLngWorldCenter;
		//strAllScopeData = getInitData('strAllScopeData');
		dblShopInitLat = getInitData('dblShopInitLat');
		dblShopInitLng = getInitData('dblShopInitLng');
		strMapName = getInitData('strMapName');
		if (dblShopInitLat != 0 && dblShopInitLng != 0) {
			dblInitLat = dblShopInitLat;
			dblInitLng = dblShopInitLng;
		}
		gObjLatLng = new google.maps.LatLng(dblInitLat, dblInitLng);
		gObjLatLngWorldCenter = new google.maps.LatLng(0, 0);
		options = {
			'zoom': intZoom,
			'center': gObjLatLng,
			'mapTypeId': google.maps.MapTypeId.ROADMAP
		};
		map = null;
		$('#map').unbind('mousemove');
		$('#map').unbind('mouseup');
		$(document).unbind('keydown');
		htmlMapObj = document.getElementById(strMapName);
		map = new google.maps.Map(htmlMapObj, options);
		strTemp = "<div class='shopIconInfoDiv'><font class='shopIconInfoText'>" + getInitData("strCoName") + "</font></div>";
		gWinInfo = createWinInfo(strTemp);
		gMarkerShopIcon = createShopIconMarker(imgMapIcon, gObjLatLng, map);
		gWinInfo.open(map, gMarkerShopIcon);
		google.maps.event.addListener(gMarkerShopIcon, 'click', function () {
			gWinInfo.open(map, gMarkerShopIcon);
		});
		dstDiv = new MyOverLayer(map, "0", gObjLatLngWorldCenter, "dstDiv");
		tipsDiv = new MyOverLayer(map, "", gObjLatLngWorldCenter, "tipsDiv");
		google.maps.event.addListener(map, "dragstart", function (event) {
			if (strDrawStatus != 'hand') {
				strDrawStatusTemp = strDrawStatus;
				strDrawStatus = 'hand';
			}
		});
		google.maps.event.addListener(map, "mousemove", function (event) {
			gNowLatLng = event.latLng;
			floatMouseLng = gNowLatLng.lng();
			floatMouseLat = gNowLatLng.lat();
			mouseMove();
			setMsgToText();
		});
		google.maps.event.addListener(map, "mouseup", function (event) {
			gNowLatLng = event.latLng;
			floatMouseLng = gNowLatLng.lng();
			floatMouseLat = gNowLatLng.lat();
			mouseMove();
			setMsgToText();
			leftMouseDown();
			if (strDrawStatusTemp != 'hand') {
				strDrawStatus = strDrawStatusTemp;
				strDrawStatusTemp = 'hand';
			}
		});
		$(document).keydown(function (event) {
			if (chkKeyWord(event, 'c')) 
				closeIrregular();
		});
		init_MapMarker(dblInitLat, dblInitLng);
		initToolbarStatus();
		$('#drawTool').hide();
		//$('#saveScopeSpan').hide();
		//$('#delScopeSpan').hide();
		if (!reloadMap){
			//eval(strAllScopeData);
			redrawScopeData();
		}else if (sNowClickScopeObj != null){
			sNowClickScopeObj.polygon.setMap(map);
		}
			
		gObjLatLngWorldCenter = null;
		gObjLatLng = null;
		htmlMapObj = null;
	}
function chkFncStatus() {
	/*
		if (map != null && map.getProjection()) {
			if (getInitData("strFncOpenOrClose") == "N") {
				alert(MSG0051);
				redirect("/webSetAction.do");
			}
		} else 
				setTimeout("chkFncStatus()", 400);
				*/
	}
function setMsgToText() {
		if (sNowClickScopeObj == null || sNowClickScopeObj.id == -1) {
			if (strDrawStatus == "drawSquare") {
				if (mySquare.strClickStatus == "endDraw") {
					aryStrLatLngTxt[aryStrLatLngTxt.length] = MSG0052;
					aryStrLatLngTxt[aryStrLatLngTxt.length] = calDistanceFourDot(floatMouseLat, floatMouseLng, mySquare.gObjMarkerFirst.getPosition().lat(), mySquare.gObjMarkerFirst.getPosition().lng());
					aryStrLatLngTxt[aryStrLatLngTxt.length] = " ";
					aryStrLatLngTxt[aryStrLatLngTxt.length] = MeterMsg;
					strLatLngTxt = aryStrLatLngTxt.join("");
					dstDiv.setText(strLatLngTxt);
					aryStrLatLngTxt = null;
					aryStrLatLngTxt = new Array();
				}
			} else if (strDrawStatus == "drawCircle") {
				if (myCircle.strClickStatus == "endDraw") {
					aryStrLatLngTxt[aryStrLatLngTxt.length] = MSG0052;
					aryStrLatLngTxt[aryStrLatLngTxt.length] = calDistanceFourDot(floatMouseLat, floatMouseLng, myCircle.gObjMarkerFirst.getPosition().lat(), myCircle.gObjMarkerFirst.getPosition().lng());
					aryStrLatLngTxt[aryStrLatLngTxt.length] = " ";
					aryStrLatLngTxt[aryStrLatLngTxt.length] = MeterMsg;
					strLatLngTxt = aryStrLatLngTxt.join("");
					dstDiv.setText(strLatLngTxt);
					aryStrLatLngTxt = null;
					aryStrLatLngTxt = new Array();
				}
			} else if (strDrawStatus == "drawIrregular") {
				if (myIrregular.strClickStatus == "startDraw") {
					if (myIrregular.aryMarker != null && myIrregular.aryMarker.length > 0 && myIrregular.aryMarker.length < intIrrDotTotal) {
						aryStrLatLngTxt[aryStrLatLngTxt.length] = MSG0052;
						aryStrLatLngTxt[aryStrLatLngTxt.length] = calDistanceFourDot(floatMouseLat, floatMouseLng, myIrregular.aryMarker[0].getPosition().lat(), myIrregular.aryMarker[0].getPosition().lng());
						aryStrLatLngTxt[aryStrLatLngTxt.length] = " ";
						aryStrLatLngTxt[aryStrLatLngTxt.length] = MeterMsg;
						strLatLngTxt = aryStrLatLngTxt.join("");
						dstDiv.setText(strLatLngTxt);
						aryStrLatLngTxt = null;
						aryStrLatLngTxt = new Array();
					}
				}
			}
		}
		dstDiv.move(gNowLatLng);
		if (strDrawStatus != "hand") 
			tipsDiv.move(gNowLatLng);
	}
function setTextToTips() {
		if (strDrawStatus == 'drawSquare') strTips = ClickDrawStartDotMsg;
		else if (strDrawStatus == 'drawCircle') strTips = ClickDrawStartDotMsg;
		else if (strDrawStatus == 'drawIrregular') strTips = CanStillBeAddedMsg + (intIrrDotTotal - 1) + DotMsg;
		if (tipsDiv.getText() != strTips) tipsDiv.setText(strTips);
		if (!tipsDiv.isVisible()) {
			tipsDiv.move(gNowLatLng);
			tipsDiv.show();
		}
	}
function initToolbarStatus() {
		setStatus('hand', 'hand');
		chgDrawTlsImg();
		tipsDiv.hide();
		dstDiv.hide();
		dstDiv.setText("0");
	}
function setStatus(tdName, nowClick) {
		var tdObj = null;
		
		tdObj = document.getElementById(tdName);
		strDrawStatus = nowClick;
		tdObj = null;
	}

function chgDrawTlsImg() {
		$('#hand').children('img:eq(0)').attr('src', imgGMove);
		$('#drawSquare').children('img:eq(0)').attr('src', imgGBtn);
		$('#drawCircle').children('img:eq(0)').attr('src', imgGBtn2);
		$('#drawIrregular').children('img:eq(0)').attr('src', imgGBtn3);
		if (strDrawStatus == 'hand') {
			strTips = ClickDrawStartDotMsg;
			$('#hand').children('img:eq(0)').attr('src', imgGMoveOnclick);
		} else if (strDrawStatus == 'drawSquare') {
			strTips = ClickDrawStartDotMsg;
			$('#drawSquare').children('img:eq(0)').attr('src',imgGBtn1Onclick);
		} else if (strDrawStatus == 'drawCircle') {
			strTips = ClickDrawStartDotMsg;
			$('#drawCircle').children('img:eq(0)').attr('src',imgGBtn2Onclick);
		} else if (strDrawStatus == 'drawIrregular') {
			strTips = CanStillBeAddedMsg + (intIrrDotTotal - 1) + DotMsg;
			$('#drawIrregular').children('img:eq(0)').attr('src', imgGBtn3Onclick);
		}
	}
function checkDraw(strMsg) {
		var bl = true;
		if (sNowClickScopeObj != null && sNowClickScopeObj.id != -1) {
			alert(strMsg);
			bl = false;
			initToolbarStatus();
		}
		return bl;
	}
function saveScope() {
		var bl = false;
		if (sNowClickScopeObj != null) {
			bl = confirm(MSG0033);
			if (bl) {
				if (sNowClickScopeObj.type == "Square") 
					saveSquareScope();
				else if (sNowClickScopeObj.type == "Circle") 
					saveCircleScope();
				else if (sNowClickScopeObj.type == "Irregular") 
					saveIrregularScope();
				//setElObjDisable("saveScope", false);
				//chgDabImg("saveScope", false);
			}
		} else 
			alert(MSG0043);
	}
function directSaveScope() {
	var bl = false;
	var distanceVar =$("#distance").val();
	if (checkDistance(distanceVar) == 1) {
		alert("半徑，請輸入整數!");
		tmpCircle();//重新顯示地圖
	}else{
		tmpCircle();//重新顯示地圖
	//if (sNowClickScopeObj != null) {
		bl = confirm(MSG0033);
		if (bl) {
			directSaveCircleScope( $("#distance").val() );
			//setElObjDisable("saveScope", false);
			//chgDabImg("saveScope", false);
		}
	//mapOnLoad();
	}
}
function tmpCircle(){
	var gObjRoundLatLng;
	//gObjRoundLatLng = new google.maps.LatLng(floatMouseLat, floatMouseLng);
	gObjRoundLatLng = new google.maps.LatLng(dblShopInitLat, dblShopInitLng);
	//if (myCircle.strClickStatus == "startDraw") {
		myCircle.gObjMarkerFirst.setPosition(gObjRoundLatLng);
		if (!myCircle.gObjMarkerFirst.getVisible()) 
			myCircle.gObjMarkerFirst.setVisible(true);
		myCircle.strClickStatus = "endDraw";
		strTips = ClickDrawEndDotMsg;
		tipsDiv.setText(strTips);
		dstDiv.show();
	//} else if (myCircle.strClickStatus == "endDraw") {
		myCircle.gObjMarkerLast.setPosition(gObjRoundLatLng);
		myCircle.gObjMarkerFirst.setVisible(false);
		myCircle.gObjMarkerLast.setVisible(false);
		myCircle.id = -1;
		myCircle.strClickStatus = "startDraw";
		myCircle.floatCenter = myCircle.gObjMarkerFirst.getPosition().lat() + "," + myCircle.gObjMarkerFirst.getPosition().lng();
		//myCircle.floatCenter = floatMouseLat + "," + floatMouseLng;
		//myCircle.floatCenter = "24,24";
		myCircle.floatTan = myCircle.gObjMarkerLast.getPosition().lat() + "," + myCircle.gObjMarkerLast.getPosition().lng();
		//myCircle.floatTan = floatMouseLat + "," + floatMouseLng;
		//myCircle.floatDistance = calDistance(myCircle.gObjMarkerFirst.getPosition(), myCircle.gObjMarkerLast.getPosition());
		myCircle.floatDistance = $("#distance").val();
		if (drawCircle(myCircle)) 
			setObjToNowClick(myCircle);
		initToolbarStatus();
	//}
}

function checkDistance(keyValue) {
	var dataCount = 0;
	for (var i = 0; i < keyValue.length; i++) {
		if (keyValue.charAt(i) < '0' || keyValue.charAt(i) > '9') {
			dataCount += 1;
			break;
		}
	}
	if (dataCount > 0) {
		return 1;
	} else {
		return 0;
	}
}
function delScope() {
		var bl = false;
		if (sNowClickScopeObj != null) {
			//bl = confirm(MSG0032);
			//if (bl) {
			if (true) {
				if (sNowClickScopeObj.id != -1) {
					if (sNowClickScopeObj.type == "Square") 
						delDBSquareScope();
					else if (sNowClickScopeObj.type == "Circle") 
						delDBCircleScope();
					else if (sNowClickScopeObj.type == "Irregular") 
						delDBIrregularScope();
				} else {
					removeObjFromMap(sNowClickScopeObj.polygon);
					sNowClickScopeObj = null;
				}
				//setElObjDisable("saveScope", false);
				//chgDabImg("saveScope", false);
				//setElObjDisable("delScope", false);
				//chgDabImg("delScope", false);
			}
		} else {
			//alert(MSG0043);
			}
	}
function chkStatus(tdName, nowClick) {
		if (checkDraw(MSG0042)) {
			initDrawStatus();
			setStatus(tdName, nowClick);
			chgDrawTlsImg();
		}
	}
function initDrawStatus() {
		if (sNowClickScopeObj != null && sNowClickScopeObj.polygon != null && sNowClickScopeObj.id == -1) {
			removeObjFromMap(sNowClickScopeObj.polygon);
			sNowClickScopeObj = null;
			//setElObjDisable("saveScope", false);
			//chgDabImg("saveScope", false);
			//setElObjDisable("delScope", false);
			//chgDabImg("delScope", false);
		}
		mySquare.gObjMarkerFirst.setVisible(false);
		mySquare.gObjMarkerLast.setVisible(false);
		mySquare.strClickStatus = "startDraw";
		myCircle.gObjMarkerFirst.setVisible(false);
		myCircle.gObjMarkerLast.setVisible(false);
		myCircle.strClickStatus = "startDraw";
		for (var i = 0; i < myIrregular.aryMarker.length; i++) 
			removeObjFromMap(myIrregular.aryMarker[i]);
		for (var i = 0; i < myIrregular.aryLine.length; i++) 
			removeObjFromMap(myIrregular.aryLine[i]);
		myIrregular.aryMarker = null;
		myIrregular.aryLine = null;
		myIrregular.aryMarker = new Array();
		myIrregular.aryLine = new Array();
		myIrregular.curMarker.setVisible(false);
		myIrregular.strClickStatus = "startDraw";
	}
function leftMouseDown() {
		//var mouseX, mouseY;
		var gObjRoundLatLng;
		//var intAryIrrDotLen;
			if (sNowClickScopeObj == null || sNowClickScopeObj.id == -1) {
				gObjRoundLatLng = new google.maps.LatLng(floatMouseLat, floatMouseLng);
				if (strDrawStatus == "drawSquare") {
					if (mySquare.strClickStatus == "startDraw") {
						mySquare.gObjMarkerFirst.setPosition(gObjRoundLatLng);
						if (!mySquare.gObjMarkerFirst.getVisible()) 
							mySquare.gObjMarkerFirst.setVisible(true);
						mySquare.strClickStatus = "endDraw";
						strTips = ClickDrawEndDotMsg;
						tipsDiv.setText(strTips);
						dstDiv.show();
					} else if (mySquare.strClickStatus == "endDraw") {
						var aryGobjPoint;
						mySquare.gObjMarkerLast.setPosition(gObjRoundLatLng);
						mySquare.gObjMarkerFirst.setVisible(false);
						mySquare.gObjMarkerLast.setVisible(false);
						mySquare.id = -1;
						mySquare.strClickStatus = "startDraw";
						aryGobjPoint = createPoint(mySquare.gObjMarkerFirst.getPosition().lng(), mySquare.gObjMarkerFirst.getPosition().lat(), mySquare.gObjMarkerLast.getPosition().lng(), mySquare.gObjMarkerLast.getPosition().lat());
						if (drawSquareOnMap(aryGobjPoint, mySquare)) 
							setObjToNowClick(mySquare);
						aryGobjPoint = null;
						initToolbarStatus();
					}
				} else if (strDrawStatus == "drawCircle") {
					if (myCircle.strClickStatus == "startDraw") {
						myCircle.gObjMarkerFirst.setPosition(gObjRoundLatLng);
						if (!myCircle.gObjMarkerFirst.getVisible()) 
							myCircle.gObjMarkerFirst.setVisible(true);
						myCircle.strClickStatus = "endDraw";
						strTips = ClickDrawEndDotMsg;
						tipsDiv.setText(strTips);
						dstDiv.show();
					} else if (myCircle.strClickStatus == "endDraw") {
						myCircle.gObjMarkerLast.setPosition(gObjRoundLatLng);
						myCircle.gObjMarkerFirst.setVisible(false);
						myCircle.gObjMarkerLast.setVisible(false);
						myCircle.id = -1;
						myCircle.strClickStatus = "startDraw";
						myCircle.floatCenter = myCircle.gObjMarkerFirst.getPosition().lat() + "," + myCircle.gObjMarkerFirst.getPosition().lng();
						//myCircle.floatCenter = "24,24";
						myCircle.floatTan = myCircle.gObjMarkerLast.getPosition().lat() + "," + myCircle.gObjMarkerLast.getPosition().lng();
						myCircle.floatDistance = calDistance(myCircle.gObjMarkerFirst.getPosition(), myCircle.gObjMarkerLast.getPosition());
						if (drawCircle(myCircle)) 
							setObjToNowClick(myCircle);
						initToolbarStatus();
					}
				} else if (strDrawStatus == "drawIrregular") {
					if (myIrregular.strClickStatus == "startDraw") {
						if (myIrregular.aryMarker != null && myIrregular.aryMarker.length < intIrrDotTotal) {
							myIrregular.aryMarker[myIrregular.aryMarker.length] = createMarker(gObjRoundLatLng.lat(), gObjRoundLatLng.lng(), false);
							if (!myIrregular.aryMarker[myIrregular.aryMarker.length - 1].getVisible()) myIrregular.aryMarker[myIrregular.aryMarker.length - 1].setVisible(true);
								setObjToMap(myIrregular.aryMarker[myIrregular.aryMarker.length - 1]);
							if (myIrregular.aryMarker.length == 9) {
								myIrregular.aryMarker[myIrregular.aryMarker.length] = createMarker(myIrregular.aryMarker[0].getPosition().lat(), myIrregular.aryMarker[0].getPosition().lng(), false);
								tipsDiv.hide();
							}
							strTips = CanStillBeAddedMsg + (intIrrDotTotal - myIrregular.aryMarker.length - 1) + DotMsg + "," + OrClickCToCloseMsg;
							tipsDiv.setText(strTips);
							dstDiv.show();
							addLineToWeb();
							if (myIrregular.aryMarker.length >= 2) {
								var intLength = 0;
								intLength = myIrregular.aryMarker.length - 1;
								if (myIrregular.aryMarker[0].getPosition().lat() == myIrregular.aryMarker[intLength].getPosition().lat() && myIrregular.aryMarker[0].getPosition().lng() == myIrregular.aryMarker[intLength].getPosition().lng()) {
									myIrregular.strClickStatus = "endDraw";
									myIrregular.id = -1;
									if (drawIrregular(myIrregular)) {
										setObjToNowClick(myIrregular);
										for (var i = 0; i < myIrregular.aryMarker.length; i++) 
											removeObjFromMap(myIrregular.aryMarker[i]);
										for (var i = 0; i < myIrregular.aryLine.length; i++) 
											removeObjFromMap(myIrregular.aryLine[i]);
										myIrregular.curMarker.setVisible(false);
									}
									initToolbarStatus();
								}
							}
						}
					} else if (myIrregular.strClickStatus == "endDraw") {
						if (myIrregular != null && myIrregular.polygon != null) {
							removeObjFromMap(myIrregular.polygon);
							myIrregular.aryMarker = null;
							myIrregular.aryLine = null;
							myIrregular.aryMarker = new Array();
							myIrregular.aryLine = new Array();
							myIrregular.strClickStatus = "startDraw";
							leftMouseDown(e, gLatLng);
						}
					}
				}
			}
		gObjRoundLatLng = null;
}
function mouseMove() {
		//var mouseX, mouseY;
		if (map.getProjection()) {
			if (strDrawStatus == "drawSquare" || strDrawStatus == "drawCircle" || strDrawStatus == "drawIrregular") map.setOptions({
				draggableCursor: 'crosshair'
			});
			else if (strDrawStatus == "hand") map.setOptions({
				draggableCursor: 'move'
			});
		}
	}
function closeIrregular() {
		if (strDrawStatus == "drawIrregular") {
			if (myIrregular.strClickStatus == "startDraw") {
				if (myIrregular.aryMarker != null && myIrregular.aryMarker.length < intIrrDotTotal) {
					if (myIrregular.aryMarker.length < 3) {
						strTips = MSG0044;
						tipsDiv.setText(strTips);
					} else {
						var gObjTemp;
						//var intLength = 0;
						gObjTemp = myIrregular.aryMarker[0];
						myIrregular.aryMarker[myIrregular.aryMarker.length] = createMarker(gObjTemp.getPosition().lat(), gObjTemp.getPosition().lng(), false);
						myIrregular.strClickStatus = "endDraw";
						myIrregular.id = -1;
						if (drawIrregular(myIrregular)) {
							setObjToNowClick(myIrregular);
							for (var i = 0; i < myIrregular.aryMarker.length; i++)
								removeObjFromMap(myIrregular.aryMarker[i]);
							for (var i = 0; i < myIrregular.aryLine.length; i++)
								removeObjFromMap(myIrregular.aryLine[i]);
							myIrregular.curMarker.setVisible(false);
						}
						initToolbarStatus();
					}
				}
			}
		}
	}
function lockDot(obj, gObjRoundLatLng) {
		obj.setPosition(gObjRoundLatLng);
	}
function setObjToNowClick(obj) {
		sNowClickScopeObj = obj;
		//setElObjDisable("delScope", true);
		//chgDabImg("delScope", true);
		if (sNowClickScopeObj.id == -1) {
			//setElObjDisable("saveScope", true);
			//chgDabImg("saveScope", true);
		} else {
			//setElObjDisable("saveScope", false);
			//chgDabImg("saveScope", false);
		}
		google.maps.event.addListener(obj.polygon, 'click', function () {
			sNowClickScopeObj = obj;
		});
	}
function init_MapMarker(poiY, poiX) {
		var gObjMarker;
		if (mySquare.gObjMarkerFirst != null) {
			removeObjFromMap(mySquare.gObjMarkerFirst);
			removeObjFromMap(mySquare.gObjMarkerLast);
			mySquare.gObjMarkerFirst = null;
			mySquare.gObjMarkerLast = null;
		}
		gObjMarker = createMarker(poiY, poiX, false);
		mySquare.gObjMarkerFirst = gObjMarker;
		mySquare.gObjMarkerFirst.setVisible(false);
		setObjToMap(mySquare.gObjMarkerFirst);
		gObjMarker = null;
		gObjMarker = createMarker(poiY, poiX, false);
		mySquare.gObjMarkerLast = gObjMarker;
		mySquare.gObjMarkerLast.setVisible(false);
		setObjToMap(mySquare.gObjMarkerLast);
		gObjMarker = null;
		if (myCircle.gObjMarkerFirst != null) {
			removeObjFromMap(myCircle.gObjMarkerFirst);
			removeObjFromMap(myCircle.gObjMarkerLast);
			myCircle.gObjMarkerFirst = null;
			myCircle.gObjMarkerLast = null;
		}
		gObjMarker = createMarker(poiY, poiX, false);
		myCircle.gObjMarkerFirst = gObjMarker;
		myCircle.gObjMarkerFirst.setVisible(false);
		setObjToMap(myCircle.gObjMarkerFirst);
		gObjMarker = null;
		gObjMarker = createMarker(poiY, poiX, false);
		myCircle.gObjMarkerLast = gObjMarker;
		myCircle.gObjMarkerLast.setVisible(false);
		setObjToMap(myCircle.gObjMarkerLast);
		gObjMarker = null;
		if (myIrregular.curMarker != null) {
			removeObjFromMap(myIrregular.curMarker);
			myIrregular.curMarker = null;
		}
		gObjMarker = createMarker(poiY, poiX, false);
		myIrregular.curMarker = gObjMarker;
		myIrregular.curMarker.setVisible(false);
		setObjToMap(myIrregular.curMarker);
		gObjMarker = null;
	}
function hiddenAllDiv() {	
		/*if ($('#header').is(':hidden')) {
			$('#header').show();
			$('#content_wrap_top').show();
			$('#cont_left').show();
			$('#footer').show('slow');
			$('#clear_both').show();
			
			$('#wrp').removeClass("bigMap").addClass('wrapper');
			$('#cnt_wrap').removeClass("bigMap").addClass('content_wrap');					
			$('#cnt').removeClass("bigMap").addClass('content');
			$('#cnt_right').removeClass("bigMap").addClass('cont_right');
			
			$('#map').height(400);
			$('#map').width(530);
			$('#visibleBtn').val(DrawScopeMsg);
			$('#visibleBtn').attr("src", imgPaintArea);
			initToolbarStatus();
			initDrawStatus();
		} else {
			$('#header').hide('slow');
			$('#content_wrap_top').hide();
			$('#cont_left').hide();
			$('#footer').hide();
			$('#clear_both').hide();
			
			$('#wrp').removeClass("wrapper").addClass('bigMap');
			$('#cnt_wrap').removeClass("content_wrap").addClass('bigMap');
			$('#cnt').removeClass("content").addClass('bigMap');
			$('#cnt_right').removeClass("cont_right").addClass('bigMap');
			
			$('#map').height($(window).height());
			$('#map').width($(window).width());
			$('#visibleBtn').val(BackMsg);
			$('#visibleBtn').attr("src", imgReback);
			
			mapOnLoad(true);
		}*/
		$('#map').height(600);
		//$('#map').width(900);
		if ($('#drawTool').is(':hidden')) {
			$('#drawTool').show();
			//$('#saveScopeSpan').show();
			//$('#delScopeSpan').show();
		} else {
			$('#drawTool').hide();
			//$('#saveScopeSpan').hide();
			//$('#delScopeSpan').hide();
			closeHelp();
		}
	}
function chgDabImg(strClickName, blStatus) {

		if (strClickName == "saveScope") {
			if (blStatus) 
				$('#saveScope').attr("src", imgSaveScope);
			else 
				$('#saveScope').attr("src", ImgSaveScopeDab);
		} else if (strClickName == "delScope") {
			if (blStatus) 
				$('#delScope').attr("src", ImgDelScope);
			else 
				$('#delScope').attr("src", ImgDelScopeDab);
		}
	}