<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Patient Medical Details - Hospital Management System</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<script>
    function checkTwoDates() {
    	
	        var dateOfAdmission = new Date(document.getElementById("DateofAdmission").value).setHours(0, 0, 0, 0);
	        var dateOfDischarge = new Date(document.getElementById("DateofDischarge").value).setHours(0, 0, 0, 0);
	        if((dateOfAdmission-dateOfDischarge)>0) {
	            //document.getElementById("dateOfAdmission").style.borderColor = "#E34234";
	            //document.getElementById("dateOfDischarge").style.borderColor = "#E34234";
	            document.getElementById("errMsgTwoDates").innerHTML = "Date of Admission can not exceed Date of Discharge";  
	            return false;  
	        }else{
	        	document.getElementById("errMsgTwoDates").innerHTML = ""; 
	            return true;
	        }
  
  }
</script>
<jsp:include page="navBar.jsp"></jsp:include>
<body class="background">
	<div class="formContent">
		 <form action="<%=request.getContextPath()%>/AddPatientMedicalDetails" method="post" onsubmit="return checkTwoDates(this)" class="content">
		 	<h3>Patient ID is : ${patientId}</h3>
		 	<fieldset>
 					 <legend>2.Medical Details</legend>
 					<table>
 					<tr>
 					<td>Department<span style="color:#ff0000">*</span></td>
 					<td><select id="department" name="department" required style="width:180px" onfocus='checkDept(this)' onblur='this.size=1;' onchange='this.size=1; this.blur();'> 
                           <option id="selectionDept" value="">Select Department</option>
                        </select></td>
 					</tr>
 					<tr>
 					<td>Doctor<span style="color:#ff0000">*</span></td>
 					<td><select id="doctor" name="doctor" required  style="width:180px" onfocus='this.size=5;' onblur='this.size=1;' onchange='this.size=1; this.blur();'>
                           <option value="">Select Doctor</option>
                        </select></td>
 					</tr>
 					<tr>
 					<td>Description<span style="color:#ff0000">*</span></td>
 					<td>
 					<textarea id="description" name="description" rows="7" cols="20" required></textarea></td>
                         
 				</tr>
 				<tr>
 					<td>Date of Admission<span style="color:#ff0000">*</span></td>
 					<td><input type="date" id="DateofAdmission" name="DateofAdmission" required style="width:160px"></td>
 				</tr>
 				<tr>
 					<td>Date of Discharge</td>
 					<td><input type="date" id="DateofDischarge" name="DateofDischarge" style="width:160px"></td>
 				</tr>
 				<tr>
 					<td>Bed No.</td>
 					<td><select id="bedNo" name="bedNo" style="width:180px" onfocus='this.size=5;' onblur='this.size=1;' onchange='this.size=1; this.blur();'> 
                           <option value="">Select Bed No.</option>
                        </select></td>
 				</tr>
 			</table>
 			<br/> <span id = "errMsgTwoDates" style="color:red"> </span> 
 			<input type="hidden" name="patientId" value="${patientId}" />
			<br/><input type = "submit" value="Save"><br/>
 			</fieldset>
		 </form>
		 </div> 
	<script type="text/javascript">
	
	/* function checkBedNo(x) {
		x.size=5;
		$('#bedNo').find('#selectionBedNo').remove();
	} */
	
	function checkDept(x) {
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
                  /*   $('#doctor').append('<option>Select Doctor</option>');  */

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
                
                $.ajax({
                    url: "BedNoServlet",
                    method: "GET",
                    data: {operation: 'bedNo'},
                    success: function (data, textStatus, jqXHR) {
                        console.log(data);
                        let obj = $.parseJSON(data);
                        $.each(obj, function (key, value) {
                            $('#bedNo').append('<option value="' + value.bedId + '">' + value.bedNo + '</option>')
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
  padding-top: 10px;
  padding-right: 30px;
  padding-bottom: 50px;
  padding-left: 20px;
  max-height: 700px;
  max-width: 400px;
  margin-top: 20px;
  margin-bottom: 350px;
  margin-right: 200px;
  margin-left: 500px;
  background: white;
  text-align: center;
  border-radius: 25px;
  border: 3px solid blue;
}


tr,td {
           width:150px;
           text-align:center;
           padding:5px
       }

</style>
</html>