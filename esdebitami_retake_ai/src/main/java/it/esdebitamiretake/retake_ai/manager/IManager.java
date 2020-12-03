/**
 * 
 */
package it.esdebitamiretake.retake_ai.manager;

import it.esdebitamiretake.retake_ai.manager.IManager.IManagerResponseHandler;

/**
 * @author Utente
 *
 */
public interface IManager<Z extends IManagerResponseHandler<INPUT>,INPUT> {

	public abstract void manage(Z responseHandler,INPUT anyInput);
	
	public interface IManagerResponseHandler<INPUT>{
		public abstract void handleBusiness(INPUT inputFromManager);
	}
;}
