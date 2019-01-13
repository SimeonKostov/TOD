package com.tod.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="challenge_videos")
public class ChallengeVideo implements Actionable, INotifier, IResponse{

	@Id
	private String id;
	private LocalDateTime uploadDate;
	private User user;
	private Challenge challenge;
	private String url;
	private String thumbnail;
	
	public ChallengeVideo() {

	}
	
	public ChallengeVideo(LocalDateTime uploadDate, User user, Challenge challenge, String url) {
		this();
		this.uploadDate = uploadDate;
		this.user = user;
		this.challenge=challenge;
		this.url = url;
	}

	public ChallengeVideo(String id, LocalDateTime uploadDate, User user, Challenge challenge, String url) {
		this(uploadDate, user, challenge, url);
		this.id=id;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUploadDate() {
		return uploadDate.toString();
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}

	public User getUser() {
		return user;
	}

	public String getUsername() {
		return this.user.getUsername();
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String getContent() {
		return "";
	}

	@Override
	public boolean getAnonymous() {
		return this.challenge.anonymous;
	}

	@Override
	public void setAnonymous(boolean anonymous) {
		
	}

	@Override
	public boolean getAnswered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAnswered(boolean answered) {
		
	}

	@Override
	public boolean isChallenge() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isComment() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((challenge == null) ? 0 : challenge.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((uploadDate == null) ? 0 : uploadDate.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	
	public String getChallengeSender() {
		return this.challenge.sender.getUsername();
	}
	
	public String getChallengeContent() {
		return this.challenge.getContent();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChallengeVideo other = (ChallengeVideo) obj;
		if (challenge == null) {
			if (other.challenge != null)
				return false;
		} else if (!challenge.equals(other.challenge))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (uploadDate == null) {
			if (other.uploadDate != null)
				return false;
		} else if (!uploadDate.equals(other.uploadDate))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ChallengeVideo [id=" + id + ", uploadDate=" + uploadDate + ", user=" + user + ", challenge=" + challenge
				+ ", url=" + url + ", thumbnail=" + thumbnail + "]";
	}
}
