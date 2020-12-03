package it.esdebitamiretake.retake_ai.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.esdebitamiretake.retake_ai.collection.Group;



public abstract interface GroupRepository extends MongoRepository<Group, String>{
	
	Group findByNameAndApplication(String name,String application);
}
