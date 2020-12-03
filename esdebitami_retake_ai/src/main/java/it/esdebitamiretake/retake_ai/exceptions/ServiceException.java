package it.esdebitamiretake.retake_ai.exceptions;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Errors error;

	private final Class<?> clazz;

	private final Exception ex;

	
	public ServiceException(Class<?> clazz, Exception ex, Errors error) {
		super(error.getMessage());
		this.error = error;
		this.clazz = clazz;
		this.ex = ex;
	}

	public ServiceException(Errors error) {
		super(error.getMessage());
		this.error = error;
		this.ex = null;
		this.clazz = null;
	}

	public Integer getCode() {
		return this.error.getCode();
	}

	public HttpStatus getStatus() {
		return this.error.getStatus();
	}

	public Errors getError() {
		return this.error;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public Exception getEx() {
		return ex;
	}

}
