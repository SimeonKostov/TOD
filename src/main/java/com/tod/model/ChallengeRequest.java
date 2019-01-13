package com.tod.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="challenge_requests")
public class ChallengeRequest extends Dispatches implements INotifier{
	
	public ChallengeRequest() {
		
	}

	public ChallengeRequest(LocalDateTime uploadDate, User sender, User receiver, boolean isAnonymous,
			boolean isAnswered) {
		super(uploadDate, sender, receiver, isAnonymous, isAnswered);
	}

	public ChallengeRequest(String id, LocalDateTime uploadDate, User sender, User receiver, boolean isAnonymous, boolean isAnswered) {
		super(id, uploadDate, sender, receiver, isAnonymous, isAnswered);
	}

	@Override
	public int compareTo(Dispatches o) {
		if(this.uploadDate.equals(o.uploadDate)) {
			return this.sender.getUsername().compareTo(o.sender.getUsername());
		}
		
		return this.uploadDate.compareTo(o.uploadDate);
	}
	
	@Override
	public String getContent() {
		return "";
	}

	@Override
	public boolean getAnonymous() {
		return this.anonymous;
	}

	@Override
	public void setAnonymous(boolean anonymous) {
		this.anonymous=anonymous;
	}

	@Override
	public boolean getAnswered() {
		return answered;
	}

	@Override
	public void setAnswered(boolean answered) {
		this.answered=answered;
	}
}
