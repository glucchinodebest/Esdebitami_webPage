/*    */ package it.esdebitamiretake.retake_ticket.model;
/*    */ 
/*    */ import org.springframework.data.annotation.Id;
/*    */ 
/*    */ public class UserToken
/*    */ {
/*    */   @Id
/*    */   private String id;
/*    */   private String username;
/*    */   private String tokenFCM;
/*    */   
/*    */   public String getUsername() {
/* 13 */     return this.username;
/*    */   }
/*    */   
/* 16 */   public void setUsername(String username) { this.username = username; }
/*    */   
/*    */   public String getTokenFCM() {
/* 19 */     return this.tokenFCM;
/*    */   }
/*    */   
/* 22 */   public void setTokenFCM(String tokenFCM) { this.tokenFCM = tokenFCM; }
/*    */   
/*    */   public String getId() {
/* 25 */     return this.id;
/*    */   }
/*    */   
/* 28 */   public void setId(String id) { this.id = id; }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\model\UserToken.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */