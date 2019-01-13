package com.tod.controller;

import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tod.model.Answer;
import com.tod.model.ChallengeVideo;
import com.tod.model.IResponse;
import com.tod.model.User;
import com.tod.repositories.AnswerRepository;
import com.tod.repositories.ChallengeVideoRepository;

@Controller
public class WelcomeController {

	@Autowired
	ChallengeVideoRepository challengeVideoRepository;
	@Autowired
	AnswerRepository answerRepository;
	
	@RequestMapping(value="*")
	public String welcomePage(HttpSession session) {
		System.out.println("zdrasti");
		System.out.println(session.getAttribute("sessionUser")==null);
		if(session.getAttribute("sessionUser")==null) {
			return "index";
		}
		else {
			return "redirect:/main";
		}
	}
	
	//MAIN PAGE FEED LOADER
	@RequestMapping(value="main", method=RequestMethod.GET)
	public String welcomeFeed(HttpSession session, Model model) {
		if(session.getAttribute("sessionUser")==null) {
			return "index";
		}
		
		Page<Answer> answers=answerRepository.findAll(new PageRequest(0, 5, new Sort(Direction.DESC,"dateTime")));
	
		model.addAttribute("cont", answers.getContent());
		return "main";
	}
	
	//MAIN/CHALLENGES PAGE AJAX LOAD-MORE
	@RequestMapping(value="feed{show}", method=RequestMethod.GET)
	@ResponseBody
	public LinkedHashSet<IResponse> mainView(HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model, @PathVariable String show) {
		Page<Answer> answers = null;
		Page<ChallengeVideo> videos=null;
		LinkedHashSet<IResponse> response=new LinkedHashSet<>();

		if(show.equals("=answers")) {
			int offset=Integer.parseInt(req.getParameter("offset").toString());
			
			answers=answerRepository.findAll(new PageRequest(offset, 5, new Sort(Direction.DESC,"dateTime")));
			
			response.addAll(answers.getContent());
	
		}
		if(show.equals("=challenges")) {
			int offset=Integer.parseInt(req.getParameter("offset").toString());
			
			videos=challengeVideoRepository.findAll(new PageRequest(offset, 5, new Sort(Direction.DESC,"uploadDate")));
			
			response.addAll(videos.getContent());
			
		}
		
		return response;
	}
	
	//LOGIN/SIGN UP PAGE LINKS
	@RequestMapping(value="loginPage")
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping(value="signupPage")
	public String signupPage(Model model){
		model.addAttribute("user", new User());
		return "signup";
	}
	
	//FORGOTTEN PASSWORD PAGE
	@RequestMapping(value="forgottenPassword")
	public String forgottenPassPage() {
		return "passForgot";
	}
}
