package com.tod.model;

import java.util.LinkedHashSet;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tod.repositories.UserRepository;

@Document(collection="users")
public class User implements INotifier{

	@Id
	private String id;
	
	@NotNull
	@NotEmpty(message = "*Please enter your username!")
	@Size(min=5, max=30, message = "*Username must be 5 to 30 characters!" )
	@Pattern(regexp="[a-zA-Z0-9_.-]{0,}", message="*Username must contain: "
												 + "[a-z] "
												 + "[A-Z] "
												 + "[0-9] "
												 + "[-] "
												 + "[_] "
												 + "[.]")
	@Indexed
	private String username;
	
	@NotNull
	@NotEmpty(message = "*Please enter your full name!")
	@Indexed
	private String fullName;
	
	@NotNull
	@NotEmpty(message = "*Please enter a password!")
	@Size(min=8, message = "*Your password must contains at least 8 characters!")
	private String password;
	
	@NotNull
	@NotEmpty(message = "*Please enter your email!")
	@Email(message = "*Please enter a valid email!")
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", message = "* •••••@•••••.com!")
	@Indexed
	private String email;
	
	private String about;
	private String profilePic;
	private String coverPhoto;
	private LinkedHashSet<String> followed;
	private LinkedHashSet<String> followers;
	private LinkedHashSet<Challenge> availableChallenges;
	private LinkedHashSet<ChallengeVideo> videos;
	private LinkedHashSet<Question> availableQuestions;
	private LinkedHashSet<Answer> answers;
	
	public User() {
		this.followers=new LinkedHashSet<>();
		this.followed=new LinkedHashSet<>();
		this.availableChallenges=new LinkedHashSet<>();
		this.videos=new LinkedHashSet<>();
		this.availableQuestions=new LinkedHashSet<>();
		this.answers=new LinkedHashSet<>();
	}
	
	public User(String username, String fullName, String password, String email) {
		this();
		this.username=username;
		this.fullName=fullName;
		this.password=password;
		this.email=email;
	}

	
	public User(String id, String username, String fullName, String password, String email, String about, String profilePic, String coverPhoto) {
		this(username, fullName, password, email);
		this.id = id;
		this.about = about;
		this.profilePic = profilePic;
		this.coverPhoto=coverPhoto;
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getCoverPhoto() {
		return coverPhoto;
	}

	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

	public LinkedHashSet<String> getFollowed() {
		return followed;
	}

	public void setFollowed(LinkedHashSet<String> followed) {
		this.followed = followed;
	}

	public LinkedHashSet<String> getFollowers() {
		return followers;
	}

	public void setFollowers(LinkedHashSet<String> followers) {
		this.followers = followers;
	}

	public LinkedHashSet<Challenge> getAvailableChallenges() {
		return availableChallenges;
	}

	public void setAvailableChallenges(LinkedHashSet<Challenge> availableChallenges) {
		this.availableChallenges = availableChallenges;
	}

	public LinkedHashSet<ChallengeVideo> getVideos() {
		return videos;
	}

	public void setVideos(LinkedHashSet<ChallengeVideo> videos) {
		this.videos = videos;
	}

	public LinkedHashSet<Question> getAvailableQuestions() {
		return availableQuestions;
	}

	public void setAvailableQuestions(LinkedHashSet<Question> availableQuestions) {
		this.availableQuestions = availableQuestions;
	}

	public LinkedHashSet<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(LinkedHashSet<Answer> answers) {
		this.answers = answers;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((about == null) ? 0 : about.hashCode());
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((availableChallenges == null) ? 0 : availableChallenges.hashCode());
		result = prime * result + ((availableQuestions == null) ? 0 : availableQuestions.hashCode());
		result = prime * result + ((coverPhoto == null) ? 0 : coverPhoto.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((followed == null) ? 0 : followed.hashCode());
		result = prime * result + ((followers == null) ? 0 : followers.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((profilePic == null) ? 0 : profilePic.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((videos == null) ? 0 : videos.hashCode());
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
		User other = (User) obj;
		if (about == null) {
			if (other.about != null)
				return false;
		} else if (!about.equals(other.about))
			return false;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (availableChallenges == null) {
			if (other.availableChallenges != null)
				return false;
		} else if (!availableChallenges.equals(other.availableChallenges))
			return false;
		if (availableQuestions == null) {
			if (other.availableQuestions != null)
				return false;
		} else if (!availableQuestions.equals(other.availableQuestions))
			return false;
		if (coverPhoto == null) {
			if (other.coverPhoto != null)
				return false;
		} else if (!coverPhoto.equals(other.coverPhoto))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (followed == null) {
			if (other.followed != null)
				return false;
		} else if (!followed.equals(other.followed))
			return false;
		if (followers == null) {
			if (other.followers != null)
				return false;
		} else if (!followers.equals(other.followers))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (profilePic == null) {
			if (other.profilePic != null)
				return false;
		} else if (!profilePic.equals(other.profilePic))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (videos == null) {
			if (other.videos != null)
				return false;
		} else if (!videos.equals(other.videos))
			return false;
		return true;
	}

	@Override
	public String getContent() {
		return "";
	}

	@Override
	public boolean getAnonymous() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAnonymous(boolean anonymous) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean getAnswered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAnswered(boolean answered) {
		// TODO Auto-generated method stub
	}

	//TODO implement social login
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user=userRepository.findByUsername(username);
//		
//		if (user == null) {
//			throw new UsernameNotFoundException("No user found with username: " + username);
//	    }
//		 
//		UserPrincipal principal = UserPrincipal.builder()
//                .firstName(user.getFirstName())
//                .id(user.getId())
//                .lastName(user.getLastName())
//                .password(user.getPassword())
//                .role(user.getRole())
//                .socialSignInProvider(user.getSignInProvider())
//                .username(user.getEmail())
//                .build();
// 
//        return principal;
//	}
}
