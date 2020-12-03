/**
 * 
 */
package it.esdebitamiretake.retake_ai.manager;

import java.io.File;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.esdebitamiretake.retake_ai.fileutils.FileChk;
import it.esdebitamiretake.retake_ai.manager.handler.HandleSentenceDetection;


/**
 * @author Utente
 *
 */
@Component
public class SentenceDetectorManager implements IManager<HandleSentenceDetection,String>{

	@Autowired
	private FileChk fileChk;
	
	@Value("${ai.work.path}")
	private URI workPath;
	
	@Value("${ai.work.filename.sentbin}")
	private String sentbinFn;
	
	@Value("${ai.work.filename.tokenbin}")
	private String tokenbinFn;
	
	public SentenceDetectorManager() {
	}

	@Override
	public void manage(HandleSentenceDetection responseHandler, String anyInput) {
		File baseDirectory = new File(workPath);
		File sentBinfile = fileChk.retriveFile(baseDirectory, this.sentbinFn);
		File tokenBinfile = fileChk.retriveFile(baseDirectory, this.tokenbinFn);
		responseHandler.setSentBinFile(sentBinfile);
		responseHandler.setTokenBinfile(tokenBinfile);
		responseHandler.handleBusiness(anyInput);
	}	


}
