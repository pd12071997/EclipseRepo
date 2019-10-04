<%@page import="com.lti.model.Product" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<%
	List<Product> list = (List<Product>) session.getAttribute("listOfProducts");
	for(Product p : list) {
%>
	<tr>
		<td><%= p.getId() %></td>
		<td><%= p.getName() %></td>
		<td><%= p.getPrice() %></td>
		<td><%= p.getQuantity() %></td>
	</tr>
<%
	}
%>
<tr>
<td>
<a href="ProductServlet?action=prev">Prev</a> | <a href="ProductServlet?action=next">Next</a>
</td>
</tr>
</table>
</body>
</html>