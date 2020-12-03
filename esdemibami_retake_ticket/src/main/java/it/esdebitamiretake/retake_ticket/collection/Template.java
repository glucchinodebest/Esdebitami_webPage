package it.esdebitamiretake.retake_ticket.collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "templates")
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonFormat(with = { com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY })
@XmlRootElement
@ApiModel(description = "Model to create the template")
public class Template {
	
	@Id
	@Field("_id")
	@JsonProperty("templateId")
	@XmlElement
	private ObjectId templateId;
	
	@Field("authorId")
	@JsonProperty("authorId")
	@XmlElement
	private String authorId;
	
	@Field("templateType")
	@JsonProperty("templateType")
	@XmlElement
	private String templateType;
	
	/*public enum Type {
		LIBERO, MISTO, CONTROLLATO
	};

	private Type templateType;*/

	@Field("dataset")
	@JsonProperty("dataset")
	@XmlElement
	private String dataset;

	@Field("link")
	@JsonProperty("link")
	@XmlElement
	private String link;

	@Field("title")
	@Indexed(unique = true)
	@NotNull
	@NotEmpty
	@JsonProperty("title")
	@XmlElement
	private String title;
	
	@Field("description")
	@JsonProperty("description")
	@XmlElement
	private String description;
	
	@Field("status")
	@JsonProperty("status")
	@XmlElement
	@NotNull
	public Integer status;
	
	@Field("saveDate")
	@JsonProperty("saveDate")
	@XmlElement
	private Date saveDate;
	
	@Field("publishDate")
	@JsonProperty("publishDate")
	@XmlElement
	private Date publishDate;
	
	@Field("keywords")
	@JsonProperty("keywords")
	@JsonFormat(with = { com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY })
	@XmlElement
	private List<String> keywords;
	
	@Field("icon")
	@JsonProperty("icon")
	@XmlElement
	private String icon;
	
	@Field("xml")
	@JsonProperty("xml")
	@XmlElement
	private String xml;
	
	@Field("questions")
	@JsonProperty("questions")
	@JsonFormat(with = { com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY })
	@XmlElement
	private List<Question> questions;

	
	public String getTemplateId() {
		return templateId==null?null:templateId.toString();
	}

	public void setTemplateId(ObjectId templateId) {
		this.templateId = templateId;
	}

	public String getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getSaveDate() {
		return this.saveDate;
	}

	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}

	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public List<String> getKeywords() {
		return this.keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getXml() {
		return this.xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Template [templateId=" + templateId + ", authorId=" + authorId + ", templateType=" + templateType
				+ ", dataset=" + dataset + ", link=" + link + ", title=" + title + ", description=" + description
				+ ", status=" + status + ", saveDate=" + saveDate + ", publishDate=" + publishDate + ", keywords="
				+ keywords + ", icon=" + icon + ", xml=" + xml + ", questions=" + questions + "]";
	}
}
