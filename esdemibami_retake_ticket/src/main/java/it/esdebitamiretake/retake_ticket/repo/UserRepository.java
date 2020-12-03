package it.esdebitamiretake.retake_ticket.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.esdebitamiretake.retake_ticket.anagrafica.ProfileUser;




@Repository
public interface UserRepository extends MongoRepository<ProfileUser, String>, UserCustomRepository {

}
