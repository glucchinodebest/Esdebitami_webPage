/*    */ package it.esdebitamiretake.retake_ticket.thread;
/*    */ 
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.web.client.RestTemplate;

import it.esdebitamiretake.retake_ticket.model.Notification;
/*    */ 
/*    */ public class NotificationSender
/*    */   extends Thread
/*    */ {
/*    */   private Notification notification;
/*    */   private String pushNotificationServiceUrl;
/* 14 */   private static Logger logger = LoggerFactory.getLogger("com.pccube.vvas.template");
/*    */   
/*    */   public NotificationSender(Notification notification, String pushNotificationServiceUrl)
/*    */   {
/* 18 */     this.notification = notification;
/* 19 */     this.pushNotificationServiceUrl = pushNotificationServiceUrl;
/*    */   }
/*    */   
/*    */   public void run()
/*    */   {
/* 24 */     logger.debug("pushNotificationServiceUrl=" + this.pushNotificationServiceUrl);
/* 25 */     RestTemplate restTemplate = new RestTemplate();
/*    */     
/* 27 */     String result = (String)restTemplate.postForEntity(this.pushNotificationServiceUrl, this.notification, String.class, new Object[0]).getBody();
/* 28 */     logger.info("pushNotificationService Result=" + result);
/*    */   }
/*    */   
/*    */   public Notification getNotification() {
/* 32 */     return this.notification;
/*    */   }
/*    */   
/*    */   public void setNotification(Notification notification) {
/* 36 */     this.notification = notification;
/*    */   }
/*    */   
/*    */   public String getPushNotificationServiceUrl() {
/* 40 */     return this.pushNotificationServiceUrl;
/*    */   }
/*    */   
/*    */   public void setPushNotificationServiceUrl(String pushNotificationServiceUrl) {
/* 44 */     this.pushNotificationServiceUrl = pushNotificationServiceUrl;
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\thread\NotificationSender.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */