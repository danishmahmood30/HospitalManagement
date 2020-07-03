<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.util.SessionChecker"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/cities.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/validations.js"></script>
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
<body style="background-color:skyblue;">
<div style="float:float">
<h2 style="text-align:center">Patient Registration</h2>

<form action="${pageContext.request.contextPath}/PatientController" method="post" name="create" onsubmit="return validateForm()">
  
 <table align="center" >
  
<tbody>
 <tr>
      <td><label for="ssnid">Patient SSN ID * </label></td> 
      <td><input type="number" name="ssnid" id="ssn-id" required></td>
    </tr>
 <tr><td></<td><td><span id="ssn_error" style="color:red"></span></td></tr>
 <tr>
 <td><label for="name">Patient Name * </label></td>
 <td><input type="text" name="name" id="pat-name" required></td>
 <tr><td></<td><td><span id="name_error" style="color:red"></span></td></tr>
 </tr> 
  
    <tr>
 <td><label for="age">Patient Age * </label></td>
 <td><input type="text" name="age" id="pat-age" required></td>
 <tr><td></<td><td><span id="age_error" style="color:red"></span></td></tr>
 </tr> 
  
  <tr>
 <td><label for="admission">Date of Admission * </label></td>
 <td><input type="date" name="date" id="pat-date" required></td>
 </tr>
  
  <tr>
 <td><label for="beds">Type of Bed * </label></td>
 <td> <select id="bed" name="beds" required>
    	<option value="">Select type of bed</option>
   		<option value="general">General Ward</option>
  		<option value="semi">Semi Sharing</option>
  		<option value="single">Single</option>
	</select></td>
 </tr> 
 
  <tr>
 <td><label for="address">Address * </label></td>
 <td> <textarea name="address" id="addr"rows="4" cols="25">
	</textarea></td>
 </tr> 
 
 <tr>
 <td><label for="state">State * </label></td>
 <td><select onchange="print_city('state', this.selectedIndex);" id="sts" name ="stt" class="form-control" required>
  		</select></td>
 </tr> 
  
  <tr>
 <td><label for="city" >City *</label></td>
 <td><select id ="state" name="city" class="form-control" required>
		</select>
	<script language="javascript">print_state("sts");</script></td>
 </tr>
</tbody>
</table>
<br><br><br><br>
</div>
<div style="text-align:center">
  <tr>
    <td><button type="submit" name="action" value="submit">Submit</button></td>&nbsp;&nbsp;
    <td><button type="reset" value="Reset">Reset</button></td>
  </tr>
</div>
</form>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>