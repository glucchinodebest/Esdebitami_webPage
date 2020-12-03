package it.esdebitamiretake.retake_ticket.controller;

public class ResourceAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 9073738667450295823L;

	public ResourceAlreadyExistsException(String resource,String id) {
		
		super(String.format("%s [%s] already exists", resource,id));
	}
	
}
