<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="header.jsp"></jsp:include>
<% String errorMessage=(String) request.getAttribute("errormessage"); %>
<body>
<div class="outer">
	<div class="middle">
		<div class="inner">
			<% if(errorMessage!=null){ %>
					<center><h4 style="color:red"><%=errorMessage %></h4>
			<% }%>
			<form action="LoginController" method="post">
				<table align="center">
					<tr><td>Login as:</td><td><select name="loginType" id="loginType">
						<option></option>
						<option value="reg_exec">Registration Executive</option>
						<option value="pharmacist">Pharmacist</option>
						<option value="diagnostic_exec">Diagnostic Executive</option>
					</select></td></tr>
					<tr><td>Username:</td><td><input type="text" name="username"></td></tr>
					<tr><td>Password:</td><td><input type="password" name="password"></td></tr>
					<tr><td></td><td><span style="color:red">${error}</span>
					<tr><td></td><td><input type="submit" name="action" value="login">&nbsp&nbsp&nbsp  <input type="reset" value="reset"></td></tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>