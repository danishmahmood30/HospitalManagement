<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.util.SessionChecker"%>
    <%@page import="com.bean.Patient" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/cities.js"></script>
<style>
button{
 background-color: black;
 color: white;
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
<% Patient patient =(Patient)session.getAttribute("patient"); %>
<body style="background-color:skyblue">
<div>
<h2 style="text-align:center">Update Details</h2>

<form action="${pageContext.request.contextPath}/PatientController" method="post" class="patReg">
  <div style="margin-left:480px">
  <tr><td>Patient Id:</td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <td><%=patient.getPatientId() %></td></tr>
  </div>
  <div style="margin-left:480px">
    <label for="ssnid">Patient SSN Id *</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="number" name="ssnid" id="" value="<%=patient.getSsnId() %>" required>
  </div>
  
  <div style="margin-left:480px">
    <label for="name">Patient Name * </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="name" id="" value="<%=patient.getPatientName() %>" required>
  </div>
  
  <div style="margin-left:480px">
    <label for="age">Patient Age * </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="age" id="" value="<%=patient.getAge() %>" required>
  </div>
  
  <div style="margin-left:480px">
    <label for="admission">Date of Admission * </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="date" name="date" id="" value="<%=patient.getDateOfAdmission() %>" required>
  </div>
  
  <div style="margin-left:480px">
    <label for="beds">Type of Bed * </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <select id="bed" name="beds" required>
    	<option value="">Select type of bed</option>
   		<option value="general">General Ward</option>
  		<option value="semi">Semi Sharing</option>
  		<option value="single">Single</option>
	</select>
  </div>
 
  <div style="margin-left:480px">
    <label for="address">Address * </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <textarea name="address" id="address" rows="4" cols="20" >
	</textarea>
  </div>
  

  
  <div style="margin-left:480px">
  	<label for="state">State * </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		<select onchange="print_city('state', this.selectedIndex);" id="sts" name ="stt" class="form-control" value="<%=patient.getState() %>" required>
  		</select>
  		<br/>
  	<label for="city" >City *</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<select id ="state" name="city" class="form-control" required>
		</select>
	<script language="javascript">print_state("sts");</script>
  </div>
  
  <input type="hidden" id="selectedBed" value="<%=patient.getTypeOfBed() %>">
  <input type="hidden" id="selectedState" value="<%=patient.getState() %>">
  <input type="hidden" id="selectedCity" value="<%=patient.getCity() %>">
  <input type="hidden" id="oldAddress" value="<%=patient.getAddress()%>">
  
  <!-- script to populate state and city values from patient object -->
  <script type="text/javascript">
  		window.onload= function putValues(){
  			document.getElementById("address").innerHTML=document.getElementById("oldAddress").value;
			document.getElementById("bed").value=document.getElementById("selectedBed").value;
			print_state("sts");
			document.getElementById("sts").value=document.getElementById("selectedState").value;
			var index=document.getElementById("sts").selectedIndex;
			print_city('state',index);
			document.getElementById("state").value=document.getElementById("selectedCity").value;
			}
	</script>
  
  <div style="margin-left:480px">
    <label for="status">Status * </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="status" id="" value="<%=patient.getStatus() %>" required>
  </div>
  
 <div class="sub">
  	<input type="hidden" name="patientid" value="<%=patient.getPatientId() %>">
    <input type="submit" name="action" value="Update Details">
  </div>
  <div class="res">
  	<input type="hidden" name="action" value="cancel">
    <button type="submit" name="button" value="cancel">Cancel</button>
  </div>
</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>