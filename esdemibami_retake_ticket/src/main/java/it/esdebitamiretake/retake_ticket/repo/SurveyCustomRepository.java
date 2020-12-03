package it.esdebitamiretake.retake_ticket.repo;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import it.esdebitamiretake.retake_ticket.collection.Survey;
import it.esdebitamiretake.retake_ticket.collection.Survey.Status;
import it.esdebitamiretake.retake_ticket.filter.SurveyFilter;

public abstract interface SurveyCustomRepository{
	
  public abstract List<Survey> findSurveys(SurveyFilter paramSurveyFilter, Pageable paramPageable);
  
  public abstract List<Survey> findSurveysFiltered(List<String> templateIds, String userId, LocalDate startDate, LocalDate endDate, Status status, Integer color, Integer page, Integer size, boolean sortDate, boolean sortStatus, boolean sortColor);
  
  public abstract int findSurveysUnordered(List<String> templateIds, String userId, LocalDate startDate, LocalDate endDate, Status status, Integer color);
  
  public abstract int findSurveysUnorderedByColor(List<String> templateIds, String userId, LocalDate startDate, LocalDate endDate, String color);
  
  public abstract List<Survey> findSurveysFilterByOrderedStatus(List<String> templateIds, String userId, LocalDate startDate, LocalDate endDate, Status status, String color, Integer page, Integer size, boolean sortDate);
  
  public abstract List<Survey> findSurveysFilterByColor(List<String> templateIds, String userId, LocalDate startDate, LocalDate endDate, String color, Integer page, Integer size, boolean sortDate);

}