eval(function(p,a,c,k,e,d){e=function(c){return c.toString(36)};if(!''.replace(/^/,String)){while(c--){d[c.toString(a)]=k[c]||c.toString(a)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('7 6(1,3,2,4){0.5=3;0.8=a;0.1=1;0.2=2;0.9(1);0.b=4};',12,12,'this|map|latLng|strInnerHtml|strDivClass|text|MyOverLayer|function|div|setMap|null|divClass'.split('|'),0,{}))


MyOverLayer.prototype = new google.maps.OverlayView();
eval(function(p,a,c,k,e,d){e=function(c){return c.toString(36)};if(!''.replace(/^/,String)){while(c--){d[c.toString(a)]=k[c]||c.toString(a)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('6.3.e=5(){g 2=d.h(\'1\');2.4.a=\'b\';2.c=0.f;2.q=0.o;0.1=2;0.p().n.i(0.1);2=7};6.3.j=5(){8(0.1!=7)0.1.4.9="k"};6.3.l=5(){8(0.1!=7)0.1.4.9="m"};',27,27,'this|div|divObj|prototype|style|function|MyOverLayer|null|if|display|position|absolute|innerHTML|document|onAdd|text|var|createElement|appendChild|hide|none|show|block|overlayLayer|divClass|getPanes|className'.split('|'),0,{}))


eval(function(p,a,c,k,e,d){e=function(c){return c.toString(36)};if(!''.replace(/^/,String)){while(c--){d[c.toString(a)]=k[c]||c.toString(a)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('7.6.8=5(){0(1.2!=4){0(1.2.c.b=="a")3 e;9 3 d}};',15,15,'if|this|div|return|null|function|prototype|MyOverLayer|isVisible|else|block|display|style|false|true'.split('|'),0,{}))


MyOverLayer.prototype.draw = function()
{   
	var latLng = this.getProjection().fromLatLngToDivPixel(this.latLng)  
	var	size   = new google.maps.Size(0,0);   
	this.div.style.left = latLng.x + size.width  + 'px';    
	this.div.style.top  = latLng.y + size.height + 'px';               
};   
MyOverLayer.prototype.remove = function()
{   
	this.div.parentNode.removeChild(this.div)   
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