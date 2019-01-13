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

<link rel="stylesheet" type="text/css" href="<c:url value="css/user-challenges.css"/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value="css/media-player.css"/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value="css/video-js.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="css/video-js.min.css"/>"/>

<title>TOD-Profile</title>
</head>
<body>

	<input type="hidden" id="sender-username-hidden" value="${sessionUser.username }">
	<input type="hidden" id="receiver-username-hidden" value="${requestUser.username }">
	<input type="hidden" id="session-user" value="${sessionUser }">
	<input type="hidden" id="followed-ids-hidden" value="${sessionUser.followed }">
	<input type="hidden" id="request-user-id-hidden" value="${requestUser.id }">
	<input type="hidden" id="username-hidden" name="username" value="${requestUser.username}">
	<input type="hidden" id="content" value="${cont}">
	
	<div class="general-container">
		<!-- OUTER CONTAINER -->
		<div class="outer-container">
			<!-- COVER PHOTO CONTAINER -->
			<c:if test="${requestUser.coverPhoto==null }">
				<div class="cover-photo" data-image-src="img/no_cover.png"></div>
			</c:if>
			<c:if test="${requestUser.coverPhoto!=null }">
				<div class="cover-photo" >
					<img src="external/${requestUser.coverPhoto}" class="cover-img" alt="">
				</div>
			</c:if>
			
			<div class="blured-part">
			</div>
			<div class="user-avatar">
				<c:if test="${requestUser.profilePic!=null }">
					<img src="external/${requestUser.profilePic}" class="cov-avatar" alt="">
				</c:if>
				<c:if test="${requestUser.profilePic==null }">
					<img src="<c:url value="img/user_np2.png"/>" class="cov-avatar" alt="">
				</c:if>
				
				<div class="names">
					<div style="width: 100%; height: auto; position: absolute; bottom: 10px;">
						<div class="full-name"><c:out value="${requestUser.fullName}"/></div>
						<div class="username"><a href="profilePage${requestUser.username}" class="avatar-username-link"><c:out value="@${requestUser.username}"/></a></div>
					</div>
				</div>
			</div>
		</div>
		
<%-- 		<c:if test="${sessionUser!=null }"> --%>
			<div class="fw-buttons">
				<c:if test="${sessionUser!=null }">
					<button class="follow-button">Follow</button>
					<button class="unfollow-button">Unfollow</button>
				</c:if>
				<a href="people?type=followers&username=${requestUser.username}"><button class="fw-button">Followers</button></a>
				<a href="people?type=following&username=${requestUser.username}"><button class="fw-button">Following</button></a>
				<c:if test="${sessionUser.username == requestUser.username }">
					<button class="fw-button">
		     				<img class="pr-edit-img" src="<c:url value="img/pr_edit.png"/>" alt="">
		      		</button>
				</c:if>
			</div>
			
		<c:if test="${sessionUser!=null }">
			<!-- QUESTIONS FORM -->
			<div class="form-container">
		<!-- 		<form class="questions-form" method="post" action="#"> -->
					<div class="question">
						<input class="question-field" type="text" data-limit="300" placeholder="Ask me something :)">
					</div>
					<div class="checkbox-container"><input class="checkbox" type="checkbox"> Anonymous <button class="ask-button">Ask</button></div>
		<!-- 		</form> -->
			</div>
		</c:if>
		
		<!-- ANSWERS/CHALLENGES CONTAINER -->
		<div class="ans-chall-container">
			
			<!-- ANSWERS/CHALLENGES BUTTONS -->
			<ul class="ans-chall-buttons">
				<c:if test="${sessionUser!=null }">
					<li>
						<form action="profilePage${requestUser.username }" method="get">
							<button type="submit">Answers</button>
						</form>
					</li>
					<li>
						<form action="userChallenges${requestUser.username }">
							<button type="submit">Challenges</button>
						</form>
					</li>
				</c:if>
				<c:if test="${sessionUser==null }">
					<li>
						<form action="user.${requestUser.username }" method="get">
							<button type="submit">Answers</button>
						</form>
					</li>
					<li>
						<form action="user.${requestUser.username }.challenges">
							<button type="submit">Challenges</button>
						</form>
					</li>
				</c:if>
			</ul> 
			
			<!-- RESPONSES -->
			<div class="responses-container">
			
				<c:forEach items="${ cont }" var="video">
					<div class="single-response">
						<div class="response-info">
							<c:if test="${!video.anonymous }">
								<a href="profilePage${video.challengeSender}" class="challenge-sender"><c:out value="${video.challengeSender}"/></a>
							</c:if>
							<c:if test="${video.anonymous }">
								<a href="#" class="challenge-sender"></a>
							</c:if>
							<div class="challenge-receiver">
								<c:if test="${video.user.profilePic!=null }">
									<img class="challenge-receiver-avatar" src="external/${video.user.profilePic }" alt="">
								</c:if>
								<c:if test="${video.user.profilePic==null }">
									<img class="challenge-receiver-avatar" src="<c:url value="img/user_np.png"/>" alt="">
								</c:if>
								<a href="profilePage${video.user.username}"><c:out value="${video.user.username}"/></a>
							</div>
							<div class="challenge-content"><c:out value="${video.challengeContent}"/></div>
						</div>
					    <video id="my-video" class="video-js" controls loop playsinline poster="external/${video.thumbnail }" data-setup="{}" >
						   <source src="external/${video.url }" type='video/mp4'>
						   <p class="vjs-no-js">
						     To view this video please enable JavaScript, and consider upgrading to a web browser that
						     <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
						   </p>
						</video>
						<div class="upload-date"><c:out value="${video.uploadDate}"/></div>
						</br>
						</br>
					</div>
				</c:forEach>
			</div>
			<div id="no-more-ans">No more videos</div>
			<div class="load-wrapp">
	            <div class="load-3">
	                <div class="line"></div>
	                <div class="line"></div>
	                <div class="line"></div>
	            </div>
            </div>
        </div>
<!-- 			<div style="width: 100%; text-align: center;"><a href="#" id="load-more-button">Load more</a></div> -->
			</br>
			</br>
		</div>
	</div>
<!-- JAVASCRIPT INCLUDES -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script src="<c:url value="js/user-challenges.js"/>"></script>

<!-- COVER PHOTO SETTER SCRIPT -->
<script type="text/javascript">
	
	//if(${requestUser.coverPhoto} != null){
		var list = document.getElementsByClassName('cover-photo');
		
		for (var i = 0; i < list.length; i++) {
		  var src = list[i].getAttribute('data-image-src');
		  list[i].style.backgroundImage="url('" + src + "')";
		}
	//}
</script>
</body>
</html>