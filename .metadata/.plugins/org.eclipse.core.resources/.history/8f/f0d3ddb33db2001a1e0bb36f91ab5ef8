package it.esdebitamiretake.retake_ticket.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import it.esdebitamiretake.retake_ticket.collection.ApplicationSettings;

public abstract interface ApplicationSettingsRepository extends MongoRepository<ApplicationSettings, String>{
  	
	@Query("{Id:'?0'}")
	public abstract ApplicationSettings findByName(String name);
	
}