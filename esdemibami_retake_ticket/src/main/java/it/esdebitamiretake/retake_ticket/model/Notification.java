/*    */ package it.esdebitamiretake.retake_ticket.model;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class Notification
/*    */ {
/*    */   private String title;
/*    */   private String priority;
/*    */   private String body;
/*    */   private List<String> tokens;
/*    */   private Map<String, String> datas;
/*    */   
/*    */   public String getTitle()
/*    */   {
/* 16 */     return this.title;
/*    */   }
/*    */   
/* 19 */   public void setTitle(String title) { this.title = title; }
/*    */   
/*    */   public String getPriority() {
/* 22 */     return this.priority;
/*    */   }
/*    */   
/* 25 */   public void setPriority(String priority) { this.priority = priority; }
/*    */   
/*    */   public String getBody() {
/* 28 */     return this.body;
/*    */   }
/*    */   
/* 31 */   public void setBody(String body) { this.body = body; }
/*    */   
/*    */   public List<String> getTokens() {
/* 34 */     return this.tokens;
/*    */   }
/*    */   
/* 37 */   public void setTokens(List<String> tokens) { this.tokens = tokens; }
/*    */   
/*    */   public Map<String, String> getDatas() {
/* 40 */     return this.datas;
/*    */   }
/*    */   
/* 43 */   public void setDatas(Map<String, String> datas) { this.datas = datas; }
/*    */   
/*    */   public String toString()
/*    */   {
/* 47 */     return "NotificationToSend [title=" + this.title + ", priority=" + this.priority + ", body=" + this.body + ", tokens=" + this.tokens + ", datas=" + this.datas + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\model\Notification.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */