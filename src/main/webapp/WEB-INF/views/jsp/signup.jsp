<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="<c:url value="css/signup.css"/>"/>

<title></title>
</head>
<body>

	<!-- 	SIGN UP CONTAINER -->
	<div class="jumbotron justify-content-center" style="background-color: transparent; border-radius: 0;">
<!-- 		<div class="jumbotron justify-content-center" style="background-color: #AA46F2; border-radius: 0;"> -->
	
	  <!-- 	  FORM CONTAINER -->
	  <div class="form-container justify-content-center">
	 	
		<!-- SIGN UP FORM -->
	  	<form:form action="registerUser" method="post" modelAttribute="user" acceptCharset="UTF-8">
		  <div class="form-group row justify-content-center"">
		  
		    <div class="col-sm-10">
		      <c:if test="${busyUsernameErr != null }">
		      	<div class="reg-error" style="background-color: transparent; color: red; text-align: center;">${busyUsernameErr}</div>
		      </c:if>
		      <div class="reg-error" style="background-color: transparent; color: red; text-align: center;"><form:errors path="username" size="20"/></div>
		      <form:input type="text" class="form-control" id="inputUsername" placeholder="Username" name="username" path="username" required=""/>
		    </div>
		  </div>
		  
		  <div class="form-group row justify-content-center"">
		    <div class="col-sm-10">
		      <div class="reg-error" style="background-color: transparent; color: red; text-align: center;"><form:errors path="fullName" size="30"/></div>
		      <form:input type="text" class="form-control" id="inputFullName" placeholder="Full name" name="fullName" path="fullName" required="" maxlength="30"/>
		    </div>
		  </div>
		  
		  <div class="form-group row justify-content-center"">
		    <div class="col-sm-10">
		      <c:if test="${busyEmailErr != null }">
		      	<div class="reg-error" style="background-color: transparent; color: red; text-align: center;">${busyEmailErr}</div>
		      </c:if>
		      <div class="reg-error" style="background-color: transparent; color: red; text-align: center;"><form:errors path="email" size="20"/></div>
		      <form:input type="email" class="form-control" id="inputEmail" placeholder="Email address" name="email" path="email" required=""/>
		    </div>
		  </div>
		  
		  <div class="form-group row justify-content-center"">
		    <div class="col-sm-10">
		      <div class="reg-error" style="background-color: transparent; color: red; text-align: center;"><form:errors path="password" size="20"/></div>
		      <form:input type="password" class="form-control" id="inputPassword" placeholder="Password (at least 8 characters)" name="password1" path="password" required=""/>
		    </div>
		  </div>
		  
		  <div class="form-group row justify-content-center"">
		    <div class="col-sm-10">
		      <c:if test="${passConfirmError != null }">
		      	<div class="reg-error" style="background-color: transparent; color: red; text-align: center;">${passConfirmError}</div>
		      </c:if>
		      <input type="password" class="form-control" id="inputPassword2" placeholder="Confirm password" name="password2">
		    </div>
		  </div>
		  
		  <div class="form-group row justify-content-center">
		    <div class="col-sm-10">
		      <input type="submit" class="form-control signup-button" id="signupButton" value="Sign Up">
<!-- 		      <input type="submit" class="form-control signup-button" id="signupButton" value="Sign Up" style="margin-top: 10px; border-color: #ffffff; background-color: #AA46F2; color: #ffffff;"> -->
		      <span class="loginSpan"><a href="loginPage" style="color: #51b2f7; text-decoration: underline; font-size: 12pt;">Log In</a></span>
		    </div>
		  </div>
		</form:form>
        <!-- END OF SIGN UP FORM -->
        
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
    <!--END OF SIGN UP CONTAINER-->
    
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>