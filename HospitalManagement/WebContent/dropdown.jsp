<style>
	.dropbtn {
  background-color:black;
  color: #E0FFFF;
  padding: 8px;
  font-size: 12px;
  border: none;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
  position: relative;
  display: inline-block;
}


.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {background-color: #ddd;}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {display: block;}

/* Change the background color of the dropdown button when the dropdown content is shown */
.dropdown:hover .dropbtn {background-color: #3e8e41;}
</style>
<div style="text-align:center">
<div class="dropdown">
  <button class="dropbtn">Patient</button>
  <div class="dropdown-content">
  	<a href="addPatient.jsp">Add Patient</a>
  	<a href="searchPatient.jsp">Search Patient</a>
	<a href="searchForUpdate.jsp">Update Patient Details</a>
	<a href="searchForDelete.jsp">Delete Patient</a>
    <a href="<%=request.getContextPath()%>/PatientController?action=viewAll">View all Patients</a>
	<a href="searchForBilling.jsp">Start Billing</a>
    
  </div>
</div>
&emsp;&emsp;&emsp;&emsp;
<div class="dropdown">
  <button class="dropbtn">Pharmacy</button>
  <div class="dropdown-content">
    <a href="pHome.jsp">Issue Medicine</a>
  </div>
</div>
&emsp;&emsp;&emsp;&emsp;
<div class="dropdown">
  <button class="dropbtn">Diagnostics</button>
  <div class="dropdown-content">
    <a href="dHome.jsp">Add Diagnostics</a>
  </div>
</div>
</div>