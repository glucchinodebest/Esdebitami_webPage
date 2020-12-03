package it.esdebitamiretake.retake_ticket.repo;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import it.esdebitamiretake.retake_ticket.anagrafica.ProfileUser;



public class UserRepositoryImpl implements UserCustomRepository {

	//private static Logger logger = LoggerFactory.getLogger("com.pccube.vvas.template");
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	public List<ProfileUser> findUsername(List<String> templateIds, String name, String surname, String email, String userId) {
		
		Aggregation aggregation;
		
		List<Criteria> criterias = new ArrayList<>();
		
		//templateIds has to be null
		if(!templateIds.isEmpty() || templateIds == null)
			criterias.add(Criteria.where("templates").in(templateIds)); // verify the name of the field templateIds in users collection
		
		if (StringUtils.isNotBlank(name)) 
			criterias.add(Criteria.where("name").is(name));
		
		if (StringUtils.isNotBlank(surname)) 
			criterias.add( Criteria.where("surname").is(surname));
		
		if (StringUtils.isNotBlank(email)) 
			criterias.add(Criteria.where("email").is(email));
		
		if (StringUtils.isNotBlank(userId)) 
			criterias.add(Criteria.where("_id").is(userId));

		Criteria criteria=new Criteria();
		
		if(!criterias.isEmpty())
			criteria=criteria.andOperator(criterias.toArray(new Criteria[criterias.size()]));
		
		aggregation = Aggregation.newAggregation(new AggregationOperation[] { Aggregation.match(criteria) }); 
		
		return this.mongoTemplate.aggregate(aggregation, ProfileUser.class, ProfileUser.class).getMappedResults();
		
		
	}
	
	public List<ProfileUser> findUsernameNew(List<String> templateIds, String name, String surname, String email, String userId, Integer page, Integer size, boolean sortAlph) {
		
		Aggregation aggregation;
		
		List<Criteria> criterias = new ArrayList<>();
		
		Pageable pageableAlph = PageRequest.of(page, size, Sort.by(sortAlph? Sort.Direction.ASC : Sort.Direction.DESC, "surname"));
		
		if(!templateIds.isEmpty() || templateIds == null)
			criterias.add(Criteria.where("templates").in(templateIds)); // verify the name of the field templateIds in users collection
		
		if (StringUtils.isNotBlank(name)) 
			criterias.add(Criteria.where("name").is(name));
		
		if (StringUtils.isNotBlank(surname)) 
			criterias.add( Criteria.where("surname").is(surname));
		
		if (StringUtils.isNotBlank(email)) 
			criterias.add(Criteria.where("email").is(email));
		
		if (StringUtils.isNotBlank(userId)) 
			criterias.add(Criteria.where("_id").is(userId));

		Criteria criteria=new Criteria();
		
		if(!criterias.isEmpty())
			criteria=criteria.andOperator(criterias.toArray(new Criteria[criterias.size()]));
		
		aggregation = Aggregation.newAggregation(new AggregationOperation[] { Aggregation.match(criteria),
				Aggregation.sort(pageableAlph.getSort()),
				Aggregation.skip((long)pageableAlph.getPageNumber() * pageableAlph.getPageSize()), Aggregation.limit(pageableAlph.getPageSize()) });
		
		
		return this.mongoTemplate.aggregate(aggregation, ProfileUser.class, ProfileUser.class).getMappedResults();
		
		
	}


}
