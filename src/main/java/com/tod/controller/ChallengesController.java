package com.tod.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tod.WebInitializer;
import com.tod.model.Challenge;
import com.tod.model.ChallengeRequest;
import com.tod.model.ChallengeVideo;
import com.tod.model.Notification;
import com.tod.model.NotificationTypes;
import com.tod.model.NotificationsDescriptions;
import com.tod.model.Question;
import com.tod.model.User;
import com.tod.repositories.ChallengeRepository;
import com.tod.repositories.ChallengeRequestRepository;
import com.tod.repositories.ChallengeVideoRepository;
import com.tod.repositories.NotificationRepository;
import com.tod.repositories.QuestionRepository;
import com.tod.repositories.UserRepository;

@Controller
public class ChallengesController {
	
	@Autowired
	private UserRepository userRepository;	
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ChallengeRepository challengeRepository;
	@Autowired
	private ChallengeRequestRepository challengeRequestRepository;
	@Autowired
	private ChallengeVideoRepository challengeVideoRepository;
	
	//CHALLENGE REQUESTING
	@RequestMapping(value="challengeRequest", method=RequestMethod.POST)
	@ResponseBody
	public void challengeRequest(HttpServletRequest req, HttpServletResponse resp) {
			User requestSender=userRepository.findByUsername(req.getParameter("requestSender").toString());
			User requestReceiver=userRepository.findByUsername(req.getParameter("requestReceiver").toString());
			Question question=questionRepository.findById(req.getParameter("questionId").toString());
			
			ChallengeRequest challengeRequest=new ChallengeRequest(LocalDateTime.now(), requestSender, requestReceiver, question.getAnonymous(), false);
			challengeRequestRepository.insert(challengeRequest);
			
			question.setAnswered(true);
			questionRepository.save(question);
			
			Notification notification=notificationRepository.findById(req.getParameter("notificationId").toString());
			notification.getNotifier().setAnswered(true);
			notificationRepository.save(notification);
	
			String description=NotificationsDescriptions.DARED_TO_RECEIVE_A_CHALLENGE.toString().toLowerCase().replace('_', ' ');	
			Notification newNotification=new Notification(description, NotificationTypes.NO_ANSWER.toString(), LocalDateTime.now(), requestSender, 
					requestReceiver, false, challengeRequest);
			notificationRepository.insert(newNotification);
			
	}
	
	//CHALLENGE PAGE
	@RequestMapping(value="challenge", method=RequestMethod.GET)
	public String challengePage(HttpServletRequest req, Model model, HttpSession session) {
		if(session.getAttribute("sessionUser") == null) {
			return "index";
		}
		
		Notification notification=notificationRepository.findById(req.getParameter("id").toString());
		
		model.addAttribute("notification", notification);
		
		return "challenge-page";
	}
	
	//CHALLENGE SENDING
	@RequestMapping(value="sendChallenge", method=RequestMethod.POST)
	@ResponseBody
	public void challengeSending(HttpServletRequest req) {
		User challengeSender=userRepository.findByUsername(req.getParameter("challengeSender").toString());
		User challengeReceiver=userRepository.findByUsername(req.getParameter("challengeReceiver").toString());
		
		ChallengeRequest challengeRequest=challengeRequestRepository.findById(req.getParameter("requestId").toString());
		challengeRequest.setAnswered(true);
		challengeRequestRepository.save(challengeRequest);
		
		Challenge challenge=new Challenge(req.getParameter("challengeContent").toString(), LocalDateTime.now(), challengeSender, challengeReceiver, 
				challengeRequest.getAnonymous(), false);
		challengeRepository.insert(challenge);
		
		Notification notification=notificationRepository.findById(req.getParameter("notificationId").toString());
		notification.getNotifier().setAnswered(true);
		notificationRepository.save(notification);
		
		String description=NotificationsDescriptions.SENT_YOU_CHALLENGE.toString().toLowerCase().replace('_', ' ');
		Notification newNotification=new Notification(description, NotificationTypes.CHALLENGE.toString(), LocalDateTime.now(), challengeSender, 
												   challengeReceiver, false, challenge);
		notificationRepository.insert(newNotification);	

	}
	
	//CHALLENGE RESPONSE UPLOADING PAGE
	@RequestMapping(value="videoResponse", method=RequestMethod.GET)
	public String videoUploading(HttpServletRequest req, HttpSession session, Model model) {
		if(session.getAttribute("sessionUser")==null) {
			return "index";
		}
		
		Challenge challenge=challengeRepository.findById(req.getParameter("notifierId").toString());
		Notification notification=notificationRepository.findById(req.getParameter("notificationId").toString());
		
		model.addAttribute("challenge", challenge);
		model.addAttribute("notification", notification);
		
		return "upload-video";
	}
	
	//CHALLENGE RESPONSE UPLOADING
	@RequestMapping(value="uploadVideo", method=RequestMethod.POST)
	@ResponseBody
	public void videoUploading(HttpServletRequest req, HttpSession session, @RequestParam("file1") MultipartFile challengeResponse) {
		
		Challenge challenge=null;
		User uploader=null;
		User challengeSender=null;
		String url=null;
		String filePath=null;
		boolean isSupported=false;
		ChallengeVideo challengeVideo=null;
		
		challenge=challengeRepository.findById(req.getParameter("notifierId").toString());
		uploader=challenge.getReceiver();
		challengeSender=challenge.getSender();
		
		if(challengeResponse!=null && (!FilenameUtils.getExtension(challengeResponse.getOriginalFilename()).isEmpty())) {
			
			if(FilenameUtils.getExtension(challengeResponse.getOriginalFilename()).equals(".mp4")) {
				isSupported=true;
			}
			
			url="/challenge_videos" + File.separator + uploader.getId() + "_" + LocalDate.now()+ "_" + new Random().nextInt()  + "." + FilenameUtils.getExtension(challengeResponse.getOriginalFilename());
			filePath=WebInitializer.FILES_LOCATION + url;
			File file=new File(filePath);
			try {
				challengeResponse.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			challengeVideo=new ChallengeVideo(LocalDateTime.now(), uploader, challenge, url);
			challengeVideoRepository.insert(challengeVideo);
			challenge.setAnswered(true);
			challengeRepository.save(challenge);
			
			Notification notification=notificationRepository.findById(req.getParameter("notificationId").toString());
			notification.getNotifier().setAnswered(true);
			notificationRepository.save(notification);
			
		}
		
		String line=null;
        String oldFormat = filePath;
        String newFormatLocation=WebInitializer.FILES_LOCATION + "/challenge_videos";
        String thumbnailsLocation=WebInitializer.FILES_LOCATION + "/thumbnails";
        String newFormatName = File.separator + uploader.getId() + "_" + LocalDate.now()+ "_" + new Random().nextInt();
        String cmd=null;
        Process p=null;
        BufferedReader in=null;
        
		if(!isSupported) {
			try {
				//converting video to mp4 format
	            cmd = "/usr/bin/ffmpeg/bin/ffmpeg -i " + oldFormat + " " + newFormatLocation + "" + newFormatName + ".mp4";
	            p = Runtime.getRuntime().exec(cmd);
	            
	            in = new BufferedReader(
	                    new InputStreamReader(p.getErrorStream()));
	            while ((line = in.readLine()) != null) {
	                System.out.println(line);
	            }
	            p.waitFor();
	            System.out.println("Video converted successfully!");
	            in.close();
	            
	            Files.deleteIfExists(Paths.get(oldFormat));
	            
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
		}
		
		cmd = "C:\\ffmpeg\\bin\\ffmpeg -i " + newFormatLocation.concat(newFormatName.concat(".mp4")) + " -ss 00:00:04.435 -vframes 1 " + thumbnailsLocation.concat(newFormatName.concat(".jpg"));
        try {
			p = Runtime.getRuntime().exec(cmd);
			in = new BufferedReader(
	                new InputStreamReader(p.getErrorStream()));
	        while ((line = in.readLine()) != null) {
	            System.out.println(line);
	        }
	        
	        p.waitFor();
	        System.out.println("Video converted successfully!");
	        in.close();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
        String description=NotificationsDescriptions.MET_THE_CHALLENGE.toString().toLowerCase().replace('_', ' ');
		Notification newNotification=new Notification(description, NotificationTypes.CHALLENGE_RESPONSE.toString(), LocalDateTime.now(), 
													uploader, challengeSender, false, challengeVideo);
		notificationRepository.insert(newNotification);
		
        challengeVideo.setUrl("/challenge_videos".concat(newFormatName.concat(".mp4")));
        challengeVideo.setThumbnail("/thumbnails".concat(newFormatName.concat(".jpg")));
        challengeVideoRepository.save(challengeVideo);

	}
	
	//CHALLENGES PAGE
	@RequestMapping(value="challenges", method=RequestMethod.GET)
	public String challenges(HttpServletRequest req, Model model, HttpSession session) {
		if(session.getAttribute("sessionUser") == null) {
			return "index";
		}
		
		Page<ChallengeVideo> videos=challengeVideoRepository.findAll(new PageRequest(0, 5, new Sort(Direction.DESC,"uploadDate")));
		
		model.addAttribute("cont", videos.getContent());
		
		return "challenges";
	}
	
	//AJAX LOAD MORE CHALLENGES
	@RequestMapping(value="loadChallenges", method=RequestMethod.GET)
	@ResponseBody
	public List<ChallengeVideo> loadVideos(HttpServletRequest req){
		
		int offset=Integer.parseInt(req.getParameter("offset").toString());
		
		Page<ChallengeVideo> videos=challengeVideoRepository.findAll(new PageRequest(offset, 5, new Sort(Direction.DESC,"uploadDate")));
		
		return videos.getContent();
	}
	
	//SINGLE CHALLENGE RESPONSE PAGE
	@RequestMapping(value="challengeResponse", method=RequestMethod.GET)
	public String answeredQuestionPage2(Model model, HttpServletRequest req, HttpSession session) {
		if(session.getAttribute("sessionUser")==null) {
			return "index";
		}
		
		ChallengeVideo video=challengeVideoRepository.findById(req.getParameter("notifierId").toString());	
		System.out.println(video.toString());
		model.addAttribute("video", video);
		
		return "video";
	}
}
