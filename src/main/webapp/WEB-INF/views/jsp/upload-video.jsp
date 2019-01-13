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

<link rel="stylesheet" type="text/css" href="<c:url value="css/upload-video.css"/>"/>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
<title>TOD</title>
</head>
<body>
	<!-- HIDDEN FIELDS -->
	<input type="hidden" id="challenge-id" value="${challenge.id }">
	<input type="hidden" id="challenge-sender-id" value="${challenge.sender.id }">
	<input type="hidden" id="uploader-id" value="${challenge.receiver.id }">
	<input type="hidden" id="notification-id" value="${notification.id }">
	
	<c:if test="${!challenge.answered }">
		<div class="upload-container">
			<div class="upload">
			 <div class="upload-files">
			  <header>
			   <p>
			    <i class="fas fa-cloud-upload-alt"></i>
			    <span class="up">up</span>
			    <span class="load">load</span>
			   </p>
			  </header>
			  <div class="body" id="drop">
			   <i class="fas fa-file-alt"></i>
			  </div>
			  <form id="upload_form" enctype="multipart/form-data" method="post">
				  <label class="custom-file-upload">
				  <input type="file" name="file1" id="file1" accept="video/*">
				  Choose file
				  </label>
				  <div id="file-name"></div>
				  </br>
				  <progress id="progressBar" value="0" max="100"></progress>
				  <input type="button" class="upload_button" value="Upload File" onclick="uploadFile()">
				  <div class="status-div">
				  	 <h3 id="status"></h3>
				  	 <p id="loaded_n_total"></p>
				  </div>
				</form>
				<div class="advice_div">*For best performance upload videos in MP4, WebM or Ogg format</div>
			  <footer>
			   <div class="divider">
			    <span><AR>FILES</AR></span>
			   </div>
			   <div class="list-files">
			    <!--   template   -->
			   </div>
						<button class="importar">UPDATE FILES</button>
			  </footer>
			 </div>
			</div>
		</div>
		</br></br>
	</c:if>
	<c:if test="${challenge.answered }">
		<!-- SUCCESS CONTAINER -->
		<div class="success-container">
			<img src="<c:url value="img/success-icon.png"/>" class="success-icon" alt="">
		</div>
	</c:if>
<!-- JACASCRIPT INCLUDES -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/jquery-form/form@4.2.2/dist/jquery.form.min.js" integrity="sha384-FzT3vTVGXqf7wRfy8k4BiyzvbNfeYjK+frTVqZeNDFl8woCbF0CYG6g2fMEFFo/i" crossorigin="anonymous"></script>
<script type="text/javascript" src="jquery.form.js"></script>
  
<script src="js/upload-video.js"></script>
</body>
</html>
