<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.util.SessionChecker"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
button{
 background-color: black;
 color: white;
}
h2{
	text-align:center
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
<body style="background-color:skyblue">
<% 
int id = (int)session.getAttribute("patientId");
String status=(String)session.getAttribute("status");
System.out.println(id);
System.out.println(status);%>

<%if(status.equals("update")){%> 
	<h2 >Patient details successfully updated for id: <%=id %></h2>	
<%} else if(status.equals("create")){%>
	<h2>Patient successfully created with id: <%=id %></h2>
<%} else if(status.equals("delete")){%>
	<h2>Patient with id: <%=id %> deleted successfully</h2>
<%} else if(status.equals("update_medicine")){%>
	<h2>Medicine issued Successfully for Patient Id:<%=id %></h2>
<%} else if(status.equals("update_test")){%>
	<h2>Test added Successfully for Patient Id:<%=id %></h2>
<%} else if(status.equals("discharge")){%>
	<h2>Patient with id <%=id %> Successfully Discharged</h2>
<%} %>
<form action="${pageContext.request.contextPath}/PatientController" method="post">
<div style="text-align:center ;height:300px" >
<table align="center">
	<tr><td><input type="hidden" name="action" value="back"></td></tr>
	<%if(!(status.equals("update_medicine")||status.equals("update_test"))) {%>
	<tr><td><button type="submit" name="button" value="regex_home">Go Home</button></td></tr>
	<%} %>
	<%if(status.equals("create")) {%>
	<tr><td><button type="submit" name="button" value="add_another_patient">Add Another Patient</button></td></tr>
	<%} %>
	<%if(status.equals("update")) {%>
	<tr><td><button type="submit" name="button" value="update_another">Update Another Patient</button></td></tr>
	<%} %>
	<%if(status.equals("delete")) {%>
	<tr><td><button type="submit" name="button" value="delete_another">Delete Another Patient</button></td></tr>
	<%} %>
	<%if(status.equals("update_medicine")) {%>
	<tr><td><button type="submit" name="button" value="pharma_home">Go Home</button></td></tr>
	<%} %>
	<%if(status.equals("update_test")) {%>
	<tr><td><button type="submit" name="button" value="diagnostics_home">Go Home</button></td></tr>
	<%} %>
</table>
</div>
</form>
<jsp:include page="footer2.jsp"></jsp:include>
</body>
</html>