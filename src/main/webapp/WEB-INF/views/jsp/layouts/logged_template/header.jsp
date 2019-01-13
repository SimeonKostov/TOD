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

<link rel="stylesheet" type="text/css" href="<c:url value="css/main_header.css"/>"/>

<title></title>
</head>
<body>
	<!-- HIDDEN INPUTS -->
	<input id="session_user" type="hidden" value="${sessionUser.username }">
	<input id="gif_icon" type="hidden" value="<c:url value="img/notified.png"/>">
	
	<!-- HEADER -->
	<nav class="navbar navbar-expand-lg navbar-light" >
	  <a class="navbar-brand" href="index">
	  	<img src="<c:url value="img/logo.png"/>" style="width: 90px; height: auto;" alt="">
	  </a>
	
	  <!-- TOGGLE BUTTON -->
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="true" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  
	  <!-- BOOTSTRAP DROPDOWN MENU -->
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	  
	  	<form class="form-inline my-2 my-lg-0" action="resultsPage">
	      <input id="search-bar" class="form-control mr-sm-2" type="search" placeholder="Find friends" name="criteria" aria-label="Search" required="required">
	      <button id="search-btn" class="btn btn-outline-success my-2 my-sm-0" type="submit" style="background-color: #ffffff; border-color: #ff8a84; color: #ff8a84;">Search</button>
	    </form>
	  
	    <c:if test="${sessionUser!=null }">
	    	<ul id="dropdown-menu" class="navbar-nav">
		      <li class="nav-item">
	      		<a class="nav-link" href="profilePage${sessionUser.username }" title="Profile">
	      			<img src="<c:url value="img/user_np.png"/>" style="width: 30px; height: 30px;" alt="">
	      			<span class="header-link-text">Profile</span>
	      		</a>
		      </li>
		      
		      <li class="nav-item">
		        <a id="notifications-link" class="nav-link" title="Notifications">
		        	<img id="notifier_icon" src="<c:url value="img/notifications.png"/>" style="width: 30px; height: 30px;" alt="">
		        	<span class="header-link-text">Notifications</span>
	       		</a>
		      </li>
		      
		      <li class="nav-item">
		        <a class="nav-link" href="logoutUser" title="Sign Out">
		        	<img src="<c:url value="img/logout.png"/>" style="width: 30px; height: 30px;" alt="">
		        	<span class="header-link-text">Sign Out</span>
	        	</a>
		      </li>
		    </ul>
	    </c:if>
	    
	  </div>
	  
	  <div class="autocomplete1">
<!-- 	  	<a href="profilePagemoniak40"> -->
<!-- 	  		<div class="result"> -->
<%-- 		    	<img class="avatar" src="<c:url value="img/user_np.png"/>" alt=""> --%>
<!-- 				<span class="fullName">Simeon Kostov</span> -->
<!-- 				<span class="username">[@moniak40]</span> -->
<!-- 			</div> -->
<!-- 	  	</a> -->
	  </div>
	  
	  <!-- NOTIFICATIONS MENU -->
	  <div id="notifications-container">
<!-- 			<div class="notification"> -->
<%-- 				<img class="noti-icon" src="<c:url value="img/question-noti.png"/>" alt=""> --%>
<!-- 				<span class="noti-username">Simeon Kostov</span> asked you: <span class="noti-content">daawpdapowpofjapojfaposjwa[fawfpoasfpjfaksjdawdakwfas;lkf;aslfjpoqwpfojas;fas;mfa;kfwfopqwpofjqpofas;klm]</span> -->
<!-- 			</div> -->
<!-- 	  	<button class="view-all-btn">View all</button> -->
	  </div>
	  
	</nav>
	<!-- END OF HEADER -->
	
<!-- JAVASCRIPT INCLUDES -->
<script src="<c:url value="js/bootstrap.min.js"/>"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> -->

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script src="<c:url value="js/header.js"/>"></script>
</body>
</html>