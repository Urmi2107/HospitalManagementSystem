<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<title>Search Criteria - Hospital Management System</title>
</head>
<script>
function isEmpty(val){
	if (val==="undefined" || val == null || val == "" || val.trim().length==0) {
        return true;
     }else{
    	 return false;
     }
}
    function checkfields() {
    	var patientName = document.getElementById("PatientName").value;
    	var age = document.getElementById("age").value;
    	var department = document.getElementById("department").value;
    	var doctor = document.getElementById("doctor").value;
    	var bedNo = document.getElementById("bedNo").value;
        var dateOfAdmission = new Date(document.getElementById("DateofAdmission").value).setHours(0, 0, 0, 0);
        var dateOfDischarge = new Date(document.getElementById("DateofDischarge").value).setHours(0, 0, 0, 0);
        if(isEmpty(patientName) && isEmpty(age) && isEmpty(department) && isEmpty(doctor) && isEmpty(bedNo) && isEmpty(document.getElementById("DateofAdmission").value) && isEmpty(document.getElementById("DateofDischarge").value)){
        	document.getElementById("checkFieldErrMsg").innerHTML = "Please choose at least one search criteria to proceed";
            return false;  
        }else if((dateOfAdmission-dateOfDischarge)>0) {
            document.getElementById("checkFieldErrMsg").innerHTML = "Date of Admission can not exceed Date of Discharge";
            return false;  
        }else{
        	document.getElementById("checkFieldErrMsg").innerHTML = ""; 
            return true;
        }
 	
  }
    
</script>
<body class="background">
	<div class="formContent">
<!-- <h3>Work in progress</h3> -->
<form action="<%=request.getContextPath()%>/SearchResults" method="post" class="content"> 
	<fieldset>
 					 <legend>Search By Basic Details</legend>
 			<table>
 				<tr>
 					<td>Patient Name</td>
 					<td><input type="text" id="PatientName" class="fadeIn second" name="PatientName" style="width:160px" placeholder="Patient Name"
                           pattern="[a-zA-Z ]*$" title="Please enter valid name. Only alphabets and spaces are allowed."></td>
 				</tr>
 				<tr>
 					<td>Age</td>
 					<td><select id="age" name="age" style="width:160px" onfocus='this.size=5;' onblur='this.size=1;' onchange='this.size=1; this.blur();'> 
                           <option value="">Select Age</option>
                           <option value="18">0 - 18</option>
  						   <option value="40">19 - 40</option>
						   <option value="60">41 - 60</option>
						   <option value="80">61 - 80</option>
						   <option value="100">81 - 100</option>
                        </select></td>
 					</tr>
 				</table>
 				</fieldset><br/>
 				 <fieldset>
			       <legend>Search By Medical Details</legend>
			       <table>
			       <tr>
 					<td>Department</td>
 					<td><select id="department" name="department" style="width:160px" onfocus='this.size=5;' onblur='this.size=1;' onchange='this.size=1; this.blur();'> 
                           <option id="selectionDept" value="">Select Department</option>
                        </select></td>
 					</tr>
 					<tr>
 					<td>Doctor</td>
 					<td><select id="doctor" name="doctor" style="width:160px" onfocus='this.size=5;' onblur='this.size=1;' onchange='this.size=1; this.blur();'>
                           <option id="selectionDoctor" value="">Select Doctor</option>
                        </select></td>
 					</tr>
			       <tr>
 					<td>Date of Admission</td>
 					<td><input type="date" id="DateofAdmission" name="DateofAdmission" style="width:160px"></td>
 					</tr>
 					<tr>
 					<td>Date of Discharge</td>
 					<td><input type="date" id="DateofDischarge" name="DateofDischarge" style="width:160px"></td>
 					</tr>
			       <tr>
 					<td>Bed No.</td>
 					<td><select id="bedNo" name="bedNo" style="width:160px" onfocus='this.size=5;' onblur='this.size=1;' onchange='this.size=1; this.blur();'> 
                           <option value="">Select Bed No.</option>
                        </select></td>
 				</tr></table>
			    </fieldset>
 				<span id = "checkFieldErrMsg" style="color:red"> </span> 
 				<%-- <input type="hidden" name="patientId" value="${patientId}" /> --%>
				<br/><input type = "submit" value="Search" onclick="var temp=checkfields(this);return temp;"/><br/>
 				</form>
 				</div>
 <script>
 $(document).ready(function () {
	 $.ajax({
         url: "DeptDocServlet",
         method: "GET",
         data: {operation: 'allDoctors'},
         success: function (data, textStatus, jqXHR) {
             console.log(data);
             let obj = $.parseJSON(data);
             $.each(obj, function (key, value) {
                 $('#doctor').append('<option value="' + value.docId + '">' + value.docName + '</option>')
             });
         },
         error: function (jqXHR, textStatus, errorThrown) {
             $('#doctor').append('<option>Doctor Unavailable</option>');
         },
         cache: false
     }); 
     
         $.ajax({
             url: "DeptDocServlet",
             method: "GET",
             data: {operation: 'department'},
             success: function (data, textStatus, jqXHR) {
                 console.log(data);
                 let obj = $.parseJSON(data);
                 $.each(obj, function (key, value) {
                     $('#department').append('<option value="' + value.deptId + '">' + value.deptName + '</option>')
                 });
             },
             error: function (jqXHR, textStatus, errorThrown) {
                 $('#department').append('<option>Department Unavailable</option>');
             },
             cache: false
         });
         
         $('#department').change(function () {
             $('#doctor').find('option').remove();
         	$('#doctor').append('<option value="">Select Doctor</option>'); 
         	
             let deptId = $('#department').val();
             let data = {
                 operation: "doctor",
                 id: deptId
             };
             
             $.ajax({
                 url: "DeptDocServlet",
                 method: "GET",
                 data: data,
                 success: function (data, textStatus, jqXHR) {
                     console.log(data);
                     let obj = $.parseJSON(data);
                     $.each(obj, function (key, value) {
                         $('#doctor').append('<option value="' + value.docId + '">' + value.docName + '</option>')
                     });
                 },
                 error: function (jqXHR, textStatus, errorThrown) {
                     $('#doctor').append('<option>Doctor Unavailable</option>');
                 },
                 cache: false
             });
         });
         
	 $.ajax({
         url: "BedNoServlet",
         method: "GET",
         data: {operation: 'allBedNo'},
         success: function (data, textStatus, jqXHR) {
             console.log(data);
             let obj = $.parseJSON(data);
             $.each(obj, function (key, value) {
                 $('#bedNo').append('<option value="' + value.bedId + '">' + value.bedNo + '</option>')
             });
         },
         error: function (jqXHR, textStatus, errorThrown) {
             $('#bedNo').append('<option>Bed Unavailable</option>');
         },
         cache: false
     });
 });
 
</script>
</body>
<style type="text/css">
tr,td {
           width:150px;
           text-align:center;
           padding:5px
       }
legend{
	font-size: 19px;
}
</style>
</html>