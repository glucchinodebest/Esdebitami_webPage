package it.esdebitamiretake.retake_ai.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class DatasetRepositoryImpl implements DatasetCustomRepository{
	
	@Autowired
	MongoTemplate mongoTemplate;

}
