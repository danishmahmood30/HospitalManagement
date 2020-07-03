<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.bean.Patient" %>
    <%@page import="com.bean.Medicine" %>
    <%@page import="com.bean.Test" %>
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
<% Patient patient =(Patient)session.getAttribute("patient"); %>
<div>
	<h3>Diagnostics</h3>
	<table border="1" cellpadding="5" align="center" style="width:100%;">
	<tr class="head">
		<th><b>SSS Id</b></th>
		<th><b>Patient Id</b></th>
		<th><b>Patient Name</b></th>
		<th><b>Age</b></th>
		<th><b>Address</b></th>
		<th><b>DOJ</b></th>
		<th><b>Type of Room</b></th>
	</tr>
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
	</table>
</div>
<% String testFound= (String)session.getAttribute("testFound");
	if(testFound.equalsIgnoreCase("no")){
%>
<h3 class="error">No previous Test records found</h3>
<%}else{
	ArrayList<Test> tList=(ArrayList<Test>)session.getAttribute("testList");%>
	<div>
		<h3>Test Conducted</h3>
		<table border=1 cellpadding="5" align="center" style="width:30%;">
			<tr class="head">
			<th><b>Name of the Test</b></th>
			<th><b>Amount</b></th>
			</tr>
		<%for(Test test: tList){ %>
			<tr class="row">
			<td><%=test.getTestName()%></td>
			<td><%=test.getTestRate()%></td>
			</tr>
		<% }%>
		</table>
	</div>
<%} %>

<% String isAdd =(String)session.getAttribute("addTest"); %>
<% if(isAdd.equals("no")) { %>
<div class="center">
	<br><br>
	<form action="${pageContext.request.contextPath}/PatientController" method="post">
	<input class="button" type="submit" name="action" value="Add Diagnostics">
	</form>
</div>
<%} %>

<% if(isAdd.equals("yes")) { %>

<%	ArrayList<Test> temp_test_list=(ArrayList<Test>)session.getAttribute("temp_test_list"); %>
	
	<div>
		<h3>New Test</h3>
		<table border=1 cellpadding="5" align="center" style="width:30%;">
			<tr class="head">
			<th><b>Name of the Test</b></th>
			<th><b>Amount</b></th>
			</tr>
		<%for(Test test: temp_test_list){ %>
			<tr class="row">
			<td><%=test.getTestName()%></td>
			<td><%=test.getTestRate()%></td>
			</tr>
		<% }%>
		</table>
	</div>
<%} %>
<% if(isAdd.equals("yes")) { %>
<div class="center">
	<br><br>
	<form action="${pageContext.request.contextPath}/PatientController" method="post">
		<input class="button" type="submit" name="action" value="Add Another Test">
		<button class="search" type="submit" name="action" value="update_diagnostics" >Update</button>
	</form>
</div>
<%} %>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>