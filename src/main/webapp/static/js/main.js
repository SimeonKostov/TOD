/*Main page infinite scroll script*/

$(document).ready(function(){
	var content=document.getElementById("content");
	var start=1;
	var limit=5;
	var isChallenge=false;
	
	if(content.length === 0){
		$( "#load-more-button" ).hide();
		$( "#no-ans" ).show();
	}else{
		$( "#load-more-button" ).show();
		$( "#no-ans" ).hide();
	}
	
//	$( "#load-more-button" ).click(function(){
	var isWaiting=false;
	$(window).scroll(function() {
	   if(($(window).scrollTop() + $(window).height() >= $(document).height()-10) && !isWaiting) {
		   $(".load-wrapp").show();
		   isWaiting=true;
		$.ajax({
			url: 'feed=answers',
			type: 'GET',
			data: {'offset': start,
			},
			cache: false,
			success: function(newData){
					$(".load-wrapp").hide();
					start++;
					
					if(newData.length === 0){
//						$( "#load-more-button" ).hide();
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
						
						var senderUsername=(!el.question.anonymous) ? el.question.sender.username : "";
						
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
						    									+ '<li>' + el.date+ '</li>'
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
//		});
	});