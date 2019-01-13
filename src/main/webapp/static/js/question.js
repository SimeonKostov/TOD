/**
 * QUESTION PAGE JAVASCRIPT
 */

$(document).ready(function(){
	
	//QUESTION ANSWERING
	$( ".answer-btn" ).click(function(){
		var answerSender=document.getElementById("question-receiver").value;
		var answerReceiver=document.getElementById("question-sender").value;
		var answerContent=document.getElementsByClassName("answer-field")[0].value;
		var questionId=document.getElementById("question-id").value;
		var notificationId=document.getElementById("notification-id").value;
		
		$.ajax({
			url: 'answerQuestion',
			type: 'post',
			data:{
				'answerSender': answerSender,
				'answerReceiver': answerReceiver,
				'answerContent': answerContent,
				'questionId': questionId,
				'notificationId': notificationId
			},
			cache: false,
			success:function(newData){
				$(".outer-container").remove();
				$(".success-container").show();
				
				setTimeout(function(){
					window.location.href = "index";
				}, 1000);
			}
		})
	});
	
	//CHALLENGE REQUEST
	$( ".no-answer-btn" ).click(function(){
		var requestSender=document.getElementById("question-receiver").value;
		var requestReceiver=document.getElementById("question-sender").value;
		var questionId=document.getElementById("question-id").value;
		var notificationId=document.getElementById("notification-id").value;
		
		$.ajax({
			url: 'challengeRequest',
			type: 'post',
			data:{
				'requestSender': requestSender,
				'requestReceiver': requestReceiver,
				'questionId': questionId,
				'notificationId': notificationId
			},
			cache: false,
			success:function(newData){
				$(".outer-container").remove();
				$(".success-container").toggle();
				
				setTimeout(function(){
					window.location.href = "index";
				}, 1000);
			}
		})
	});
});