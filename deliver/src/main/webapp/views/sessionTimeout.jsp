<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HttpSessionRequiredException</title>
<script src="<c:url value="/resources/js/jquery.js"/>"></script>
<script>
		alert('<spring:message code="MSG.SessionTimeout" />');
		top.location = '/portal/login';
	
</script>
</head>
<body>
</body>
</html>