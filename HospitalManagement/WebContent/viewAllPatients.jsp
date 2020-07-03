<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.bean.Patient" %>
    <%@page import="java.util.*" %>
    <%@page import="com.util.SessionChecker"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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

<div>
<% ArrayList<Patient> plist =(ArrayList<Patient>)session.getAttribute("patientList"); %>
<div class="center">
<h2>All Active Patients</h2>
	<table border="1" cellpadding="5" style="width:100%">
	<tr class="head">
		<th><b>SSS Id</b></th>
		<th><b>Patient Id</b></th>
		<th><b>Patient Name</b></th>
		<th><b>Age</b></th>
		<th><b>Address</b></th>
		<th><b>DOJ</b></th>
		<th><b>Type of Room</b></th>
	</tr >
	<% for(Patient patient: plist) {%>
	<tr class="row">
		<td><%=patient.getSsnId()%></td>
		<td><%=patient.getPatientId()%></td>
		<td><%=patient.getPatientName()%></td>
		<td><%=patient.getAge()%></td>
		<% String address= patient.getAddress()+","+patient.getCity()+","+patient.getState(); %>
		<td><%=address%></td>
		<td><%=patient.getDateOfAdmission()%></td>
		<td><%=patient.getTypeOfBed()%></td>
	</tr>
	<%} %>
	</table><br>
	<form action="${pageContext.request.contextPath}/PatientController" method="post">
	
	<input type="hidden" name="action" value="jjj">
	<button type="submit" name="button" value="regex_home">home</button>
	
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>