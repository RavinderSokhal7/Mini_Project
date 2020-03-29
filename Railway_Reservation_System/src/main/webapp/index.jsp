<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login form</title>
    </head>
    <body>
    <%
    	if(session.getAttribute("username")!=null){
    		response.sendRedirect("home.jsp");
    	}
    %>
        <form method="post" action="login">
           <table style="with: 50%">
           <tr>
               <td>User Name</td>
               <td><input type="text" name="username" value="" placeholder= "Enter Username" required/></td>
           </tr>
           <tr>
               <td>Password</td>
               <td><input type="password" name="password" value="" placeholder= "Enter Password" required /></td>
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