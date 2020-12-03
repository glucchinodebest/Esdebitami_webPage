package it.esdebitamiretake.retake_ticket.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "surveys")
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "Structure of the survey, save the interaction of the user on a specific template")
public class Survey {
	
	@Id
	@Field("_id")
	@JsonProperty("surveyId")
	private Object surveyId;
	
	@Field("userId")
	@JsonProperty("userId")
	@NotNull
	@NotEmpty
	private String userId;
	
	@Field("templateId")
	@JsonProperty("templateId")
	@NotNull
	@NotEmpty
	private String templateId;
	
	@Field("surveyDate")
	@JsonProperty("surveyDate")
	private Date surveyDate;
	
	@Field("answers")
	@JsonProperty("answers")
	private List<Answer> answers;
	
	@Field("feedback")
	@JsonProperty("feedback")
	private String feedback;
	
	@Field("replies")
	@JsonProperty("replies")
	private List<Reply> replies;
	
	@Field("showNotification")
	@JsonProperty("showNotification")
	private Boolean showNotification;
	
	@Transient
	@JsonProperty("templateTitle")
	private String templateTitle;

	public enum Status {
		OPEN, CLOSE
	};
	
	@Field("color")
	@JsonProperty("color")
	private Integer color;
	
	@Field("colorObj")
	@JsonProperty("colorObj")
	private Color colorObj;
	
	@Field("counterWeight")
	@JsonProperty("counterWeight")
	private Integer counterWeight;
	
	@Field("counterScore")
	@JsonProperty("counterScore")
	private Integer counterScore;
	
	@Field("average")
	@JsonProperty("average")
	private Integer average;

	@Field("status")
	@JsonProperty("status")
	private Status status;
	
	@Field("attachementFile")
	@JsonProperty("attachementFile")
	private Attachement attachementFile;

	public Survey(String surveyId, String userId, String templateId, Date surveyDate, List<Answer> answers,
			String feedback, List<Reply> replies, Boolean showNotification, String templateTitle, Status status) {
		this.surveyId = surveyId;
		this.userId = userId;
		this.templateId = templateId;
		this.surveyDate = surveyDate;
		this.answers = answers;
		this.feedback = feedback;
		this.replies = replies;
		this.showNotification = showNotification;
		this.templateTitle = templateTitle;
		this.status = status;
	}

	public Survey() {
	}

	public Survey(String surveyId, String userId, String templateId, Date surveyDate, List<Answer> answers,
			String feedback, List<Reply> replies, Boolean showNotification) {
		this.surveyId = surveyId;
		this.userId = userId;
		this.templateId = templateId;
		this.surveyDate = surveyDate;
		this.answers = answers;
		this.feedback = feedback;
		this.replies = replies;
		this.showNotification = showNotification;

	}

	public Survey(Object surveyId, @NotNull @NotEmpty String userId, @NotNull @NotEmpty String templateId,
			Date surveyDate, List<Answer> answers, String feedback, List<Reply> replies, Boolean showNotification,
			String templateTitle, Integer color, Color colorObj, Integer counterWeight, Integer counterScore,
			Integer average, Status status) {
		this.surveyId = surveyId;
		this.userId = userId;
		this.templateId = templateId;
		this.surveyDate = surveyDate;
		this.answers = answers;
		this.feedback = feedback;
		this.replies = replies;
		this.showNotification = showNotification;
		this.templateTitle = templateTitle;
		this.color = color;
		this.colorObj = colorObj;
		this.counterWeight = counterWeight;
		this.counterScore = counterScore;
		this.average = average;
		this.status = status;
	}

	public Survey(Object surveyId, @NotNull @NotEmpty String userId, @NotNull @NotEmpty String templateId,
			Date surveyDate, List<Answer> answers, String feedback, List<Reply> replies, Boolean showNotification,
			String templateTitle, Integer color, Color colorObj, Integer counterWeight, Integer counterScore,
			Integer average, Status status, Attachement attachementFile) {
		this.surveyId = surveyId;
		this.userId = userId;
		this.templateId = templateId;
		this.surveyDate = surveyDate;
		this.answers = answers;
		this.feedback = feedback;
		this.replies = replies;
		this.showNotification = showNotification;
		this.templateTitle = templateTitle;
		this.color = color;
		this.colorObj = colorObj;
		this.counterWeight = counterWeight;
		this.counterScore = counterScore;
		this.average = average;
		this.status = status;
		this.attachementFile = attachementFile;
	}

	public String getSurveyId() {
		return surveyId==null?null:surveyId.toString();
	}

	public void setSurveyId(Object surveyId) {
		this.surveyId = surveyId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateTitle() {
		return this.templateTitle;
	}

	public void setTemplateTitle(String templateTitle) {
		this.templateTitle = templateTitle;
	}

	public Date getSurveyDate() {
		return this.surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public List<Reply> getReplies() {
		return this.replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	public Boolean getShowNotification() {
		return this.showNotification;
	}

	public void setShowNotification(Boolean showNotification) {
		this.showNotification = showNotification;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public Integer getCounterWeight() {
		return counterWeight;
	}

	public void setCounterWeight(Integer counterWeight) {
		this.counterWeight = counterWeight;
	}

	public Integer getCounterScore() {
		return counterScore;
	}

	public void setCounterScore(Integer counterScore) {
		this.counterScore = counterScore;
	}

	public Integer getAverage() {
		return average;
	}

	public void setAverage(Integer average) {
		this.average = average;
	}

	public Color getColorObj() {
		return colorObj;
	}

	public void setColorObj(Color colorObj) {
		this.colorObj = colorObj;
	}

	public Attachement getAttachementFile() {
		return attachementFile;
	}

	public void setAttachementFile(Attachement attachementFile) {
		this.attachementFile = attachementFile;
	}

	@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", userId=" + userId + ", templateId=" + templateId + ", surveyDate="
				+ surveyDate + ", answers=" + answers + ", feedback=" + feedback + ", replies=" + replies
				+ ", showNotification=" + showNotification + ", templateTitle=" + templateTitle + ", status=" + status
				+ "attachementFile= " + attachementFile  + "]";
	}
}