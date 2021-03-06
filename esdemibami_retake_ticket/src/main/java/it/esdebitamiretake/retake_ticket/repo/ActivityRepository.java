package it.esdebitamiretake.retake_ticket.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.esdebitamiretake.retake_ticket.collection.Activity;

public abstract interface ActivityRepository extends MongoRepository<Activity, String>{
	
	public abstract Activity findByName(String name);
	
	public abstract Activity findByActivityCode(Integer activityCode);

}