<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<div class="body" align="center">
			<form method="GET" action="books" target="_parent">
				<h3 style="margin:10px 0;font-size:24px;text-overflow:ellipsis;overflow:hidden;white-space:nowrap">Search: ${search}</h3>
				<input type="text" name="search" placeholder="Search" style="padding:8px 10px;width:84.5%;border: 1px solid #999;" required>
				<input type="submit" value="Search" style="width:12%;margin:0;padding:8px 10px;"></input>
			</form>
		</div>
</body>
</html>