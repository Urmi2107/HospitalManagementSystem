<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<title>Add Patient Basic Details - Hospital Management System</title>
</head>
<script>
    function checkDate() {
        var dateOfBirth = new Date(document.getElementById("DateofBirth").value).setHours(0, 0, 0, 0);
        var todayDate = new Date().setHours(0, 0, 0, 0); 
        if (dateOfBirth >= todayDate) {
            
            document.getElementById("DateofBirth").style.borderColor = "#E34234";
            document.getElementById("errMsgDate").innerHTML = "Date of Birth must be earlier than today's date";  
            return false;  
        }else{
        	document.getElementById("errMsgDate").innerHTML = ""; 
            return true;
        }
       
    }
</script>
<jsp:include page="navBar.jsp"></jsp:include>
<body class="background">
	<div class="formContent">
		 <form action="<%=request.getContextPath()%>/AddPatientBasicDetails" method="post" onsubmit="return checkDate(this)" class="content"> 
 		 <%-- <c:if test="${not empty editProfileError}">
	    				<div style="color:#ff0000">${editProfileError}</div>
		 </c:if> --%> 
 			<h3><b>Add Patient Details</b></h3>
 			<p><i>There are two sections to be filled :<br/> Basic Details and Medical Details</i></p>
 			<fieldset>
 					 <legend>1.Basic Details</legend>
 			<table>
 				<tr>
 					<td>Patient Name<span style="color:#ff0000">*</span></td>
 					<td><input type="text" id="PatientName" class="fadeIn second" name="PatientName" 
                           pattern="[a-zA-Z ]*$" title="Please enter valid name. Only alphabets and spaces are allowed." required></td>
 				</tr>
 				<tr>
 				<td>Gender<span style="color:#ff0000">*</span></td>
 				<td>  <input type="radio" id="female" name="gender" value="F" required>
					  <label for="female">Female</label>
					  <input type="radio" id="male" name="gender" value="M">
					  <label for="male">Male</label></td>
 				</tr>
 				<tr>
 					<td>Date of Birth<span style="color:#ff0000">*</span></td>
 					<td><input type="date" id="DateofBirth" name="DateofBirth" required style="width:160px"></td>
 				</tr>
 				<tr>
 				 <tr> 
                    <td>Contact No.<span style="color:#ff0000">*</span></td>
                    <td><input type="text" id="PatientContact" class="fadeIn second" name="PatientContact" required
                            pattern="[0-9]{10}" title="Please enter valid contact No. Only numbers are allowed. Must contain 10 digits"></td>
                  </tr>
 				  <tr>
 				  	<td>Email ID</td>
 				  	<td> <input type="email" id="PatientEmail" class="fadeIn second" name="PatientEmail" 
                            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Please enter valid Email ID."></td>
                  </tr>
                  <tr>
 					<td>Patient Address<span style="color:#ff0000">*</span></td>
 					<td><input type="text" id="PatientAddress" class="fadeIn second" name="PatientAddress" required
                           pattern="^[#.0-9a-zA-Z\s,/-]+$" title="Specialized symbols like @,$,^,%,etc. is not allowed."></td>
 				</tr>
 			</table>
			<br/> <span id = "errMsgDate" style="color:red"> </span> <br/>
			<br/><input type = "submit" value="Save"><br/>
			</fieldset>
 			</form>
 			</div>
 			 

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