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

<link rel="stylesheet" type="text/css" href="<c:url value="css/searchResults.css"/>"/>

<title></title>

</head>
<body>
	<input type="hidden" id="criteria" value="${criteria }">
<!-- ALL NOTIFICATIONS FOR USER -->
	<div class="outer-container">
		<div class="heading-container">
			<h4>Results</h4>
		</div>
		<div class="results-container">
<%-- 				<a class="user-link" href="profilePage${content.sender.username }"> --%>
<!-- 					<div class="user"> -->
<%-- 						<img class="avatar" src="<c:url value="img/user_np.png"/>" alt=""> --%>
<!-- 				   		<span class="fullName">Simeon Kostov</span> -->
<!-- 						<span class="username">[moniak40]</span> -->
<!-- 			   		</div> -->
<!-- 		   	    </a> -->
		</div>
		<div id="no-more-ans">No results</div>
		<div style="width: 100%; text-align: center;"><a href="#" id="load-more-button">Load more</a></div>
			</br></br>
	</div>
<!-- JAVASCRIPT INCLUDES -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="<c:url value="js/searchResult.js"/>"></script>
</body>
</html>