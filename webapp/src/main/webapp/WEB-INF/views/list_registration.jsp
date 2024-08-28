<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@include file="menu.jsp" %>
   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>list_registration</title>
</head>
<body>
<h2>All Registrations</h2>
<table>
    <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
            <th>Mobile</th> 
            <th>Delete</th>   
            <th>Update</th>   
    </tr>
   <c:forEach items="${registrations}" var="registration">
   <tr>
       <td>${registration.firstName}</td>
       <td>${registration.lastName}</td>
       <td>${registration.email}</td>
       <td>${registration.mobile}</td>
       <td><a href="delete?id=${registration.id}">delete</a></td>
        <td><a href="get-by-id?id=${registration.id}">update</a></td>
    </tr>
   </c:forEach>
</table>
    
</body>
</html>