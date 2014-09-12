<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/jquery/themes/ui-lightness/ui.all.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery-1.4.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/ui/ui.core.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/ajaxupload.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/ui/ui.draggable.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/ui/ui.resizable.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/ui/ui.dialog.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.bgiframe.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.maxlength.js"/>"></script>
<script type="text/javascript">
function image_upload() {

	$('#dialog').remove();
	
	$('#cont_left').prepend('<div id="dialog" style="padding: 3px 0px 0px 0px;"><iframe src="shopintropic/shop/storeallpicturecore" style="padding:0; margin: 0; display: block; width: 100%; height: 100%;" frameborder="no" scrolling="auto"></iframe></div>');

	$('#dialog').dialog({

		title: '<spring:message code="LAB1011"/>',

		close: function (event, ui) {

		parent.location.reload();

		},	

		bgiframe: false,

		width: 900,

		height: 530,

		resizable: false,

		modal: false,
		
		draggable:true

	});
	
};
</script>