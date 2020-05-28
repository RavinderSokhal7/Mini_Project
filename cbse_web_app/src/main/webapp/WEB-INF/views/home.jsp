<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true" errorPage="error.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Webteam">
<title>Home | RCL</title>
<spring:url value="/resources/css/index.css" var="indexCss" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${indexCss}" rel="stylesheet" type="text/css" />
</head>

<body>


<jsp:include page="MainHeaderComp.jsp"></jsp:include>

<div id="page">
	<div id="content">
		<div id="title"><h2>Component Selection</h2>
		<span class="welcome">Welcome ${user.getName() },</span></div>
		<div class="body" align="center">
		<fieldset class="box">
			<table>
			<form method="get" id="select" class="login" target="_parent">
				
				
					<tr><td align="center">Select a Category:</td>
				        <td align="center"><select id="selectCategory" onchange="getComponent()" name="category" style="margin: 10px 20px 10px;">
				        	<option value="none" selected disabled hidden> 
						          Select an Option 
						    </option> 
						    <c:if test="${not empty category}">
							    <option selected="selected">
							    	${category.name}
								</option>
						    </c:if>						    
				            <c:forEach items="${listCategory}" var="category">
				                <option value="${category.id}">${category.name}</option>
				            </c:forEach>
				        </select></td>
				    </tr>

			</form>
			<form method="post" action="construct_page" class="login" target="_blank">
				
				    <tr><td align="center">Select Components:</td><td></td><td></td></tr>
				        
				            <c:forEach items="${listComponent}" var="component">
				                <tr><td></td><td align="center"><label for="${component.id }"> ${component.detail}</label></td>
				                <td align="left"><input type="checkbox" name="components" id="${component.id }" value="${component.name}"></td></tr>
				            </c:forEach>
				        
				    
			        <tr><td></td><td colspan="2" align="center"><input id="btn" type="submit" style="width:100%;" value="Preview Page" /></td></tr>
			        <tr><td></td><td colspan="2" align="center"><input id="btn" type="submit" style="width:100%;" value="Download Components" /></td></tr>
					<tr><td></td><td colspan="2"><span id="msg">${msg}</span></td></tr>
				
				
			</form>
			</table>
			</fieldset>
		</div>
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

<script>
function getComponent() {
	var x = document.getElementById("selectCategory").value;
	document.getElementById("select").submit();
}
</script>

<jsp:include page="footerComp.jsp"></jsp:include>

</body>
</html>