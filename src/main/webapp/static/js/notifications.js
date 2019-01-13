/**
 * NOTIFICATIONS JS
 */

$(document).ready(function(){
	//AJAX LOAD MORE NOTIFICATIONS
	var start=0;
	var limit=7;
	
		$.ajax({
			url: 'getNotifications',
			type: 'GET',
			data: {'offset': start,
				   'limit': limit
			},
			cache: false,
			success: function(newData){
					start++;
					
					if(newData.length === 0){
						$( "#load-more-button" ).hide();
						$( "#no-more-ans" ).show();
					}
					
					$.each(newData, function(index, el) {
						var notiIcon;
						var senderUsername=(el.notifier.isAnonymous) ? "Someone" : el.sender.username;
						var description=el.description;
						var content=el.notifier.content;
						var notificationLink;
						
						if(el.type === "QUESTION"){
							notiIcon="img/question-noti.png";
							
							notificationLink="question?notifierId="+el.notifier.id+"&notificationId="+el.id;
						}
						if(el.type === "ANSWER"){
							notiIcon="img/answer-noti.png";
							notificationLink="answer?id="+el.notifier.id;
						}
						if(el.type === "NO_ANSWER"){
							notiIcon="img/no-answer-noti.png";
							notificationLink="challenge?id="+el.id;
						}
						if(el.type === "CHALLENGE"){
							notiIcon="img/challenge-noti.png";
							notificationLink="videoResponse?notifierId="+el.notifier.id+"&notificationId="+el.id+"&sender="+el.sender.id+"&receiver="+el.receiver.id;
						}
						if(el.type === "CHALLENGE_RESPONSE"){
							notiIcon="img/video-noti.png";
							notificationLink="challengeResponse?notifierId="+el.notifier.id+"&notificationId="+el.id;
						}
						if(el.type === "FOLLOW"){
							notiIcon="img/follow-noti.png";
							notificationLink="profilePage"+el.sender.username;
						}
						
						$(".notifications-container").append('<a class="all-notification-link" href="' + notificationLink + '">'
																	+ '<div class="all-notification">'
																   	+ '<img class="all-noti-icon" src="' + notiIcon + '" alt="">'
																   	+ '<span class="all-noti-username">' + senderUsername + '</span> '
																   	+ description 
																   	+ '<span class="all-noti-content">' + content + '</span>'
																   	+ '<span class="all-noti-date-time">' + el.date + '</span>'
															   	+ '</div>'
														   	+ '</a>');
					});
				}
			});
	
	$( "#load-more-button" ).click(function(){
		$.ajax({
			url: 'getNotifications',
			type: 'GET',
			data: {'offset': start,
				   'limit': limit
			},
			cache: false,
			success: function(newData){
					start++;
					
					if(newData.length === 0){
						$( "#load-more-button" ).hide();
						$( "#no-more-ans" ).show();
					}
					
					$.each(newData, function(index, el) {
						var notiIcon;
						var senderUsername=(el.notifier.isAnonymous) ? "Someone" : el.sender.username;
						var description=el.description;
						var content=el.notifier.content;
						var notificationLink;
						
						if(el.type === "QUESTION"){
							notiIcon="img/question-noti.png";
//							if(el.notifier.isAnswered){
//								notificationLink="answeredQuestion?id="+el.notifier.id;
//							}
//							else{
								notificationLink="question?id="+el.notifier.id;
//							}
						}
						if(el.type === "ANSWER"){
							notiIcon="img/answer-noti.png";
							notificationLink="answer?id="+el.notifier.id;
						}
						if(el.type === "NO_ANSWER"){
							notiIcon="img/no-answer-noti.png";
//							if(el.notifier.isAnswered){
//								notificationLink="index";
//							}
//							else{
								notificationLink="challenge?id="+el.id;
//							}
						}
						if(el.type === "CHALLENGE"){
							notiIcon="img/challenge-noti.png";
//							if(el.notifier.isAnswered){
//								notificationLink="answeredChallenge?id="+el.notifier.id;
//							}
//							else{
								notificationLink="videoResponse?id="+el.notifier.id+"&sender="+el.sender.id+"&receiver="+el.receiver.id;
//							}
						}
						if(el.type === "CHALLENGE_RESPONSE"){
							notiIcon="img/video-noti.png";
							notificationLink="challengeResponse?id="+el.notifier.id;
						}
						if(el.type === "FOLLOW"){
							notiIcon="img/follow-noti.png";
							notificationLink="profilePage"+el.sender.username;
						}
						$(".notifications-container").append('<a class="all-notification-link" href="' + notificationLink + '">'
																	+ '<div class="all-notification">'
																   	+ '<img class="all-noti-icon" src="' + notiIcon + '" alt="">'
																   	+ '<span class="all-noti-username">' + senderUsername + '</span> '
																   	+ description 
																   	+ '<span class="all-noti-content">' + content + '</span>'
																   	+ '<span class="all-noti-date-time">' + el.date + '</span>'
															   	+ '</div>'
														   	+ '</a>');
					});
				}
			})
		});
});