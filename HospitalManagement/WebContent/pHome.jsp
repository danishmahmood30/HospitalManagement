<%@page import="com.util.SessionChecker"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!-- Home page for Pharmacist -->

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
<body>

	<h2>Pharmacy</h2><br><br>

<div class="center" style="height:300px">
<form action="${pageContext.request.contextPath}/PatientController" method="post">
  <label for="patientid">Patient Id:</label>
  <input type="number" id="patientid" name="patientid" required
        maxlength="9" size="15">
  <button class="search" type="submit" name="action" value="searchPharma" >
  	search patient
  </button>
</form>
</div>
<jsp:include page="footer2.jsp"></jsp:include>	
</body>

</html>

