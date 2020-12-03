package it.esdebitamiretake.retake_ticket.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ticket.collection.Group;
import it.esdebitamiretake.retake_ticket.repo.GroupRepository;

@Service
public class GroupService {
	
	@Autowired
	GroupRepository groupRepository;
	
	public Group find(String name,String application) {

		return groupRepository.findByNameAndApplication(name,application);
	}
}