<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register - Hospital Management System</title>
</head>
<script>
    function checkPassword() {
        var password = document.getElementById("password").value;
        var rePassword = document.getElementById("repassword").value;
        if (password != rePassword) {
            
            document.getElementById("password").style.borderColor = "#E34234";
            document.getElementById("repassword").style.borderColor = "#E34234";
            document.getElementById("errMsg").innerHTML = "Passwords are not same";  
            return false;  
        }else{
        	document.getElementById("errMsg").innerHTML = ""; 
            return true;
        }
       
    }
</script>
<body class="background">
 <div id="formContent">
          
    
 <form action="<%=request.getContextPath()%>/Register" method="post" onsubmit="return checkPassword(this)" class="content">
 		 <c:if test="${not empty registrationError}">
	    				<div style="color:#ff0000">${registrationError}</div>
		 </c:if>
 	<h2>Sign Up</h2>
 			<table>
 				<tr>
 					<td>Enter Name<span style="color:#ff0000">*</span></td>
 					<td><input type="text" id="Name" class="fadeIn second" name="Name"
                           placeholder="Enter Name" pattern="[a-zA-Z ]*$" title="Please enter valid name. Only alphabets and spaces are allowed." required> <br/></td>
                 </tr>
                  <tr> 
                    <td>Enter Contact No.  </td>
                    <td><input type="text" id="Contact" class="fadeIn second" name="Contact"
                           placeholder="Enter Contact No." pattern="[0-9]{10}" title="Please enter valid contact No. Only numbers are allowed. Must contain 10 digits"> <br/></td>
                  </tr>     
 				  <tr>
 				  	<td>Enter Email ID<span style="color:#ff0000">*</span></td>
 				  	<td> <input type="email" id="Email" class="fadeIn second" name="Email"
                           placeholder="Enter Email ID" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Please enter valid Email ID." required> <br/></td>
                  </tr>
                  <tr>
                   <td>Enter Username<span style="color:#ff0000">*</span> </td>
                   <td><input type="text" id="Username" class="fadeIn second" name="Username"
                           placeholder="Enter Username" pattern="[a-zA-Z][a-zA-Z0-9-_]*$" title="Please enter valid user name. Only letters (either case), numbers, hyphens, underscores are allowed. The username must start with a letter." required> <br/></td>
                  </tr>
                  <tr>
                    <td> Enter Password<span style="color:#ff0000">*</span></td>
                    <td> <input type="password" id="password"
                           class="fadeIn third" name="password" placeholder="Enter Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{3,20}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 3 or more characters and not more than 20 characters" required> <br/></td>
                  </tr>
                  <tr>
                    <td>Re-enter Password<span style="color:#ff0000">*</span></td>
                    <td> <input type="password" id="repassword"
                           class="fadeIn third" name="repassword" placeholder="Enter Password Again" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{3,20}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 3 or more characters and not more than 20 characters" required> <br/></td>
                  </tr>
               </table>  
               <span id = "errMsg" style="color:red"> </span> <br/>
              <input type = "submit" value="Submit"><br/>
                   
   </form>

</body>

<style type="text/css">
.background {
  background-image: url("images/before login.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  opacity: 0.8;
}

.content {
  max-width: 400px;
  margin-top: 230px;
  margin-bottom: 350px;
  margin-right: 200px;
  margin-left: 640px;
  background: white;
  text-align: center;
  border-radius: 25px;
  padding: 30px ;
  border: 3px solid blue;
}

td {
   text-align: center;
 }

</style>
</html>