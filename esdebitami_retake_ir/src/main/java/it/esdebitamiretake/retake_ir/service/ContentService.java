package it.esdebitamiretake.retake_ir.service;


import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ir.controller.ResourceNotFoundException;
import it.esdebitamiretake.retake_ir.repo.ContentRepository;
import it.esdebitamiretake.retale_ir.db.collection.Content;

@Service
public class ContentService extends LoggerService {

	@Autowired
	ContentRepository contentRepository;

	ContentService() {

		super(ContentService.class);
	}

	public Content find(String id) throws ResourceNotFoundException {

		return contentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("content", id));
	}

	public List<Content> find(Integer page, Integer pagesize) {

		return contentRepository.findAll(PageRequest.of(page, pagesize)).getContent();
	}

	public List<Content> findByResource(String resourceId) {

		return contentRepository.findByResourceId(new ObjectId(resourceId));
	}

	public boolean existsByResource(String resourceId) {

		return contentRepository.existsByResourceId(new ObjectId(resourceId));
	}

	public Content save(Content content) {

		return contentRepository.save(content);
	}

	public void delete(String id) throws ResourceNotFoundException {

		contentRepository.delete(find(id));
	}

	public void deleteAll(Collection<Content> contents) {

		contentRepository.deleteAll(contents);
	}
}