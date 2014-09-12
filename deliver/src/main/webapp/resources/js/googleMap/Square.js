function Square() {
	this.type = "Square";
	this.gObjMarkerFirst = null;
	this.gObjMarkerLast = null;
	this.strClickStatus = "startDraw";
}
Square.prototype = new Shape();