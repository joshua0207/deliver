function addLineToWeb() {
	var gObjLine = null;
	var gObjLatLng = null;
	var aryTemp = new Array();
	if (myIrregular.aryMarker.length > 1) {
		var intLength = 0;
		intLength = myIrregular.aryMarker.length;
		gObjLatLng = new google.maps.LatLng(myIrregular.aryMarker[intLength - 2].getPosition().lat(), myIrregular.aryMarker[intLength - 2].getPosition().lng());
		aryTemp.push(gObjLatLng);
		gObjLatLng = null;
		gObjLatLng = new google.maps.LatLng(myIrregular.aryMarker[intLength - 1].getPosition().lat(), myIrregular.aryMarker[intLength - 1].getPosition().lng());
		aryTemp.push(gObjLatLng);
		gObjLatLng = null;
		gObjLine = createLine(aryTemp, myIrregular.color, 1, 1);
		myIrregular.aryLine.push(gObjLine);
		setObjToMap(myIrregular.aryLine[myIrregular.aryLine.length - 1]);
	}
	aryTemp = null;
	gObjLine = null;
	gObjLatLng = null;
}
function drawIrregular(obj) {
	var bl = false;
	if (obj.aryMarker.length > 2) {		
		var aryGobjPoint = new Array();
		var gObjIrregular;
		var gObjLatLng;
		
		for (var i = 0; i < obj.aryMarker.length; i++) {
			
			gObjLatLng = new google.maps.LatLng(obj.aryMarker[i].getPosition().lat(), obj.aryMarker[i].getPosition().lng());
			aryGobjPoint.push(gObjLatLng);
			
			gObjLatLng = null;
		}
		gObjIrregular = createPolygon(aryGobjPoint, obj.color, 1, 0.8, obj.color, .2);
		obj.polygon = gObjIrregular;
		setObjToMap(obj.polygon);
		aryGobjPoint = null;
		gObjIrregular = null;
		bl = true;
	}
	return bl;
}
function saveIrregularScope() {
	
	var aryStrDotLatLng = new Array();
	var strDotLatLng = "";

	for (var i = 0; i < sNowClickScopeObj.aryMarker.length; i++) {
		aryStrDotLatLng[i] = sNowClickScopeObj.aryMarker[i].getPosition().lat() + "," + sNowClickScopeObj.aryMarker[i].getPosition().lng() + ";";
		if (sNowClickScopeObj.dblMaxLng < sNowClickScopeObj.aryMarker[i].getPosition().lng()) 
			sNowClickScopeObj.dblMaxLng = sNowClickScopeObj.aryMarker[i].getPosition().lng();
		if (sNowClickScopeObj.dblMaxLat < sNowClickScopeObj.aryMarker[i].getPosition().lat()) 
			sNowClickScopeObj.dblMaxLat = sNowClickScopeObj.aryMarker[i].getPosition().lat();
		if (sNowClickScopeObj.dblMinLng > sNowClickScopeObj.aryMarker[i].getPosition().lng()) 
			sNowClickScopeObj.dblMinLng = sNowClickScopeObj.aryMarker[i].getPosition().lng();
		if (sNowClickScopeObj.dblMinLat > sNowClickScopeObj.aryMarker[i].getPosition().lat()) 
			sNowClickScopeObj.dblMinLat = sNowClickScopeObj.aryMarker[i].getPosition().lat();
	}
	strDotLatLng = aryStrDotLatLng.join("");
	aryStrDotLatLng = null;
	
	$.ajax({
		type:"POST",
		url:"addServiceScopeIrregular.action",
		data:{			
			dotLatLng:strDotLatLng,
			total:sNowClickScopeObj.aryMarker.length,
			dblMaxLng:sNowClickScopeObj.dblMaxLng,
			dblMaxLat:sNowClickScopeObj.dblMaxLat,
			dblMinLng:sNowClickScopeObj.dblMinLng,
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
function delDBIrregularScope() {
	$.ajax({
		type:"POST",
		url:"delServiceScopeIrregular.action",
		data:{
			
		},
		dataType:"json",
		error: function(data){
			alert(data.msg);
		},
		success: function(data){			
			//setElObjDisable("delScope", false);
			removeObjFromMap(sNowClickScopeObj.polygon);
			sNowClickScopeObj = null;
			alert(data.msg);
		}
	});
}