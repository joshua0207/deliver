<%
	response.setHeader("Cache-Control","no-cache,no-store,Must-revalidate");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Expires","Mon, 26 Jul 1997 05:00:00 GMT");
	response.setDateHeader ("Expires", 0);
%>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache,no-store,Must-revalidate">
<meta http-equiv="Expires" content="Mon, 26 Jul 1997 05:00:00 GMT">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<base href="<%=request.isSecure() ? "https" : "http"%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">



<script src="<c:url value="/resources/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/common.js"/>"></script>
