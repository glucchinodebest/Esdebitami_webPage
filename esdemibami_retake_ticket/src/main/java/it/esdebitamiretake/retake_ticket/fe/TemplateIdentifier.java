/*    */ package it.esdebitamiretake.retake_ticket.fe;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonProperty;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TemplateIdentifier
/*    */ {
/*    */   @JsonProperty("templateId")
/*    */   private String templateId;
/*    */   
/*    */   public TemplateIdentifier() {}
/*    */   
/*    */   public TemplateIdentifier(String templateId)
/*    */   {
/* 16 */     this.templateId = templateId;
/*    */   }
/*    */   
/*    */   public String getTemplateId() {
/* 20 */     return this.templateId;
/*    */   }
/*    */   
/*    */   public void setTemplateId(String templateId) {
/* 24 */     this.templateId = templateId;
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\fe\TemplateIdentifier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */