<%@page import="factories.UserDaoFactory"%>
<%@page import="interfaces.UserDao"%>
<%@page import="models.UserBO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" errorPage = "error.jsp"%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Success</title>
</head>
<body>
<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");//For HTTP 1.1
	response.setHeader("Pragma","no-cache");
	response.setHeader("Expires", "0");//for Proxy cache removal
	if(session.getAttribute("username")==null){
		response.sendRedirect("index.jsp");
	}
	//String username = (String)session.getAttribute("username");
	UserDao udao = UserDaoFactory.getInstance();
	UserBO user = new UserBO();
	user = udao.getUserDetails((String)session.getAttribute("username"));
	
%>
		<%-- <h1>${user.username} LOGGED IN</h1>
		<h2>welcome ${user.firstname} ${user.lastname}</h2>
		<p>Address :: ${user.address}</p><p>Contact No :: ${user.contactno}</p> --%>
		<h1><%= user.getUsername() %> LOGGED IN</h1>
		<h2>welcome <%= user.getFirstname() %> <%= user.getLastname() %></h2>
		<p>Address :: <%= user.getAddress() %></p><p>Contact No :: <%= user.getContactno() %></p>
		<br><a href='logout.jsp'>Log out</a>
</body>
</html>