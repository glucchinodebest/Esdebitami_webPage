package it.esdebitamiretake.retake_ai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ai.collection.Group;
import it.esdebitamiretake.retake_ai.repo.GroupRepository;



@Service
public class GroupService {
	
	@Autowired
	GroupRepository groupRepository;
	
	public Group find(String name,String application) {
		return groupRepository.findByNameAndApplication(name,application);
	}
	
	
}
