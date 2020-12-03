package it.esdebitamiretake.retake_ir.scraping.model;

public class Resource{

	private final String context;
	private final String uri;
	private final String reference;
	
	public Resource(String context,String uri,String reference) {
		
		this.context=context;
		this.uri = uri.toLowerCase();
		this.reference=reference;
	}

	public String getUri() {
		
		return uri;
	}
	
	public String getContext() {
		
		return context;
	}
	
	public String getReference() {
		
		return reference;
	}
	
	@Override
	public boolean equals(Object o) {

		if (o != null && o instanceof Resource)
			return ((Resource) o).getUri().equals(this.getUri());
		
		return false;
	}
}