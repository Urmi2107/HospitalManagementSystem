<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search - Hospital Management System</title>
</head>
<jsp:include page="navBar.jsp"></jsp:include>
<body class="background">
	<div>
	<c:set var="option" value='${param.searchOption}'/>
	<%-- <c:out value="${option}"/> --%>
            <div id="formContent">
                <c:choose>
                	 <c:when test="${option eq 'detailedView'}">
                		<form action="<%=request.getContextPath()%>/ViewPatientDetails" method="post" class="content">
                		 <c:if test="${not empty patientIdErrorMsgSearch}">
			    				<div style="color:#ff0000">${patientIdErrorMsgSearch}</div><br/>
							</c:if>
		                  <div>Enter Patient ID to view Details<span style="color:#ff0000">*  </span></div><br/>
		                  <div><input type="number" id="patientId" name="patientId" required
		                           placeholder="Patient ID" required /></div>
		                    <br/><br/>
		                    <input type="hidden" name="searchOption" value="detailedView" />
		                    <input type="hidden" name="actionToBePerformed" value="search" />
		                    <input type="submit" value="Submit"><br/></form>
		                  </c:when>
					 <c:otherwise>
							<jsp:include page="searchCriteria.jsp"></jsp:include>
					 </c:otherwise>
				</c:choose>
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
  margin-top: 30px;
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