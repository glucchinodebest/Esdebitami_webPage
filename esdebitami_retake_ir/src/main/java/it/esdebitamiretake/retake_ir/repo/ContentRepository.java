package it.esdebitamiretake.retake_ir.repo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.esdebitamiretake.retale_ir.db.collection.Content;

@Repository
public interface ContentRepository extends MongoRepository<Content, String> {

	List<Content> findByResourceId(ObjectId resourceId);

	boolean existsByResourceId(ObjectId resourceId);
}
