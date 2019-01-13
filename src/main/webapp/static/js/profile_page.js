/**
 * PROFILE PAGE JAVASCRIPT 
 */

//AJAX QUESTION ASKING
//$(document).ready(function(){
//	
//});


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
			
			$.ajax({
				url: 'profileContent=answers',
				type: 'GET',
				data: {'offset': start,
					   'username': username
				},
				cache: false,
				success: function(newData){
					 $(".load-wrapp").hide();
						start++;
						
						if(newData.length === 0){
//							$( "#load-more-button" ).hide();
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
							
							var senderUsername=(!el.question.anonymous) ? document.getElementById("sender-username-hidden").value : "";
		
							$(".answers-container").append('<div class="answer-container">' 
															+ '<div class="ans-avatar-container">'
																+ '<img src="' + profilePic + '" class="ans-avatar" alt="">'
																+ '<a href="profilePage' + el.user.username + '" class="ans-username-link">' + el.user.username + '</a>'
					    									+ '</div>'
					    									+ '<div class="q-a-list-container">'
						    									+ '<ul class="q-a-list">'
						    										+ '<li>'
						    											+ '<a class="ans-sender-username-link" href="profilePage'+ senderUsername + '">'+ senderUsername + '</a>'
						    										+ '</li>'
						    										+ '<br>'
							    									+ '<li>' + el.question.content + '</li>'
							    									+ '<hr color="#111111"/>'
							    									+ '<li>' + el.content + '</li>'
							    									+ '<li>' + el.date + '</li>'
						    									+ '</ul>'
						    								+ '</div>'
						    							+ '</div>'
						    							+ '<hr class="answer-spacer-line"/>');
							isWaiting=false;
						});
					}
				})
				return false;
	   		}
		});
});