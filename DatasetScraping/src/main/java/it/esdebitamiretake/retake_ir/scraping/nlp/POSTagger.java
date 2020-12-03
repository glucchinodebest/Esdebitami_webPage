package it.esdebitamiretake.retake_ir.scraping.nlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

class POSTagger {

	private final POSModel model;
	
	POSTagger(Language language) throws IOException {
	
		Path path=Paths.get("models",String.format("%s-pos-maxent.bin",language.name().toLowerCase()));
		
		if(!Files.exists(path))
			path=Paths.get("models","en-pos-maxent.bin");
			
		try(InputStream inputStream=getClass().getClassLoader().getResourceAsStream("models/en-pos-maxent.bin")){
			model = new POSModel(inputStream);
		} 
	}

	String[] pos(String...words) {

	    return new POSTaggerME(model).tag(words);
	}
}