<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Navigation Bar - Hospital Management System</title>
</head>
<body>
<ul class="mx-auto">
  <!-- <li class="dropdown">
    <a href="javascript:void(0)" class="dropbtn">User Profile</a>
    <div class="dropdown-content">
      <a href="userHome.jsp">My Profile</a>
      <a href="LogoutServlet">Logout </a>
    </div>
   </li> -->
  <li><a href="LoginSuccess"><i class="fa fa-user"></i> My Profile</a></li>
  <li><a href="AddPatientBasicDetails"><i class="fa fa-plus-circle"></i> Add Details of Patient </a></li>
  <li><a href="searchOptions.jsp"><i class="fa fa-search"></i> Search Details of Patient </a></li>
  <li><a href="patientId.jsp"><i class="fa fa-edit"></i> Update Details of Patient </a></li>
  <li><a href="deletePatient.jsp"><i class="fa fa-trash"></i> Delete Details of Patient </a></li>	
  <li><a href="LogoutServlet"><i class="fa fa-power-off"></i> Logout </a></li>
 <!--  <li class="dropdown">
  		<a href="javascript:void(0)" class="dropbtn">Modify Details of Patient</a>
  		<div class="dropdown-content">
  		<a href="#">Update Details of Patient</a>
  		<a href="#">Delete Details of Patient</a>
  	</div>
  </li> -->
</ul>
</body>
<style>
ul {
  list-style-type: none;
  margin: 0 auto;
  padding: 0;
  overflow: hidden;
  background-color: #38444d;
  float:none;
  display: block;
  text-align: center;
}

li {
 display: inline-block;
  float: none;
}

li a, .dropbtn {
  display: inline-block;
  color: white;
  text-align: center;
  padding: 20px 20px;
  text-decoration: none;
}

li a:hover, .dropdown:hover .dropbtn {
  background-color: #3366FF;
  color: white;
}

li.dropdown {
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.dropdown-content a:hover {background-color: #f1f1f1;}
.dropdown:hover .dropdown-content {
  display: block;
}
</style>
</html>