/*
 * Author: Edward
 * Date: May 24, 2006
 * 
 * Copyright 2006 Avectec, Inc.  All Rights Reserved.
 * 
 * @(#)
 * Description: Validation
 */
 
 //avoidLink("http://localhost:8019/rltts/");
 totaldisplaymode=true;
 findanyerror=false;
 totaldisplaystring="";
 

/**
 * 驗證初始化
 * 
 * 
 */

function init()
{
	totaldisplaystring="";
	findanyerror=false;
}

/**
 * 驗證結果產出
 * 
 * 
 */
function result()
{
	if (findanyerror==false)
	 return true;
	if (totaldisplaymode==true)
	    alert("請檢查下列錯誤:\n"+totaldisplaystring);
	else
	    alert("請檢查此錯誤:\n"+totaldisplaystring);
	return false;
}
/**
 * 判防止別人不正當引用此JS
 * 
 * @param 允許的網址
 */


function avoidLink(str)
{
   //偵測瀏覽器目前的網頁位址
   var domaincheck=document.location.href; 
   var accepted_ok=false;
   if (domaincheck.indexOf("http")!=-1)
   { 
       if (domaincheck.indexOf(str)!=-1)
       { 
          accepted_ok=true;
       }
	   else
          accepted_ok=false;
   }
   else
     accepted_ok=true;

   if (!accepted_ok)
   {
    alert("禁止非法使用JS檔案");
    history.back(-1); //強制回到前一頁
   }
}
 

/**
 * 判斷前端輸入欄位是否出現非法字元  輸入【欄位名稱】與【欄位值】，判斷所有字元是否合法
 * 
 * @param column
 * @param str
 * @return boolean
 */
function checkSQLInjection(column, str)
{
    for(i=0; i<str.length; i++)
    {
        //檢查是否為非法字元'
        if(str.charAt(i) == "\'")
        {
            alert("【" + column + "】欄位出現【\'】非法字元");
            return false;
        }
        //檢查是否為非法字元"
        if(str.charAt(i) == "\"")
        {
            alert("【" +column + "】欄位出現【\"】非法字元");
            return false;
        }
        //檢查是否為非法字元`
        if(str.charAt(i) == "`")
        {
            alert("【" +column + "】欄位出現【`】非法字元");
            return false;
        }
        //檢查是否為非法字元<
        if(str.charAt(i) == "<")
        {
            alert("【" +column + "】欄位出現【<】非法字元");
            return false;
        }
        //檢查是否為非法字元>
        if(str.charAt(i) == ">")
        {
            alert("【" +column + "】欄位出現【>】非法字元");
            return false;
        }
    }
    //若全部合法，則回傳true;
    return true;
}

/**
 * 判斷前端輸入欄位是否符合Email格式  輸入【欄位】，判斷是否合法
 * 
 * @param field
 * @param column
 */
 
function checkemailValid(field,column) 
{
	if (totaldisplaymode==false && findanyerror==true)
	   return;
    var str = field.value;
    var reg1 = /(@.*@)|(\.\.)|(@\.)|(\.@)|(^\.)|( )|(\')|(\")/; // not valid
    var reg2 = /^.+\@(\[?)[a-zA-Z0-9\-\.]+\.([a-zA-Z]{2,3}|[0-9]{1,3})(\]?)$/; // valid
    if (!reg1.test(str) && reg2.test(str)) 
	{ 
      return true;
    }
    
    //如果執行到此行，表示有非法的情況發生
	if (findanyerror==false)
	{
		findanyerror=true;   //將findanyerror設定為true，讓錯誤的欄位取得focus
		field.focus();
		field.select();
	}
	totaldisplaystring=totaldisplaystring+"欄位["+column+"]不是一個有效的email\n";
	return false;
}

/**
 * 判斷前端輸入欄位是否空值  輸入【欄位】，判斷是否合法
 * 
 * @param field
 * @param column
 */
 
function checkEmpty(field,column) 
{
	if (totaldisplaymode==false && findanyerror==true)
	   return;
    var str = field.value;
    var reg1 = /(\')|(\")|(`)|(<)|(>)/; // not valid
    if (!reg1.test(str) && str.length>0) 
	{ 
      return true;
    }
	if (findanyerror==false)
	{
		findanyerror=true;		
		if(field.type != "select-one")  //下拉式選單輸入值為空白時，並未支援field.select
    		field.select();
    	else
    	    field.focus();
	}
	if (reg1.test(str))
	{
		  field.value="";
		  totaldisplaystring=totaldisplaystring+"欄位["+column+"]有特殊位元\n";
	}
	else
	{
		  totaldisplaystring=totaldisplaystring+"欄位["+column+"]並未填入任何值\n";
	}	
	return false;
}

/**
 * 判斷前端輸入欄位是否長度超過  輸入【欄位】，判斷是否合法
 * 
 * @param field
 * @param column
 * @param maxlength
 */
 
function checkMaxLength(field,column,length) 
{
	if (totaldisplaymode==false && findanyerror==true)
	   return;
    var str = field.value;
    var reg1 = /(\')|(\")|(`)|(<)|(>)/; // not valid
    if (!reg1.test(str) && str.length<=length) 
	{ 
      return true;
    }
	if (findanyerror==false)
	{
		findanyerror=true;
		field.focus();
		field.select();
	}
	if (reg1.test(str))
	{
		  field.value="";
		  totaldisplaystring=totaldisplaystring+"欄位["+column+"]有特殊位元\n";
	}
	else
	{
		  totaldisplaystring=totaldisplaystring+"欄位["+column+"]超過最大欄位數["+length+"]\n";
	}	
	return false;
}
/**
 * 判斷前端輸入欄位是否有特殊字元  輸入【欄位】，判斷是否合法
 * 
 * @param field
 * @param column
 */
 
function checkSpecial(field,column) 
{
	if (totaldisplaymode==false && findanyerror==true)
	   return;
    var str = field.value;
    var reg1 = /(\')|(\")|(`)|(<)|(>)/; // not valid
    if (!reg1.test(str)) 
	{ 
      return true;
    }
	if (findanyerror==false)
	{
		findanyerror=true;
		field.focus();
		field.select();
	}
	if (reg1.test(str))
	{
		  field.value="";
		  totaldisplaystring=totaldisplaystring+"欄位["+column+"]有特殊位元\n";
	}
	return false;
}

/**
 * 判斷前端輸入欄位是否為整數  輸入【欄位】，判斷是否合法
 * 
 * @param field
 * @param column
 */
 
function checkInteger(field,column) 
{
	if (totaldisplaymode==false && findanyerror==true)
	   return;
    var str = field.value;
    var reg1 = /[^\d]/; // Not valid
    if (!reg1.test(str)) 
	{ 
      return true;
    }
	if (findanyerror==false)
	{
		findanyerror=true;
		field.focus();
		field.select();
	}
	if (reg1.test(str))
	{
		  totaldisplaystring=totaldisplaystring+"欄位["+column+"]有非整數字元\n";
	}
	return false;
}

/**
 * 判斷前端輸入欄位是否為浮點數  輸入【欄位】，判斷是否合法
 * 
 * @param field
 * @param column
 */
 
function checkFloat(field,column) 
{
	if (totaldisplaymode==false && findanyerror==true)
	   return;
    var str = field.value;
    var reg1 = /\./; //  float
    if (!reg1.test(str)) 
	{ 
	    var reg2=/[^\d]/; // Not valid
        if (!reg2.test(str)) 
    	{ 
          return true;
        }
	}
	else
	{
		var reg2=/[^(\d|\.)]/; // Not valid
		var reg3=/\d\.\d/
        if (!reg2.test(str) && reg3.test(str)) 
    	{ 
          return true;
        }
	}
	if (findanyerror==false)
	{
		findanyerror=true;
		field.focus();
		field.select();
	}
	if (reg1.test(str))
	{
		  totaldisplaystring=totaldisplaystring+"欄位["+column+"]非浮點數格式\n";
	}
	return false;
}

/**
 * 判斷兩欄位是否相等  輸入【欄位】，判斷是否合法
 * 
 * @param fromfield
 * @param tofield
 * @param displaytext
 */
 
function checkCompare(fromfield,tofield,displaytext) 
{
	if (fromfield.value==tofield.value)
	   return true;
	if (findanyerror==false)
	{
		findanyerror=true;
		fromfield.focus();
		fromfield.select();
	}
	totaldisplaystring=totaldisplaystring+displaytext+"\n";
	return false;
}
function backUp()
 {
 	    var result=confirm('是否確定離開?');
		if(result==true)
 		{
 	    	history.go(-1);
 	    }
 	 
  }

function check2(frm1){
  var len=frm1.checkbox.length;
  var flag1=0;
  if(len==null){
     if(frm1.checkbox.checked){
         flag1++;
     }
  }
  else{
     for(i=0;i<len;i++){
         if(frm1.checkbox[i].checked){
             flag1++;
         }
     }
  }
  if(flag1==0){
     alert("請至少選擇一項需進行的項目!!");
     return false;
  }
  else{
  	frm1.submit();
  }
}