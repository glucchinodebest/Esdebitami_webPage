package it.esdebitamiretake.retake_ir.repo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.esdebitamiretake.retale_ir.db.collection.Resource;

@Repository
public interface ResourceRepository extends MongoRepository<Resource, String> {

	List<Resource> findByContextId(ObjectId contextId);

	Resource findByUri(String uri);

}