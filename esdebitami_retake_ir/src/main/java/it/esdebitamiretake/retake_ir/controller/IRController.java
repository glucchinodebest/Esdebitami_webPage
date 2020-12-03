package it.esdebitamiretake.retake_ir.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.lucene.queryparser.classic.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import it.esdebitamiretake.retake_ir.model.Answer;
import it.esdebitamiretake.retake_ir.search.Language;
import it.esdebitamiretake.retake_ir.search.SearchProperties;
import it.esdebitamiretake.retake_ir.search.lexical.Searcher;
import it.esdebitamiretake.retake_ir.search.semantic.PyClient;
import it.esdebitamiretake.retake_ir.service.ContentService;
import it.esdebitamiretake.retake_ir.service.ContextService;
import it.esdebitamiretake.retake_ir.service.ResourceService;
import it.esdebitamiretake.retake_ir.service.TemplateService;
import it.esdebitamiretake.retale_ir.db.collection.Content;
import it.esdebitamiretake.retale_ir.db.collection.Context;
import it.esdebitamiretake.retale_ir.db.collection.Resource;

@SwaggerDefinition(tags = {
		@Tag(name = "Context Resource REST Endpoint", description = "Operations pertaining to context in Context Management System") })
@RestController
public class IRController {

	private static final String MESSAGE_401 = "You are not authorized to perform this action";

	private static final Logger logger = LoggerFactory.getLogger(IRController.class);

	@Autowired
	ContextService contextService;

	@Autowired
	TemplateService templateService;

	@Autowired
	ResourceService resourceService;

	@Autowired
	ContentService contentService;

	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	private SearchProperties searchProperties;

	private PyClient pyClient;

	@PostConstruct
	public void init() throws MalformedURLException {

		pyClient = new PyClient(searchProperties.getClient());
	}

	@GetMapping(value = "/contexts", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get the list of available contexts", tags = "contexts")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The list of contexts has been successfully retrieved"),
			@ApiResponse(code = 401, message = MESSAGE_401), @ApiResponse(code = 404, message = "No context found") })
	@ResponseBody
	public List<Context> getContexts(
			@ApiParam(value = "Template id", required = false) @RequestParam(required = false) String templateId,
			@ApiParam(value = "Zero-based page index", required = false, allowableValues = "range[0, infinity]") @RequestParam(required = false) @Min(0) Integer page,
			@ApiParam(value = "The size of the page to be returned", required = false, allowableValues = "range[1, infinity]") @Min(1) @RequestParam(required = false) Integer size)
			throws ResourceNotFoundException {

		logger.info("GET contexts");

		List<Context> contexts;

		if (templateId == null)
			contexts = size == null ? contextService.findAll() : contextService.find(page == null ? 0 : page, size);
		else
			contexts = size == null ? contextService.findByTemplate(templateId)
					: contextService.findByTemplate(templateId, page == null ? 0 : page, size);

		if (contexts.isEmpty())
			throw new ResourceNotFoundException("contexts");

		return contexts;
	}

	@GetMapping(value = "/contexts/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a context by id", tags = "contexts")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The context has been successfully retrieved"),
			@ApiResponse(code = 401, message = MESSAGE_401), @ApiResponse(code = 404, message = "Context not found") })
	@ResponseBody
	public Context getContext(@ApiParam(value = "Context id", required = true) @PathVariable String id)
			throws ResourceNotFoundException {

		logger.info("GET context [?]", id);

		return contextService.find(id);
	}

	@PostMapping("/contexts")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create a new context", tags = "contexts")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Context successfully created"),
			@ApiResponse(code = 400, message = "Invalid context object"),
			@ApiResponse(code = 401, message = MESSAGE_401), @ApiResponse(code = 404, message = "Template not found"),
			@ApiResponse(code = 409, message = "A context for this URI already exists") })
	@ResponseBody
	public URI postContext(@ApiParam(value = "Context object", required = true) @Valid @RequestBody Context context)
			throws ResourceNotFoundException {

		logger.info("POST context [?]", context);

		templateService.find(context.getTemplateId());

		try {

			return ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
					.buildAndExpand(contextService.save(context).getTemplateId()).toUri();

		} catch (DuplicateKeyException e) {

			throw new ResourceAlreadyExistsException("Context", context.getUri());
		}
	}

	@PutMapping("/contexts/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Update a context", tags = "contexts")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Context successfully updated"),
			@ApiResponse(code = 400, message = "Invalid context object"),
			@ApiResponse(code = 404, message = "Resource not found"), @ApiResponse(code = 401, message = MESSAGE_401) })
	public void putContext(@ApiParam(value = "Context id", required = true) @PathVariable String id,
			@ApiParam(value = "Context object", required = true) @Valid @RequestBody Context context)
			throws ResourceNotFoundException {

		logger.info("PUT context [?]", context);

		contextService.find(id);
		context.setId(id);
		contextService.save(context);
	}

	@DeleteMapping("/contexts/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete a context", tags = "contexts")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Context has been successfully deleted"),
			@ApiResponse(code = 401, message = MESSAGE_401), @ApiResponse(code = 404, message = "Context not found") })
	public void deleteContext(@ApiParam(value = "Context id", required = true) @PathVariable String id)
			throws ResourceNotFoundException {

		logger.info("DELETE context [?]", id);

		try {
			deleteResources(id);
		} catch (ResourceNotFoundException e) {
		}

		contextService.delete(id);
	}

	@PostMapping("/contexts/{id}/resources")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create a new resource", tags = "resources")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Resource successfully created"),
			@ApiResponse(code = 400, message = "Invalid resource object"),
			@ApiResponse(code = 401, message = MESSAGE_401),
			@ApiResponse(code = 409, message = "A resource for this uri already exists") })
	@ResponseBody
	public URI postResource(@ApiParam(value = "Context id", required = true) @PathVariable String id,
			@ApiParam(value = "Resource object", required = true) @Valid @RequestBody Resource resource)
			throws ResourceNotFoundException, IOException {

		logger.info("POST resource [?]", resource);

		Context context = contextService.find(id);

		resource.setContextId(id);

		try {

			String reference = resource.getReference();

			if (reference != null)
				resource.setEmbeddings(pyClient.train(reference, context.getLanguage()));

			return ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
					.buildAndExpand(resourceService.save(resource).getId()).toUri();

		} catch (DuplicateKeyException e) {
			throw new ResourceAlreadyExistsException("Resource", resource.getUri());
		}
	}

	@GetMapping(value = "/resources/*/contents/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a content by id", tags = "contents")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The content has been successfully retrieved"),
			@ApiResponse(code = 401, message = MESSAGE_401), @ApiResponse(code = 404, message = "Content not found") })
	@ResponseBody
	public Content getContent(@ApiParam(value = "Content id", required = true) @PathVariable String id)
			throws ResourceNotFoundException {

		logger.info("GET content [?]", id);

		return contentService.find(id);
	}

	@PostMapping("/resources/{id}/contents")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create a new content", tags = "contents")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Content successfully created"),
			@ApiResponse(code = 400, message = "Invalid content object"),
			@ApiResponse(code = 401, message = MESSAGE_401), @ApiResponse(code = 404, message = "Resource not found"),
			@ApiResponse(code = 409, message = "This content already exists for the specified resource") })
	@ResponseBody
	public URI postContent(@ApiParam(value = "Resource id", required = true) @PathVariable String id,
			@ApiParam(value = "Content object", required = true) @Valid @RequestBody Content content)
			throws ResourceNotFoundException, FileNotFoundException, IOException {

		logger.info("POST content [?]", content);

		Resource resource = resourceService.find(id);
		Context context = contextService.find(resource.getContextId());

		content.setResourceId(resource.getId());
		content.setEmbeddings(pyClient.train(content.getText(), context.getLanguage()));

		try {
			return ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
					.buildAndExpand(contentService.save(content).getId()).toUri();

		} catch (DuplicateKeyException e) {
			throw new ResourceAlreadyExistsException("Content", content.getText());
		}
	}

	@DeleteMapping("/contexts/{id}/resources")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete all resources associated with a context", tags = "resources")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Resources have been successfully deleted"),
			@ApiResponse(code = 401, message = MESSAGE_401), @ApiResponse(code = 404, message = "Context not found") })
	public void deleteResources(@ApiParam(value = "Context id", required = true) @PathVariable String id)
			throws ResourceNotFoundException {

		logger.info("DELETE resources [?]", id);

		List<Resource> resources = resourceService.findByContext(id);

		for (Resource resource : resources) {

			try {
				deleteContents(resource.getId());
			} catch (ResourceNotFoundException e) {
			}
		}

		resourceService.deleteAll(resources);
	}

	@DeleteMapping("/resources/{id}/contents")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete all contents associated with a resource", tags = "contents")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Contents have been successfully deleted"),
			@ApiResponse(code = 401, message = MESSAGE_401), @ApiResponse(code = 404, message = "Resource not found") })
	public void deleteContents(@ApiParam(value = "Resource id", required = true) @PathVariable String id)
			throws ResourceNotFoundException {

		logger.info("DELETE contents [?]", id);

		resourceService.find(id);

		List<Content> contents = contentService.findByResource(id);

		if (contents.isEmpty())
			throw new ResourceNotFoundException("contents");

		contentService.deleteAll(contents);
	}

	@GetMapping(value = "/templates/{id}/answers", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Retrieve possible answers to a question posed within a context", tags = "templates")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The list of answers has been successfully retrieved"),
			@ApiResponse(code = 401, message = MESSAGE_401), @ApiResponse(code = 404, message = "Context not found") })
	@ResponseBody
	public Collection<Answer> getAnswers(@ApiParam(value = "Template id", required = true) @PathVariable String id,
			@ApiParam(value = "Question") @RequestParam String question, @ApiParam(value = "Language") @RequestParam Language language,
			@ApiParam(value = "The number of answers to be retrieved", required = false, allowableValues = "range[1, 10]") @Min(1) @Max(10) @RequestParam(defaultValue = "1", required = false) Integer count)
			throws ResourceNotFoundException, IOException, ParseException {

		logger.info("GET answers");

		// Recupera i contesti associati al template specificato nel path URI
		List<Context> contexts = contextService.findByTemplate(id);

		if (contexts.isEmpty())
			throw new ResourceNotFoundException("contexts");

		Set<Answer> answers = new TreeSet<>(); // insieme delle risposte ordinato per score

		for (Context context : contexts) {

			// cerca la risposta nei contesti la cui lingua corrisponde a quella passata come parametro 
			if (context.getLanguage() == language) {

				// Recupera dall'indice i documenti più rilevanti rispetto alla query
				Map<String, Float> documents = new Searcher(context.getLanguage(),
						Paths.get(searchProperties.getIndex(), id, context.getID()).toAbsolutePath().toString())
								.search(question, 0.65f);

				Map<String, Object> entries = new LinkedHashMap<>();
				Map<String, Integer> resources = new HashMap<>();
				Map<Integer, String> contents = new HashMap<>();
				List<double[]> corpus = new ArrayList<>();

				// Prepara il corpus di vettori per il confronto con la query
				for (String resourceId : documents.keySet()) {

					Resource resource = resourceService.find(resourceId);

					entries.put(resourceId, resource);

					if (resource.getEmbedding() != null) {
						corpus.add(resource.getEmbedding());
						resources.put(resourceId, corpus.size() - 1);
					}

					for (Content content : contentService.findByResource(resourceId)) {
						entries.put(content.getId(), content);
						corpus.add(content.getEmbedding());
						contents.put(corpus.size() - 1, content.getId());
					}
				}

				// richiama il servizio python per calcolare la similarità semantica tra i vettori del corpus e quello della query
				double[] results = pyClient.score(question, language, corpus);
				double[] weights = SearchProperties.getWeights(id);	

				for (int i = 0; i < results.length; i++) {

					if (contents.containsKey(i)) {

						Content content = (Content) entries.get(contents.get(i));
						String resourceId = content.getResourceId();
						Resource resource = (Resource) entries.get(resourceId);
						double scoreC = Math.abs((1 - results[i]));
						double scoreR = resources.containsKey(resourceId)
								? Math.abs(1 - results[resources.get(resourceId)])
								: scoreC;
						double score = scoreR * weights[0] + scoreC * weights[1]
								+ documents.get(resource.getId()) * weights[2];
						answers.add(new Answer(content.getText(), resource.getUri(), resource.getReference(), score));
					}
				}
			}
		}

		return answers.stream().limit(count).collect(Collectors.toList());
	}

	@PostMapping("/contexts/{id}/indexes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Create a lexical index from the contexts of the specified template", tags = "templates")
	@ApiResponses(value = { @ApiResponse(code = 204, message = ""), @ApiResponse(code = 401, message = MESSAGE_401),
			@ApiResponse(code = 404, message = "Context not found") })
	public void postIndex(@ApiParam(value = "Template id", required = true) @PathVariable String id)
			throws ResourceNotFoundException, IOException {

		Context context = contextService.find(id);

		Searcher searcher = new Searcher(context.getLanguage(),
				Paths.get(searchProperties.getIndex(), context.getTemplateId(), context.getID()).toAbsolutePath().toString());
		searcher.reset();

		for (Resource resource : resourceService.findByContext(context.getID()))
			searcher.index(resource.getId(), contentService.findByResource(resource.getId()));

	}
}