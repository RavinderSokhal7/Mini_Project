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
		<span class="welcome">Welcome,</span>
		</div>
		<div class="body" align="center">
			<fieldset class="box" >
			<form action="book-train-ticket" method="post" >
			<table id="books">
			
			<thead>
				<tr>
					<th width="180px">Train No.</th><th width="600px">Train Name</th>
					<th width="200px">From Station</th><th width="80px">station no</th><th width="80px">From arrival time</th>
					<th width="80px">From departure time</th><th width="200px">To Station</th><th width="80px">station no</th>
					<th width="80px">To arrival time</th><th width="80px">To departure time</th>
					
				</tr>
			</thead>
			
			<tbody>
					<c:if test="${not empty ts}">
							<tr><th width="180px"><input type="text" name="train_no" value="${ts.train_no}" style="width:75%;" readonly/></th>
							<th width="300px">${ts.train_name }</th>
							<th width="200px">${ts.from }</th><th width="80px">${ts.from_no }</th><th width="80px">${ts.from_arr }</th>
							<th width="80px">${ts.from_dept }</th><th width="220px">${ts.to }</th><th width="80px">${ts.to_no }</th>
							<th width="80px">${ts.to_arr }</th><th width="80px">${ts.to_dept }</th>
							</tr>
					</c:if>
					<c:if test="${status == false }">
						<p id="errorMessage" style="color:red">${errorMessage }</p>
					</c:if>
			</tbody>
			</table>
			
			<h3 style="margin:10px 0 auto 10px;font-size:24px;align:center;">Fill Form to book ticket</h3>
			
				
				<jsp:include page="components/SelectDateComp.jsp"></jsp:include>
				<jsp:include page="components/SelectClassComp.jsp"></jsp:include>
				<jsp:include page="components/SelectPassengerComp.jsp"></jsp:include>
				
				<div style="margin:10px 0 auto 10px"><input id="btn" type="submit" style="width:300px;" value="Book Ticket" /></div>
				
			</form>
			</fieldset>
			</div>
			
			<jsp:include page="components/BackToPreviewPageComp.jsp"></jsp:include>
		
		</div>		
	</div>
		
	<div style="clear:both"></div>

</div>

<jsp:include page="footerComp.jsp"></jsp:include>

</body>
</html>