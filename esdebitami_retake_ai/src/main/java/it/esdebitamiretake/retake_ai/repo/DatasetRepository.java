package it.esdebitamiretake.retake_ai.repo;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import it.esdebitamiretake.retake_ai.collection.Dataset;



public abstract interface DatasetRepository extends MongoRepository<Dataset, String>,DatasetCustomRepository{
	  
	@Query("{ 'templateId' : ?0 }")
	List<Dataset> findDatasetByTemplateId( String templateId );
	

}
