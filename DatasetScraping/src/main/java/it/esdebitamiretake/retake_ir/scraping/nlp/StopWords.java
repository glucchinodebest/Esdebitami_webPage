package it.esdebitamiretake.retake_ir.scraping.nlp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

class StopWords {

	private Set<String>stops=new HashSet<>();
	
	StopWords(Language language) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		
		Path path=Paths.get("models",String.format("%s.json",language.name().toLowerCase()));
		
		if(Files.exists(path))
			stops.addAll(Arrays.asList(new Gson().fromJson(new FileReader(path.toFile()), String[].class)));
	}
	
	String[] stop(String...words) {
		
		List<String>tokens=new ArrayList<String>();
		
		for(String word:words)
			if(!word.isEmpty() && !stops.contains(word.toLowerCase()))
				tokens.add(word);
		
		return tokens.toArray(new String[]{});
	}
}
