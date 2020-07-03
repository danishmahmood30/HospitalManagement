<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.util.SessionChecker"%>
    <%@page import="com.bean.Test" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/cities.js"></script>
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
<h2>Add Diagnostics</h2>
<% String availability=(String)session.getAttribute("availability");
System.out.println(availability);%>
<% if(availability.equals("available")){ %>
	<h3>Test Available</h3>
<%}else if(availability.equals("unavailable")){ %>
	<h3 class="error">Test Not Available</h3>
<div class="center">
	<form action="${pageContext.request.contextPath}/PatientController" method="post">
  	<label for="testName">Test Name:</label>
  	<input type="text" id="patientid" name="testName" required>
  	<button class="search" type="submit" name="action" value="search_test" >
  	Search Test
  	</button>
</form>
<br><br>
	<form action="${pageContext.request.contextPath}/PatientController" method="post">
	<button class="search" type="submit" name="action" value="back_to_diagnostics" >
  	back
  	</button>
</form>
</div>
<%}else { %>
<div class="center">
<form action="${pageContext.request.contextPath}/PatientController" method="post">
  <label for="testName">Test Name:</label>
  <input type="text" id="patientid" name="testName" required>
  <button class="search" type="submit" name="action" value="search_test" >
  	Search Test
  </button>
</form>
<br><br>
<form action="${pageContext.request.contextPath}/PatientController" method="post">
	<button class="search" type="submit" name="action" value="back_to_diagnostics" >
  	back
  	</button>
</form>
</div>
<%} %>

<div class="center">
<% if(availability.equals("available")){ %>
<% Test test=(Test)session.getAttribute("test"); %>
<form action="${pageContext.request.contextPath}/PatientController" method="post">
	<table align="center" style="width:30%">
	<colgroup>
		<col style="background-color:gray">
		<col style="background-color:white">
	</colgroup>
		<tr><td style="width:200px" >Test Name:</td><td><%=test.getTestName() %></td></tr>
		<tr><td>Amount:</td><td><%=test.getTestRate() %></td></tr>
	</table><br>
		<input type="hidden" name="test_name" value="<%=test.getTestName()%>">
		<input type="hidden" name="test_id" value="<%=test.getTestId()%>">
		<input type="hidden" name="test_rate" value="<%=test.getTestRate()%>">
		<button type="submit" name="action" value="Add Test">Add Test</button>
</form>
<%} %>

</div>
<jsp:include page="footer2.jsp"></jsp:include>
</body>
</html>