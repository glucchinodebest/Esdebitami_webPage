package it.esdebitamiretake.retake_ai.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "dataset")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Dataset {

	@Id
	@Field("_id")
	private String id;

	@Field("templateId")
	@JsonProperty("templateId")
	private String templateId;

	@Field("sentence")
	@JsonProperty("sentence")
	private String sentence;

	public Dataset() {
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Dataset( String templateId, String sentence) {
		super();
		this.templateId = templateId;
		this.sentence = sentence;
	}
	
	

	
}