<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.util.SessionChecker"%>
    <%@page import="com.bean.Medicine" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/cities.js"></script>
<link href="style.css" rel="stylesheet" type="text/css"/>
</head>
<jsp:include page="header.jsp"></jsp:include>
<% if(!SessionChecker.isValidSession(session))
	{
		request.setAttribute("errormessage","Invalid Session. Please login again");
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return;
	}%>
<body>

<h2>Issue Medicine</h2><br>
<% String availability=(String)session.getAttribute("availability");
System.out.println(availability);%>
<% if(availability.equals("available")){ %>
	<h3 class="success">Medicine Available</h3>
<%}else if(availability.equals("unavailable")){ %>
	<h3 class="error">Medicine Not Available</h3>
<div class="center">
<form action="${pageContext.request.contextPath}/PatientController" method="post">
  	<label for="medicineName">Medicine Name:</label>
  	<input type="text" id="patientid" name="medicineName" required>
  	<button class="search" type="submit" name="action" value="search_medicine" >Search Medicine</button>
</form> 
<br><br>	
<form>
	<form action="${pageContext.request.contextPath}/PatientController" method="post">
  	<button class="search" type="submit" name="action" value="back_to_pharma" >back</button>
</form>
</div> 	

<%}else { %>
<div class="center">
<form action="${pageContext.request.contextPath}/PatientController" method="post">
  <label for="medicineName">Medicine Name:</label>
  <input type="text" id="patientid" name="medicineName" required>
  <button class="search" type="submit" name="action" value="search_medicine" >Search Medicine</button>
 </form>
 <br><br>
<form>
	<form action="${pageContext.request.contextPath}/PatientController" method="post">
  	<button class="search" type="submit" name="action" value="back_to_pharma" >back</button>
</form>
</div>

<%} %>

<div class="center">
<% if(availability.equals("available")){ %>
<% Medicine medicine=(Medicine)session.getAttribute("med"); %>
<form action="${pageContext.request.contextPath}/PatientController" method="post">
	<table align="center" style="width:30%">
	<colgroup>
		<col style="background-color:gray">
		<col style="background-color:white">
	</colgroup>
		<tr><td style="width:200px">Medicine Name:</td><td><%=medicine.getMedicineName() %></td></tr>
		<tr><td>Rate:</td><td><%=medicine.getRateOfMedicine() %></td></tr>
		<tr><td>Quantity:</td><td><input type="number" min="1" max="<%=medicine.getQuantityAvailable() %>" name="quantity" ></td></tr>
	</table><br>
		<input type="hidden" name="med_name" value="<%=medicine.getMedicineName()%>">
		<input type="hidden" name="med_id" value="<%=medicine.getMedicineId()%>">
		<input type="hidden" name="med_rate" value="<%=medicine.getRateOfMedicine()%>">
		<% int amount = (medicine.getQuantityAvailable())*(medicine.getRateOfMedicine());%>
		<input type="hidden" name="med_amount" value="<%=amount%>">
		<button type="submit" name="action" value="Add Medicine">Add Medicine</button>
	
</form>
<%} %>

</div>
<jsp:include page="footer2.jsp"></jsp:include>
</body>
</html>