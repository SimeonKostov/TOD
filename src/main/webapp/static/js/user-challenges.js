/**
 * USER'S CHALLENGES JAVASCRIPT
 */

$(document).ready(function(){
	//CHECK IF USER IS FOLLOWED
		var obj=document.getElementById("followed-ids-hidden").value;
		var id=document.getElementById("request-user-id-hidden").value;

		
		var sessionUser=document.getElementById("sender-username-hidden").value;
		var requestUser=document.getElementById("receiver-username-hidden").value;
		var content=document.getElementById("content");
		
		if(content.length != 0){
			$( "#load-more-button" ).show();
			$( "#no-ans" ).hide();
		}else{
			$( "#load-more-button" ).hide();
			$( "#no-ans" ).show();
		}
		
		if(sessionUser === requestUser){
			$( ".follow-button" ).hide();
			$( ".unfollow-button" ).hide();
		}
		else{
			if (obj.indexOf(id) > -1) {
				$( ".follow-button" ).hide();
				$( ".unfollow-button" ).show();
			}
			else{
				$( ".follow-button" ).show();
				$( ".unfollow-button" ).hide();
				
			}
		}
	
	//AJAX USER FOLLOW
	$( ".follow-button" ).click(function(){
		var follower=document.getElementById("sender-username-hidden").value;
		var followed=document.getElementById("receiver-username-hidden").value;
		
		$.ajax({
			url: 'followUser',
			type: 'post',
			data:{
				'follower': follower,
				'followed': followed
			},
			success: function(newData){
				$( ".follow-button" ).hide();
				$( ".unfollow-button" ).show();
			}
		});
	})
	
	//AJAX USER UNFOLLOW
	$( ".unfollow-button" ).click(function(){
		var follower=document.getElementById("sender-username-hidden").value;
		var followed=document.getElementById("receiver-username-hidden").value;
		
		$.ajax({
			url: 'unfollowUser',
			type: 'post',
			data:{
				'follower': follower,
				'followed': followed
			},
			success: function(newData){
				$( ".follow-button" ).show();
				$( ".unfollow-button" ).hide();
			}
		});
	})
	
	//EDIT-PROFILE PAGE
	var editProfileBtn=document.getElementsByClassName("fw-button")[2];
	
	$( editProfileBtn ).click(function(){
		window.location.href = "profileEditPage";
	});
	
	//AJAX QUESTION ASKING
	$( ".ask-button" ).click(function(){
		var isAnonymous=(document.getElementsByClassName("checkbox")[0].checked) ? "true" : "false";
		var question=document.getElementsByClassName("question-field")[0].value;
		var sender=document.getElementById("sender-username-hidden").value;
		var receiver=document.getElementById("receiver-username-hidden").value;
		if(!question){
			alert("Empty question field!");
		}
		else{
			$.ajax({
				url: 'sendQuestion',
				type: 'post',
				data:{
					'isAnonymous': isAnonymous,
					'question': question,
					'sender': sender,
					'receiver': receiver
				},
				success: function(newData){
						document.getElementsByClassName("question-field")[0].value="";
						document.getElementsByClassName("checkbox")[0].checked=0;
				}
			});
		}
	});

	//AJAX LOAD MORE CONTENT APPENDING
	var start=1;
	var limit=5;
	
//	$( "#load-more-button" ).click(function(){
		var isWaiting=false;
		$(window).scroll(function() {
		   if(($(window).scrollTop() + $(window).height() >= $(document).height()-10) && !isWaiting) {
			   $(".load-wrapp").show();
			   isWaiting=true;
			   var username=document.getElementById("receiver-username-hidden").value;
//				alert($(document).scrollTop());
				$.ajax({
					url: 'profileContent=challenges',
					type: 'GET',
					data: {'offset': start,
						   'username': username
					},
					cache: false,
					success: function(newData){
						 $(".load-wrapp").hide();
							start+=limit;
							
							if(newData.length === 0){
//								$( "#load-more-button" ).hide();
								$( "#no-more-ans" ).show();
							}
							
							$.each(newData, function(index, el) {
								var profilePic;
								if(el.user.profilePic==null){
									profilePic='img/user_np.png';
								}
								else{
									profilePic="external/" + el.user.profilePic;
								}
								
								var senderUsername=(!el.anonymous) ? el.challengeSender : "";
								var thumbnail="external/" + el.thumbnail;
								
								$(".responses-container").append('<div class="single-response">' 
																+ '<div class="response-info">'
																	+ '<a href="profilePage' + senderUsername + '" class="challenge-sender">' + senderUsername + '</a>'
																	+ '<div class="challenge-receiver">'
																		+ '<img class="challenge-receiver-avatar" src="' + profilePic + '" alt="">'
																		+ '<a href="profilePage' + el.user.username + '">' + el.user.username + '</a>'
							    									+ '</div>'
							    									+ '<div class="challenge-content"><c:out value="' + el.challengeContent + '"/></div>'
							    								+ '</div>'
							    								+ '<video id="my-video" class="video-js" controls loop playsinline poster="' + thumbnail + '" data-setup="{}" >'
							    								+ '<source src="external/' + el.url + '" type="video/mp4">'
								    								+ '<p class="vjs-no-js">'
								    									+ 'To view this video please enable JavaScript, and consider upgrading to a web browser that'
								    									+ '<a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>'
								    									+ '<li>' + el.date + '</li>'
							    									+ '</p>'
							    								+ '</video>'
							    								+ '<div class="upload-date">' + el.uploadDate + '</div>'
							    								+ '</br>'
							    								+ '</br>');
								isWaiting=false;
							});
						}
					})
					return false;
		   }
		});
//		});
});