package com.tod.controller;

import java.util.LinkedHashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tod.model.Notification;
import com.tod.model.User;
import com.tod.repositories.NotificationRepository;
import com.tod.repositories.UserRepository;

@Controller
public class NotificationsController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private NotificationRepository notificationRepository;
	
	//CHECKING FOR NOTIFICATIONS
	@RequestMapping(value="checkNotifications", method=RequestMethod.POST)
	@ResponseBody
	public boolean getNotifications(HttpSession session, HttpServletRequest req){
		String username=req.getParameter("username").toString();	
		User user=userRepository.findByUsername(username);
		List<Notification> list=notificationRepository.findByCheckedIsFalseAndReceiver(username);
		return list.size()>0;
	}
	
	//LOAD NOTIFICATIONS
	@RequestMapping(value="getOlderNotifications", method=RequestMethod.GET)
	@ResponseBody
	public List<Notification> loadNotifications(HttpServletRequest req){
			LinkedHashSet<Notification> iterableSet=new LinkedHashSet<>();
			User user=userRepository.findByUsername(req.getParameter("username").toString());
			Page<Notification> notifications=notificationRepository.findByReceiver(user.getUsername(), new PageRequest(0, 5));
		
		return notifications.getContent();
	}
	
	//NOTIFICATIONS PAGE 
	@RequestMapping(value="notificationsPage", method=RequestMethod.GET)
	public String notificationsPage(HttpSession session, Model model) {
		if(session.getAttribute("sessionUser") == null) {
			return "index";
		}
		
		return "notifications";
	}
	
	//AJAX LOAD MORE NOTIFICATIONS
	@RequestMapping(value="getNotifications", method=RequestMethod.GET)
	@ResponseBody
	public List<Notification> getOlderNotifications(HttpServletRequest req, HttpSession session) {
		User user=(User) session.getAttribute("sessionUser");
		int offset=Integer.parseInt(req.getParameter("offset").toString());
		int limit=Integer.parseInt(req.getParameter("limit").toString());
		
		List<Notification> list=notificationRepository.findByCheckedIsFalseAndReceiver(user.getUsername());
		for (Notification notification : list) {
			notification.setChecked(true);
			notificationRepository.save(notification);
		}
		
		Page<Notification> page=notificationRepository.findByReceiver(user.getUsername(), new PageRequest(offset, limit, new Sort(Direction.DESC,"uploadDate")));
		
		return page.getContent();
	}
}
