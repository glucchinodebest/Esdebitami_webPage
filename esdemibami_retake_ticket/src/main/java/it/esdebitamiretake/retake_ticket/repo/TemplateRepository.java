package it.esdebitamiretake.retake_ticket.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import it.esdebitamiretake.retake_ticket.collection.Template;

public abstract interface TemplateRepository
  extends MongoRepository<Template, String>, TemplateCustomRepository
{
	
  @Query(value = "{}", fields = "{ 'dataset' : 0 }")
  public abstract Page<Template> findAll(Pageable paramPageable);
  
  @Query(value = "{ $and: [ {_id: { $in: ?0 } }, { 'status' : ?1  } ] }", fields = "{ 'dataset' : 0 }")
  public abstract List<Template> findByIdsAndStatus(Set<String> idsList, Integer statusTemp);
  
  @Query(value = "{ _id: { $in: ?0 } } ", fields = "{ 'dataset' : 0 }")
  public abstract List<Template> findByIds(Set<String> idsList);
  
  @Query(value = "{ $and: [ {_id: { $in: ?0 } }, { 'status' : ?1  } ] }", fields = "{ 'dataset' : 0, 'saveDate': 0 , 'publishDate': 0 , 'keywords': 0 , 'xml': 0, 'questions':0 }")
  public abstract List<Template> findInfoByIdsAndStatus(Set<String> idsList, Integer statusTemp);
  
  @Query(value = "{ _id: { $in: ?0 } } ", fields = "{ 'dataset' : 0, 'saveDate': 0 , 'publishDate': 0 , 'keywords': 0 , 'xml': 0, 'questions':0}")
  public abstract List<Template> findInfoByIds(Set<String> idsList);
  
  @Query(value = "{ _id: '?0' } } ", fields = "{ 'dataset' : 0, 'saveDate': 0 , 'publishDate': 0 , 'keywords': 0 , 'xml': 0, 'questions':0}")
  public abstract Template findTitleById(String id);
  
  public abstract Template findByTemplateId(String paramString);
  
  public abstract List<Template> findByTemplateIdIn(List<String> paramList);
  
  public abstract List<Template> findByStatus(Integer paramInteger);
  
  public abstract String findTitleByTemplateId(String paramString);
}



/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\db\repo\TemplateRepository.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */