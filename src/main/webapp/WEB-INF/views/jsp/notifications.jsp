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

<link rel="stylesheet" type="text/css" href="<c:url value="css/notifications.css"/>"/>

<title>TOD-Notifications</title>
</head>
<body>
	<!-- ALL NOTIFICATIONS FOR USER -->
	<div class="outer-container">
		<div class="heading-container">
			<h4>Your notifications</h4>
		</div>
		<div class="notifications-container">
<!-- 		<div class="heading-container"> -->
<!-- 			<h4>Your notifications</h4> -->
<!-- 		</div> -->
		
<%-- 		<c:forEach items="${ cont }" var="content"> --%>
<%-- 			<c:if test="${content.type == 'QUESTION'"> --%>
<%-- 				<a class="all-notification-link" href="question?id=${content.notifier.id }"> --%>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${content.type == 'CHALLENGE'"> --%>
<%-- 				<a class="all-notification-link" href="videoResponse?id=${content.id }&sender=${content.sender.id }&receiver=${content.receiver.id }"> --%>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${content.type == 'ANSWER'}"> --%>
<%-- 				<a class="all-notification-link" href="answer?id=${content.notifier.id }"> --%>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${content.type == 'NO_ANSWER'}"> --%>
<%-- 				<a class="all-notification-link" href="challenge?id=${content.id }"> --%>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${content.type == 'CHALLENGE_RESPONSE'}"> --%>
<%-- 				<a class="all-notification-link" href="answeredChallenge?id=${content.id }"> --%>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${content.type == 'FOLLOW'}"> --%>
<%-- 				<a class="all-notification-link" href="profilePage${content.sender.username }"> --%>
<%-- 			</c:if> --%>
<!-- 					<div class="all-notification"> -->
<%-- 						<c:if test="${content.type == 'QUESTION' }"> --%>
<%-- 							<img class="all-noti-icon" src="<c:url value="img/question-noti.png"/>" alt=""> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${content.type == 'ANSWER' }"> --%>
<%-- 							<img class="all-noti-icon" src="<c:url value="img/answer-noti.png"/>" alt=""> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${content.type == 'NO_ANSWER' }"> --%>
<%-- 							<img class="all-noti-icon" src="<c:url value="img/no-answer-noti.png"/>" alt=""> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${content.type == 'CHALLENGE' }"> --%>
<%-- 							<img class="all-noti-icon" src="<c:url value="img/challenge-noti.png"/>" alt=""> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${content.type == 'CHALLENGE_RESPONSE' }"> --%>
<%-- 							<img class="all-noti-icon" src="<c:url value="img/video-noti.png"/>" alt=""> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${content.type == 'FOLLOW' }"> --%>
<%-- 							<img class="all-noti-icon" src="<c:url value="img/follow-noti.png"/>" alt=""> --%>
<%-- 						</c:if> --%>
<%-- 				   		<span class="all-noti-username"><c:out value="${content.sender.username }"/></span> --%>
<%-- 				   		<c:out value="${content.description }"/> --%>
<%-- 						<span class="all-noti-content"><c:out value="${content.notifier.content }"/></span> --%>
<%-- 						<span class="all-noti-date-time"><c:out value="${content.uploadDate }"/></span> --%>
<!-- 			   		</div> -->
<!-- 		   	    </a> -->
<%-- 		</c:forEach> --%>
		</div>
		<div id="no-more-ans">No more answers</div>
		<div style="width: 100%; text-align: center;"><a href="#" id="load-more-button">Load more</a></div>
			</br></br>
	</div>
	
<!-- JAVASCRIPT INCLUDES -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="<c:url value="js/notifications.js"/>"></script>
</body>
</html>