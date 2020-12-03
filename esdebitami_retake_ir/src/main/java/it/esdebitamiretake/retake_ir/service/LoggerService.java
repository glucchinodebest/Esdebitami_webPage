package it.esdebitamiretake.retake_ir.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LoggerService {

	enum Status {
		OK, ERROR, INFO
	}

	private final Logger logger;

	LoggerService(Class<?> cls) {

		logger = LoggerFactory.getLogger(cls);
	}

	protected void log(Status status, String message) {

		logger.debug(String.format("[%s] %s (%s)", logger.getClass().getName(), status, message));
	}
}