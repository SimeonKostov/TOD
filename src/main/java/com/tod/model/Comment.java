package com.tod.model;

import java.time.LocalDateTime;
import java.util.TreeSet;

public class Comment implements Comparable<Comment>, Actionable{
	
	private String id;
	private String content;
	private LocalDateTime uploadDate;
	private User user;
	private Actionable commented;
	private Comment parent;
	private TreeSet<Comment> subComments;
	
	public Comment(String content, LocalDateTime uploadDate, User user, Actionable commented) {
		this.content=content;
		this.uploadDate=uploadDate;
		this.user=user;
		this.commented=commented;
		this.subComments=new TreeSet<>();
	}
	
	public Comment(String id, String content, LocalDateTime uploadDate, User user, Actionable commented) {
		this(content, uploadDate, user, commented);
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Actionable getCommented() {
		return commented;
	}
	
	public void setCommented(Actionable commented) {
		this.commented = commented;
	}
	
	public Comment getParent() {
		return parent;
	}
	
	public void setParent(Comment parent) {
		this.parent = parent;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((uploadDate == null) ? 0 : uploadDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Comment other = (Comment) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (uploadDate == null) {
			if (other.uploadDate != null)
				return false;
		} else if (!uploadDate.equals(other.uploadDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Comment o) {
		if(this.uploadDate.equals(o.uploadDate)) {
			return this.user.getUsername().compareTo(o.getUser().getUsername());
		}
		
		return this.uploadDate.compareTo(o.uploadDate);
	}

	@Override
	public boolean isChallenge() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isComment() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void addSubcomment(Comment c) {
		this.subComments.add(c);
	}
}
