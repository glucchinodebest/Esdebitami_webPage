package it.esdebitamiretake.retake_ticket.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ticket.collection.Color;
import it.esdebitamiretake.retake_ticket.controller.ResourceNotFoundException;
import it.esdebitamiretake.retake_ticket.repo.ColorRepository;



@Service
public class ColorService {

	@Autowired
	ColorRepository colorRepository;

	
	public Color findByName(String name) throws ResourceNotFoundException {
		
		return colorRepository.findByColorName(name);
	}
	
	public Color saveColor(Color color) throws ResourceNotFoundException {
		
		return colorRepository.save(color);
	}	
	
	public List<Color> findByapplication(String app) {
		
		return colorRepository.findByAppName(app);
	}
	
	public Color findById(String id) throws ResourceNotFoundException {
		
		return colorRepository.findColorById(id);
	}
	
	public void delete(String id) throws ResourceNotFoundException {

		colorRepository.delete(findById(id));
	}
	
	public Color findByapplicationAndCode(String app, Integer code) {
		
		return colorRepository.findByAppNameAndColorCode(app, code);
	}
	
	public Color findByCode(Integer code) {
		
		return colorRepository.findByColorCode(code);
	}
}