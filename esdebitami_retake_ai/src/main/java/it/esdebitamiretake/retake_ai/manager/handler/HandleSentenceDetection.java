/**
 * 
 */
package it.esdebitamiretake.retake_ai.manager.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.esdebitamiretake.retake_ai.exceptions.Errors;
import it.esdebitamiretake.retake_ai.exceptions.ServiceException;
import it.esdebitamiretake.retake_ai.manager.IManager.IManagerResponseHandler;
import it.esdebitamiretake.retake_ai.manager.listener.IOnResultListener;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * @author Utente
 *
 */
public class HandleSentenceDetection implements IManagerResponseHandler<String> {

	private static Logger LOG = LoggerFactory.getLogger(HandleSentenceDetection.class);
	
	private File sentBinFile;
	private File tokenBinfile;
	private IOnResultListener<ArrayList<String>> resultListener;
	
	public HandleSentenceDetection() {
	}
	
	public HandleSentenceDetection(IOnResultListener<ArrayList<String>> resultListener) {
		this.resultListener = resultListener;
	}
	
	public void setSentBinFile(File sentBinFile) {
		this.sentBinFile = sentBinFile;
	}

	public void setTokenBinfile(File tokenBinfile) {
		this.tokenBinfile = tokenBinfile;
	}

	@Override
	public void handleBusiness(String inputFromManager) {
		ArrayList<String> resultToManage = new ArrayList<String>(0);
		FileInputStream sentBinStream = null;
		FileInputStream tokenBinStream = null;
		try {
			sentBinStream = new FileInputStream(this.sentBinFile);
			SentenceModel sentenceModel = new SentenceModel(sentBinStream);
			SentenceDetectorME sdetector = new SentenceDetectorME(sentenceModel);			 
		    String sentences[] = sdetector.sentDetect(inputFromManager);
		    
//		    SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
		    tokenBinStream = new FileInputStream(this.tokenBinfile);
		    TokenizerModel tokenizerModel = new TokenizerModel(tokenBinStream);
		    TokenizerME tokenizer = new TokenizerME(tokenizerModel);
		    
		    for(String current : sentences) {
		    	LOG.info("PHRASE#=>"+current);
		    	String[] tokens = tokenizer.tokenize(current);
		    	for(String currentToken : tokens) {
		    		LOG.info("TOKEN##=>"+currentToken);
		    		resultToManage.add(currentToken);
		    	}
		    }
		    this.resultListener.onSuccess(resultToManage);
		} catch (IOException e) {
			this.resultListener.onFailure(new ServiceException(Errors.NOT_MANAGED));
		}finally {
			this.closeFiles(sentBinStream,tokenBinStream);
		}		
	}
	
	private void closeFiles(InputStream ...streams) {
		for(InputStream currentStream : streams) {
			if(!Objects.isNull(currentStream)) {
				try {
					currentStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
