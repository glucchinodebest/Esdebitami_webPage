/*    */ package it.esdebitamiretake.retake_ticket.config;
/*    */ 
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ 
/*    */ @Configuration
/*    */ public class GlobalProperties
/*    */ {
/*    */   @Value("${spring.data.mongodb.uri}")
/*    */   private String springDataMongodbUri;
/*    */   @Value("${spring.mail.sender}")
/*    */   private String springMailSender;
/*    */   
/*    */   public String getSpringDataMongodbUri()
/*    */   {
/* 16 */     return this.springDataMongodbUri;
/*    */   }
/*    */   
/*    */   public void setSpringDataMongodbUri(String springDataMongodbUri) {
/* 20 */     this.springDataMongodbUri = springDataMongodbUri;
/*    */   }
/*    */   
/*    */   public String getSpringMailSender() {
/* 24 */     return this.springMailSender;
/*    */   }
/*    */   
/*    */   public void setSpringMailSender(String springMailSender) {
/* 28 */     this.springMailSender = springMailSender;
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\config\GlobalProperties.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */