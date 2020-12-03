package it.esdebitamiretake.retake_ticket.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;

import it.esdebitamiretake.retake_ticket.collection.TemplateType;

public abstract interface TemplateTypeCustomRepository{
	
  public abstract List<TemplateType> findTemplateType(Integer status, Pageable paramPageable);
  
}