<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.util.SessionChecker"%>
    <%@page import="com.bean.Patient" %>
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
<% Patient patient =(Patient)session.getAttribute("patient");
    System.out.println(patient.getPatientId());%>
<div>
			<table align="center" cellpadding=5 style="width:40%">
			<colgroup>
				<col style="background-color:#c0c0c0">
				<col style="background-color:white">
			</colgroup>
				<caption><h2>Patient Details</h2></caption>
				<tr><td style="width:200px">Patient SSN Id:</td><td><%=patient.getSsnId() %></td></tr>
				<tr><td>Patient Id:</td><td><%=patient.getPatientId() %></td></tr>
				<tr><td>Patient Name:</td><td><%=patient.getPatientName() %></td></tr>
				<tr><td>Address:</td><td><%=patient.getAddress() %></td></tr>
				<tr><td>Age:</td><td><%=patient.getAge() %></td></tr>
				<tr><td>City:</td><td><%=patient.getCity() %></td></tr>
				<tr><td>State:</td><td><%=patient.getState() %></td></tr>
				<tr><td>DOJ:</td><td><%=patient.getDateOfAdmission() %></td></tr>
				<tr><td>Type of Bed:</td><td><%=patient.getTypeOfBed() %></td></tr>
				<tr><td>Status:</td><td><%=patient.getStatus() %></td></tr>
				
			</table><br>
</div>
<div class="center">
	<form action="${pageContext.request.contextPath}/PatientController" method="post">
	<tr>
		<td><input type="hidden" name="action" value="dssds">
		<td><input type="hidden" name="patientid" value="<%=patient.getPatientId() %>">
		<td><button type="submit" name="button" value="regex_home">Home</button></td>
		<td><button type="submit" name="button" value="search_another">Search Another</button></td>
		<td><button type="submit" name="button" value="update_details">Update Details</button></td>
		<% if(patient.getStatus().equals("active")){  %>
			<td><button type="submit" name="button" value="start_bill">Start Billing</button></td>
		<% }%>	
	</tr>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>