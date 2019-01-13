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

<link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>

<link rel="stylesheet" type="text/css" href="<c:url value="css/challenge-page.css"/>"/>

<title>TOD</title>
</head>
<body>
	<!-- HIDDEN FIELDS -->
	<input type="hidden" value="${notification.sender.username }" id="notification-sender">
	<input type="hidden" value="${notification.receiver.username }" id="notification-receiver">
	<input type="hidden" value="${notification.notifier.id }" id="request-id">
	<input type="hidden" value="${notification.id }" id="notification-id">
	
	
	<c:if test="${!notification.notifier.answered }">
		<!-- CHALLENGE SENDING -->
		<div class="outer-container">
			<a href="profilePage${notification.sender.username }" class="sender-profile-link">
				<div class="sender-container">
					<ul class="sender-list">
							<li>Send</li>
							<c:if test="${notification.sender.profilePic!=null }">
								<li><img src="external/${notification.sender.profilePic}" class="sender-avatar" alt=""></li>
							</c:if>
							<c:if test="${notification.sender.profilePic==null }">
								<li><img src="<c:url value="img/user_np.png"/>" class="sender-avatar" alt=""></li>
							</c:if>
							
							<li><c:out value="${notification.sender.username}"/></li>
							<li>a challenge</li>
					</ul>
				</div>
			</a>
			
			<!-- CHALLENGE FORM -->
			<div class="challenge-form">
				<div class="challenge">
					<input type="text" class="challenge-field" data-limit="300" placeholder="Will your friend dare"/>
				</div>
				<button class="challenge-btn">Send</button>
			</div>
			
		</div>
	</c:if>
	
	<c:if test="${notification.notifier.answered }">
		<!-- SUCCESS CONTAINER -->
		<div class="success-container">
			<img src="<c:url value="img/success-icon.png"/>" class="success-icon" alt="">
		</div>
	</c:if>
	
<!-- JACASCRIPT INCLUDES -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script src="js/challenge-page.js"></script>
</body>
</html>