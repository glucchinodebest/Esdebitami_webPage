package it.esdebitamiretake.retake_ticket.anagrafica;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "anagraphics")
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "General Anagraphic model for a specific application")
public class Anagraphic {

	@Id
	@Field("_id")
	@JsonProperty("id")
	private ObjectId id;

	@Field("flag")
	@JsonProperty("flag")
	@NotNull
	@NotEmpty
	private String flag;

	@Field("value")
	@JsonProperty("value")
	@NotNull
	@NotEmpty
	private String value;
	
	@Field("appName")
	@JsonProperty("appName")
	private String appName;
	
	public Anagraphic() {
	}

	public Anagraphic(ObjectId id, String flag, String value) {
		this.id = id;
		this.flag = flag;
		this.value = value;
	}

	public Anagraphic(ObjectId id, String flag, String value, String appName) {
		this.id = id;
		this.flag = flag;
		this.value = value;
		this.appName = appName;
	}

	public Anagraphic(String flag, String value, String appName) {
		this.flag = flag;
		this.value = value;
		this.appName = appName;
	}

	public String getId() {
		return id==null?null:id.toString();
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	
	
	
}