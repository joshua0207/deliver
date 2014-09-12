function createXmlHttpRequest(xmlHttp) 
{
	//alert("test");
	if (window.ActiveXObject) 
	{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	else if (window.XMLHttpRequest) 
	{
		xmlHttp = new XMLHttpRequest();
	}
	
	return xmlHttp;
}


function getHttpRequestObject(handler) 
{
	var httpRequest = null;

	//alert(handler);
	if (window.XMLHttpRequest) 
	{
		// IE7, Mozilla, Safari browser used
		httpRequest = new XMLHttpRequest();
	} 
	else if (window.ActiveXObject) 
	{ 
		// to find the newest XMLHttp ActiveX object
		var msxmls = [ "MSXML2.XMLHttp.6.0",
					   "MSXML2.XMLHttp.5.0",
    				   "MSXML2.XMLHttp.4.0",
					   "MSXML2.XMLHttp.3.0",
					   "MSXML2.XMLHttp",
					   "Microsoft.XMLHttp"];
		
		for (i=0; i<msxmls.length; i++) 
		{
			try 
			{  // create XMLHttpRequest object for use
				httpRequest = new ActiveXObject(msxmls[i]);
				break;
			}
			catch (e) 
			{
				httpRequest = null;
			}
		}
		
	}
	//alert(httpRequest.onreadystatechange);
	// assign process handler to state-change handler event
	if (httpRequest != null)
		httpRequest.onreadystatechange = handler;
	return httpRequest;
	
}

// make a request to server
function httpMakeRequest(httpRequest, actionType, url, output) 
{
	//alert(httpRequest + " " + actionType + " " + url + " " + output);
	if (actionType.toLowerCase() == "post")
	{
		//alert(httpRequest + " " + actionType + " " + url + " " + output);
		httpRequest.open(actionType, url, true); 
		httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpRequest.send(output);          
	}
	else
	{
		//alert("test3");
		httpRequest.open(actionType, url, true); 
		httpRequest.send(null);
	}
}

// make a request to server
function getQueryParam(frm)
{
	var iElementCount;
	var i;
	var sRet = "";

	iElementCount = frm.elements.length;
	for (i=0; i<iElementCount; i++)
	{
		if (i > 0)
			sRet += "&"+frm.elements[i].id + "=" + encodeURIComponent(frm.elements[i].value);
		else
			sRet = frm.elements[i].id + "=" + encodeURIComponent(frm.elements[i].value);
	}
//	alert("sRet=" + sRet);
	return sRet;
}