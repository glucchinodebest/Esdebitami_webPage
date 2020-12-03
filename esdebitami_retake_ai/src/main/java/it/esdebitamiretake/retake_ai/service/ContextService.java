package it.esdebitamiretake.retake_ai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ai.collection.Context;
import it.esdebitamiretake.retake_ai.repo.ContextRepository;


@Service
public class ContextService {

	@Autowired
	ContextRepository contextRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	private static final Logger logger = LoggerFactory.getLogger( DatasetService.class );
	
	
	
	public Context findContextByTemplateId( String templateId ) {
		logger.info("[START] findContextByTemplateId");
		Context context = this.contextRepository.findContexByTemplateId(templateId);
		logger.info("[END] findContextByTemplateId");
		return context;
	}
	
	
}
