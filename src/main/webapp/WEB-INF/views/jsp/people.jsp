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

<link rel="stylesheet" type="text/css" href="<c:url value="css/people.css"/>"/>

<c:if test="${type=='followers'}">
	<title>Followers</title>
</c:if>
<c:if test="${type=='followings'}">
	<title>Following</title>
</c:if>
</head>
<body>

	<!-- ALL NOTIFICATIONS FOR USER -->
	<div class="outer-container">
		<c:if test="${type=='followers'}">
			<div class="heading-container">
				<h4>Followers</h4>
			</div>
		</c:if>
		<c:if test="${type=='followings'}">
			<div class="heading-container">
				<h4>Following</h4>
			</div>
		</c:if>
		<div class="people-container">
			<c:forEach items="${ cont }" var="content">
				<c:if test="${type=='followers'}">
					<a class="profile-link" href="user.${content.follower.username}">
				</c:if>
				<c:if test="${type=='followings'}">
					<a class="profile-link" href="user.${content.following.username}">
				</c:if>
						<div class="user-container">
							<c:if test="${type=='followers' }">
								<c:if test="${content.follower.profilePic!=null }">
									<img src="external/${content.follower.profilePic}" class="avatar" alt="">
								</c:if>
								<c:if test="${content.follower.profilePic==null }">
									<img src="<c:url value="img/user_np.png"/>" class="avatar" alt="">
								</c:if>
							</c:if>
							<c:if test="${type=='followings' }">
								<c:if test="${content.following.profilePic!=null }">
									<img src="external/${content.following.profilePic}" class="avatar" alt="">
								</c:if>
								<c:if test="${content.following.profilePic==null }">
									<img src="<c:url value="img/user_np.png"/>" class="avatar" alt="">
								</c:if>
							</c:if>
							<c:if test="${type=='followers' }">
								<c:out value="${content.follower.username}"></c:out>
							</c:if>
							<c:if test="${type=='followings' }">
								<c:out value="${content.following.username}"></c:out>
							</c:if>
						</div>
					</a>
			</c:forEach>
		</div>
		<c:if test="${type=='followings' }">
			<div id="no-more-ans">No more followings</div>
		</c:if>
		<c:if test="${type=='followers' }">
			<div id="no-more-ans">No more followers</div>
		</c:if>
		<div style="width: 100%; text-align: center;"><a href="#" id="load-more-button">Load more</a></div>
		</br></br>
	</div>

<!-- JAVASCRIPT INCLUDES -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="<c:url value="js/people.js"/>"></script>
</body>
</html>