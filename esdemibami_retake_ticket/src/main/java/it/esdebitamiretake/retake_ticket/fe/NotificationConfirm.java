/*    */ package it.esdebitamiretake.retake_ticket.fe;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonProperty;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NotificationConfirm
/*    */ {
/*    */   @JsonProperty("surveyId")
/*    */   String surveyId;
/*    */   @JsonProperty("userId")
/*    */   private String userId;
/*    */   
/*    */   public NotificationConfirm() {}
/*    */   
/*    */   public NotificationConfirm(String surveyId, String userId)
/*    */   {
/* 19 */     this.surveyId = surveyId;
/* 20 */     this.userId = userId;
/*    */   }
/*    */   
/*    */   public String getSurveyId() {
/* 24 */     return this.surveyId;
/*    */   }
/*    */   
/*    */   public void setSurveyId(String surveyId) {
/* 28 */     this.surveyId = surveyId;
/*    */   }
/*    */   
/*    */   public String getUserId() {
/* 32 */     return this.userId;
/*    */   }
/*    */   
/*    */   public void setUserId(String userId) {
/* 36 */     this.userId = userId;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 41 */     StringBuilder builder = new StringBuilder();
/* 42 */     builder.append("NotificationConfirm [surveyId=").append(this.surveyId).append(", userId=")
/* 43 */       .append(this.userId).append("]");
/* 44 */     return builder.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\fe\NotificationConfirm.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */