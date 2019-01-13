package com.tod.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tod.model.User;
import com.tod.repositories.UserRepository;

@Controller
public class UploadsController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="getPicture")
	@ResponseBody
	public void getPicture(HttpServletRequest req, HttpServletResponse resp) {
		User user=userRepository.findByUsername(req.getParameter("username").toString());
		String url=null;
		if(req.getParameter("type").toString().equals("profile")) {
			url=user.getProfilePic();
		}
		else {
			if(req.getParameter("type").toString().equals("cover")) {
				url=user.getCoverPhoto();
			}
		}
		
		System.out.println("THE URL IS " + url);
		File file=new File(url);
		
		try {
			Files.copy(file.toPath(), resp.getOutputStream());
		} catch (IOException e) {
			resp.setStatus(503);
		}
	}
}
