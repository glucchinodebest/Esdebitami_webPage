package it.esdebitamiretake.retake_ticket.repo;

import java.util.List;

import it.esdebitamiretake.retake_ticket.anagrafica.ProfileUser;


public abstract interface UserCustomRepository{
	
	//public abstract List<ProfileUser> findUsername(String name, String surname, String email, String userId);
	
	//public abstract List<ProfileUser> findUsernameNew(String name, String surname, String email, String userId, Integer page, Integer size, boolean sortAlph);
	
	public abstract List<ProfileUser> findUsernameNew(List<String> templateIds, String name, String surname, String email, String userId, Integer page, Integer size, boolean sortAlph);
	
	public abstract List<ProfileUser> findUsername(List<String> templateIds, String name, String surname, String email, String userId);
}