package it.esdebitamiretake.retake_ir.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ir.controller.ResourceNotFoundException;
import it.esdebitamiretake.retake_ir.repo.ContextRepository;
import it.esdebitamiretake.retale_ir.db.collection.Context;

@Service
public class ContextService extends LoggerService {

	@Autowired
	ContextRepository contextRepository;

	ContextService() {

		super(ContextService.class);
	}

	public List<Context> find(Integer page, Integer pagesize) {

		return contextRepository.findAll(PageRequest.of(page, pagesize)).getContent();
	}

	public Context find(String id) throws ResourceNotFoundException {

		return contextRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("context", id));
	}

	public List<Context> findByTemplate(String templateId,Integer page, Integer pagesize) {

		return contextRepository.findByTemplateId(templateId,PageRequest.of(page, pagesize)).getContent();
	}
	
	public List<Context> findByTemplate(String templateId) {

		return contextRepository.findByTemplateId(templateId);
	}
	
	public List<Context> findAll() {

		return contextRepository.findAll();
	}

	public boolean exists(String id) {

		return contextRepository.existsById(id);
	}

	public Context save(Context group) {

		return contextRepository.save(group);
	}

	public void delete(String id) throws ResourceNotFoundException {

		contextRepository.delete(find(id));
	}
}