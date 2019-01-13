package com.tod.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public abstract class Dispatches implements Comparable<Dispatches>, INotifier{

	@Id
	protected String id;
	protected LocalDateTime uploadDate;
	protected User sender;
	protected User receiver;
	protected boolean anonymous;
	protected boolean answered;
	
	public Dispatches() {
		
	}
	
	public Dispatches(LocalDateTime uploadDate, User sender, User receiver, boolean anonymous, boolean answered) {
		super();
		this.uploadDate = uploadDate;
		this.sender = sender;
		this.receiver = receiver;
		this.anonymous=anonymous;
		this.answered=answered;
	}
	
	public Dispatches(String id, LocalDateTime uploadDate, User sender, User receiver, boolean anonymous, boolean answered) {
		this(uploadDate, sender, receiver, anonymous, answered);
		this.id=id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (anonymous ? 1231 : 1237);
		result = prime * result + (answered ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((uploadDate == null) ? 0 : uploadDate.hashCode());
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
		Dispatches other = (Dispatches) obj;
		if (anonymous != other.anonymous)
			return false;
		if (answered != other.answered)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (uploadDate == null) {
			if (other.uploadDate != null)
				return false;
		} else if (!uploadDate.equals(other.uploadDate))
			return false;
		return true;
	}
	
}