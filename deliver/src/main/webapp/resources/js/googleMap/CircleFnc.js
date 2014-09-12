function calDistance(objLDot, objFDot) {
	var floatTemp;
	var R = 6371;
	var dLat = (objLDot.lat() - objFDot.lat()) * Math.PI / 180;
	var dLon = (objLDot.lng() - objFDot.lng()) * Math.PI / 180;
	var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(objFDot.lat() * Math.PI / 180) * Math.cos(objLDot.lat() * Math.PI / 180) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	floatTemp = R * c * 1000;
	return floatTemp;
}
function calDistanceFourDot(objLLat, objLLng, objFLat, objFLng) {
	var floatTemp;
	var R = 6371;
	var dLat = (objLLat - objFLat) * Math.PI / 180;
	var dLon = (objLLng - objFLng) * Math.PI / 180;
	var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(objFLat * Math.PI / 180) * Math.cos(objLLat * Math.PI / 180) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	floatTemp = R * c * 1000;
	return floatTemp;
}
function drawCircle(obj) {
	var gObjCircle = createCircle(obj.gObjMarkerFirst.getPosition(), obj.floatDistance, 1);
	var blResult = false;
	obj.polygon = gObjCircle;
	obj.polygon.setMap(map);
	gObjCircle = null;
	blResult = true;
	return blResult;
}
function addCircleScopeFromDB(obj) {
	var gObjCircle = createCircle(obj.gObjMarkerFirst.getPosition(), obj.floatDistance, 1);
	obj.polygon = gObjCircle;
	obj.polygon.setMap(map);
	gObjCircle = null;
}
function saveCircleScope() {
	
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
	
	$.ajax({
		type:"POST",
		url:"addServiceScopeCircle.action",
		data:{
			circleCenter:sNowClickScopeObj.floatCenter,
			circleTan:sNowClickScopeObj.floatTan,
			distance:sNowClickScopeObj.floatDistance,
			dblMaxLng:sNowClickScopeObj.dblMaxLng,
			dblMinLng:sNowClickScopeObj.dblMinLng,
			dblMaxLat:sNowClickScopeObj.dblMaxLat,
			dblMinLat:sNowClickScopeObj.dblMinLat,
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

function directSaveCircleScope( dstnc ){
	
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

	
	$.ajax({
		type:"POST",
		url:"servicescope/shop/addmapscope",
		data:{
			circleCenter:sNowClickScopeObj.floatCenter,
			//circleCenter:dblShopInitLat+","+dblShopInitLng,
			circleTan:sNowClickScopeObj.floatTan,
			//circleTan:dblShopInitLat+","+dblShopInitLng,
			distance:sNowClickScopeObj.floatDistance,
			//distance:dstnc,
			maxLng:sNowClickScopeObj.dblMaxLng,
			minLng:sNowClickScopeObj.dblMinLng,
			maxLat:sNowClickScopeObj.dblMaxLat,
			minLat:sNowClickScopeObj.dblMinLat,
		},
		dataType:"json",
		error: function(data){
			sNowClickScopeObj.id = -1;
			alert(data.msg);
		},
		success: function(data){			
			sNowClickScopeObj.id = data.shopId;
			//setElObjDisable("saveScope", false);
			//alert(data.msg);
			$('#msg').html(data.msg);
		}
	});
	
}

//刪除地圖
function delDBCircleScope() {
	
//	$.ajax({
//		type:"POST",
//		url:"delServiceScopeCircle.action",
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
//			alert(data.msg);
//		}
//	});
}