package it.esdebitamiretake.retake_ir.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.esdebitamiretake.retale_ir.db.collection.Context;


@Repository
public interface ContextRepository extends MongoRepository<Context, String> {

    Page<Context> findByTemplateId(String templateId, Pageable pageable);
    
    List<Context> findByTemplateId(String templateId);
}
