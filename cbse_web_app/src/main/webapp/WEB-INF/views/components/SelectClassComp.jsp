<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
Select Class:	
<select id="classsel" name="selectedClass" onchange="#">
     
	<option value="none" selected disabled hidden > 
	      Select Class 
	</option> 
	<c:if test="${not empty selectedClass}">
		<option selected="selected">
			${selectedClass}
		</option>
	</c:if>
	
<option value="General">General</option>
<option value="Tatkal">Tatkal</option>
<option value="Ladies">Ladies</option>
<option value="1st AC">1st AC</option>
<option value="2nd AC">2nd AC</option>
<option value="3rd AC">3rd AC</option>
<option value="Sleeper">Sleeper</option>
<option value="CC">CC</option>
<option value="Business">Business</option>
<option value="Economy">Economy</option>

</select>
</body>
</html>