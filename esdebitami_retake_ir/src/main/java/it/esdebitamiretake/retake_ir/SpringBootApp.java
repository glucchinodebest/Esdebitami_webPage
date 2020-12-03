package it.esdebitamiretake.retake_ir;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootApp {

	private static Logger logger = LoggerFactory.getLogger("com.vvas.project.ir");

	public static void main(String[] args) {

		logger.info("             ##########################################################");
		logger.info("             #     vAssistant IR Service - Application Start   	   #");
		logger.info("             ##########################################################");

		SpringApplication.run(SpringBootApp.class, args);
	}
}