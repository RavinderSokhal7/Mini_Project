<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
		<div class="body" align="center">
			<table id="books">
			<c:if test="${not empty books}">
			<thead><tr><th width="400px">Title</th><th width="220px">Author</th><th width="80px">Copies</th>
			<th width="80px">Remaining</th></tr></thead>
			</c:if>
			<tbody>
				<c:choose>
					<c:when test="${not empty books}">
					    <c:forEach items="${books}" var="book">
							<tr><td width="400px">${book.name}</td><td width="220px">${book.author}</td><td width="80px">${book.total}</td>
							<c:if test="${usertype eq 'admin'}"><td width="80px">${book.getRemaining()}</td></c:if></tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p id="errorMessage" style="color:red">No Books Found!</p>
					</c:otherwise>
				</c:choose>
			</tbody>
			</table>
		</div>
</body>
</html>