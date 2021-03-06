package it.esdebitamiretake.retake_ai.controller.nlp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.axis2.client.Options;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tempuri.XSportService;
import org.tempuri.XSportService.XSpGetApplication;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.esdebitamiretake.retake_ai.beans.SentenceInput;
import it.esdebitamiretake.retake_ai.collection.Context;
import it.esdebitamiretake.retake_ai.collection.Dataset;
import it.esdebitamiretake.retake_ai.collection.Group;
import it.esdebitamiretake.retake_ai.collection.Question;
import it.esdebitamiretake.retake_ai.collection.Template;
import it.esdebitamiretake.retake_ai.controller.AController;
import it.esdebitamiretake.retake_ai.exceptions.ServiceException;
import it.esdebitamiretake.retake_ai.manager.POSManager;
import it.esdebitamiretake.retake_ai.manager.SentenceDetectorManager;
import it.esdebitamiretake.retake_ai.manager.handler.HandlePOS;
import it.esdebitamiretake.retake_ai.manager.handler.HandleSentenceDetection;
import it.esdebitamiretake.retake_ai.manager.listener.IOnResultListener;
import it.esdebitamiretake.retake_ai.repo.DatasetRepository;
import it.esdebitamiretake.retake_ai.response.GenericArrayListBean;
import it.esdebitamiretake.retake_ai.response.VasResponse;
import it.esdebitamiretake.retake_ai.response.WordPOSTag;
import it.esdebitamiretake.retake_ai.response.Words;
import it.esdebitamiretake.retake_ai.service.ContextService;
import it.esdebitamiretake.retake_ai.service.DatasetService;
import it.esdebitamiretake.retake_ai.service.GroupService;
import it.esdebitamiretake.retake_ai.service.TemplateService;


@RestController
@EnableAutoConfiguration
@RequestMapping("/nlp")
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class NLPController extends AController {
	
	private static Logger LOG = LoggerFactory.getLogger(NLPController.class);

	@Autowired
	private TemplateService templateservice;

	@Autowired
	private SentenceDetectorManager sentenceDetectorManager;

	@Autowired
	private POSManager posManager;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	InteractionUtils interactionUtils;
	
	@Autowired
	TemplateTypeInteract templateTypeInteract;
	
	@Autowired
	DatasetRepository datasetRepository;

	@Autowired
	DatasetService datasetService;
	
	@Autowired
	ContextService contextService;
	 
	public NLPController() {
		super();
	}

	
	
	/*
	 * verificare se utilizzato
	 */
	@PostMapping(value = "/sentenceDetection", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<VasResponse> sentenceDetection(HttpServletRequest request, HttpServletResponse response, @Valid @RequestBody SentenceInput input) {
		LOG.info( "START sentenceDetection" );
		final Words words = new Words();
		IOnResultListener< ArrayList < String > > resultListener = new IOnResultListener<ArrayList< String > >() {
			@Override
			public void onSuccess(ArrayList < String > resultToManage ) {
				words.getWords().addAll( resultToManage );
			}

			@Override
			public void onFailure( ServiceException ex ) {
				throw ex;
			}
		};
		HandleSentenceDetection handler = new HandleSentenceDetection( resultListener );
		this.sentenceDetectorManager.manage( handler, input.getSententece() );
		LOG.info( "STOP sentenceDetection" );
		return buildResponseOK( words );
	}

	
	
	/*
	 * verificare se utilizzato
	 */
	@PostMapping(value = "/pos", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<VasResponse> partOfSpeech( HttpServletRequest request, HttpServletResponse response,@Valid @RequestBody GenericArrayListBean<String> input) {
		LOG.info("START partOfSpeech");
		final GenericArrayListBean< WordPOSTag > output = new GenericArrayListBean<>();
		IOnResultListener<ArrayList< WordPOSTag > > resultListener = new IOnResultListener< ArrayList< WordPOSTag > >() {
			@Override
			public void onSuccess( ArrayList< WordPOSTag > resultToManage ) {
				output.getItems().addAll( resultToManage );
			}

			@Override
			public void onFailure( ServiceException ex ) {
				throw ex;
			}
		};
		HandlePOS handler = new HandlePOS( resultListener );
		this.posManager.manage( handler, input.getItems() );
		LOG.info( "STOP partOfSpeech" );
		return buildResponseOK( output );
	}

	
	
	/*
	 * verificare se utilizzato
	 * 
	 */
	@PostMapping(value = "/correct")
	@ResponseBody
	public VasResponse correct(HttpServletRequest request, HttpServletResponse response, @RequestParam String sentence, @RequestParam String templateId) throws IOException, ParseException {
		LOG.info(" START correction ");
		sentence = sentence.trim().toLowerCase();
		VasResponse vasresponse = new VasResponse( Integer.valueOf( 0 ), "OK", null);
		ArrayList< String > correctedSentence = new ArrayList< String >();
		try {
			Template template = this.templateservice.findTemplateById( templateId );
			String templateDataset = template.getDataset();
			Process process = Runtime.getRuntime().exec( "sudo python3 correction.py " + sentence + " " + templateDataset );
			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
			String line = "";
			while ( ( line = bufferedReader.readLine() ) != null) 
				correctedSentence.add( line );
			vasresponse.setContent( correctedSentence );
		} catch ( Throwable th ) {
			LOG.info( "Errore" );
			vasresponse = new VasResponse( Integer.valueOf( 1 ), "Errore generico del server", null );
		}
		return vasresponse;
	}

	

	/*
	 * verificare se utilizzato
	 */
	@PostMapping(value = "/stemming")
	@ResponseBody
	public VasResponse stemming( HttpServletRequest request, HttpServletResponse response, @RequestBody String sentence) throws IOException {
		LOG.info( "START stemming" );
		sentence = sentence.trim().toLowerCase();
		VasResponse vasresponse = new VasResponse( Integer.valueOf( 0 ), "OK", null);
		String stemma = "";
		try {
			Process process = Runtime.getRuntime().exec( "sudo python3 stemming.py " + sentence );
			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
			String line = "";
			while ( ( line = bufferedReader.readLine() ) != null) 
				stemma += line + " ";
			LOG.info( "ENDING stemming" );
			vasresponse.setContent( stemma );
		} catch ( Throwable th ) {
			LOG.info( "Errore" );
			vasresponse = new VasResponse( Integer.valueOf( 1 ), "Errore generico del server", null );
		}
		return vasresponse ;
	}

	
	
	/*
	 * verificare se utilizzato
	 */
	@PostMapping(value = "/lemming")
	@ResponseBody
	public VasResponse lemming(HttpServletRequest request, HttpServletResponse response, @RequestBody String sentence) throws IOException {
		LOG.info( "START lemming" );
		VasResponse vasresponse = new VasResponse( Integer.valueOf( 0 ), "OK", null );
		String lemma = "";
		try {
			Process process = Runtime.getRuntime().exec( "sudo python3 lemming.py " + sentence );
			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
			String line = "";
			while ( ( line = bufferedReader.readLine() ) != null) 
				lemma+= line + " ";
			LOG.info( "ENDING stemming" );
			vasresponse.setContent( lemma );
		} catch ( Throwable th ) {
			LOG.info( "Errore" );
			vasresponse = new VasResponse( Integer.valueOf( 1 ), "Errore generico del server", null );
		}
		return vasresponse;
	}

	
	
	/*
	 * verificare se utilizzato
	 */
	@PostMapping(value = "/landetect")
	@ResponseBody
	public VasResponse landetect(HttpServletRequest request, HttpServletResponse response, @RequestBody String sentence) {
		LOG.info( "START language detection" );
		VasResponse vasresponse = new VasResponse( Integer.valueOf( 0 ), "OK", null );
		ArrayList< String > language = new ArrayList< String >();
		try {
			Process process = Runtime.getRuntime().exec( "sudo python3 landetect.py " + sentence );
			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
			String line = "";
			while ( ( line = bufferedReader.readLine() ) != null) 
				language.add( line );
			LOG.info( "ENDING language detection" );
			vasresponse.setContent( language );
		} catch (Throwable th) {
			LOG.info( "Errore" );
			vasresponse = new VasResponse( Integer.valueOf( 1 ), "Errore generico del server", null);
		}
		return vasresponse;
	}

	
	
	/*
	 * verificare se utilizzato
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/generateLink")
	@ResponseBody
	public VasResponse generateLink(HttpServletRequest request, HttpServletResponse response, @RequestParam String templateId, @RequestParam String researchKey) {
		LOG.info( "START link generation" );
		VasResponse vasresponse = new VasResponse( Integer.valueOf( 0 ), "OK", null);
		ArrayList< String > links = new ArrayList< String >();
		try {
			Template template = this.templateservice.findTemplateById( templateId );
			String templateLink = "";
			if ( template.getLink() != null ) {
				templateLink += template.getLink();
				Process process = Runtime.getRuntime().exec( "sudo python3 googleapi.py , " + templateLink + " , " + researchKey );
				BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
				String line2 = "";
				while ( ( line2 = bufferedReader.readLine() ) != null) 
					links.add( line2 );
				JSONObject returnObj = new JSONObject();
				returnObj.put( " Links ", links.toString().replace("[", "").replace("]", "").replace("'", "") );
				LOG.info( "ENDING link generation" );
				vasresponse.setContent( returnObj );
			}
		} catch ( Throwable th ) {
			LOG.info( "Errore" );
			vasresponse = new VasResponse( Integer.valueOf( 1 ), "Errore generico del server", null );
		}
		return vasresponse;
	}
	

	
	@SuppressWarnings( "unchecked" )
	@ApiOperation( value = "Find the right template basing on dataset's documents and the inputsentence", tags = "Interaction" )
	@PostMapping({ "/findTemplate" })
	@ResponseBody
	public VasResponse findTemplate( HttpServletRequest request, HttpServletResponse response, 
			@ApiParam( value = "the sentence typed/spelled by the user" , required = true) @RequestParam String sentence,
			@ApiParam( value = "Token from wich identify the user and his group" , required = true ) @RequestHeader String accessToken,
			@ApiParam( value = "The group to wich the users is signed" , required = true) @RequestParam String groupName ) {
		LOG.info( "[START] findTemplate" );
		VasResponse res = new VasResponse( Integer.valueOf( 0 ), "OK", null );
		try {
			sentence = InteractionUtils.stemSentence( sentence ).trim().toLowerCase();
			JSONObject returnObject = new JSONObject();
			sentence = sentence.trim().toLowerCase();
			List<Template> templates = getTemplatesByGroupAndStatus( groupName, accessToken, 0 );
			boolean controllo = false;			
			List < Dataset > datasets = new ArrayList< Dataset >();
			for ( Template template : templates ) 
				datasets.addAll( this.datasetService.findDatasetByTemplateId( template.getTemplateId() ) );
			for ( Dataset dataset : datasets ) {
				String corpusIstance = dataset.getSentence();
				if ( sentence.equals( InteractionUtils.stemSentence( corpusIstance ) ) ) {
					controllo = true;
					System.out.println( sentence + " EQUALS " + corpusIstance );	LOG.info( sentence + " EQUALS " + corpusIstance );
					returnObject.put( "templateId", dataset.getTemplateId() );
					res.setContent( returnObject );
					return res;
				}
			}
			if ( !controllo )
				res = this.interactionUtils.resolveAmbiguityInFindTemplateWithCorpus( datasets, sentence );
		} catch ( Throwable th ) {
			LOG.info( "[ERROR] findTemplate" );
			res = new VasResponse( Integer.valueOf( 1 ), "Errore generico del server", null );
		}
		LOG.info( "[END] findTemplate" );
		return res;
	}
	
	
	
	@PostMapping( value = "/findNextQuestionExp" )
	@ApiOperation( value = "Find the correct next question inside a specific template based on the response from user", tags = "Interaction" )
	@ResponseBody
	public VasResponse findNextQuestionExp( HttpServletRequest request, HttpServletResponse response,
			@ApiParam( value = "The sentence inputed form the user" , required = true ) @RequestParam String sentence,
			@ApiParam( value = "The template with wich the user is interacting" , required = true ) @RequestParam String templateId,
			@ApiParam( value = "The questioId with wich the user is interacting" , required = false ) @RequestParam( required = false ) Integer questionId ) {
		LOG.info( "[START] findNextQuestion" );
		VasResponse res = new VasResponse( Integer.valueOf( 0 ), "OK", null );
		try {
			sentence = sentence.trim().toLowerCase();
			Template template = this.templateservice.findTemplateById( templateId );
			String templateType = template.getTemplateType();
			if ( questionId == null ) {
				Question question = this.interactionUtils.findFirstQuestion( templateId );
				questionId = question.getQuestionId();
			}
			LOG.info( "Interacting with question : " + questionId );
			if ( "Libero".equals( templateType ) ) {
				LOG.info( "STARTING navigation in template LIBERO : " + template.getTemplateId() );
				List< Question > templateQuestions = template.getQuestions();
				for ( Question questions : templateQuestions ) {
					if ( questions.getQuestionId().equals( questionId ) ) 
						res = this.templateTypeInteract.freeQuestion( questions );
				}
				LOG.info( "ENDING navigation in template LIbero " + template.getTemplateId() );
			} 
			else if ( "Controllato".equals( templateType ) ) {
				LOG.info( "STARTING navigation in template CONTROLLATO " + template.getTemplateId() );
				res = this.templateTypeInteract.controlledQuestion( sentence, template, questionId );
				LOG.info( "ENDING navigation in template CONTROLLATO " + template.getTemplateId() );
			} 
			else if ( "TestInvalsi".equals( templateType ) ) {
				LOG.info( "STARTING navigation in template INVALSI " + template.getTemplateId() );
				res = this.templateTypeInteract.questionarioInvalsi( template, sentence, questionId );
				LOG.info( "ENDING navigation in template INVALSI " + template.getTemplateId() );
			} 
			else if ( "Anagrafica".equals( templateType ) ) {
				LOG.info( "STARTING navigation in template Anagrafica " + template.getTemplateId() );
				res = this.templateTypeInteract.Anagrafica(template, sentence, questionId );
				LOG.info( "ENDING navigation in template Anagrafica " + template.getTemplateId() );
			} 
			else {
				LOG.info( "STARTING navigation in template MISTO/OTHER TYPE " + template.getTemplateId() );
				res = this.templateTypeInteract.mixedQuestion(sentence, template, questionId );
				LOG.info( "ENDING navigation in template MISTO/OTHER TYPE " + template.getTemplateId() );
			}
		} catch ( Throwable th ) {
			LOG.info( "[ERROR] findNextQuestion" );
			res = new VasResponse( Integer.valueOf( 1 ), "Errore generico del server", null );
		}
		LOG.info( "[END] findNextQuestion" );
		return res;
	}

	
	
	public String loadApplicationName( String accessToken ) throws RemoteException {
		XSportService xSportService=new XSportService();
		Options options = xSportService._getServiceClient().getOptions();
		options.setManageSession( true );
		options.setProperty( org.apache.axis2.transport.http.HTTPConstants.COOKIE_STRING, accessToken );
		return xSportService.xSpGetApplication( new XSpGetApplication() ).getXSpGetApplicationResult();
	}
	
	
	
	public List< Template > getTemplatesByGroupAndStatus( String groupName , String accessToken , Integer status ) throws RemoteException {
		Group group = groupService.find( groupName,  loadApplicationName( accessToken ) );
		Set< String > templatesIds = group.getTemplates();
		List< Template > listTemp = this.templateservice.findTemplatesByIdsAndStatus( templatesIds , status );
		return listTemp;
	}
	
	
	@GetMapping({ "/findContext/{templateId}" })
	@ResponseBody
	public VasResponse findContext( HttpServletRequest request, HttpServletResponse response, @PathVariable String templateId ) {
		VasResponse res = new VasResponse( Integer.valueOf( 0 ), "OK", null );
		Context context = this.contextService.findContextByTemplateId(templateId);
		res.setContent(context);
		return res;
	}

	
	

	
	
}
