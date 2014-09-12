function MyOverLayer(map, strInnerHtml, latLng, strDivClass) {
	this.text = strInnerHtml;
	this.div = null;
	this.map = map;
	this.latLng = latLng;
	this.setMap(map);
	this.divClass = strDivClass;
};

MyOverLayer.prototype = new google.maps.OverlayView();
MyOverLayer.prototype.onAdd = function () {
	var divObj = document.createElement('div');
	divObj.style.position = 'absolute';
	divObj.innerHTML = this.text;
	divObj.className = this.divClass;
	this.div = divObj;
	this.getPanes().overlayLayer.appendChild(this.div);
	divObj = null;
};
MyOverLayer.prototype.hide = function () {
	if (this.div != null) this.div.style.display = "none";
};
MyOverLayer.prototype.show = function () {
	if (this.div != null) this.div.style.display = "block";
};

MyOverLayer.prototype.isVisible = function () {
	if (this.div != null) {
		if (this.div.style.display == "block") return true;
		else return false;
	}
};

MyOverLayer.prototype.draw = function()
{   
	var latLng = this.getProjection().fromLatLngToDivPixel(this.latLng);  
	var	size   = new google.maps.Size(0,0);   
	this.div.style.left = latLng.x + size.width  + 'px';    
	this.div.style.top  = latLng.y + size.height + 'px';               
};   
MyOverLayer.prototype.remove = function()
{   
	this.div.parentNode.removeChild(this.div);
	this.div = null;       
}; 	  	

MyOverLayer.prototype.setText = function(strText)
{   
	if (this.div != null) {
		this.text = strText;
		this.div.innerHTML = this.text;
	}	
}; 

MyOverLayer.prototype.getText = function()
{   
	return this.text;    
}; 

MyOverLayer.prototype.move = function(mouseLatlng)
{   
//alert(mouseLatlng);
	if (this.div != null && mouseLatlng != null) {
		var latLng = this.getProjection().fromLatLngToDivPixel(mouseLatlng), size = new google.maps.Size(0, 0);
		this.div.style.left = latLng.x + size.width + 'px';
		this.div.style.top = latLng.y + size.height + 'px';
	}
	//alert(this.div.style.left);
	
}; 

MyOverLayer.prototype.click = function()
{   
	//alert("click");
};