<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Webteam">
<spring:url value="/resources/css/index.css" var="indexCss" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${indexCss}" rel="stylesheet" type="text/css" />
</head>

<body>
	<form action="select-date" method = "post" >
		<label for="date_sel">Select Date: </label>
		<input type="date" id="date_sel" name="selected_date" onchange="#">
	</form>
<!-- <button onclick="myFunction()">Select Date</button>
<script>
function myFunction() {
  var x = document.createElement("INPUT");
  x.setAttribute("type", "date");  
  document.body.appendChild(x);
}
</script> -->

</body>
</html>