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
<title>User Home - Hospital Management System</title>
</head>
<jsp:include page="navBar.jsp"></jsp:include>
<body class="background">
	<div class="content">
	    <h3><div><b>${welcomeMsg}</b></div></h3>
		<h4>My Profile <i class="glyphicon glyphicon-user" style="color: blue"></i></h4><br/>
				<div><b>Name : </b>${name}</div><br/>
				<fieldset>
 					 <legend>Credentials</legend>
				<div><b>Username : </b>${userName}</div><br/>
				<div>Edit Password <a href="modifyPassword.jsp"><i class='far fa-edit' style="color: blue"></i></a></div><br/>
				</fieldset>
				<fieldset>
 					 <legend>Contact Details</legend>
				<c:choose>
					 <c:when test="${not empty contactNo}">
				    				<div><b>Phone : </b>${contactNo}</div><br/>
					 </c:when>
					 <c:otherwise>
					    <div><b>Phone : </b>-</div><br/>
					 </c:otherwise>
				</c:choose>
				
				<c:choose>
					 <c:when test="${not empty email}">
				    				<div><b>Email ID : </b>${email}</div>
					 </c:when>
					 <c:otherwise>
					    <div><b>Email ID : </b>-</div>
					 </c:otherwise>
			   </c:choose>
			 </fieldset><br/><br/>
			 <div>Edit Profile <a href="EditProfile"><i class='far fa-edit' style="color: blue"></i></a></div><br/><br/>
		</div>
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