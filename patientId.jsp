<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient ID - Hospital Management System</title>
</head>
<jsp:include page="navBar.jsp"></jsp:include>
<body class="background">
	<div>
            <div id="formContent">
                <form action="<%=request.getContextPath()%>/ViewPatientDetails" method="post" class="content">
	                <c:if test="${not empty patientIdError}">
	    				<div style="color:#ff0000">${patientIdError}</div><br/>
					</c:if>
                  <table>
                   <tr>
                   <td>Enter Patient ID<span style="color:#ff0000">*  </span>  </td>
                   <td><input type="number" id="patientId" name="patientId" required
                           placeholder="Patient ID" required />
                    </table>
                    <input type="hidden" name="actionToBePerformed" value="update" />
                    <br/>
                    <input
                        type="submit" value="Submit"><br/><br/>
                </form>
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