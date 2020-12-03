package it.esdebitamiretake.retake_ir.service;


import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ir.controller.ResourceNotFoundException;
import it.esdebitamiretake.retake_ir.repo.ResourceRepository;
import it.esdebitamiretake.retale_ir.db.collection.Resource;

@Service
public class ResourceService extends LoggerService {

	@Autowired
	ResourceRepository resourceRepository;

	ResourceService() {

		super(ResourceService.class);
	}

	public Resource find(String id) throws ResourceNotFoundException {

		return resourceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource", id));
	}

	public List<Resource> findByContext(String contextId) {

		return resourceRepository.findByContextId(new ObjectId(contextId));
	}

	public Resource findByUri(String uri) {

		return resourceRepository.findByUri(uri);
	}

	public Resource save(Resource resource) {

		return resourceRepository.save(resource);
	}

	public void deleteAll(Collection<Resource> resources)  {

		resourceRepository.deleteAll(resources);
	}
}