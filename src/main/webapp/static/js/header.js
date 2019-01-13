/**
 * HEADER JAVASCRIPT
 */

$(document).ready(function(){
	
	//CHECKING FOR NOTIFICATIONS
	var session='<%=Session["sessionUser"] != null%>';
	var newIcon=document.getElementById("gif_icon").value;
	var sessionUser=document.getElementById("session_user").value;
	
	if(session || !(sessionUser === null)){
		setInterval(function(){
			$.ajax({
				url: 'checkNotifications',
				type: 'post',
				data:{
					'username': sessionUser
				},
				success: function(newData){
					if(newData){
						$("#notifier_icon").attr('src', 'img/notified.png');
						$(".navbar-toggler").css('border-color', 'red');
					}
				}
			});
		}, 1000);
	}
	
	//DISPLAYING NOTIFICATIONS DIV
	var dropdownMenu=document.getElementsByClassName("navbar-nav")[0];
	var landscapePhoneWidth=window.matchMedia( "(max-width: 991px)" );
	var landscapePhoneHeight=window.matchMedia( "(max-height: 700px)" );
	
	$(".navbar-toggler").click(function(){
		if($("#notifications-container").is(":visible")){
			$("#notifications-container").hide();
			$(".form-inline").first().show();
			$(".nav-item").first().show();
			$(".nav-item").last().show();
			$(".notification").hide();
			$(".horizontal-line").hide();
			$(".view-all-btn").hide();
		}
		if($(".autocomplete1").is(":visible")){
			$(".autocomplete1").hide();
			$(".result").hide();
			$(".nav-item").show();
			$("#search-bar").val("");
		}
	});
	
	$( "#notifications-link" ).click(function(){
		if($("#notifications-container").is(":visible")){
			$("#notifications-container").hide();
			$(".form-inline").first().show();
			$(".nav-item").first().show();
			$(".nav-item").last().show();
			$(".notification").hide();
			$(".horizontal-line").hide();
			$(".view-all-btn").hide();
			
		}
		else{
			$("#notifications-container").show();
			if(landscapePhoneWidth.matches && landscapePhoneHeight.matches){
				$(".form-inline").first().hide();
				$(".nav-item").first().hide();
				$(".nav-item").last().hide();
				$("#notifier_icon").attr('src', 'img/notifications.png');
				$(".navbar-toggler").css('border-color', '#f2f2f2');
			}
		
			$.ajax({
				url: 'getNotifications',
				type: 'get',
				data:{
					'username': sessionUser,
					'offset': 0,
					'limit': 5
				},
				success: function(newData){
					if(newData.length > 0){
						$.each(newData, function(index, el) {
							var notiIcon;
							var senderUsername=(el.notifier.anonymous) ? "Someone" : el.sender.username;
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
								
								senderUsername=el.notifier.sender.username;
								notificationLink="challenge?id="+el.id;
							
							}
							if(el.type === "CHALLENGE"){
								notiIcon="img/challenge-noti.png";
								notificationLink="videoResponse?notifierId="+el.notifier.id+"&notificationId="+el.id+"&sender="+el.sender.id+"&receiver="+el.receiver.id;
							}
							if(el.type === "CHALLENGE_RESPONSE"){
								notiIcon="img/video-noti.png";
								notificationLink="challengeResponse?notifierId="+el.notifier.id;
								senderUsername=el.sender.username;
							}
							if(el.type === "FOLLOW"){
								notiIcon="img/follow-noti.png";
								notificationLink="profilePage"+el.sender.username;
							}
					
							$("#notifications-container").append('<a class="notification-link" href="' + notificationLink + '">'
																	+ '<div class="notification">'
																	   	+ '<img class="noti-icon" src="' + notiIcon + '" alt="">'
																	   	+ '<span class="noti-username">' + senderUsername + '</span> '
																	   	+ description 
																	   	+ '<span class="noti-content">' + content + '</span>'
																   	+ '</div>'
															   	+ '</a>')
						    
						});
						
						$("#notifications-container").append('<a href="notificationsPage"><button class="view-all-btn">View all</button></a>');
						$("#notifier_icon").attr('src', 'img/notifications.png');
						$(".navbar-toggler").css('border-color', '#f2f2f2');
					}
					else{
						$("#notifications-container").append('<div class="notification">No notifications yet</div>');
						$(".notification").css('text-align', 'center');
					}
				}
			});
		}
	});
	
	//AUTOCOMPLETE SEARCHING
	$("#search-bar").keyup(function(){
		var page=0;
		var limit=5;
		var criteria=$(this).val();
		
		$(".result").hide();
		$(".view-all-btn").hide();
		$(".notification").hide();
		if(criteria === "" || !criteria){
			$(".autocomplete1").hide();
			$(".nav-item").show();
		}else{
			$(".autocomplete1").show();
			if(landscapePhoneWidth.matches && landscapePhoneHeight.matches){
				$(".nav-item").hide();
			}
			
			$.ajax({
				url: 'autocomplete',
				type: 'get',
				data:{
					'page': page,
					'limit': limit,
					'criteria': criteria
				},
				success: function(newData){
					if(newData.length > 0){
						$.each(newData, function(index, el) {
							var profilePic;
							if(el.profilePic == null){
								profilePic="img/user_np.png";
							}else{
								profilePic="external/" + el.profilePic; 
							}
							var fullName=el.fullName;
							var username=el.username;
							var profileLink="profilePage" + username;
							$(".autocomplete1").append('<a href="' + profileLink + '">'
																	+ '<div class="result">'
																	   	+ '<img class="avatar" src="' + profilePic + '" alt="">'
																	   	+ '<span class="fullName">' + fullName + '</span> ' 
																	   	+ '<span class="username">[@' + username + ']</span>'
																   	+ '</div>'
															   	+ '</a>')
						    
						});
						
						$(".autocomplete1").append('<a href="searchResults?a=' + criteria + '"><button class="view-all-btn">View more</button></a>');
					}
					else{
						$(".autocomplete1").append('<div class="notification">No results</div>');
						$(".notification").css('text-align', 'center');
					}
				}
			})
		}
	});
	
	$("#search-btn").click(function(){
//		$("#search-bar").val("");
//		$.get("", function(data){
//
//		});
	});
});