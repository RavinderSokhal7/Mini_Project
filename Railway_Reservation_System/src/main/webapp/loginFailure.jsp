<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Failure</title>
</head>
<body>
<%
    	if(session.getAttribute("username")!=null){
    		response.sendRedirect("home.jsp");
    	}
    %>
	<h1>FAILED to LOG IN</h1>
	Not Yet Registered! <a href='registerform.jsp'>Register Here</a>
	<br>Already Registered! <a href='index.jsp'>Try Again Here</a>
</body>
</html>