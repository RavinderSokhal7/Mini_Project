    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration Form</title>
    </head>
    <body>
    <%
    	/* int k = 1/0; */
    	if(session.getAttribute("username")!=null){
    		response.sendRedirect("home.jsp");
    	}
    %>
    <h1>Register Form</h1>
    <form action="register" method="post">
    			<table style="with: 50%">
    				<tr>
    					<td>First Name</td>
    					<td><input type="text" name="first_name" placeholder= "Enter First name" required/></td>
    				</tr>
    				<tr>
    					<td>Last Name</td>
    					<td><input type="text" name="last_name" /></td>
    				</tr>
    				<tr>
    					<td>UserName</td>
    					<td><input type="text" name="username" placeholder= "Enter Username" required/></td>
    				</tr>
    					<tr>
    					<td>Password</td>
    					<td><input type="password" name="password" placeholder= "Enter Password" required /></td>
    				</tr>
    				<tr>
    					<td>Address</td>
    					<td><input type="text" name="address" /></td>
    				</tr>
    				<tr>
    					<td>Contact No</td>
    					<td><input type="text" name="contact" /></td>
    				</tr></table>
    			<input type="submit" value="Submit" />
    			</form>
    			Already Registered! <a href="index.jsp">Login Here</a>
    </body>
    </html>