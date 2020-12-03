package it.esdebitamiretake.retake_ticket.anagrafica;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class Profile{

	@Field("name")
	@JsonProperty("name")
	@NotNull
	private String name;
	
	@Field("surname")
	@JsonProperty("surname")
	@NotNull
	private String surname;
	
	@Field("email")
	@JsonProperty("email")
	private String email;
	
	@Field("phone")
	@JsonProperty("phone")
	@NotNull
	private String phone;
	
	public String getName() {
		
		return name;
	}
	
	public String getSurname() {
		
		return surname;
	}
	
	public String getEmail() {
		
		return email;
	}

	public String getPhone() {
		
		return phone;
	}
	
	public void setEmail(String email) {
		
		this.email=email;
	}

	@Override
	public String toString(){
		
		return String.format("%s %s", name,surname);
	}
}