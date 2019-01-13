<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="<c:url value="css/passForgot.css"/>"/>

<title></title>
</head>
<body>

	<div class="field-container">
	  <i class="fas fa-lock" style="color: #ff8a84; font-size: 30px; margin-top: 30px;"></i>
	  <h2>Forgotten password</h2>
	  <form action="sendEmail" method="post" accept-charset="UTF-8">
	  	<div class="form-group row justify-content-center"">
			<div class="col-sm-10">
		        <c:if test="${emailError != null }">
	      	  		<div class="reg-error" style="background-color: transparent; color: red; text-align: center;">${emailError}</div>
			    </c:if>
			    <c:if test="${success != null }">
	      	  		<div class="reg-error" style="background-color: transparent; color: #51b2f7; text-align: center;">${success}</div>
			    </c:if>
		    	<input type="email" class="form-control" id="email" placeholder="Enter your email" name="email" required="" >
		    	<button class="submit-btn" type="submit">Proceed</button>
			</div>
		</div>
	  </form>
	</div>


<!-- JAVASCRIPT INCLUDES -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>