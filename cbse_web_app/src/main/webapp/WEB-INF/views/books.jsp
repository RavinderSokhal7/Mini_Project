<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Library Webteam">
<title>Books | Library</title>
<spring:url value="/resources/css/index.css" var="indexCss" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${indexCss}" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="header">
	<h1><a href="#">Library Management System</a></h1>
			<ul id="nav-menu">
				<c:if test="${not empty menulinks}">
				    <c:forEach items="${menulinks}" var="menulink">
						<li><a href="${menulink.getLink()}">${menulink.getName()}</a></li>
					</c:forEach>
				</c:if>
			</ul>
</div>
<div id="page">
	<div id="content">
		<div id="title"><h2>Books</h2>
		<span class="welcome">Welcome ${user},</span></div>
		
		<c:if test="${usertype eq 'admin'}">
		
		<div class="body" align="center">
			<form method="post" action="add-book">
				<h3 style="margin:10px 0;font-size:24px">Add Books</h3>
				<div style="margin:10px 0">
					<label style="display:block">Name</label>
					<input type="text" name="name" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" max-length="50" placeholder="Introduction to Library Management" required>
				</div>
				<div style="margin:10px 0">
					<label style="display:block">Author</label>
					<input type="text" name="author" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" max-length="50" placeholder="Milner, B.E." required>
				</div>
				<div style="margin:10px 0">
					<label style="display:block">Copies</label>
					<input type="number" name="copies" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" placeholder="10" required>
				</div>
				<input type="submit" value="Submit" style="width:12%;margin:0;padding:8px 10px;">
			</form>
		</div>
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
			</form>
		</div>
		</c:if>
		<c:if test="${not empty SearchComp }">
		<jsp:include page="${SearchComp }" />
		</c:if>
		<%-- <div class="body" align="center">
			<form method="GET">
				<h3 style="margin:10px 0;font-size:24px;text-overflow:ellipsis;overflow:hidden;white-space:nowrap">Search: ${search}</h3>
				<input type="text" name="search" placeholder="Search" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" required>
				<input type="submit" value="Search" style="width:12%;margin:0;padding:8px 10px;"></input>
			</form>
		</div> --%>
		<c:if test="${not empty ShowBooksComp }">
		<jsp:include page="${ShowBooksComp }"></jsp:include>
		</c:if>
		<%-- <div class="body" align="center">
			<table id="books">
			<thead><tr><th width="400px">Title</th><th width="220px">Author</th><th width="80px">Copies</th><c:if test="${usertype eq 'admin'}"><th width="80px">Remaining</th></c:if></tr></thead>
			<tbody>
					<c:if test="${not empty books}">
					    <c:forEach items="${books}" var="book">
							<tr><td width="400px">${book.name}</td><td width="220px">${book.author}</td><td width="80px">${book.total}</td><c:if test="${usertype eq 'admin'}"><td width="80px">${book.getRemaining()}</td></c:if></tr>
						</c:forEach>
					</c:if>
			</tbody>
			</table>
		</div> --%>
	</div>
			<ul id="side_menu">
		<li><a href="">Quick Links</a>
			<ul style="display:block">
				<c:if test="${not empty quicklinks}">
				    <c:forEach items="${quicklinks}" var="quicklink">
						<li><a href="${quicklink.getLink()}">${quicklink.getName()}</a></li>
					</c:forEach>
				</c:if>
			</ul>
		</li>
		</ul>
		
	<div style="clear:both"></div>
</div>

<div id="footer" style="position:relative; font-size:14px;" >
<p id="legal">&copy;Copyright Library All Rights Reserved. Designed by <a href="contact">Library Webteam</a></p>
	<p id="links"  ><a href="http://172.31.102.36">172.31.102.36</a> 
	
	</p>
</div>

</body>
</html>