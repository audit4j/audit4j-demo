<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Audit4j Demo Application</title>
</head>
<body>

	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>


	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h4 align="right">
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h4>
	</c:if>
	
	<h3>Test Without Annotation</h3>
	<p>
		Please see <a
			href="http://audit4j.org/documentation/#sending_events_call">this</a>
		documentation section for more details.
	<p>

		Example from:
		<code>org.audt4j.demo.spring.service.impl.UserServiceImpl</code>


	<h4>
		<a href="/testAddUser">Click here to test</a>
	</h4>
	<br />
	
	

</body>
</html>