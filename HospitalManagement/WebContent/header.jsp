<div id="header-wrapper" style="background:deepskyblue; position:relative; top:0;width:100%">
			<h1 style="text-align:center">ABC HOSPITAL MANAGEMENT SYSTEM</h1>
<% if(session.getAttribute("username")!=null) { %>
	<jsp:include page="dropdown.jsp"></jsp:include>
	<form action="LoginController" method="post">
			<input type="hidden" name="action" value="logout">

					<div style="margin-top: 10px;" align="right">Hello <%=session.getAttribute("username") %>&nbsp;&nbsp;<button style="background-color:black; color:white" type="submit" value="logout">Logout</button></div>
				</form>
			<%} %>

<hr style="height:2px; color:black; background-color:black">

</div>

