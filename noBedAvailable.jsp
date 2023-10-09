<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>No Bed Available - Hospital Management System</title>
</head>
<jsp:include page="navBar.jsp"></jsp:include>
<body class="background">
 	<div class="content">
 		<div style="color:red">Sorry for the inconvenience <br/><br/> No Bed is available right now!</div><br/>
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
</style>
</html>