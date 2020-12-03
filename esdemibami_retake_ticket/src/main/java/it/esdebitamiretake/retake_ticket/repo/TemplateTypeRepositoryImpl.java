package it.esdebitamiretake.retake_ticket.repo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import it.esdebitamiretake.retake_ticket.collection.TemplateType;


public class TemplateTypeRepositoryImpl implements TemplateTypeCustomRepository {

	private static Logger logger = LoggerFactory.getLogger("com.pccube.vvas.template");
	
	@Autowired
	MongoTemplate mongoTemplate;

	public List<TemplateType> findTemplateType(Integer status, Pageable pageable) {

		logger.debug("JSON INPUT Repo Status" + status);
		
		Aggregation aggregation;

		List<Criteria> criterias = new ArrayList<>();
		
		if (status != null) 
			criterias.add(Criteria.where("status").is(status));

		Criteria criteria=new Criteria();
		
		if(!criterias.isEmpty())
			criteria=criteria.andOperator(criterias.toArray(new Criteria[criterias.size()]));
					
		aggregation = Aggregation.newAggregation(new AggregationOperation[] { Aggregation.match(criteria), Aggregation.sort(Sort.by(Sort.Direction.ASC,  "templateTypeName")), Aggregation.skip((long)pageable.getPageNumber() * pageable.getPageSize()), Aggregation.limit(pageable.getPageSize()) });

		return this.mongoTemplate.aggregate(aggregation, TemplateType.class, TemplateType.class).getMappedResults();
	}

}
