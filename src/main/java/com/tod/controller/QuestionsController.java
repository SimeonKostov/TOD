package com.tod.controller;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tod.model.Answer;
import com.tod.model.Notification;
import com.tod.model.NotificationTypes;
import com.tod.model.NotificationsDescriptions;
import com.tod.model.Question;
import com.tod.model.User;
import com.tod.repositories.AnswerRepository;
import com.tod.repositories.NotificationRepository;
import com.tod.repositories.QuestionRepository;
import com.tod.repositories.UserRepository;

@Controller
public class QuestionsController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private NotificationRepository notificationRepository;

	//AJAX QUESTION ASKING
	@RequestMapping(value="sendQuestion", method=RequestMethod.POST)
	@ResponseBody
	public void askQuestion(HttpServletRequest req, HttpServletResponse resp) {
		
		String content=req.getParameter("question").toString();
		
		User sender=userRepository.findByUsername(req.getParameter("sender").toString());
		
		User receiver=userRepository.findByUsername(req.getParameter("receiver").toString());
		
		boolean isAnonymous=Boolean.parseBoolean(req.getParameter("isAnonymous").toString());
		
		Question question=new Question(content, LocalDateTime.now(), sender, receiver, isAnonymous, false);
		questionRepository.insert(question);
		
		String description=NotificationsDescriptions.ASKED_YOU_А_QUESTION.toString().toLowerCase().replace('_', ' ');
		Notification notification=new Notification(description, NotificationTypes.QUESTION.toString(), LocalDateTime.now(), sender, receiver, 
												   false, question);
		notificationRepository.insert(notification);
			
	}
	
	//QUESTION PAGE
	@RequestMapping(value="question", method=RequestMethod.GET)
	public String questionPage(HttpServletRequest req, HttpSession session, Model model) {
		if(session.getAttribute("sessionUser") == null) {
			return "index";
		}
		
		String notifierId=req.getParameter("notifierId").toString();
		String notificationId=req.getParameter("notificationId").toString();
		
		Question question=questionRepository.findById(notifierId);
		Notification notification=notificationRepository.findById(notificationId);
		
		model.addAttribute("question",question);
		model.addAttribute("notification",notification);
		
		return "question";
	}
	
	//AJAX QUESTION ANSWERING
	@RequestMapping(value="answerQuestion", method=RequestMethod.POST)
	@ResponseBody
	public void answerQuestion(HttpServletRequest req, HttpServletResponse resp) {
			User answerSender=userRepository.findByUsername(req.getParameter("answerSender").toString());
			User answerReceiver=userRepository.findByUsername(req.getParameter("answerReceiver").toString());
			String answerContent=req.getParameter("answerContent").toString();
			
			Question question=questionRepository.findById((req.getParameter("questionId").toString()));
			question.setAnswered(true);
			questionRepository.save(question);
			
			Notification notification=notificationRepository.findById(req.getParameter("notificationId").toString());
			notification.getNotifier().setAnswered(true);
			notificationRepository.save(notification);
					
			Answer answer=new Answer(answerContent, LocalDateTime.now(), answerSender, question);
			answerRepository.insert(answer);
			
			String description=NotificationsDescriptions.ANSWERED_YOUR_QUESTION.toString().toLowerCase().replace('_', ' ');
			Notification newNotification=new Notification(description, NotificationTypes.ANSWER.toString(), LocalDateTime.now(), answerSender, 
					answerReceiver, false, answer);
			
			notificationRepository.insert(newNotification);
	}
	
//	//ANSWERED QUESTION PAGE
//	@RequestMapping(value="answeredQuestion", method=RequestMethod.GET)
//	public String answeredQuestionPage1(Model model, HttpServletRequest req, HttpSession session) {
//		if(session.getAttribute("sessionUser")==null) {
//			return "index";
//		}
//		
//		Answer answer=answerRepository.findById(req.getParameter("id").toString());
//		model.addAttribute("answer", answer);
//		
//		return "answered-question";
//	}
	
	@RequestMapping(value="answer", method=RequestMethod.GET)
	public String answeredQuestionPage2(Model model, HttpServletRequest req, HttpSession session) {
		if(session.getAttribute("sessionUser")==null) {
			return "index";
		}
		
		Answer answer=answerRepository.findById(req.getParameter("id").toString());	
		model.addAttribute("answer", answer);
		
		return "answered-question";
	}
}
