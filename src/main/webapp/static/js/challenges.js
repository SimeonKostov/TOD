/**
 * Challenges page JS
 */

$(document).ready(function(){
	var start=1;
	var limit=5;
	var content=document.getElementById("content");
	
	if(content.length != 0){
		$( "#load-more-button" ).show();
		$( "#no-ans" ).hide();
	}else{
		$( "#load-more-button" ).hide();
		$( "#no-ans" ).show();
	}
	
//	$( "#load-more-button" ).click(function(){
	var isWaiting=false;
	$(window).scroll(function() {
	   if(($(window).scrollTop() + $(window).height() >= $(document).height()-10) && !isWaiting) {
		   $(".load-wrapp").show();
		   isWaiting=true;
			$.ajax({
				url: 'loadChallenges',
				type: 'GET',
				async: true,
				data: {'offset': start,
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
							
							var senderUsername=(!el.anonymous) ? el.challengeSender : "";
							var thumbnail="external/" + el.thumbnail;
							
							$(".responses-container").append('<div class="single-response">' 
															+ '<div class="response-info">'
																+ '<a href="profilePage' + senderUsername + '" class="challenge-sender">' + el.challengeSender + '</a>'
																+ '<div class="challenge-receiver">'
																	+ '<img class="challenge-receiver-avatar" src="' + profilePic + '" alt="">'
															        + '<a href="profilePage' + el.user.username + '">' + el.user.username + '</a>'
																+ '</div>'
																+ '<div class="challenge-content">' + el.challengeContent + '</div>'
															+ '</div>'
															+ '<video id="my-video" class="video-js" controls loop playsinline poster="' + thumbnail + '" data-setup="{}" >'
																+ '<source src="external/' + el.url + '" type="video/mp4">'
							    								+ '<p class="vjs-no-js">'
							    									+ 'To view this video please enable JavaScript, and consider upgrading to a web browser that'
							    									+ '<a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>'
							    								+ '</p>'
															+ '</video>'
															+ '<div class="upload-date">' + el.uploadDate + '</div>'
															+ '</br>'
														+ '</div>');
							isWaiting=false;
	
						});
					}
				})
				return false;
	   		}
		});
	});