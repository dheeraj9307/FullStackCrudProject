<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
  <%@include file="menu.jsp" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>registration</title>
</head>
<body>
	<h2>Update Registration...</h2>
	<form action="updateReg" method="post">
		<pre>
		<input type="hidden" name="id" value="${registration.id}"/>
          First Name <input type="text"name="firstName"value="${registration.firstName}"/>
          Last Name<input type="text"name="lastName"value="${registration.lastName}"/>
          Email<input type="text"name="email"value="${registration.email}"/>
          Mobile<input type="text"name="mobile"value="${registration.mobile}"/>
          <input type="submit" value="update"/>
        </pre>
	</form>
	${msg}
	${information}
</body>
</html>

