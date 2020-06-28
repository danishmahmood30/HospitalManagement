<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.bean.Patient" %>
    <%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div>
<% ArrayList<Patient> plist =(ArrayList<Patient>)request.getAttribute("customerList"); %>
<div>
	<table border="1" cellpadding="5">
	<tr>
		<th><b>SSS Id</b></th>
		<th><b>Customer Id</b></th>
		<th><b>Customer Name</b></th>
		<th><b>Age</b></th>
		<th><b>Address</b></th>
		<th><b>State</b></th>
		<th><b>City</b></th>
		<th><b>DOJ</b></th>
		<th><b>Type of Room</b></th>
	</tr>
	<% for(Patient patient: plist) {%>
	<tr>
		<td><%=patient.getSsnId()%></td>
		<td><%=patient.getPatientId()%></td>
		<td><%=patient.getPatientName()%></td>
		<td><%=patient.getAge()%></td>
		<td><%=patient.getAddress()%></td>
		<td><%=patient.getState()%></td>
		<td><%=patient.getCity()%></td>
		<td><%=patient.getDateOfAdmission()%></td>
		<td><%=patient.getTypeOfBed()%></td>
	</tr>
	<%} %>
	</table>
	<form action="${pageContext.request.contextPath}/PatientController" method="post">
	<table>
	<tr><td><button type="submit" name="button" value="home">home</button></td></tr>
	</table>
	</form>
</div>

</body>
</html>