<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<div align="justify">
			<form method="get" action="get-trains">
				<input id="btn" type="submit" style="width:200px;" value="Get Trains" />
			</form>
			<table id="books">
			<c:if test="${not empty trainSchedules}">
			<thead><tr><th width="100px">Train No.</th><th width="220px">From Station</th><th width="80px">From arrival time</th>
			<th width="80px">From departure time</th><th width="220px">To Station</th>
			<th width="80px">To arrival time</th><th width="80px">To departure time</th><th width="80px">Book Train</th>
			</tr></thead>
			</c:if>
			<tbody>
				<c:choose>
					<c:when test="${not empty trainSchedules}">
					    <c:forEach items="${trainSchedules}" var="ts">
							<form method="post" action = "book-train">
							<tr><th width="100px">${ts.train_no}</th><th width="220px">${ts.from }</th><th width="80px">${ts.from_arr }</th>
							<th width="80px">${ts.from_dept }</th><th width="220px">${ts.to }</th>
							<th width="80px">${to_arr }</th><th width="80px">${ts.to_dept }</th><th width="80px"><input id="btn" type="submit" style="width:100%;" value="Book" /></th>
							</tr>
							</form>
						</c:forEach>
					</c:when>
					<c:when test="${empty fromCity }"></c:when>
					<c:when test="${empty toCity }"></c:when>
					<c:otherwise>
						<p id="errorMessage" style="color:red">No Trains Found!</p>
					</c:otherwise>
				</c:choose>
			</tbody>
			</table>
	</div>
</body>
</html>