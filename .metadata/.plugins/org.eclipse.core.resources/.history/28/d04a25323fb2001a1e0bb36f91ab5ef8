package it.esdebitamiretake.retake_ticket.anagrafica;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ProfileADR.class, name = "adr"),
})
@ApiModel(description = "All details about a company user (type=company)")
public class ProfileCompany extends ProfileUser{
	
	@Field("cf")
	@JsonProperty("cf")
	@NotNull
	private String cf;
		
	@Field("company")
	@JsonProperty("company")
	@NotNull
	private String company;
		

}