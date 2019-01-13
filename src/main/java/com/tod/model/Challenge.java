package com.tod.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="challenges")
public class Challenge extends Dispatches{

	protected String content;
	
	public Challenge() {
		
	}
	
	public Challenge(String content, LocalDateTime uploadDate, User sender, User receiver, boolean isAnonymous, boolean isAnswered) {
		super(uploadDate, sender, receiver, isAnonymous, isAnswered);
		this.content=content;
	}
	
	public Challenge(String id, String content, LocalDateTime uploadDate, User sender, User receiver, boolean isAnonymous, boolean isAnswered) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Challenge other = (Challenge) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}
	
}
