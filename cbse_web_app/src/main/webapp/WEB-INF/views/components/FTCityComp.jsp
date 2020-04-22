<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>

<form id="selectCities" action="train-select-city" method="post">

	Select From City:	
	<select id="fromCity" name="fromCity" onchange="fromChanged()">
	     <!-- <option value="empty">Select a City</option> -->
	<option value="none" selected disabled hidden > 
	      Select a City 
	</option> 
	<c:if test="${not empty fromCity}">
		<option selected="selected">
			${fromCity}
		</option>
	</c:if>
	
	<option value="Agra">Agra</option>
	<option value="Ahmedabad">Ahmedabad</option>
	<option value="Ajmer">Ajmer</option>
	<option value="Aligarh">Aligarh</option>
	<option value="Alwar">Alwar</option>
	<option value="Ambala">Ambala</option>
	<option value="Amritsar">Amritsar</option>
	<option value="Aurangabad">Aurangabad</option>
	<option value="Azamgarh">Azamgarh</option>
	<option value="Bahraich">Bahraich</option>
	<option value="Bangalore">Bangalore</option>
	<option value="Bareilly">Bareilly</option>
	<option value="Bathinda">Bathinda</option>
	<option value="Bhopal">Bhopal</option>
	<option value="Bijnor">Bijnor</option>
	<option value="Bikaner">Bikaner</option>
	<option value="Bilaspur">Bilaspur</option>
	<option value="Chamoli">Chamoli</option>
	<option value="Chandigarh">Chandigarh</option>
	<option value="Chennai">Chennai</option>
	<option value="Chitrakoot">Chitrakoot</option>
	<option value="Coimbatore">Coimbatore</option>
	<option value="Cuttack">Cuttack</option>
	<option value="Darjeeling">Darjeeling</option>
	<option value="Dehradun">Dehradun</option>
	<option value="Etawah">Etawah</option>
	<option value="Faizabad">Faizabad</option>
	<option value="Faridabad">Faridabad</option>
	<option value="Faridkot">Faridkot</option>
	<option value="Fatehgarh Sahib">Fatehgarh Sahib</option>
	<option value="Fatehpur">Fatehpur</option>
	<option value="Ghaziabad">Ghaziabad</option>
	<option value="Gorakhpur">Gorakhpur</option>
	<option value="Gulbarga">Gulbarga</option>
	<option value="Gurgaon">Gurgaon</option>
	<option value="Gwalior">Gwalior</option>
	<option value="Hardoi">Hardoi</option>
	<option value="Haridwar">Haridwar</option>
	<option value="Hissar">Hissar</option>
	<option value="Hyderabad">Hyderabad</option>
	<option value="Indore">Indore</option>
	<option value="Jabalpur">Jabalpur</option>
	<option value="Jaipur">Jaipur</option>
	<option value="Jalandhar">Jalandhar</option>
	<option value="Kanpur">Kanpur</option>
	<option value="Kolkata">Kolkata</option>
	<option value="Lucknow">Lucknow</option>
	<option value="Ludhiana">Ludhiana</option>
	<option value="Meerut">Meerut</option>
	<option value="Mumbai ">Mumbai</option>
	<option value="Muzaffarnagar">Muzaffarnagar</option>
	<option value="Muzaffarpur">Muzaffarpur</option>
	<option value="Mysore">Mysore</option>
	<option value="Nagaur">Nagaur</option>
	<option value="Nainital">Nainital</option>
	<option value="New Delhi">New Delhi</option>
	<option value="Patiala">Patiala</option>
	<option value="Patna">Patna</option>
	<option value="Pauri Garhwal">Pauri Garhwal</option>
	<option value="Pratapgarh">Pratapgarh</option>
	<option value="Prayagraj">Prayagraj</option>
	<option value="Pune">Pune</option>
	<option value="Saharanpur">Saharanpur</option>
	<option value="Shimla">Shimla</option>
	<option value="Solan">Solan</option>
	<option value="Srinagar">Srinagar</option>
	<option value="Thane">Thane</option>
	<option value="Ujjain">Ujjain</option>
	<option value="Varanasi">Varanasi</option>
	<option value="Vellore">Vellore</option>
	<option value="Yamuna Nagar">Yamuna Nagar</option>
	  </select>

	Select To City:	
	<select id="toCity" name="toCity" onchange="toChanged()">
	     <!-- <option value="empty">Select a City</option> -->
	<option value="none" selected disabled hidden > 
	      Select a City 
	</option> 
	<c:if test="${not empty toCity}">
		<option selected="selected">
			${toCity}
		</option>
	</c:if>
	  
	
	<option value="Agra">Agra</option>
	<option value="Ahmedabad">Ahmedabad</option>
	<option value="Ajmer">Ajmer</option>
	<option value="Aligarh">Aligarh</option>
	<option value="Prayagraj">Prayagraj</option>
	<option value="Alwar">Alwar</option>
	<option value="Ambala">Ambala</option>
	<option value="Amritsar">Amritsar</option>
	<option value="Aurangabad">Aurangabad</option>
	<option value="Azamgarh">Azamgarh</option>
	<option value="Bahraich">Bahraich</option>
	<option value="Bangalore">Bangalore</option>
	<option value="Bareilly">Bareilly</option>
	<option value="Bathinda">Bathinda</option>
	<option value="Bhopal">Bhopal</option>
	<option value="Bijnor">Bijnor</option>
	<option value="Bikaner">Bikaner</option>
	<option value="Bilaspur">Bilaspur</option>
	<option value="Chamoli">Chamoli</option>
	<option value="Chandigarh">Chandigarh</option>
	<option value="Chennai">Chennai</option>
	<option value="Chitrakoot">Chitrakoot</option>
	<option value="Coimbatore">Coimbatore</option>
	<option value="Cuttack">Cuttack</option>
	<option value="Darjeeling">Darjeeling</option>
	<option value="Dehradun">Dehradun</option>
	<option value="Etawah">Etawah</option>
	<option value="Faizabad">Faizabad</option>
	<option value="Faridabad">Faridabad</option>
	<option value="Faridkot">Faridkot</option>
	<option value="Fatehgarh Sahib">Fatehgarh Sahib</option>
	<option value="Fatehpur">Fatehpur</option>
	<option value="Ghaziabad">Ghaziabad</option>
	<option value="Gorakhpur">Gorakhpur</option>
	<option value="Gulbarga">Gulbarga</option>
	<option value="Gurgaon">Gurgaon</option>
	<option value="Gwalior">Gwalior</option>
	<option value="Hardoi">Hardoi</option>
	<option value="Haridwar">Haridwar</option>
	<option value="Hissar">Hissar</option>
	<option value="Hyderabad">Hyderabad</option>
	<option value="Indore">Indore</option>
	<option value="Jabalpur">Jabalpur</option>
	<option value="Jaipur">Jaipur</option>
	<option value="Jalandhar">Jalandhar</option>
	<option value="Kanpur">Kanpur</option>
	<option value="Kolkata">Kolkata</option>
	<option value="Lucknow">Lucknow</option>
	<option value="Ludhiana">Ludhiana</option>
	<option value="Meerut">Meerut</option>
	<option value="Mumbai ">Mumbai</option>
	<option value="Muzaffarnagar">Muzaffarnagar</option>
	<option value="Muzaffarpur">Muzaffarpur</option>
	<option value="Mysore">Mysore</option>
	<option value="Nagaur">Nagaur</option>
	<option value="Nainital">Nainital</option>
	<option value="New Delhi">New Delhi</option>
	<option value="Patiala">Patiala</option>
	<option value="Patna">Patna</option>
	<option value="Pauri Garhwal">Pauri Garhwal</option>
	<option value="Pratapgarh">Pratapgarh</option>
	<option value="Prayagraj">Prayagraj</option>
	<option value="Pune">Pune</option>
	<option value="Saharanpur">Saharanpur</option>
	<option value="Shimla">Shimla</option>
	<option value="Solan">Solan</option>
	<option value="Srinagar">Srinagar</option>
	<option value="Thane">Thane</option>
	<option value="Ujjain">Ujjain</option>
	<option value="Varanasi">Varanasi</option>
	<option value="Vellore">Vellore</option>
	<option value="Yamuna Nagar">Yamuna Nagar</option>
	  </select>

</form>

<script>

function fromChanged(){
	var x = document.getElementById("fromCity").value;
	document.getElementById("selectCities").submit();
}

function toChanged(){
	var x = document.getElementById("toCity").value;
	document.getElementById("selectCities").submit();
}


</script>  
  
</body>
</html>