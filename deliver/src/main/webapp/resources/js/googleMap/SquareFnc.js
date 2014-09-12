function drawSquareOnMap(aryGobjPoint, obj) {
	var gObjPolygon;
	//var intAryLength = 0;
	var blResult = false;
	gObjPolygon = createPolygon(aryGobjPoint, obj.color, 1, 0.8, obj.color, .2);
	obj.polygon = gObjPolygon;
	obj.polygon.setMap(map);
	gObjPolygon = null;
	blResult = true;
	return blResult;
}
function addSquareScopeFromDB(newSquare) {
	var aryGobjPoint;
	aryGobjPoint = createPoint(newSquare.gObjMarkerFirst.getPosition().lng(), newSquare.gObjMarkerFirst.getPosition().lat(), newSquare.gObjMarkerLast.getPosition().lng(), newSquare.gObjMarkerLast.getPosition().lat());
	drawSquareOnMap(aryGobjPoint, newSquare);
	aryGobjPoint = null;
	newSquare = null;
}
function saveSquareScope() {
	//alert(sNowClickScopeObj.gObjMarkerFirst.getPosition().lat());
	$.ajax({
		type:"POST",
		url:"addServiceScopeSquare.action",
		data:{
			firstDotLat:sNowClickScopeObj.gObjMarkerFirst.getPosition().lat(),
			firstDotLng:sNowClickScopeObj.gObjMarkerFirst.getPosition().lng(),
			lastDotLat:sNowClickScopeObj.gObjMarkerLast.getPosition().lat(),
			lastDotLng:sNowClickScopeObj.gObjMarkerLast.getPosition().lng(),
		},
		dataType:"json",
		error: function(data){
			sNowClickScopeObj.id = -1;
			alert(data.msg);
		},
		success: function(data){			
			sNowClickScopeObj.id = data.shopId;
			//setElObjDisable("saveScope", false);
			alert(data.msg);
		}
	});
}
function delDBSquareScope() {

//	$.ajax({
//		type:"POST",
//		url:"delServiceScopeSquare.action",
//		data:{
//			
//		},
//		dataType:"json",
//		error: function(data){
//			alert(data.msg);
//		},
//		success: function(data){			
			//setElObjDisable("delScope", false);
			removeObjFromMap(sNowClickScopeObj.polygon);
			sNowClickScopeObj = null;
			//alert(data.msg);
//		}
//	});
}
function createPoint(sLastDotX, sFirstDotY, sFirstDotX, sLastDotY) {
	var aryGobjPoint = new Array();
	var gObjLatLngTL, gObjLatLngTR, gObjLatLngBR, gObjLatLngBL;
	gObjLatLngTL = new google.maps.LatLng(sFirstDotY, sLastDotX);
	gObjLatLngTR = new google.maps.LatLng(sFirstDotY, sFirstDotX);
	gObjLatLngBR = new google.maps.LatLng(sLastDotY, sFirstDotX);
	gObjLatLngBL = new google.maps.LatLng(sLastDotY, sLastDotX);
	aryGobjPoint.push(gObjLatLngTL);
	aryGobjPoint.push(gObjLatLngTR);
	aryGobjPoint.push(gObjLatLngBR);
	aryGobjPoint.push(gObjLatLngBL);
	gObjLatLngTL = null;
	gObjLatLngTR = null;
	gObjLatLngBR = null;
	gObjLatLngBL = null;
	return aryGobjPoint;
}