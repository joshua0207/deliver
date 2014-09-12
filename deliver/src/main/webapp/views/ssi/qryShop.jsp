<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

 
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
			var MSG0060 = '<spring:message code="LAB0060" />';
			var MSG0061 = '<spring:message code="MSG0061" />';
 			
 			function setDataToDB() {
 				$('#rgsForm').attr('action', 'shopbasemanager/shop/updateAccountPwd');
				$('#rgsForm').submit();
 			}

 			function pageOnload() {
 				$("#chkForm").click(function() {
 					if ($("#rgsForm").valid()) setDataToDB();
 				});
 				$.validator.addMethod("chkEngOrNum", function(value, element) {
 					var re = /^([a-zA-Z0-9]+)$/;
 					return this.optional(element) || (re.test(value));
 				});
 				$.validator.addMethod("notEqualTo", function(value, element, param) {
 					return value != $(param).val();
 				});
 				 event: 'blur',
 				$('#rgsForm').validate({
 					rules: {
 						oldPassword: {
 							required: true,
 							rangelength: [4, 30]
 						},
 						newPassword: {
							required: true,
							rangelength: [4, 30],
							equalTo: '#confirmPassword',
							chkEngOrNum: true,
							notEqualTo: '#shopId'
						},
						confirmPassword: {
							required: true,
							rangelength: [4, 30],
							equalTo: '#newPassword',
							chkEngOrNum: true
						}
 					},
 					messages: {
 						newPassword: {
 							notEqualTo: LAB0060
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
 					}
 				}); 
 				$('#tb').css("padding", "0px");
 				
 			}
 			
		-->
		</script>