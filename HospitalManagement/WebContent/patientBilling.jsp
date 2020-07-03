<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.bean.Patient" %>
    <%@page import="com.bean.Medicine" %>
    <%@page import="com.bean.Test" %>
    <%@page import="java.util.*" %>
    <%@page import="com.util.SessionChecker"%>
    <%@page import="java.time.temporal.ChronoUnit"%>
    <%@page import="java.time.LocalDate"%>
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
	<h3>Patient Billing</h3>
	
<% Patient patient =(Patient)session.getAttribute("patient"); %>
<% long amount=0; %>
<% long med_sum=0; %>
<% long test_sum=0; %>
<div>
	<table border="1" cellpadding="5" style="width:100%;">
	<caption></caption>
	<tr class="head">
		<th><b>Patient Id</b></th>
		<th><b>Patient Name</b></th>
		<th><b>Age</b></th>
		<th><b>Address</b></th>
		<th><b>Date of Admission</b></th>
		<th><b>Date of Discharge</b></th>
		<th><b>Type of Room</b></th>
	</tr>
	<tr class="row">
		<td><%=patient.getPatientId()%></td>
		<td><%=patient.getPatientName()%></td>
		<td><%=patient.getAge()%></td>
		<% String address= patient.getAddress()+","+patient.getCity()+","+patient.getState(); %>
		<% %>
		<% long millis = System.currentTimeMillis();
		   java.sql.Date date = new java.sql.Date(millis); 
		   Date add_Date = patient.getDateOfAdmission();
		   String adate = add_Date.toString();
		   String ddate = date.toString();
		   LocalDate addDate = LocalDate.parse(adate);
		   LocalDate disDate =LocalDate.parse(ddate);
		   long days_diff= ChronoUnit.DAYS.between(addDate,disDate); 
		   %>
		<%int days=6; %>
		<% if(patient.getTypeOfBed().equals("semi")) {
				amount = days_diff * 4000;
			}else if(patient.getTypeOfBed().equals("general")){
				amount = days_diff * 2000;
			}else if(patient.getTypeOfBed().equals("single")){
				amount = days_diff * 8000;
			}%>
		
		<td><%=address%></td>
		<td><%=patient.getDateOfAdmission()%></td>
		<td><%=date%></td>
		<td><%=patient.getTypeOfBed()%></td>	
	</tr>
	</table>
	<br>
	<p align="right" style="margin-right:100px"><b>Number of Days:&nbsp</b><%=days_diff%>  &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;<b>Bill for Room:</b>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;Rs <%=amount %> </p>
</div>
<% String medicineFound= (String)session.getAttribute("medicineFound");
	if(medicineFound.equalsIgnoreCase("no")){
%>
<h3 class="error">No previous medicine records found</h3>
<%}else{
	ArrayList<Medicine> mList=(ArrayList<Medicine>)session.getAttribute("medicineList");%>
	
<div>
	<h3>Medicine Issued</h3>
		<table border=1 align="center" style="width:60%;">
			<tr class="head">
			<th><b>Medicine</b></th>
			<th><b>Quantity</b></th>
			<th><b>Rate</b></th>
			<th><b>Amount</b></th>
			</tr>
		<%for(Medicine medicine: mList){ %>
			<tr class="row">
			<td><%=medicine.getMedicineName()%></td>
			<td><%=medicine.getQuantityAvailable()%></td>
			<td><%=medicine.getRateOfMedicine()%></td>
			<% int med_amount = (medicine.getQuantityAvailable())*(medicine.getRateOfMedicine());%>
			<%med_sum=med_sum+med_amount; %>
			<td><%=med_amount %></td>
			</tr>
		<% }%>
		</table>
	<p  align="right" style="margin-right:350px"><b>Bill for Pharmacy</b> :&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; Rs <%=med_sum %> </p>
</div>
<%} %>

<% String testFound= (String)session.getAttribute("testFound");
	if(testFound.equalsIgnoreCase("no")){
%>
<h3 class="error">No previous Test records found</h3>
<%}else{
	ArrayList<Test> tList=(ArrayList<Test>)session.getAttribute("testList");%>
	<div>
		<h3>Test Conducted</h3>
		<table border=1 align="center" style="width:30%;">
			<tr class="head">
			<th><b>Name of the Test</b></th>
			<th><b>Amount</b></th>
			</tr>
		<%for(Test test: tList){ %>
			<tr class="row">
			<td><%=test.getTestName()%></td>
			<td><%=test.getTestRate()%></td>
			</tr>
			<% test_sum=test_sum+test.getTestRate(); %>
		<% }%>
		</table>
		<p align="right" style="margin-right:500px"><b>Bill for Diagnostics</b> :&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; Rs <%=test_sum %> </p>
	</div>
<%} %>
<div class="center">
	<% long total = amount+med_sum+test_sum; %>
	<p><b>Grand Total</b> :&nbsp;&nbsp; &nbsp;Rs <%=total %> </p>
	<form action="${pageContext.request.contextPath}/PatientController" method="post">
		<input type="hidden" name="patientId" value="<%=patient.getPatientId() %>"></td>
		<button class="search" type="submit" name="action" value="confirm_bill" >Confirm</button>
		<button class="search" type="submit" name="action" value="cancel" >Cancel</button>
		</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>