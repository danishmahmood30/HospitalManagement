<%@page import="com.util.SessionChecker"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	.leftbox{
		float:left;
		background:deepskyblue;
		width:30%;
		height:180px;
	}
	.middle{
		float:left;
		background:deepskyblue;
		width:40%;
		height:180px;
	}
	.rightbox{
		float:right;
		background:deepskyblue;
		width:30%;
		height:180px;
	}
</style>
</head>
<jsp:include page="header.jsp"></jsp:include>
<% if(!SessionChecker.isValidSession(session))
	{
		request.setAttribute("errormessage","Invalid Session. Please login again");
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return;
	}%>
<body style="background-color:skyblue; text-align:center">
	<div class="leftbox" style="background-color:skyblue" >
	 	<h2>Registration Executive</h2>
		<a href="addPatient.jsp">Add New Patient</a><br>
		<a href="searchPatient.jsp">Search Patient</a><br>
		<a href="searchForUpdate.jsp">Update Patient Details</a><br>
		<a href="searchForDelete.jsp">Delete Patient</a><br>
		<a href="<%=request.getContextPath()%>/PatientController?action=viewAll">View all active Patients</a><br>
		<a href="searchForBilling.jsp">Start Billing</a><br>
	</div>
	<div class="middle" style="background-color:skyblue; text-align:center" >
	 	<h2>Pharmacy</h2>
		<a href="pHome.jsp">Issue Medicine</a><br>
	</div>
	<div class="rightbox" style="background-color:skyblue;text-align:center" >
	 	<h2>Diagnostics</h2>
		<a href="dHome.jsp">Add Diagnostics</a><br>
	</div>
</body>
<jsp:include page="footer2.jsp"></jsp:include>
</html> 