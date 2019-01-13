<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>

<link rel="stylesheet" type="text/css" href="<c:url value="css/edit-profile.css"/>"/>

<title>TOD-Edit profile</title>
</head>
<body>

	<!-- PROFILE EDITING CONTAINER -->
	<div class="outer-container">
		<div class="heading-container">
			<h4>Edit profile</h4>
		</div>
		
		<!-- FIELDS' CONTAINER -->
		<div class="fields-container">
			<c:if test="${error != null }">
				<div style="width: 100%; color: red; text-align: center">
					<c:out value="${error }"></c:out>
				</div>
			</c:if>
			<form:form action="editProfile" method="post" modelAttribute="user" enctype="multipart/form-data" acceptCharset="UTF-8">
				<div class="reg-error" ><form:errors path="fullName" size="20"/></div>
				<div class="field-label" >Full name</div>
		      	<form:input type="text" class="form-field" id="inputFullName" placeholder="" name="fullName" path="fullName" maxlength="30" required=""/>
		      	
		      	<c:if test="${busyUsernameErr != null }">
			    	<div class="reg-error">${busyUsernameErr}</div>
			    </c:if>
			    <div class="reg-error"><form:errors path="username" size="20"/></div>
			    <div class="field-label" >Username</div>
			    <form:input type="text" class="form-field" id="inputUsername" placeholder="" name="username" path="username" maxlength="30" required=""/>
			    
			    <div class="field-label" >New password</div>
		        <input type="Password" class="form-field" id="inputPassword" placeholder="" name="password"/>
		        
		        <div class="field-label" >Confirm new passowrd</div>
		        <input type="Password" class="form-field" id="inputConfirmation" placeholder="" name="confirmation"/>
		        
			    <c:if test="${busyEmailErr != null }">
		      		<div class="reg-error">${busyEmailErr}</div>
		        </c:if>
		        <div class="reg-error"><form:errors path="email" size="20"/></div>
		        <div class="field-label" >Email</div>
		        <form:input type="email" class="form-field" id="inputEmail" placeholder="" name="email" path="email" required=""/>
		        
		        <div class="field-label" >About</div>
		        <form:input type="text" class="form-field" id="inputAbout" placeholder="" name="about" path="about" required=""/>
		        
		        <div class="field-label" >Profile picture</div>
		        <input type="file" class="form-field" id="inputProfilePicture" placeholder="" name="profilePic" accept="image/*"/>
		        
		        <div class="field-label" >Cover photo</div>
		        <input type="file" class="form-field" id="inputCoverPhoto" placeholder="" name="coverPhoto" accept="image/*"/>
		        
		        <input type="submit" class="update-btn" id="update-btn" value="Update">
			</form:form>
		</div>
	</div>
	</br></br>
	
<!-- JAVASCRIPT INCLUDES -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script src="js/edit-profile.js"></script>
</body>
</html>