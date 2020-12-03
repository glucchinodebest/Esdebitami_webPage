/**
 * 
 */
package it.esdebitamiretake.retake_ai.manager;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import it.esdebitamiretake.retake_ai.manager.handler.HandleStemming;


/**
 * @author Utente
 *
 */
@Component
public class StemmingManager implements IManager<HandleStemming,ArrayList<String>>{
	
	
	public StemmingManager() {
	}

	@Override
	public void manage(HandleStemming responseHandler, ArrayList<String> anyInput) {
		responseHandler.handleBusiness(anyInput);
	}

}
