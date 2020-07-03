<div align="center">
<form action="LoginController" method="post">
				     <select name="Patient" >
	                     <option>Patient</option>
	                     <option>Update Patient</option>
		                 <option>Delete Patient</option>
		                 <option>View Patient</option>
		                 <option>Search Patient</option>
		                 <option>Patient Billing</option>
			
	                 </select>
	                 &emsp;
	                <select name="Pharmacy" >
	                     <option>Pharmacy</option>
		                 <option>Issue Medicines</option>
			
	                 </select>
	                 &emsp;
	                 <select name="Diagnostics" >
	                     <option>Diagnostics</option>
		                 <option>Add Diagnostics</option>
		
			
	                 </select>
	                 <div align="right">
					<input type="hidden" name="action" value="logout">
					<input type="submit" value="Logout"></div>
				</form>
<hr>
</div>