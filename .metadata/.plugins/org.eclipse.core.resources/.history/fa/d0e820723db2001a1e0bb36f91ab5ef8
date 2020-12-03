package it.esdebitamiretake.retake_ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ticket.anagrafica.Anagraphic;
import it.esdebitamiretake.retake_ticket.repo.AnagraphicRepository;



@Service
public class AnagraphicService {

	@Autowired
	AnagraphicRepository anagraphicRepository;

	public List<Anagraphic> findByapplication(String app) {
		
		return anagraphicRepository.findByAppName(app);
	}
	
	public Anagraphic saveAnagraphic(Anagraphic anagraphic) {
		
		return this.anagraphicRepository.save(anagraphic);
	}	
	
}