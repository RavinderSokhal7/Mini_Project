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
			<form method="post" action="edit-book">
				<h3 style="margin:10px 0;font-size:24px">Edit Books</h3>
				<select name="bookid" style="padding:8px 10px;width:87.5%;border: 1px solid #999;" required>
					<option selected disabled value="">-- Select a Book --</option>
					<c:if test="${not empty books}">
					    <c:forEach items="${books}" var="book">
							<option value="${book.getID()}">${book.getName()} - ${book.getAuthor()}</option>
						</c:forEach>
					</c:if>
				</select>
				<div style="margin:10px 0">
					<label style="display:block">Total Copies</label>
					<input type="number" name="total" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" placeholder="10" required>
				</div>
				<div style="margin:10px 0">
					<label style="display:block">Remaining Copies</label>
					<input type="number" name="rem" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" placeholder="10" required>
				</div>
				<input type="submit" value="Select" style="width:12%;margin:0;padding:8px 10px;">
				<p id="errorMessage" <c:if test="${status == false}">style="color:red"</c:if><c:if test="${status == true}">style="color:green"</c:if>>${errorMessage}</p>
			</form>
			<jsp:include page="components/BackToPreviewPageComp.jsp"></jsp:include>
		</div>
		
	</div>
		
	<div style="clear:both"></div>
</div>

<jsp:include page="footerComp.jsp"></jsp:include>

</body>
</html>