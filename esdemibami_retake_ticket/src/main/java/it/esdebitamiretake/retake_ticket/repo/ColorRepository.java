package it.esdebitamiretake.retake_ticket.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import it.esdebitamiretake.retake_ticket.collection.Color;


public abstract interface ColorRepository extends MongoRepository<Color, String>{
	
	public abstract Color findByColorName(String colorName);
	
	public abstract List<Color> findByAppName(String appName);
	
	@Query("{ id : {$eq: ?0}}")
	public abstract Color findColorById(String id);
	
	public abstract Color findByAppNameAndColorCode(String appName, Integer colorCode);

	public abstract Color findByColorCode(Integer colorCode);

}