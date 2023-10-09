<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Options - Hospital Management System</title>
</head>
<script type="text/javascript">
    document.getElementById("proceedBtn").onclick = function () {
        location.href = "https://www.google.co.in/";
    };
</script>
<jsp:include page="navBar.jsp"></jsp:include>
<body class="background">
<div>
            <div id="formContent">
                <form action="<%=request.getContextPath()%>/search.jsp" method="get" onsubmit="search.jsp" class="content">
                	<h4>Select one option:</h4>
 				      <input type="radio" id="detailedView" name="searchOption" value="detailedView" required>
					  <label for="detailedView">View details of a particular Patient</label><br/><br/>
					  <input type="radio" id="tabluarView" name="searchOption" value="tabluarView">
					  <label for="tabluarView">Search based on Patient's basic and medical details</label>
					  <br/><br/><input type="submit" id=proceedBtn value="Proceed" >
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