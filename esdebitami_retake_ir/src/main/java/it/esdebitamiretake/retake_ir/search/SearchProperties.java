package it.esdebitamiretake.retake_ir.search;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("search")
public class SearchProperties {

	private String index;
	private String client;
	
	public String getIndex() {
		
		return index;
	}
	
	public String getClient() {
		
		return client;
	}
	
	public void setIndex(String index) {
		
		this.index=index;
	}
	
	public void setClient(String client) {
		
		this.client=client;
	}
	
	public static double[] getWeights(String id) {
		
		double[] weights=null;
		
		switch(id) {
		
		default:
			weights=new double[]{0.2,0.4,0.4};
			break;
			
		}
		
		return weights;
	}
}
