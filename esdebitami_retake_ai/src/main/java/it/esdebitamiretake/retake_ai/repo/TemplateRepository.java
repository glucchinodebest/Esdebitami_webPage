package it.esdebitamiretake.retake_ai.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import it.esdebitamiretake.retake_ai.collection.Template;


public abstract interface TemplateRepository
  extends MongoRepository<Template, String>, TemplateCustomRepository
{
  public abstract Page<Template> findAll(Pageable paramPageable);
  
  public abstract Template findByTemplateId(String paramString);
  
  public abstract List<Template> findByTemplateIdIn(List<String> paramList);
  
  public abstract List<Template> findByStatus(Integer paramInteger);
  
  public abstract String findTitleByTemplateId(String paramString);
  
  
  
  	@Query("{status: {$eq: ?0}}")
	List<Template> findActiveTemplate(Integer i);
	

  	
    @Query(value = "{ $and: [ {_id: { $in: ?0 } }, { 'status' : ?1  } ] }" )
    public abstract List<Template> findByIdsAndStatus(Set<String> idsList, Integer statusTemp);
	
    
    
    
}
