package it.esdebitamiretake.retake_ir.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ir.controller.ResourceNotFoundException;
import it.esdebitamiretake.retake_ir.repo.TemplateRepository;
import it.esdebitamiretake.retale_ir.db.collection.Template;

@Service
public class TemplateService extends LoggerService {

	@Autowired
	TemplateRepository templateRepository;

	TemplateService() {

		super(Template.class);
	}

	public Template find(String id) throws ResourceNotFoundException {

		return templateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("template", id));
	}

	public boolean exists(String id) {

		return templateRepository.existsById(id);
	}

}