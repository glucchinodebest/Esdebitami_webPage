/*    */ package it.esdebitamiretake.retake_ticket.service;
/*    */ 
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.mail.SimpleMailMessage;
/*    */ import org.springframework.mail.javamail.JavaMailSender;
/*    */ import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ticket.config.GlobalProperties;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class MailService
/*    */ {
/*    */   @Autowired
/*    */   public JavaMailSender emailSender;
/*    */   @Autowired
/*    */   public GlobalProperties globalProperties;
/*    */   
/*    */   public void sendSimpleMessage(String to, String subject, String text)
/*    */   {
/* 21 */     SimpleMailMessage message = new SimpleMailMessage();
/* 22 */     message.setTo(to);
/* 23 */     message.setSubject(subject);
/* 24 */     message.setText(text);
/* 25 */     message.setFrom(this.globalProperties.getSpringMailSender());
/* 26 */     this.emailSender.send(message);
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\service\MailService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */