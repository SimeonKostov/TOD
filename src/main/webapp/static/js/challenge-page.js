/**
 * CHALLENGE PAGE JAVASCRIPT
 */

$(document).ready(function(){
	//CHALLENGE SENDING
	$(".challenge-btn").click(function(){
		var challengeSender=document.getElementById("notification-receiver").value;
		var challengeReceiver=document.getElementById("notification-sender").value;
		var requestId=document.getElementById("request-id").value;
		var challengeContent=document.getElementsByClassName("challenge-field")[0].value;
		var notificationId=document.getElementById("notification-id").value;
		
		$.ajax({
			url: 'sendChallenge',
			type: 'post',
			data:{
				'challengeSender': challengeSender,
				'challengeReceiver': challengeReceiver,
				'requestId': requestId,
				'challengeContent': challengeContent,
				'notificationId': notificationId
			},
			success:function(){
				$(".outer-container").remove();
				$(".success-container").toggle();
				
				setTimeout(function(){
					window.location.href = "index";
				}, 1000);
			}
		})
	});
});