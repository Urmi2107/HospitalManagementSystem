<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<title>Edit Profile - Hospital Management System</title>
</head>
<jsp:include page="navBar.jsp"></jsp:include>
<body class="background">
	<div class="formContent">
		 <form action="<%=request.getContextPath()%>/EditProfile" method="post" class="content"> 
 		 <c:if test="${not empty editProfileError}">
	    				<div style="color:#ff0000">${editProfileError}</div>
		 </c:if> 
 			<h3><b>Edit My Profile</b></h3> 
 			<i class="glyphicon glyphicon-user" style="color: blue"></i>
 			<i class='far fa-edit' style="color: blue"></i>
 			<table>
 				<tr>
 					<td>Name<span style="color:#ff0000">*</span>		</td>
 					<td><input type="text" id="Name" class="fadeIn second" name="Name" value="${name}"
                           pattern="[a-zA-Z ]*$" title="Please enter valid name. Only alphabets and spaces are allowed." required> <br/></td>
 				</tr><br/><br/>
 				 <tr> 
                    <td>Contact No.		</td>
                    <td><input type="text" id="Contact" class="fadeIn second" name="Contact" value="${contactNo}"
                            pattern="[0-9]{10}" title="Please enter valid contact No. Only numbers are allowed. Must contain 10 digits"> <br/></td>
                  </tr><br/><br/>    
 				  <tr>
 				  	<td>Email ID<span style="color:#ff0000">*</span>		</td>
 				  	<td> <input type="email" id="Email" class="fadeIn second" name="Email" value="${email}"
                            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Please enter valid Email ID." required> <br/></td>
                  </tr>
 			</table>
			<br/><input type = "submit" value="Save"><br/>
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
  max-height: 400px;
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