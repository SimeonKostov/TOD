/**
 * SEARCH RESULTS JS
 */

$(document).ready(function(){
	//AJAX LOAD MORE NOTIFICATIONS
	var page=0;
	var limit=7;
	var criteria= $("#criteria").val();
	
		$.ajax({
			url: 'autocomplete',
			type: 'GET',
			data: {'page': page,
				   'limit': limit,
				   'criteria': criteria
			},
			cache: false,
			success: function(newData){
					page++;
					
					if(newData.length === 0){
						$( "#load-more-button" ).hide();
						$( "#no-more-ans" ).show();
					}
					
					$.each(newData, function(index, el) {
						var avatar;
						if(el.profilePic == null){
							avatar="img/user_np.png"
						}else{
							avatar="external/" + el.profilePic
						}
						var fullName=el.fullName
						var username=el.username;
						
						$(".results-container").append('<a class="all-notification-link" href="profilePage' + username + '">'
																+ '<div class="user">'
																   	+ '<img class="avatar" src="' + avatar + '" alt="">'
																   	+ '<span class="fullName">' + fullName + '</span> '
																   	+ '<span class="username">[@' + username + ']</span>'
															   	+ '</div>'
														   	+ '</a>');
					});
				}
			});
	
	$( "#load-more-button" ).click(function(){
		$.ajax({
			url: 'autocomplete',
			type: 'GET',
			data: {
				'page': page,
				'limit': limit,
				'criteria': criteria
			},
			cache: false,
			success: function(newData){
					page++;
					
					if(newData.length === 0){
						$( "#load-more-button" ).hide();
						$( "#no-more-ans" ).show();
					}
					
					$.each(newData, function(index, el) {
						var avatar;
						if(el.profilePic == null){
							avatar="img/user_np.png"
						}else{
							avatar="external/" + el.profilePic
						}
						var fullName=el.fullName
						var username=el.username;
						
						$(".results-container").append('<a class="all-notification-link" href="profilePage' + username + '">'
																+ '<div class="user">'
																   	+ '<img class="avatar" src="' + avatar + '" alt="">'
																   	+ '<span class="fullName">' + fullName + '</span> '
																   	+ '<span class="username">' + username + '</span>'
															   	+ '</div>'
														   	+ '</a>');
					});
				}
			})
		});
});