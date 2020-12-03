package it.esdebitamiretake.retake_ir.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.esdebitamiretake.retale_ir.db.collection.Template;

@Repository
public interface TemplateRepository extends MongoRepository<Template, String> {

}