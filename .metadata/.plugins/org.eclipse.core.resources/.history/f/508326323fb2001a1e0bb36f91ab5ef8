package it.esdebitamiretake.retake_ticket.anagrafica;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ProfileCompany.class, name = "company"),
})
@Document(collection = "users")
@ApiModel(description = "User details info. The type of user must be indicated using the 'type' attribute")
public class ProfileUser extends Profile{

	@Id
	@Field("_id")
	//@JsonIgnore
	private String id;

	@Field("deviceToken")
	@JsonProperty("deviceToken")
	private String[] deviceToken;
		
	@Field("templates")
	@JsonProperty("templates")
	private String[] templates;
	
	public String getId() {
		
		return id.toString();
	}
		
	public void setId(String id) {
		
		this.id=id;
	}
		
	@Override
	public String toString(){
		
		return id.toString();
	}
}