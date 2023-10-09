<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login - Hospital Management System</title>
</head>
<body class="background">
        <div>
            <div id="formContent">
                <form action="<%=request.getContextPath()%>/Login" method="post" class="content">
	                <c:if test="${not empty loginError}">
	    				<div style="color:#ff0000">${loginError}</div>
					</c:if>
                 <h2>Sign In</h2>
                  <table>
                  	
                   <tr>
                   <td>Enter Username<span style="color:#ff0000">*</span>  </td>
                   <td><input type="text" id="Username" name="Username"
                           placeholder="Username" required pattern="[a-zA-Z][a-zA-Z0-9-_]*$" title="Please enter valid user name. Only letters (either case), numbers, hyphens, underscores are allowed. The username must start with a letter."> <br/></td>
                    </tr>
                    <tr>
                     <td>Enter Password<span style="color:#ff0000">*</span> </td>
                     <td><input type="password" id="password"
                           name="password" placeholder="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{3,20}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 3 or more characters and not more than 20 characters"> <br/></td>
                   	 </tr>
                     </table>
                    <br/><input
                        type="submit" value="Log In"><br/><br/>
                        <div>Forgot Password?  <a href="modifyPassword.jsp">Reset your Password</a></div>
                </form>

              
                <div id="formFooter">
                    <a class="underlineHover" href="register.jsp">Create Account</a>
                </div>

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
</style>
</html>