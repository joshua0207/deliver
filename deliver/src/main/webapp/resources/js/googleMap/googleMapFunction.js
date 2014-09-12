function createWinInfo(strTemp) {
	var infowindow = new google.maps.InfoWindow({
		content: strTemp
	});
	return infowindow;
}
function createShopIconMarker(strIconPath, gObjLatLng, map) {
	var markerOptions;
	var gObjMarker;
	markerOptions = {
		icon: strIconPath,
		position: gObjLatLng,
		map: map
	};
	gObjMarker = new google.maps.Marker(markerOptions);
	return gObjMarker;
}
function createMarker(floatLat, floatLng, booleanDraggable) {
	var gObjMarker;
	var gObjLatLng;
	gObjLatLng = new google.maps.LatLng(floatLat, floatLng);
	gObjMarker = new google.maps.Marker({
		position: gObjLatLng,
		draggable: booleanDraggable
	});
	gObjLatLng = null;
	return gObjMarker;
}
function setObjToMap(obj) {
	obj.setMap(map);
}
function removeObjFromMap(obj) {
	if (obj != null) {
		obj.setMap(null);
		obj = null;
	}
}
function moveMapCenter(latObjName, lngObjName) {
	var lat;
	var lng;
	var gObjLatLng;
	lat = getElObjValue(latObjName);
	lng = getElObjValue(lngObjName);
	gObjLatLng = new google.maps.LatLng(lat, lng);
	map.panTo(gObjLatLng);
	gObjLatLng = null;
}
function checkZoom() {
	if (map.getZoom() < intZoom) 
		map.setZoom(intZoom);
}
function createCircle(gObjLatLng, fltRadius, intWidth) {
	//將轉入的半徑數值強制轉為 float
	var fFltRadius = parseFloat(fltRadius);
                                     	
	var gObjCircle = new google.maps.Circle({
		center: gObjLatLng,
		clickable: true,
		radius: fFltRadius,
		strokeWeight: 1
	});
	return gObjCircle;
}
function createPolygon(aryPoint, strColor, flotWeight, floatOpacity, strWebAreaColor, intOpacity) {
	var gObjPolygon;
	gObjPolygon = new google.maps.Polygon({
		paths: aryPoint,
		strokeColor: strColor,
		strokeWeight: flotWeight,
		strokeOpacity: floatOpacity,
		fillColor: strWebAreaColor,
		fillOpacity: intOpacity
	});
	return gObjPolygon;
}
function createLine(aryObj, strColor, strOpacity, strWeight) {
	var gObjLine = new google.maps.Polyline({
		path: aryObj,
		strokeColor: strColor,
		strokeOpacity: strOpacity,
		strokeWeight: strWeight
	});
	return gObjLine;
}