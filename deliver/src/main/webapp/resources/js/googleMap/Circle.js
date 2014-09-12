function Circle() {
	this.floatCenter = "";
	this.floatTan = "";
	this.floatDistance = 0;
	this.type = "Circle";
	this.gObjMarkerFirst = null;
	this.gObjMarkerLast = null;
	this.strClickStatus = "startDraw";
	this.dblMaxLng = -1;
	this.dblMaxLat = -1;
	this.dblMinLng = 1000;
	this.dblMinLat = 1000;
}
Circle.prototype = new Shape();