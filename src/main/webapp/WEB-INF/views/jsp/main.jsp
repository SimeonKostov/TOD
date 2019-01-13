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

<link rel="stylesheet" type="text/css" href="<c:url value="css/main.css"/>"/>

<title></title>

</head>
<body>
	<input type="hidden" value="${cont}" id="content">
	
	<!-- FOLLOWINGS CONTAINER -->
<!-- 	<div class="followings-container"> -->
<!-- 	</div> -->
		
	<!-- ANSWERS/CHALLENGES CONTAINER -->
	<div class="ans-chall-container">
		
		<!-- ANSWERS/CHALLENGES BUTTONS -->
		<ul class="ans-chall-buttons">
			<li>
				<form action="mainFeed" method="get">
					<button type="submit">Answers</button>
				</form>
			</li>
			<li>
				<form action="challenges">
					<button type="submit">Challenges</button>
				</form>
			</li>
			
		</ul> 

		<!-- SINGLE ANSWER CONTAINER -->
		<div class="answers-container">
			<input type="hidden" id="sender-username-hidden" value="${sessionUser.username }">
			
			<c:forEach items="${ cont }" var="content">
				<div class="answer-container">
					<div class="ans-avatar-container">
						<c:if test="${content.user.profilePic!=null }">
							<img src="external/${content.user.profilePic}" class="ans-avatar" alt="">
						</c:if>
						<c:if test="${content.user.profilePic==null }">
							<img src="<c:url value="img/user_np.png"/>" class="ans-avatar" alt="">
						</c:if>
						<a href="profilePage${content.user.username}" class="ans-username-link"><c:out value="${content.user.username}"/></a>
					</div>
					
					<div class="q-a-list-container">
						<ul class="q-a-list">
							<c:if test="${!content.question.anonymous }">
								<li>
									<a class="ans-sender-username-link" href="user/${content.question.sender.username }"><c:out value="${content.question.sender.username }"/></a>
								</li>
							</c:if>
							<br>
							<li><c:out value="${content.question.content }"/></li>
							<hr color="#111111" />
							<li><c:out value="${content.content }"/></li>
							<li><c:out value="${content.dateTime }"/></li>
						</ul>
					</div>
				</div>
				<hr class="answer-spacer-line"/>
			</c:forEach>
		</div>
		<div id="no-more-ans">No more answers</div>
		<div id="no-ans">No answers yet</div>
		<div class="load-wrapp">
            <div class="load-3">
                <div class="line"></div>
                <div class="line"></div>
                <div class="line"></div>
            </div>
        </div>
<!-- 		<div style="width: 100%; text-align: center;"><a href="#" id="load-more-button">Load more</a></div> -->
		</br></br>
	</div>
	
<!-- JAVASCRIPT INCLUDES -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="<c:url value="js/main.js"/>"></script>
</body>
</html>
