<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<%@include file="/views/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exception</title>
	<script type="text/javascript">
	$(function(){
		alert('系統錯誤，請恰系統管理員 ﹗﹗'+'<c:out value="${exception}" />');
		//history.go(-1);
		location.href='<c:url value="/loginadmin/index" />';
	});
	</script>
</head>
<body>
	${exception}
</body>
</html>