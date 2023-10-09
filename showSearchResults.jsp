<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.hospital.management.system.PatientDetails"%>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<title>Show Search Results - Hospital Management System</title>
</head>
<jsp:include page="navBar.jsp"></jsp:include>
<body class="background">
		<c:choose>
				 <c:when test="${not empty EmptySearchResultMsg}">
						 			<div class="content">${EmptySearchResultMsg}</div>
				 </c:when>
				<c:otherwise>
				<div class="contentSearchResults">
				  <%! List<PatientDetails> patientDetailsList; %>
						<h3>Search Results:</h3>
						<table>
						    <tr>
						      <th>Patient Id</th>
						      <th>Patient Name</th>
						      <th>Gender</th>
						      <th>Date Of Birth</th>
						      <th>Age</th>
						      <th>Contact No.</th>
						      <th>Email</th>
						      <th>Address</th>
						       <th>Department</th>
						      <th>Doctor</th>
						      <th>Description</th>
						      <th>Date Of Admission</th>
						      <th>Date Of Discharge</th>
						      <th>Bed No</th>
						      <th>Action</th>
						    </tr>
						
						
						  <%
						  patientDetailsList = (List<PatientDetails>)request.getAttribute("patientDetailsList");
							if(patientDetailsList != null && !patientDetailsList.isEmpty()) {
								for(int i=0;i<patientDetailsList.size();i++) {		
							%>
							<tr>
								<td><%=patientDetailsList.get(i).getPatientId() %></td>
								<td><%=patientDetailsList.get(i).getPatientName() %></td>
								<td><%=patientDetailsList.get(i).getGender() %></td>
								<td><%=patientDetailsList.get(i).getDateOfBirth() %></td>
								<td><%=patientDetailsList.get(i).getAge() %></td>
								<td><%=patientDetailsList.get(i).getPatientContactNo() %></td>
								<td><%=patientDetailsList.get(i).getPatientEmail() %></td>
								<td><%=patientDetailsList.get(i).getPatientAddress() %></td>
								<td><%=patientDetailsList.get(i).getMedicalDept() %></td>
								<td><%=patientDetailsList.get(i).getDoctorName() %></td>
								<td><%=patientDetailsList.get(i).getDescription() %></td>
								<td><%=patientDetailsList.get(i).getDateOfAdmission() %></td>
								<td><%=patientDetailsList.get(i).getDateOfDischarge()!=null?patientDetailsList.get(i).getDateOfDischarge():""%></td>
								<td><%=patientDetailsList.get(i).getBedNo() %></td>
								<td><form action="<%=request.getContextPath()%>/ViewPatientDetails" method="post">
									<button class="btn" id="btnViewAction" name="patientId" value="<%=patientDetailsList.get(i).getPatientId()%>" style="float:left;width:50%;text-align:center;padding-left:3px;"><i class="fa fa-eye" aria-hidden="true"></i></button></form>
									<form action="<%=request.getContextPath()%>/ViewPatientDetails" method="post">
									<button class="btn" id="btnUpdateAction" name="patientId" value="<%=patientDetailsList.get(i).getPatientId()%>" style="float:right;width:45%;text-align:center;padding-left:1px;"><i class='far fa-edit'></i></button>
									<input type="hidden" name="actionToBePerformed" value="update" />
								</form>
								</td>
								</tr>
							<%
								}
							
							} %>
						  </table>
						  </div>
		   </c:otherwise>
		</c:choose>
	
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
  max-width: 300px;
  margin-top: 50px;
  margin-bottom: 350px;
  margin-right: 200px;
  margin-left: 490px;
  background: white;
  text-align: center;
  border-radius: 25px;
  padding: 30px ;
  border: 3px solid blue;
}
.contentSearchResults {
 /*  max-width: 400px; */
  margin-top: 20px;
  margin-bottom: 350px;
  margin-right: 50px;
  margin-left: 50px;
  background: white;
  text-align: center;
  border-radius: 25px;
  padding: 10px ;
  border: 3px solid blue;
}
table, th, td {
  border: 1px solid black;
  }
  th, td {
  background-color: #C9DFEC;
}
</style>
</html>