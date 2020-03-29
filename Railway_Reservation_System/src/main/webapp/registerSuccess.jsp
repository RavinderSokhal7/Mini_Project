<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>log in</title>
</head>
<body>
	Successfully Registered!
<br>Please Login Here<br>
<form method="post" action="login">
           <table style="with: 50%">
           <tr>
               <td>User Name</td>
               <td><input type="text" name="username" value="" /></td>
           </tr>
           <tr>
               <td>Password</td>
               <td><input type="password" name="password" value="" /></td>
           </tr>
           <tr>
               <td><input type="submit" value="Login" /></td>
               <td><input type="reset" value="Reset" /></td>
           </tr>
           <tr>
               <td colspan="2">Not Yet Registered!! <a href="registerform.jsp">Register Here</a></td>
           </tr>
           </table>
        </form>
</body>
</html>