<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true" errorPage="error.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Webteam">
<title>Preview | CBSE</title>
<spring:url value="/resources/css/index.css" var="indexCss" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${indexCss}" rel="stylesheet" type="text/css" />
</head>

<body>

<jsp:include page="HeaderComp.jsp"></jsp:include>
<div id="page">
	<div id="content">
		<div id="title"><h2>${category }</h2>
		<span class="welcome">Welcome,</span>
		</div>
		<div class="body" align="center">
		
			<form action="book-train-ticket">
				
				<jsp:include page="components/SelectDateComp.jsp"></jsp:include>
				<jsp:include page="components/SelectClassComp.jsp"></jsp:include>
			</form>
			<p>${train.train_no } ${train.available }</p>
		
		</div>		
	</div>
		
	<div style="clear:both"></div>

</div>

<jsp:include page="footerComp.jsp"></jsp:include>

</body>
</html>