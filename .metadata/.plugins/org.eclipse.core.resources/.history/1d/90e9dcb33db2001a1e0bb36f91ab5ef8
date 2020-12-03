package it.esdebitamiretake.retake_ticket.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import it.esdebitamiretake.retake_ticket.collection.Report;


public abstract interface ReportRepository extends MongoRepository<Report, String>{
  
  public abstract List<Report> findByActivityCodeAndApplication(Integer activityCode, String application);

  public abstract List<Report> findByApplication(String applicationName);
  
  public abstract List<Report> findByUserAndApplication(String userName, String applicationName);
  
  @Query("{ $and: [ { 'reportDate': {$gte: ?0 , $lt: ?1} } , { 'activityCode' : ?2 } , { 'templateId' : { $in: ?3 } } ] }")
  public abstract List<Report> findByDate(long startDate, long endDate, Integer code, List<String> templateIds);
  
  @Query("{ $and: [ { 'reportDate': {$gte: ?0 , $lt: ?1} } , { 'activityCode' : ?2 } , { 'templateId' : ?3 } ] }")
  public abstract List<Report> findByTemplate(long startDate, long endDate, Integer code, String id);
  
  @Query("{templateId:'?0'}")
  public abstract List<Report> findReportByTemplateId(String theTemplateId);
  
  @Query("{ $and: [ { 'reportDate': {$gte: ?0 , $lt: ?1} } , { 'activityCode' : ?2 } , { 'color' :  ?3  } ] }")
  public abstract List<Report> findReportsByDateAndColor(long startDate, long endDate, Integer code, Integer color);
}