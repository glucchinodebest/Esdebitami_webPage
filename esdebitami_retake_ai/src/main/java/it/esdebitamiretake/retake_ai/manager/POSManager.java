/**
 * 
 */
package it.esdebitamiretake.retake_ai.manager;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.esdebitamiretake.retake_ai.fileutils.FileChk;
import it.esdebitamiretake.retake_ai.manager.handler.HandlePOS;


/**
 * @author Utente
 *
 */
@Component
public class POSManager implements IManager<HandlePOS,ArrayList<String>>{

	@Autowired
	private FileChk fileChk;
	
	@Value("${ai.work.path}")
	private URI workPath;
	
	@Value("${ai.work.filename.posmaxentbin}")
	private String posmaxentbinFn;
	
	
	public POSManager() {
	}

	@Override
	public void manage(HandlePOS responseHandler, ArrayList<String> anyInput) {
		File baseDirectory = new File(workPath);
		File posmaxentBinFile = fileChk.retriveFile(baseDirectory, this.posmaxentbinFn);
		responseHandler.setPosmaxentBinFile(posmaxentBinFile);
		responseHandler.handleBusiness(anyInput);
	}

}
