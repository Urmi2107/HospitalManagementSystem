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
<title>View Patient Details - Hospital Management System</title>
</head>
<jsp:include page="navBar.jsp"></jsp:include>
<body class="background">
	<div class="content">
	<h3><div><b>Patient Details</b></div></h3><br/>
	<h4>Patient ID : ${patientDetails.patientId}</h4><br/>
	<fieldset>
 					 <legend>Basic Details</legend>
				<div><b>Patient Name : </b>${patientDetails.patientName}</div><br/>
				<div><b>Gender : </b>${patientDetails.gender} </div><br/>
				<div><b>Date Of Birth : </b>${patientDetails.dateOfBirth}</div><br/>
				<div><b>Age : </b>${patientDetails.age} </div><br/>
				<div><b>Contact No. : </b>${patientDetails.patientContactNo}</div><br/>
				<c:choose>
					 <c:when test="${not empty patientDetails.patientEmail}">
				    				<div><b>Email ID : </b>${patientDetails.patientEmail} </div><br/>
					 </c:when>
					 <c:otherwise>
					    <div><b>Email ID : </b>-</div><br/>
					 </c:otherwise>
				</c:choose>
				<div><b>Address : </b>${patientDetails.patientAddress}</div><br/>
				</fieldset>
				<fieldset>
 					 <legend>Medical Details</legend>
 					 <div><b>Department : </b>${patientDetails.medicalDept}</div><br/>
					<div><b>Doctor : </b>${patientDetails.doctorName} </div><br/>
					<div><b>Description : </b>${patientDetails.description}</div><br/>
					<div><b>Date of Admission : </b>${patientDetails.dateOfAdmission} </div><br/>
				<c:choose>
					 <c:when test="${not empty patientDetails.dateOfDischarge}">
				    				<div><b>Date of Discharge : </b>${patientDetails.dateOfDischarge}</div><br/>
					 </c:when>
					 <c:otherwise>
					    <div><b>Date of Discharge : </b>-</div><br/>
					 </c:otherwise>
				</c:choose>
				<c:choose>
					 <c:when test="${not empty patientDetails.bedNo}">
				    				<div><b>Bed No. : </b>${patientDetails.bedNo}</div><br/>
					 </c:when>
					 <c:otherwise>
					    <div><b>Bed No. : </b>-</div><br/>
					 </c:otherwise>
				</c:choose>
				
			 </fieldset><br/><br/>
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
</style>
</html>