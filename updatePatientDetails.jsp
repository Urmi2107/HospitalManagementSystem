<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>

<title>Update Patient Details - Hospital Management System</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<script>
    function verifyDate() {
        var dateOfBirth = new Date(document.getElementById("DateofBirth").value).setHours(0, 0, 0, 0);
        var todayDate = new Date().setHours(0, 0, 0, 0); 
        var dateOfAdmission = new Date(document.getElementById("DateofAdmission").value).setHours(0, 0, 0, 0);
        var dateOfDischarge = new Date(document.getElementById("DateofDischarge").value).setHours(0, 0, 0, 0);
 		if (dateOfBirth >= todayDate) {
           document.getElementById("verifyDateErrMsg").innerHTML = "Date of Birth must be earlier than today's date";  
           return false;  
        }
        else if((dateOfAdmission-dateOfDischarge)>0) {
            document.getElementById("verifyDateErrMsg").innerHTML = "Date of Admission can not exceed Date of Discharge"; 
            return false;  
        }else{
        	document.getElementById("verifyDateErrMsg").innerHTML = ""; 
            return true;
        }
 	
  }
</script>
<jsp:include page="navBar.jsp"></jsp:include>
<body class="background">
	<div class="formContent">
	 <form action="<%=request.getContextPath()%>/UpdatePatientDetails" method="post" class="content">
	 <h3><div><b>Update Patient Details</b></div></h3><br/>
	  <h4>Patient ID : ${patientDetails.patientId}</h4><br/>
	  <fieldset>
 					 <legend>Basic Details</legend>
 					 <table>
 					<tr>
 					<td>Patient Name<span style="color:#ff0000">*</span></td>
 					<td><input type="text" id="PatientName" class="fadeIn second" name="PatientName" value="${patientDetails.patientName}"
                           pattern="[a-zA-Z ]*$" title="Please enter valid name. Only alphabets and spaces are allowed." required></td>
 					</tr>
	 				<tr>
	 				<td>Gender<span style="color:#ff0000">*</span></td>
	 				<c:choose>
						 <c:when test="${patientDetails.gender eq 'F'}">
					    				<td>  <input type="radio" id="female" name="gender" value="F" required checked="checked">
						  				<label for="female">Female</label>
						 				 <input type="radio" id="male" name="gender" value="M">
						 				 <label for="male">Male</label></td>
						 </c:when>
						 <c:otherwise>
						    		<td>  <input type="radio" id="female" name="gender" value="F" required >
						  			<label for="female">Female</label>
						  			<input type="radio" id="male" name="gender" value="M" checked="checked">
						  			<label for="male">Male</label></td>
						 </c:otherwise>
					</c:choose>
	 				</tr>
	 				<tr>
 					<td>Date of Birth<span style="color:#ff0000">*</span></td>
 					<td><input type="date" id="DateofBirth" name="DateofBirth" required value="${patientDetails.dateOfBirth}" style="width:160px"></td>
 					</tr>
 					 <tr> 
                    <td>Contact No.<span style="color:#ff0000">*</span></td>
                    <td><input type="text" id="PatientContact" class="fadeIn second" name="PatientContact" required value="${patientDetails.patientContactNo}"
                            pattern="[0-9]{10}" title="Please enter valid contact No. Only numbers are allowed. Must contain 10 digits"></td>
                  	</tr>
                  	 <tr>
 				  	<td>Email ID</td>
 				  	<td> <input type="email" id="PatientEmail" class="fadeIn second" name="PatientEmail" value="${patientDetails.patientEmail}"
                            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Please enter valid Email ID."></td>
                  	</tr>
                  	<tr>
 					<td>Patient Address<span style="color:#ff0000">*</span></td>
 					<td><input type="text" id="PatientAddress" class="fadeIn second" name="PatientAddress" required value="${patientDetails.patientAddress}"
                           pattern="^[#.0-9a-zA-Z\s,/-]+$" title="Specialized symbols like @,$,^,%,etc. is not allowed."></td>
 					</tr>
 					</table>
 					</fieldset><br/>
				<fieldset>
 					 <legend>Medical Details</legend>
 					 <table>
 					 <tr>
 					<td>Department<span style="color:#ff0000">*</span></td>
 					<td><select id="department" name="department" style="width:180px" onfocus='checkDropdown(this)' onblur='this.size=1;' onchange='this.size=1; this.blur();'>
                         	<!-- <option>Select Department</option> -->
                           <option id="selectionDept" value="${deptId}" selected>${patientDetails.medicalDept}</option>
                        </select></td>
 					</tr>
 					<tr>
 					<td>Doctor<span style="color:#ff0000">*</span></td>
 					<td><select id="doctor" name="doctor" style="width:180px" onfocus='size=5;' onblur='this.size=1;' onchange='this.size=1; this.blur();'>
                           <!-- <option>Select Doctor</option> -->
                           <option value="${docId}" selected>${patientDetails.doctorName}</option>
                        </select></td>
 					</tr>
 					<%--  <div><b>Department : </b>${patientDetails.medicalDept}</div><br/>
					<div><b>Doctor : </b>${patientDetails.doctorName} </div><br/> --%>
					<tr>
 					<td>Description<span style="color:#ff0000">*</span></td>
 					<td>
 					<textarea id="description" name="description" rows="7" cols="20" required >${patientDetails.description}</textarea></td>
 					</tr>
 					<tr>
 					<td>Date of Admission<span style="color:#ff0000">*</span></td>
 					<td><input type="date" id="DateofAdmission" name="DateofAdmission" required value="${patientDetails.dateOfAdmission}" style="width:160px"></td>
 					</tr>
 					<tr>
 					<td>Date of Discharge</td>
 					<td><input type="date" id="DateofDischarge" name="DateofDischarge" value="${patientDetails.dateOfDischarge}" style="width:160px"></td>
 					</tr>
 					<tr>
 					<td>Bed No.</td>
 					<%--  <td><select id="bedNo" name="bedNo" style="width:180px" onfocus='this.size=5;' onblur='this.size=1;' onchange='this.size=1; this.blur();'> 
                           <option selected>${patientDetails.bedNo}</option>
                        </select></td> --%>
                    <td> <select id="bedNo" name="bedNo" style="width:180px" onfocus='this.size=5;' onblur='this.size=1;' onchange='this.size=1; this.blur();'>
                    	<c:choose>
						 <c:when test="${not empty patientDetails.bedNo}">
						 			<option value="">Select Bed</option>
				    				<option value="${patientDetails.bedNo}" selected>${patientDetails.bedNo}</option>
					 </c:when>
					 <c:otherwise>
					    <option value="">Select Bed</option>
					 </c:otherwise>
					</c:choose>
                   </select> </td>
 				</tr>
			 </table>
			 </fieldset>
			 <input type="hidden" name="patientId" value="${patientDetails.patientId}" />
			 <input type="hidden" name="bedNoHidden" value="${patientDetails.bedNo}" />
			 <input type="hidden" name="departmentHidden" value="${deptId}" />
			 <input type="hidden" name="doctorHidden" value="${docId}" /><br/>
			 <span id = "verifyDateErrMsg" style="color:red"> </span><br/>
			<input type = "submit" value="Save" onclick="var temp=verifyDate(this);return temp;">
			 </form>
	</div>
<script type="text/javascript">

function checkDropdown(x) {
	x.size=5;
	$('#department').find('#selectionDept').remove();
}
	
	 $(document).ready(function () {
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
           /*   $('select').formSelect(); */
         },
         error: function (jqXHR, textStatus, errorThrown) {
             $('#department').append('<option>Department Unavailable</option>');
         },
         cache: false
     });
	 $('#department').change(function () {
         $('#doctor').find('option').remove();

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
                /*  $('select').formSelect(); */
             },
             error: function (jqXHR, textStatus, errorThrown) {
                 $('#doctor').append('<option>Doctor Unavailable</option>');
             },
             cache: false
         });
     });
	/*  $('#bedNo').append('<option value="">Select Bed</option>'); */
	 $.ajax({
         url: "BedNoServlet",
         method: "GET",
         data: {operation: 'bedNo'},
         success: function (data, textStatus, jqXHR) {
             console.log(data);
             let obj = $.parseJSON(data);
             $.each(obj, function (key, value) {
                 $('#bedNo').append('<option value="' + value.bedNo + '">' + value.bedNo + '</option>')
             });
           /*   $('select').formSelect(); */
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
.background {
  background-image: url("images/after login.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  opacity: 0.8;
}

.content {
  max-width: 400px;
  margin-top: 20px;
  margin-bottom: 350px;
  margin-right: 200px;
  margin-left: 500px;
  background: white;
  text-align: center;
  border-radius: 25px;
  padding: 10px ;
  border: 3px solid blue;
}
tr,td {
           width:150px;
           text-align:center;
           padding:5px
       }
</style>
</html>