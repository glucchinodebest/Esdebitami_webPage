/*    */ package it.esdebitamiretake.retake_ticket.collection;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonInclude;
/*    */ import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

/*    */ import java.util.Date;
import java.util.List;

/*    */ import org.springframework.data.mongodb.core.mapping.Field;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JsonInclude(JsonInclude.Include.ALWAYS)
		 @ApiModel(description = "Model of the reply for the survey")
/*    */ public class Reply
/*    */ {
/*    */   @Field("sentDate")
/*    */   @JsonProperty("sentDate")
/*    */   Date sentDate;
/*    */   @Field("userEmail")
/*    */   @JsonProperty("userEmail")
/*    */   String userEmail;
/*    */   @Field("emailObject")
/*    */   @JsonProperty("emailObject")
/*    */   String emailObject;
/*    */   @Field("emailBody")
/*    */   @JsonProperty("emailBody")
/*    */   String emailBody;

		   @Field("attachement")
		   @JsonProperty("attachement")
		   List<Attachement> attachement;

/*    */   
/*    */   public Reply() {}
/*    */   
/*    */   public Reply(String userEmail, String emailObject, String emailBody)
/*    */   {
/* 35 */     this.userEmail = userEmail;
/* 36 */     this.emailObject = emailObject;
/* 37 */     this.emailBody = emailBody;
/*    */   }
/*    */   
/*    */   public Reply(Date sentDate, String userEmail, String emailObject, String emailBody)
/*    */   {
/* 42 */     this.sentDate = sentDate;
/* 43 */     this.userEmail = userEmail;
/* 44 */     this.emailObject = emailObject;
/* 45 */     this.emailBody = emailBody;
/*    */   }

		   public Reply(Date sentDate, String userEmail, String emailObject, String emailBody, List<Attachement> attachement)
		   {
			   this.sentDate = sentDate;
			   this.userEmail = userEmail;
			   this.emailObject = emailObject;
			   this.emailBody = emailBody;
			   this.attachement = attachement;
		   }

/*    */   
/*    */   public Date getSentDate() {
/* 49 */     return this.sentDate;
/*    */   }
/*    */   
/*    */   public void setSentDate(Date sentDate) {
/* 53 */     this.sentDate = sentDate;
/*    */   }
/*    */   
/*    */   public String getUserEmail() {
/* 57 */     return this.userEmail;
/*    */   }
/*    */   
/*    */   public void setUserEmail(String userEmail) {
/* 61 */     this.userEmail = userEmail;
/*    */   }
/*    */   
/*    */   public String getEmailObject() {
/* 65 */     return this.emailObject;
/*    */   }
/*    */   
/*    */   public void setEmailObject(String emailObject) {
/* 69 */     this.emailObject = emailObject;
/*    */   }
/*    */   
/*    */   public String getEmailBody() {
/* 73 */     return this.emailBody;
/*    */   }
/*    */   
/*    */   public void setEmailBody(String emailBody) {
/* 77 */     this.emailBody = emailBody;
/*    */   }


			public List<Attachement> getAttachement() {
				return attachement;
			}
			public void setAttachement(List<Attachement> attachement) {
				this.attachement = attachement;
			}
			
/*    */   
/*    */   public String toString()
/*    */   {
/* 82 */     StringBuilder builder = new StringBuilder();
/* 83 */     builder.append("Reply [sentDate=").append(this.sentDate).append(", userEmail=").append(this.userEmail)
/* 84 */       .append(", emailObject=").append(this.emailObject).append(", emailBody=").append(this.emailBody)
			   .append(", attachement=").append(this.attachement)
/* 85 */       .append("]");
/* 86 */     return builder.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\db\collection\Reply.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */