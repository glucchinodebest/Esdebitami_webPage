package it.esdebitamiretake.retake_ai.controller.dataset;
public class ResourceNotFoundException extends Exception{
	
	private static final long serialVersionUID = 7461823068396374490L;

	public ResourceNotFoundException(String resource, String id) {

		super(String.format("%s [%s] not found", resource, id));
	}

	public ResourceNotFoundException(String resource) {

		super(String.format("No %s found", resource));
	}

	
	public ResourceNotFoundException(String resource, String field, String type) {

		super(String.format("Condition/Conditions on the %s, for %s (type %s), is/are not respected", field, resource, type));
	}
}