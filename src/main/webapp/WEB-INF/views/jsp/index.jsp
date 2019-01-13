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

<link rel="stylesheet" type="text/css" href="<c:url value="css/index.css"/>"/>

<title></title>
</head>
<body>
	
	<!-- 	LOG IN/SIGN UP CONTAINER -->
	<div class="jumbotron justify-content-center" style="margin-top: 3px; background-color: transparent; border-radius: 0;">
<!-- 		<div class="jumbotron justify-content-center" style="margin-top: 3px; background-color: #AA46F2; border-radius: 0;"> -->
	
		<!-- 	LOG IN/SIGN UP LINKS CONTAINER -->
		<div class="jumbotron-spacer-div"></div>
		
		<div class="signup-container ">
			<a class="btn btn-primary signup-button" href="signupPage" role="button">Sign Up</a>
		</div>
		
		<div class="login-container ">
			<a class="btn btn-primary login-button" href="loginPage" role="button">Log In</a>
		</div>
		<!-- END OF LOG IN/SIGN UP LINKS CONTAINER -->
		
		<!-- SOCIAL LOGIN CONTAINER -->
		<div class="jumbotron-spacer-div">
			<ul class="nav justify-content-center">
			
			  <li class="nav-item">
			    <a class="nav-link active" href="#">
			    	<img src="<c:url value="img/fa_f.png"/>" style="width: 35px; height: auto;">
			    </a>
			  </li>
			  
			  <li class="nav-item">
			    <a class="nav-link" href="#">
			    	<img src="<c:url value="img/fa_t.png"/>" style="width: 35px; height: auto;">
			    </a>
			  </li>
			  
			  <li class="nav-item">
			    <a class="nav-link" href="#">
			    	<img src="<c:url value="img/fa_i.png"/>" style="width: 35px; height: auto;">
			    </a>
			  </li>
			  
			</ul>
		</div>
		<!-- END OF SOCIAL LOGIN CONTAINER -->
		
	</div>
	<!-- END OF LOGIN/SIGN UP CONTAINER -->
	
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>