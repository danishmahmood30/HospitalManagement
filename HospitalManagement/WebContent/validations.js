function validateForm()
{	//alert("hi");
	var ssnId= document.getElementById("ssn-id").value;
	var name= document.getElementById("pat-name").value;	
	var age = document.getElementById("pat-age").value;	
	var date= document.getElementById("pat-date").value;
	var bed= document.getElementById("bed").value;
	var address= document.getElementById("addr").value;
	var state= document.getElementById("sts").value;
	var city= document.getElementById("state").value;
	//alert(age);
	//alert(date);
	//alert(bed);
	//alert(address);
	//alert(state);
	//alert(city);
	var ssn_error = document.getElementById("ssn_error");
	var name_error = document.getElementById("name_error");
	var name_error = document.getElementById("age_error");
	var arr= ssnId.length;
	var letters = /^[A-Za-z ]+$/;
	var age_len = age.length;
	
	if (arr<9||arr>9){
		ssn_error.innerHTML="*SSN Id must contain 9 digits";
		return false
	}
	if (!name.match(letters)){
		name_error.innerHTML="*name must contain alphabets";
		return false
	}
	if (age_len>3){
		age_error.innerHTML="*age cannot be more than 3 digits";
		return false
	}
}