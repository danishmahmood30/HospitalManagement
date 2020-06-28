<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
int id = (int)session.getAttribute("patientId");
String status=(String)session.getAttribute("status");
System.out.println(id);
System.out.println(status);%>

<%if(status.equals("update")){%> 
	<h2>Customer details successfully updated for id: <%=id %></h2>	
<%} else if(status.equals("create")){%>
	<h2>Customer successfully created with id: <%=id %></h2>
<%} else if(status.equals("delete")){%>
	<h2>Customer with id: <%=id %> deleted successfully</h2>
<%} %>
<form action="${pageContext.request.contextPath}/PatientController" method="post">
<table>
	<tr><td><input type="hidden" name="action" value="back"></td></tr>
	<tr><td><button type="submit" name="button" value="home">Go Home</button></td></tr>
	<tr><td><button type="submit" name="button" value="add_another_patient">Add Another Patient</button></td></tr>
</table>
</form>
</body>
</html>