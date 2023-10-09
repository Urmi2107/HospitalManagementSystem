<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Patient - Hospital Management System</title>
</head>
<jsp:include page="navBar.jsp"></jsp:include>
<body class="background">
<div>
            <div id="formContent">
                <form action="<%=request.getContextPath()%>/DeletePatientDetails" method="post" class="content">
                <c:choose>
					 <c:when test="${not empty deletePatientDetailsMsg}">
				    				<div style="color:black">${deletePatientDetailsMsg}</div><br/>
					 </c:when>
					 <c:otherwise>
							 <c:if test="${not empty patientIdErrorMsg}">
			    				<div style="color:#ff0000">${patientIdErrorMsg}</div><br/>
							</c:if>
						   <table>
		                  	 <tr>
		                   <td>Enter Patient ID<span style="color:#ff0000">*  </span>  </td>
		                   <td><input type="number" id="patientId" name="patientId" required
		                           placeholder="Patient ID" required />
		                    </table>
		                     <br/>
		                    <input type="submit" value="Submit"><br/><br/>
					 </c:otherwise>
				</c:choose>
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