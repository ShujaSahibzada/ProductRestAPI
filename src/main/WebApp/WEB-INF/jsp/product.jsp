<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product List - Zaryans Spring Boot Web Application</title>
</head>
<body>
    <h1 align="center">My Product List</h1>
    <br/>
    <table border="1" cellpadding="10">
        <tr>
            <th>Id</th><th>Name</th><th>Price</th>
        </tr>
        <c:forEach var="product" items="${product}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
        </tr>    
        </c:forEach>
    </table>
</body>
</html>