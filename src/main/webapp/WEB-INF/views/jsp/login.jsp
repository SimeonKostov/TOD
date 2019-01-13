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

<link rel="stylesheet" type="text/css" href="<c:url value="css/login.css"/>"/>

<title></title>
</head>
<body>

<!-- 	LOG IN CONTAINER -->
	<div class="jumbotron justify-content-center" style="background-color: transparent; border-radius: 0;">
<!-- 		<div class="jumbotron justify-content-center" style="background-color: #AA46F2; border-radius: 0;"> -->
	  
<!-- 	  FORM CONTAINER -->
	  <div class="form-container justify-content-center">
<!-- 	  	LOGIN FORM -->
	  	<form action="login" method="post" accept-charset="UTF-8">
		  <div class="form-group row justify-content-center"">
		  
		    <div class="col-sm-10">
		      <c:if test="${loginError != null }">
		      	<div class="reg-error" style="background-color: transparent; color: red; text-align: center;">${loginError}</div>
		      </c:if>
		      <c:if test="${emptyError != null }">
		      	<div class="reg-error" style="background-color: transparent; color: red; text-align: center;">${emptyError}</div>
		      </c:if>
		      <input type="text" class="form-control" id="inputUsername" placeholder="Username or email" name="identifier" required="">
		    </div>
		  </div>
		  
		  <div class="form-group row justify-content-center"">
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password" required="" >
		    </div>
		  </div>
		  
		  <div class="form-group row justify-content-center">
		    <div class="col-sm-10">
		      <input type="submit" class="form-control login-button" id="loginButton" value="Log In">
<!-- 				<input type="submit" class="form-control login-button" id="loginButton" value="Log In" style="margin-top: 10px; border-color: #ffffff; background-color: #AA46F2; color: #ffffff;"> -->
				<table class="table">
				    <tr>
				      <td scope="col" align="left" style="padding: 0; margin: 0;"> <a class="nav-link" href="signupPage" style="font-size: 12pt; text-decoration: underline; color: #ff8a84;">Register</a></td>
				      <td scope="col" align="right" style="padding: 0; margin: 0;"> <a class="nav-link" href="forgottenPassword" style="font-size: 12pt; text-decoration: underline; color: #ff8a84; ">Forgotten password</a></td>
				    </tr>
				</table>
		    </div>
		  </div>
		  
		</form>
<!-- 		END OF LOGIN FORM -->

<!--    SOCIAL LOG IN CONTAINER -->
		<div class="jumbotron-spacer-div justify-content-center" >
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
<!-- END OF SOCIAL LOG IN CONTAINER -->

	  </div>
<!-- END OF FORM CONTAINER -->
	</div>
<!--END OF LOG IN CONTAINER-->

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>