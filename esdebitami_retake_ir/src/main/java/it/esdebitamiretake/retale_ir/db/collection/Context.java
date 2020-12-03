package it.esdebitamiretake.retale_ir.db.collection;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import it.esdebitamiretake.retake_ir.search.Language;
import it.esdebitamiretake.retake_ir.validation.CronExpression;

@Document(collection = "contexts")
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonFormat(with = { com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY })
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(description = "A context associated with a specific template")
public class Context {

	@Id
	@Field("_id")
	@JsonProperty("id")
	@ApiModelProperty(notes = "The database generated context ID")
	private ObjectId id;

	@Field("uri")
	@JsonProperty("uri")
	@ApiModelProperty(notes = "The sources of the context")
	@NotBlank
	@Indexed(unique=true)
	private String uri;

	@Field("cron")
	@JsonProperty("cron")
	@ApiModelProperty(notes = "The cron expression that describe individual details of the schedule.")
	@CronExpression
	private String cronExp;

	@Field("css")
	@JsonProperty("css")
	@ApiModelProperty(notes = "The css query to find resources within the context")
	private String cssQuery;

	@Field("cssRef")
	@JsonProperty("cssRef")
	@ApiModelProperty(notes = "The css query to find the reference (e.g.: title) within the context resource")
	private String cssRefQuery;
	
	@Field("template")
	@JsonProperty("template")
	@ApiModelProperty(notes = "The template to which the context refers")
	@NotNull
	private String templateId;

	@Field("language")
	@JsonProperty("language")
	@ApiModelProperty(notes = "The ISO 639-1 code of the language used in context resources")
	@NotNull
	private Language language;
	
	@Field("date")
	@JsonProperty("date")
	private Date date;
	
	public String getID() {

		return id.toString();
	}
	
	public String getUri() {

		return uri;
	}

	public String getCronExp() {

		return cronExp;
	}

	public String getCssQuery() {

		return cssQuery;
	}
	
	public String getCssRefQuery() {

		return cssRefQuery;
	}
	
	public String getTemplateId() {

		return templateId;
	}

	public Language getLanguage() {
		
		return language;
	}
	
	public void setId(String id) {
		
		this.id=new ObjectId(id);
	}

	@Override
	public String toString() {

		return String.format("%s:%s", getTemplateId(),getUri());
	}
}