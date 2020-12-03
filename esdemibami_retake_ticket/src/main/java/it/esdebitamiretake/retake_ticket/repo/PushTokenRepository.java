package it.esdebitamiretake.retake_ticket.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.esdebitamiretake.retake_ticket.model.UserToken;

@Repository
public abstract interface PushTokenRepository
  extends MongoRepository<UserToken, String>
{
  public abstract UserToken findByUsername(String paramString);
  
  public abstract UserToken findByTokenFCM(String paramString);
}


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\db\repo\PushTokenRepository.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */