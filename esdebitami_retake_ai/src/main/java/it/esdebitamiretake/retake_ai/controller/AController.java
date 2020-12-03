/**
 * 
 */
package it.esdebitamiretake.retake_ai.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.esdebitamiretake.retake_ai.response.VasResponse;


/**
 * @author Utente
 *
 */
public abstract class AController {

	private static Logger logger = LoggerFactory.getLogger(AController.class);
	
	public AController() {
	}

	@GetMapping(value="/")
	@ResponseBody
    public ResponseEntity<VasResponse> home(HttpServletRequest request, HttpServletResponse response) {
		logger.info("################### home ###################");
		return buildResponseOK("Welcome to "+request.getServletContext().getContextPath());
    }
	
	protected ResponseEntity<VasResponse> buildResponseOK(Object payload){
		VasResponse vasResponse = new VasResponse();
		vasResponse.setContent(payload);
		vasResponse.setStatusCode(HttpStatus.OK.value());
		vasResponse.setStatusMessage(HttpStatus.OK.getReasonPhrase());
		return new ResponseEntity<VasResponse>(vasResponse, HttpStatus.OK);
	}
}
