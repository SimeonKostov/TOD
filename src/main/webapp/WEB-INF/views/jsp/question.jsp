<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>

<link rel="stylesheet" type="text/css" href="<c:url value="css/question.css"/>"/>

<title>TOD</title>
</head>
<body>
	<!-- HIDDEN FILEDS -->
	<input type="hidden" value="${question.sender.username }" id="question-sender">
	<input type="hidden" value="${question.receiver.username }" id="question-receiver">
	<input type="hidden" value="${question.id}" id="question-id">
	<input type="hidden" value="${question}" id="question">
	<input type="hidden" value="${notification.id}" id="notification-id">
	
	
	<c:if test="${!question.answered }">
		<!-- QUESTION -->
		<div class="outer-container">
			<c:if test="${!question.anonymous }">
				<a href="profilePage${question.sender.username }" class="sender-profile-link">
					<div class="sender-container">
						<ul class="sender-list">
								<c:if test="${question.sender.profilePic!=null }">
									<li><img src="external/${question.sender.profilePic}" class="sender-avatar" alt=""></li>
								</c:if>
								<c:if test="${question.sender.profilePic==null }">
									<li><img src="<c:url value="img/user_np.png"/>" class="sender-avatar" alt=""></li>
								</c:if>
								
								<li><c:out value="${question.sender.username}"/></li>
						</ul>
					</div>
				</a>
			</c:if>
			<c:if test="${question.anonymous }">
				<div class="sender-container">Anonymous</div>
			</c:if>
			<!-- QUESTION CONTENT -->
			<div class="question-content">
				<c:out value="${question.content}"/>
			</div>
			
			<!-- ANSWER FORM -->
			<div class="answer-form">
				<div class="answer">
					<input type="text" class="answer-field" data-limit="300" placeholder="Answer the question"/>
				</div>
				
				<ul class="answer-buttons-list">
					<li><button class="answer-btn">Answer</button></li>
					<li>or</li>
					<li><button class="no-answer-btn">Request a challenge</button></li>
				</ul>
			</div>
			
		</div>
	</c:if>
	
	<c:if test="${question.answered }">
		<!-- SUCCESS CONTAINER -->
		<div class="success-container">
			<img src="<c:url value="img/success-icon.png"/>" class="success-icon" alt="">
		</div>
	</c:if>
	
<!-- JACASCRIPT INCLUDES -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script src="js/question.js"></script>
</body>
</html>