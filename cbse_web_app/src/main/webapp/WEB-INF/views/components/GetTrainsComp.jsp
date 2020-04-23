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
			<c:if test="${not empty trainSchedules}">
			<table id="books">
			
			<thead>
				<tr>
					<th width="180px">Train No.</th><th width="600px">Train Name</th>
					<th width="200px">From Station</th><th width="80px">station no</th><th width="80px">From arrival time</th>
					<th width="80px">From departure time</th><th width="200px">To Station</th><th width="80px">station no</th>
					<th width="80px">To arrival time</th><th width="80px">To departure time</th>
					<th width="80px">Book Train</th>
				</tr>
			</thead>
			
			<tbody>
				<c:if test="${not empty trainSchedules}">
				    <c:forEach items="${trainSchedules}" var="ts">
						<form method="post" action = "book-train">
						<tr><th width="180px"><input type="text" name="train_no" value="${ts.train_no}" style="width:70%;" readonly/></th>
						<th width="300px">${ts.train_name }</th>
						<th width="200px">${ts.from }</th><th width="80px">${ts.from_no }</th><th width="80px">${ts.from_arr }</th>
						<th width="80px">${ts.from_dept }</th><th width="200x">${ts.to }</th><th width="50px">${ts.to_no }</th>
						<th width="80px">${ts.to_arr }</th><th width="80px">${ts.to_dept }</th>
						<th width="80px"><input id="btn" type="submit" style="width:100%;" value="Book" /></th>
						</tr>
						</form>
					</c:forEach>
				</c:if>
				<c:if test="${status == false }">
					<p id="errorMessage" style="color:red">${errorMessage }</p>
				</c:if>
			</tbody>
			</table>
			</c:if>
	</div>
</body>
</html>