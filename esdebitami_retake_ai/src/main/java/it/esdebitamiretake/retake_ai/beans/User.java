/**
 * 
 */
package it.esdebitamiretake.retake_ai.beans;

import javax.validation.constraints.NotNull;

/**
 * @author Utente
 *
 */
public class User {

	@NotNull
	private String nome;
	@NotNull
	private String cognome;
	@NotNull
	private Long id;
	
	public User() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
