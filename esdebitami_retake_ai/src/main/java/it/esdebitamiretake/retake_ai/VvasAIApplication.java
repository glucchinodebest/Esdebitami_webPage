package it.esdebitamiretake.retake_ai;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VvasAIApplication {

	private static Logger logger = LoggerFactory.getLogger("com.pccube.vvas.ai");
	
	public static File currentDirFile;
	public static String pypath;
	
	public static void main(String[] args) throws IOException {
		logger.info("             #     vAssistant AI Service - Application Start    #");
		SpringApplication sa = new SpringApplication(VvasAIApplication.class);
		currentDirFile=new File(".");
		pypath=currentDirFile.getCanonicalPath()+"\\src\\main\\java\\com\\pccube\\vas\\ai\\py";
		sa.run(args);
	}

}
