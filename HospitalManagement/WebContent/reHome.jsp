<%@page import="com.util.SessionChecker"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Home page for Registration Desk Executive -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="header.jsp"></jsp:include>
<% if(!SessionChecker.isValidSession(session))
	{
		request.setAttribute("errormessage","Invalid Session. Please login again");
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return;
	}%>
<body>
<center><h1>Registration Executive</h1></center>
<div>
	<a href="addPatient.jsp">Add New Patient</a><br>
	<a href="updatePatient.jsp">Update Patient Details</a><br>
	<a href="deletePateint.jsp">Delete Patient</a><br>
	<a href="viewAllPatients.jsp">View all active Patients</a><br>
</body>
</html>

