package com.tod.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.hash.Hashing;
import com.tod.WebInitializer;
import com.tod.model.Answer;
import com.tod.model.Challenge;
import com.tod.model.ChallengeVideo;
import com.tod.model.Follow;
import com.tod.model.IResponse;
import com.tod.model.Notification;
import com.tod.model.NotificationTypes;
import com.tod.model.NotificationsDescriptions;
import com.tod.model.Question;
import com.tod.model.User;
import com.tod.repositories.AnswerRepository;
import com.tod.repositories.ChallengeVideoRepository;
import com.tod.repositories.FollowRepository;
import com.tod.repositories.NotificationRepository;
import com.tod.repositories.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private ChallengeVideoRepository challengeVideoRepository;
	@Autowired
	private FollowRepository followRepository;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private EmailServiceImpl emailService;
	
	//USER REGISTRATION METHOD
	@RequestMapping(value="registerUser", method=RequestMethod.POST)
	public String registerUser(HttpSession session, HttpServletRequest req, @Valid @ModelAttribute("user") User user, BindingResult result, Errors errors, Model model) {
		if(session.getAttribute("sessionUser") != null) {
			return "redirect:/mainFeed";
		}
		
		if(user.getUsername()==null) {
			return "signup";
		}
		if(result.hasErrors()) {
			if(req.getParameter("password2").isEmpty() || req.getParameter("password2")==null) {
				model.addAttribute("passConfirmError", "*Please confirm your password!");
			}
			return "signup";
		}
		
		if(!user.getPassword().equals(req.getParameter("password2")) || req.getParameter("password2").isEmpty() || req.getParameter("password2")==null) {
			model.addAttribute("passConfirmError", "*Please confirm your password!");
			return "signup";
		}
		
		
		if((userRepository.findByUsername(user.getUsername())==null) && (userRepository.findByEmail(user.getEmail())==null)){
			
			user.setPassword(Hashing.sha512().hashString(user.getPassword(), StandardCharsets.UTF_8).toString());
			userRepository.insert(user);
			session.setAttribute("sessionUser", user);
			session.setAttribute("isLogged", true);
			return "redirect:/main";
		}
		else {
			if(userRepository.findByUsername(user.getUsername())!=null) {
				model.addAttribute("busyUsernameErr", "*Username already exists!");
				return "signup";
			}
			else {
				model.addAttribute("busyEmailErr", "*Email already exists!");
				return "signup";
			}
		}
	}
	
	//USER LOGIN METHOD
	@RequestMapping(value="login")
	public String loginUser(HttpServletRequest req, HttpSession session, Model model) {
		
		if(session.getAttribute("sessionUser") != null) {
			return "redirect:/mainFeed";
		}
		
		if(req.getParameter("identifier")==null || req.getParameter("password")==null) {
			model.addAttribute("emptyError", "*Enter your username/email and password!");
			return "login";
		}
		
		User u=null;
		
		if(userRepository.findByUsernameAndPassword(req.getParameter("identifier"), Hashing.sha512().hashString(req.getParameter("password"), StandardCharsets.UTF_8).toString()) != null) {	
			u=userRepository.findByUsername(req.getParameter("identifier"));
			session.setAttribute("sessionUser", u);
			session.setAttribute("isLogged", true);
			
			return "redirect:/mainFeed";
		}
		if(userRepository.findByEmailAndPassword(req.getParameter("identifier"), Hashing.sha512().hashString(req.getParameter("password"), StandardCharsets.UTF_8).toString()) != null) {
			u=userRepository.findByUsername(req.getParameter("identifier"));
			session.setAttribute("sessionUser", u);
			session.setAttribute("isLogged", true);
			
			return "redirect:/main";
		}
		else {
			model.addAttribute("loginError", "*Wrong username/email or password!");
			return "login";
		}
	}
	
	//PASSWORD RESETTING
	@RequestMapping(value="sendEmail", method=RequestMethod.POST)
	public String passReset(Model model, HttpServletRequest req) {
		String email=req.getParameter("email").toString();
		User u=userRepository.findByEmail(email);
		
		if(u ==null) {
			model.addAttribute("emailError", "User with email does not exist!");
		}else {
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
			String newPassword = RandomStringUtils.random( 12, characters );
		    u.setPassword(Hashing.sha512().hashString(newPassword, StandardCharsets.UTF_8).toString());
			mongoTemplate.updateMulti(new Query(Criteria.where("sender.username").is(u.getUsername())), Update.update("sender", u), Question.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("receiver.username").is(u.getUsername())), Update.update("receiver", u), Question.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("user.username").is(u.getUsername())), Update.update("user", u), Answer.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("question.sender.username").is(u.getUsername())), Update.update("question.sender", u), Answer.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("question.receiver.username").is(u.getUsername())), Update.update("question.receiver", u), Answer.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("sender.username").is(u.getUsername())), Update.update("sender", u), Challenge.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("receiver.username").is(u.getUsername())), Update.update("receiver", u), Challenge.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("user.username").is(u.getUsername())), Update.update("user", u), ChallengeVideo.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("challenge.sender.username").is(u.getUsername())), Update.update("challenge.sender", u), ChallengeVideo.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("challenge.receiver.username").is(u.getUsername())), Update.update("challenge.receiver", u), ChallengeVideo.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("follower.username").is(u.getUsername())), Update.update("follower", u), Follow.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("following.username").is(u.getUsername())), Update.update("following", u), Follow.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("sender.username").is(u.getUsername())), Update.update("sender", u), Notification.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("receiver.username").is(u.getUsername())), Update.update("receiver", u), Notification.class);
			userRepository.save(u);
			emailService.sendSimpleMessage(u.getEmail(), "Password recovery", "Hey " + u.getFullName() + ", \n\nYou recently let us know that you have forgotten your password. Please use the temporary random generated password below to Sign in and then change it with one you prefer. \n\n   Temporary password:\n   " + newPassword + " \n\nBest regards, \nTeam ToD :)");
			req.removeAttribute("email");
			model.addAttribute("success", "Check your inbox!");
		}
		return "passForgot";
	}
	
	//PROFILE PAGE LOADING METHOD
	@RequestMapping(value="user.{user}", method=RequestMethod.GET)
	public String profilePage(HttpSession session, HttpServletRequest req, Model model, @PathVariable String user) {
		
		System.out.println(user);
		User u = userRepository.findByUsername(user);
		model.addAttribute("requestUser", u);
		
		LinkedHashSet<IResponse> iterableAnswers=new LinkedHashSet<>();
		System.out.println(u.getUsername());		
		Page<Answer> answers = answerRepository.findByUsername(u.getUsername(), new PageRequest(0, 5, new Sort(Direction.DESC,"uploadDate")));
		for (Answer answer : answers) {
			iterableAnswers.add(answer);
		}
		model.addAttribute("cont", iterableAnswers);
			
		return "profile";
	}
	
	//PROFILE PAGE LOADING METHOD
	@RequestMapping(value="profilePage{user}", method=RequestMethod.GET)
	public String myPage(HttpSession session, HttpServletRequest req, Model model, @PathVariable String user) {
		if(session.getAttribute("sessionUser") == null) {
			return "index";
		}
		
		System.out.println(user);
		User u = userRepository.findByUsername(user);
		model.addAttribute("requestUser", u);
		
		LinkedHashSet<IResponse> iterableAnswers=new LinkedHashSet<>();
		System.out.println(u.getUsername());		
		Page<Answer> answers = answerRepository.findByUsername(u.getUsername(), new PageRequest(0, 5, new Sort(Direction.DESC,"dateTime")));
		for (Answer answer : answers) {
			iterableAnswers.add(answer);
		}
		model.addAttribute("cont", iterableAnswers);
			
		return "profile";
	}
	
	//USER'S CHALLENGES PAGE
	@RequestMapping(value="user.{user}.challenges", method=RequestMethod.GET)
	public String userChallenges(HttpSession session, HttpServletRequest req, Model model, @PathVariable String user) {

		User u = userRepository.findByUsername(user);
		
		model.addAttribute("requestUser", u);
		
		Page<ChallengeVideo> videos = challengeVideoRepository.findByUsername(u.getUsername(), new PageRequest(0, 5, new Sort(Direction.DESC,"uploadDate")));
		model.addAttribute("cont", videos.getContent());

		return "user-challenges";
	}
	
	//USER'S CHALLENGES PAGE
	@RequestMapping(value="userChallenges{user}", method=RequestMethod.GET)
	public String myChallenges(HttpSession session, HttpServletRequest req, Model model, @PathVariable String user) {
		if(session.getAttribute("sessionUser") == null) {
			return "index";
		}

		User u = userRepository.findByUsername(user);
		
		model.addAttribute("requestUser", u);
		
		Page<ChallengeVideo> videos = challengeVideoRepository.findByUsername(u.getUsername(), new PageRequest(0, 5, new Sort(Direction.DESC,"uploadDate")));
		model.addAttribute("cont", videos.getContent());

		return "user-challenges";
	}
	
//	//PROFILE PAGE AJAX LOAD-MORE
	@RequestMapping(value="profileContent{show}", method=RequestMethod.GET)
	@ResponseBody
	public LinkedHashSet<IResponse> mainView(HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model, @PathVariable String show) {
		LinkedHashSet<IResponse> response=new LinkedHashSet<>();
		String username=req.getParameter("username").toString();
		int offset=Integer.parseInt(req.getParameter("offset").toString());
		User u=userRepository.findByUsername(username);
		
		System.out.println(u.getUsername());
		if(show.equals("=answers")) {
			
			Page<Answer> answers=answerRepository.findByUsername(u.getUsername(), new PageRequest(offset, 5, new Sort(Direction.DESC,"dateTime")));
			response.addAll(answers.getContent());
			
		}
		else {
			Page<ChallengeVideo> videos=challengeVideoRepository.findByUsername(u.getUsername(), new PageRequest(offset, 5, new Sort(Direction.DESC,"uploadDate")));
			response.addAll(videos.getContent());
			System.out.println("RESPONSE SIZE IS " + videos.getContent().size());
		}
		
		return response;
	}
	
	//AJAX FOLLOW USER
	@RequestMapping(value="followUser", method=RequestMethod.POST)
	@ResponseBody
	public void followUser(HttpServletRequest req, HttpSession session) {

		User follower=(User)session.getAttribute("sessionUser");
		User followed=userRepository.findByUsername(req.getParameter("followed").toString());
		
		followed.getFollowers().add(follower.getId());
		follower.getFollowed().add(followed.getId());
		userRepository.save(followed);
		userRepository.save(follower);
		
		Follow follow=new Follow(follower, followed);
		followRepository.insert(follow);
		
		String description=NotificationsDescriptions.FOLLOWED_YOU.toString().toLowerCase().replace('_', ' ');
		Notification notification=new Notification(description, NotificationTypes.FOLLOW.toString(), LocalDateTime.now(), 
												   follower, followed, false, follower);

		notificationRepository.insert(notification);
	}
	
	//AJAX UNFOLLOW USER
	@RequestMapping(value="unfollowUser", method=RequestMethod.POST)
	@ResponseBody
	public void unfollowUser(HttpServletRequest req, HttpSession session) {
		User follower=(User)session.getAttribute("sessionUser");
		User followed=userRepository.findByUsername(req.getParameter("followed").toString());
		
		follower.getFollowed().remove(followed.getId());
		followed.getFollowers().remove(follower.getId());
		userRepository.save(followed);
		userRepository.save(follower);
		
		Follow follow=followRepository.findByFollowerAndFollowing(follower.getUsername(), followed.getUsername());
		followRepository.delete(follow);
	}
	
	//FOLLOWERS/FOLLOWINGS PAGE
	@RequestMapping(value="people")
	public String people(HttpServletRequest req, HttpSession session, Model model) {
		String username=req.getParameter("username").toString();
		
		if(req.getParameter("type").toString().equals("followers")) {
			model.addAttribute("type", "followers");
			Page<Follow> p=followRepository.findByFollowingUsername(username, new PageRequest(0, 7));
			model.addAttribute("cont", p.getContent());
		}else {
			model.addAttribute("type", "followings");
			Page<Follow> p=followRepository.findByFollowerUsername(username, new PageRequest(0, 7));
			model.addAttribute("cont", p.getContent());
		}
		System.out.println("ASDASDASD");
		return "people";
	}
	
	//PROFILE EDITING PAGE
	@RequestMapping(value="profileEditPage", method=RequestMethod.GET)
	public String profileEditPage(HttpSession session, Model model) {
		if(session.getAttribute("sessionUser") == null) {
			return "index";
		}
		
		User user=(User) session.getAttribute("sessionUser");
		
		model.addAttribute("user", user);
		
		return "edit-profile";
	}
	
	//EDIT PROFILE
	@RequestMapping(value="editProfile", method=RequestMethod.POST)
	public String editProfile(HttpSession session, HttpServletRequest req, @Valid @ModelAttribute("user") User user, BindingResult result, Errors errors, Model model, 
			@RequestParam("profilePic") MultipartFile profilePic, @RequestParam("coverPhoto") MultipartFile coverPhoto,  @RequestParam("fullName") String fullName) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if(session.getAttribute("sessionUser") == null) {
			return "index";
		}
		User oldUser=(User) session.getAttribute("sessionUser");
		String url=null;
		String filePath=null;
		File file=null;
		
		String password=req.getParameter("password").toString();
		String passConfirm=req.getParameter("confirmation").toString();
		
		user.setId(oldUser.getId());
		if(!password.isEmpty()) {
			if(password.length()<8) {
				model.addAttribute("error", "*Your password must contains at least 8 characters!");
				model.addAttribute("user", oldUser);
				return "edit-profile";
			}
			if(passConfirm != null || !passConfirm.isEmpty()) {
				if(password.equals(passConfirm)) {
					user.setPassword(Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString());
				}else {
					model.addAttribute("error", "*Please confirm your password");
					model.addAttribute("user", oldUser);
					return "edit-profile";
				}
			}else {
				model.addAttribute("error", "*Please confirm your password");
				model.addAttribute("user", oldUser);
				return "edit-profile";
			}
		}else {
			user.setPassword(oldUser.getPassword());
			user.setFollowers(oldUser.getFollowers());
		}
		user.setFollowed(oldUser.getFollowed());
		user.setProfilePic(oldUser.getProfilePic());
		user.setCoverPhoto(oldUser.getCoverPhoto());
		try {
			if(profilePic!=null && (FilenameUtils.getExtension(profilePic.getOriginalFilename()) != "")) {
				url="/profile_pictures" + File.separator + user.getUsername().hashCode() + "_" + LocalDate.now()+ "_" + new Random().nextInt() + "." + FilenameUtils.getExtension(profilePic.getOriginalFilename());
				filePath=WebInitializer.FILES_LOCATION + url;
				file=new File(filePath);
				profilePic.transferTo(file);
				user.setProfilePic(url);
				userRepository.save(user);
			}
			if(coverPhoto!=null && (FilenameUtils.getExtension(coverPhoto.getOriginalFilename()) != "")) {
				url="/cover_photos" + File.separator + user.getUsername().hashCode() + "_" + LocalDate.now()+ "_" + new Random().nextInt() + "." + FilenameUtils.getExtension(coverPhoto.getOriginalFilename());
				filePath=WebInitializer.FILES_LOCATION + url;
				file=new File(filePath);
				coverPhoto.transferTo(file);
				user.setCoverPhoto(url);
				userRepository.save(user);
			}
			
			userRepository.save(user);
			//TODO: check whether this method is the best way
			mongoTemplate.updateMulti(new Query(Criteria.where("sender.username").is(oldUser.getUsername())), Update.update("sender", user), Question.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("receiver.username").is(oldUser.getUsername())), Update.update("receiver", user), Question.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("user.username").is(oldUser.getUsername())), Update.update("user", user), Answer.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("question.sender.username").is(oldUser.getUsername())), Update.update("question.sender", user), Answer.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("question.receiver.username").is(oldUser.getUsername())), Update.update("question.receiver", user), Answer.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("sender.username").is(oldUser.getUsername())), Update.update("sender", user), Challenge.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("receiver.username").is(oldUser.getUsername())), Update.update("receiver", user), Challenge.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("user.username").is(oldUser.getUsername())), Update.update("user", user), ChallengeVideo.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("challenge.sender.username").is(oldUser.getUsername())), Update.update("challenge.sender", user), ChallengeVideo.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("challenge.receiver.username").is(oldUser.getUsername())), Update.update("challenge.receiver", user), ChallengeVideo.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("follower.username").is(oldUser.getUsername())), Update.update("follower", user), Follow.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("following.username").is(oldUser.getUsername())), Update.update("following", user), Follow.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("sender.username").is(oldUser.getUsername())), Update.update("sender", user), Notification.class);
			mongoTemplate.updateMulti(new Query(Criteria.where("receiver.username").is(oldUser.getUsername())), Update.update("receiver", user), Notification.class);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.removeAttribute("sessionUser");
		session.setAttribute("sessionUser", user);
		
		return "redirect:/profilePage" + user.getUsername();
	}
	
	//SEARCHING RESULTS PAGE
	@RequestMapping(value="resultsPage")
	public String resultsPage(HttpSession session, HttpServletRequest req, Model model) {
		if(session.getAttribute("sessionUser") == null) {
			return "index";
		}
		
		String criteria=req.getParameter("criteria").toString();
		
		model.addAttribute("criteria", criteria);
		
		return "searchResults";
	}
	
	//SEARCHING RESULTS
	@RequestMapping(value="autocomplete")
	@ResponseBody
	public List<User> autocomp(HttpServletRequest req) {
		String criteria=req.getParameter("criteria").toString();
		int page=Integer.parseInt(req.getParameter("page").toString());
		int limit=Integer.parseInt(req.getParameter("limit").toString());
		
		Page<User> users=userRepository.findByUsernameLikeOrFullNameLikeIgnoreCase(criteria, criteria, new PageRequest(page, limit));
		
		return users.getContent();
	}
	
	//USER LOGOUT METHOD
	@RequestMapping(value="logoutUser")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("sessionUser");
		session.invalidate();
		
		return "index";
	}
}
