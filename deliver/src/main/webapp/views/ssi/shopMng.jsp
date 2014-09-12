<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%//@ page import="ctp.vo.shopadmin.shopBaseManagerVO"%>
<%//@ page import="ctp.util.sysParameter.sysparameterutil.sysParameterUtil"%>
<%//@ page import="org.apache.commons.lang.StringUtils"%>
<%
	/* shopBaseManagerVO shopBaseMngVO = (shopBaseManagerVO)request.getAttribute("shopBaseData");

    sysParameterUtil spu1 = new sysParameterUtil();
    String secrtName1 = StringUtils.trimToEmpty(spu1.getCPValue("secrtName").trim());
    spu1=null;
    String hostName1 = request.getServerName();
    String headHttps1 = ""; 
    if(hostName1.equalsIgnoreCase(secrtName1)){
	   headHttps1 = "https://";	        
    } else {
	   headHttps1 = "http://";	     
    }   */
%>		
 
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
		<script language="javascript" type="text/javascript" src="<c:url value="/resources/js/addr.js"/>"></script> 
        <script type="text/javascript" src="<c:url value="/resources/js/googleMap/static.js"/>"></script> 
        <script type="text/javascript" src="<c:url value="/resources/js/googleMap/googleMapFunction.js"/>"></script> 
		<script src="<c:url value="/resources/cbg-include/Scripts/colors.js"/>" type="text/javascript" ></script>

		<script language="javascript">
		<!--
		
		$(document).ready(function() {
	        
	    });
		
			var MSG0022 = '<spring:message code="MSG0022" />';
			var MSG0045 = '<spring:message code="MSG0045" />';
			var MSG0046 = '<spring:message code="MSG0046" />';	
			var MSG0047 = '<spring:message code="MSG0047" />';	
			var MSG0057 = '<spring:message code="MSG0057" />';
			var LAB0060 = '<spring:message code="LAB0060" />';
			var MSG0061 = '<spring:message code="MSG0061" />';
					
 			function setValToField()
			{		
				<%-- setOpSlcedByKeyWord("cityAddrC",'<%//=shopBaseMngVO.getAddrCCity()%>');
				changeZone("cityAddrC","cantonAddrC");	
				setOptionSelected("cantonAddrC",'<%//=shopBaseMngVO.getAddrCCanton()%>');
				//$('#detailAddrC').val('<%//=shopBaseMngVO.getAddrCDetail()%>'); --%>	
				
				setOpSlcedByKeyWord("cityAddrL",'${shopBaseData.addrLCity}');
				changeZone("cityAddrL","cantonAddrL");	
				setOptionSelected("cantonAddrL",'${shopBaseData.addrLCanton}');	
				//$('#detailAddrL').val('<%//=shopBaseMngVO.getAddrLDetail()%>');
				
				//統一發票二聯式，三䏈式
				<%-- if( "0" == "<%//=shopBaseMngVO.getInvoiceType()%>"){
					$(".chkInvoice:eq(0)").attr("checked",true);
					$(".chkInvoice:eq(1)").attr("checked",false);
				}	
				else if( "1" == "<%//=shopBaseMngVO.getInvoiceType()%>"){
					$(".chkInvoice:eq(0)").attr("checked",false);
					$(".chkInvoice:eq(1)").attr("checked",true);
					$("#divInvoiceData").slideDown();
				} --%>	
			}
 			
 			//地址複制
 			/* function setValueAddrAToAddrB() {
 				var strACityValue;
 				var strACanTonValue;
 				strACityValue = getOptionValueById("cityAddrC");
 				strACanTonValue = getOptionValueById("cantonAddrC");
 				setOptionSelected("cityAddrL", strACityValue);
 				changeZone("cityAddrL", "cantonAddrL");
 				setOptionSelected("cantonAddrL", strACanTonValue);
 				setValueByAtoB_chk('detailAddrC', 'detailAddrL', 'eqAddrC');
 			} */
 			function setDataToDB() {
 				var geocoder = new google.maps.Geocoder();
 				var aryStr = new Array();
 				var strTemp = "";
 				var dblInitLat = 0;
 				var dblInitLng = 0;
 				/* aryStr[aryStr.length] = $('#cityAddrC').val();
 				aryStr[aryStr.length] = $('#cantonAddrC').val();
 				aryStr[aryStr.length] = $('#detailAddrC').val(); */
 				aryStr[aryStr.length] = $('#cityAddrL').val();
 				aryStr[aryStr.length] = $('#cantonAddrL').val();
 				aryStr[aryStr.length] = $('#detailAddrL').val();
 				strTemp = aryStr.join("");
 				geocoder.geocode({
 					'address': strTemp
 				}, function(results, status) {
 					if (status == google.maps.GeocoderStatus.OK) {
 						var location;
 						var htmlObjHdn;
 						location = results[0].geometry.location;
 						dblInitLat = location.lat();
 						dblInitLng = location.lng();
 						//alert(dblInitLat);
 						//alert(dblInitLng);
 						$('#addrCLat').val(dblInitLat);
 						$('#addrCLng').val(dblInitLng);
 						htmlObjHdn = document.createElement("input");
 						htmlObjHdn.setAttribute("type", "hidden");
 						htmlObjHdn.setAttribute("id", "strAllShopTypeName");
 						htmlObjHdn.setAttribute("name", "strAllShopTypeName");
 						htmlObjHdn.setAttribute("value", getTitleFormChkBoxByName("myShopTypeId"));
 						$('#rgsForm').append(htmlObjHdn);
 						$('#rgsForm').attr('action', 'shopbasemanager/shop/updateShopData');
 						$('#rgsForm').submit();
 					} else if (status == google.maps.GeocoderStatus.ZERO_RESULTS) {
 						alert(MSG0047);
 					}
 				});
 			}

 			function chkInvoiceField() {
 				/* alert('test');
 				var blChk = false;
 				var id = $('input[name=invoiceType]:checked').val();
 				if (id == 1) {
 					if ($("#invoiceName").val().trim() == "") {
 						alert(MSG0057)
 					} else if ($("#identNum").val() == "" || !checkIdentNum($("#identNum").val())) alert(MSG0022);
 					else blChk = true
 				} else if (id == 0) {
 					blChk = true
 				} */
 				var blChk = true;
 				return blChk;
 			}
 			function chkCoNum() {
 				var blChk = true;
 				var strCoNum = $("#coNum").val() + "";
 				/*if (strCoNum.trim() == "" || checkIdentNum(strCoNum)) blChk = true;
 				else alert(MSG0022); */
 				if (strCoNum.trim() != ""){
 					if(checkIdentNum(strCoNum)){
 						blChk = true;
 					}else{
 						alert(MSG0022);
 						blChk = false;
 					}
 				}
 				return blChk;
 			}

 			function pageOnload() {
 				$("#chkForm").click(function() {
 					//alert(chkCoNum());
 					if (chkInvoiceField() && chkCoNum()) if ($("#rgsForm").valid()) setDataToDB();
 				});
 				/* $.validator.addMethod("chkEngOrNum", function(value, element) {
 					var re = /^([a-zA-Z0-9]+)$/;
 					return this.optional(element) || (re.test(value));
 				});
 				$.validator.addMethod("notEqualTo", function(value, element, param) {
 					return value != $(param).val();
 				});  */
 				 event: 'blur',
 				$('#rgsForm').validate({
 					rules: {
 						/* userPw: {
 							required: true,
 							rangelength: [4, 30],
 							equalTo: '#cnfUserPw',
 							chkEngOrNum: true,
 							notEqualTo: '#shopId'
 						},
 						cnfUserPw: {
 							required: true,
 							rangelength: [4, 30],
 							equalTo: '#userPw',
 							chkEngOrNum: true
 						}, */
 						coName: {
 							required: true,
 							rangelength: [1, 30]
 						},
 						coPeople: {
 							required: true,
 							rangelength: [1, 30]
 						},
 						detailAddrL: {
 							required: true,
 							rangelength: [3, 100]
 						},
 						/* detailAddrC: {
 							required: true,
 							rangelength: [3, 100]
 						}, */
 						tel: {
 							required: true,
 							rangelength: [3, 13],
 							digits: true
 						},
 						mobilePhone: {
 							required: true,
 							rangelength: [10, 13],
 							digits: true
 						},
 						telOrder: {
 							required: true,
 							rangelength: [3, 13],
 							digits: true
 						},
 						email: {
 							email: true
 						},
 						myShopTypeId: {
 							required: true,
 							minlength: 1
 						}
 					},
 					messages: {
 						myShopTypeId : {
 							required : "請至少勾選一個項目"
 						}
 						/* userPw: {
 							chkEngOrNum: MSG0061,
 							equalTo: MSG0045,
 							notEqualTo: LAB0060
 						},
 						cnfUserPw: {
 							chkEngOrNum: MSG0061,
 							equalTo: MSG0046
 						} */
 					},
 					errorPlacement: function(error, element) { 
 					    if ( element.is(":radio") ) 
 					        error.appendTo ( element.parent() ); 
 					    else if ( element.is(":checkbox") ) 
 					        error.appendTo ( element.parent() ); 
 					    else if ( element.is("input[name=captcha]") ) 
 					        error.appendTo ( element.parent() ); 
 					    else 
 					        error.insertAfter(element); 
 					} 
 				}); 
 				$('#tb').css("padding", "0px");
 				//initCounty("cityAddrC");
 				initCounty("cityAddrL");
 				/* $('#cityAddrC').change(function() {
 					changeZone('cityAddrC', 'cantonAddrC');
 				}); */
 				$('#cityAddrL').change(function() {
 					changeZone('cityAddrL', 'cantonAddrL');
 				});
 				$('#equalsTel').click(function() {
 					setValueByAtoB_chk('tel', 'telOrder', 'equalsTel');
 				});
 				/* $('#eqAddrC').click(function() {
 					if ($(this).attr("checked")) setValueAddrAToAddrB();
 				}); */
 				/* $(".chkInvoice").click(function() {
 					var id = $(this).val();
 					if (id == 1) {
 						$("#divInvoiceData").slideDown();
 					} else if (id == 0) {
 						$("#divInvoiceData").slideUp();
 					}
 				}); */
 			}
 			
 			//備份壓縮
 			//eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--){d[e(c)]=k[c]||e(c)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('6 1n(){5 H;5 I;H=U("k");I=U("M");S("i",H);N("i","J");S("J",I);1k(\'u\',\'1e\',\'1c\')}6 P(){5 18=X C.B.1G();5 d=X 1D();5 K="";5 D=0;5 E=0;d[d.F]=$(\'#k\').8();d[d.F]=$(\'#M\').8();d[d.F]=$(\'#u\').8();K=d.1E("");18.1I({\'1J\':K},6(14,L){7(L==C.B.15.1O){5 n;5 g;n=14[0].1N.n;D=n.1M();E=n.1C();$(\'#1L\').8(D);$(\'#1P\').8(E);g=1B.1t("O");g.t("1s","1r");g.t("f","16");g.t("Z","16");g.t("m",1u("1q"));$(\'#l\').1z(g);$(\'#l\').1j(\'1y\',\'1x.1v?1w=1K\');$(\'#l\').25()}h 7(L==C.B.15.27){p(28)}})}6 V(){5 e=12;5 f=$(\'O[Z=2e]:1p\').8();7(f==1){7($("#2f").8().11()==""){p(2j)}h 7($("#19").8()==""||!Y($("#19").8()))p(17);h e=2}h 7(f==0){e=2}r e}6 Q(){5 e=12;5 z=$("#2g").8()+"";7(z.11()==""||Y(z))e=2;h p(17);r e}6 26(){$("#1V").q(6(){7(V()&&Q())7($("#l").1U())P()});$.1a.W("j",6(m,v){5 R=/^([a-1T-1R-9]+)$/;r G.1S(v)||(R.1Y(m))});$.1a.W("w",6(m,v,T){r m!=$(T).8()});20:\'2d\',$(\'#l\').1Z({21:{y:{b:2,c:[4,s],o:\'#A\',j:2,w:\'#22\'},A:{b:2,c:[4,s],o:\'#y\',j:2},24:{b:2,c:[1,s]},1X:{b:2,c:[1,s]},1e:{b:2,c:[3,1h]},u:{b:2,c:[3,1h]},1i:{b:2,c:[3,13],x:2},1W:{b:2,c:[10,13],x:2},1m:{b:2,c:[3,13],x:2},1l:{1l:2},1q:{b:2,2k:1}},2h:{y:{j:1d,o:2i,w:29},A:{j:1d,o:2a}}});$(\'#2b\').1Q("2c","1A");1f("k");1f("i");$(\'#k\').1g(6(){N(\'k\',\'M\')});$(\'#i\').1g(6(){N(\'i\',\'J\')});$(\'#1b\').q(6(){1k(\'1m\',\'1i\',\'1b\')});$(\'#1c\').q(6(){7($(G).1j("1p"))1n()});$(".1F").q(6(){5 f=$(G).8();7(f==1){$("#1o").1H()}h 7(f==0){$("#1o").23()}})}',62,145,'||true|||var|function|if|val|||required|rangelength|aryStr|blChk|id|htmlObjHdn|else|cityAddrL|chkEngOrNum|cityAddrC|rgsForm|value|location|equalTo|alert|click|return|30|setAttribute|detailAddrC|element|notEqualTo|digits|userPw|strCoNum|cnfUserPw|maps|google|dblInitLat|dblInitLng|length|this|strACityValue|strACanTonValue|cantonAddrL|strTemp|status|cantonAddrC|changeZone|input|setDataToDB|chkCoNum|re|setOptionSelected|param|getOptionValueById|chkInvoiceField|addMethod|new|checkIdentNum|name||trim|false||results|GeocoderStatus|strAllShopTypeName|MSG0022|geocoder|identNum|validator|eqTelOrder|eqAddrC|MSG0061|detailAddrL|initCounty|change|100|tel|attr|setValueByAtoB_chk|email|telOrder|setValueAddrAToAddrB|divInvoiceData|checked|myShopTypeId|hidden|type|createElement|getTitleFormChkBoxByName|do|method|shopBaseManagerAction|action|append|0px|document|lng|Array|join|chkInvoice|Geocoder|slideDown|geocode|address|updateShopData|addrCLat|lat|geometry|OK|addrCLng|css|Z0|optional|zA|valid|chkForm|mobilePhone|coPeople|test|validate|event|rules|shopId|slideUp|coName|submit|pageOnload|ZERO_RESULTS|MSG0047|LAB0060|MSG0046|tb|padding|blur|invoiceType|invoiceName|coNum|messages|MSG0045|MSG0057|minlength'.split('|'),0,{}))
			
		-->
		</script>