<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.util.SessionChecker"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/cities.js"></script>
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
<center><h1>Patient Registration</h1></center>

<form action="${pageContext.request.contextPath}/PatientController" method="post" class="patReg">
  
  <div class="">
    <label for="ssnid">Patient SSN ID * </label>
    <input type="number" name="ssnid" id="" required>
  </div>
  
  <div class="">
    <label for="name">Patient Name * </label>
    <input type="text" name="name" id="" required>
  </div>
  
  <div class="">
    <label for="age">Patient Age * </label>
    <input type="text" name="age" id="" required>
  </div>
  
  <div class="">
    <label for="admission">Date of Admission * </label>
    <input type="date" name="date" id="" required>
  </div>
  
  <div class="">
    <label for="beds">Type of Bed * </label>
    <select id="bed" name="beds" required>
    	<option value="">Select type of bed</option>
   		<option value="general">General Ward</option>
  		<option value="semi">Semi Sharing</option>
  		<option value="single">Single</option>
	</select>
  </div>
 
  <div class="">
    <label for="address">Address * </label>
    <textarea name="address" rows="4" cols="50">
	</textarea>
  </div>
  

  
  <div>
  	<label for="state">State * </label>
  		<select onchange="print_city('state', this.selectedIndex);" id="sts" name ="stt" class="form-control" required>
  		</select>
  		<br/>
  	<label for="city" >City *</label>
		<select id ="state" name="city" class="form-control" required>
		</select>
	<script language="javascript">print_state("sts");</script>
  </div>
  
  <div class="sub">
    <input type="submit" name="action" value="submit">
  </div>
  <div class="res">
    <input type="reset" value="Reset">
  </div>
</form>
</div>
</body>
</html>