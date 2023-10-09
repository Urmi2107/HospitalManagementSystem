<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify Password - Hospital Management System</title>
</head>
<script>
    function checkNewPassword() {
        var password = document.getElementById("new_password").value;
        var rePassword = document.getElementById("re_new_password").value;
        if (password != rePassword) {
            
            document.getElementById("new_password").style.borderColor = "#E34234";
            document.getElementById("re_new_password").style.borderColor = "#E34234";
            document.getElementById("errMsg_modifyPass").innerHTML = "Passwords are not same";  
            return false;  
        }else{
        	document.getElementById("errMsg_modifyPass").innerHTML = ""; 
            return true;
        }
       
    }
</script>
<body class="background">
	<div>
            <div id="formContent">
                <form action="<%=request.getContextPath()%>/ModifyPassword" method="post" class="content"v onsubmit="return checkNewPassword(this)">
                	 <c:if test="${not empty newPasswordError}">
	    				<div style="color:#ff0000">${newPasswordError}</div>
		 			</c:if>
                 <h2>Change Password</h2>
                  <table>
                  <tr>
 				  	<td>Enter Email ID<span style="color:#ff0000">*</span></td>
 				  	<td> <input type="email" id="Email" class="fadeIn second" name="Email"
                           placeholder="Enter Email ID" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Please enter valid Email ID." required> <br/></td>
                  </tr>
                   <tr>
                   <td>Enter Username<span style="color:#ff0000">*</span>  </td>
                   <td><input type="text" id="Username" name="Username"
                           placeholder="Username" required pattern="[a-zA-Z][a-zA-Z0-9-_]*$" title="Please enter valid user name. Only letters (either case), numbers, hyphens, underscores are allowed. The username must start with a letter."> <br/></td>
                    </tr>
                    
                   	  <tr>
                     <td>Enter New Password<span style="color:#ff0000">*</span> </td>
                     <td><input type="password" id="new_password"
                           name="new_password" placeholder="New password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{3,20}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 3 or more characters and not more than 20 characters"> <br/></td>
                   	 </tr>
                   	 <tr>
                    <td>Confirm Password<span style="color:#ff0000">*</span></td>
                    <td> <input type="password" id="re_new_password"
                           class="fadeIn third" name="re_new_password" placeholder="Enter New password Again" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{3,20}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 3 or more characters and not more than 20 characters" required> <br/></td>
                  </tr>
                     </table>
                      <span id = "errMsg_modifyPass" style="color:red"> </span> <br/>
                    <br/><input
                        type="submit" value="Submit"><br/><br/>
                </form>
              
            </div>
        </div>
	
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
</html>