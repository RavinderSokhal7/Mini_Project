<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true" errorPage="error.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Webteam">
<title>Preview | RCL</title>
<spring:url value="/resources/css/index.css" var="indexCss" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${indexCss}" rel="stylesheet" type="text/css" />
</head>

<body>

<jsp:include page="HeaderComp.jsp"></jsp:include>

<div id="page">
	<div id="content">
		<div id="title"><h2>${category }</h2>
		<span class="welcome">Welcome,</span></div>
		<div class="body" align="center">

			<c:forEach items="${components }" var="component">
				<jsp:include page="components/${component }"></jsp:include>
			</c:forEach>
			
			<c:if test="${category == 'Railway Reservation System' }">
			<c:if test="${not empty bookTicket }">
			<div class="body" align="center">
			<fieldset class="box" >
				<form action="book-train-ticket-from-constructed-page" method="post" >
					<h3 style="margin:10px 0 auto 10px;font-size:24px;align:center;">Fill Form to book ticket</h3>
					<c:forEach items="${bookTicket }" var="component">
						<jsp:include page="components/${component }"></jsp:include>
					</c:forEach>
				</form>
				</fieldset></div>
			</c:if>
			</c:if>
			<c:if test="${status == false }">
				<p id="errorMessage" style="color:red">${errorMessage }</p>
			</c:if>
		</div>
	</div>
		
	<div style="clear:both"></div>
</div>

<jsp:include page="footerComp.jsp"></jsp:include>

</body>
</html>