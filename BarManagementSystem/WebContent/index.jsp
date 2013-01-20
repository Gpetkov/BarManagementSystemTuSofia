<%@page import="com.tu.university.barmanagement.model.User"%>
<%@page import="com.tu.university.barmanagement.controler.UserControler"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if(request.isUserInRole("barman"))
		{
			response.sendRedirect("http://localhost:8080/BarManagementSystem/Barman/desktop.html");
		}else
		{
			if(request.isUserInRole("manager"))
			{
				response.sendRedirect("http://localhost:8080/BarManagementSystem/Manager/desktop.html");
			}
			else
			{
				response.sendRedirect("http://localhost:8080/BarManagementSystem/Waiter/desktop.html");
			}
		}
	%>
</body>
</html>