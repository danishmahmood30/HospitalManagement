<div id="header-wrapper">
	<div id="header" class="container">
		<div id="logo">
			<h1><a href="#">LifeLine Hospital</a></h1>
			<div style="margin-top: 10px;">
			<% if(session.getAttribute("username")!=null) { %>
				<h2 align="center" style="color:black; diplay:inline; margin-left:100px">Welcome <%=session.getAttribute("username") %>&nbsp;&nbsp;</h2>
				<form action="LoginController" method="post">
					<input type="hidden" name="action" value="logout">
					<input type="submit" value="Logout">
				</form>
			<%} %>
			</div>
		</div>
	</div>
</div>