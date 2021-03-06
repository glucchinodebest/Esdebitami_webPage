/*    */ package it.esdebitamiretake.retake_ticket.fe;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonProperty;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TemplateInfo
/*    */ {
/*    */   @JsonProperty("templateId")
/*    */   private String templateId;
/*    */   @JsonProperty("title")
/*    */   private String title;
/*    */   
/*    */   public TemplateInfo() {}
/*    */   
/*    */   public TemplateInfo(String templateId, String title)
/*    */   {
/* 19 */     this.templateId = templateId;
/* 20 */     this.title = title;
/*    */   }
/*    */   
/*    */   public String getTemplateId() {
/* 24 */     return this.templateId;
/*    */   }
/*    */   
/*    */   public void setTemplateId(String templateId) {
/* 28 */     this.templateId = templateId;
/*    */   }
/*    */   
/*    */   public String getTitle() {
/* 32 */     return this.title;
/*    */   }
/*    */   
/*    */   public void setTitle(String title) {
/* 36 */     this.title = title;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 41 */     StringBuilder builder = new StringBuilder();
/* 42 */     builder.append("TemplateInfo [templateId=").append(this.templateId).append(", title=").append(this.title)
/* 43 */       .append("]");
/* 44 */     return builder.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\fe\TemplateInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */