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
			<table id="books">
				<thead>
					<tr>
						<th width="300px">Reservation Id</th><th width="300px">Name of user</th>
						<th width="300px">Train No.</th><th width="300px">Train Name</th>					
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="300px">${reservation_id }</th><th width="300px">${user.name }</th>
						<th width="300px">${train.train_no }</th><th width="300px">${train.train_name }</th>					
					</tr>
				</tbody>
			</table>
			<table id="books">
				<thead>
					<tr>
						<th width="300px">From Station</th><th width="300px">From Station no.</th>
						<th width="300px">From Arrival Time</th><th width="300px">From Departure time</th>					
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="300px">${ts.from }</th><th width="300px">${ts.from_no }</th>
						<th width="300px">${ts.from_arr }</th><th width="300px">${ts.from_dept }</th>					
					</tr>
				</tbody>
			</table>
			<table id="books">
				<thead>
					<tr>
						<th width="300px">To Station</th><th width="300px">To Station no.</th>
						<th width="300px">To Arrival Time</th><th width="300px">To Departure time</th>					
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="300px">${ts.to }</th><th width="300px">${ts.to_no }</th>
						<th width="300px">${ts.to_arr }</th><th width="300px">${ts.to_dept }</th>					
					</tr>
				</tbody>
			</table>
			<table id="books">
				<thead>
					<tr>
						<th width="300px">Date of Boarding</th><th width="300px">(YYYY-MM-dd)format</th>
						<th width="300px">Fare per passenger</th><th width="300px"></th>					
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="300px"></th><th width="300px">${date }</th>
						<th width="300px">${farepp }</th><th width="300px"></th>					
					</tr>
				</tbody>
			</table>
			<table id="books">
				<thead>
					<tr>
						<th width="300px">Class of coach</th><th width="300px">Adult Passengers</th>
						<th width="300px">Child Passengers</th><th width="300px">Total Fare</th>					
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="300px">${selectedClass }</th><th width="300px">${adult }</th>
						<th width="300px">${child }</th><th width="300px">${total_cost }</th>					
					</tr>
				</tbody>
			</table>
			<c:if test="${status == false }">
				<p id="errorMessage" style="color:red">${errorMessage }</p>
			</c:if>
			<c:if test="${status == true }">
				<p id="errorMessage" style="color:green">${errorMessage }</p>
			</c:if>
			<input type="button" id="btn" style="width:30%;" value="Print Bill" onClick="printPage()"/>
			<jsp:include page="components/BackToPreviewPageComp.jsp"></jsp:include>
<script>
    function printPage() {
        window.print();  
    }
</script>
		</div>
	</div>
	<div style="clear:both"></div>

</div>

<jsp:include page="footerComp.jsp"></jsp:include>

</body>
</html>