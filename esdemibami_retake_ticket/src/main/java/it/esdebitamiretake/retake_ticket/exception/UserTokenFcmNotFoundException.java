package it.esdebitamiretake.retake_ticket.exception;

public class UserTokenFcmNotFoundException extends Throwable {

	private static final long serialVersionUID = -481259014471522044L;

	public UserTokenFcmNotFoundException(String resource, String id) {

		super(String.format("%s [%s] not found", resource, id));
	}

	public UserTokenFcmNotFoundException(String resource) {

		super(String.format("No %s found", resource));
	}
	
	public UserTokenFcmNotFoundException() {
	}
	
}


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\exception\UserTokenFcmNotFoundException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */