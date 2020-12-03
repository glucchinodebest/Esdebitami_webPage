/*    */ package it.esdebitamiretake.retake_ticket.fe;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonProperty;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SettingsIdentifier
/*    */ {
/*    */   @JsonProperty("settingsId")
/*    */   private String settingsId;
/*    */   
/*    */   public SettingsIdentifier() {}
/*    */   
/*    */   public SettingsIdentifier(String settingsId)
/*    */   {
/* 16 */     this.settingsId = settingsId;
/*    */   }
/*    */   
/*    */   public String getSettingsId() {
/* 20 */     return this.settingsId;
/*    */   }
/*    */   
/*    */   public void setTemplateId(String settingsId) {
/* 24 */     this.settingsId = settingsId;
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\fe\TemplateIdentifier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */