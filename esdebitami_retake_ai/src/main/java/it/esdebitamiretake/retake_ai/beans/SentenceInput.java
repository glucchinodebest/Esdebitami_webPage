/**
 * 
 */
package it.esdebitamiretake.retake_ai.beans;

import javax.validation.constraints.NotNull;

/**
 * @author Utente
 *
 */
public class SentenceInput {

	@NotNull
	private String sententece;
	
	public SentenceInput() {
	}
	
	public String getSententece() {
		return sententece;
	}
	
	public void setSententece(String sententece) {
		this.sententece = sententece;
	}

}
