package it.esdebitamiretake.retake_ir.search.semantic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.google.gson.Gson;

import it.esdebitamiretake.retake_ir.search.Language;

public class PyClient extends RestClient {


	public PyClient(String url) throws MalformedURLException {
		
		super(url);
	}


	public double[] train(String instance,Language language) throws IOException {
		
		double[] we=null;
		
		try {
			
			Map<String,Object>body=new HashMap<>();
			body.put("language", language);
			body.put("instance", instance);
			Gson gson=new Gson();
			
			RestResponse response=this.post("train", gson.toJson(body));
			
			if(response.getStatus()==HttpStatus.OK.value()) 
				we= gson.fromJson(response.getPayload(), double[].class);
						
		} catch (URISyntaxException e) {
	
		}
		
		return we;
	}
	

	public double[] score(String instance,Language language,List<double[]> corpus) throws IOException {
		
		double[] we=null;
		
		try {
			
			Map<String,Object>body=new HashMap<>();
			body.put("language", language);
			body.put("instance", instance);
			body.put("corpus", corpus);
			
			Gson gson=new Gson();
			RestResponse response=this.post("score", gson.toJson(body));
			
			if(response.getStatus()==HttpStatus.OK.value()) 
				we= gson.fromJson(response.getPayload(), double[].class);
						
		} catch (URISyntaxException e) {
	
		}
		
		return we;
	}
}
