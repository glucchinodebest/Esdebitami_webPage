package it.esdebitamiretake.retake_ai.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import it.esdebitamiretake.retake_ai.collection.Context;


	public abstract interface ContextRepository extends MongoRepository<Context, String>,DatasetCustomRepository{
		
		@Query("{ 'template' : ?0 }")
		Context findContexByTemplateId( String templateId );
	}


