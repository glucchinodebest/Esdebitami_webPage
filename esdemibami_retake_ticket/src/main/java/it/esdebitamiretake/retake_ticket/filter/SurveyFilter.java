package it.esdebitamiretake.retake_ticket.filter;

import java.time.LocalDate;

import it.esdebitamiretake.retake_ticket.collection.Survey.Status;


public class SurveyFilter {
	
	private String templateId;
	private String userId;
	private LocalDate startDate;
	private LocalDate endDate;
	private Status status;

	public SurveyFilter() {

	}

	public SurveyFilter(String templateId) {
		
		this(templateId,null);
	}

	public SurveyFilter(String templateId, String userId) {
		
		this(templateId,userId,null);
	}
	
	public SurveyFilter(String templateId, String userId, LocalDate startDate) {

		this(templateId,userId,startDate,null);
	}
	
	public SurveyFilter(String templateId, String userId, LocalDate startDate, LocalDate endDate) {

		this(templateId,userId,startDate,endDate,null);
	}
	
	public SurveyFilter(String templateId, String userId, LocalDate startDate, LocalDate endDate, Status status) {

		this.templateId = templateId;
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public String getTemplateId() {

		return this.templateId;
	}

	public void setTemplateId(String templateId) {
		
		this.templateId = templateId;
	}

	public String getUserId() {
		
		return this.userId;
	}

	public void setUserId(String userId) {
		
		this.userId = userId;
	}

	public LocalDate getStartDate() {
		
		return this.startDate;
	}

	public LocalDate getEndDate() {
	
		return this.endDate;
	}
	
	public Status getStatus() {
		
		return status;
	}
	
	public void setStartDate(LocalDate startDate) {
		
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		
		this.endDate = endDate;
	}

	public void setStatus(Status status) {
		
		this.status = status;
	}

	public String toString() {
		
		return String.format("SurveyFilter [templateId=%s, userId=%s, startDate=%s, endDate=%s]",this.getTemplateId(),this.getUserId(),this.getStartDate(),this.getEndDate());
	}
}