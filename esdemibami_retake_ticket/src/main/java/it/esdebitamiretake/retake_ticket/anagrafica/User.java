package it.esdebitamiretake.retake_ticket.anagrafica;

import java.util.Calendar;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Transient;
import org.tempuri.XSpService.MembershipUser;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	@JsonProperty("username")
	@NotNull
	private String username;

	@JsonProperty("password")
	private String password;

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("passwordQuestion")
	private String passwordQuestion;
	
	@JsonProperty("passwordAnswer")
	private String passwordAnswer;

	@JsonProperty("creationDate")
	private Calendar creationDate;

	@JsonProperty("lastActivityDate")
	private Calendar lastActivityDate;

	@JsonProperty("approved")
	private boolean approved;

	@Transient
	@JsonProperty("profile")
	private ProfileUser profile;
	
	public User() {

	}

	public User(MembershipUser user) {

		this.username=user.get_UserName();
		this.email=user.get_Email();
		this.passwordQuestion=user.get_PasswordQuestion();
		this.creationDate=user.get_CreationDate();
		this.approved=user.get_IsApproved();
		this.lastActivityDate=user.get_LastActivityDate();
	}

	public String getUsername() {

		return username;
	}

	public String getPassword() {

		return password;
	}
	
	public String getEmail() {
		
		return email;
	}

	public String getPasswordQuestion() {

		return passwordQuestion;
	}
	
	public String getPasswordAnswer() {

		return passwordAnswer;
	}
	
	public boolean isApproved() {

		return approved;
	}
	
	public ProfileUser getProfile() {
		
		return profile;
	}
	
	public void setProfile(ProfileUser profile) {
		
		this.profile=profile;
	}
	
	@Override
	public String toString() {
		
		return getUsername();
	}
	
	public MembershipUser toMembershipUser() {

		MembershipUser user=new MembershipUser();
		
		user.set_UserName(getUsername());
		user.set_PasswordQuestion(getPasswordQuestion());
		user.set_IsApproved(this.isApproved());
		
		return user;
	}
}