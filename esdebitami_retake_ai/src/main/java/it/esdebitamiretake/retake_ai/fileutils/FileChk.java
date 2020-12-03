/**
 * 
 */
package it.esdebitamiretake.retake_ai.fileutils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Utente
 *
 */
@Component
public class FileChk {

	private static Logger logger = LoggerFactory.getLogger(FileChk.class);
	
	public FileChk() {
	}

	public boolean existFilesIntoWorkPathWithExpression(File workPath,ArrayList<File> filesRetrieved,String regexFilter){
		boolean rtrnValue = false;
		if(workPath.exists()){
			FilenameFilter fnFilter = new FileNameRegexFilter(regexFilter);
			File[] filesToWork = workPath.listFiles(fnFilter);		
			if(filesToWork!=null && filesToWork.length>0){
				rtrnValue = true;
				filesRetrieved.addAll(Arrays.asList(filesToWork));
			}else{
				logger.info("#=>WARNING: There are not files matching to the pattern["+regexFilter+"] into folder:("+workPath.getPath()+")");
			}
		}else{
			logger.info("#=>WARNING: path does not exist:("+workPath.getPath()+")");
		}
		return rtrnValue;
	}
	
	public File retriveFile(File workPath, String regexFilter){
		File rtrnValue = null;
		if(workPath.exists()){
			FilenameFilter fnFilter = new FileNameRegexFilter(regexFilter);
			File[] filesToWork = workPath.listFiles(fnFilter);		
			if(filesToWork!=null && filesToWork.length==1){
				rtrnValue = filesToWork[0];
			}else{
				logger.info("#=>WARNING: There are not files matching to the pattern["+regexFilter+"] into folder:("+workPath.getPath()+")");
			}
		}else{
			logger.info("#=>WARNING: path does not exist:("+workPath.getPath()+")");
		}
		return rtrnValue;
	}
	
	private static class FileNameRegexFilter implements FilenameFilter{		
		Pattern pattern = null;
	    
		public FileNameRegexFilter(String _regexFileName) {
			this.pattern = Pattern.compile(_regexFileName);
		}
		
		@Override
		public boolean accept(File dir, String fileName) {	
			return this.pattern.matcher(fileName).matches();
		}		
	}
}
