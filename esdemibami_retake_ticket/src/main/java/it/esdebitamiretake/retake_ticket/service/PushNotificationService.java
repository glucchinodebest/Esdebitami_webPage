/*    */ package it.esdebitamiretake.retake_ticket.service;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;

/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ticket.collection.Survey;
import it.esdebitamiretake.retake_ticket.exception.UserTokenFcmNotFoundException;
import it.esdebitamiretake.retake_ticket.fe.ReplyInfo;
import it.esdebitamiretake.retake_ticket.model.Notification;
import it.esdebitamiretake.retake_ticket.model.UserToken;
import it.esdebitamiretake.retake_ticket.repo.PushTokenRepository;
import it.esdebitamiretake.retake_ticket.repo.SurveyRepository;
import it.esdebitamiretake.retake_ticket.thread.NotificationSender;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class PushNotificationService
/*    */ {
/*    */   @Value("${pushNotification.service.url}")
/*    */   String pushNotificationServiceUrl;
/*    */   @Value("${pushNotification.invalidTokenFCM}")
/*    */   String invalidTokenFCM;
/*    */   @Autowired
/*    */   SurveyRepository surveyRepository;
/*    */   @Autowired
/*    */   PushTokenRepository pushTokenRepository;
/* 40 */   private static Logger logger = LoggerFactory.getLogger("com.pccube.vvas.template");
/*    */   
/*    */   public void push(ReplyInfo theReplyInfo) throws UserTokenFcmNotFoundException {
/* 43 */     Survey survey = this.surveyRepository.findBySurveyId(theReplyInfo.getSurveyId());
/* 44 */     String userid = survey.getUserId();
/*    */     
/* 46 */     UserToken userToken = this.pushTokenRepository.findByUsername(userid);
/* 47 */     List<String> destFcmTokens = new ArrayList();
/* 48 */     if (existsTokenFCM(userToken))
/*    */     {
/* 50 */       destFcmTokens.add(userToken.getTokenFCM());
/*    */       
/* 52 */       Notification notification = new Notification();
/* 53 */       notification.setPriority("high");
/* 54 */       notification.setTokens(destFcmTokens);
/* 55 */       Map<String, String> data = new HashMap();
/* 56 */       data.put("pushcode", "2");
/* 57 */       data.put("title", theReplyInfo.getReply().getEmailObject());
/* 58 */       data.put("body", theReplyInfo.getReply().getEmailBody());
/* 59 */       data.put("survey_id", theReplyInfo.getSurveyId());
/* 60 */       data.put("created", new Date().toString());
/* 61 */       notification.setDatas(data);
/*    */       
/* 63 */       NotificationSender messageThread = new NotificationSender(notification, this.pushNotificationServiceUrl);
/*    */       
/* 65 */       messageThread.start();
/*    */     }
/*    */     else {
/* 68 */       logger.warn("FCM Token non disponibile per l'utente " + userid);
/* 69 */       throw new UserTokenFcmNotFoundException();
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean existsTokenFCM(UserToken theUserToken) {
/* 74 */     return (theUserToken != null) && (StringUtils.isNotBlank(theUserToken.getTokenFCM())) && 
/* 75 */       (!StringUtils.equals(theUserToken.getTokenFCM(), this.invalidTokenFCM));
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\service\PushNotificationService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */