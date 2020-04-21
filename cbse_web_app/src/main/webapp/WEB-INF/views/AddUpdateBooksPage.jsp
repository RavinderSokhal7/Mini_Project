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

<div id="header">
	<h1><a href="#">${category}</a></h1>
			
</div>
<div id="page">
	<div id="content">
		<div id="title"><h2>${category }</h2>
		<span class="welcome">Welcome,</span></div>
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
		
	</div>
		
	<div style="clear:both"></div>
</div>

<jsp:include page="footerComp.jsp"></jsp:include>

</body>
</html>