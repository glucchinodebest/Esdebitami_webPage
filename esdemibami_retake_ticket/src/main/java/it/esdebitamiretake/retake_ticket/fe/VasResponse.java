/*    */ package it.esdebitamiretake.retake_ticket.fe;
/*    */ 
/*    */ 
/*    */ public class VasResponse
/*    */ {
/*    */   private Integer statusCode;
/*    */   
/*    */   private String statusMessage;
/*    */   private Object content;
/*    */   
/*    */   public VasResponse() {}
/*    */   
/*    */   public VasResponse(Integer statusCode, String statusMessage, Object content)
/*    */   {
/* 15 */     this.statusCode = statusCode;
/* 16 */     this.statusMessage = statusMessage;
/* 17 */     this.content = content;
/*    */   }
/*    */   
/*    */   public Integer getStatusCode() {
/* 21 */     return this.statusCode;
/*    */   }
/*    */   
/*    */   public void setStatusCode(Integer statusCode) {
/* 25 */     this.statusCode = statusCode;
/*    */   }
/*    */   
/*    */   public String getStatusMessage() {
/* 29 */     return this.statusMessage;
/*    */   }
/*    */   
/*    */   public void setStatusMessage(String statusMessage) {
/* 33 */     this.statusMessage = statusMessage;
/*    */   }
/*    */   
/*    */   public Object getContent() {
/* 37 */     return this.content;
/*    */   }
/*    */   
/*    */   public void setContent(Object content) {
/* 41 */     this.content = content;
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\fe\VasResponse.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */