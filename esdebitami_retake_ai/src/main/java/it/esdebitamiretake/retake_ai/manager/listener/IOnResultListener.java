/**
 * 
 */
package it.esdebitamiretake.retake_ai.manager.listener;

import it.esdebitamiretake.retake_ai.exceptions.ServiceException;

/**
 * @author Utente
 *
 */
public interface IOnResultListener<T> {

	public void onSuccess(T resultToManage);
	public void onFailure(ServiceException ex);
}
