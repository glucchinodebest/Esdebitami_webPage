/*    */ package it.esdebitamiretake.retake_ticket.collection;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonFormat;
/*    */ import com.fasterxml.jackson.annotation.JsonInclude;
/*    */ import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

/*    */ import java.util.List;
/*    */ import org.springframework.data.mongodb.core.mapping.Field;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JsonInclude(JsonInclude.Include.ALWAYS)
		 @ApiModel(description = "Model of the answer for the interaction")
/*    */ public class Answer
/*    */ {
/*    */   @Field("questionId")
/*    */   @JsonProperty("questionId")
/*    */   private Integer questionId;
/*    */   @Field("questionText")
/*    */   @JsonProperty("questionText")
/*    */   private String questionText;
/*    */   @Field("answerText")
/*    */   @JsonProperty("answerText")
/*    */   private String answerText;
/*    */   @Field("wrongAnswers")
/*    */   @JsonProperty("wrongAnswers")
/*    */   @JsonFormat(with={com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY})
/*    */   private List<String> wrongAnswers;

		   @Field("attachement")	
		   @JsonProperty("attachement")
		   private List<Attachement> attachement;

/*    */   
/*    */   public Answer() {}
/*    */   
/*    */   public Answer(Integer questionId, String questionText, String answerText, List<String> wrongAnswers)
/*    */   {
/* 39 */     this.questionId = questionId;
/* 40 */     this.questionText = questionText;
/* 41 */     this.answerText = answerText;
/* 42 */     this.wrongAnswers = wrongAnswers;
/*    */   }
/*    */   
/*    */   public Answer(Integer questionId, String questionText, String answerText)
/*    */   {
/* 47 */     this.questionId = questionId;
/* 48 */     this.questionText = questionText;
/* 49 */     this.answerText = answerText;
/*    */   }
/*    */   
/*    */   public Integer getQuestionId() {
/* 53 */     return this.questionId;
/*    */   }
/*    */   
/*    */   public void setQuestionId(Integer questionId) {
/* 57 */     this.questionId = questionId;
/*    */   }
/*    */   
/*    */   public String getQuestionText() {
/* 61 */     return this.questionText;
/*    */   }
/*    */   
/*    */   public void setQuestionText(String questionText) {
/* 65 */     this.questionText = questionText;
/*    */   }
/*    */   
/*    */   public String getAnswerText() {
/* 69 */     return this.answerText;
/*    */   }
/*    */   
/*    */   public void setAnswerText(String answerText) {
/* 73 */     this.answerText = answerText;
/*    */   }
/*    */   
/*    */   public List<String> getWrongAnswers() {
/* 77 */     return this.wrongAnswers;
/*    */   }
/*    */   
/*    */   public void setWrongAnswers(List<String> wrongAnswers) {
/* 81 */     this.wrongAnswers = wrongAnswers;
/*    */   }

			public List<Attachement> getAttachement() {
				return attachement;
			}
			public void setAttachement(List<Attachement> attachement) {
				this.attachement = attachement;
			}

/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\db\collection\Answer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */