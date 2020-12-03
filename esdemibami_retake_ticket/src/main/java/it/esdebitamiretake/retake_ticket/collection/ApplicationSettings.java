package it.esdebitamiretake.retake_ticket.collection;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

@Document(collection="applicationSettings")
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "Application preferences (settings)")
public class ApplicationSettings {
	
	@Id
	@Field("_id")
	@JsonProperty("id")
	private String id;
	
	@Field("prefs")
	@JsonProperty("prefs")
	@JsonFormat(with = { com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY })
	@NotNull
	@NotEmpty
	private List<Preference> prefs;

	public ApplicationSettings(String id, List<Preference> prefs) {
		this.id = id;
		this.prefs = prefs;
	}

	public ApplicationSettings() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Preference> getPrefs() {
		return prefs;
	}

	public void setPrefValues(List<Preference> prefs) {
		this.prefs = prefs;
	}
	
	public Preference getPreference(String name) {
		Preference preference = null;
		for (Preference p : prefs) {
		  if (p.getName().equalsIgnoreCase(name)) {
		    preference = p;
		    break;
		  }
		}
		return preference;
	}

}
