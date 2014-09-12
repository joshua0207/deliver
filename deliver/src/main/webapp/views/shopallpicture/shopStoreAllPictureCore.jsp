<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<%@include file="/views/ssi/view.jsp"%>
<html>  
  <head> 
        <title><spring:message code="LAB0046"/></title>
  
        <style type="text/css"> 
            body {
                padding: 0;
                margin: 0;
                background: #F7F7F7;
                font-family: Verdana, Arial, Helvetica, sans-serif;
                font-size: 11px;
            }
            img {
                border: 0;
            }
            #container {
                padding: 0px 10px 7px 10px;
                height: 340px;
            }
            #menu {
                clear: both;
                height: 29px;
                margin-bottom: 3px;
            }
            #column_left {
                background: #FFF;
                border: 1px solid #CCC;
                float: left;
                width: 20%;
                height: 320px;
                overflow: auto;
            }
            #column_right {
                background: #FFF;
                border: 1px solid #CCC;
                float: right;
                width: 78%;
                height: 320px;
                overflow: auto;
                text-align: center;
            }
            #column_right div {
                text-align: left;
                padding: 5px;
            }
            #column_right a {
                display: inline-block;
                text-align: center;
                border: 1px solid #EEEEEE;
                cursor: pointer;
                margin: 5px;
                padding: 5px;
            }
            #column_right a.selected {
                border: 1px solid #7DA2CE;
                background: #EBF4FD;
            }
            #column_right input {
                display: none;
            }
            #dialog {
                display: none;
            }
            .button {
                display: block;
                float: left;
                padding: 8px 5px 8px 25px;
                margin-right: 5px;
                background-position: 5px 6px;
                background-repeat: no-repeat;
                cursor: pointer;
            }
            .button:hover {
                background-color: #EEEEEE;
            }
            .thumb {
                padding: 5px;
                width: 105px;
                height: 105px;
                background: #F7F7F7;
                border: 1px solid #CCCCCC;
                cursor: pointer;
                cursor: move;
                position: relative;
            }
            div img { 
                max-width:120px; 
                width:117px; 
                width:expression(document.body.clientWidth>600?"600px":"auto"); 
                overflow:hidden; 
            } 

            /* jQuery Maxlength plugin 需要的 class */
            .notification {
                border:3px solid #d55b5b;
                background-color: #ffcdcd;
                padding:5px;
            }
            .smaple1 {
                width: 300px;
            }

        </style>
    </head>
    <body>

        <div id="container">
            <div id="menu">

                <a id="delete" class="button" style="background-image: url('resources/js/jquery/image/filemanager/edit-delete.png');">刪除</a>
                <a id="upload" class="button" style="background-image: url('resources/js/jquery/image/filemanager/upload.png');">上傳</a>
                <a id="rename" class="button" style="background-image: url('resources/js/jquery/image/filemanager/edit-rename.png');">修改說明</a>
                <a id="refresh" class="button" style="background-image: url('resources/js/jquery/image/filemanager/refresh.png');">刷新</a>
                <a id="close" class="button" style="background-image: url('resources/cbg-include/images/filemanager/close.gif');">關閉</a>
			
            </div>
            <div id="column_right" style="position: absolute; left:8.5px; top:35px; height: 90%; width: 98%; "></div>

        </div>
 
        <script type="text/javascript"><!--

            var message = new Array();//宣告讀取的訊息
            var name = new Array();//宣告讀取的檔名
            $(function() {
            	
               //自動載入照片
               $.ajax({
                    url: 'shopintropic/shop/loadallpic',
                    type: 'POST',
                    data: '',
                    dataType: 'json',
                    error: function(xhr, textStatus, errorThrown) {
						alert('An error occurred! ' + ( errorThrown ? errorThrown : xhr.status ));
	   				},
	   				success: function(json, textStatus){
	   					
		   				if(textStatus=='success'){
		   					html = '<div>';
	                        if (json != null && json.thumb.length >0 ) {
	                            for ( var i = 0; i < json.thumb.length; i++) {
	                                message [i]   = json.title[i]; //把讀取的訊息放在message裡
	                                name [i] = json.filename[i];//把讀取的檔名放到 name裡
						 			//alert(json.thumb[i]);
	                                html += '<a file="' + json.filename[i] +'"><img src="' + json.thumb[i] + '"/><div align="left" ><font color="#0066FF"><spring:message code="LAB1019"/></font><br/>'+ json.title[i] +'</div></a>';
	                            }
	                        }
	                        html += '</div>';
	                        $('#column_right').html(html);
		   				} else {
		   					alert('<spring:message code="LAB1012"/>');
		   				 	window.location.reload();
		   				}
		   			},
		   			complete: function(data){
		   				//alert('complete');
		   			}
                });
	
	
                //按一下圖片選取
                $('#column_right a').live('click', function () {

                    if ($(this).attr('class') == 'selected') {
			
                        $(this).removeAttr('class');
                    } else {
                        $('#column_right a').removeAttr('class');
			
                        $(this).attr('class', 'selected');
                    }
                });
	

                //連按兩下圖片，目前無用途
                $('#column_right a').live('dblclick', function () {
				    
                });		
	  
					
                //刪除照片
                $('#delete').bind('click', function () {
                    if( $('#column_right a.selected').attr('file') != undefined)
                    {	
                        answer=window.confirm('<spring:message code="LAB1020" />');
                        if(answer){
			
                            path = $('#column_right a.selected').attr('file');	
                            if (path) {
                                $.ajax({
                                    url: 'shopintropic/shop/deleteshoppic',
                                    type: 'POST',
                                    data: 'path=' + path, //path：檔名
                                    dataType: 'json',
                                    success: function(json) {
                                        if (json.success) {
						
                                            //alert('<spring:message code="delSuccess" />');
                                            window.location.reload();
                                        }
					
                                        if (json.error) {
                                            alert('<spring:message code="delError" />');
                                            window.location.reload();
                                        }
                                    }
                                });				
                            } 
		
                        }else{
                            //alert("刪除取消");
                        }

                    }else{
                        alert('<spring:message code="LAB1016" />');
                    }
                });
	
                //修改
                $('#rename').bind('click', function () {
                    if( $('#column_right a.selected').attr('file') != undefined)
                    {
		
                        $('#dialog').remove();
				
                        var t ; //選取的那一個檔案的迴圈數
		
                        for(var i=0 ;i<name.length;i++){	
                        	message[i]=message[i].replace(/<br>/g,'\n');//換行轉回去 
                            if($('#column_right a.selected').attr('file') == name[i]) //當選取的檔名 = 讀取的檔名時，把讀取的那一個陣列數放到t
                            {
                                t = i;
                            }
                        }
		
                        
                        html  = '<div id="dialog" >';	
                        html += '<spring:message code="LAB1014" />:<br/><textarea name="name" id="name" wrap="off"  ROWS=12 COLS=47 >'+message[t]+'</textarea><input type="button" value="<spring:message code="LAB1015" />" />';	//message[t]讀出選取的說明內容放到textarea裡
                        html += '</div>';
		
                        $('#column_right').prepend(html);//建立以上的html

                        /*設定textarea的maxlength*/
                        // 長度最多 100 字, 訊息狀態部份採用滑出入方式
                        $(document).ready(function(e) {	
                            $("#name").maxlength({
                                maxCharacters: 45, /*字數*/
                                slider: true /*是否顯示還仍可輸入幾個字元*/
                            });
                        });
		
                        $('#dialog').dialog({
                            title: '<spring:message code="LAB1013" />',
                            resizable: false
                        });
		
                        $('#dialog input[type=\'button\']').bind('click', function () {
                            path = $('#column_right a.selected').attr('file');
							
                            if (path) {		
                            	//alert(path);
                                $.ajax({
                                    url: 'shopintropic/shop/updatepicmessage',
                                    type: 'POST',
                                    data: 'path=' + path + '&name=' + $('#dialog textarea[name=\'name\']').val(), //回傳 → path：檔名,name：訊息內容。
                                    dataType: 'json',
                                    success: function(json) {
                                        if (json.success) {
                                            $('#dialog').remove();
							
                                            //alert('<spring:message code="LAB1017" />');
                                            window.location.reload();
                                        } 
						
                                        if (json.error) {
                                            alert('<spring:message code="LAB1018" />');
                                            window.location.reload();
                                        }
                                    }
                                });			
                            }
                        });
                    }else{
                        alert('<spring:message code="LAB1016" />');
                    }		
                });

                
                //照片上傳
                new AjaxUpload('#upload', {
                    action: 'shopintropic/shop/shopallpictureup',
                    name: 'image',
                    autoSubmit: true,
                    responseType: 'json',
                    onChange: function(file, extension) {// 文件被上傳完成前觸發
                        this.submit();
                    },	
                    onSubmit: function(file, extension) {// 限制檔案副檔名         
                        if (extension && /^(jpg|png|gif)$/.test(extension))
                        {  
                        }  
                        else  
                        {  
                            alert('<spring:message code="LAB1004" />');
                            window.location.reload();
                            return false;  
                        }  
                    },
                    onComplete: function(file, json) { //接收json訊息顯示結果
                        //接收success的json
                    	//alert(json.msg);
                        window.location.reload();
                    }
		
                });

                //刷新
                $('#refresh').bind('click', function () {
                    window.location.reload();
		
                });	

                //關閉
                $('#close').bind('click', function () {
                	parent.location.reload();
                    parent.$('#dialog').dialog('close');
                    parent.$('#dialog').remove();
                   
                    
                });		
	
            });

            //--></script>
    </body>

</html>
