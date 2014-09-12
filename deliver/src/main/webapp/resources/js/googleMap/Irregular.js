function Irregular() {
	this.aryMarker = new Array();
	this.aryLine = new Array();
	this.type = "Irregular";
	this.curMarker = null;
	this.strClickStatus = "startDraw";
	this.dblMaxLng = -1;
	this.dblMaxLat = -1;
	this.dblMinLng = 1000;
	this.dblMinLat = 1000;
}
Irregular.prototype = new Shape();