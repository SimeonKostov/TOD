package com.tod.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="questions")
public class Question extends Dispatches{

	protected String content;
	
	public Question() {
		
	}
	
	public Question(String content, LocalDateTime uploadDate, User sender, User receiver, boolean isAnonymous, boolean isAnswered) {
		super(uploadDate, sender, receiver, isAnonymous, isAnswered);
		this.content=content;
	}
	
	public Question(String id, String content, LocalDateTime uploadDate, User sender, User receiver, boolean isAnonymous, boolean isAnswered) {
		super(id, uploadDate, sender, receiver, isAnonymous, isAnswered);
		this.content=content;
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
		// TODO Auto-generated method stub
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
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
		return this.answered;
	}

	@Override
	public void setAnswered(boolean answered) {
		this.answered=answered;
	}

	
}
