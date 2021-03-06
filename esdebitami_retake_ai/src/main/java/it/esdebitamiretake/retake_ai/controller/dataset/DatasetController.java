package it.esdebitamiretake.retake_ai.controller.dataset;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.esdebitamiretake.retake_ai.collection.Dataset;
import it.esdebitamiretake.retake_ai.collection.Template;
import it.esdebitamiretake.retake_ai.controller.nlp.GestioneCorpus;
import it.esdebitamiretake.retake_ai.controller.nlp.NLPController;
import it.esdebitamiretake.retake_ai.repo.DatasetRepository;
import it.esdebitamiretake.retake_ai.service.DatasetService;
import it.esdebitamiretake.retake_ai.service.TemplateService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/nlp")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class DatasetController {

	private static Logger LOG = LoggerFactory.getLogger(NLPController.class);
	
	@Autowired
	TemplateService templateService;
	
	@Autowired
	GestioneCorpus gestioneCorpus;
	
	@Autowired
	DatasetService datasetService;
	
	@Autowired
	DatasetRepository datasetRepository;

	
	
	@PostMapping( value = "/dataset/{templateId}", produces="application/json" )
	@ApiOperation(value = "Create documents for collection dataset from template", tags = "Dataset")
	@ResponseBody
	public String saveDataset ( @ApiParam ( value = "template id from wich extact dataset", required = true) @PathVariable String templateId ) throws InvalidFormatException, IOException,it.esdebitamiretake.retake_ai.controller.dataset.ResourceNotFoundException{
		int numElem = 0 ;
		LOG.info( "[START] POST/dataset" );
		Template template = templateService.findTemplateById( templateId );
		if ( template == null ) 
				throw new ResourceNotFoundException( "Template" );
		List < Dataset > tempDataset = this.datasetService.findDatasetByTemplateId( templateId );
		if ( tempDataset.size() != 0 ) {
			LOG.info( "[DELETE] datasets of template : " + templateId );
			for ( Dataset  data : tempDataset ) {
				this.datasetService.deleteDataset( data.getId() );
				LOG.info( "Deleting old dataset for template : " + templateId + " , number of elements deleted : " + tempDataset.size() );
			}
		}
		if( template.getDataset() != null ) {
			LOG.info( "Extracting dataset from template : " + templateId );
			List< String > corpus = this.gestioneCorpus.getCorpus( template.getDataset() );
			for ( String corpusIstance : corpus ) {
				if( corpusIstance.trim() != "" ) {
					corpusIstance = corpusIstance.trim().toLowerCase();
					Dataset dataset = new Dataset ( templateId, corpusIstance );
					datasetService.saveDataset( dataset );
					numElem++ ;
				}
			}
		}
		LOG.info( "[END] POST/dataset" );
		return ( "Number of elements saved : " + numElem );
	}
	
	

	@DeleteMapping( value = "/dataset/{templateId}", produces="application/json" )
	@ApiOperation(value = "Create documents for collection dataset from template", tags = "Dataset")
	@ResponseBody
	public String deleteDataset ( @ApiParam ( value = "template id from of wich delete datasets documents", required = true) @PathVariable String templateId ) throws ResourceNotFoundException {
		int numElem = 0 ;
		LOG.info( "[START] DELETE/dataset" );
		LOG.info( "[DELETE] Deleting dataset of template : " + templateId );
		List < Dataset > datasets = this.datasetService.findDatasetByTemplateId( templateId );
		if ( datasets.size() == 0  )
			return ( "No datasets linked to template : " + templateId );
		for ( Dataset dataset : datasets ) {
			this.datasetService.deleteDataset( dataset.getId() );
			numElem ++ ;
		}
		LOG.info( "END DELETING dataset of template : " +  templateId );
		return ( "Number of elements deleted : " + numElem );
	}

	
	
	
}
