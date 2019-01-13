package com.tod.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="follows")
public class Follow {

	@Id
	private String id;
	@Indexed
	private User follower;
	@Indexed
	private User following;
	
	public Follow() {
		
	}
	
	public Follow(User follower, User following) {
		super();
		this.follower = follower;
		this.following = following;
	}
	
	public Follow(String id, User follower, User following) {
		super();
		this.id=id;
		this.follower = follower;
		this.following = following;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public User getFollowing() {
		return following;
	}

	public void setFollowing(User following) {
		this.following = following;
	}
	
	
	
}
