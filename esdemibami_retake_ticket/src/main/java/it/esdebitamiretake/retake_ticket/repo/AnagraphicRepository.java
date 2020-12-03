package it.esdebitamiretake.retake_ticket.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.esdebitamiretake.retake_ticket.anagrafica.Anagraphic;


public abstract interface AnagraphicRepository extends MongoRepository<Anagraphic, String>{
	
	public abstract List<Anagraphic> findByAppName(String appName);

}