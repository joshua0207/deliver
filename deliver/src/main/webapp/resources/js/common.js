String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g, "");
}

String.prototype.ltrim = function() {
	return this.replace(/^\s+/, "");
}

String.prototype.rtrim = function() {
	return this.replace(/\s+$/, "");
}

function changeIMG(sObjID, sImgPath) {
	var sObj;

	sObj = document.getElementById(sObjID).src = sImgPath;

	sObj = null;
}

function changeBgColor(sTID, sBgColor) {
	var sObj;

	sObj = document.getElementById(sTID);
	sObj.style.backgroundColor = sBgColor;

	sObj = null;
}

function visLayer(loadId, vis) {
	var sObj;

	sObj = document.getElementById(loadId);
	sObj.style.visibility = vis;

	sObj = null;
}

function displayObj(objId, objDisplay) {
	var sObj;

	sObj = document.getElementById(objId);
	sObj.style.display = objDisplay;

	sObj = null;
}

function setValue(objId, objValue) {
	var sObj;

	sObj = document.getElementById(objId);
	sObj.value = objValue;

	sObj = null;
}

function getFormOptionsText(formId, elId) {
	return document.forms[formId].elements[elId].options[document.forms[formId].elements[elId].selectedIndex].text;
}

function getFormOptionsValue(formId, elId) {
	return document.forms[formId].elements[elId].options[document.forms[formId].elements[elId].selectedIndex].value;
}

function getOptionTextById(sObjId) {
	var sObj;
	var sText = "";

	sObj = document.getElementById(sObjId);
	sText = sObj.options[sObj.selectedIndex].text;
	sObj = null;

	return sText;
}

function getOptionValueById(sObjId) {
	var sObj;
	var sText = "";

	sObj = document.getElementById(sObjId);
	sText = sObj.options[sObj.selectedIndex].value;
	sObj = null;

	return sText;
}

function setElObjText(sObjId, sObjText) {
	var sObj;

	sObj = document.getElementById(sObjId);
	sObj.text = sObjText;
	sObj = null;
}

function setElObjValue(sObjId, sObjValue) {
	var sObj;

	sObj = document.getElementById(sObjId);
	sObj.value = sObjValue;
	sObj = null;
}

function setElObj_src(sObjId, sObj_src) {
	var sObj;

	sObj = document.getElementById(sObjId);
	sObj.src = sObj_src;
	sObj = null;
}

function setElObjInnerHTML(sObjId, sObjHtml) {
	var sObj;

	sObj = document.getElementById(sObjId);
	sObj.innerHTML = sObjHtml;
	// sObj.document.write(sObjHtml);
	sObj = null;
}

function setElObjDisable(sObjId, bl) {
	var sObj;

	sObj = document.getElementById(sObjId);

	if (bl)
		sObj.disabled = "";
	else
		sObj.disabled = "disabled";

	sObj = null;
}

function setElObjCheckedById(sObjId, sChk) {
	var sObj;

	sObj = document.getElementById(sObjId);
	sObj.checked = sChk;
	sObj = null;
}

function getElObjCheckedById(sObjId) {
	var sObj;
	var sCheck;

	sObj = document.getElementById(sObjId);
	sCheck = sObj.checked;
	sObj = null;

	return sCheck;
}

function getElObjText(sObjId) {
	var sObj;
	var sText;

	sObj = document.getElementById(sObjId);
	sText = sObj.text;
	sObj = null;

	return sText;
}

function getElObjValue(sObjId) {
	var sObj;
	var sValue;

	sObj = document.getElementById(sObjId);
	sValue = sObj.value;
	sObj = null;

	return sValue;
}

function setValueByAtoB(strObjAId, strObjBId) {
	var strObjAValue;

	strObjAValue = getElObjValue(strObjAId);
	setElObjValue(strObjBId, strObjAValue);
}

function setValueByAtoB_chk(strObjAId, strObjBId, chkId) {
	var strObjAValue;
	var chk;

	chk = getElObjCheckedById(chkId);

	if (chk) {
		strObjAValue = getElObjValue(strObjAId);
		setElObjValue(strObjBId, strObjAValue);
	}

}

function removeOp(opId) {
	var obj;

	obj = document.getElementById(opId)

	while (obj.options.length > 0)
		obj.remove(0);

	obj = null;
}

function addOp(opId, opText, opValue) {
	var obj;

	obj = document.getElementById(opId)

	obj.options.add(new Option(opText, opValue));// option(text,value)

	obj = null;
}

// Create a DHTML layer
function createLayer(name, inleft, intop, width, height, visible, content, sAlpha, sBgColor, sAlign, sOverFlow) {
	var layer;
	var sValue;
	/*
	 * if (!document.all) { document.writeln('<layer name="' + name + '" left=' + inleft + ' top=' + intop + ' width=' + width + ' height=' +
	 * height + ' visibility=' + (visible ? '"show"' : '"hide"') + ' -moz-opacity:' + (sAlpha / 100) + ' background-color:' + sBgColor + '
	 * align="' + sAlign + '">'); document.writeln(content); document.writeln('</layer>'); } else
	 */
	{
		sValue = (sAlpha / 100);
		document.writeln('<div id="' + name + '" style="position:absolute; overflow:hidden; left:' + inleft + 'px; top:' + intop
				+ 'px; width:' + width + 'px; height:' + height + 'px;' + ';visibility:' + (visible ? 'visible;' : 'hidden;')
				+ ';filter:alpha(Opacity=' + sAlpha + ')' + ';-moz-opacity:' + sValue + ';opacity:' + sValue + '; background-color:'
				+ sBgColor + ';overflow:' + sOverFlow + ';" align=' + sAlign + '>');
		document.writeln(content);
		document.writeln('</div>');
	}
}

function clipLayer(name, clipleft, cliptop, clipright, clipbottom) {
	var layer = getLayer(name);

	if (!isIE) {
		layer.clip.left = clipleft;
		layer.clip.top = cliptop;
		layer.clip.right = clipright;
		layer.clip.bottom = clipbottom;
	} else {
		var newWidth = clipright - clipleft;
		var newHeight = clipbottom - cliptop;

		layer.height = newHeight;
		layer.width = newWidth;
		layer.top = cliptop + "px";
		layer.left = clipleft + "px";
	}
	layer = null;
}

function setLayerIndex(layerId, sZindex) {
	var obj;

	obj = document.getElementById(layerId);

	obj.style.zIndex = sZindex;

	obj = null;
}

// get the layer object called "name"
function getLayer(sObjId) {
	/*
	 * if (!isIE) return(document.layers[name]); else
	 */
	{
		var theObj = document.getElementById(sObjId);
		return theObj.style;
	}
}

// move layer to x,y
function moveLayer(sObjId, x, y) {
	var layer = getLayer(sObjId);

	if (!isIE)
		layer.moveTo(x, y);
	else {
		layer.left = x + "px";
		layer.top = y + "px";
	}
}

// set layer background color
function setObjBackgroundColor(sObjId, color) {
	var layer = getLayer(sObjId);

	if (!isIE)
		layer.bgColor = color;
	else
		layer.backgroundColor = color;

	layer = null;
}

// toggle layer to invisible
function hideLayer(sObjId) {
	var layer = getLayer(sObjId);

	if (!isIE)
		layer.visibility = "hide";
	else
		layer.visibility = "hidden";
}

// toggle layer to visible
function showLayer(sObjId) {
	var layer = getLayer(sObjId);

	if (!isIE)
		layer.visibility = "show";
	else
		layer.visibility = "visible";
}

function panClipLayer(name, clipleft, cliptop, clipright, clipbottom) {
	var layer = getLayer(name);

	if (!isIE) {
		layer.clip.left = clipleft;
		layer.clip.top = cliptop;
		layer.clip.right = clipright;
		layer.clip.bottom = clipbottom;
	} else {
		layer.clip = 'rect(' + cliptop + ' ' + clipright + ' ' + clipbottom + ' ' + clipleft + ')';
	}

}

function drag(obj, e, sW, sH) {
	var _x;
	var _y;

	if (!e)
		e = window.event;

	_x = e.clientX - parseInt(obj.style.left);
	_y = e.clientY - parseInt(obj.style.top);

	if (document.addEventListener)// FireFox
	{
		document.addEventListener("mousemove", move, true);
		document.addEventListener("mouseup", up, true);
	} else if (attachEvent)// IE
	{
		document.attachEvent("onmousemove", move);
		document.attachEvent("onmouseup", up);
	}
	// alert(sW + " " + sH);
	// alert(obj.style.left);
	function move(e) {
		var sObjLeft = parseInt(obj.style.left);
		var sObjTop = parseInt(obj.style.top);

		if (!e)
			e = Window.event;

		if (sObjLeft > 0 && ((sObjLeft + parseInt(obj.style.width)) < parseInt(sW)))
			obj.style.left = e.clientX - _x + "px";
		else if (sObjLeft <= 0)
			obj.style.left = "0px";
		else if ((sObjLeft + parseInt(obj.style.width)) >= parseInt(sW))// right
			obj.style.left = parseInt(sW) - parseInt(obj.style.width) + "px";

		if (sObjTop > 0 && ((sObjTop + parseInt(obj.style.height)) < parseInt(sH)))
			obj.style.top = e.clientY - _y + "px";
		else if (sObjTop <= 0)
			obj.style.top = "0px";
		else if ((sObjTop + parseInt(obj.style.height)) >= parseInt(sH))
			obj.style.top = parseInt(sH) - parseInt(obj.style.height) + "px";

	}
	function up(e) {
		var sCope = 1;
		var sObjLeft = parseInt(obj.style.left);
		var sObjTop = parseInt(obj.style.top);

		if (!e)
			e = Window.event;

		if (sObjLeft <= 0)
			obj.style.left = sCope + "px";
		if ((sObjLeft + parseInt(obj.style.width)) >= parseInt(sW))// right
			obj.style.left = parseInt(sW) - parseInt(obj.style.width) - sCope + "px";

		if (sObjTop <= 0)
			obj.style.top = sCope + "px";
		if ((sObjTop + parseInt(obj.style.height)) >= parseInt(sH))
			obj.style.top = parseInt(sH) - parseInt(obj.style.height) - sCope + "px";

		if (document.removeEventListener) {
			document.removeEventListener("mousemove", move, true);
			document.removeEventListener("mouseup", up, true);
		} else if (document.detachEvent) {
			document.detachEvent("onmousemove", move);
			document.detachEvent("onmouseup", up);
		}

	}

}

function colorMenu() {
	var colorArray = new Array("black", "red", "orange", "green", "blue", "pink", "yellow");

	for (var i = 0; i < colorArray.length; i++) {
		if (i == 0)
			document.writeln('<input id="LineColor" name="LineColor" type="radio" value="0" checked>');
		else
			document.writeln('<input id="LineColor" name="LineColor" type="radio" value="' + i + '">');

		document.writeln('<span class="' + colorArray[i] + '">Line</span>');
		// document.writeln('<span class="' + colorArray[i] + '">��</span>');
	}
	colorArray = null;

}

function getFormatTime(now) {
	var s;
	var y, m, d, hh, mm, ss;

	y = now.getFullYear();
	m = now.getMonth() + 1;
	d = now.getDate();
	hh = now.getHours();
	mm = now.getMinutes();
	ss = now.getSeconds();

	s = y + "/" + m + "/" + d + " " + hh + ":" + mm + ":" + ss;
	return s;
}

function setLastLogoutTime(sUID, sJetLag, sDTime, sCID, sUserIP, sWebServer) {
	var xmlHttp;
	var urlStr = "";
	var sFormatTime;
	var now;

	// alert(sJetLag + " , " + sDTime);
	now = new Date(((sJetLag * 60 * 60 * 1000) + sDTime));
	sFormatTime = getFormatTime(now);
	// alert(sFormatTime);
	urlStr += "setLastLogoutTime=true&UserID=" + sUID + "&LastLogoutTime=" + sFormatTime + "&CID=" + sCID + "&UserIP=" + sUserIP
			+ "&WebServer=" + sWebServer;
	// alert(urlStr);
	xmlHttp = createXmlHttpRequest(xmlHttp);
	xmlHttp.open("POST", "setItem.jsp", true);
	xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xmlHttp.send(urlStr);

}

function setObjVisibility(objId, objStatus) {
	var sObj;

	sObj = document.getElementById(objId);
	sObj.style.visibility = objStatus;
	sObj = null;
}

function setObjPosition(objId, objPosition, sValue) {
	var sTxt;

	sTxt = "document.getElementById('" + objId + "').style." + objPosition + "='" + sValue + "px';"
	eval(sTxt);
	// alert(sTxt);
	// document.getElementById(objName ).style.objPosition = sValue + "px";
}

function checkEventCode(e) {
	var charCode;
	var ev;

	if (window.event) {
		charCode = e.keyCode;
		ev = window.event;
	} else {
		charCode = e.which;
		;
		ev = e;
	}

	if ((charCode < 48 && charCode != 45 && charCode != 42 && charCode != 36) || (charCode > 57 && charCode < 65)
			|| (charCode > 90 && charCode < 97) || charCode > 122) {
		ev = false;
	}
}

function getKeyCode(e) {
	var charCode;

	if (window.event)
		charCode = e.keyCode;
	else
		charCode = e.which;

	return charCode;
}

function chkKeyWord(e, strWord) {
	var aryKeyCode = new Array('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z');
	var charCode;
	var cur = -1;

	for (var i = 0; i < aryKeyCode.length; i++)
		if (strWord == aryKeyCode[i])
			cur = i;
	cur += 65;
	aryKeyCode = null;

	if (window.event)
		charCode = e.keyCode;
	else
		charCode = e.which;

	if (charCode == cur)
		return true;

}

/*
 * function checkTextLength(obj) { var sValue; var sb = true;
 * 
 * sValue = obj.value;
 * 
 * if( sValue.trim().length <= 0) { alert(""); sb = false; } //alert(sValue);
 * 
 * return sb; }
 */

function reciprocal(objId, seconds) {

	if (seconds - 1 > 0) {
		setElObjValue(objId, seconds - 1);
		setTimeout(reciprocal(objId, seconds - 1), 1000);
	}
}

function checkNumber(objId, strMsg) {
	var sValue;
	var charCode;

	sValue = getElObjValue(objId);

	for (var i = 0; i < sValue.length; i++) {
		charCode = sValue.charCodeAt(i);
		// if ( (charCode < 48 || charCode > 57) && charCode != 45 )
		if (charCode < 48 || charCode > 57) {
			// alert(strMsg);
			setElObjValue(objId, "");
			sb = false;
			break;
		}
	}

}

// 檢查關鍵字，避免使用者進行隱碼攻擊
function checkKeyWord(objVal) {
	var aryStrKeyWord = new Array("insert", "update", "delete", "drop", "select", "from", "--", "*", ">", "<", "=");
	var sb = true;

	for (var i = 0; i < aryStrKeyWord.length; i++) {
		if (objVal.indexOf(aryStrKeyWord[i]) >= 0) {
			// alert(" Do not accept special characters ");
			sb = false;
		}
	}

	return sb;
}

// 只可輸入英文或數字
function onlyEngAndNum(objVal) {
	var re = /^([a-zA-Z0-9]+)$/;
	var bl = false;

	if (re.test(objVal)) {// 若為英文或數字則回傳true
		bl = true;
	}
	return bl;

}

function checkInputText(objVal) {
	var sb = true;

	sb = checkKeyWord(objVal);

	return sb;
}
function getTitleFormChkBoxByName(chkBoxName)// 取得checkbox的title
{
	var objChkBox = null;

	var strTemp = "";

	objChkBox = document.getElementsByName(chkBoxName);// checkBox

	// if checkBox.checked == true , add value to strTemp
	for (var i = 0; i < objChkBox.length; i++) {
		if (objChkBox[i].checked)
			strTemp += objChkBox[i].title + ";";
	}

	objChkBox = null;

	return strTemp;
}
function checkFormAllTextCurrent(sFormId) {
	var sForm;
	var sObj;
	var sb = true;

	sForm = document.getElementById(sFormId);
	sObj = sForm.elements;

	for (var i = 0; i < sObj.length; i++) {
		if (sObj[i].type == "text" || sObj[i].type == "password") {

			if (!checkInputText(sObj[i])) {
				// alert("test");
				sb = false;
				break;
			}

		}
	}
	sForm = null;

	return sb;
}

function checkFormAllTextLength(sFormId) {
	var sForm;
	var sObj;
	var sb = true;

	sForm = document.getElementById(sFormId);
	sObj = sForm.elements;

	for (var i = 0; i < sObj.length; i++) {
		if (sObj[i].type == "text" || sObj[i].type == "password") {
			// alert(sObj[i].id + ": " + sObj[i].style.display);
			if (sObj[i].style.display != "none") {
				if (sObj[i].value.trim().length <= 0) {
					alert("No Data");
					sb = false;
					break;
				}
			}
		}
	}

	sForm = null;
	return sb;
}

function addOption(opId, sText, sValue) {
	var sObj;
	var sOption;

	sOption = new Option(sText, sValue);
	sObj = document.getElementById(opId);
	sObj.options.add(sOption);

	sOption = null;
	sObj = null;
}

function removeAllOption(opId) {
	var sObject = null;

	sObject = document.getElementById(opId);

	if (sObject.options.length > 0) {
		while (sObject.options.length > 0)
			sObject.remove(0);
	}
	sObject = null;
}

function changeMileage_Speed(sUnit, oSpeed) {
	var nSpeed = "";

	if (sUnit == "mile")
		nSpeed = oSpeed * CHANGEOVER_SPD_Mile;
	else
		nSpeed = oSpeed * CHANGEOVER_SPD_KM;

	return nSpeed;
}

function redirect(sUrl) {
	location.href = sUrl;
}

function setCookie(c_name, c_value, days) {
	var now = new Date();

	if (days == "")
		now.setTime(now.getTime() + 1000 * 60 * 60 * 24 * 30);
	else
		now.setTime(now.getTime() + 1000 * 60 * 60 * 24 * days);

	document.cookie = c_name + "=" + escape(c_value) + ";expires=" + now.toGMTString();

}

function getCookie(c_name) {
	var c_start = 0;
	var c_end = 0;

	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=");
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1;
			c_end = document.cookie.indexOf(";", c_start);
			if (c_end == -1)
				c_end = document.cookie.length;

			return unescape(document.cookie.substring(c_start, c_end));
		}
	}
	return "";
}

function getValueFormChkBoxByName(chkBoxName) {
	var objChkBox = null;

	var strTemp = "";

	objChkBox = document.getElementsByName(chkBoxName);// checkBox

	// if checkBox.checked == true , add value to strTemp
	for (var i = 0; i < objChkBox.length; i++) {
		if (objChkBox[i].checked)
			strTemp += objChkBox[i].value + ";";
	}

	objChkBox = null;

	return strTemp;
}

function setChkBoxValToObjByBoxName(chkBoxName, hiddenObjId) {
	var objChkBox = null;
	var objHidden = null;
	var strTemp = "";

	objChkBox = document.getElementsByName(chkBoxName);// checkBox
	objHidden = document.getElementById(hiddenObjId);// hidden

	// if checkBox.checked == true , add value to hidden
	for (var i = 0; i < objChkBox.length; i++) {
		if (objChkBox[i].checked)
			strTemp += objChkBox[i].value + ";";
	}
	objHidden.value = strTemp;
	objChkBox = null;

}

function setChkBoxChkedByObjVal(chkBoxName, hiddenId) {
	var objChkBox = null;// checkBox
	var objHidden = null;// hidden
	var strTemp = "";
	var strChkBoxValue = "";

	objChkBox = document.getElementsByName(chkBoxName);
	objHidden = document.getElementById(hiddenId);
	strTemp = objHidden.value;// hidden value

	for (var i = 0; i < objChkBox.length; i++) {
		strChkBoxValue = objChkBox[i].value;// checkbox value

		if (strTemp.indexOf(strChkBoxValue) != -1)
			objChkBox[i].checked = true;//
		// else
		// objChkBox[i].checked = false;

	}
	objChkBox = null;
	objHidden = null;

}

function setChkBoxChkedByVal(chkBoxName, strVal) {
	var objChkBox = null;// checkBox
	var objHidden = null;// hidden
	var strTemp = "";
	var strChkBoxValue = "";

	objChkBox = document.getElementsByName(chkBoxName);

	for (var i = 0; i < objChkBox.length; i++) {
		strChkBoxValue = objChkBox[i].value;// checkbox value

		if (strVal.indexOf(strChkBoxValue) != -1)
			objChkBox[i].checked = true;//
		// else
		// objChkBox[i].checked = false;

	}
	objChkBox = null;

}

// All chkBox will checked or cancel
function setChkBoxChkedByBoxName(sObjName, sChk) {
	var sObj;

	sObj = document.getElementsByName(sObjName);

	for (var i = 0; i < sObj.length; i++)
		sObj[i].checked = sChk;

	sObj = null;
}

function setOptionSelected(selObjId, strVal) {
	// alert(selName.length);
	var obj;

	obj = document.getElementById(selObjId);
	// alert(obj.length);
	for (var i = 0; i < obj.length; i++) {
		if (obj[i].value == strVal) {
			obj[i].selected = true;
			break;
		}
	}
	obj = null;

}

function setOpSlcedByKeyWord(selObjId, strVal) {
	var obj;

	obj = document.getElementById(selObjId);
	// alert(strVal);
	for (var i = 0; i < obj.length; i++) {
		if (strVal.indexOf(obj[i].value) != -1) {
			obj[i].selected = true;
			break;
		}
	}

	obj = null;

}

function getCheckedFormChkBoxByName(chkBoxName) {
	var objChkBox = null;
	var bl = false;

	objChkBox = document.getElementsByName(chkBoxName);// checkBox

	// if checkBox.checked == true , add value to strTemp
	for (var i = 0; i < objChkBox.length; i++) {
		if (objChkBox[i].checked) {
			bl = true;
			break;
		}
	}

	objChkBox = null;

	return bl;
}

function reciprocal(objId, seconds, strUrl) {
	var strTemp = "";

	strTemp = "reciprocal('" + objId + "'," + (seconds - 1) + ",'" + strUrl + "')";
	// alert(strUrl);
	if (seconds - 1 > 0) {
		setElObjInnerHTML(objId, seconds - 1);
		setTimeout(strTemp, 1000);

	} else
		window.location = strUrl;
}
function getLengthById(formId, objId) {

	var total = 1;
	// alert($('#aa').length);
	var allObj = document.forms[formId];

	for (var i = 0; i < allObj.length; i++) {
		var obj = allObj[i];

		if (obj.id == objId)
			total += 1;

		obj = null;
	}
	// alert(total);
	return total;

}

function getWinWidth() {
	var intWidth = window.innerWidth;

	if (intWidth == null || intWidth == 'undefined') {
		intWidth = document.documentElement.clientWidth;
	}

	// alert(mapFrameWidth);
	return intWidth;
}

// get the Map Image height
function getWinHeight() {
	var intHeight = window.innerHeight;

	if (intHeight == null || intHeight == 'undefined') {
		intHeight = document.documentElement.clientHeight;
	}
	return intHeight;
}

function getScrollTop() {
	var intHeight = document.body.scrollTop;

	if (intHeight == null || intHeight == 'undefined' || intHeight == 0) {
		intHeight = document.documentElement.scrollTop;
	}
	return intHeight;
}

function getIEVersion() {
	var browser = navigator.appName
	var b_version = navigator.appVersion
	var version = b_version.split(";");
	var trim_Version = "";

	if (version[1] != undefined) {
		trim_Version = version[1].replace(/[ ]/g, "");
		if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE7.0") {
			return 7;
		} else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE6.0") {
			return 6;
		} else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE8.0") {
			return 8;
		} else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE9.0") {
			return 9;
		}
	}

}

function formSubmit(strFormName) {
	var obj;

	obj = document.getElementById(strFormName);
	obj.submit();

	obj = null;
}

function checkIdentNum(sid) {// 檢查統一編號
	var blChk = false;
	var tbNum = new Array(1, 2, 1, 2, 1, 2, 4, 1);
	var temp = 0;
	var total = 0;

	sid = "" + sid;
	// alert(sid.length);
	if (sid != "" && sid.match(/^\d{8}$/)) {
		for (var i = 0; i < tbNum.length; i++) {
			temp = sid.charAt(i) * tbNum[i];
			total += Math.floor(temp / 10) + temp % 10;
		}
		if (total % 10 == 0 || (total % 10 == 9 && sid.charAt(6) == 7))
			blChk = true;

	}
	return blChk;
}
function onlyNumber(objVal) {
	var re = /^([0-9]+)$/;
	var bl = false;

	if (re.test(objVal)) {// 若為數字則回傳true
		bl = true;
	}
	return bl;

}

function moveOpA2B(opAId, opBId)// 將A下拉式選單中有選取的項目移至B下拉式選單
{
	var slcOpAObject;
	var slcOpBObject;
	var newOpObject;

	slcOpAObject = document.getElementById(opAId);
	slcOpBObject = document.getElementById(opBId);

	for (var i = 0; i < slcOpAObject.length; i++) {
		if (slcOpAObject.options[i].selected) {
			if (!isInTheList(slcOpBObject, slcOpAObject[i])) {
				newOpObject = document.createElement("option");

				// alert( slcOpAObject.options[i].text + " " + slcOpAObject.options[i].value );
				// newOpObject.setAttribute("text",slcOpAObject.options[i].text);
				// newOpObject.setAttribute("value",slcOpAObject.options[i].value);
				newOpObject.text = slcOpAObject.options[i].text;
				newOpObject.value = slcOpAObject.options[i].value;
				// slcOpBObject.appendChild(newOpObject);

				try {
					slcOpBObject.add(newOpObject, null);
				} catch (ex) {
					slcOpBObject.add(newOpObject);
				}

				newOpObject = null;
			}
		}
	}
	del_OptionFromList(opAId);

	slcOpAObject = null;
	slcOpBObject = null;
}

function moveAllOpA2B(opAId, opBId)// 將A下拉式選單中所有的項目移至B下拉式選單
{
	var slcOpAObject;
	var slcOpBObject;
	var newOpObject;

	slcOpAObject = document.getElementById(opAId);
	slcOpBObject = document.getElementById(opBId);

	for (var i = 0; i < slcOpAObject.length; i++) {
		if (!isInTheList(slcOpBObject, slcOpAObject[i]))// 檢查該選項是否已存在
		{
			newOpObject = document.createElement("option");

			// alert( slcOpAObject.options[i].text + " " + slcOpAObject.options[i].value );
			// newOpObject.setAttribute("text",slcOpAObject.options[i].text);
			// newOpObject.setAttribute("value",slcOpAObject.options[i].value);
			newOpObject.text = slcOpAObject.options[i].text;
			newOpObject.value = slcOpAObject.options[i].value;
			// slcOpBObject.appendChild(newOpObject);
			try {
				slcOpBObject.add(newOpObject, null);
			} catch (ex) {
				slcOpBObject.add(newOpObject);
			}

			newOpObject = null;
		}
	}
	slcOpAObject.innerHTML = "";
	slcOpAObject = null;
	slcOpBObject = null;
}

function isInTheList(list, o)// 檢查該選項值是否已存在下拉式選單中
{
	var bRet = 0;
	var i = 0;

	for (i = 0; i < list.length; i++) {
		// alert(list.options[i].text + " " + o.text);
		if (list.options[i].text == o.text) {
			bRet = true;
			break;
		}
	}// end for

	return bRet;
}

function del_OptionFromList(slcObjId)// 刪除選取的選項
{
	var slcObj;

	slcObj = document.getElementById(slcObjId);

	for (var i = slcObj.length - 1; i >= 0; i--) {
		if (slcObj.options[i].selected == true)
			slcObj.remove(i);
	}// end for

	slcObj = null;
}
// 身分證字號
function chkPID(id) {
	var tab = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
	var A1 = new Array(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3);
	var A2 = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5);
	var Mx = new Array(9, 8, 7, 6, 5, 4, 3, 2, 1, 1);

	id = id.toUpperCase();

	if (id.length != 10)
		return false;

	i = tab.indexOf(id.charAt(0));

	if (i == -1)
		return false;

	sum = A1[i] + A2[i] * 9;

	for (var i = 1; i < 10; i++) {
		var v = parseInt(id.charAt(i));

		if (isNaN(v))
			return false;

		sum = sum + v * Mx[i];
	}
	if (sum % 10 != 0)
		return false;

	return true;
}

function checkThisNumber(keyValue) {
	var dataCount = 0;

	for (var i = 0; i < keyValue.length; i++) {
		if (keyValue.charAt(i) < '0' || keyValue.charAt(i) > '9') {
			dataCount += 1;
			break;
		}
	}

	if (dataCount > 0) {
		return false;
	} else {
		return true;
	}
}