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

<link rel="stylesheet" type="text/css" href="<c:url value="css/answered-question.css"/>"/>

<title>TOD</title>
</head>
<body>

<!-- QUESTION -->
	<div class="outer-container">
		<div class="answer-container">
					<div class="ans-avatar-container">
						<c:if test="${answer.user.profilePic!=null }">
							<img src="external/${answer.user.profilePic}" class="ans-avatar" alt="">
						</c:if>
						<c:if test="${answer.user.profilePic==null }">
							<img src="<c:url value="img/user_np.png"/>" class="ans-avatar" alt="">
						</c:if>
						<a href="profilePage${answer.user.username}" class="ans-username-link"><c:out value="${answer.user.username}"/></a>
					</div>
					
					<div class="q-a-list-container">
						<ul class="q-a-list">
							<c:if test="${!answer.question.anonymous }">
								<li>
									<a class="ans-sender-username-link" href="profilePage${answer.question.sender.username }"><c:out value="${answer.question.sender.username }"/></a>
								</li>
							</c:if>
							<br>
							<li><c:out value="${answer.question.content }"/></li>
							<hr color="#ff8a84"/>
							<li><c:out value="${answer.content }"/></li>
							<li><c:out value="${answer.dateTime }"/></li>
						</ul>
					</div>
				</div>
	</div>
<!-- JAVASCRIPT INCLUDES -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</body>
</html>