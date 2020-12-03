/*    */ package it.esdebitamiretake.retake_ticket.fe;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonProperty;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SurveyInfo
/*    */ {
/*    */   @JsonProperty("templateId")
/*    */   private String templateId;
/*    */   @JsonProperty("title")
/*    */   private String title;
/*    */   @JsonProperty("description")
/*    */   private String description;
/*    */   @JsonProperty("icon")
/*    */   private String icon;
/*    */   @JsonProperty("status")
/*    */   public Integer status;
/*    */   @JsonProperty("surveysCount")
/*    */   public Integer surveysCount;
/*    */   
/*    */   public SurveyInfo() {}
/*    */   
/*    */   public SurveyInfo(String templateId, String title, String description, String icon, Integer status, Integer surveysCount)
/*    */   {
/* 32 */     this.templateId = templateId;
/* 33 */     this.title = title;
/* 34 */     this.description = description;
/* 35 */     this.icon = icon;
/* 36 */     this.status = status;
/* 37 */     this.surveysCount = surveysCount;
/*    */   }
/*    */   
/*    */   public String getTemplateId() {
/* 41 */     return this.templateId;
/*    */   }
/*    */   
/*    */   public void setTemplateId(String templateId) {
/* 45 */     this.templateId = templateId;
/*    */   }
/*    */   
/*    */   public String getTitle() {
/* 49 */     return this.title;
/*    */   }
/*    */   
/*    */   public void setTitle(String title) {
/* 53 */     this.title = title;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 57 */     return this.description;
/*    */   }
/*    */   
/*    */   public void setDescription(String description) {
/* 61 */     this.description = description;
/*    */   }
/*    */   
/*    */   public String getIcon() {
/* 65 */     return this.icon;
/*    */   }
/*    */   
/*    */   public void setIcon(String icon) {
/* 69 */     this.icon = icon;
/*    */   }
/*    */   
/*    */   public Integer getStatus() {
/* 73 */     return this.status;
/*    */   }
/*    */   
/*    */   public void setStatus(Integer status) {
/* 77 */     this.status = status;
/*    */   }
/*    */   
/*    */   public Integer getSurveysCount() {
/* 81 */     return this.surveysCount;
/*    */   }
/*    */   
/*    */   public void setSurveysCount(Integer surveysCount) {
/* 85 */     this.surveysCount = surveysCount;
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\fe\SurveyInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */