package it.esdebitamiretake.retake_ticket.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ticket.collection.Activity;
import it.esdebitamiretake.retake_ticket.controller.ResourceNotFoundException;
import it.esdebitamiretake.retake_ticket.repo.ActivityRepository;



@Service
public class ActivityService {

	@Autowired
	ActivityRepository activityRepository;

	public List<Activity> findAll() {
		
		return activityRepository.findAll();
	}
	
	public Activity findActivityByName(String name) throws ResourceNotFoundException {
		
		return activityRepository.findByName(name);
	}
	
    public Activity findActivityByCode(Integer code) throws ResourceNotFoundException {
		
		return activityRepository.findByActivityCode(code);
	}
	
	public Activity save(Activity activity) {
		
		return this.activityRepository.save(activity);
	}	
	
	public void delete(String name) throws ResourceNotFoundException {

		activityRepository.delete(findActivityByName(name));
	}
}