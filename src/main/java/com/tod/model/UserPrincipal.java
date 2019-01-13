package com.tod.model;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.social.security.SocialUser;

public class UserPrincipal { //extends SocialUser{

	private Long id;
	 
    private String fullName;
 
    private Role role;
 
//    public UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//		super(username, password, authorities);
//		// TODO Auto-generated constructor stub
//	}
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public SocialMediaService getSocialSignInProvider() {
		return socialSignInProvider;
	}

	public void setSocialSignInProvider(SocialMediaService socialSignInProvider) {
		this.socialSignInProvider = socialSignInProvider;
	}

	private SocialMediaService socialSignInProvider;
//	public static class Builder {
//		 
//        private Long id;
// 
//        private String username;
// 
//        private String fullName;
// 
//        private String password;
// 
//        private Role role;
// 
//        private SocialMediaService socialSignInProvider;
// 
//        private Set<GrantedAuthority> authorities;
// 
//        public Builder() {
//            this.authorities = new HashSet<>();
//        }
// 
//        public Builder fullName(String fullName) {
//            this.fullName = fullName;
//            return this;
//        }
// 
//        public Builder id(Long id) {
//            this.id = id;
//            return this;
//        }
// 
//        public Builder password(String password) {
//            if (password == null) {
//                password = "SocialUser";
//            }
// 
//            this.password = password;
//            return this;
//        }
// 
//        public Builder role(Role role) {
//            this.role = role;
// 
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
//            this.authorities.add(authority);
// 
//            return this;
//        }
// 
//        public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
//            this.socialSignInProvider = socialSignInProvider;
//            return this;
//        }
// 
//        public Builder username(String username) {
//            this.username = username;
//            return this;
//        }
// 
//        public UserPrincipal build() {
//        	UserPrincipal user = new UserPrincipal(username, password, authorities);
// 
//            user.id = id;
//            user.fullName=fullName;
//            user.role = role;
//            user.socialSignInProvider = socialSignInProvider;
// 
//            return user;
//        }
//    }
}
