package it.esdebitamiretake.retake_ir.scraping.nlp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NLFactory {
	
	private final Map<Language,NLPipeline>pipelines;
	private static NLFactory instance;
	
	private NLFactory() {
		
		pipelines=new HashMap<>();
	}
	
	public static NLFactory getInstance() {
		
		if(instance==null)
			instance=new NLFactory();
		
		return instance;
	}
	
	public synchronized NLPipeline loadPipeline(String iso) throws IOException, NLFactoryException {
		
		Language language;
		
		try {
			language=Language.valueOf(iso.toUpperCase());
		}
		catch(IllegalArgumentException e) {
			throw new NLFactoryException(iso);
		}
		
		NLPipeline pipeline = pipelines.get(language);
		
		if(pipeline==null) {
			pipeline=new NLPipeline(language);
			pipelines.put(language, pipeline);
		}
			
		return pipeline;
	}
	
	public class NLFactoryException extends Exception{

		private static final long serialVersionUID = 8816821228666953487L;
		
		NLFactoryException(String lang){
			super(String.format("Language %s not supported.", lang));
		}
	}
}
