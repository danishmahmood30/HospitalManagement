<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.bean.Patient" %>
    <%@page import="com.bean.Medicine" %>
    <%@page import="java.util.*" %>
    <%@page import="com.util.SessionChecker"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pharmacy</title>
<link href="style.css" rel="stylesheet" type="text/css"/>
</head>
<jsp:include page="header.jsp"></jsp:include>
<% if(!SessionChecker.isValidSession(session))
	{
		request.setAttribute("errormessage","Invalid Session. Please login again");
		request.getRequestDispatcher("login.jsp").forward(request, response);
		return;
	}%>
<body style="background-color:skyblue;">
<div style="text-align:center;"><h3>Pharmacy</h3></div>
<% Patient patient =(Patient)session.getAttribute("patient"); %>
<div class="table1" style="text-align:center;" >
<table class="table" style="width:100%;">
	<tr style="background-color:gray;">
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
<% String medicineFound= (String)session.getAttribute("medicineFound");
	if(medicineFound.equalsIgnoreCase("no")){
%>
<div style="text-align:center;">
<h3 class="error">No previous medicine records found</h3></div>
<%}else{
	ArrayList<Medicine> mList=(ArrayList<Medicine>)session.getAttribute("medicineList");%>
	<div style="text-align:center;">
		<center><h3>Medicine Issued</h3></center>
		<table class="table" align="center" style="width:60%;">
			<tr style="background-color:gray;">
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
			<% int amount = (medicine.getQuantityAvailable())*(medicine.getRateOfMedicine());%>
			<td><%=amount %></td>
			</tr>
		<% }%>
		</table>
	</div>
<%} %>

<% String isAdd =(String)session.getAttribute("addMed"); %>
<% if(isAdd.equals("no")) { %>
<div style="text-align:center">
	<br><br>
	<form action="${pageContext.request.contextPath}/PatientController" method="post">
	<button type="submit" name="action" value="Issue Medicine">Issue Medicine</button>
	</form>
</div>
<%} %>

<% if(isAdd.equals("yes")) { %>

<%	ArrayList<Medicine> temp_med_list=(ArrayList<Medicine>)session.getAttribute("temp_med_list"); %>
	
	<div style="text-align:center;">
		<center><h3>New Medicine</h3></center>
		<table class="table" align="center" style="width:60%;">
			<tr style="background-color:gray;">
			<th><b>Medicine</b></th>
			<th><b>Quantity</b></th>
			<th><b>Rate</b></th>
			<th><b>Amount</b></th>
			</tr>
		<%for(Medicine medicine: temp_med_list){ %>
			<tr class="row">
			<td><%=medicine.getMedicineName()%></td>
			<td><%=medicine.getQuantityAvailable()%></td>
			<td><%=medicine.getRateOfMedicine()%></td>
			<% int amount = (medicine.getQuantityAvailable())*(medicine.getRateOfMedicine());%>
			<td><%=amount %></td>
			</tr>
		<% }%>
		</table>
	</div>
<%} %>
<% if(isAdd.equals("yes")) { %>
<div style="text-align:center" >
	
	<br><br>
	<form action="${pageContext.request.contextPath}/PatientController" method="post">
		<button type="submit" name="action" value="Add Another Medicine">Add</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button class="search" type="submit" name="action" value="update_medicine" >Update</button>
	
	</form>
</div>
<%} %>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>