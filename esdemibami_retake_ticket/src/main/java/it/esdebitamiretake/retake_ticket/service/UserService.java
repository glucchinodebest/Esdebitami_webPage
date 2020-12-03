package it.esdebitamiretake.retake_ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ticket.anagrafica.ProfileUser;
import it.esdebitamiretake.retake_ticket.controller.ResourceNotFoundException;
import it.esdebitamiretake.retake_ticket.repo.UserRepository;




@Service
public class UserService{

	@Autowired
	UserRepository userRepository;

	public ProfileUser find(String id) throws ResourceNotFoundException {

		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User",id));
	}

	public boolean exists(String id) {
		
		return userRepository.existsById(id);
	}
	
	public ProfileUser save(ProfileUser user) {

		return userRepository.save(user);
	}

	public void delete(String id) throws ResourceNotFoundException {

		userRepository.delete(find(id));
	}
	
	//'TODO used in surveys anagrafica -> remove
	/*public List<String> findUserByInfo(String name, String surname, String email, String id) {
		
		List<ProfileUser> usersList = userRepository.findUsername(name, surname, email, id);
		
		List<String> usernames = new ArrayList<String>();
		
		for(ProfileUser user : usersList) {
			
			String username = user.getId();
			usernames.add(username);
			
		}
		
		return usernames;
	}*/
	
	public List<ProfileUser> findAllUsers() {
		
		List<ProfileUser> usersList = userRepository.findAll();
		
		return usersList;
	}
	
	public List<ProfileUser> findUserByInfoNew(List<String> templateIds, String name, String surname, String email, String id, Integer page, Integer size, boolean sortAlph) {
		
		//List<ProfileUser> usersList = userRepository.findUsernameNew(name, surname, email, id, page, size, sortAlph);
		List<ProfileUser> usersList = userRepository.findUsernameNew(templateIds, name, surname, email, id, page, size, sortAlph);
		
		return usersList;
	}
	
	public int findUserByInfoNewUnordered(List<String> templateIds, String name, String surname, String email, String id) {
		
		//List<ProfileUser> usersList = userRepository.findUsername(name, surname, email, id);
		List<ProfileUser> usersList = userRepository.findUsername(templateIds, name, surname, email, id);
		
		return usersList.size();
	}
}