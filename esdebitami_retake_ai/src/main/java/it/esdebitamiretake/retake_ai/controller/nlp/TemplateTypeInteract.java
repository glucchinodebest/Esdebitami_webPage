package it.esdebitamiretake.retake_ai.controller.nlp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.esdebitamiretake.retake_ai.collection.Context;
import it.esdebitamiretake.retake_ai.collection.Question;
import it.esdebitamiretake.retake_ai.collection.Response;
import it.esdebitamiretake.retake_ai.collection.Template;
import it.esdebitamiretake.retake_ai.collection.findSentenceForInvalsi;
import it.esdebitamiretake.retake_ai.response.VasResponse;
import it.esdebitamiretake.retake_ai.service.ContextService;


@Service
public class TemplateTypeInteract {
	
	private static Logger LOG = LoggerFactory.getLogger(NLPController.class);
	
	@Autowired
	InteractionUtils interactionUtils;
	
	@Autowired
	ContextService contextservice;
	
	
	@SuppressWarnings( "unchecked" )
	public VasResponse controlledQuestion( String sentence, Template template, Integer questionId )throws IOException, InvalidFormatException {
		LOG.info( "[START] ControlledQuestion" );
		String originalSentence = sentence;
		sentence = InteractionUtils.stemSentence( sentence ).trim().toLowerCase(); 
		JSONObject returnObj = new JSONObject();
		VasResponse res = new VasResponse( Integer.valueOf( 0 ), "OK", null );
		String fieldFlag = null;
		List < Integer > questionsId = new ArrayList < Integer >();
		if ( sentence.equals( "image/*" ) || sentence.equals( "application/pdf" ) ) {
			res = InteractionUtils.LoadingDataInsideQuestion( template );
			return res;
		} 
		else {
			List < Question > templateQuestions = template.getQuestions();
			for ( Question question : templateQuestions ) {
				Integer questId = question.getQuestionId();
				if ( questId.equals( questionId ) ) {
					fieldFlag = question.getFieldFlag();
					List < Response > responses = question.getResponses();
					boolean controllo = false;
					for ( Response response : responses ) {
						List < String > keywords = response.getKeywords();
						controllo = this.interactionUtils.sentenceEqualsKeyword( keywords , sentence );
						if ( controllo ) {
							returnObj = InteractionUtils.detectFoglia( response, templateQuestions, null);
							if( fieldFlag!=null )
								returnObj.put( "fieldFlag" , fieldFlag );
							res.setContent( returnObj );
							return res;
						}
					}
					if ( !controllo ) {
						questionsId = this.interactionUtils.findSentence( sentence, template, questionId, controllo );   
					}
				}
			}
		}
		System.out.println( "questionsID : " + questionsId ); LOG.info( "questionsID : " + questionsId.toString() );
		if ( questionsId.size() == 0 ) {
			returnObj.put( "nextQuestionId", -1);
			returnObj.put( "foglia", "false" );
			returnObj.put( "ambiguity", "non è stata trovata alcuna corrispondenza" );
		}
		else if ( questionsId.size() == 1 ) {
			for ( Question question : template.getQuestions() ) {
				if ( question.getQuestionId().equals( questionsId.get( 0 ) ) ) {
					if ( question.getResponses().isEmpty() )
						returnObj.put( "foglia", "true" );
					else 
						returnObj.put( "foglia", "false" );
					returnObj.put( "nextQuestionId", questionsId.get( 0 ) );
					break;
				}
			}
		} 
		else if ( questionsId.size() > 1 ) 
			returnObj = this.interactionUtils.resolveAmbiguity( sentence, template, questionId );
		returnObj.put( "fieldFlag", fieldFlag );
		if ( returnObj.get( "nextQuestionId" ).equals( -1 ) && !returnObj.get( "ambiguity" ).toString().contains( "forse cercavi" ) )  {
			Context context = this.contextservice.findContextByTemplateId( template.getTemplateId() );
			if( context != null ) {
				LOG.info( "[RETRIEVING] context : " + context.getID() );
				String language = context.getLanguage().toString();
				LOG.info( "[RETRIEVING] language : " + context.getLanguage() );
				String env = "";
				LOG.info("[INFO] " + InetAddress.getLocalHost().toString());
				if ( InetAddress.getLocalHost().toString().contains( "dev" ) ) 
					env = "dev.vassistant.it";
				else if( InetAddress.getLocalHost().toString().contains( "test" ) )
					env = "test.vassistant.it";
				else if( InetAddress.getLocalHost().toString().contains( "demo" ) )
					env = "demo.vassistant.it";
		        String path = "https://" + env + ":8045/vvas-ir/templates/5e452e9942d01002ba6d4335/answers?question=" + originalSentence.replace( " " , "%20" ) + "&count=1&language=" + language ;
		        LOG.info( "[CONSUMING]  " + path ) ; 	System.out.println( "[CONSUMING]  " + path ) ;;
		        try {
		        	URL url = new URL( path ) ;
		        	HttpURLConnection conn = ( HttpURLConnection ) url.openConnection() ;
		            conn.setRequestMethod( "GET" );
		            InputStreamReader in = new InputStreamReader( conn.getInputStream() ) ;
		            BufferedReader br = new BufferedReader( in ) ;
		            String output ;
		            String json = "" ;
		            while ( ( output = br.readLine() ) != null ) 
		            	json += output + " " ;
		            conn.disconnect() ;
		            JSONParser parser = new JSONParser() ;
		            JSONObject content =  ( JSONObject ) parser.parse( json.replace( "[" , "" ).replace( "]" , "" ).trim() ) ;
		            LOG.info("[RESULT] " + content);
		            String uri = content.get( "uri" ).toString() ;
		            returnObj.put( "ambiguity" , "La soluzione alle tue domande è in questo link : " + uri ) ;
		            returnObj.put( "foglia" , "true" ) ;
		            returnObj.put( "nextQuestionId" , -2 ) ;
		            res.setContent( returnObj ) ;
		        } catch ( Exception e ) {
		        	System.out.println( "Exception in NetClientGet:- " + e ) ;
		        }	
			}
		}
		LOG.info( "[END] ControlledQuestion" ) ;
		res.setContent( returnObj ) ;
		return res ;
	}

	
	
	
	@SuppressWarnings({ "unused", "unchecked" })
	public VasResponse mixedQuestion( String sentence, Template template, Integer questionId ) throws IOException, InvalidFormatException {
		LOG.info( "[START] mixedQuestion" );
		JSONObject returnObject = new JSONObject();
		VasResponse vasresponse = new VasResponse( Integer.valueOf( 0 ), "OK", null );
		List< Question > questions = template.getQuestions();
		JSONObject returnoObject = new JSONObject();
		for ( Question question : questions ) {
			if ( question.getQuestionId().equals( questionId ) ) {
				List< Response > responses = question.getResponses();
				for ( Response response : responses ) {
					if ( response.getKeywords() == null ) {
						List< String > nullKeyword = new ArrayList< String >();
						response.setKeywords( nullKeyword );
					}
					if ( response.getKeywords().isEmpty() ) {
						for ( Question q : template.getQuestions() ) {
							if( q.getQuestionId().equals( question.getResponses().get( 0 ).getNextQuestionId() ) ) {
								if ( q.getResponses().isEmpty() )  
									returnObject.put( "foglia", "true" );
								else 
									returnObject.put( "foglia", "false" );
								returnObject.put( "nextQuestionId", question.getResponses().get( 0 ).getNextQuestionId() );
								vasresponse.setContent( returnObject );
								}
							}
						}
					}
				}
			}
		if ( vasresponse.getContent() == null ) 
			vasresponse = controlledQuestion( sentence, template, questionId );
		LOG.info( "[END] mixedQuestion" );
		return vasresponse;
	}

	
	
	
	@SuppressWarnings("unchecked")
	public  VasResponse freeQuestion( Question question ) {
		LOG.info( "[START] freeQuestion" );
		JSONObject returnObject = new JSONObject();
		VasResponse vasresponse = new VasResponse( Integer.valueOf( 0 ), "OK", null );
		if ( question.getLastQuestion() != null && question.getLastQuestion() )
			returnObject.put( "foglia", "true" );
		else {
			returnObject.put( "foglia", "false" );
			returnObject.put( "nextQuestionId", question.getResponses().get( 0 ).getNextQuestionId());
		}
		vasresponse.setContent( returnObject );
		LOG.info( "[END] freeQuestion" );
		return vasresponse;
	}
	
	
	
	
	@SuppressWarnings( "unchecked" )
	public VasResponse questionarioInvalsi( Template template, Object sentence, Integer questionId ) throws IOException {
		LOG.info( "[START] questionarioInvalsi" );
		VasResponse res = new VasResponse( Integer.valueOf( 0 ), "OK", null );
		JSONObject returnObj = new JSONObject();
		sentence = sentence.toString().trim().toLowerCase();
		List<Question> templateQuestions = template.getQuestions();
		List<Integer> questionsId = null;
		findSentenceForInvalsi f = new findSentenceForInvalsi();
		Integer weight = null;
		String dataType = null;
		List<Integer> score = new ArrayList<Integer>();
		for ( Question question : templateQuestions ) {
			Integer questId = question.getQuestionId();
			if ( questId.equals( questionId ) ) {
				dataType = question.getDataType();
				if ( dataType.equals( "S" ) ) {
					weight = question.getWeight();
					List< Response > responses = question.getResponses();
					boolean controllo = false;
					for ( Response response : responses ) {
						List< String > keywords = response.getKeywords();
						controllo = this.interactionUtils.sentenceEqualsKeyword( keywords, sentence.toString() );
						if ( controllo ) {
							res.setContent( InteractionUtils.detectFoglia( response, templateQuestions, weight ) );
							return res;
						}
					}
					if ( !controllo ) {
						f = this.interactionUtils.findSentenceForInvalsi( sentence.toString(), template, questionId, controllo );
						questionsId = f.getQuestionsId();
						score = f.getScore();
					}
					System.out.println( "questionsID : " + questionsId ) ;
					LOG.info( "questionsID : " + questionsId.toString() );
					System.out.println( questionsId.size() );
					if ( questionsId.size() == 0 ) {
						returnObj.put( "nextQuestionId", -1 );
						returnObj.put( "foglia", "false" );
						returnObj.put( "ambiguity", "non è stata trovata alcuna corrispondenza" );
					} 
					else if ( questionsId.size() == 1 ) {
						for ( Question question2 : template.getQuestions() ) {
							if ( question2.getQuestionId().equals( questionsId.get( 0 ) ) ) {
								if ( question2.getResponses().isEmpty() )
									returnObj.put( "foglia", "true" );
								else
									returnObj.put( "foglia", "false" );
								returnObj.put( "nextQuestionId", questionsId.get( 0 ) );
								returnObj.put( "score", score.get( 0 ) * weight );
								returnObj.put( "weight", weight );
								res.setContent( returnObj );
							}
						}
					} 
					else if ( questionsId.size() > 1 )
						returnObj = this.interactionUtils.resolveAmbiguity( sentence.toString(), template, questionId );
					res.setContent( returnObj );
					return res;
				} else {
					List< Double > nums = this.interactionUtils.stringToNum( sentence.toString() );
					if ( nums.size() == 0 )
						returnObj.put( "ambiguity", "non è stato trovato alcun valore numerico nella frase" );
					else if ( nums.size() >1 )
						returnObj.put( "ambiguity", "è stato trovato più di un valore numerico nella frase" );
					else {
						Double number = nums.get( 0 );
						Response response = question.getResponses().get( 0 );
						Double targetNumber = Double.valueOf( response.getValue().toString() );
						String operator = response.getOperator();
						returnObj.put( "weight", question.getWeight() );
						returnObj.put( "nextQuestionId", response.getNextQuestionId() );
						for ( Question quest  : template.getQuestions()) {
							if( response.getNextQuestionId().equals(quest.getQuestionId() ) ) {
								if ( quest.getResponses().isEmpty() || quest.getResponses() == null )
									returnObj.put("foglia", "true" );
								else
									returnObj.put("foglia", "false" );		
							}
						}
						if ( (operator.equals( ">" ) && number > targetNumber  ) ||
								(operator.equals( ">=" ) && number >= targetNumber  ) ||
								(operator.equals( "==" ) && number.equals( targetNumber )  ) ||
								(operator.equals( "<=" ) && number <= targetNumber  ) ||
								(operator.equals( "<" ) && number < targetNumber  ) 
								) 
							returnObj.put("score", response.getScore() * question.getWeight() );
						else 
							returnObj.put("score", 0 );
					}
					res.setContent(returnObj);
				}
			}
		}
		LOG.info( "[END] questionarioInvalsi" );
		return res;
	}
	
	
	
	
	@SuppressWarnings( "unchecked" )
	public VasResponse Anagrafica( Template template, String sentence, Integer questionId ) throws IOException, InvalidFormatException {
		LOG.info( "[START] Anagrafica" );
		JSONObject returnObject = new JSONObject();
		VasResponse vasresponse = new VasResponse( Integer.valueOf( 0 ), "OK", null );
		List< Question > questions = template.getQuestions();
		for ( Question question : questions ) {
			if ( question.getQuestionId().equals( questionId ) ) {
				String fieldFlag = question.getFieldFlag();
				List< Response > responses = question.getResponses();
				for ( Response response : responses ) {
					if ( response.getKeywords() == null ) {
						List< String > nullKeyword = new ArrayList< String >();
						response.setKeywords( nullKeyword );
					}
					if ( response.getKeywords().isEmpty() ) {
						for ( Question q : template.getQuestions() ) {
							if( q.getQuestionId().equals( question.getResponses().get( 0 ).getNextQuestionId() ) ) {
								returnObject.put( "fieldFlag", fieldFlag );
								if ( q.getResponses().isEmpty() )  
									returnObject.put( "foglia", "true" );
								else 
									returnObject.put( "foglia", "false" );
								returnObject.put( "nextQuestionId", question.getResponses().get( 0 ).getNextQuestionId());
								vasresponse.setContent( returnObject );
								return vasresponse;
								}
							}
						}
					}
				}
			}
		if ( vasresponse.getContent() == null ) 
			vasresponse = controlledQuestion( sentence, template, questionId );
		LOG.info( "[END] Anagrafica" );
		return vasresponse;
	}
	
	
	
	
}
